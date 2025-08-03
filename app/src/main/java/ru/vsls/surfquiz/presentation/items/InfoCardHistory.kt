package ru.vsls.surfquiz.presentation.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.vsls.surfquiz.R

@Composable
fun InfoCard(onBackToStart: () -> Unit) {
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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                stringResource(R.string.info),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 20.dp),
                textAlign = TextAlign.Center
            )
            Button(onClick = { onBackToStart() }, modifier = Modifier.padding(top = 24.dp)) {
                Text(stringResource(R.string.start_new_quiz))
            }
        }
    }
}