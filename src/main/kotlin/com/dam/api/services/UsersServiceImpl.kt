package com.dam.api.services

import com.dam.api.commons.GenericServiceImpl
import com.dam.api.models.User
import com.dam.api.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class UsersServiceImpl : UsersServiceAPI, GenericServiceImpl<User, Long>() {

    @Autowired
    lateinit var UsersRepository: UsersRepository

    override val dao: CrudRepository<User, Long>
        get() = UsersRepository

}