package com.laptrinhjavaweb.utils;

import java.lang.reflect.Field;

public class CheckObjectUtils {
    public static boolean isObjectWithoutNotNullAttribute(Object object) throws IllegalAccessException {
        boolean fieldIsNull = true;
        //Class<?> zClass = object.getClass();
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            String fieldName = field.getName();
            Object objectValue = field.get(object);
            if(fieldName != "buildingTypes"){
                if (objectValue != null){
                    fieldIsNull = false;
                    break;
                }
            }else{
                Object[] array = (Object[]) field.get(object);
                int num = array.length;
                if(array.length == 0){
                    break;
                }
//                else{
//                    if(ValidateUtils.areValid((String[]) array)){
//                        fieldIsNull = false;
//                        break;
//                    }
//                }
            }

        }
        return fieldIsNull;
    }
}
