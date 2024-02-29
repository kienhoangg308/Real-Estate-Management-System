package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.searchOutput.RentAreaSearchOutput;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.service.IRentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentAreaService implements IRentAreaService {

    @Autowired
    private RentAreaRepository rentAreaRepository;

    private List<Integer> findItemOfSourceNotTarget(List<Integer> source, List<Integer> target) {
        List<Integer> itemOfSourceButNotTarget = new ArrayList<>();
        for(int i =0;i<source.size();i++) {
            if(!target.contains(source.get(i))) {
                itemOfSourceButNotTarget.add(source.get(i));
            }
        }
        return itemOfSourceButNotTarget;
    }

    @Override
    public List<RentAreaSearchOutput> findByBuilding(BuildingEntity buildingEntity) {
        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuilding(buildingEntity);

        List<RentAreaSearchOutput> results = new ArrayList<>();
        for(RentAreaEntity item : rentAreaEntities) {
            RentAreaSearchOutput rentAreaSearchOutput = new RentAreaSearchOutput();
            rentAreaSearchOutput.setValue(item.getValue());
            results.add(rentAreaSearchOutput);
        }

        return results;
    }

    @Override
    public void save(BuildingEntity buildingEntity, List<Integer> values) {
        RentAreaEntity rentAreaEntity = new RentAreaEntity();
        for(Integer i : values){
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntity.setValue(i);
            rentAreaRepository.save(rentAreaEntity);
        }
    }

    @Override
    public RentAreaEntity findOneByBuilding(BuildingEntity buildingEntity) {
        RentAreaEntity rentAreaEntity = rentAreaRepository.findOneByBuilding(buildingEntity);
        return rentAreaEntity;
    }

    @Override
    public void edit(BuildingEntity buildingEntity, List<Integer> values) {
        List<Integer> source = new ArrayList<>();
        List<Integer> target = new ArrayList<>();
        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuilding(buildingEntity);

        for(Integer i : values){
            RentAreaEntity rentAreaEntity = new RentAreaEntity(i, buildingEntity);
            rentAreaRepository.save(rentAreaEntity);
        }

        for(RentAreaEntity rentAreaEntity : rentAreaEntities){
            rentAreaRepository.delete(rentAreaEntity.getId());
        }

    }

    @Override
    @Transactional
    public void delete(BuildingEntity buildingEntity) {
        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuilding(buildingEntity);
        List<Long> ids = new ArrayList<>();
        for(RentAreaEntity item: rentAreaEntities){
            ids.add(item.getId());
        }
        rentAreaRepository.deleteByIdIn(ids);
    }


}
