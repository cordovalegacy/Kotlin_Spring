package com.kotlin.spring.kotlin_spring.service

import com.kotlin.spring.kotlin_spring.dto.LanguageDTO
import com.kotlin.spring.kotlin_spring.entity.Language
import com.kotlin.spring.kotlin_spring.exception.LanguageNotFoundException
import com.kotlin.spring.kotlin_spring.repository.LanguageRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class LanguageService(val languageRepository: LanguageRepository) {

    companion object: KLogging()

    //saveLanguage will take in the shape of our data transfer object, and return a data transfer object
    fun saveLanguage(languageDTO: LanguageDTO): LanguageDTO{
        //let is an extension function, it lets us execute a block of code on the RESULT of a function call
        val languageEntity = languageDTO.let {
            Language(null, it.name, it.discipline, it.difficulty)
        }
        languageRepository.save(languageEntity)

        logger.info { "Saved Language is: $languageEntity" }

        //let gives us lambdas (scope function)
        return languageEntity.let {
            LanguageDTO(it.id, it.name, it.discipline, it.difficulty)
        }
    }

    fun getLanguages(): List<LanguageDTO> {
        return languageRepository.findAll()
                .map {
                    LanguageDTO(it.id, it.name, it.discipline, it.difficulty);
                }
    }

    fun updateLanguage(languageId: Int, languageDTO: LanguageDTO): LanguageDTO {
        val languageToUpdate = languageRepository.findById(languageId)
        return if (languageToUpdate.isPresent){
            languageToUpdate.get()
                    .let {
                        it.name = languageDTO.name
                        it.difficulty = languageDTO.difficulty
                        it.discipline = languageDTO.discipline
                        languageRepository.save(it)
                        LanguageDTO(it.id, it.name, it.discipline, it.difficulty)
                    }
        } else {
            throw LanguageNotFoundException("Language $languageId does not exist")
        }
    }
}