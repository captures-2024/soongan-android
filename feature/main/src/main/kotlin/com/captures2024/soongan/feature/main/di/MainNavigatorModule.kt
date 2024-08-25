package com.captures2024.soongan.feature.main.di

import android.app.Activity
import android.content.Intent
import com.captures2024.soongan.core.common.extension.startActivityWithAnimation
import com.captures2024.soongan.core.navigator.activity.MainActivityNavigator
import com.captures2024.soongan.feature.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

internal class MainActivityNavigatorImpl
@Inject
constructor(

) : MainActivityNavigator {
    override fun navigateFrom(
        activity: Activity,
        withFinish: Boolean,
        intentBuilder: Intent.() -> Intent,
    ) {
        activity.startActivityWithAnimation<MainActivity>(
            withFinish = withFinish,
            intentBuilder = intentBuilder,
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MainNavigatorModule {
    @Singleton
    @Binds
    abstract fun bindMainNavigator(mainNavigatorImpl: MainActivityNavigatorImpl): MainActivityNavigator
}
