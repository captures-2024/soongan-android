package com.captures2024.soongan.feature.sign.di

import android.app.Activity
import android.content.Intent
import com.captures2024.soongan.core.common.extension.startActivityWithAnimation
import com.captures2024.soongan.feature.navigator.SignNavigator
import com.captures2024.soongan.feature.sign.SignActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

internal class SignNavigatorImpl
@Inject
constructor(

) : SignNavigator {
    override fun navigateFrom(
        activity: Activity,
        withFinish: Boolean,
        intentBuilder: Intent.() -> Intent,
    ) {
        activity.startActivityWithAnimation<SignActivity>(
            withFinish = withFinish,
            intentBuilder = intentBuilder,
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SignNavigatorModule {
    @Singleton
    @Binds
    abstract fun bindSignNavigator(signNavigatorImpl: SignNavigatorImpl): SignNavigator
}