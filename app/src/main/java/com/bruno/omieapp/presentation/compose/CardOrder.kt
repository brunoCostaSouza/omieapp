package com.bruno.omieapp.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bruno.omieapp.domain.model.OrderModel
import com.bruno.omieapp.util.formatCurrency
import com.bruno.omieapp.util.formatData

@Composable
fun cardOrder(
    orderModel: OrderModel,
    onItemClick: (id: Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier
            .clickable { orderModel.id?.let { onItemClick(it) } }
            .padding(5.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            val modifierText = Modifier.padding(9.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Cliente: ${orderModel.client} ",
                    modifier = modifierText.weight(4f),
                    maxLines = 2,
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = TextUnit(20F, TextUnitType.Sp)
                )
                Text(
                    "Total: ${orderModel.amount.formatCurrency()}",
                    modifier = modifierText,
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = TextUnit(18F, TextUnitType.Sp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "QTD de itens: ${orderModel.items.size}",
                    modifier = modifierText,
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = TextUnit(15F, TextUnitType.Sp)
                )
                Text(
                    "Data: ${orderModel.localDateTime.formatData()}",
                    modifier = modifierText,
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = TextUnit(15F, TextUnitType.Sp)
                )
            }
        }
    }
}