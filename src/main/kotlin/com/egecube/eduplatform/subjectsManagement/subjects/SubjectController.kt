package com.egecube.eduplatform.subjectsManagement.subjects

import com.egecube.eduplatform.subjectsManagement.subjects.consts.SubjectsRoutes
import com.egecube.eduplatform.subjectsManagement.subjects.dto.SubjectResponseDto
import com.egecube.eduplatform.subjectsManagement.subjects.utils.mapSubjectToResponse
import org.modelmapper.ModelMapper
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
class SubjectController(
    private val subjectService: SubjectService,
    private val modelMapper: ModelMapper,
) {


    @GetMapping(SubjectsRoutes.SUBJECTS)
    fun getSubjects(): List<SubjectResponseDto> =
        subjectService.getAll().map { subject -> mapSubjectToResponse(subject) }

    @GetMapping(SubjectsRoutes.SUBJECT)
    fun getSubjectById(@PathVariable(value = "subjectId") subjectId: Long): SubjectResponseDto {
        return mapSubjectToResponse(subjectService.getById(subjectId))
    }


    @GetMapping(SubjectsRoutes.SUBJECT_PARTICIPANTS)
    fun getSubjectParticipants(@PathVariable id: Long) {
    }

    @Secured("ADMIN")
    @PostMapping(SubjectsRoutes.SUBJECT_PARTICIPANTS)
    fun addParticipantToSubject(
        @PathVariable subjectId: Long,
        @RequestBody participantsIds: Set<Long>
    ): SubjectResponseDto {
        return mapSubjectToResponse(subjectService.addParticipantsToSubject(subjectId, participantsIds))
    }

}