package com.monzo.androidtest.individual

import android.content.Context
import com.monzo.androidtest.api.GuardianService
import com.monzo.androidtest.common.injection.NetworkModule
import com.monzo.androidtest.individual.data.IndividualMapper
import com.monzo.androidtest.individual.data.IndvArticlesRepository
import com.monzo.androidtest.individual.presentation.IndividualViewModel

class IndividualModule {
    fun inject(context: Context, url: String): IndividualViewModel {
        val networkModule = NetworkModule(context = context)
        val guardianService = networkModule.provideRetrofit().create(GuardianService::class.java)
        return IndividualViewModel(
            repository = IndvArticlesRepository(
                guardianService = guardianService,
                individualMapper = IndividualMapper()
            ),
            url = url
        )
    }
}