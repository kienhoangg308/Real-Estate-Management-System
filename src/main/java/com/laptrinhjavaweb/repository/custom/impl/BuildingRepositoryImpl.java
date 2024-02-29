package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.SpecialSearchParamsEnum;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.QueryBuilderUtils;
import com.laptrinhjavaweb.utils.ValidateUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    List<BuildingEntity> results = new ArrayList<>();
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private void buildingQueryWithJoin(Map<String,Object> params, List<String> rentTypes,StringBuilder joinQuery, StringBuilder whereQuery) {


        String district = (String) params.get("district");
        Integer rentAreaFrom = (Integer) params.get("rentAreaFrom");
        Integer rentAreaTo = (Integer) params.get("rentAreaTo");
        Long staff = (Long) params.get("staff");

        if (ValidateUtils.isValid(district)) {
            joinQuery.append(" inner join district on district.id = building.districtid");
            whereQuery.append(" and district.code = '" + district + "'");
        }
        if (ValidateUtils.isValid(rentAreaFrom) || ValidateUtils.isValid(rentAreaTo)) {
            joinQuery.append(" inner join rentarea ra on ra.buildingid = building.id");
            if (ValidateUtils.isValid(rentAreaFrom)) {
                whereQuery.append(" and ra.value >= " + rentAreaFrom);
            }
            if (ValidateUtils.isValid(rentAreaTo)) {
                whereQuery.append(" and ra.value <= " + rentAreaTo);
            }
        }
        if (ValidateUtils.isValid(staff)) {
            whereQuery.append(" and user.username like '%" + staff + "%'");
            whereQuery.append(" and ab.staffid = " + staff);
        }

        if (rentTypes != null && !rentTypes.isEmpty()) {
            joinQuery.append(" inner join buildingrenttype br on br.buildingid = building.id")
                    .append(" inner join renttype rt on rt.id = br.renttypeid");
            whereQuery.append(" and(");
            List<String> resultRentTypes = new ArrayList<>();
            for (String item : rentTypes) {
                resultRentTypes.add(" rt.code LIKE '%" +item+ "%'");
            }
            whereQuery.append(String.join(" OR ", resultRentTypes));
        }
    }

    private void buildingQueryWithoutJoin(Map<String,Object> params, StringBuilder whereQuery) {

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            if (!getSpecialSearchParams().contains(key)) {
                String column = SystemConstant.BUILDING_ALIAS + key;
                Object value = entry.getValue();

                if (entry.getValue() instanceof String) {
                    whereQuery.append(QueryBuilderUtils.withLike(column, value.toString()));
                } else if (entry.getValue() instanceof Integer) {
                    whereQuery.append(QueryBuilderUtils.withOperator(column, value, SystemConstant.AND_STATEMENT));
                }
            }
        }

    }


    private List<String> getSpecialSearchParams() {
        List<String> params = new ArrayList<>();

        for (SpecialSearchParamsEnum item : SpecialSearchParamsEnum.values()) {
            params.add(item.getValue().toLowerCase());
        }

        return params;
    }

    @Override
    public List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> rentType){

            StringBuilder sql = new StringBuilder(SystemConstant.SELECT_FROM_BUILDING);

            StringBuilder joinQuery = new StringBuilder();
            StringBuilder whereQuery = new StringBuilder();

            buildingQueryWithJoin(params, rentType, joinQuery, whereQuery);
            buildingQueryWithoutJoin(params, whereQuery);

            sql.append(joinQuery).append(SystemConstant.WHERE_ONE_EQUAL_ONE).append(whereQuery).append(SystemConstant.GROUP_BY_BUILDING_ID);
            Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
            return query.getResultList();

    }

    private void buildSqlCommonUsingBuilder(BuildingSearchBuilder builder,StringBuilder whereQuery) {

        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field:fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if(!fieldName.equals("type") && !fieldName.startsWith("rentArea")
                        && !fieldName.equals("district") && !fieldName.equals("staffId")
                        && !fieldName.startsWith("rentPrice")) {
                    Object objectValue = field.get(builder);
                    if (ValidateUtils.isValid(objectValue)) {
                        if(field.getType().getName().equals("java.lang.String")) {
                            whereQuery.append(" and building."+fieldName.toLowerCase()+" like '%"+objectValue+"%' ");
                        }else if(field.getType().getName().equals("java.lang.Integer")) {
                            whereQuery.append(" and building."+fieldName.toLowerCase()+" = "+objectValue+"" );
                        }else if(field.getType().getName().equals("java.lang.Long")) {
                            whereQuery.append(" and building."+fieldName.toLowerCase()+" = "+objectValue+"" );
                        }
                    }
                }
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buildSqlSpecialUsingBuilder(BuildingSearchBuilder builder,StringBuilder joinQuery,StringBuilder whereQuery) {
        String district = builder.getDistrict();
        Integer rentAreaFrom = builder.getRentAreaFrom();
        Integer rentAreaTo = builder.getRentAreaTo();
        String[] rentTypes = builder.getType();
        Long staffId = builder.getStaffId();
        Integer rentPriceFrom = builder.getRentPriceFrom();
        Integer rentPriceTo = builder.getRentPriceTo();

        if(ValidateUtils.isValid(staffId)){
            joinQuery.append(" inner join buildingassignment on buildingassignment.buildingid = building.id");
            whereQuery.append(" and buildingassignment.userid = " + staffId + " ");
        }
        if(ValidateUtils.isValid(district)){
            whereQuery.append(" and building.district = '" + district + "' ");
        }
        if (ValidateUtils.isValid(rentAreaFrom) || ValidateUtils.isValid(rentAreaTo)) {
            joinQuery.append(" inner join rentarea ra on ra.buildingid = building.id");
            if (ValidateUtils.isValid(rentAreaFrom)) {
                whereQuery.append(" and ra.value >= " + rentAreaFrom);
            }
            if (ValidateUtils.isValid(rentAreaTo)) {
                whereQuery.append(" and ra.value <= " + rentAreaTo);
            }
        }
        if (ValidateUtils.isValid(rentPriceFrom) || ValidateUtils.isValid(rentPriceTo)) {
            if (ValidateUtils.isValid(rentPriceFrom)) {
                whereQuery.append(" and building.rentprice >= " + rentPriceFrom);
            }
            if (ValidateUtils.isValid(rentPriceTo)) {
                whereQuery.append(" and building.rentprice <= " + rentPriceTo);
            }
        }
        if (ValidateUtils.areValid(rentTypes)) {
            whereQuery.append(" and (");
            List<String> resultRentTypes = new ArrayList<>();
            for (String item : rentTypes) {
                resultRentTypes.add(" building.type LIKE '%" +item+ "%' ");
            }
            whereQuery.append(String.join(" OR ", resultRentTypes));
            whereQuery.append(")");
        }

    }

    @Override
    public List<BuildingEntity> searchBuilding(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
        StringBuilder sql = new StringBuilder(SystemConstant.SELECT_FROM_BUILDING);

        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();

        //buildingQueryWithoutJoin(params, whereQuery);
        buildSqlSpecialUsingBuilder(buildingSearchBuilder, joinQuery, whereQuery);
        buildSqlCommonUsingBuilder(buildingSearchBuilder, whereQuery);

        sql.append(joinQuery).append(SystemConstant.WHERE_ONE_EQUAL_ONE).append(whereQuery).append(SystemConstant.GROUP_BY_BUILDING_ID);

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);

        //return query.getResultList();
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return query.getResultList();
    }

    @Override
    public List<BuildingEntity> search(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder(SystemConstant.SELECT_FROM_BUILDING);

        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();

        //buildingQueryWithoutJoin(params, whereQuery);
        buildSqlSpecialUsingBuilder(buildingSearchBuilder, joinQuery, whereQuery);
        buildSqlCommonUsingBuilder(buildingSearchBuilder, whereQuery);

        sql.append(joinQuery).append(SystemConstant.WHERE_ONE_EQUAL_ONE).append(whereQuery).append(SystemConstant.GROUP_BY_BUILDING_ID);

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);

        List<BuildingEntity> results = query.getResultList();

        //return query.getResultList();
        return results;
    }


    private String buildQueryFilter() {
        String sql = "SELECT * FROM building";
        return sql;
    }

    @Override
    public List<BuildingEntity> getAllBuildings(Pageable pageable) {
        StringBuilder sql = new StringBuilder(buildQueryFilter())
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        System.out.println("Final query: " + sql.toString());

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public void deleteUser(Long buildingId, Long userId) {

    }

    @Override
    public int countTotalItems() {
        String sql = buildQueryFilter();
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }




}
