package com.hoony.example.dto

import java.io.Serializable

data class OrderReqDto(
  val sku: String,
  val quantity: Long,
  val buyer: String,
)

data class OrderResDto(
  val id: Long,
  val sku: String,
  val quantity: Long,
  val buyer: String,
)