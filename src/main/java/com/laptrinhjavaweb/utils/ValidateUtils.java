package com.laptrinhjavaweb.utils;

public class ValidateUtils {

    public static boolean isValidated(Object obj) {
        boolean isTrue = null != obj && !"".equals(obj.toString());

        if(isTrue){
            return obj instanceof String || StringBuilder.class.equals(obj.getClass());
        }
        return false;
//        boolean valid = false;
//        String object = input.toString();
//        if(object != null && !object.equals(" ") && object != "null"){
//            valid = true;
//        }
//        return valid;
    }

    public static boolean areValid(String[] array) {
        boolean valid = false;
        //for(int i =0;i<)
        if(array.length == 0){
            valid = false;
        }else{
            int i = 0;
            String object = array[i];
            if(object !=null && object != " "){
                valid = true;
            }
        }

        return valid;
    }

//    public static boolean isValid(Object obj){
//        boolean valid = false;
//        if(obj != null && obj != "" && !obj.equals(0)){
//            valid = true;
//        }
//        return valid;
//    }

    public static boolean isValid(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String && obj.equals("")) {
            return false;
        }
        if (obj instanceof Integer && (Integer)obj == 0) {
            return false;
        }
        if (obj instanceof Long && (Long)obj == 0L) {
            return false;
        }
        return true;
    }
}
