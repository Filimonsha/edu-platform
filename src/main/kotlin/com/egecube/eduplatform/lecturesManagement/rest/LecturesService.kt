//package com.egecube.eduplatform.lecturesManagement.rest
//
//import com.egecube.eduplatform.lecturesManagement.domain.Lecture
//import com.egecube.eduplatform.lecturesManagement.dto.LectureDto
//import org.slf4j.LoggerFactory
//import org.springframework.stereotype.Service
//
//@Service
//class LecturesService(
//    private val lecturesRepository: LecturesRepository
//) {
//    private val logger = LoggerFactory.getLogger(this::class.java)
//
//    fun getTranslationsForUser(): List<Lecture> {
//        return lecturesRepository.findAll() as ArrayList<Lecture>
//    }
//
//    fun getTranslationById(eventId: Long): Lecture? {
//        return try {
//            lecturesRepository.findById(eventId).get()
//        } catch (e: NoSuchElementException) {
//            null
//        }
//    }
//
//    fun createTranslation(lectureDto: LectureDto): Long? {
//        val newTranslation = Lecture.build()
//            .initCommonWithDto<Lecture>(lectureDto.common).also {
//                it!!.externalLink = lectureDto.externalLink
//                it.webinarStatus = lectureDto.webinarStatus
//            }
//        return try {
//            val stream = lecturesRepository.save(newTranslation!!)
//            logger.info("Created new translation: ${stream.name}")
//            stream.id
//        } catch (e: IllegalArgumentException) {
//            logger.warn("Unable to register stream")
//            null
//        }
//    }
//}