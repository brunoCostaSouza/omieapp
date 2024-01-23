package com.bruno.omieapp.domain.usecase

import com.bruno.omieapp.domain.model.ItemModel

interface ValidateItemModelUseCase {
    operator fun invoke(itemModel: ItemModel): Pair<Boolean, String>
}