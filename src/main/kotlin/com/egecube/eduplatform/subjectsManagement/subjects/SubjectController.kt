package com.egecube.eduplatform.subjectsManagement.subjects

import com.egecube.eduplatform.subjectsManagement.subjects.dto.SubjectResponseDto
import com.egecube.eduplatform.subjectsManagement.subjects.consts.SubjectsRoutes
import com.egecube.eduplatform.subjectsManagement.subjects.utils.mapSubjectToResponse
import org.modelmapper.ModelMapper
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
    fun getSubjectById(@PathVariable(value = "id") subjectId: Long): SubjectResponseDto {
        return mapSubjectToResponse(subjectService.getById(subjectId).orElseThrow())
    }


    @GetMapping(SubjectsRoutes.SUBJECT_PARTICIPANTS)
    fun getSubjectParticipants(@PathVariable id: Long) {
    }

    @PostMapping(SubjectsRoutes.SUBJECT_PARTICIPANTS)
    fun addParticipantToSubject(@PathVariable id: Long, @RequestBody participantsIds: Set<Long>): SubjectResponseDto {
        return mapSubjectToResponse(subjectService.addParticipantsToSubject(id, participantsIds))
    }

}