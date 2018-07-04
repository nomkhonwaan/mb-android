package com.nomkhonwaan.mb.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Post(
        @JsonProperty("id") val id: Int,
        @JsonProperty("title") val title: String,
        @JsonProperty("body") val body: String,
        @JsonProperty("userId") val userId: Int
)