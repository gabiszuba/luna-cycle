package com.example.lunacycle.feature.calendar

import java.util.Calendar
import org.junit.Assert.*
import org.junit.Test

class CalendarMonthRingTest {
    @Test fun everyMonthFillsExactlyOneCircle() {
        listOf(
            CalendarUiState(2025, Calendar.FEBRUARY, 1) to 28,
            CalendarUiState(2024, Calendar.FEBRUARY, 1) to 29,
            CalendarUiState(2024, Calendar.APRIL, 1) to 30,
            CalendarUiState(2024, Calendar.OCTOBER, 1) to 31
        ).forEach { (state, days) ->
            assertEquals(days, state.daysInMonth)
            assertEquals(360f, state.daySweepAngle * days, 0.001f)
            assertEquals(-90f + 180f / days, state.selectedDayAngle!!, 0.001f)
            assertEquals(270f - 180f / days, state.copy(selectedDay = days).selectedDayAngle!!, 0.001f)
        }
    }

    @Test fun middleDayOfOddMonthIsDirectlyOppositeStart() {
        val state = CalendarUiState(2024, Calendar.OCTOBER, 16)
        assertEquals(90f, state.selectedDayAngle!!, 0.001f)
    }

    @Test fun adjacentDaysHaveEqualAngularSpacingRegardlessOfPhase() {
        val state = CalendarUiState()
        (1 until state.daysInMonth).forEach { day ->
            val first = state.copy(selectedDay = day).selectedDayAngle!!
            val second = state.copy(selectedDay = day + 1).selectedDayAngle!!
            assertEquals(state.daySweepAngle, second - first, 0.001f)
        }
    }

    @Test fun unselectedMonthHasNoMarker() {
        assertNull(CalendarUiState().moveMonth(1).selectedDayAngle)
        assertNull(CalendarUiState(selectedDay = 32).selectedDayAngle)
    }
}