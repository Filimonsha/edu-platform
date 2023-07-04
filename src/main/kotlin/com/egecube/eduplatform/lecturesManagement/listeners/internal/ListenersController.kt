package com.egecube.eduplatform.lecturesManagement.listeners.internal

import com.egecube.eduplatform.lecturesManagement.lectures.LectureResponseDto
import com.egecube.eduplatform.lecturesManagement.lectures.internal.Lecture
import com.egecube.eduplatform.lecturesManagement.listeners.ListenersService
import com.egecube.eduplatform.lecturesManagement.listeners.internal.const.ListenersRoutes
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.*

@RestController
class ListenersController(
    private val listenersService: ListenersService,
    private val modelMapper: ModelMapper,
) {

    @GetMapping(ListenersRoutes.LISTENERS)
    fun getAllListeners(): List<ListenerResponseDTO> {

        return listenersService
            .getListeners()
            .map { listener: Listener ->
                ListenerResponseDTO(
                    listener.name,
                    listener.secondName,
                    listener.email,
                    listener.availableLectures.map { lecture: Lecture -> lecture.id }.toSet()
                )
            }
    }

//    @GetMapping(ListenersRoutes.LISTENER_LECTURES)
    @GetMapping("api/lectures-management/listeners/{listenerId}/lectures")
    fun getAllListenerLectures(@PathVariable("listenerId") listenerId: Long): List<LectureResponseDto> {
        return listenersService
            .getAllListenerLectures(listenerId)
    }

    @PostMapping(ListenersRoutes.LISTENER_LECTURES)
    fun addToListenerAvailableLecture(
        @PathVariable("listenerId") listenerId: Long,
        @RequestBody listenersIds: Set<Long>
    ) {
        listenersService.addToListenerAvailableLecture(listenerId, listenersIds)
    }
}