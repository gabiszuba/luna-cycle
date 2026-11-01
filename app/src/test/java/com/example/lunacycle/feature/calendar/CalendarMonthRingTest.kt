package com.example.lunacycle.feature.calendar

import java.util.Calendar
import org.junit.Assert.*
import org.junit.Test

class CalendarMonthRingTest {
    @Test fun todayUsesDeviceDateInsteadOfCalendarSelection() {
        val date = java.util.GregorianCalendar(2026, Calendar.NOVEMBER, 1)
        val today = CalendarUiState.today(date)
        assertEquals(2026, today.year)
        assertEquals(Calendar.NOVEMBER, today.month)
        assertEquals(1, today.selectedDay)
        val browsedMonth = today.moveMonth(-1).copy(selectedDay = 23)
        assertNotEquals(browsedMonth, CalendarUiState.today(date))
        assertEquals(today, CalendarUiState.today(date))
    }

    @Test fun todayTracksMonthAndYearRollover() {
        val date = java.util.GregorianCalendar(2026, Calendar.DECEMBER, 31)
        assertEquals(31, CalendarUiState.today(date).selectedDay)
        date.add(Calendar.DAY_OF_MONTH, 1)
        val today = CalendarUiState.today(date)
        assertEquals(2027, today.year)
        assertEquals(Calendar.JANUARY, today.month)
        assertEquals(1, today.selectedDay)
    }

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