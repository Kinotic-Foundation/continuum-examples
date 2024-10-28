package com.coolcompany.ecommerce_main.api.domain;


import jakarta.persistence.*;

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

    @Column(length = 400)
    private String description;

    private float price;

    private float rating;

    @AttributeOverrides({
            @AttributeOverride(name = "width", column = @Column(name = "THUMB_WIDTH")),
            @AttributeOverride(name = "height", column = @Column(name = "THUMB_HEIGHT")),
            @AttributeOverride(name = "url", column = @Column(name = "THUMB_URL"))
    })
    @Embedded
    private Image thumbnailImage;

    @Embedded
    private Image image;

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

    public float getRating() {
        return rating;
    }

    public Product setRating(float rating) {
        this.rating = rating;
        return this;
    }

    public Image getThumbnailImage() {
        return thumbnailImage;
    }

    public Product setThumbnailImage(Image thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
        return this;
    }

    public Image getImage() {
        return image;
    }

    public Product setImage(Image image) {
        this.image = image;
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
