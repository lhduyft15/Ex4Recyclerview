package com.hnam.recyclerview

data class Movie(
    val title: String,
    val poster_path: String,
    val backdrop_path: String,
    val overview: String,
    val vote_average: Float,
    val vote_count: Int,
    val release_date: String,
    val video: Boolean)