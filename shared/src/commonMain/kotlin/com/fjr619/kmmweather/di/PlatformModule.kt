package com.fjr619.kmmweather.di

import com.fjr619.kmmweather.data.local.datastore.DataStoreProvider
import org.koin.core.module.Module
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.qualifier.Qualifier
expect fun platformModule(): Module

expect inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): KoinDefinition<T>
