package com.bruno.omieapp.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetLastNumberOrderUseCase {
   suspend fun getLastNumberOrder(): Flow<String>
}