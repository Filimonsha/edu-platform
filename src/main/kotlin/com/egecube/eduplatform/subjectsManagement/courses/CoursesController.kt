package com.egecube.eduplatform.subjectsManagement.courses

import com.egecube.eduplatform.subjectsManagement.courses.consts.CoursesRoutes
import com.egecube.eduplatform.subjectsManagement.courses.dto.CourseRequestDto
import com.egecube.eduplatform.subjectsManagement.courses.dto.CourseResponseDto
import com.egecube.eduplatform.subjectsManagement.courses.utils.mapCourseToResponse
import org.modelmapper.ModelMapper
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*


@RestController
class CoursesController(private val courseService: CourseService, private val modelMapper: ModelMapper) {

    @GetMapping(CoursesRoutes.COURSES)
    fun getCoursesBySubjectId(@PathVariable subjectId: Long): List<CourseResponseDto> {
        return courseService.getCoursesBySubjectId(subjectId)
            .map { course -> mapCourseToResponse(course) }
    }

    @Secured("ADMIN")
    @PostMapping(CoursesRoutes.COURSES)
    fun createCourse(@PathVariable subjectId: Long, @RequestBody requestBody: CourseRequestDto): CourseResponseDto {

        return mapCourseToResponse(courseService.createCourse(subjectId, requestBody.name))
    }

    @GetMapping(CoursesRoutes.COURSE)
    fun getGroupsByFlow(@PathVariable subjectId: Long, @PathVariable courseId: Long): CourseResponseDto {
        return mapCourseToResponse(courseService.getCourseById(subjectId, courseId).orElseThrow())
    }

}