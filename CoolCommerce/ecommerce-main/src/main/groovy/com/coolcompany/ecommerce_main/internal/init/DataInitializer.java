package com.coolcompany.ecommerce_main.internal.init;

import com.coolcompany.ecommerce_main.api.domain.Category;
import com.coolcompany.ecommerce_main.api.domain.Product;
import com.coolcompany.ecommerce_main.internal.repositories.CategoryRepository;
import com.coolcompany.ecommerce_main.internal.repositories.ProductRepository;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private final static String[] CATEGORY_NAMES = {"Rocking Chair", "Table", "Lamp"};
    private static final int ADJECTIVE_COUNT = 1915;

    // In the real would not be stored in the source code
    private final static String API_KEY = "3bee173c6377742def8a5e5d8f34e87f";
    private final static String API_SECRET = "6f26e6a5596e2898";

    public DataInitializer(CategoryRepository categoryRepository,
                           ProductRepository productRepository,
                           ResourceLoader resourceLoader) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() throws Exception{

        if(categoryRepository.count() == 0){
            Flickr flickr = new Flickr(API_KEY, API_SECRET, new REST());
            SearchParameters searchParameters = new SearchParameters();
            searchParameters.setAccuracy(1);
            searchParameters.setSafeSearch(Flickr.SAFETYLEVEL_SAFE);

            List<String> adjectives = getAdjectives();

            for(String categoryName : CATEGORY_NAMES){
                Category category = new Category().setName(categoryName);
                category = categoryRepository.save(category);

                searchParameters.setTags(new String[]{categoryName});
                PhotoList<Photo> photos = flickr.getPhotosInterface().search(searchParameters, 20, 0);

                for(Photo photo: photos){
                    Product product = new Product();
                    product.setCategory(category);
                    product.setName(adjectives.get(getRandomNumberInRange(ADJECTIVE_COUNT)) + " " + categoryName);
                    product.setDescription(photo.getDescription() != null ? photo.getDescription() : photo.getTitle());
                    product.setImageThumbnailUrl(getPhotoUrlForSize(photo, "w"));
                    product.setImageUrl(getPhotoUrlForSize(photo, "c"));
                    product.setSourceUrl(photo.getUrl());

                    productRepository.save(product);
                }
            }
        }
    }


    /**
     * Constructs a Flikr photo URL for the photo and suffix
     * Format described here https://www.flickr.com/services/api/misc.urls.html
     * @param photo to get the URL for
     * @param sizeSuffix the size suffix to use
     * @return the URL to the desired photo
     */
    private String getPhotoUrlForSize(Photo photo, String sizeSuffix){
        return "https://live.staticflickr.com/"
                + photo.getServer() + "/"
                + photo.getId() + "_"
                + photo.getSecret()+ "_"
                + sizeSuffix + ".jpg";
    }


    private List<String> getAdjectives() throws IOException {
        List<String> ret;
        try (Stream<String> fileStream = new BufferedReader(new InputStreamReader(resourceLoader.getResource("classpath:adjectives.txt").getInputStream())).lines()) {
            ret = fileStream.collect(Collectors.toList());
        }
        return ret;
    }

    private static int getRandomNumberInRange(int max) {
        Random r = new Random();
        return r.ints(0, (max + 1)).findFirst().getAsInt();
    }

}
