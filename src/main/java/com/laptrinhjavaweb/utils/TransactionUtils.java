package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.enums.TransactionEnum;

import java.util.*;

public class TransactionUtils {

    public static Map<TransactionEnum,String> getTransaction(){
        Map<TransactionEnum,String> map = new HashMap<>();
        List<String> transactionName = new ArrayList<>();
        List<TransactionEnum> transactions = Arrays.asList(TransactionEnum.class.getEnumConstants());
        for(TransactionEnum item: transactions){
            transactionName.add(item.getValue());
        }
        for(String item: transactionName){
            map.put(TransactionEnum.fromValue(item),item);
        }
        return map;
    }
}
