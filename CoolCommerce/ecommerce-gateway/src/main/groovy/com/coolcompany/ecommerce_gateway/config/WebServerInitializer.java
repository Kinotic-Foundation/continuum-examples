package com.coolcompany.ecommerce_gateway.config;

import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/**
 * Created by Navíd Mitchell 🤪 on 5/10/22.
 */
@Component
public class WebServerInitializer {
    private static final Logger log = LoggerFactory.getLogger(WebServerInitializer.class);

    private final Vertx vertx;
    private final WebServerVerticle webServerVerticle;

    public WebServerInitializer(Vertx vertx,
                                WebServerVerticle webServerVerticle) {
        this.vertx = vertx;
        this.webServerVerticle = webServerVerticle;
    }

    @PostConstruct
    void init(){
        vertx.deployVerticle(webServerVerticle, v -> {
            if(v.succeeded()){
                log.info("Initialized Web Server Verticle");
            }else{
                log.error("Could not start Web Server", v.cause());
            }
        });
    }
}
