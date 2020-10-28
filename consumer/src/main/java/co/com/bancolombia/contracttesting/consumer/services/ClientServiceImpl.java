package co.com.bancolombia.contracttesting.consumer.services;

import co.com.bancolombia.contracttesting.consumer.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private WebClient webClient;

    @Override
    public Flux<Client> findAll() {
        return webClient.get()
                .accept(APPLICATION_JSON)
//                .retrieve()
//                .bodyToFlux(Client.class);
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Client.class));
    }

    @Override
    public Mono<Client> findById(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return webClient.get().uri("/{id}", params)
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Client.class);
    }

    @Override
    public Mono<Client> save(Client client) {
        return webClient.post()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                // .body(fromValue(client))
                .bodyValue(client)
                .retrieve()
                .bodyToMono(Client.class);
    }

    @Override
    public Mono<Client> update(Client client, String id) {
        return webClient.put().uri("/{id}", Collections.singletonMap("id", id))
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .bodyValue(client)
                .retrieve()
                .bodyToMono(Client.class);
    }

    @Override
    public Mono<Void> delete(String id) {
        return webClient.delete().uri("/{id}", Collections.singletonMap("id", id))
                .retrieve()
                .bodyToMono(Void.class);
    }
}
