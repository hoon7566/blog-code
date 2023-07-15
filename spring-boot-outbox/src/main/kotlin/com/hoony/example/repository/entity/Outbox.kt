package com.hoony.example.repository.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "outbox")
@Entity
class Outbox {

  //outbox table 설계
  //id, aggregate_id, aggregate_type, type, payload, created_at, processed_at, failed_at, error_message
  //id: pk
  @Id
  @Column(name = "id")
  var id: String = "String"

  @Column(name = "payload")
  var payload: String? = null
}