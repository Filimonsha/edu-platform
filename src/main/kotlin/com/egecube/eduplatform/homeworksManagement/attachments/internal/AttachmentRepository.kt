package com.egecube.eduplatform.homeworksManagement.attachments.internal

import com.egecube.eduplatform.homeworksManagement.attachments.internal.Attachment
import org.springframework.data.mongodb.repository.MongoRepository

interface AttachmentRepository:MongoRepository<Attachment,String> {
}