package com.egecube.eduplatform._security_.tokens.domain

import jakarta.persistence.*


@Entity
open class UserToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long,
    open var username: String,
    @Column(length = 1024)
    open var token: String
) {
    companion object {
        fun build(): UserToken = UserToken::class.java.getConstructor().newInstance()
    }
}
