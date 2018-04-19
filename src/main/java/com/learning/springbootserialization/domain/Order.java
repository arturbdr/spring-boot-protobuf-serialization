package com.learning.springbootserialization.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String oid;
    private Person person;
    private Collection<Sku> skuCollection;
}
