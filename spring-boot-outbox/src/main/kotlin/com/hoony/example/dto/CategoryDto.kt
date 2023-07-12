package com.hoony.example.dto

import java.io.Serializable

data class CategoryDto(
  val categoryCode: String,
  var categoryName: String,
): Serializable