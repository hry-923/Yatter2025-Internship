package com.dmm.bootcamp.yatter2025.infra.api.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageJson(
  @Json(name = "id") val id: String,
  @Json(name = "type") val type: String,
  @Json(name = "url") val url: String,
  @Json(name = "description") val description: String?
)
