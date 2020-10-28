package co.com.bancolombia.contracttesting.provider;

import co.com.bancolombia.contracttesting.provider.models.Client;
import co.com.bancolombia.contracttesting.provider.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
public class ProviderApplication {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    public static final Logger logger = LoggerFactory.getLogger(ProviderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        reactiveMongoTemplate.dropCollection("clients").subscribe();
//
//        Flux.just(
//                new Client(123456789, "Mao Puerta", "1988-11-30"),
//                new Client(987654321, "Isacc Puerta", "2015-10-16"),
//                new Client(135792468, "danny barrientos", "1988-11-30")
//        ).flatMap(client -> {
//            client.setRequestDate(new Date());
//            return clientService.save(client);
//        }).subscribe(client -> logger.info("INSERT: " + client.getId() + " ... " + client.getNameClient()));
//    }
}
