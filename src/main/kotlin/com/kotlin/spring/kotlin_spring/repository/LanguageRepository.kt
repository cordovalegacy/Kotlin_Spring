package com.kotlin.spring.kotlin_spring.repository

import com.kotlin.spring.kotlin_spring.entity.Language
import org.springframework.data.repository.CrudRepository

interface LanguageRepository: CrudRepository<Language, Int> {

}