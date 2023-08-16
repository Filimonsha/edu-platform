package com.egecube.eduplatform._security_.jwt_utils

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JwtRepository: CrudRepository<String, Long> {
    fun findStringBy(string: String)
}