package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.repository.custom.BuildingAssignmentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingAssignmentRepository extends BuildingAssignmentRepositoryCustom {
    //void deleteById(Long assignmentBuildingId);
    //BuildingAssignmentEntity getAssignmentBuilding(Long buildingId, Long aLong);

    //List<BuildingAssignmentEntity> findByBuilding(BuildingEntity buildingEntity);
}
