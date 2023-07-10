//package com.egecube.eduplatform.lecturesManagement.rest
//
//import com.egecube.eduplatform.lecturesManagement.consts.LecturesRoutes
//import com.egecube.eduplatform.lecturesManagement.domain.Lecture
//import com.egecube.eduplatform.lecturesManagement.dto.LectureDto
//import org.springframework.http.ResponseEntity
//import org.springframework.security.access.annotation.Secured
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//class LecturesController(
//    private val lecturesService: LecturesService
//) {
//
//    @GetMapping(LecturesRoutes.LECTURES_ROUTE)
//    fun getAvailableLectures(): ResponseEntity<List<Lecture>> {
//        val streams = lecturesService.getTranslationsForUser()
//        return if (streams.isNotEmpty()) {
//            ResponseEntity.ok().body(streams)
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }
//
//    @Secured("ADMIN", "TEACHER")
//    @PostMapping(LecturesRoutes.LECTURES_ROUTE)
//    fun addNewStream(
//        @RequestBody lectureDto: LectureDto
//    ): ResponseEntity<Long> {
//        println(lectureDto.common.assignedPersonIds)
//        val newStreamId = lecturesService.createTranslation(lectureDto)
//        return if (newStreamId != null) {
//            ResponseEntity.ok().body(newStreamId)
//        } else {
//            ResponseEntity.badRequest().build()
//        }
//    }
//
//    @GetMapping(LecturesRoutes.LECTURE_ROUTE)
//    fun getStream(
//        @PathVariable("id") streamId: Long
//    ): ResponseEntity<Lecture> {
//        val stream = lecturesService.getTranslationById(streamId)
//        return if (stream != null) {
//            ResponseEntity.ok().body(stream)
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }
//}