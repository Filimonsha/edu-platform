package com.egecube.eduplatform.homeworksManagement.internal

import com.egecube.eduplatform.homeworksManagement.internal.domain.Attachment
import org.springframework.data.mongodb.repository.MongoRepository

interface AttachmentRepository:MongoRepository<Attachment,String> {
}