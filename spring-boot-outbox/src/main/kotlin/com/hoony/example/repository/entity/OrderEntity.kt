package com.hoony.example.repository.entity

import javax.persistence.*

@Table(name = "orders")
@Entity
class OrderEntity(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Long = 0,

  @Column(name = "sku")
  var sku: String,

  @Column(name = "quantity")
  var quantity: Long,

  @Column(name = "buyer")
  var buyer: String,

  ) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as OrderEntity

    if (id != other.id) return false
    if (sku != other.sku) return false
    if (quantity != other.quantity) return false
    if (buyer != other.buyer) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + sku.hashCode()
    result = 31 * result + quantity.hashCode()
    result = 31 * result + buyer.hashCode()
    return result
  }

  override fun toString(): String {
    return "OrderEntity(id=$id, sku='$sku', quantity=$quantity, buyer='$buyer')"
  }
}