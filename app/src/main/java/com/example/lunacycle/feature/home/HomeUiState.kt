package com.example.lunacycle.feature.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeUiState(
    val userName: String,
    val cycleDay: Int,
    val phaseName: String,
    val phaseStatus: String,
    val daysUntilPeriod: Int,
    val nextPeriodDate: String,
    val fertileLevel: String,
    val fertileDates: String,
    val insights: List<HomeInsight>,
    val dailyTips: List<HomeInsight>
) {
    companion object {
        fun fake() = HomeUiState(
            userName = "Gabrielle",
            cycleDay = 7,
            phaseName = "Fase de Ovulação",
            phaseStatus = "Alta Fertilidade",
            daysUntilPeriod = 8,
            nextPeriodDate = "21 Ago",
            fertileLevel = "Alta",
            fertileDates = "19 - 24 Ago",
            insights = listOf(
                HomeInsight("Aumento do desejo sexual", "Níveis elevados de estrogênio podem aumentar a libido hoje.", InsightKind.Desire),
                HomeInsight("Muco cervical elástico", "Sinal clássico de fertilidade alta. Consistência clara e úmida.", InsightKind.CervicalMucus)
            ),
            dailyTips = listOf(
                HomeInsight("Dicas Diárias", "Beber bastante água hoje ajudará a reduzir o inchaço comum nesta fase.", InsightKind.Tip),
                HomeInsight("Monitoramento de Humor", "Mudanças sutis no humor são normais agora. Tente praticar meditação por 10 minutos.", InsightKind.Mood)
            )
        )
    }
}

@Immutable
data class HomeInsight(val title: String, val description: String, val kind: InsightKind)

enum class InsightKind { Desire, CervicalMucus, Tip, Mood }
