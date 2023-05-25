package com.egecube.eduplatform.subjectsManagement.courses

import com.egecube.eduplatform.subjectsManagement.courses.consts.CoursesRoutes
import com.egecube.eduplatform.subjectsManagement.courses.dto.CourseResponseDto
import com.egecube.eduplatform.subjectsManagement.courses.utils.mapCourseToResponse
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class CoursesController(private val courseService: CourseService, private val modelMapper: ModelMapper) {

    @GetMapping(CoursesRoutes.COURSES)
    fun getGroupById(): List<CourseResponseDto> {
        return courseService.getAllCourses()
            .map { course -> mapCourseToResponse(course) }
    }

    @GetMapping(CoursesRoutes.COURSE)
    fun getGroupsByFlow(@PathVariable(value = "id") courseId: Long): CourseResponseDto {
        return mapCourseToResponse(courseService.getCourseById(courseId).orElseThrow())
    }
}