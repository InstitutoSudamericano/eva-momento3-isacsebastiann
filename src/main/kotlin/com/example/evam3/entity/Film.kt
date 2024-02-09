package com.example.evam3.entity
import jakarta.persistence.*
@Entity
@Table(name = "film")
class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Version
    var version: Int? = null

    var title: String? = null

    var director: String? = null

    var duration: Int? = null

    @Column(name = "year_of_release")
    var yearOfRelease: Int? = null

    var genre: String? = null

    var rating: String? = null

    @Column(name = "production_country")
    var productionCountry: String? = null

    var awards: String? = null

    var synopsis: String? = null
}
