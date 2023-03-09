package com.dam.api.services

import com.dam.api.commons.GenericServiceImpl
import com.dam.api.models.Movie
import com.dam.api.repositories.MoviesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class MoviesServiceImpl : MoviesServiceAPI, GenericServiceImpl<Movie, Long>() {

    @Autowired
    lateinit var MoviesRepository: MoviesRepository

    override val dao: CrudRepository<Movie, Long>
        get() = MoviesRepository

}