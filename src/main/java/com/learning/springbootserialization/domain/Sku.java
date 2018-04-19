package com.learning.springbootserialization.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sku {
    private String id;
    private int price;
    private SkuType skutype;
}
