package com.example.evam3.controller

import com.example.evam3.entity.Scene
import com.example.evam3.service.SceneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/scenes")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class SceneController {

    @Autowired
    lateinit var sceneService: SceneService

    @GetMapping
    fun list(): ResponseEntity<List<Scene>> {
        val scenes = sceneService.listAll()
        return ResponseEntity(scenes, HttpStatus.OK)
    }

    @PostMapping
    fun save(@RequestBody scene: Scene): ResponseEntity<Scene> {
        val savedScene = sceneService.save(scene)
        return ResponseEntity(savedScene, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody scene: Scene): ResponseEntity<Scene> {
        val updatedScene = sceneService.update(id, scene)
        return ResponseEntity(updatedScene, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        sceneService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
