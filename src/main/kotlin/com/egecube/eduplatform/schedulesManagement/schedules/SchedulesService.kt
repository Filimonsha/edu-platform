//package com.egecube.eduplatform.schedulesManagement.schedules
//
//import com.egecube.eduplatform.lecturesManagement.lectures.LecturesService
//import com.egecube.eduplatform.schedulesManagement.schedules.dto.ScheduleResponseDto
//import com.egecube.eduplatform.subjectsManagement.events.ParticipantRegistered
//import org.springframework.transaction.event.TransactionalEventListener
//
//class SchedulesService(
//    private val scheduleRepository: ScheduleRepository,
//    private val lecturesService: LecturesService,
//) {
//    fun getById(id: Long):ScheduleResponseDto{
//        val g = lecturesService.
//        scheduleRepository.findById(id)
//    }
//
//    fun getAll() = scheduleRepository.findAll()
//
//
//    @TransactionalEventListener
//    fun registerSchedule(participantRegistered: ParticipantRegistered): Schedule {
//       return scheduleRepository.save(Schedule(participantRegistered.participantId))
//    }
//}