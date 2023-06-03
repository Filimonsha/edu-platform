package com.egecube.eduplatform._security_.accounts

import com.egecube.eduplatform._security_.accounts.domain.UserAccount
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAccountRepository: CrudRepository<UserAccount, Long> {
    fun findByEmail(query: String): UserAccount?
}
