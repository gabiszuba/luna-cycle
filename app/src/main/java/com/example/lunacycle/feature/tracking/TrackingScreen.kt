package com.example.lunacycle.feature.tracking

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunacycle.core.designsystem.theme.*
import com.example.lunacycle.core.designsystem.component.AppScreenHeader
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

private data class TrackingOption(val label: String, val icon: ImageVector)
private val symptomOptions = listOf(
    TrackingOption("Cólicas", Icons.Rounded.MonitorHeart),
    TrackingOption("Dor de cabeça", Icons.Rounded.Bolt),
    TrackingOption("Inchaço", Icons.Rounded.Waves),
    TrackingOption("Cansaço", Icons.Rounded.DarkMode),
    TrackingOption("Calores", Icons.Rounded.DeviceThermostat),
    TrackingOption("Outro", Icons.Rounded.Add)
)
private val moodOptions = listOf(
    TrackingOption("Feliz", Icons.Rounded.SentimentSatisfiedAlt),
    TrackingOption("Sensível", Icons.Rounded.FavoriteBorder),
    TrackingOption("Estressada", Icons.Rounded.Air),
    TrackingOption("Exausta", Icons.Rounded.Coffee)
)
private val energyLevels = listOf("Esgotada", "Baixa", "Média", "Alta", "Radiante")

@Composable
fun TrackingScreen(state: TrackingUiState, onAction: (TrackingAction) -> Unit, onBack: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).imePadding()) {
        Box(Modifier.padding(horizontal = AppSpacing.lg, vertical = AppSpacing.md)) {
            AppScreenHeader("Registrar", onBack)
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = AppSpacing.lg, vertical = AppSpacing.xl),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.hero)
        ) {
            item {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("Fluxo", Modifier.weight(1f), style = MaterialTheme.typography.titleLarge)
                    Surface(shape = CircleShape, color = MaterialTheme.colorScheme.background,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)) {
                        Text("Obrigatório", Modifier.padding(horizontal = AppSpacing.xs, vertical = AppSpacing.hairline),
                            style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
                Spacer(Modifier.height(AppSpacing.lg))
                Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min).selectableGroup(), horizontalArrangement = Arrangement.spacedBy(AppSpacing.sm)) {
                    listOf("Leve", "Moderado", "Intenso").forEachIndexed { index, flow ->
                        TrackingOptionCard(
                            label = if (flow == "Moderado") "Médio" else flow,
                            selected = state.entry.flow == flow,
                            onClick = { onAction(TrackingAction.SelectFlow(flow)) },
                            modifier = Modifier.weight(1f),
                            role = Role.RadioButton
                        ) {
                            FlowIntensityIcon(index + 1)
                        }
                    }
                }
            }
            item {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("Sintomas", Modifier.weight(1f), style = MaterialTheme.typography.titleLarge)
                    Text("Mais usados", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                }
                Spacer(Modifier.height(AppSpacing.lg))
                Column(verticalArrangement = Arrangement.spacedBy(AppSpacing.sm)) {
                    symptomOptions.chunked(3).forEach { row ->
                        Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min), horizontalArrangement = Arrangement.spacedBy(AppSpacing.sm)) {
                            row.forEach { option ->
                                IconOption(option, option.label in state.entry.symptoms,
                                    { onAction(TrackingAction.ToggleSymptom(option.label)) }, Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
            item {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("Humor", Modifier.weight(1f), style = MaterialTheme.typography.titleLarge)
                    Text(if (state.entry.mood == null) "0 selecionados" else "1 selecionado",
                        style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Spacer(Modifier.height(AppSpacing.lg))
                Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min).selectableGroup(), horizontalArrangement = Arrangement.spacedBy(AppSpacing.sm)) {
                    moodOptions.forEach { option ->
                        IconOption(option, state.entry.mood == option.label,
                            { onAction(TrackingAction.SelectMood(option.label)) }, Modifier.weight(1f), Role.RadioButton)
                    }
                }
            }
            item { EnergyCard(state.entry.energy) { onAction(TrackingAction.SelectEnergy(it)) } }
            item {
                var showNotes by rememberSaveable(state.date) { mutableStateOf(false) }
                if (showNotes || state.entry.notes.isNotEmpty()) {
                    OutlinedTextField(state.entry.notes, { onAction(TrackingAction.EditNotes(it)) }, Modifier.fillMaxWidth(),
                        label = { Text("Anotação opcional") }, minLines = 2, maxLines = 4, shape = MaterialTheme.shapes.medium)
                } else {
                    TextButton(onClick = { showNotes = true }, contentPadding = PaddingValues(0.dp)) {
                        Icon(Icons.Rounded.Add, null, Modifier.size(16.dp))
                        Spacer(Modifier.width(AppSpacing.xs))
                        Text("Adicionar anotação", style = MaterialTheme.typography.labelMedium)
                    }
                }
                Spacer(Modifier.height(AppSpacing.md))
                Button(onClick = { onAction(TrackingAction.Save) }, enabled = state.entry.flow != null,
                    modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp), shape = RoundedCornerShape(16.dp)) {
                    Text("Salvar Registro", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(Modifier.height(AppSpacing.md))
                Text("Registro rápido para acompanhar seu dia.\nDisponível durante esta sessão.",
                    Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun QuickEntryHeader(state: TrackingUiState, onAction: (TrackingAction) -> Unit, onBack: () -> Unit) {
    Box(Modifier.fillMaxWidth().padding(horizontal = AppSpacing.md, vertical = AppSpacing.sm)) {
        IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(Icons.Rounded.Close, "Fechar registro")
        }
        Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("REGISTRO RÁPIDO", style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onAction(TrackingAction.ChangeDay(-1)) }) {
                    Icon(Icons.Rounded.ChevronLeft, "Dia anterior", Modifier.size(20.dp), tint = MaterialTheme.colorScheme.primary)
                }
                val date = SimpleDateFormat("d MMM", Locale.forLanguageTag("pt-BR")).format(state.date).replace(".", "")
                Text(if (state.isToday) "Hoje, $date" else date, style = MaterialTheme.typography.titleLarge)
                IconButton(enabled = !state.isToday, onClick = { onAction(TrackingAction.ChangeDay(1)) }) {
                    Icon(Icons.Rounded.ChevronRight, "Próximo dia", Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
private fun IconOption(option: TrackingOption, selected: Boolean, onClick: () -> Unit, modifier: Modifier, role: Role = Role.Checkbox) {
    TrackingOptionCard(option.label, selected, onClick, modifier, role) {
        Icon(option.icon, null, Modifier.size(26.dp))
    }
}

@Composable
private fun TrackingOptionCard(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    role: Role,
    icon: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxHeight().clip(RoundedCornerShape(16.dp))
            .selectable(selected, role = role, onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline)
    ) {
        Column(
            Modifier.padding(horizontal = AppSpacing.xxs, vertical = AppSpacing.sm),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppSpacing.xxs, Alignment.CenterVertically)
        ) {
            CompositionLocalProvider(LocalContentColor provides
                if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary) {
                Box(Modifier.height(26.dp), contentAlignment = Alignment.Center) { icon() }
            }
            Text(label, style = MaterialTheme.typography.labelMedium, textAlign = TextAlign.Center,
                maxLines = 2,
                color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
private fun FlowIntensityIcon(drops: Int) {
    Row(
        Modifier.height(26.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(drops) {
            Icon(Icons.Rounded.WaterDrop, null, Modifier.size(if (drops == 1) 22.dp else 16.dp))
        }
    }
}

@Composable
private fun EnergyCard(energy: String?, onChange: (String) -> Unit) {
    Surface(Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp), color = MaterialTheme.colorScheme.surface) {
        Column(Modifier.padding(AppSpacing.lg)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("Energia", Modifier.weight(1f), style = MaterialTheme.typography.titleLarge)
                Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer) {
                    Text(energy ?: "Como está?", Modifier.padding(horizontal = AppSpacing.xs, vertical = AppSpacing.xxs),
                        style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
            Spacer(Modifier.height(AppSpacing.xs))
            Text("Como está sua disposição?", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(AppSpacing.lg))
            Slider(value = energyLevels.indexOf(energy).takeIf { it >= 0 }?.toFloat() ?: 2f,
                onValueChange = { onChange(energyLevels[it.roundToInt()]) }, valueRange = 0f..4f, steps = 3,
                modifier = Modifier.fillMaxWidth())
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("ESGOTADA", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("ENERGIZADA", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
internal fun TrackingHeader(title: String, onBack: () -> Unit) = AppScreenHeader(title, onBack)

@Composable
internal fun SectionLabel(text: String) {
    Text(text, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

@Preview(showBackground = true, widthDp = 390, heightDp = 1100)
@Composable
private fun TrackingScreenPreview() {
    AppTheme(darkTheme = true) { Surface { TrackingScreen(TrackingUiState(entry = DailyEntry(flow = "Moderado", symptoms = setOf("Cólicas", "Dor de cabeça", "Cansaço"), energy = "Alta")), {}, {}) } }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun TrackingScreenLightPreview() {
    AppTheme(darkTheme = false) { Surface { TrackingScreen(TrackingUiState(), {}, {}) } }
}
