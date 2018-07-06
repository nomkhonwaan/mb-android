package com.nomkhonwaan.mb.models

import com.fasterxml.jackson.annotation.JsonProperty

data class LatestPublishedPosts(
        @JsonProperty("links") val links: Links,
        @JsonProperty("data") val data: List<Post?>
//        @JsonProperty("included") val included: List<Any?>
)