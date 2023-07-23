package com.egecube.eduplatform.homeworksManagement.attachments.internal

import org.bson.types.Binary

data class Attachment(
    val title: String,
    val data: Binary
)