package com.example.evam3.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "scene")
class Scene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var description: String? = null

    var budget: BigDecimal? = null

    var minutes: Int? = null

    var location: String? = null

    @Column(name = "filming_date")
    var filmingDate: Date? = null

    @Column(name = "key_characters")
    var keyCharacters: String? = null

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    var film: Film? = null
}
