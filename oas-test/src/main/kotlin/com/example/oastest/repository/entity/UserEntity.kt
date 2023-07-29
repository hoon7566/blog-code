package com.example.oastest.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


@Table("USERS")
class UserEntity(
    @Id
    @Column("ID")
    var id: Long? = null,
    @Column("NAME")
    var name: String,
    @Column("AGE")
    var age: Long,
)