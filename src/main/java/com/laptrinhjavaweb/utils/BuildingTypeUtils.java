package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.constant.BuildingTypeConstant;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;

import java.util.*;

public class BuildingTypeUtils {
    public static Map<BuildingTypesEnum,String> getTypes() {
        Map<BuildingTypesEnum,String> map = new HashMap<>();
        List<String> typeName = new ArrayList<>();
        List<BuildingTypesEnum> buildingTypes = Arrays.asList(BuildingTypesEnum.class.getEnumConstants());
        for(BuildingTypesEnum item: buildingTypes){
            typeName.add(item.getValue());
        }
        for(String item: typeName){
            map.put(BuildingTypesEnum.fromValue(item), item);
        }
        return map;

    }
}
