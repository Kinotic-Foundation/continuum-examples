package com.coolcompany.ecommerce_main.api.domain;

import javax.persistence.*;

/**
 * Created by Navíd Mitchell 🤪 on 3/1/22.
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private float price;

    private String imageThumbnailUrl;

    private String imageUrl;

    private String sourceUrl;

    @ManyToOne
    private Category category;

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Product setPrice(float price) {
        this.price = price;
        return this;
    }

    public String getImageThumbnailUrl() {
        return imageThumbnailUrl;
    }

    public Product setImageThumbnailUrl(String imageThumbnailUrl) {
        this.imageThumbnailUrl = imageThumbnailUrl;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Product setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Product setCategory(Category category) {
        this.category = category;
        return this;
    }
}
