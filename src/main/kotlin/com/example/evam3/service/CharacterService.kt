package com.example.evam3.service

import com.example.evam3.entity.Character
import com.example.evam3.repository.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharacterService {

    @Autowired
    lateinit var characterRepository: CharacterRepository

    fun listAll(): List<Character> {
        return characterRepository.findAll()
    }

    fun save(character: Character): Character {
        if (character.name.isNullOrBlank()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Character name must not be empty")
        }
        return characterRepository.save(character)
    }

    fun update(id: Long, characterDetails: Character): Character {
        val character = characterRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found with id: $id")
        }

        character.name = characterDetails.name.takeIf { !it.isNullOrBlank() } ?: character.name
        character.actorName = characterDetails.actorName.takeIf { !it.isNullOrBlank() } ?: character.actorName
        // Aquí puedes seguir asignando los campos que sean necesarios actualizar
        // Asegúrate de validar cualquier campo que no deba estar vacío

        return characterRepository.save(character)
    }

    fun delete(id: Long) {
        if (!characterRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found with id: $id")
        }
        characterRepository.deleteById(id)
    }

    fun findById(id: Long): Character? {
        return characterRepository.findById(id).orElse(null)
    }
}
