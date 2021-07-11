package com.imooc.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

    WAIT(0,"未支付"),
    SUCCESS(1,"支付成功"),
    ;

    private final Integer CODE;
    private final String MSG;

    PayStatusEnum(Integer code,String msg){
        this.CODE = code;
        this.MSG = msg;
    }

}
