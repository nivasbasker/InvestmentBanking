package com.leo.repo

import com.leo.data.NotificationPreference
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepo : JpaRepository<NotificationPreference, Int>