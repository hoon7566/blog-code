package com.hoony.example.service

import com.hoony.example.config.CacheNames
import com.hoony.example.dto.CategoryDto
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CacheService {


  @Cacheable(value = [CacheNames.META_DATA_SERVICE_GET_CATEGORY], key = "#root.methodName")
  fun getCategory() : List<CategoryDto> {
    println("getCategory")
    return mutableListOf(
        CategoryDto("11111111", "반팔" ),
        CategoryDto("11111112", "반바지" ),
        CategoryDto("11111113", "코트" ),
    )
  }
}