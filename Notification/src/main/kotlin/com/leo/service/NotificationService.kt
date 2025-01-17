package com.leo.service

import com.leo.data.NotificationMethod
import com.leo.data.NotificationPreference
import com.leo.data.NotificationPreferenceDTO
import com.leo.repo.NotificationRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NotificationService {
    @Autowired
    private lateinit var notificationRepo: NotificationRepo

    @Transactional
    @Modifying
    fun setPreference(dto: NotificationPreferenceDTO) {
        notificationRepo.save(
            NotificationPreference(
                dto.userId,
                dto.largeTransaction,
                dto.lowBalance,
                dto.notificationMethod ?: NotificationMethod.SMS
            )
        )
    }
}