package com.egecube.eduplatform._security_.accounts

import com.egecube.eduplatform._security_.accounts.domain.UserAccount
import com.egecube.eduplatform._security_.accounts.domain.UserRole
import com.egecube.eduplatform._security_.accounts.dto.ChangeUserDataDto
import com.egecube.eduplatform._security_.accounts.dto.RegisterRequest
import com.egecube.eduplatform._security_.accounts.dto.UserAccountDto
import com.egecube.eduplatform._security_.events.UserAccountCreated
import com.egecube.eduplatform._security_.events.UserAccountDeleted
import com.egecube.eduplatform._security_.events.UserAccountModified
import com.egecube.eduplatform._security_.events.UserRightsUpdated
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserAccountService(
    private val userAccountRepository: UserAccountRepository,
    private val passwordEncoder: PasswordEncoder,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun registerNewUser(request: RegisterRequest): UserAccountDto? {
        if (userAccountRepository.findByEmail(request.userMail) != null) {
            return null
        }
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
            // Publish event
            applicationEventPublisher.publishEvent(
                UserAccountCreated(UserAccountDto(registered))
            )
            UserAccountDto(registered)
        } catch (_: IllegalArgumentException) {
            logger.warn("Unable to register new user ${newUser.email}")
            null
        }
    }

    fun getUserById(id: Long): UserAccountDto? {
        return try {
            val userAccount = userAccountRepository.findById(id).get()
            UserAccountDto(userAccount)
        } catch (e: NoSuchElementException) {
            null
        }
    }

    fun getUserByName(name: String): UserAccountDto? {
        return try {
            val userAccount = userAccountRepository.findByEmail(name)
            UserAccountDto(userAccount!!)
        } catch (e: NoSuchElementException) {
            null
        } catch (e: NullPointerException) {
            null
        }
    }

    @Transactional
    fun changeBaseUserDataById(id: Long, changes: ChangeUserDataDto): UserAccountDto? {
        return try {
            val changing = userAccountRepository.findById(id).get()
            // Change base values
            changing.email = changes.userMail
            changing.firstName = changes.firstName
            changing.lastName = changes.lastName
            changing.phone = changes.userPhone
            // Save
            val changed = userAccountRepository.save(changing)
            // Publish event
            applicationEventPublisher.publishEvent(
                UserAccountModified(UserAccountDto(changed))
            )
            logger.info("Changed base account info for ${changing.email}")
            UserAccountDto(changed)
        } catch (e: NoSuchElementException) {
            null
        }
    }

    @Transactional
    fun changeSecureUserDataById(id: Long, changes: ChangeUserDataDto): UserAccountDto? {
        return try {
            val changing = userAccountRepository.findById(id).get()
            // Change secure values
            changing.role = changes.role
            // Save
            val changed = userAccountRepository.save(changing)
            // Publish event
            applicationEventPublisher.publishEvent(
                UserRightsUpdated(changed.role)
            )
            logger.warn("Changed secure account info of ${changing.email} to ${changing.role}")
            UserAccountDto(changed)
        } catch (e: NoSuchElementException) {
            null
        }
    }

    @Transactional
    fun deleteAccountById(id: Long): Long {
        userAccountRepository.deleteById(id)
        applicationEventPublisher.publishEvent(UserAccountDeleted(id))
        logger.info("Deleted account of user $id")
        return id
    }
}