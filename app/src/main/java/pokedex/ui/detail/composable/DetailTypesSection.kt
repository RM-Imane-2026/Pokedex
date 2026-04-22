package pokedex.ui.detail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetailTypesSection(types: List<String>) {

    Text(
        text = "Tipos:",
        fontWeight = FontWeight.SemiBold,
        color = DetailColors.Primary
    )

    Spacer(modifier = Modifier.height(8.dp))

    Column (verticalArrangement = Arrangement.spacedBy(6.dp)) {

        types.forEach { type ->

            Row (verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(DetailColors.TypeDot)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = type,
                    color = DetailColors.SecondaryText
                )
            }
        }
    }
}
