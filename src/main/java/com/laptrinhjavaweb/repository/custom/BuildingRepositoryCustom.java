package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BuildingRepositoryCustom {

    //List<BuildingAssignmentEntity> getAssignedStaffIds(Long buildingId);

    List<BuildingEntity> findBuilding(Map<String,Object> params, List<String> rentType);

    List<BuildingEntity> searchBuilding(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable);

    List<BuildingEntity> search(BuildingSearchBuilder buildingSearchBuilder);

    List<BuildingEntity> getAllBuildings(Pageable pageable);

    void deleteUser(Long buildingId, Long userId);

    int countTotalItems();
}
