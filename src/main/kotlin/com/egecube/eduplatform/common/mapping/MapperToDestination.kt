package com.egecube.eduplatform.common.mapping

interface MapperToDestination {
    fun <T> mapToDestination(): T
}