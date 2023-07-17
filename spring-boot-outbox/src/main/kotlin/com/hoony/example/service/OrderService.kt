package com.hoony.example.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.hoony.example.config.Log
import com.hoony.example.dto.OrderReqDto
import com.hoony.example.dto.OrderResDto
import com.hoony.example.repository.OrderRepository
import com.hoony.example.repository.OutboxRepository
import com.hoony.example.repository.entity.OrderEntity
import com.hoony.example.repository.entity.OutboxEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class OrderService(
  private val orderRepository: OrderRepository,
  private val outBoxRepository: OutboxRepository,
) : Log {

  @Transactional
  fun order(orderReqDto: OrderReqDto): OrderResDto {
    val order = OrderEntity(
      sku = orderReqDto.sku,
      quantity = orderReqDto.quantity,
      buyer = orderReqDto.buyer,
    ).let {
      orderRepository.save(it)
    }

    OutboxEntity(
      topic = "orderQueue",
      payload = ObjectMapper().writeValueAsString(order),
    ).let {
      outBoxRepository.save(it)
    }
    return order.toS()
  }

  fun OrderEntity.toS() = OrderResDto(
    id = this.id,
    sku = this.sku,
    quantity = this.quantity,
    buyer = this.buyer,
  )


}