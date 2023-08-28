package com.egecube.eduplatform.lecturesManagement.lectures.internal

import com.egecube.eduplatform.lecturesManagement.lectures.LectureResponseDto
import com.egecube.eduplatform.lecturesManagement.lectures.LecturesService
import com.egecube.eduplatform.lecturesManagement.lectures.internal.const.LecturesRoutes
import org.springframework.web.bind.annotation.*

@RestController
class LectureController(
    private val lecturesService: LecturesService
) {
    @GetMapping(LecturesRoutes.LECTURES)
    fun getLectures() {
        println("giving lectures")
//        TODO
    }

    @PostMapping(LecturesRoutes.LECTURES)
    fun createLecture(
        @RequestBody lectureRequestDto: LectureRequestDto
    ): LectureResponseDto {
        val newLecture = Lecture(
            id = null,
            name = lectureRequestDto.name,
            description = lectureRequestDto.description,
            startsAt = lectureRequestDto.startsAt,
            endsAt = lectureRequestDto.endsAt
        )

        val createdLecture = lecturesService.createLecture(newLecture, lectureRequestDto.listeners)

        return LectureResponseDto(
            createdLecture.name,
            createdLecture.description,
            createdLecture.startsAt,
            createdLecture.endsAt
        )
    }

    @GetMapping(LecturesRoutes.LECTURE)
    fun getLecture(@PathVariable(value = "lectureId") lectureId: Long): LectureResponseDto {
        val foundLecture = lecturesService.getById(lectureId)
        return LectureResponseDto(
            foundLecture.name,
            foundLecture.description,
            foundLecture.startsAt,
            foundLecture.endsAt
        )
    }

    @PutMapping(LecturesRoutes.LECTURE)
    fun modifyLecture(
        @PathVariable(value = "lectureId") lectureId: Long,
        @RequestBody lecture: LectureRequestDto
    ): LectureResponseDto {
        val (name, description, startsAt, endsAt) = lecture
        val replacedLecture = lecturesService.replaceLecture(
            lectureId,
            Lecture(
                null,
                name,
                description, startsAt, endsAt
            )
        )
        return LectureResponseDto(
            replacedLecture.name,
            replacedLecture.description,
            replacedLecture.startsAt,
            replacedLecture.endsAt
        )
    }
}