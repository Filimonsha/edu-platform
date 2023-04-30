package com.egecube.eduplatform.homework.evaluation

data class Criteria(
    val weightage: Map<String, Double>,
    val minScore: Double,
    val maxScore: Double,
    val additionalDetails: Map<String, String>
)