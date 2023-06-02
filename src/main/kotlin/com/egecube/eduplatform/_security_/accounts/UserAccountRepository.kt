package com.egecube.eduplatform._security_.accounts

import com.egecube.eduplatform._security_.accounts.domain.UserAccount
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAccountRepository: CrudRepository<UserAccount, Long> {

    // Still nothing but basic functions, probably everything could be overlapped
    // by inner accounts logic

    fun findByEmail(query: String): UserAccount?

//    fun findAllByFirstNameContainingOrLastNameContainingOrderByFirstName(queryF: String, queryS: String): ArrayList<UserAccount>?
}
