package com.imooc.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"取消"),
    ;

    private final Integer CODE;
    private final String MSG;

    OrderStatusEnum(Integer code,String msg){
        this.CODE = code;
        this.MSG = msg;
    }

}
