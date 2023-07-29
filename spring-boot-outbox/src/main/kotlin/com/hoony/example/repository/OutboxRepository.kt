package com.hoony.example.repository

import com.hoony.example.repository.entity.OrderEntity
import com.hoony.example.repository.entity.OutboxEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OutboxRepository : JpaRepository<OutboxEntity, String> {
}