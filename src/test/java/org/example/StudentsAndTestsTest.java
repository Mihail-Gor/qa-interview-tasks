package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudentsAndTestsTest {

    @Test
    @DisplayName("Проверка выборки топ-5 студентов с наивысшими оценками через Stream API")
    void testTop5StudentsSorting() {
        List<StudentsAndTests.StudentDto> students = List.of(
                new StudentsAndTests.StudentDto("Иван", "Иванов", "4.5", "20"),
                new StudentsAndTests.StudentDto("Мария", "Петрова", "4.8", "21"),
                new StudentsAndTests.StudentDto("Петр", "Сидоров", "3.9", "22"),
                new StudentsAndTests.StudentDto("Анна", "Смирнова", "4.9", "20"),
                new StudentsAndTests.StudentDto("Дмитрий", "Козлов", "4.2", "23"),
                new StudentsAndTests.StudentDto("Елена", "Новикова", "4.7", "21"),
                new StudentsAndTests.StudentDto("Сергей", "Морозов", "3.5", "22")
        );

        List<StudentsAndTests.StudentDto> top5 = students.stream()
                .sorted(Comparator.comparingDouble((StudentsAndTests.StudentDto s) -> Double.parseDouble(s.avgGrade)).reversed())
                .limit(5)
                .toList();

        assertThat(top5).hasSize(5);
        assertThat(top5.get(0).avgGrade).isEqualTo("4.9");
        assertThat(top5.get(1).avgGrade).isEqualTo("4.8");
        assertThat(top5.get(2).avgGrade).isEqualTo("4.7");
        assertThat(top5.get(3).avgGrade).isEqualTo("4.5");
        assertThat(top5.get(4).avgGrade).isEqualTo("4.2");
    }

    @Test
    @DisplayName("Список менее 5 студентов должен возвращаться полностью без ошибок")
    void testListWithLessThan5Students() {
        List<StudentsAndTests.StudentDto> smallList = List.of(
                new StudentsAndTests.StudentDto("Иван", "Иванов", "4.5", "20"),
                new StudentsAndTests.StudentDto("Анна", "Смирнова", "4.9", "20")
        );

        List<StudentsAndTests.StudentDto> result = smallList.stream()
                .sorted(Comparator.comparingDouble((StudentsAndTests.StudentDto s) -> Double.parseDouble(s.avgGrade)).reversed())
                .limit(5)
                .toList();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).avgGrade).isEqualTo("4.9");
        assertThat(result.get(1).avgGrade).isEqualTo("4.5");
    }
}
