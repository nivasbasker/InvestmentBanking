package com.leo.data

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id

@Entity
data class NotificationPreference(
    @Id
    var userId: Int,
    var largeTransaction: Boolean,
    var lowBalance: Boolean,

    @Enumerated(EnumType.STRING)
    var notificationMethod: NotificationMethod
)

enum class NotificationMethod {
    EMAIL,
    SMS
}
