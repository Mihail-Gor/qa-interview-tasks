package org.example;

import java.util.*;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class StudentsAndTests {

    /*
    * Coding Task: Top 5 Students by Average Grade
        Описание задачи
        Вам необходимо написать программу на Java, которая обрабатывает список студентов и выводит топ-5 студентов с наивысшими средними оценками.
        Требования
        1. DTO класс Student
        Создайте класс StudentDto со следующими свойствами:

        FirstName (string) - имя студента
        LastName (string) - фамилия студента
        AvgGrade (string) - средняя оценка студента
        Age (string) - возраст студента
        2. Входные данные
        Дан список студентов List<StudentDto>. Вы можете использовать следующий тестовый набор данных:
        var students = new List<StudentDto>
        {
            new StudentDto { FirstName = "Иван", LastName = "Иванов", AvgGrade = "4.5", Age = "20" },
            new StudentDto { FirstName = "Мария", LastName = "Петрова", AvgGrade = "4.8", Age = "21" },
            new StudentDto { FirstName = "Петр", LastName = "Сидоров", AvgGrade = "3.9", Age = "22" },
            new StudentDto { FirstName = "Анна", LastName = "Смирнова", AvgGrade = "4.9", Age = "20" },
            new StudentDto { FirstName = "Дмитрий", LastName = "Козлов", AvgGrade = "4.2", Age = "23" },
            new StudentDto { FirstName = "Елена", LastName = "Новикова", AvgGrade = "4.7", Age = "21" },
            new StudentDto { FirstName = "Сергей", LastName = "Морозов", AvgGrade = "3.5", Age = "22" }
        };3. Задание
        Реализуйте метод или логику, которая:
        Получает список студентов
        Сортирует студентов по средней оценке (AvgGrade) в порядке убывания
        Выбирает первых 5 студентов из отсортированного списка

        Возвращает результат в виде List<StudentDto>
        4. Ожидаемый результат
    * */

    static class StudentDto {

        String firstName;
        String lastName;
        String avgGrade;
        String age;

        StudentDto (String firstName, String lastName, String avgGrade, String age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.avgGrade = avgGrade;
            this.age = age;
        }


    }

    public static List<StudentDto> students = List.of(
            new StudentDto("Иван", "Иванов", "4.5", "20" ),
            new StudentDto("Мария", "Петрова", "4.8", "21" ),
            new StudentDto("Петр", "Сидоров", "3.9", "22" ),
            new StudentDto("Анна", "Смирнова", "4.9", "20" ),
            new StudentDto("Дмитрий", "Козлов", "4.2", "23" ),
            new StudentDto("Елена", "Новикова", "4.7", "21" ),
            new StudentDto("Сергей", "Морозов", "3.5", "22" )


    );

    public static void main(String[] args) {
        List<StudentDto> students = new ArrayList<>();
        students.add(new StudentDto("Мария", "Петрова", "4.8", "21"));
        students.add(new StudentDto("Мария", "Петрова", "4.8", "21"));
        students.add(new StudentDto("Петр", "Сидоров", "3.9", "22"));
        students.add(new StudentDto("Анна", "Смирнова", "4.9", "20"));
        students.add(new StudentDto("Дмитрий", "Козлов", "4.2", "23"));
        students.add(new StudentDto("Елена", "Новикова", "4.7", "21"));
        students.add(new StudentDto("Сергей", "Морозов", "3.5", "22"));
        students.add(new StudentDto("Иван", "Иванов", "4.5", "20"));

        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (Float.valueOf(students.get(i).avgGrade) > Float.valueOf(students.get(j).avgGrade)) {
                    StudentDto temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(students.get(i).avgGrade);
        }
        System.out.println("");


    }




//    public static void main(String[] args) {
//        System.out.println(students.stream().sorted(Comparator.comparing(StudentDto::getAvgGrade).reversed()).limit(5).toList());
//
//    }

/*
* базовый смоук тест
* проверка граничных значений для поля avgGrade, и промежуточных, с условием, что переменная double пример(1.0, 2.5, 5.0, 1, 2, 5)
* проверка валидаций на некорректные значения выше граничных + те символы, которые не поддерживаются + больше заданного интервала double в т.ч. негативные числа
* проверка остальных полей на корректные и некорректные значения и на бизнес валидации
* */




}