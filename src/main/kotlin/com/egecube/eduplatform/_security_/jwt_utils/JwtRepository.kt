package com.egecube.eduplatform._security_.jwt_utils

import com.egecube.eduplatform._security_.tokens.domain.UserToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface JwtRepository: CrudRepository<UserToken, Long> {
    fun findAllByUsername(name: String): List<UserToken>
    fun deleteAllByUsername(name: String)
    fun findByUsername(name: String): Optional<UserToken>
}