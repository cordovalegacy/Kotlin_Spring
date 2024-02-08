package com.kotlin.spring.kotlin_spring.controller

import com.kotlin.spring.kotlin_spring.dto.LanguageDTO
import com.kotlin.spring.kotlin_spring.service.LanguageService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/languages")
class LanguageController(val languageService: LanguageService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //the type of the body of the post is going to match the data class of the LanguageDataTransferObject(DTO)
    fun saveLanguage(@RequestBody languageDTO: LanguageDTO ): LanguageDTO{
        return languageService.saveLanguage(languageDTO)
    }
}