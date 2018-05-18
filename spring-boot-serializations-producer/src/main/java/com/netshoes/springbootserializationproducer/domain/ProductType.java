package com.netshoes.springbootserializationproducer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {

    GIFT(0),
    NORMAL(1);
    private int type;
}
