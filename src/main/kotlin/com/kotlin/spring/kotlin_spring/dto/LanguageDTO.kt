package com.kotlin.spring.kotlin_spring.dto

//data class is really just a source for our app to process data
data class LanguageDTO(
    val id : Int?,
    val name: String,
    val discipline: String,
    val difficulty: Int
)