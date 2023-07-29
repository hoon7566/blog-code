package com.hoony.example.controller

import com.hoony.example.config.Log
import com.hoony.example.dto.OrderReqDto
import com.hoony.example.service.OrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class OrderController(
  private val orderService: OrderService
) : Log {

  @PostMapping("/order")
  fun order(@RequestBody orderRequest: OrderRequest) {
    orderService.order(orderRequest.toS())
  }

  data class OrderRequest(
    val sku: String,
    val quantity: Long,
    val buyer: String,
  ) {
    fun toS() = OrderReqDto(
      sku = this.sku,
      quantity = this.quantity,
      buyer = this.buyer,
    )
  }

}
