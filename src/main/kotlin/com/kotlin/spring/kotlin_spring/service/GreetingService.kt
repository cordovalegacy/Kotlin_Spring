package com.kotlin.spring.kotlin_spring.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

//this annotation will allow us to inject this dependency into wherever we need it
@Service
class GreetingService {

    //this annotation and syntax pulls the message from the application.yml file
    @Value("\${message}")
    //this allows us to initialize the variable with no initial value
    lateinit var message: String

    fun retrieveGreeting(name: String): String {
        return "$name, $message"
    }
}