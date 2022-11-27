package com.coolcompany.ecommerce_main.internal.repositories;

import com.coolcompany.ecommerce_main.api.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Navíd Mitchell 🤪 on 3/2/22.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
