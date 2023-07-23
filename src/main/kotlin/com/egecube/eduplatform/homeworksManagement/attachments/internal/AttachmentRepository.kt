package com.egecube.eduplatform.homeworksManagement.attachments.internal

import org.springframework.data.mongodb.repository.MongoRepository

interface AttachmentRepository:MongoRepository<Attachment,String> {
}