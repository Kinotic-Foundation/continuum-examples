package com.coolcompany.ecommerce_main.internal.repositories;

import com.coolcompany.ecommerce_main.api.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Navíd Mitchell 🤪 on 3/1/22.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

}
