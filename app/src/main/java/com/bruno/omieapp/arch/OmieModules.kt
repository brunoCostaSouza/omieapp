package com.bruno.omieapp.arch

import com.bruno.omieapp.data.local.OrderRepository
import com.bruno.omieapp.data.local.OrderRepositoryImpl
import com.bruno.omieapp.domain.usecase.GetLastNumberOrderUseCase
import com.bruno.omieapp.domain.usecase.GetLastNumberOrderUseCaseImpl
import com.bruno.omieapp.domain.usecase.GetOrdersUseCase
import com.bruno.omieapp.domain.usecase.GetAllOrdersUseCaseImpl
import com.bruno.omieapp.domain.usecase.GetOrderByIdUseCase
import com.bruno.omieapp.domain.usecase.GetOrderByIdUseCaseImpl
import com.bruno.omieapp.domain.usecase.SaveOrderUseCase
import com.bruno.omieapp.domain.usecase.SaveOrderUserCaseImpl
import com.bruno.omieapp.domain.usecase.ValidateItemModelUseCase
import com.bruno.omieapp.domain.usecase.ValidateItemModelUseCaseImpl
import com.bruno.omieapp.presentation.viewModel.MainViewModel
import com.bruno.omieapp.presentation.viewModel.OrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    /** Repository */
    single<OrderRepository> { OrderRepositoryImpl(DatabaseModule.provideRealm())}

    /** UseCase */
    single<SaveOrderUseCase> { SaveOrderUserCaseImpl() }
    single<GetOrdersUseCase> { GetAllOrdersUseCaseImpl() }
    single<GetLastNumberOrderUseCase> { GetLastNumberOrderUseCaseImpl() }
    single<ValidateItemModelUseCase> { ValidateItemModelUseCaseImpl() }
    single<GetOrderByIdUseCase> { GetOrderByIdUseCaseImpl() }

    /** ViewModel */
    viewModel { MainViewModel(get(), get()) }
    viewModel { OrderViewModel(get(), get(), get()) }
}