package com.hoony.coroutinewebflux.repository

import com.hoony.coroutinewebflux.repository.entity.ItemEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ItemRepository : CoroutineCrudRepository<ItemEntity, Long> {

    suspend fun findByItemId(itemId: Long): ItemEntity?

    suspend fun findByCategoryCode(categoryCode: String): Flow<ItemEntity>

    @Query("SELECT * FROM items WHERE item_name = :itemName")
    suspend fun findByItemName(itemName: String): Flow<ItemEntity>
}