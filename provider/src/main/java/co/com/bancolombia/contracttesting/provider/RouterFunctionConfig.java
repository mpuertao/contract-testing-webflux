package co.com.bancolombia.contracttesting.provider;

import co.com.bancolombia.contracttesting.provider.handler.ClientHandler;
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
        return route(GET("/api/server/clients"), clientHandler::findAllClients)
                .andRoute(GET("/api/server/clients/{id}"), clientHandler::findClientById)
                .andRoute(POST("/api/server/clients"), clientHandler::createClient)
                .andRoute(PUT("/api/server/clients/{id}"), clientHandler::update)
                .andRoute(DELETE("/api/server/clients/{id}"), clientHandler::delete);
    }
}
