package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity,Long> {

    List<RentAreaEntity> findByBuilding(BuildingEntity buildingEntity);

    RentAreaEntity findOneByBuilding(BuildingEntity buildingEntity);

    void deleteByIdIn(List<Long> ids);

    void delete(RentAreaEntity rentAreaEntity);

}
