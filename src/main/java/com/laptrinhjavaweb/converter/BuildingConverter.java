package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingEditDTO;
import com.laptrinhjavaweb.dto.searchOutput.BuildingSearchOutput;
import com.laptrinhjavaweb.dto.searchOutput.RentAreaSearchOutput;
import com.laptrinhjavaweb.dto.searchOutput.RentTypeSearchOutput;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictEnum;
import com.laptrinhjavaweb.utils.BuildingTypeUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;



    public BuildingEntity convertFromBuildingEditDTOToEntity(BuildingEditDTO buildingEditDTO){
        //convertFromArrayToString(buildingEditDTO.getType());
        BuildingEntity result = modelMapper.map(buildingEditDTO, BuildingEntity.class);
        result.setType(convertFromArrayToString(buildingEditDTO.getType()));
        //result.setDistrict(districtEntity);
        return result;
    }

    private List<Integer> convertRentAreas(String rentArea){
        List<Integer> result = new ArrayList<>();
        String[] arrOfAreas = rentArea.split(",");
        for(int i =0; i< arrOfAreas.length;i++){
            result.add(Integer.parseInt(arrOfAreas[i]));
        }
        return result;
    }

    private String[] convertFromStringToArray(String string){
        String[] arr = string.split(",");
        return arr;
    }

    private static String getType(String type){
        List<String> results = new ArrayList<>();
        String[] typeSplit = type.split(",");
        for(String item: typeSplit){
            results.add(BuildingTypesEnum.valueOf(item).getValue());
        }
        return String.join(",",results);
    }

    public BuildingDTO convertToDto(BuildingEntity entity) {
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        return result;
    }

    public BuildingEntity convertToEntity(BuildingDTO dto) {
        BuildingEntity result = modelMapper.map(dto, BuildingEntity.class);
        return result;
    }

    public BuildingSearchOutput convertToBuildingSearchOutput(BuildingEntity buildingEntity) {
        BuildingSearchOutput buildingSearchOutput = modelMapper.map(buildingEntity, BuildingSearchOutput.class);
        //buildingSearchOutput.setRentArea(convertRentAreas(rentAreaSearchOutput));
        buildingSearchOutput.setAddress(buildingEntity.getStreet() + "-" + buildingEntity.getWard() + "-"+ DistrictEnum.valueOf(buildingEntity.getDistrict()).getValue());
        return buildingSearchOutput;
    }

    private static String convertRentType(List<RentTypeSearchOutput> rentTypeList){
        List<String> listOfRentTypes = new ArrayList<>();
        for(int i = 0; i<rentTypeList.size();i++) {
            listOfRentTypes.add(rentTypeList.get(i).getName());
        }
        return String.join(",", listOfRentTypes);
    }

    private static String convertRentAreas(List<RentAreaSearchOutput> rentAreaList) {
        List<String> listOfRentAreas = new ArrayList<>();
        for(int i = 0; i<rentAreaList.size();i++) {
            listOfRentAreas.add(rentAreaList.get(i).getValue().toString());
        }
          //String listString = listOfRentAreas.toString();
//        listString = listString.substring(1, listString.length()-1);
//        return listString;
        return String.join(",",listOfRentAreas);
    }

    private String convertFromArrayToString(String[] arr){
        List<String> items = new ArrayList<>();
        for(String i : arr){
            items.add(i);
        }
        return String.join(",",items);
    }

//    private BuildingEntity convertBuildingEntityValue(BuildingEntity updatedValue, BuildingEntity originalValue){
//        Field[] updateValues = updatedValue.class.getDeclaredFields();
//    }



//    public static BuildingSearchOutput convertToBuildingSearchOutput(BuildingEntity item) {
//        BuildingSearchOutput buildingSearchOutput = new BuildingSearchOutput();
//        buildingSearchOutput.setId(item.getId());
//        //List<RentAreaSearchOutput> rentAreas = rentAreaService.findById(item.getId());
//        buildingSearchOutput.setName(item.getName());
//        //buildingSearchOutput.setAddress(item.getStreet() + "-" + item.getWard() + "-" ); //item.getDistrict());
//
//        //buildingSearchOutput.setStaffs(convertStaffs(staffService.findById(item.getId())));
//        buildingSearchOutput.setDistrict(findDistrict(item.getId()));
////        buildingSearchOutput.setRentType(convertRentType(rentTypeService.findById(item.getId())));
////        buildingSearchOutput.setRentAreas(convertRentAreas(rentAreas));
////        buildingSearchOutput.setRentPrice(computeRentPrice(rentAreas, item.getRentPrice()));
////        buildingSearchOutput.setNumberOfBasement(item.getNumberOfBasement());
////        buildingSearchOutput.setLevel(item.getLevel());
////        buildingSearchOutput.setDirection(item.getDirection());
//        return buildingSearchOutput;
//    }

    public BuildingSearchOutput convertToBuildingSearchOutput(BuildingEntity buildingEntity, List<RentAreaSearchOutput> rentAreaSearchOutput){
        BuildingSearchOutput buildingSearchOutput = modelMapper.map(buildingEntity, BuildingSearchOutput.class);
        buildingSearchOutput.setRentArea(convertRentAreas(rentAreaSearchOutput));
        buildingSearchOutput.setAddress(buildingEntity.getStreet() + " -- " + buildingEntity.getWard() + " -- "+ DistrictEnum.valueOf(buildingEntity.getDistrict()).getValue());
        return buildingSearchOutput;
    }

    public BuildingEditDTO convertToBuildingEditDTO(BuildingEntity buildingEntity, List<RentAreaSearchOutput> rentAreaSearchOutput){
        BuildingEditDTO buildingEditDTO = modelMapper.map(buildingEntity, BuildingEditDTO.class);
        buildingEditDTO.setRentArea(convertRentAreas(rentAreaSearchOutput));
        buildingEditDTO.setType(convertFromStringToArray(buildingEntity.getType()));
        buildingEditDTO.setDistrict(buildingEntity.getDistrict());
        return buildingEditDTO;
    }

    public static BuildingDTO convertParamsToDTO(Map<String, Object> params) {
        BuildingDTO buildingDTO = new BuildingDTO();
        try {
            BeanUtils.populate(buildingDTO, params);
        } catch (Exception e) {
            System.err.println("Error populating BuildingDTO from params: " + e.getMessage());
        }
        return buildingDTO;
    }
}

