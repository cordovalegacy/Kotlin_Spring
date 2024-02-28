package com.kotlin.spring.kotlin_spring.utils

import com.kotlin.spring.kotlin_spring.dto.LanguageDTO
import com.kotlin.spring.kotlin_spring.entity.Language

    fun mockLanguageList() = listOf<Language>(
            Language(null, "Python", "backend", 2),
            Language(null, "Java", "backend", 4),
            Language(null, "TypeScript", "frontend/backend", 1),
            Language(null, "JavaScript", "frontend", 3),
            Language(null, "Kotlin", "backend", 3)
    )

    fun languageDTO(
        id: Int? = null,
        name: String = "Python",
        discipline: String = "backend",
        difficulty: Int = 4
        ) = LanguageDTO(
        id, name, discipline, difficulty
        )