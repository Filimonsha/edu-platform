package com.egecube.eduplatform._security_.user_data

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users_capacity")
open class UserAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var firstName: String?,
    var lastName: String?,
    var email: String?,
    var password: String?,
    var accountSuspended: Boolean = false,
    @Enumerated(EnumType.STRING)
    var role: UserRole = UserRole.USER
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return arrayListOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String =
        if (password.isNullOrEmpty()) "null" else password!!

    override fun getUsername(): String =
        if (email.isNullOrEmpty()) "null" else email!!

    override fun isAccountNonExpired() = !accountSuspended
    override fun isAccountNonLocked() = !accountSuspended
    override fun isEnabled() = !accountSuspended

    // No expiration logic yet
    override fun isCredentialsNonExpired() = true
}