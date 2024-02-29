package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.AssignmentInputModel;
import com.laptrinhjavaweb.dto.AssignmentOutputModel;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingEditDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.dto.searchOutput.BuildingSearchOutput;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictEnum;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingSearchOutput> findAll();

    //AssignmentOutputModel assignBuildingToStaffs(AssignmentInputModel assignmentInputModel);

    AssignmentOutputModel assignBuildingToStaffs(AssignmentInputModel assignmentInputModel);

    List<BuildingSearchOutput> findBuilding(BuildingDTO buildingDTO,Pageable pageable);

    List<BuildingSearchOutput> search(BuildingDTO buildingDTO);

    BuildingEditDTO editBuilding(BuildingEditDTO buildingEditDTO);


    BuildingEditDTO findById(Long id);

    void deleteItems(List<Long> ids);

    public List<BuildingTypesEnum> getTypeCode();

    public List<String> getType();

    public List<String> getDistrict();

    public List<DistrictEnum> getDistrictCode();

    List<BuildingSearchOutput> getAllBuildings(Pageable pageable);

    int countTotalItems();

    ResponseDTO loadStaff(Long buildingId);

    BuildingSearchOutput getBuildigSearch(BuildingDTO buildingDTO, Pageable pageable);


}
