package com.hoony.coroutinewebflux.repository

import com.hoony.coroutinewebflux.repository.entity.ItemEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ItemRepository : CoroutineCrudRepository<ItemEntity, Long> {

    suspend fun findByItemId(itemId: Long): ItemEntity?

    suspend fun findByCategoryCode(categoryCode: String): Flow<ItemEntity>
}