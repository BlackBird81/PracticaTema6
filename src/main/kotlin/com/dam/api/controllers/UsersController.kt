package com.dam.api.controllers

import com.dam.api.models.User
import com.dam.api.services.UsersServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController

@RequestMapping("/users")
@CrossOrigin("*")
class UsersController {

    @Autowired
    lateinit var usersService: UsersServiceImpl

    @GetMapping("")
    fun getAll(): ResponseEntity<MutableList<User>> {
        var listUsers: MutableList<User>? = mutableListOf()
        listUsers = usersService.all
        return ResponseEntity(listUsers, HttpStatus.OK)
    }

    @GetMapping("/{nick}")
    fun getOneUser(@PathVariable nick: String): ResponseEntity<User>{
        var nickUser: String = nick

        var listUsers: MutableList<User>? = mutableListOf()
        listUsers = usersService.all

        var user: User? = usersService[0]
        listUsers?.forEach(){
            if (it.nick.equals(nickUser)){
                user= usersService[it.id]
            }
        }


        return ResponseEntity<User>(user, HttpStatus.OK)
    }

    @PostMapping("/")
    fun insertUser(@RequestBody user: User): ResponseEntity<User> {

        usersService.save(user)

        println("ID: ${user.id}")
        println("Nombre: ${user.nick}")
        return ResponseEntity<User>(user, HttpStatus.OK)
    }

    @PutMapping("/{nick}")
    fun updateUser(@PathVariable nick: String ,@RequestBody user: User): ResponseEntity<String> {

        var listUsers: MutableList<User>? = usersService.all

        listUsers?.forEach(){
            if (it.nick.equals(nick)){
                usersService.delete(it.id)
                usersService.save(user)
            }
        }


        return ResponseEntity<String>("USER UPDATED!", HttpStatus.OK)
    }

    @DeleteMapping("/{nick}")
    fun deleteUser(@PathVariable nick: String): ResponseEntity<String>{

        var nickUser: String = nick

        var listUsers: MutableList<User>? = usersService.all

        var idUser:Long = 0
        listUsers?.forEach(){
            if (it.nick.equals(nickUser)){
                idUser = it.id
            }
        }

        usersService.delete(idUser)
        return ResponseEntity<String>("USER DELETED", HttpStatus.OK)
    }

}