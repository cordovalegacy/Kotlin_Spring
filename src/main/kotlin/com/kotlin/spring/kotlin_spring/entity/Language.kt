package com.kotlin.spring.kotlin_spring.entity

import jakarta.persistence.*

@Entity
@Table(name="Languages")
data class Language(
        @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Int?,
        var name: String,
        var discipline: String,
        var difficulty: Int
)