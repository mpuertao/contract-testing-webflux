package co.com.bancolombia.contracttesting.consumer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureWebTestClient
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "co.com.bancolombia.contract-testing:provider:+:stubs:8090")
@DirtiesContext
public class ConsumerApplicationTests {

    @StubRunnerPort("provider")
    int producerPort;

    @Autowired
    private WebTestClient webTestClient;

    @Before
    public void before() {
        webTestClient = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:" + this.producerPort)
                .build();
    }

    @Test
    public void findAllTest() {
        webTestClient
                .get()
                .uri("/api/server/clients")
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isOk();
    }
}
