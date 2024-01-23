package com.bruno.omieapp.presentation.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bruno.omieapp.domain.model.ItemModel
import com.bruno.omieapp.util.Helper

@Composable
fun ListViewItems(
    listItems: List<ItemModel> = listOf()
) {

}

@Composable
@Preview
fun previewListItem() {
    ListViewItems(Helper().getListItemsMock())
}