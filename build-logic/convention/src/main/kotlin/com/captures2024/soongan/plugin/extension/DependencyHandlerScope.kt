package com.captures2024.soongan.plugin.extension

import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any) = add(
    configurationName = "implementation",
    dependencyNotation = dependencyNotation
)

internal fun DependencyHandlerScope.testImplementation(dependencyNotation: Any) = add(
    configurationName = "testImplementation",
    dependencyNotation = dependencyNotation
)

internal fun DependencyHandlerScope.debugImplementation(dependencyNotation: Any) = add(
    configurationName = "debugImplementation",
    dependencyNotation = dependencyNotation
)

internal fun DependencyHandlerScope.androidTestImplementation(dependencyNotation: Any) = add(
    configurationName = "androidTestImplementation",
    dependencyNotation = dependencyNotation
)

internal fun DependencyHandlerScope.ksp(dependencyNotation: Any) = add(
    configurationName = "ksp",
    dependencyNotation = dependencyNotation
)

internal fun DependencyHandlerScope.kspTest(dependencyNotation: Any) = add(
    configurationName = "kspTest",
    dependencyNotation = dependencyNotation
)
