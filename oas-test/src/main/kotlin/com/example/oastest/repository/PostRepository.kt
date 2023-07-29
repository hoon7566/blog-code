package com.example.oastest.repository

import com.example.oastest.repository.entity.PostEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface PostRepository : R2dbcRepository<PostEntity, Long> {
}