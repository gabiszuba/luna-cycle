package com.example.lunacycle.core.designsystem.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.dp

/** A consistent set of airy, rounded line icons on a centered 24-unit grid. */
object LunaIcons {
    private fun line(name: String, data: String, mirrored: Boolean = false): ImageVector =
        ImageVector.Builder(
            name = name, defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f, autoMirror = mirrored
        ).apply {
            addPath(
                pathData = addPathNodes(data),
                fill = null, stroke = SolidColor(Color.Black),
                strokeLineWidth = 1.5f, strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            )
        }.build()

    val Home = line("Home", "M3.5,10.5 L12,3.5 L20.5,10.5 M5.5,9 L5.5,19 Q5.5,20.5 7,20.5 L10,20.5 L10,14 L14,14 L14,20.5 L17,20.5 Q18.5,20.5 18.5,19 L18.5,9")
    val Calendar = line("Calendar", "M7,3 L7,7 M17,3 L17,7 M4,9 L20,9 M6,5 L18,5 Q20,5 20,7 L20,19 Q20,21 18,21 L6,21 Q4,21 4,19 L4,7 Q4,5 6,5 M8,13 L9,13 M15,13 L16,13 M8,17 L9,17 M15,17 L16,17")
    val Journal = line("Journal", "M6,3.5 L17,3.5 Q19,3.5 19,5.5 L19,20.5 L6,20.5 Q4,20.5 4,18.5 L4,5.5 Q4,3.5 6,3.5 M7.5,3.5 L7.5,20.5 M11,8 L16,8 M11,12 L15,12 M11,16 L14,16")
    val AddCircle = line("AddCircle", "M21,12 A9,9 0,1 1,3,12 A9,9 0,1 1,21,12 M8,12 L16,12 M12,8 L12,16")
    val Add = line("Add", "M5,12 L19,12 M12,5 L12,19")
    val Drop = line("Drop", "M12,3 C10,6 5.5,10.5 5.5,14.5 A6.5,6.5 0,0 0,18.5,14.5 C18.5,10.5 14,6 12,3 Z M9,14.5 Q9,17 11.5,17.5")
    val Leaf = line("Leaf", "M5,19 C1,8 10,3 20,4 C21,14 16,22 5,19 Z M5,19 L15,9 M10,14 L10,9 M10,14 L15,14")
    val Heart = line("Heart", "M12,20 C9,17.5 3.5,14 3.5,9 A4.5,4.5 0,0 1,12,7 A4.5,4.5 0,0 1,20.5,9 C20.5,14 15,17.5 12,20 Z")
    val Sparkles = line("Sparkles", "M10,4 Q10,11 3,11 Q10,11 10,18 Q10,11 17,11 Q10,11 10,4 Z M18,3 L18,7 M16,5 L20,5 M19,16 L19,20 M17,18 L21,18")
    val Smile = line("Smile", "M21,12 A9,9 0,1 1,3,12 A9,9 0,1 1,21,12 M8,10 L8,10.5 M16,10 L16,10.5 M8,14 Q12,18 16,14")
    val Bell = line("Bell", "M9.5,20 Q12,22 14.5,20 M5,17.5 Q7,15 7,10 A5,5 0,0 1,17,10 Q17,15 19,17.5 Z M12,3 L12,5")
    val Trend = line("Trend", "M4,17 L9,12 L13,15 L20,7 M14,7 L20,7 L20,13", true)
    val ChevronRight = line("ChevronRight", "M9,6 L15,12 L9,18", true)
    val ChevronLeft = line("ChevronLeft", "M15,6 L9,12 L15,18", true)
    val Back = line("Back", "M20,12 L4,12 M10,6 L4,12 L10,18", true)
    val Clock = line("Clock", "M21,12 A9,9 0,1 1,3,12 A9,9 0,1 1,21,12 M12,7 L12,12 L15.5,14")
    val Edit = line("Edit", "M14.5,5.5 L18.5,9.5 M4,20 L5,15 L16,4 Q17.5,2.5 19,4 L20,5 Q21.5,6.5 20,8 L9,19 Z")
    val Pulse = line("Pulse", "M3,12 L7,12 L10,5 L14,19 L17,12 L21,12")
    val Moon = line("Moon", "M20.5,14 A9,9 0,1 1,10,3.5 A7,7 0,0 0,20.5,14 Z")
    val Waves = line("Waves", "M3,7 Q6,4 9,7 T15,7 T21,7 M3,12 Q6,9 9,12 T15,12 T21,12 M3,17 Q6,14 9,17 T15,17 T21,17")
    val Sun = line("Sun", "M16,12 A4,4 0,1 1,8,12 A4,4 0,1 1,16,12 M12,2.5 L12,4 M12,20 L12,21.5 M2.5,12 L4,12 M20,12 L21.5,12 M5.3,5.3 L6.4,6.4 M17.6,17.6 L18.7,18.7 M5.3,18.7 L6.4,17.6 M17.6,6.4 L18.7,5.3")
    val Wind = line("Wind", "M3,8 L14,8 Q18,8 18,5.5 Q18,2 14.5,3.5 M3,12 L18,12 Q22,12 21,8 M3,16 L12,16 Q16,16 16,19 Q16,22 13,21")
    val Cup = line("Cup", "M5,8 L16,8 L16,15 Q16,19 10.5,19 Q5,19 5,15 Z M16,9 L18,9 Q22,9 21,12 Q20.5,15 16,14 M7,3 L7,5 M11,2 L11,5 M4,22 L18,22")
    val Close = line("Close", "M6,6 L18,18 M18,6 L6,18")
}