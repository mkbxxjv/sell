package com.imooc.dto;

import lombok.Data;

@Data
public class CartDTO {

    /*ååID*/
    private String productId;

    /*æ°é*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
