package com.coolcompany.ecommerce_main.internal.init;

import com.coolcompany.ecommerce_main.api.domain.Category;
import com.coolcompany.ecommerce_main.api.domain.Product;
import com.coolcompany.ecommerce_main.internal.repositories.CategoryRepository;
import com.coolcompany.ecommerce_main.internal.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Navíd Mitchell 🤪 on 3/2/22.
 */
@Component
public class DataInitializer {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    private final static String[] CATEGORY_NAMES = {"Rocking Chair", "Fireplace", "Lamp", "Bed", "Table"};

    private final static String LOREM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras at elit eu tortor blandit vehicula. Morbi sit amet fringilla lorem, a rhoncus tellus. Etiam sed pulvinar ipsum, at hendrerit justo. Integer egestas lacus id fermentum faucibus. Cras iaculis arcu et sem feugiat dignissim. Praesent pharetra nibh sed eros sollicitudin, in tincidunt metus pretium. Praesent sed felis leo.";

    public DataInitializer(CategoryRepository categoryRepository,
                           ProductRepository productRepository,
                           ResourceLoader resourceLoader,
                           ObjectMapper objectMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() throws Exception{

        if(categoryRepository.count() == 0){

            List<String> adjectives = getAdjectives();

            for(String categoryName : CATEGORY_NAMES){
                Category category = new Category().setName(categoryName+"s");
                category = categoryRepository.save(category);

                for(Product product: getProductsFromJson(categoryName)){
                    product.setCategory(category);
                    product.setName(adjectives.get(getRandomNumberInRange(adjectives.size())) + " " + categoryName);
                    product.setDescription(LOREM);
                    product.setPrice(getRandomFloat(9.99F, 999.99F, 2));
                    product.setRating(getRandomFloat(0.0F, 5.0F, 1));

                    productRepository.save(product);
                }
            }
        }
    }

    private List<Product> getProductsFromJson(String category) throws IOException{
        List<Product> ret;
        CollectionType mapCollectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class);
        try(InputStream is = resourceLoader.getResource("classpath:"+category.replaceAll(" ", "-")+"s.json").getInputStream()) {
            ret = objectMapper.readValue(is, mapCollectionType);
        }
        Collections.shuffle(ret);
        return ret;
    }

    private List<String> getAdjectives() throws IOException {
        List<String> ret;
        try (Stream<String> fileStream = new BufferedReader(new InputStreamReader(resourceLoader.getResource("classpath:adjectives.txt")
                                                                                                .getInputStream())).lines()) {
            ret = fileStream.collect(Collectors.toList());
        }
        return ret;
    }

    private static float getRandomFloat(float min, float max, int numDecimals){
        BigDecimal minBd = new BigDecimal(min);
        BigDecimal maxBd = new BigDecimal(max);
        BigDecimal randomBigDecimal = minBd.add(BigDecimal.valueOf(Math.random()).multiply(maxBd.subtract(minBd)));
        return randomBigDecimal.setScale(numDecimals, RoundingMode.HALF_UP).floatValue();
    }

    private static int getRandomNumberInRange(int upperBoundExclusive) {
        Random r = new Random();
        return r.ints(0, upperBoundExclusive).findFirst().getAsInt();
    }

}
