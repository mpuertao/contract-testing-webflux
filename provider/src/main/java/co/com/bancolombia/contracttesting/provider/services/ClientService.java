package co.com.bancolombia.contracttesting.provider.services;

import co.com.bancolombia.contracttesting.provider.models.Client;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ClientService {

    Flux<Client> findAll();
    Mono<Client> findById(String id);
    Mono<Client> save(Client client);
    Mono<Void> delete(Client client);
}
