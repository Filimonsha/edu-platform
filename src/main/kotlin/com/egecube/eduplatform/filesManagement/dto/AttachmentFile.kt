package com.egecube.eduplatform.filesManagement.dto

import org.springframework.http.MediaType

class AttachmentFile(
    val fileName: String,
    val fileType: MediaType,
//    val fileSize: String,
    val fileData: ByteArray,
)