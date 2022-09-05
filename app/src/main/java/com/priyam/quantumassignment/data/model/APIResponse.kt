package com.priyam.quantumassignment.data.model

data class APIResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)