package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.repository.custom.BuildingAssignmentRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class BuildingAssignmentRepositoryImpl implements BuildingAssignmentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    //BuildingAssignmentEntity buildingAssignmentEntity = new BuildingAssignmentEntity();
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

//    @Override
//    public BuildingAssignmentEntity getAssignmentBuilding(Long buildingId, Long staffId) {
//
//        StringBuilder sql = new StringBuilder("select *" + " from buildingassignment "
//                + SystemConstant.WHERE_ONE_EQUAL_ONE +"");
//
//        if(ValidateUtils.isValid(buildingId)) {
//            sql.append(" and buildingassignment.buildingid like "+buildingId+ "");
//        }
//        if(ValidateUtils.isValid(staffId)) {
//            sql.append(" and buildingassignment.userid like "+staffId+ "");
//        }
//        Query query = entityManager.createNativeQuery(sql.toString(), BuildingAssignmentEntity.class);
//        return (BuildingAssignmentEntity) query.getSingleResult();
//
//    }
}
