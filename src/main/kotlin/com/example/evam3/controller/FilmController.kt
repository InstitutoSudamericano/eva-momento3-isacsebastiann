package com.example.evam3.controller

import com.example.evam3.entity.Film
import com.example.evam3.service.FilmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/films")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class FilmController {
    @Autowired
    lateinit var filmService: FilmService

    @GetMapping
    fun list(): ResponseEntity<List<Film>> {
        val films = filmService.list()
        return ResponseEntity(films, HttpStatus.OK)
    }

    @PostMapping
    fun save(@RequestBody film: Film): ResponseEntity<Film> {
        val savedFilm = filmService.save(film)
        return ResponseEntity(savedFilm, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody film: Film): ResponseEntity<Film> {
        val updatedFilm = filmService.update(id, film)
        return ResponseEntity(updatedFilm, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        filmService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}