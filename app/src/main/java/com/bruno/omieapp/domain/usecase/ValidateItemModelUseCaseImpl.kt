package com.bruno.omieapp.domain.usecase

import com.bruno.omieapp.domain.model.ItemModel

class ValidateItemModelUseCaseImpl : ValidateItemModelUseCase {
    override fun invoke(item: ItemModel): Pair<Boolean, String> {
        var message = ""
        var isValid = true
        if (item.description.isEmpty()) {
            message = "* Informe o nome do produto\n"
            isValid = false
        }
        if (item.quantity == 0) {
            message += "* Informe a quantidade\n"
            isValid = false
        }
        if (item.unityValue == 0.0) {
            message += "* O valor unitário deve ser maior que zero"
            isValid = false
        }
        return Pair(isValid, message)
    }
}