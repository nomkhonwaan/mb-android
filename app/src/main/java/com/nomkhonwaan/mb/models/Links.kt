package com.nomkhonwaan.mb.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Links (
        @JsonProperty("self") val self: String,
        @JsonProperty("next") val next: String?,
        @JsonProperty("previous") val previous: String?
)