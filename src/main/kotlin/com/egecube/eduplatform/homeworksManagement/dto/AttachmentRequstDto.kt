package com.egecube.eduplatform.homeworksManagement.dto

import org.springframework.web.multipart.MultipartFile

data class AttachmentRequstDto(
    val title: String,
    val file: MultipartFile
)
