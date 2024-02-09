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

    fun list(): List<Film> {
        return filmRepository.findAll()
    }

    fun save(film: Film): Film {
        validateFilm(film)
        return filmRepository.save(film)
    }

    fun update(id: Long, filmDetails: Film): Film {
        val film = filmRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found with id: $id")
        }

        film.title = filmDetails.title.takeIf { !it.isNullOrBlank() } ?: film.title
        film.director = filmDetails.director.takeIf { !it.isNullOrBlank() } ?: film.director
        film.duration = filmDetails.duration ?: film.duration
        film.yearOfRelease = filmDetails.yearOfRelease ?: film.yearOfRelease
        film.genre = filmDetails.genre.takeIf { !it.isNullOrBlank() } ?: film.genre
        film.rating = filmDetails.rating.takeIf { !it.isNullOrBlank() } ?: film.rating
        film.productionCountry = filmDetails.productionCountry.takeIf { !it.isNullOrBlank() } ?: film.productionCountry
        film.awards = filmDetails.awards ?: film.awards
        film.synopsis = filmDetails.synopsis ?: film.synopsis

        validateFilm(film)

        return filmRepository.save(film)
    }


    fun delete(id: Long) {
        if (!filmRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found with id: $id")
        }
        filmRepository.deleteById(id)
    }

    private fun validateFilm(film: Film) {
        if (film.title?.trim().isNullOrEmpty()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Film title must not be empty")
        }
    }
}
