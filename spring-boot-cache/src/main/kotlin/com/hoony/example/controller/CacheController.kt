package com.hoony.example.controller

import com.hoony.example.dto.CategoryDto
import com.hoony.example.service.CacheService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CacheController(
  private val cacheService: CacheService
) {

  @GetMapping("/category")
  fun getCategory() =
    ResponseEntity.ok(cacheService.getCategory())

  @GetMapping("/test-modify-category")
  fun putCategory(): ResponseEntity<List<CategoryDto>> {
    val categoryList = cacheService.getCategory()

    categoryList.find { categoryDto -> categoryDto.categoryCode == "11111111" }?.let {
      it.categoryName = "반팔 카테고리명 변경 테스트"
    }
    return ResponseEntity.ok(categoryList)
  }


}
