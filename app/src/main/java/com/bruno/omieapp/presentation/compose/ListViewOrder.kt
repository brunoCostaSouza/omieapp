package com.bruno.omieapp.presentation.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bruno.omieapp.domain.model.OrderModel
import com.bruno.omieapp.util.Helper

@Composable
fun ListViewOrders(
    orderModelList: List<OrderModel>? = null,
    onItemClick: (id: Int) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            if (orderModelList != null) {
                itemsIndexed(orderModelList) { _, item ->
                    cardOrder(orderModel = item){ onItemClick(it) }
                }
            }
        }
    }
}

@Composable
@Preview
fun preview() {
    ListViewOrders(orderModelList = Helper().getListOrdersMock()){}
}