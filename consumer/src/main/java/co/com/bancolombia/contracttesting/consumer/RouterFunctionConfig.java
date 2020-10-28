package co.com.bancolombia.contracttesting.consumer;

import co.com.bancolombia.contracttesting.consumer.handler.ClientHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(ClientHandler clientHandler){
        return route(GET("api/client"), clientHandler::findAll)
                .andRoute(GET("api/client/{id}"), clientHandler::findById)
                .andRoute(POST("api/client"), clientHandler::save)
                .andRoute(PUT("api/client/{id}"), clientHandler::update)
                .andRoute(DELETE("api/client/{id}"), clientHandler::delete);
    }
}
