package com.github.transformeli.desafiospring.enums;

import com.github.transformeli.desafiospring.exception.BadRequestException;
import lombok.Getter;

public enum ParamOrderEnum {

    ASCENDING_ALPHA("Ascending alphabet"),
    DESCENDING_ALPHA("Descending alphabet"),
    HIGHER_PRICE("Higher Price"),
    LOWER_PRICE("Lower Price");

    @Getter
    private String name;

    ParamOrderEnum(String name) {
        this.name = name;
    }

    public static ParamOrderEnum valueOf(int orderId) {
        if(orderId > 3 || orderId < 0)
        {
            throw new BadRequestException("invalid orderId");
        }
        return ParamOrderEnum.values()[orderId];
    }
}
