package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.searchOutput.RentAreaSearchOutput;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.service.impl.RentAreaService;

import java.util.List;

public interface IRentAreaService {

    List<RentAreaSearchOutput> findByBuilding(BuildingEntity buildingEntity);

    void save(BuildingEntity buildingEntity, List<Integer> values);

    RentAreaEntity findOneByBuilding(BuildingEntity buildingEntity);

    void edit(BuildingEntity buildingEntity, List<Integer> values);

    void delete(BuildingEntity buildingEntity);
}
