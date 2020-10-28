package co.com.bancolombia.contracttesting.provider.handler;

import co.com.bancolombia.contracttesting.provider.models.Client;
import co.com.bancolombia.contracttesting.provider.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class ClientHandler {

    @Autowired
    private ClientService clientService;

    public Mono<ServerResponse> findAllClients(ServerRequest serverRequest) {
        return ServerResponse.ok().body(clientService.findAll(), Client.class);
    }

    public Mono<ServerResponse> findClientById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return clientService.findById(id).flatMap(client -> ServerResponse.ok()
                    .contentType(APPLICATION_JSON)
                    .body(fromValue(client)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createClient(ServerRequest serverRequest){
        Mono<Client> clientMono = serverRequest.bodyToMono(Client.class);
        return clientMono.flatMap(client -> {
            if (client.getRequestDate() == null) client.setRequestDate(new Date());
            return clientService.save(client).flatMap(clientBD -> ServerResponse.created(URI.create("/api/server/clients".concat(clientBD.getId())))
            .contentType(APPLICATION_JSON)
            .body(fromValue(clientBD)));
        });
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        Mono<Client> clientMono = serverRequest.bodyToMono(Client.class);
        Mono<Client> clientBD = clientService.findById(id);
        return clientBD.zipWith(clientMono, (db, req) -> {
            db.setNameClient(req.getNameClient());
            db.setClientId(req.getClientId());
            db.setBirthdate(req.getBirthdate());
            return db;
        }).flatMap(client -> ServerResponse.created(URI.create("/api/server/clients".concat(client.getId())))
        .contentType(APPLICATION_JSON)
        .body(clientService.save(client), Client.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        Mono<Client> clientMonoDB = clientService.findById(id);
        return clientMonoDB.flatMap(client -> clientService.delete(client).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
