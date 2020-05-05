package main.java.lesson2;

/**
 * Реализовать у класса Task1 переменные age (тип int),  name (тип String), ageGroup(тип String)
 * реализовать методы геттеры и сеттеры для каждой переменной.
 * Должны быть выполненны следующие условия:
 *  - не должно быть возможности напрямую устанваливать или читать значение age/name
 *  - нельзя установить значение age меньше нуля
 *  - максимальныое значение для возраста - 100
 *  - значение name не может быть короче чем 3 символа и длиннее чем 50 символов
 *  - name не может состоять из одних только пробелов
 *  - не зависимо от того ввел пользователь имя с большой или с маленькой буквы,
 *      оно должно быть записано в переменную name с большой буквы
 *  - ageGroup должен устанавливаться автоматически при установке возраста
 *      - child если age до 15 лет
 *      - student  - если age от 15 до 25 лет
 *      - worker - если age от 26 до 65 лет
 *      - pensioner - если age старше 66 лет
 *  - ageGroup можно только прочитать с помощью геттера, сеттер должен быть приватным и недоступным для других классов
 */

public class Task1 {
    private int age;
    private String name;
    String ageGroup;

    public void setAge (int age) {
        if (age > 0 && age < 15) {
            this.age = age;
            ageGroup = "child";
        } else if (age >= 15 && age <= 25) {
            this.age = age;
            ageGroup = "student";
        } else if (age >= 26 && age <= 65) {
            this.age = age;
            ageGroup = "worker";
        } else if (age > 65 && age <= 100) {
            this.age = age;
            ageGroup = "pensioner";
        } else {
            System.out.println("Please enter the age between 1 and 100");
        }
    }

    public int getAge() {
        return this.age;
    }

    public void setName (String name) {
        if (name.length()>=3 && name.length()<=50 && !name.trim().equals("")) {
            this.name = name.substring(0,1).toUpperCase() + name.substring(1);
        }
        else {
            System.out.println("The name is incorrect. Enter more than 3 and less than 50 characters" );
        }
    }
    public String getName() {
        return this.name;
    }

    private void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getAgeGroup() {
        return this.ageGroup;
    }

    public String toString () {

        return "User " + this.name + " is " + this.age + " years old. " + this.name + " is in " + this.ageGroup + " age group.";
    }

}
