package com.egecube.eduplatform._security_

import com.egecube.eduplatform._security_.accounts.UserAccountRepository
import com.egecube.eduplatform._security_.accounts.UserAccountService
import com.egecube.eduplatform._security_.accounts.domain.UserAccount
import com.egecube.eduplatform._security_.accounts.domain.UserRole
import com.egecube.eduplatform._security_.accounts.dto.ChangeUserDataDto
import com.egecube.eduplatform._security_.accounts.dto.RegisterRequest
import com.egecube.eduplatform._security_.accounts.dto.UserAccountDto
import com.egecube.eduplatform._security_.events.UserAccountCreated
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.modulith.test.ApplicationModuleTest
import org.springframework.modulith.test.AssertablePublishedEvents


@SpringBootTest // forced to load all contexts for spring security
@ApplicationModuleTest
class SecurityIntegrationTests {

    @Autowired
    private lateinit var userService: UserAccountService
    @Autowired
    private lateinit var userAccountRepository: UserAccountRepository

    private val mockUserRequest = RegisterRequest(
        "Vadim", "Mikhu", "mail@mail.ru", null, "pass"
    )
    private val mockSubjectRequest = RegisterRequest(
        "Guest", "Guestov", "nomail@mail.ru", null, "pass"
    )
    private val suggestedEvent = UserAccountDto(
        1, "mail@mail.ru", null, "Vadim", "Mikhu", false, UserRole.USER
    )
    private val suggestedChanges = ChangeUserDataDto(
        "nonomail@mail.ru", "7700", "Guest_", "Guestov_", "pass_", UserRole.ADMIN
    )
    private val suggestedAfterAllChanges = UserAccount(
        1, "Guest_", "Guestov_", "nonomail@mail.ru", "7700", "pass_", false, UserRole.ADMIN
    )


    @Test fun simulateRegister() {
        val newUser = userService.registerNewUser(mockUserRequest)
        assertThat(newUser).isNotNull()
        assertThat(newUser!!).isEqualTo(1)

    }

    @Test fun checkRegisterEvent(events: AssertablePublishedEvents) {
        val newUser = userService.registerNewUser(mockUserRequest)
        assertThat(newUser).isNotNull()
        assertThat(newUser!!).isEqualTo(1)

        assertThat(events)
            .contains(UserAccountCreated::class.java)
            .matching(UserAccountCreated::data, suggestedEvent)
    }

    @Test fun checkRegisterQuery() {
        val newUser = userService.registerNewUser(mockUserRequest)
        assertThat(newUser).isNotNull()

        assertThat(
            userAccountRepository.findByEmail(mockUserRequest.userMail)!!.id
        ).isEqualTo(1)
    }

    @Test fun checkNoDuplicateRegistry() {
        val firstTry = userService.registerNewUser(mockUserRequest)
        val secondTry = userService.registerNewUser(mockUserRequest)

        assertThat(firstTry).isEqualTo(1)
        assertThat(secondTry).isNull()
    }

    @Test fun changeOnlyOpenAccountData() {
        val defUser = userService.registerNewUser(mockSubjectRequest)
        userService.changeBaseUserDataById(1, suggestedChanges)
        val changedUser = userAccountRepository.findById(1).get()

        assertThat(defUser!!).isEqualTo(1)
        assertThat(changedUser.email).isEqualTo(suggestedAfterAllChanges.email)
        assertThat(changedUser.phone).isEqualTo(suggestedAfterAllChanges.phone)
        assertThat(changedUser.role).isNotEqualTo(suggestedAfterAllChanges.role)

    }

    @Test fun changeSecureAccountData() {
        val defUser = userService.registerNewUser(mockSubjectRequest)
        userService.changeBaseUserDataById(1, suggestedChanges)
        userService.changeSecureUserDataById(1, suggestedChanges)
        val changedUser = userAccountRepository.findById(1).get()

        assertThat(defUser!!).isEqualTo(1)
        assertThat(changedUser.email).isEqualTo(suggestedAfterAllChanges.email)
        assertThat(changedUser.role).isEqualTo(suggestedAfterAllChanges.role)
    }
}