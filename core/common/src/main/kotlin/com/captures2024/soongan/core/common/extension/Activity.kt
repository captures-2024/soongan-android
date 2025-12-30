package com.captures2024.soongan.core.common.extension

import android.app.Activity
import android.content.Intent

inline fun <reified T : Activity> Activity.startActivityWithAnimation(
    withFinish: Boolean,
    intentBuilder: Intent.() -> Intent = { this },
) {
    startActivity(Intent(this, T::class.java).intentBuilder())
    if (withFinish) finish()
}
