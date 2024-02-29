package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.utils.ValidateUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private void buildSqlCommonUsingBuilder(CustomerSearchBuilder builder, StringBuilder whereQuery) {
        try {
            Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
            for (Field field:fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if(!fieldName.equals("staffId")){
                    Object objectValue = field.get(builder);
                    if (ValidateUtils.isValid(objectValue)) {
                        if(field.getType().getName().equals("java.lang.String")) {
                            whereQuery.append(" and customer."+fieldName.toLowerCase()+" like '%"+objectValue+"%' ");
                        }else if(field.getType().getName().equals("java.lang.Long")) {
                            whereQuery.append(" and customer."+fieldName.toLowerCase()+" = "+objectValue+"" );
                        }
                    }
                }
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buildSqlSpecialUsingBuilder(CustomerSearchBuilder customerSearchBuilder, StringBuilder joinQuery, StringBuilder whereQuery) {
        Long staffId = customerSearchBuilder.getStaffId();

        if(ValidateUtils.isValid(staffId)){
            joinQuery.append(" inner join customerassignment on customerassignment.customerid = customer.id");
            whereQuery.append(" and customerassignment.userid = " + staffId + " ");
        }
    }

    @Override
    public List<CustomerEntity> searchCustomer(CustomerSearchBuilder customerSearchBuilder, Pageable pageable) {
        StringBuilder sql = new StringBuilder(SystemConstant.SELECT_FROM_CUSTOMER);

        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();

        buildSqlSpecialUsingBuilder(customerSearchBuilder, joinQuery, whereQuery);
        buildSqlCommonUsingBuilder(customerSearchBuilder, whereQuery);

        sql.append(joinQuery).append(SystemConstant.WHERE_ONE_EQUAL_ONE).append(whereQuery).append(SystemConstant.GROUP_BY_CUSTOMER_ID);

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return query.getResultList();
    }

    @Override
    public List<CustomerEntity> search(CustomerSearchBuilder customerSearchBuilder) {
        StringBuilder sql = new StringBuilder(SystemConstant.SELECT_FROM_CUSTOMER);

        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();

        buildSqlSpecialUsingBuilder(customerSearchBuilder, joinQuery, whereQuery);
        buildSqlCommonUsingBuilder(customerSearchBuilder, whereQuery);

        sql.append(joinQuery).append(SystemConstant.WHERE_ONE_EQUAL_ONE).append(whereQuery).append(SystemConstant.GROUP_BY_CUSTOMER_ID);

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);

        return query.getResultList();
    }

}
