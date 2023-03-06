package com.coolcompany.ecommerce_main.internal.api;

import com.coolcompany.ecommerce_main.api.StoreService;
import com.coolcompany.ecommerce_main.api.domain.CartItem;
import com.coolcompany.ecommerce_main.api.domain.Category;
import com.coolcompany.ecommerce_main.api.domain.CheckoutInfo;
import com.coolcompany.ecommerce_main.api.domain.Product;
import com.coolcompany.ecommerce_main.internal.repositories.CategoryRepository;
import com.coolcompany.ecommerce_main.internal.repositories.ProductRepository;
import com.coolcompany.ecommerce_payment.api.MerchantServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by Navíd Mitchell 🤪 on 3/2/22.
 */
@Component
@RequiredArgsConstructor
public class DefaultStoreService implements StoreService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final MerchantServices merchantServices;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsForCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Mono<UUID> checkout(CheckoutInfo checkoutInfo) {
        return Mono.defer(() -> {
            BigDecimal total = BigDecimal.ZERO;
            for (CartItem cartItem : checkoutInfo.getCartItems()) {
                total = total.add(cartItem.getTotal());
            }
            return merchantServices.processPayment(checkoutInfo.getCustomerInfo(),
                                                   checkoutInfo.getPaymentInfo(),
                                                   total);
        }).subscribeOn(Schedulers.parallel());
    }

}
