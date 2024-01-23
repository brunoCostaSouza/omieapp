package com.bruno.omieapp.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bruno.omieapp.R
import com.bruno.omieapp.presentation.theme.OmieAppTheme
import com.bruno.omieapp.presentation.viewModel.MainEvent
import com.bruno.omieapp.presentation.viewModel.MainViewModel
import com.bruno.omieapp.util.formatCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mViewModel: MainViewModel
) {
    OmieAppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Text(stringResource(id = R.string.title_home))
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (mViewModel.state.value.listOrders.isEmpty()) {
                            EmptyOrders()
                        } else {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                text = "${stringResource(id = R.string.text_title_amount_sales)} ${mViewModel.state.value.salesAmount.formatCurrency()}",
                                style = MaterialTheme.typography.titleLarge,
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.Center,
                                fontSize = TextUnit(15F, TextUnitType.Sp)
                            )
                            ListViewOrders(orderModelList = mViewModel.state.value.listOrders) {
                                mViewModel.onItemListClick(it)
                            }
                        }
                    }
                    ExtendedFloatingActionButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp),
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = { mViewModel.onEvent(MainEvent.NewOrder) },
                        icon = { Icon(Icons.Filled.Add, "") },
                        text = { Text(text = stringResource(id = R.string.text_new_order)) },
                    )
                }
            }
        }
        if (mViewModel.showDialog.value) {
            DialogOrderDetail(
                mViewModel.showDialog,
                mViewModel.state.value.orderSelected!!
            )
        }
        simpleToast(mViewModel.toastMessage)
    }
}