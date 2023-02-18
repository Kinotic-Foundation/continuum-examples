package com.coolcompany.ecommerce_main.api;

import com.coolcompany.ecommerce_main.api.domain.Category;
import com.coolcompany.ecommerce_main.api.domain.CheckoutInfo;
import com.coolcompany.ecommerce_main.api.domain.Product;
import org.kinotic.continuum.api.annotations.Publish;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Created by Navíd Mitchell 🤪 on 3/1/22.
 */
@Publish(version = "0.1.0")
public interface StoreService {

    List<Category> getAllCategories();

    List<Product> getAllProductsForCategoryId(Long categoryId);

    Product getProduct(Long productId);

    Mono<Void> checkout(CheckoutInfo checkoutInfo);

}
