package com.example.lunacycle.feature.calendar

import java.util.Calendar
import org.junit.Assert.*
import org.junit.Test

class CalendarUiStateTest {
    @Test
    fun referenceMonthStartsOnTuesdayAndHas31Days() {
        val state = CalendarUiState()
        assertEquals(2, state.firstDayOffset)
        assertEquals(31, state.daysInMonth)
    }

    @Test
    fun monthNavigationCrossesYearsAndClearsSelection() {
        val december = CalendarUiState(year = 2024, month = Calendar.DECEMBER)
        val january = december.moveMonth(1)
        assertEquals(2025, january.year)
        assertEquals(Calendar.JANUARY, january.month)
        assertNull(january.selectedDay)
        assertEquals(december.copy(selectedDay = null), january.moveMonth(-1))
    }

    @Test
    fun februaryHandlesLeapYearsAndCenturyRules() {
        assertEquals(29, CalendarUiState(year = 2024, month = Calendar.FEBRUARY).daysInMonth)
        assertEquals(28, CalendarUiState(year = 2025, month = Calendar.FEBRUARY).daysInMonth)
        assertEquals(28, CalendarUiState(year = 2100, month = Calendar.FEBRUARY).daysInMonth)
    }

    @Test
    fun samplePhasesAreLimitedToRecordedDates() {
        val state = CalendarUiState()
        assertNull(state.phaseFor(10))
        assertEquals(CalendarPhase.Menstruation, state.phaseFor(11))
        assertEquals(CalendarPhase.Menstruation, state.phaseFor(15))
        assertEquals(CalendarPhase.Follicular, state.phaseFor(16))
        assertEquals(CalendarPhase.Follicular, state.phaseFor(24))
        assertEquals(CalendarPhase.Ovulation, state.phaseFor(25))
        assertEquals(CalendarPhase.Luteal, state.phaseFor(26))
        assertNull(state.moveMonth(1).phaseFor(11))
        assertNull(state.copy(year = 2025).phaseFor(11))
    }
}
