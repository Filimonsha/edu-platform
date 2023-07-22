package com.egecube.eduplatform.filesManagement.internal

import com.egecube.eduplatform.filesManagement.AttachmentFileService
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class FileController(
    private val attachmentFileService: AttachmentFileService
) {
    @GetMapping("/api/files/{fileId}")
    fun getFile(
        @PathVariable fileId: String
    ): ResponseEntity<ByteArrayResource> {
        val attachmentFile = attachmentFileService.getFile(fileId)
        return ResponseEntity.ok()
            .contentType(attachmentFile.fileType)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentFile.fileName + "\"")
            .body(ByteArrayResource(attachmentFile.fileData))
    }

    @PostMapping("/api/files/")
    fun loadFile(@RequestParam file: MultipartFile): String {
        return attachmentFileService.saveFile(file).toString()
    }
}