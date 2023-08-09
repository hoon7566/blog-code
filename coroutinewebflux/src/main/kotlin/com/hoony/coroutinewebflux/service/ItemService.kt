package com.hoony.coroutinewebflux.service

import com.hoony.coroutinewebflux.repository.ItemRepository
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service


@Service
class ItemService(
    private val itemRepository: ItemRepository
) {

    suspend fun getItem() = coroutineScope{
        itemRepository.findAll()
    }

    suspend fun getItemByCategoryCode(categoryCode: String) = coroutineScope{
        itemRepository.findByCategoryCode(categoryCode)
    }

    suspend fun getItemByItemName(itemName: String) = coroutineScope{
        itemRepository.findByItemName(itemName)
    }

    suspend fun getItemById(id: Long) = coroutineScope{
        itemRepository.findById(id)
    }
}