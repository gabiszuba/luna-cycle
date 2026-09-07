package com.example.lunacycle.feature.calendar

import java.util.Calendar
import java.util.GregorianCalendar

data class CalendarUiState(
    val year: Int = 2024,
    val month: Int = Calendar.OCTOBER,
    val selectedDay: Int? = 13
) {
    private val firstDay get() = GregorianCalendar(year, month, 1)
    val daysInMonth get() = firstDay.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOffset get() = firstDay.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY
    val monthName get() = monthNames[month]

    fun moveMonth(amount: Int): CalendarUiState {
        val date = firstDay.apply { add(Calendar.MONTH, amount) }
        return copy(year = date.get(Calendar.YEAR), month = date.get(Calendar.MONTH), selectedDay = null)
    }

    // Demonstration data belongs only to the reference month.
    fun phaseFor(day: Int): CalendarPhase? = if (year == 2024 && month == Calendar.OCTOBER) {
        when (day) {
            in 11..15 -> CalendarPhase.Menstruation
            in 16..24 -> CalendarPhase.Follicular
            25 -> CalendarPhase.Ovulation
            in 26..31 -> CalendarPhase.Luteal
            else -> null
        }
    } else null

    companion object {
        private val monthNames = listOf("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro")
    }
}

enum class CalendarPhase(val label: String) {
    Menstruation("Menstruação"), Follicular("Fase Folicular"), Ovulation("Ovulação"), Luteal("Fase Lútea")
}

sealed interface CalendarAction {
    data object PreviousMonth : CalendarAction
    data object NextMonth : CalendarAction
    data class SelectDay(val day: Int) : CalendarAction
}
