package com.dam.api.repositories

import com.dam.api.models.Movie
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MoviesRepository : CrudRepository<Movie, Long> {
}