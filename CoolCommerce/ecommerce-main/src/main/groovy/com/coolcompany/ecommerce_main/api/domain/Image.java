package com.coolcompany.ecommerce_main.api.domain;

import jakarta.persistence.Embeddable;
import java.net.URL;

/**
 * Created by Navíd Mitchell 🤪 on 3/4/22.
 */
@Embeddable
public class Image {

    private int width;

    private int height;

    private URL url;

    public int getWidth() {
        return width;
    }

    public Image setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public Image setHeight(int height) {
        this.height = height;
        return this;
    }

    public URL getUrl() {
        return url;
    }

    public Image setUrl(URL url) {
        this.url = url;
        return this;
    }
}
