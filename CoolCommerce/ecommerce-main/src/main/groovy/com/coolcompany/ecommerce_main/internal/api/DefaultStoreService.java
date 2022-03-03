package com.coolcompany.ecommerce_main.internal.api;

import com.coolcompany.ecommerce_main.api.StoreService;
import com.coolcompany.ecommerce_main.api.domain.Category;
import com.coolcompany.ecommerce_main.api.domain.Product;
import com.coolcompany.ecommerce_main.internal.repositories.CategoryRepository;
import com.coolcompany.ecommerce_main.internal.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Navíd Mitchell 🤪 on 3/2/22.
 */
@Component
public class DefaultStoreService implements StoreService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public DefaultStoreService(CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsForCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

}
