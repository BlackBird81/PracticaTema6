package com.dam.api.models

import jakarta.persistence.*

@Entity
@Table(name="session")
class Session(
    @Id
    @Column(name="id")
    var id: Long,

    @Column(name="movie_id")
    var movie_id: Long,

    @Column(name="room_id")
    var room_id: Long,

    @Column(name="date")
    var date: String

) {
}