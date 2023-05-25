package com.egecube.eduplatform.subjectsManagement.subjects.utils

import com.egecube.eduplatform.subjectsManagement.subjects.dto.SubjectResponseDto
import com.egecube.eduplatform.subjectsManagement.subjects.Subject


fun mapSubjectToResponse(
    subject: Subject
) = SubjectResponseDto(
    subject.id,
    subject.name,
    subject.description,
    subject.courses.map { course -> course.id }.toSet(),
    subject.participants.map { participant -> participant.id }.toSet()
)