package ru.vsls.surfquiz.presentation.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.surfquiz.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizStartBlock(
    onStart: (String) -> Unit
) {
    val levels = listOf("Easy", "Medium", "Hard")
    var expanded by remember { mutableStateOf(false) }
    var selectedLevel by remember { mutableStateOf(levels.first()) }

    Card(
        modifier = Modifier.padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.start_dailyquiz),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(8.dp))

            // Выпадающий список выбора сложности
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = selectedLevel,
                    onValueChange = {},
                    label = { Text("Сложность") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    levels.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedLevel = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = { onStart(selectedLevel) }) {
                Text(text = stringResource(R.string.start_game))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizStartBlockPreview() {
    QuizStartBlock(onStart = {})
}