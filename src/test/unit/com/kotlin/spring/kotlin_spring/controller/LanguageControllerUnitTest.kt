package com.kotlin.spring.kotlin_spring.controller

import com.kotlin.spring.kotlin_spring.dto.LanguageDTO
import com.kotlin.spring.kotlin_spring.service.LanguageService
import com.kotlin.spring.kotlin_spring.utils.languageDTO
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [LanguageController::class])
@AutoConfigureWebTestClient
class LanguageControllerUnitTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var languageServiceMock: LanguageService

    //not working
    @Test
    fun addLanguage() {
        val languageDto = LanguageDTO(null, "TypeScript", "frontend/backend", 2)
        every { languageServiceMock.saveLanguage(any()) } returns languageDTO(id=1)

        val savedLanguageDTO = webTestClient
            .post()
            .uri("/v1/languages")
            .bodyValue(languageDto) //this will handle passing the proper data into the body of request
            .exchange() //this actually makes the call to the endpoint
            .expectStatus().isCreated
            .expectBody(LanguageDTO::class.java) //auto maps the response.json to the languageDTO
            .returnResult()
            .responseBody

        Assertions.assertTrue {
            savedLanguageDTO!!.id != null // !! operator is called the non-null assertion operator, and it asserts to the compiler that the expression to its left will not evaluate to null at runtime
        }
    }

    @Test
    fun getAllLanguages(){

        every { languageServiceMock.getLanguages() }.returnsMany(
            listOf(languageDTO(id=1), languageDTO(id=2, name = "Python"))
        )

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
}