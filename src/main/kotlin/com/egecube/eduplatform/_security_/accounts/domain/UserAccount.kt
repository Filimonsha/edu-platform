package com.egecube.eduplatform._security_.accounts.domain

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

// todo(kotlin jpa not working!!!!!)

@Entity
@Table(name = "users_capacity")
open class UserAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long,
    open var firstName: String,
    open var lastName: String,
    open var email: String,
    open var phone: String?,
    open var passWord: String,
    open var accountSuspended: Boolean = false,
    @Enumerated(EnumType.STRING)
    open var role: UserRole = UserRole.USER
) : UserDetails {

    // Custom builder, cause Kotlin noArg plugin generates only Java empty constructor
    companion object {
        fun build(): UserAccount = UserAccount::class.java.getConstructor().newInstance()
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return arrayListOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String = passWord
    override fun getUsername(): String = email

    override fun isAccountNonExpired() = !accountSuspended
    override fun isAccountNonLocked() = !accountSuspended
    override fun isEnabled() = !accountSuspended

    // No expiration logic yet
    override fun isCredentialsNonExpired() = true
}
