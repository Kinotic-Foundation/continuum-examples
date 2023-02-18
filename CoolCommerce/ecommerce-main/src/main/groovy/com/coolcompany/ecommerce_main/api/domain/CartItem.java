package com.coolcompany.ecommerce_main.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Navíd Mitchell 🤪 on 1/12/23.
 */
@Data
@NoArgsConstructor
public class CartItem {

    private Product product;

    private Integer quantity;

    private BigDecimal total;

}
