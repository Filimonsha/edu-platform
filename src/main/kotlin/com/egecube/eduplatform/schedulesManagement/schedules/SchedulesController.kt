//package com.egecube.eduplatform.schedulesManagement.schedules
//
//import com.egecube.eduplatform.schedulesManagement.schedules.const.SchedulesRoutes
//import com.egecube.eduplatform.schedulesManagement.schedules.dto.ScheduleResponseDto
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RestController
//
//
//@RestController
//class SchedulesController(
//    private val schedulesService: SchedulesService
//) {
////    @GetMapping(SchedulesRoutes.SCHEDULES)
////    fun getSchedules():List
//
//    @GetMapping(SchedulesRoutes.SCHEDULE)
//    fun getSchedule(@PathVariable(value = "scheduleId") scheduleId:Long):ScheduleResponseDto{
//        return schedulesService.getById(scheduleId)
//    }
//
//}