package com.kotlin.spring.kotlin_spring.entity

import jakarta.persistence.*

@Entity
@Table(name="Languages")
data class Language(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Int?,
    val name: String,
    val discipline: String,
    val difficulty: Int
)