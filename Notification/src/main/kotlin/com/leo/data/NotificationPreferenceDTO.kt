package com.leo.data

import jakarta.persistence.Entity
import jakarta.persistence.Id

data class NotificationPreferenceDTO(
    var userId: Int,
    var largeTransaction: Boolean,
    var lowBalance: Boolean,
    var notificationMethod: NotificationMethod?
)

