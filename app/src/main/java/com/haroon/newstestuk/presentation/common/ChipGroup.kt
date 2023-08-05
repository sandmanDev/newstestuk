package com.haroon.newstestuk.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ChipGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
        options.forEach { option ->
            val isSelected = selectedOption.lowercase() == option.lowercase()
            Chip(
                text = option,
                textColor = if (isSelected) Color.White else Color.Black,
                backgroundColor = if (isSelected) Color.Black else Color.LightGray,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        if (selectedOption == option) MaterialTheme.colors.primary
                        else MaterialTheme.colors.secondary
                    ),
                onChipClick = {
                    onOptionSelected(option)
                }
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}



@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color,
    backgroundColor: Color,
    onChipClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable(onClick = onChipClick)
            .background(backgroundColor),
        color = Color.Transparent,
        elevation = 1.dp,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.body2.copy(color = Color.White)
            )
        }
    }
}