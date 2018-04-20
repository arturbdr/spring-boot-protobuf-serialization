package com.learning.springbootserializationconsumer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SkuType {

    GIFT(0),
    NORMAL(1);
    private int type;
}
