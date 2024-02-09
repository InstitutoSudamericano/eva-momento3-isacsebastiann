package com.example.evam3.service

import com.example.evam3.entity.Scene
import com.example.evam3.repository.FilmRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {


    @Autowired
    lateinit var sceneRepository: SceneRepository

    @Autowired
    lateinit var filmRepository: FilmRepository

    fun list ():List<Scene>{
        return sceneRepository.findAll()
    }

    fun save(scene: Scene): Scene {
        try {
            filmRepository.findById(scene.filmId)
                ?: throw Exception("El ID de película proporcionado no se encuentra en nuestra base de datos.")
            scene.title?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El título de la escena no puede estar vacío. Por favor, proporcione un título válido.")
            scene.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("La descripción de la escena no puede estar vacía. Por favor, proporcione una descripción válida.")
            scene.budget?.takeIf { it > 0.00 }
                ?: throw Exception("El presupuesto de la escena debe ser mayor a 0. Por favor, proporcione un presupuesto válido.")
            scene.minutes?.takeIf { it > 0.00 }
                ?: throw Exception("La duración de la escena en minutos debe ser mayor a 0. Por favor, proporcione una duración válida.")

            val film = filmRepository.findById(scene.filmId)
            val currentTotalCost = sceneRepository.sumMinutesByFilmId(scene.filmId!!) ?: 0

            if (film != null) {
                if (currentTotalCost + (scene.minutes ?: 0) > (film.duration ?: 0)) {
                    throw Exception("La duración total de las escenas excede la duración total de la película. Por favor, ajuste la duración de la escena.")
                }
            }
            return sceneRepository.save(scene)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }


    fun update(scene: Scene): Scene {
        try {
            sceneRepository.findById(scene.id)
                ?: throw Exception("ID no existe")

            return sceneRepository.save(scene)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = sceneRepository.findById(id)
                ?: throw Exception("ID no existe")
            sceneRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}