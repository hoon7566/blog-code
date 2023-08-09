package com.hoony.coroutinewebflux.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("users")
class UserEntity (
    @Id
    @Column("user_id")
    var userId: Long,
    @Column("user_name")
    var userName: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (userId != other.userId) return false
        if (userName != other.userName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + userName.hashCode()
        return result
    }
}
