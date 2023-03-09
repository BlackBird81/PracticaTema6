package com.dam.api.controllers

import com.dam.api.models.Session
import com.dam.api.services.SessionsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RestController

@RequestMapping("/sessions")
@CrossOrigin("*")
class SessionsController {

    @Autowired
    lateinit var sessionsService: SessionsServiceImpl

    @GetMapping("")
    fun getAll(): ResponseEntity<MutableList<Session>> {
        var listSessions: MutableList<Session>? = mutableListOf()
        listSessions = sessionsService.all
        return ResponseEntity(listSessions, HttpStatus.OK)
    }

    @GetMapping("/sincetoday")
    fun getSessionSinceToday(): ResponseEntity<MutableList<Session>> {
        var listSinceToday: MutableList<Session>? = mutableListOf()
        var listSessions: MutableList<Session>? =sessionsService.all

        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val currentDate = LocalDate.now()

        listSessions?.forEach{
            var dateStr :String = it.date

            var date = LocalDate.parse(dateStr,formatter)

            if (date >= currentDate){
                println("HOY---> "+currentDate.toString())
                println("fecha---> "+date.toString())
                listSinceToday?.add(it)
            }

        }
        return ResponseEntity(listSinceToday, HttpStatus.OK)
    }

    @GetMapping("/today")
    fun getSessionsToday(): ResponseEntity<MutableList<Session>> {
        var listSinceToday: MutableList<Session>? = mutableListOf()
        var listSessions: MutableList<Session>? =sessionsService.all

        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val currentDate = LocalDate.now()

        listSessions?.forEach{
            var dateStr :String = it.date

            var date = LocalDate.parse(dateStr,formatter)

            if (currentDate == date){
                listSinceToday?.add(it)
            }

        }
        return ResponseEntity(listSinceToday, HttpStatus.OK)
    }

    /*
    @GetMapping("/{id}")
    fun getOneSession(@PathVariable id: String): ResponseEntity<Session>{
        var idSession: Long = id.toLong()
        var session: Session? = sessionsService[idSession]

        return ResponseEntity<Session>(session, HttpStatus.OK)
    }

    @PostMapping("/")
    fun insertSession(@RequestBody session: Session): ResponseEntity<Session> {

        sessionsService.save(session)

        println("ID: ${session.id}")
        return ResponseEntity<Session>(session, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteSession(@PathVariable id: String): ResponseEntity<String>{
        sessionsService.delete(id.toLong())
        return ResponseEntity<String>("DELETED", HttpStatus.OK)
    }
*/
}