package com.rhonda.bookstoredemo.handlers;

import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
@Order(-2)
public class GlobalErrorHandler implements WebExceptionHandler {

    private final Logger logger = LogManager.getLogger(BookHandler.class);

    @Override
    public @NotNull Mono<Void> handle(@NotNull ServerWebExchange exchange, @NotNull Throwable ex) {
        return handle(ex)
                .flatMap(it -> it.writeTo(exchange, new HandlerStrategiesResponseContext(HandlerStrategies.withDefaults())))
                .flatMap(it -> Mono.empty());
    }

    Mono<ServerResponse> handle(Throwable throwable) {
        logger.error(throwable.getMessage());
        if (throwable instanceof IllegalArgumentException) {
            return createResponse(BAD_REQUEST, throwable.getMessage());
        }
        return createResponse(INTERNAL_SERVER_ERROR, throwable.getMessage());
    }

    private Mono<ServerResponse> createResponse(HttpStatus status, String message) {
        return ServerResponse.status(status).body(Mono.just(message), String.class);
    }
}

class HandlerStrategiesResponseContext implements ServerResponse.Context {

    private final HandlerStrategies handlerStrategies;

    public HandlerStrategiesResponseContext(HandlerStrategies handlerStrategies) {
        this.handlerStrategies = handlerStrategies;
    }

    @Override
    public List<HttpMessageWriter<?>> messageWriters() {
        return this.handlerStrategies.messageWriters();
    }

    @Override
    public List<ViewResolver> viewResolvers(){
        return this.handlerStrategies.viewResolvers();
    }

}