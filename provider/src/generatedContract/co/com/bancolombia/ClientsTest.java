package co.com.bancolombia;

import co.com.bancolombia.contract-testing;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.Rule;
import io.restassured.module.webtestclient.specification.WebTestClientRequestSpecification;
import io.restassured.module.webtestclient.response.WebTestClientResponse;

import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.webtestclient.RestAssuredWebTestClient.*;

@SuppressWarnings("rawtypes")
public class ClientsTest extends contract-testing {

	@Test
	public void validate_shouldFindAllClientsWhenIsCalledAPI() throws Exception {
		// given:
			WebTestClientRequestSpecification request = given()
					.header("Content-Type", "application/json");

		// when:
			WebTestClientResponse response = given().spec(request)
					.get("/api/client");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");

		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).array().contains("['id']").isEqualTo("5f93a03b1ca80a42577a2412");
			assertThatJson(parsedJson).array().contains("['clientId']").isEqualTo(123456789);
			assertThatJson(parsedJson).array().contains("['nameClient']").isEqualTo("Mao Puerta");
			assertThatJson(parsedJson).array().contains("['birthdate']").isEqualTo("1988-11-30");
			assertThatJson(parsedJson).array().contains("['requestDate']").isEqualTo("2020-10-24T03:32:11.355+00:00");
			assertThatJson(parsedJson).array().contains("['id']").isEqualTo("5f93a03b1ca80a42577a2413");
			assertThatJson(parsedJson).array().contains("['clientId']").isEqualTo(987654321);
			assertThatJson(parsedJson).array().contains("['nameClient']").isEqualTo("Isacc Puerta");
			assertThatJson(parsedJson).array().contains("['birthdate']").isEqualTo("2015-10-16");
			assertThatJson(parsedJson).array().contains("['requestDate']").isEqualTo("2020-10-24T03:32:11.463+00:00");
	}

}
