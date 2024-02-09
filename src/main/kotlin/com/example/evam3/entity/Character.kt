package com.example.evam3.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "character")
class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String? = null

    @Column(name = "actor_name")
    var actorName: String? = null

    var race: String? = null

    var alignment: String? = null

    var backstory: String? = null

    @Column(name = "appearance_in_films")
    var appearanceInFilms: String? = null

    var cost: BigDecimal? = null

    var stock: Int? = null

    @ManyToOne
    @JoinColumn(name = "scene_id", referencedColumnName = "id")
    var scene: Scene? = null
}
