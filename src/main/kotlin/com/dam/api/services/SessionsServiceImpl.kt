package com.dam.api.services

import com.dam.api.commons.GenericServiceImpl
import com.dam.api.models.Session
import com.dam.api.repositories.SessionsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class SessionsServiceImpl : SessionsServiceAPI, GenericServiceImpl<Session, Long>() {

    @Autowired
    lateinit var SessionsRepository: SessionsRepository

    override val dao: CrudRepository<Session, Long>
        get() = SessionsRepository

}