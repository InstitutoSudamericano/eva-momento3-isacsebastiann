package com.example.evam3.service

import com.example.evam3.entity.Film
import com.example.evam3.repository.FilmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FilmService {
    @Autowired
    lateinit var filmRepository: FilmRepository

    fun list ():List<Film>{
        return filmRepository.findAll()
    }

    fun save(film: Film): Film {
        try {
            film.title?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Cada gran historia necesita un título. No dejes el tuyo en el olvido.")

            film.director?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Detrás de cada gesta hay un director. ¿Quién guía la tuya?")

            film.duration?.takeIf { it > 0.00 }
                ?: throw Exception("La duración debe ser mayor a cero, como el viaje desde Hobbiton hasta Mordor.")

            film.releaseYear?.takeIf { it != null }
                ?: throw Exception("La era de lanzamiento no puede ser desconocida, como los senderos del Bosque Viejo.")

            return filmRepository.save(film)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }


    fun update(film: Film): Film{
        try {
            filmRepository.findById(film.id)
                ?: throw Exception("ID no disponible")

            return filmRepository.save(film)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = filmRepository.findById(id)
                ?: throw Exception("ID no disponible")
            filmRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}