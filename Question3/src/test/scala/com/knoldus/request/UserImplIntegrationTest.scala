package com.knoldus.request

import com.knoldus.db.CompanyReadDto
import com.knoldus.models.User
import com.knoldus.validator.{EmailValidator, UserValidator}
import org.scalatest.flatspec.AnyFlatSpec

class UserImplIntegrationTest extends AnyFlatSpec {

  val companyName = new CompanyReadDto
  val validateEmail = new EmailValidator
  val userValidator = new UserValidator(companyName,validateEmail)

  val userImpl = new UserImpl(userValidator)

  "User" should "not be created as company does not exists in DB" in {
    val rakhiUser: User = User("Rakhi","pareek",24,"Google","rakhi.pareek@gmail.com")

    val result = userImpl.createUser(rahulUser)
    assert(result.isEmpty)
  }

  "User" should "not be created as email id is not valid" in {
    val shubhamUser: User = User("Shubham","saini",23,"Knoldus","shubham.saini@knoldus")

    val result = userImpl.createUser(shubhamUser)
    assert(result.isEmpty)
  }

  "User" should "not be created as company does not exists in DB and email id is not valid" in {
    val parashUser: User = User("paras","Kumar",22,"Microsoft","paras.kumar@microsoft")

    val result = userImpl.createUser(parashUser)
    assert(result.isEmpty)
  }

  "User" should "be created" in {
    val lokeshUser: User = User("Lokesh","Kumar",21,"Knoldus","lokesh.kumar@knoldus.com")

    val result = userImpl.createUser(lokeshUser)
    assert(!result.isEmpty)
  }

}
