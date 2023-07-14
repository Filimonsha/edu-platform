package com.egecube.eduplatform.homeworksManagement.utils.evaluation

data class Criteria(
    val weightage: Map<String, Double>,
    val minScore: Double,
    val maxScore: Double,
    val additionalDetails: Map<String, String>
)