package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.*;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.dto.searchOutput.BuildingSearchOutput;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictEnum;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingAssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("buildingAPIOfAdmin")
@RequestMapping({"/api/building"})
@Slf4j
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;

    @PostMapping
    public BuildingEditDTO save(@RequestBody BuildingEditDTO buildingEditDTO) {
        return buildingService.editBuilding(buildingEditDTO);
    }



    @PostMapping("/assignment")
    public AssignmentOutputModel assignBuildingToStaffs(@RequestBody AssignmentInputModel input) {
        AssignmentOutputModel output = buildingService.assignBuildingToStaffs(input);
        return output;
    }



    @GetMapping({"/{buildingid}/staffs"})
    public ResponseDTO loadStaff(@PathVariable("buildingid") Long buildingId) {
        ResponseDTO result = buildingService.loadStaff(buildingId);
        return result;
    }

    @GetMapping
    public List<BuildingSearchOutput> findBuilding(@RequestBody BuildingDTO buildingDTO) {
        List<BuildingSearchOutput> results = buildingService.search(buildingDTO);
        return results;
    }

//    @GetMapping
//    public List<BuildingSearchOutput> findBuilding(@RequestParam Map<String, Object> params) {
//        BuildingDTO buildingDTO = BuildingConverter.convertParamsToDTO(params);
//        List<BuildingSearchOutput> results = buildingService.search(buildingDTO);
//        List<BuildingSearchOutput> buildingSearchOutputs = buildingService.findBuilding(buildingDTO, new PageRequest(buildingDTO.getPage() - 1, buildingDTO.getMaxPageItems()));
//        return buildingSearchOutputs;
//    }

    @GetMapping({"/server-side"})
    public ResponseEntity<List<BuildingSearchOutput>> getBuildings(@RequestParam Map<String, Object> params) {
        BuildingDTO buildingDTO = BuildingConverter.convertParamsToDTO(params);
        List<BuildingSearchOutput> buildingSearchOutputs = buildingService.findBuilding(buildingDTO, new PageRequest(buildingDTO.getPage() - 1, buildingDTO.getMaxPageItems()));
        return ResponseEntity.ok(buildingSearchOutputs);
    }



    @GetMapping("/all")
    public List<BuildingSearchOutput> findAll(){
        List<BuildingSearchOutput> results = buildingService.findAll();
        return results;
    }

    @GetMapping("/{buildingid}")
    public BuildingEditDTO findById(@PathVariable("buildingid") Long id){
        BuildingEditDTO buildingEditDTO = buildingService.findById(id);
        return buildingEditDTO;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBuildings(@RequestBody InputDTO inputDTO){
        buildingService.deleteItems(inputDTO.getBuildingIds());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/district")
    public List<DistrictEnum> getTypeCode(){
        return buildingService.getDistrictCode();
    }
}
