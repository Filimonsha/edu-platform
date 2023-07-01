package com.egecube.eduplatform.lectures.rest

import com.egecube.eduplatform.lectures.domain.Lecture
import com.egecube.eduplatform.lectures.dto.LectureDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class LecturesService(
    private val lecturesRepository: LecturesRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getTranslationsForUser(
        principal: Principal
    ): List<Lecture> {
        return lecturesRepository.findAll() as ArrayList<Lecture>
    }

    fun getTranslationById(eventId: Long): Lecture? {
        return try {
            lecturesRepository.findById(eventId).get()
        } catch (e: NoSuchElementException) {
            null
        }
    }

    fun createTranslation(lectureDto: LectureDto): Long? {
        val newTranslation = Lecture.build()
            .initCommonWithDto<Lecture>(lectureDto.common).also {
                it!!.externalLink = lectureDto.externalLink
                it.webinarStatus = lectureDto.webinarStatus
            }
        return try {
            val stream = lecturesRepository.save(newTranslation!!)
            logger.info("Created new translation: ${stream.name}")
            stream.id
        } catch (e: IllegalArgumentException) {
            logger.warn("Unable to register stream")
            null
        }
    }
}