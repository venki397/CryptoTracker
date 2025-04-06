package com.venki.cryptotracker.presentation.coin_detail.components


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

data class ChartStyle(
    val chartLineColor : Color,
    val unselectedColor : Color,
    val selectedColor : Color,
    val axisLinesThicknessPx : Float,
    val labelFontSize : TextUnit,
    val minYLabelSpacing : Dp,
    val verticalPadding : Dp,
    val horizontalPadding : Dp,
    val xAxisLabelSpacing : Dp,
    val helperLinesThicknessPx : Float

)
