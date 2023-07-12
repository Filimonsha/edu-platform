package com.egecube.eduplatform.homeworksManagement.internal.domain

import org.bson.types.Binary

data class Attachment(
    val title: String,
    val data: Binary
)