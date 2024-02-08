package com.kotlin.spring.kotlin_spring.controller

import com.kotlin.spring.kotlin_spring.service.GreetingService
import mu.KLogging


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
//base url
@RequestMapping("/v1/greetings")
//injecting the greetingService class into our GreetingController
class GreetingController(val greetingService: GreetingService) {

    //in kotlin, this is the way we can get access to the logging class
    companion object: KLogging()

    @GetMapping("/{name}")
    //extracting the path variable and passing it into our service
    fun getGreeting(@PathVariable("name") name: String): String {

        //we are able to use logger because of our companion object
        logger.info { "Name is $name" }
        return greetingService.retrieveGreeting(name);
    }

}