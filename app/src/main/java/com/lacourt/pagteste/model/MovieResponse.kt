package com.lacourt.pagteste.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @Expose
    val results: List<Movie>
)