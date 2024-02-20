package com.kotlin.spring.kotlin_spring.controller

import com.kotlin.spring.kotlin_spring.dto.LanguageDTO
import com.kotlin.spring.kotlin_spring.entity.Language
import com.kotlin.spring.kotlin_spring.repository.LanguageRepository
import com.kotlin.spring.kotlin_spring.utils.mockLanguageList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class LanguageControllerIntegrationTest {

    //this will connect us automatically from the spring boot application to the test client
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var languageRepository: LanguageRepository

    @BeforeEach
    fun setUp(){
        //clean up the db table before each test case so each case gets new data
        languageRepository.deleteAll();
        val languages = mockLanguageList();
        languageRepository.saveAll(languages);

    }

    //to automatically generate a test method -> cmnd + n
    @Test
    fun addLanguage() {
        val languageDTO = LanguageDTO(null, "TypeScript", "frontend/backend", 2)

        val savedLanguageDTO = webTestClient
                .post()
                .uri("/v1/languages")
                .bodyValue(languageDTO) //this will handle passing the proper data into the body of request
                .exchange() //this actually makes the call to the endpoint
                .expectStatus().isCreated
                .expectBody(LanguageDTO::class.java) //auto maps the response.json to the languageDTO
                .returnResult()
                .responseBody

        //checks if the responseBody has a valid (not-null) id
        //test will fail if this comes back false
        Assertions.assertTrue {
            savedLanguageDTO!!.id != null // !! operator is called the non-null assertion operator, and it asserts to the compiler that the expression to its left will not evaluate to null at runtime
        }
    }

    @Test
    fun getAllLanguages(){
        val languageDTOs = webTestClient
                .get()
                .uri("/v1/languages")
                .exchange()
                .expectStatus().isOk
                .expectBodyList(LanguageDTO::class.java)
                .returnResult()
                .responseBody

        Assertions.assertEquals(5, languageDTOs!!.size)
    }

    @Test
    fun updateLanguage(){
        val testLanguage = Language(null, "Python", "backend", 2)
        languageRepository.save(testLanguage)

        val updateLanguageRequestBody = LanguageDTO(null, "TypeScript", "frontend/backend", 1)

        val updatedLanguageDTO = webTestClient
                .put()
                .uri("/v1/languages/{languageId}", testLanguage.id)
                .bodyValue(updateLanguageRequestBody)
                .exchange()
                .expectStatus().isCreated
                .expectBody(LanguageDTO::class.java)
                .returnResult()
                .responseBody

        Assertions.assertEquals(updateLanguageRequestBody.name, updatedLanguageDTO!!.name)
    }
}