package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.enums.DistrictEnum;

import java.util.*;

public class DistrictUtils {

    public static Map<DistrictEnum,String> getDistrict(){
        Map<DistrictEnum,String> map = new HashMap<>();
//        List<String> districtsName = new ArrayList<>();
//        List<DistrictEnum> districts = Arrays.asList(DistrictEnum.class.getEnumConstants());
//        for(DistrictEnum item: districts){
//            districtsName.add(item.getValue());
//        }
//        for(String item : districtsName){
//            map.put(item,DistrictEnum.valueOf(item).getValue());
//        }
//        return map;
        List<String> districtsName = new ArrayList<>();
        List<DistrictEnum> districts = Arrays.asList(DistrictEnum.class.getEnumConstants());
        for(DistrictEnum item: districts){
            districtsName.add(item.getValue());
        }
        for(String item: districtsName){
            map.put(DistrictEnum.fromValue(item), item);
        }
        return map;
    }
}
