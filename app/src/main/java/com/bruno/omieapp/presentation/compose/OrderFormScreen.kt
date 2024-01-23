package com.bruno.omieapp.presentation.compose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bruno.omieapp.R
import com.bruno.omieapp.domain.model.ItemModel
import com.bruno.omieapp.presentation.theme.OmieAppTheme
import com.bruno.omieapp.presentation.viewModel.OrderEvent
import com.bruno.omieapp.presentation.viewModel.OrderViewModel
import com.bruno.omieapp.util.CurrencyTransformation
import com.bruno.omieapp.util.formatCurrency
import com.bruno.omieapp.util.formatToDouble
import kotlinx.coroutines.flow.SharedFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderForm(
    navHostController: NavHostController,
    mViewModel: OrderViewModel = koinViewModel()
) {
    OmieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "${stringResource(id = R.string.text_title_number_order)}${mViewModel.state.value.lastNumber}",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily.SansSerif
                )
                Divider(modifier = Modifier.padding(7.dp))
                formClient(mViewModel)
                Divider(modifier = Modifier.padding(7.dp))
                formProduct(mViewModel)
                Divider(modifier = Modifier.padding(7.dp))
                Row(
                    modifier = Modifier
                        .weight(4f)
                        .fillMaxSize(),
                ) {
                    LazyColumn {
                        itemsIndexed(mViewModel.listItemsState) { index, item ->
                            cardItem(item) { mViewModel.removeAt(index) }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        modifier = Modifier.padding(5.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                        onClick = { navHostController.navigateUp() }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_cancel_24), null
                        )
                        Text(
                            text = stringResource(id = R.string.text_button_cancel),
                            Modifier.padding(start = 10.dp),
                            color = Color.White
                        )
                    }
                    Button(
                        modifier = Modifier.padding(5.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary),
                        onClick = { mViewModel.onEvent(OrderEvent.Save(mViewModel.state.value.order)) }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_save_24), null
                        )
                        Text(
                            text = stringResource(id = R.string.text_button_save),
                            Modifier.padding(start = 10.dp),
                            color = Color.White
                        )
                    }
                }
                simpleToast(mViewModel.toastMessage)
            }
        }
    }
}

@Composable
fun formClient(viewModel: OrderViewModel) {
    val nameClientState = remember { mutableStateOf("") }
    var isErrorClientName by rememberSaveable { mutableStateOf(false) }
    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = stringResource(id = R.string.text_title_client),
        style = MaterialTheme.typography.titleLarge,
        fontFamily = FontFamily.SansSerif
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = nameClientState.value,
        onValueChange = {
            nameClientState.value = it
            viewModel.state.value.order.client = it
            isErrorClientName = it.isEmpty()
        },
        isError = isErrorClientName,
        placeholder = { Text(text = stringResource(id = R.string.text_client_name)) },
    )
}

@Composable
fun formProduct(
    viewModel: OrderViewModel
) {
    var productName by rememberSaveable { mutableStateOf("") }
    var productQtd by rememberSaveable { mutableStateOf("1") }
    var productValue by rememberSaveable { mutableStateOf("") }
    var productValueDouble = rememberSaveable { mutableDoubleStateOf(0.0) }

    var isErrorProductName by rememberSaveable { mutableStateOf(false) }
    var isErrorProductQtd by rememberSaveable { mutableStateOf(false) }
    var isErrorProductValue by rememberSaveable { mutableStateOf(false) }

    Column {
        Text(
            modifier = Modifier.padding(10.dp),
            text = stringResource(id = R.string.text_title_product),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.SansSerif
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = productName,
            isError = isErrorProductName,
            onValueChange = {
                productName = it
                isErrorProductName = productName.isEmpty()
            },
            placeholder = { Text(text = stringResource(id = R.string.text_product_name)) },
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number
                ),
                value = productQtd,
                onValueChange = {
                    productQtd = it
                    isErrorProductQtd = productQtd.isEmpty() || productQtd.toInt() == 0
                },
                isError = isErrorProductQtd,
                placeholder = { Text(text = stringResource(id = R.string.text_product_qtd)) },
            )
            val myTransformation = CurrencyTransformation()
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Decimal
                ),
                visualTransformation = myTransformation,
                value = productValue,
                onValueChange = {
                    productValue = if (it.startsWith("0")) "" else it
                    productValueDouble.doubleValue =
                        myTransformation.filter(AnnotatedString(it)).text.text.formatToDouble()
                    isErrorProductValue = productValueDouble.doubleValue == 0.0
                },
                isError = isErrorProductValue,
                placeholder = { Text(text = stringResource(id = R.string.text_product_value)) }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    val item = ItemModel(
                        description = productName,
                        quantity = if (productQtd.isEmpty()) 0 else productQtd.toInt(),
                        unityValue = productValueDouble.doubleValue
                    )
                    viewModel.addItem(item)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_add_shopping_cart_24),
                    contentDescription = "icon"
                )
                Text(
                    text = stringResource(id = R.string.text_button_add_item),
                    Modifier.padding(start = 10.dp),
                    color = Color.White
                )
            }
        }
        if (!viewModel.listItemsState.isEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "${stringResource(id = R.string.text_title_qtd_items_v2)} ${viewModel.listItemsState.size}",
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "${stringResource(id = R.string.text_title_order_value)} ${viewModel.state.value.order.amount.formatCurrency()}",
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}