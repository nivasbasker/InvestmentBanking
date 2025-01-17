package com.leo.controller

import com.leo.data.NotificationPreference
import com.leo.data.NotificationPreferenceDTO
import com.leo.repo.NotificationRepo
import com.leo.service.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("notifications")
class NotificationController {

    @Autowired
    private lateinit var notificationService: NotificationService

    @Autowired
    private lateinit var environment: Environment

    @PutMapping()
    fun setPreference(@RequestBody notificationPreference: NotificationPreferenceDTO): ResponseEntity<String> {
        notificationService.setPreference(notificationPreference)

        val response = environment.getProperty("API.SET_SUCCESS")
        return ResponseEntity(response, HttpStatus.OK)
    }
}