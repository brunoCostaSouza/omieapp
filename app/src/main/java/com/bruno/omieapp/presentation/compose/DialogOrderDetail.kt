package com.bruno.omieapp.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bruno.omieapp.R
import com.bruno.omieapp.domain.model.OrderModel
import com.bruno.omieapp.util.formatCurrency
import com.bruno.omieapp.util.formatData

@Composable
fun DialogOrderDetail(
    openDialog: MutableState<Boolean>,
    orderModel: OrderModel
) {
    MaterialTheme {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            text = {
                Surface() {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(4.dp),
                                text = "${stringResource(id = R.string.text_title_number_order)}${orderModel.id}"
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "${stringResource(id = R.string.text_title_date)} ${orderModel.localDateTime.formatData()}",
                                style = MaterialTheme.typography.titleSmall,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = TextUnit(15F, TextUnitType.Sp)
                            )
                        }
                        Divider(modifier = Modifier.padding(4.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "${stringResource(id = R.string.text_title_client_name)} ${orderModel.client} ",
                                maxLines = 2,
                                style = MaterialTheme.typography.titleSmall,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = TextUnit(20F, TextUnitType.Sp)
                            )
                        }
                        Divider(modifier = Modifier.padding(4.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Text(
                                "${stringResource(id = R.string.text_title_amount)} ${orderModel.amount.formatCurrency()}",
                                style = MaterialTheme.typography.titleSmall,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = TextUnit(18F, TextUnitType.Sp)
                            )
                        }
                        Divider(modifier = Modifier.padding(4.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "${stringResource(id = R.string.text_title_qtd_items)} ${orderModel.items.size}",
                                style = MaterialTheme.typography.titleSmall,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = TextUnit(15F, TextUnitType.Sp)
                            )
                        }
                        Divider(modifier = Modifier.padding(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            LazyColumn {
                                itemsIndexed(orderModel.items) { index, item ->
                                    cardItem(item, isRemovable = false) {}
                                }
                            }
                        }
                        Divider(modifier = Modifier.padding(4.dp))
                    }
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            stringResource(id = R.string.text_button_close),
                            color = Color.White
                        )
                    }
                }
            }
        )
    }
}