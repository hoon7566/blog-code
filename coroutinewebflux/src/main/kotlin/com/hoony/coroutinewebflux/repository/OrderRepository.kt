package com.hoony.coroutinewebflux.repository

import com.hoony.coroutinewebflux.repository.entity.OrderEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface OrderRepository : R2dbcRepository<OrderEntity, Long>{
}