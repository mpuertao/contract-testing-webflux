package co.com.bancolombia.contracttesting.provider.repository;

import co.com.bancolombia.contracttesting.provider.models.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
}
