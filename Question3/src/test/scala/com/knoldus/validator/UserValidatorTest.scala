package com.knoldus.validator

import com.knoldus.db.CompanyReadDto
import com.knoldus.models.{Company, User}
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

class UserValidatorTest extends AnyFlatSpec {

  val rakhiUser: User = User("Rakhi","Kumar",22,"Google","rakhi.pareek@knoldus.in")
  val knoldusCompany: Company = Company("Knoldus", "knoldus@gmail.com", "Noida")

  val mockedCompanyReadDto = mock[CompanyReadDto]
  val mockedEmailvalidator = mock[EmailValidator]

  val userValidator = new UserValidator(mockedCompanyReadDto,mockedEmailvalidator)

  "User" should "be valid" in {

    when(mockedCompanyReadDto.getCompanyByName(rakhiUser.companyName)) thenReturn(Option(knoldusCompany))
    when(mockedEmailvalidator.emailIdIsValid(rakhiUser.emailId)) thenReturn(true)

    val result = userValidator.userIsValid(rakhiUser)
    assert(result)
  }

  "User" should "be invalid because his email is not valid" in {

    when(mockedCompanyReadDto.getCompanyByName(rakhiUser.companyName)) thenReturn(Option(knoldusCompany))
    when(mockedEmailvalidator.emailIdIsValid(rakhiUser.emailId)) thenReturn(false)

    val result = userValidator.userIsValid(rakhiUser)
    assert(!result)
  }

  "User" should "be invalid because his company does not exists in DB" in {

    when(mockedCompanyReadDto.getCompanyByName(rakhiUser.companyName)) thenReturn(None)
    when(mockedEmailvalidator.emailIdIsValid(rakhiUser.emailId)) thenReturn(true)

    val result = userValidator.userIsValid(rakhiUser)
    assert(!result)
  }

  "User" should "be invalid because his email id is not valid and his company does not exists in DB" in {

    when(mockedCompanyReadDto.getCompanyByName(rakhiUser.companyName)) thenReturn(None)
    when(mockedEmailvalidator.emailIdIsValid(rakhiUser.emailId)) thenReturn(false)

    val result = userValidator.userIsValid(rakhiUser)
    assert(!result)
  }

}
