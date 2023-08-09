package com.hoony.coroutinewebflux.repository

import com.hoony.coroutinewebflux.repository.entity.UserEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface UserRepository : R2dbcRepository<UserEntity, Long>{
}