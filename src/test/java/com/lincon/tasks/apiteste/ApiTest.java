package com.lincon.tasks.apiteste;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiTest {

	/*@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/task-backend-0.0.1-SNAPSHOT";
	}*/
	
	@Test
	public void deveRetornarTarefas() {
		RestAssured.given()
		.when()
			.get("http://localhost:8001/task-backend-0.0.1-SNAPSHOT/todo")
		.then()
			.statusCode(200);
	}
	
	@Test
	public void deveAdicionarTarefasComSucesso() {
		RestAssured.given()
			.body("{ \"task\":\"Teste-api\",\"dueDate\":\"2030-12-30\" }")
			.contentType(ContentType.JSON)
		.when()
			.post("http://localhost:8001/task-backend-0.0.1-SNAPSHOT/todo")
		.then()
			.log().all()
			.statusCode(201);
	}
	
	@Test
	public void naoDeveAdicionarTarefaInvalida() {
		RestAssured.given()
			.body("{ \"task\":\"Teste-api\",\"dueDate\":\"2010-12-30\" }")
			.contentType(ContentType.JSON)
		.when()
			.post("http://localhost:8001/task-backend-0.0.1-SNAPSHOT/todo")
		.then()
			.log().all()
			.statusCode(400)
			.body("message", CoreMatchers.is(""));
	}
}
