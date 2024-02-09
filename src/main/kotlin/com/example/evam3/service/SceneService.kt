package com.example.evam3.service

import com.example.evam3.entity.Scene
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class SceneService {

    @Autowired
    lateinit var sceneRepository: SceneRepository

    fun listAll(): List<Scene> {
        return sceneRepository.findAll()
    }

    fun save(scene: Scene): Scene {
        validateScene(scene)
        return sceneRepository.save(scene)
    }

    fun update(id: Long, updatedScene: Scene): Scene {
        val scene = sceneRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Scene not found with id: $id")
        }

        scene.description = updatedScene.description.takeUnless { it.isNullOrBlank() } ?: scene.description
        scene.budget = updatedScene.budget ?: scene.budget
        scene.minutes = updatedScene.minutes ?: scene.minutes
        scene.location = updatedScene.location.takeUnless { it.isNullOrBlank() } ?: scene.location
        scene.filmingDate = updatedScene.filmingDate ?: scene.filmingDate
        scene.keyCharacters = updatedScene.keyCharacters.takeUnless { it.isNullOrBlank() } ?: scene.keyCharacters
        validateScene(scene)
        return sceneRepository.save(scene)
    }

    fun delete(id: Long) {
        if (!sceneRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Scene not found with id: $id")
        }
        sceneRepository.deleteById(id)
    }

    fun findById(id: Long): Optional<Scene> {
        return sceneRepository.findById(id)
    }

    private fun validateScene(scene: Scene) {
        if (scene.description.isNullOrBlank()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Scene description must not be empty")
        }
        // Add additional validation as necessary
    }
}
