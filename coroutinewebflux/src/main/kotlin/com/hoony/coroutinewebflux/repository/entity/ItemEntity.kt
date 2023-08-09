package com.hoony.coroutinewebflux.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("items")
class ItemEntity (
    @Id
    @Column("item_id")
    var itemId: Long,
    @Column("item_name")
    var itemName: String,
    @Column("category_code")
    var categoryCode: String,
    @Column("item_price")
    var itemPrice: BigDecimal,
    @Column("quantity")
    var quantity: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemEntity

        if (itemId != other.itemId) return false
        if (itemName != other.itemName) return false
        if (categoryCode != other.categoryCode) return false
        if (itemPrice != other.itemPrice) return false
        if (quantity != other.quantity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = itemId.hashCode()
        result = 31 * result + itemName.hashCode()
        result = 31 * result + categoryCode.hashCode()
        result = 31 * result + itemPrice.hashCode()
        result = 31 * result + quantity
        return result
    }

    override fun toString(): String {
        return "ItemEntity(itemId=$itemId, itemName='$itemName', categoryCode='$categoryCode', itemPrice=$itemPrice, quantity=$quantity)"
    }
}