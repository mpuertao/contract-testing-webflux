package co.com.bancolombia.contracttesting.consumer.services;

import co.com.bancolombia.contracttesting.consumer.models.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<Client> findAll();
    Mono<Client> findById(String id);
    Mono<Client> save(Client client);
    Mono<Client> update(Client client, String id);
    Mono<Void> delete(String id);

}
