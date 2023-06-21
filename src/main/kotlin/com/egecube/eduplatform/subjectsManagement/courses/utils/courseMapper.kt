package com.egecube.eduplatform.subjectsManagement.courses.utils

import com.egecube.eduplatform.subjectsManagement.courses.Course
import com.egecube.eduplatform.subjectsManagement.courses.dto.CourseResponseDto


fun mapCourseToResponse(
    course: Course
) = CourseResponseDto(
    course.id,
    course.name,
    course.subject.id,
    course.participants.map { participant -> participant.id }.toSet(),
)