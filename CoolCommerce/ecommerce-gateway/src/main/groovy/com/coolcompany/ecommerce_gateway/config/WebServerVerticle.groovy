package com.coolcompany.ecommerce_gateway.config

import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.CorsHandler
import io.vertx.ext.web.handler.StaticHandler
import org.springframework.stereotype.Component

@Component
class WebServerVerticle extends AbstractVerticle {

    private HttpServer httpServer

    @Override
    void start() throws Exception {
        httpServer = getVertx().createHttpServer()
        Router router = Router.router(getVertx())
        router.route("/*").handler(StaticHandler.create())
        httpServer.requestHandler(router).listen(9090)
    }

    @Override
    void stop() throws Exception {
        httpServer.close()
    }
}
