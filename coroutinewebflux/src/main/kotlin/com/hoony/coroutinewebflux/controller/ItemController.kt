package com.hoony.coroutinewebflux.controller

import com.hoony.coroutinewebflux.service.ItemService
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/items")
class ItemController(
    private val itemService: ItemService
) {

    @GetMapping
    suspend fun getItems() = coroutineScope { itemService.getItem() }

    @GetMapping("/{id}")
    suspend fun getItemById( @PathVariable id: Long) = coroutineScope { itemService.getItemById(id) }
}