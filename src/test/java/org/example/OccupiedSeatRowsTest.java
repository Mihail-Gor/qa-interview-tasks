package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OccupiedSeatRowsTest {

    @Test
    @DisplayName("Базовый пример: самый длинный отрезок свободных мест внутри ряда")
    void testStandardMiddleSequence() {
        List<Integer> row = List.of(1, 0, 0, 0, 1);
        // Между 0 и 4 индексом 3 свободных места: 1, 2, 3 -> середина индекс 2
        int seat = OccupiedSeatRows.chooseSeat(row);
        assertThat(seat).isEqualTo(2);
    }

    @Test
    @DisplayName("Свободные места с самого начала ряда")
    void testFreeSeatsAtBeginning() {
        List<Integer> row = List.of(0, 0, 0, 1);
        // До первой 1 есть 3 свободных места (индексы 0, 1, 2). Самое дальнее место - 0 (дистанция 3)
        int seat = OccupiedSeatRows.chooseSeat(row);
        assertThat(seat).isEqualTo(0);
    }

    @Test
    @DisplayName("Свободные места в самом конце ряда")
    void testFreeSeatsAtEnd() {
        List<Integer> row = List.of(1, 0, 0, 0, 0);
        // После 1 на индексе 0 идут 4 свободных места. Самое дальнее место - 4 (дистанция 4)
        int seat = OccupiedSeatRows.chooseSeat(row);
        assertThat(seat).isEqualTo(4);
    }

    @Test
    @DisplayName("Сравнение альтернативного алгоритма maxDistToClosest")
    void testAlternativeAlgorithm() {
        int[] seats = new int[]{1, 0, 0, 0, 1, 0, 1};
        int bestIndex = OccupiedSeatRows.maxDistToClosest(seats);
        assertThat(bestIndex).isEqualTo(2);
    }
}
