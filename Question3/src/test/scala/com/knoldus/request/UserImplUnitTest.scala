package com.knoldus.request
import com.knoldus.models.User
import com.knoldus.validator.UserValidator
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

class UserImplUnitTest extends AnyFlatSpec {

  val mockedUserValidator = mock[UserValidator]
  val rakhiUser: User = User("Rakhi","Pareek",21,"knoldus","rakhi.pareek@knoldus.com")

  "User" should "be created" in {
    val userImpl = new UserImpl(mockedUserValidator)

    when(mockedUserValidator.userIsValid(rakhiUser)) thenReturn(true)
    val result = userImpl.createUser(rakhiUser)
    assert(!result.isEmpty)
  }

  "User" should "not be created" in {
    val userImpl = new UserImpl(mockedUserValidator)

    when(mockedUserValidator.userIsValid(lokeshUser)) thenReturn(false)
    val result = userImpl.createUser(rakhiUser)
    assert(result.isEmpty)
  }

}
