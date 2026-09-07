package com.example.lunacycle.feature.tracking

import androidx.lifecycle.ViewModel
import java.util.Calendar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TrackingViewModel : ViewModel() {
    private val mutableState = MutableStateFlow(TrackingUiState())
    val uiState = mutableState.asStateFlow()
    // UI prototype: drafts and saved entries are retained during this app session.
    private val drafts = mutableMapOf<String, DailyEntry>()
    private val entries = mutableMapOf<String, DailyEntry>()

    fun onAction(action: TrackingAction) {
        val current = mutableState.value
        val entry = current.entry
        mutableState.value = when (action) {
            is TrackingAction.ChangeDay -> {
                if (action.offset > 0 && current.isToday) return
                drafts[dayKey(current.date)] = entry
                val date = Calendar.getInstance().apply {
                    timeInMillis = current.date
                    add(Calendar.DAY_OF_MONTH, action.offset)
                }.timeInMillis
                TrackingUiState(date, drafts[dayKey(date)] ?: entries[dayKey(date)] ?: DailyEntry(), entries[dayKey(date)])
            }
            is TrackingAction.SelectFlow -> current.copy(entry = entry.copy(flow = action.value))
            is TrackingAction.ToggleSymptom -> current.copy(entry = entry.copy(
                symptoms = if (action.value in entry.symptoms) entry.symptoms - action.value else entry.symptoms + action.value
            ))
            is TrackingAction.SelectMood -> current.copy(entry = entry.copy(mood = action.value.takeUnless { it == entry.mood }))
            is TrackingAction.SelectEnergy -> current.copy(entry = entry.copy(energy = action.value))
            is TrackingAction.EditNotes -> current.copy(entry = entry.copy(notes = action.value))
            TrackingAction.Save -> {
                if (entry.flow == null) return
                entries[dayKey(current.date)] = entry
                current.copy(savedEntry = entry)
            }
        }
    }
}
