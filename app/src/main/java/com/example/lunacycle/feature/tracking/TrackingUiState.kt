package com.example.lunacycle.feature.tracking

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class DailyEntry(
    val flow: String? = null,
    val symptoms: Set<String> = emptySet(),
    val mood: String? = null,
    val energy: String? = null,
    val notes: String = ""
) {
    val hasContent: Boolean get() = flow != null || symptoms.isNotEmpty() || mood != null || energy != null || notes.isNotBlank()
}

data class TrackingUiState(
    val date: Long = Calendar.getInstance().timeInMillis,
    val entry: DailyEntry = DailyEntry(),
    val savedEntry: DailyEntry? = null
) {
    val dateLabel: String get() = SimpleDateFormat("d 'de' MMMM", Locale.forLanguageTag("pt-BR")).format(date)
    val isToday: Boolean get() = dayKey(date) == dayKey(Calendar.getInstance().timeInMillis)
    val isSaved: Boolean get() = savedEntry != null && entry == savedEntry
}

internal fun dayKey(date: Long): String = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).format(date)

sealed interface TrackingAction {
    data class ChangeDay(val offset: Int) : TrackingAction
    data class SelectFlow(val value: String) : TrackingAction
    data class ToggleSymptom(val value: String) : TrackingAction
    data class SelectMood(val value: String) : TrackingAction
    data class SelectEnergy(val value: String) : TrackingAction
    data class EditNotes(val value: String) : TrackingAction
    data object Save : TrackingAction
}
