package com.hoony.example.repository.entity

import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "outbox")
@Entity
class OutboxEntity (

  @Id
  @GeneratedValue(generator = "uuid")
  @Column(name = "id")
  var id: UUID = UUID.randomUUID(),

  @Column(name = "topic")
  var topic: String,

  @Column(name = "payload")
  var payload: String,

  ){


  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as OutboxEntity

    if (id != other.id) return false
    if (topic != other.topic) return false
    if (payload != other.payload) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + topic.hashCode()
    result = 31 * result + payload.hashCode()
    return result
  }

  override fun toString(): String {
    return "OutboxEntity(id=$id, topic='$topic', payload='$payload')"
  }

}