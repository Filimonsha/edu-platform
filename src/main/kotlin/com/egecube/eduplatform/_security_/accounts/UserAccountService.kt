package com.egecube.eduplatform._security_.accounts

import com.egecube.eduplatform._security_.accounts.dto.RegisterRequest
import com.egecube.eduplatform._security_.accounts.domain.UserAccount
import com.egecube.eduplatform._security_.accounts.domain.UserRole
import com.egecube.eduplatform._security_.accounts.dto.ChangeUserDataDto
import com.egecube.eduplatform._security_.accounts.dto.UserAccountDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

// TODO("make full review and rewrite logic")

@Service
class UserAccountService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var userAccountRepository: UserAccountRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun registerNewUser(request: RegisterRequest): Long? {
        val newUser = UserAccount.build().also {
            it.firstName = request.firstName
            it.lastName = request.lastName
            it.email = request.userMail
            it.phone = request.userPhone
            it.passWord = passwordEncoder.encode(request.password)
            it.role = UserRole.USER
        }
        return try {
            val registered = userAccountRepository.save(newUser)
            logger.info("Registered new user: ${registered.email}")
            registered.id
        } catch (_: IllegalArgumentException) {
            logger.warn("Unable to register new user ${newUser.email}")
            null
        }
    }

    fun getUserById(id: Long): UserAccountDto? {
        return try {
            val user = userAccountRepository.findById(id).get()
            UserAccountDto(
                userId = user.id,
                accountSuspended = user.accountSuspended,
                firstName = user.firstName,
                lastName = user.lastName,
                userMail = user.email,
                userPhone = user.phone,
                userRole = user.role
            )
        } catch (e: UsernameNotFoundException) {
            null
        }

    }

    fun changeBaseUserDataById(id: Long, changes: ChangeUserDataDto): Long? {
        return try {
            val changing = userAccountRepository.findById(id).get()
            // Change base values
            changing.email = changes.userMail
            changing.firstName = changes.firstName
            changing.lastName = changes.lastName
            changing.phone = changes.userPhone
            // Save
            userAccountRepository.save(changing)
            logger.info("Changed base account info for ${changing.email}")
            changing.id
        } catch (e: UsernameNotFoundException) {
            null
        }
    }

    fun changeSecureUserDataById(id: Long, changes: ChangeUserDataDto): Long? {
        return try {
            val changing = userAccountRepository.findById(id).get()
            // Change base values
            changing.role = changes.role
            // Save
            userAccountRepository.save(changing)
            logger.warn("Changed secure account info of ${changing.email} to ${changing.role}")
            changing.id
        } catch (e: UsernameNotFoundException) {
            null
        }
    }

    fun deleteAccountById(id: Long): Long {
        userAccountRepository.deleteById(id)
        return id
    }
}