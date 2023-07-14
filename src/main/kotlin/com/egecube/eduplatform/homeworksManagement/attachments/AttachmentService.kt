package com.egecube.eduplatform.homeworksManagement.attachments

import com.egecube.eduplatform.homeworksManagement.attachments.internal.Attachment
import com.egecube.eduplatform.homeworksManagement.attachments.internal.AttachmentRepository
import org.bson.BsonBinarySubType
import org.bson.types.Binary
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
class AttachmentService(
    private val attachmentRepository: AttachmentRepository
) {
    fun loadAttachment(title: String, file: MultipartFile): Attachment {
        print(file.bytes.toString())
        val attachment = Attachment(
            title,
            Binary(
                BsonBinarySubType.BINARY,
                file.bytes
            )
        )
        return attachmentRepository.save(attachment)

    }

    fun getAttachemnt(): Attachment {
        return attachmentRepository.findAll().get(0)
    }
}