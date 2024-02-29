package com.laptrinhjavaweb.enums;

public enum TransactionEnum {

    QUA_TRINH_CSKH("QUÁ TRÌNH CSKH"),

    DAN_DI_XEM("DẪN ĐI XEM"),

    HOP_DONG("HỢP ĐỒNG");

    private final String value;

    TransactionEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static TransactionEnum fromValue(String value){
        for(TransactionEnum transactionEnum: TransactionEnum.values()){
            if(transactionEnum.getValue() == value){
                return transactionEnum;
            }
        }
        throw new IllegalArgumentException("No value");
    }

}
