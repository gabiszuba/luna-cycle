package com.example.lunacycle.feature.tracking

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.lunacycle.core.designsystem.component.LunaIcons
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.geometry.Offset
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
    TrackingOption("Cólicas", LunaIcons.Pulse),
    TrackingOption("Dor de cabeça", LunaIcons.Pulse),
    TrackingOption("Inchaço", LunaIcons.Waves),
    TrackingOption("Cansaço", LunaIcons.Moon),
    TrackingOption("Calores", LunaIcons.Sun),
    TrackingOption("Outro", LunaIcons.Add)
)
private val moodOptions = listOf(
    TrackingOption("Feliz", LunaIcons.Smile),
    TrackingOption("Sensível", LunaIcons.Heart),
    TrackingOption("Estressada", LunaIcons.Wind),
    TrackingOption("Exausta", LunaIcons.Cup)
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
                        Icon(LunaIcons.Add, null, Modifier.size(16.dp))
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
            Icon(LunaIcons.Close, "Fechar registro")
        }
        Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("REGISTRO RÁPIDO", style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onAction(TrackingAction.ChangeDay(-1)) }) {
                    Icon(LunaIcons.ChevronLeft, "Dia anterior", Modifier.size(20.dp), tint = MaterialTheme.colorScheme.primary)
                }
                val date = SimpleDateFormat("d MMM", Locale.forLanguageTag("pt-BR")).format(state.date).replace(".", "")
                Text(if (state.isToday) "Hoje, $date" else date, style = MaterialTheme.typography.titleLarge)
                IconButton(enabled = !state.isToday, onClick = { onAction(TrackingAction.ChangeDay(1)) }) {
                    Icon(LunaIcons.ChevronRight, "Próximo dia", Modifier.size(20.dp))
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
            Icon(LunaIcons.Drop, null, Modifier.size(if (drops == 1) 22.dp else 16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
            val gold = MaterialTheme.colorScheme.primary
            val trackColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
            val thumbColor = MaterialTheme.colorScheme.surface
            Slider(
                value = energyLevels.indexOf(energy).takeIf { it >= 0 }?.toFloat() ?: 2f,
                onValueChange = { onChange(energyLevels[it.roundToInt().coerceIn(0, 4)]) },
                valueRange = 0f..4f,
                steps = 3,
                modifier = Modifier.fillMaxWidth(),
                thumb = {
                    Box(
                        Modifier.size(24.dp)
                            .background(gold.copy(alpha = 0.12f), CircleShape)
                            .padding(3.dp)
                            .background(thumbColor, CircleShape)
                            .border(1.5.dp, gold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(Modifier.size(6.dp).background(gold, CircleShape))
                    }
                },
                track = { slider ->
                    Canvas(Modifier.fillMaxWidth().height(24.dp)) {
                        val centerY = size.height / 2
                        val progress = (slider.value / 4f).coerceIn(0f, 1f)
                        val rtl = layoutDirection == androidx.compose.ui.unit.LayoutDirection.Rtl
                        fun position(fraction: Float) = Offset(
                            size.width * if (rtl) 1f - fraction else fraction, centerY
                        )
                        drawLine(trackColor, position(0f), position(1f), 4.dp.toPx(), StrokeCap.Round)
                        if (progress > 0f) {
                            drawLine(gold, position(0f), position(progress), 4.dp.toPx(), StrokeCap.Round)
                        }
                        repeat(5) { step ->
                            val fraction = step / 4f
                            drawCircle(
                                if (fraction <= progress) gold else trackColor,
                                3.dp.toPx(), position(fraction)
                            )
                        }
                    }
                }
            )
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
