package com.egecube.eduplatform.filesManagement

import com.egecube.eduplatform.filesManagement.dto.AttachmentFile
import com.mongodb.client.gridfs.model.GridFSFile
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.gridfs.GridFsOperations
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AttachmentFileService(
    private val gridFsTemplate: GridFsTemplate,
    private val gridFsOperations: GridFsOperations
) {
    fun loadFile(multipartFile: MultipartFile): ObjectId {
//        val basicDBObject = BasicDBObject()
//        basicDBObject.put("fileSize")
//        metadata.put("fileSize", upload.getSize());

        //store in database which returns the objectID

        val objectId = gridFsTemplate.store(multipartFile.inputStream, multipartFile.name, multipartFile.contentType)
        //return as a string
        return objectId
    }

    fun getFile(fileId:String):AttachmentFile{

        val gridFSFile: GridFSFile = gridFsTemplate.findOne(Query(Criteria.where("_id").`is`(fileId)))

        return AttachmentFile(
            gridFSFile.filename,
            MediaType.parseMediaType(gridFSFile.metadata?.get("_contentType").toString()),
            gridFsOperations.getResource(gridFSFile).inputStream.readAllBytes())
//                    loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );

    }
}
