package com.example.evam3.service

import com.example.evam3.entity.Character
import com.example.evam3.repository.CharacterRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharacterService {
    @Autowired
    lateinit var sceneRepository: SceneRepository
    @Autowired
    lateinit var characterRepository: CharacterRepository



    fun list ():List<Character>{
        return characterRepository.findAll()
    }

    fun save(character: Character): Character {
        try {
            sceneRepository.findById(character.sceneId)
                ?: throw Exception("Parece que este ID de escena ha tomado el camino a Moria y no ha regresado. Verifica el mapa y asegúrate de que es correcto.")

            character.name?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Todo ser de la Tierra Media debe tener un nombre, incluso los más pequeños. Por favor, dale uno a tu personaje.")

            character.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Una historia sin descripción es como un libro sin palabras. Llena las páginas de tu personaje con su historia.")

            // Aplicar operaciones transaccionales con dos tablas.
            val scene = sceneRepository.findById(character.sceneId)
            val currentTotalCost = characterRepository.sumCostBySceneId(character.sceneId!!) ?: 0.0

            // Comprobar Budget Para que no se pase
            if (scene != null) {
                if (currentTotalCost + (character.cost ?: 0.0) > (scene.budget ?: 0.0)) {
                    throw Exception("Las riquezas de Erebor no son suficientes para cubrir este gasto. Ajusta el costo antes de que Smaug se dé cuenta.")
                }
            }

            character.side?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("En la guerra por la Tierra Media, todos deben elegir un bando. ¿De qué lado está tu personaje?")

            return characterRepository.save(character)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }

    fun update(character: Character): Character {
        try {
            characterRepository.findById(character.id)
                ?: throw Exception("ID no existe")

            return characterRepository.save(character)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = characterRepository.findById(id)
                ?: throw Exception("ID no existe")
            characterRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}