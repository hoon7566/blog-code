package com.example.oastest.repository.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("POST")
class PostEntity(
    @Id
    @Column("ID")
    var id: Long? = null,
    @Column("TITLE")
    var title: String,
    @Column("CONTENT")
    var content: String,
    @Column("AUTHOR_ID")
    var authorId: Long,
) {
    @CreatedDate
    @Column("CREATED_AT")
    lateinit var createdAt: LocalDateTime
    @LastModifiedDate
    @Column("UPDATED_AT")
    lateinit var updatedAt: LocalDateTime
}