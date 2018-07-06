package com.nomkhonwaan.mb.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Post(
        @JsonProperty("type") val type: String,
        @JsonProperty("id") val id: String,
        @JsonProperty("attributes") val attributes: PostAttributes
//        @JsonProperty("relationships") val relationships: PostRelationships
)

data class PostAttributes(
        @JsonProperty("title") val title: String,
        @JsonProperty("slug") val slug: String,
        @JsonProperty("status") val status: PostStatus,
        @JsonProperty("publishedAt") val publishedAt: String?,
        @JsonProperty("html") val html: String,
        @JsonProperty("markdown") val markdown: String,
        @JsonProperty("createdAt") val createdAt: String,
        @JsonProperty("updatedAt") val updatedAt: String?
)

enum class PostStatus { DRAFt, PUBLISHED }