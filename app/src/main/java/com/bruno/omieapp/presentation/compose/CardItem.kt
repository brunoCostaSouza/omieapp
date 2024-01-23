package com.bruno.omieapp.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bruno.omieapp.R
import com.bruno.omieapp.domain.model.ItemModel
import com.bruno.omieapp.util.CallFunction
import com.bruno.omieapp.util.Helper
import com.bruno.omieapp.util.formatCurrency

@Composable
fun cardItem(
    item: ItemModel,
    isRemovable: Boolean = true,
    onItemRemove: CallFunction
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier
            .padding(5.dp)
            .height(85.dp)
    ) {
        val modifierText = Modifier.padding(start = 5.dp, top = 2.dp, end = 3.dp)
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Produto: ${item.description} ",
                    modifier = modifierText.weight(4f),
                    maxLines = 2,
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = TextUnit(15F, TextUnitType.Sp)
                )
                if (isRemovable) {
                    Button(
                        modifier = Modifier
                            .height(30.dp)
                            .weight(1f),
                        onClick = { onItemRemove() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_delete_24), null
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        Text(
                            "QTD: ${item.quantity}",
                            modifier = modifierText,
                            style = MaterialTheme.typography.titleSmall,
                            fontFamily = FontFamily.Serif,
                            fontSize = TextUnit(10F, TextUnitType.Sp)
                        )
                        Text(
                            "Valor unitário:",
                            modifier = modifierText,
                            style = MaterialTheme.typography.titleSmall,
                            fontFamily = FontFamily.Serif,
                            fontSize = TextUnit(10F, TextUnitType.Sp)
                        )
                        Text(
                            item.unityValue.formatCurrency(),
                            modifier = modifierText,
                            style = MaterialTheme.typography.titleSmall,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = TextUnit(15F, TextUnitType.Sp)
                        )
                    }
                }
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        Text(
                            "Valor Total:",
                            modifier = modifierText,
                            style = MaterialTheme.typography.titleSmall,
                            fontFamily = FontFamily.Serif,
                            fontSize = TextUnit(10F, TextUnitType.Sp)
                        )
                        Text(
                            item.amount.formatCurrency(),
                            modifier = modifierText,
                            style = MaterialTheme.typography.titleSmall,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = TextUnit(15F, TextUnitType.Sp)
                        )
                    }
                }
            }
        }
    }
}