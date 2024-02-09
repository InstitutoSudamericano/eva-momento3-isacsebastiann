package com.example.evam3.controller

import com.example.evam3.entity.Character
import com.example.evam3.service.CharacterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/characters")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class CharacterController {

    @Autowired
    private lateinit var characterService: CharacterService

    @GetMapping
    fun list(): ResponseEntity<List<Character>> {
        val characters = characterService.listAll()
        return ResponseEntity.ok(characters)
    }

    @PostMapping
    fun save(@RequestBody character: Character): ResponseEntity<Character> {
        val savedCharacter = characterService.save(character)
        return ResponseEntity.status(201).body(savedCharacter)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody character: Character): ResponseEntity<Character> {
        val updatedCharacter = characterService.update(id, character)
        return ResponseEntity.ok(updatedCharacter)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        characterService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Character> {
        val character = characterService.findById(id)
        return character?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }
}
