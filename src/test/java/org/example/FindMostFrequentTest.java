package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FindMostFrequentTest {

    @Test
    @DisplayName("Базовый пример из задания собеседования")
    void testStandardInterviewExample() {
        String input = "The world is full of obvious things which nobody by any chance ever observes.";
        char result = FindMostFrequent.findMostFrequent(input);
        assertThat(result).isEqualTo('o');
    }

    @ParameterizedTest
    @CsvSource({
            "'aba', 'a'",
            "'abacaba', 'a'",
            "'a b c a', 'a'",
            "'A b C a', 'a'",
            "'asfafsffasffwafaaags', 'a'"
    })
    @DisplayName("Позитивные проверки нахождения самого частого символа")
    void testFrequentCharacters(String input, char expected) {
        assertThat(FindMostFrequent.findMostFrequent(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("При равенстве частот должен возвращаться символ ближе к началу строки")
    void testTieBreakerFirstOccurrenceWins() {
        // 'a' и 'b' встречаются по 2 раза, но 'a' идет раньше
        assertThat(FindMostFrequent.findMostFrequent("aabb")).isEqualTo('a');
        // 'b' и 'a' встречаются по 2 раза, но 'b' идет раньше
        assertThat(FindMostFrequent.findMostFrequent("bbaa")).isEqualTo('b');
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t\n"})
    @DisplayName("Исключение IllegalArgumentException на null, пустые или пробельные строки")
    void testNullOrEmptyInputThrowsException(String input) {
        assertThatThrownBy(() -> FindMostFrequent.findMostFrequent(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
