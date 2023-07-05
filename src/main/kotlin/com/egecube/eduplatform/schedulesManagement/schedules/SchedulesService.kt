package com.egecube.eduplatform.schedulesManagement.schedules

import com.egecube.eduplatform.lecturesManagement.lectures.LecturesService
import com.egecube.eduplatform.lecturesManagement.listeners.ListenersService
import com.egecube.eduplatform.schedulesManagement.schedules.internal.Schedule
import com.egecube.eduplatform.schedulesManagement.schedules.internal.ScheduleRepository
import com.egecube.eduplatform.schedulesManagement.schedules.internal.dto.ScheduleResponseDto
import com.egecube.eduplatform.subjectsManagement.events.ParticipantRegistered
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class SchedulesService(
    private val scheduleRepository: ScheduleRepository,
    private val lecturesService: LecturesService,
    private val listenersService: ListenersService,
) {
    fun getById(scheduleId: Long): ScheduleResponseDto {

        val foundSchedule = scheduleRepository.findById(scheduleId).orElseThrow()

        val availableListenerLectures = listenersService.getAllListenerLectures(scheduleId)

        return ScheduleResponseDto(
            foundSchedule.id,
            availableListenerLectures
        )
    }

    fun getAll() = scheduleRepository.findAll()


    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun registerSchedule(participantRegistered: ParticipantRegistered): Schedule {
        return scheduleRepository.save(Schedule(participantRegistered.participantId))
    }
}