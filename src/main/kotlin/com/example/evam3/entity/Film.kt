package com.example.evam3.entity

import jakarta.persistence.*

@Entity
@Table(name="film")
class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    var title: String? = null

    var director: String? = null

    var duration: Long? = null

    /*var rating: Int? = null*/
}
