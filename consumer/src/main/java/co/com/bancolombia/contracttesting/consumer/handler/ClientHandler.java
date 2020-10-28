package co.com.bancolombia.contracttesting.consumer.handler;

import co.com.bancolombia.contracttesting.consumer.models.Client;
import co.com.bancolombia.contracttesting.consumer.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class ClientHandler {

    @Autowired
    private ClientService clientService;

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(clientService.findAll(), Client.class);
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return clientService.findById(id).flatMap(client -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .bodyValue(client))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(error -> {
                    WebClientResponseException responseError = (WebClientResponseException) error;
                    if (responseError.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return ServerResponse.notFound().build();
                    }
                    return Mono.error(responseError);
                });
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        Mono<Client> clientMono = serverRequest.bodyToMono(Client.class);
        return clientMono.flatMap(client -> {
            if (client.getRequestDate() == null) client.setRequestDate(new Date());
            return clientService.save(client);
        }).flatMap(client -> ServerResponse.created(URI.create("/api/client/".concat(client.getId())))
                .contentType(APPLICATION_JSON)
                .bodyValue(client));
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        Mono<Client> clientMono = serverRequest.bodyToMono(Client.class);
        String id = serverRequest.pathVariable("id");
        return clientMono.flatMap(client -> ServerResponse.created(URI.create("api/client".concat(id)))
        .contentType(APPLICATION_JSON)
        .body(clientService.update(client, id), Client.class));
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        return clientService.delete(id)
                .then(ServerResponse.noContent().build());
    }
}
