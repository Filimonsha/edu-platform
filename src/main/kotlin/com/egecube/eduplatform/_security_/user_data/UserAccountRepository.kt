package com.egecube.eduplatform._security_.user_data

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAccountRepository: CrudRepository<UserAccount, Long> {

    fun findByEmail(query: String): UserAccount?

    fun findByAllNamesContaining(query: String): ArrayList<UserAccount>? {
        return findAllByFirstNameContainingOrLastNameContainingOrderByFirstName(query, query)
    }

    fun findAllByFirstNameContainingOrLastNameContainingOrderByFirstName(queryF: String, queryS: String): ArrayList<UserAccount>?
}
