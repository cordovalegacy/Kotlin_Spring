package com.kotlin.spring.kotlin_spring.controller

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

//spin up the server on a random port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
//this config will scan your controller and make all your endpoints available to your test
@AutoConfigureWebTestClient
class GreetingControllerIntegrationTest {

    //dependency injection to set up your webTestClient for usage
    @Autowired
    lateinit var webTestClient: WebTestClient

    //this annotation creates a test method
    @Test
    fun retrieveGreeting(){
        val name = "Brendan"
        val result = webTestClient
            .get()//define request type (get)
            .uri("/v1/greetings/{name}", name) //what endpoint is our test calling
            .exchange()
            .expectStatus().is2xxSuccessful //any 200 response will be considered successful
            .expectBody(String::class.java) //expect a string, return a java class
            .returnResult() //return the test result

        //this is how we assert the test result to our console
        Assertions.assertEquals("$name, Hello from the development profile!", result.responseBody)
                                                    //compare this^           - against -    this^
    }
}