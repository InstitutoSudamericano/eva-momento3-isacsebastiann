package com.example.evam3.entity

import jakarta.persistence.*

@Entity
@Table(name="character")
class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    var name: String? = null

    var description: String? = null

    var cost: Double? = null

    var side: String? = null

    @Column(name = "scene_id")
    var sceneId: Long? = null

    @Column(name = "actor_name")
    var actorName: String? = null

    var race: String? = null

    var alignment: String? = null
}
