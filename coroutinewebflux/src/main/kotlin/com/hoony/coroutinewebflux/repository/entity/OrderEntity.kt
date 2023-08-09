package com.hoony.coroutinewebflux.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("orders")
class OrderEntity (
    @Id
    @Column("order_id")
    var orderId : Long,
    @Column("order_date_time")
    var orderDateTime: LocalDateTime,
    @Column("user_id")
    var userId: Long,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderEntity

        if (orderId != other.orderId) return false
        if (orderDateTime != other.orderDateTime) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = orderId.hashCode()
        result = 31 * result + orderDateTime.hashCode()
        result = 31 * result + userId.hashCode()
        return result
    }
}
