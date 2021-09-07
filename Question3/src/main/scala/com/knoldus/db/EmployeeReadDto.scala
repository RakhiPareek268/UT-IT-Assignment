package com.knoldus.db

import com.knoldus.models.Employee
import scala.collection.immutable.HashMap

class EmployeeReadDto {

  val rakhiEmployee: Employee = Employee("Rakhi","Pareek",21,15000,"Intern","knoldus","rakhi.pareek@knoldus.com")
  val parasEmployee: Employee = Employee("Paras","Jain",28,150000,"Software Engineer","Philips","paras.jain@philips.com")

  val employees: HashMap[String, Employee] = HashMap("Rakhi" -> rakhiEmployee, "Paras" -> parasEmployee)
  def getEmployeeByName(name: String): Option[Employee] = employees.get(name)

}
