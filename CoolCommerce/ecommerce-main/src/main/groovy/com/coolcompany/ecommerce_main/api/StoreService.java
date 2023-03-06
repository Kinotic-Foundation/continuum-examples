package com.coolcompany.ecommerce_main.api;

import com.coolcompany.ecommerce_main.api.domain.Category;
import com.coolcompany.ecommerce_main.api.domain.CheckoutInfo;
import com.coolcompany.ecommerce_main.api.domain.Product;
import org.kinotic.continuum.api.annotations.Publish;
import org.kinotic.continuum.api.annotations.Version;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

/**
 * Created by Navíd Mitchell 🤪 on 3/1/22.
 */
@Publish
@Version("0.1.0")
public interface StoreService {

    List<Category> getAllCategories();

    List<Product> getAllProductsForCategoryId(Long categoryId);

    Product getProduct(Long productId);

    Mono<UUID> checkout(CheckoutInfo checkoutInfo);

}
