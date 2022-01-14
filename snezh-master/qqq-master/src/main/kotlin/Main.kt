import java.util.*

abstract class Employee(
    var firstName: String,
    var lastName: String,
    var exp: Int,
    var baseSalary: Double,
    var myManager: String
){

    open fun giveSalary(): Double{
if (exp > 2 && exp <= 5)
    baseSalary = baseSalary + 200
       else if (exp > 5)
           baseSalary = (baseSalary * 1.2) + 500
        return baseSalary
    }
}

class Developer(
    firstName: String,
    lastName: String,
    exp: Int,
    baseSalary: Double,
    myManager: String
) : Employee (firstName, lastName, exp, baseSalary, myManager){

}
class Designer (
    firstName: String,
    lastName: String,
    exp: Int,
    baseSalary: Double,
    myManager: String,
    var coef: Double
): Employee (firstName, lastName, exp, baseSalary, myManager){
    override fun giveSalary(): Double {
    return super.giveSalary() * coef
}
}

class Manager (
    firstName: String,
    lastName: String,
    exp: Int,
    baseSalary: Double,
    var team: MutableList<Employee> = mutableListOf()

): Employee (firstName, lastName, exp, baseSalary, myManager = "Null" ){
    fun teams(){team.forEach { Employee ->
        if (Employee.myManager == this.firstName)
            team.add(Employee)

    }}
    override fun giveSalary(): Double {
        val devCount = team.filterIsInstance<Developer>().count()
        val designerCount = team.filterIsInstance<Designer>().count()

        if (devCount > designerCount)
            return super.giveSalary() * 1.1 // baseSalary = baseSalary * 1.1
        if (devCount + designerCount > 5 && devCount + designerCount <= 10)
            return super.giveSalary() + 200 // baseSalary = baseSalary + 200
        if (devCount + designerCount > 10)
            return super.giveSalary() + 300
        return super.giveSalary()
    }}

class Department(
    var managers: MutableList<Manager> = mutableListOf()
){
    fun giveAllSalary(){
        managers.forEach { manager ->
        manager.giveSalary()
        manager.team.forEach { employee ->
        employee.giveSalary()
        }
        }
    }

}


fun main(){
    val dev1 = Developer("Oleg","Bedunov",1, 2000.0, "Inokentiy")
    val dev2 = Developer("Vasiliy","Miroshnichenko",16, 3500.0, "Sanya")
    val dev3 = Developer("Golova","Starchenko",4, 900.0, "Inokentiy")
    val dev4 = Developer("Sergey","Solodovnik",2, 1600.0, "Sanya")
    val designer1 = Designer("Woody", "Danchenko",8, 2200.0, "Inokentiy", 0.3,)
    val designer2 = Designer("Pink", "Brown",3, 1200.0,"Sanya", 0.8)
    val designer3 = Designer("Adam", "Anderson",6, 3000.0,"Inokentiy", 0.3)
    val manager1 = Manager("Inokentiy", "Donald",28, 700.0, mutableListOf())
    val manager2 = Manager("Sanya", "Kovbasa",1, 700.0, mutableListOf())


    println("Salary of " + dev1.firstName + " " + dev1.lastName + " = " + dev1.giveSalary())
    println("Salary of " + dev2.firstName + " " + dev2.lastName + " = " + dev2.giveSalary())
    println("Salary of " + dev3.firstName + " " + dev3.lastName + " = " + dev3.giveSalary())
    println("Salary of " + dev4.firstName + " " + dev4.lastName + " = " + dev4.giveSalary())

    println("Salary of " + designer1.firstName + " " + designer1.lastName + " = " + designer1.giveSalary())
    println("Salary of " + designer2.firstName + " " + designer2.lastName + " = " + designer2.giveSalary())
    println("Salary of " + designer3.firstName + " " + designer3.lastName + " = " + designer3.giveSalary())

    println("Salary of " + manager1.firstName + " " + manager1.lastName + " = " + manager1.giveSalary())
    println("Salary of " + manager2.firstName + " " + manager2.lastName + " = " + manager2.giveSalary())
}
