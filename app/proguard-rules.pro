# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep Dagger
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }

-keep class * extends androidx.lifecycle.ViewModel {
    <init>();
}
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# Keep Dagger-related classes
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}

# Keep specific interface and its methods
-keepnames interface dagger.Lazy
-keepnames class dagger.Lazy

#-keep class org.robolectric.** { *; }
-keep, allowobfuscation, allowoptimization @kotlinx.serialization.Serializable class *

-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
-keep,allowobfuscation,allowshrinking class kotlinx.coroutines.flow.Flow

-keepattributes RuntimeVisibleAnnotations
-keep,allowobfuscation,allowshrinking class * extends androidx.navigation.Navigator

# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# R8 full mode strips generic signatures from return types if not kept.
-keep,allowobfuscation,allowshrinking class retrofit2.Response

-keep class com.kakao.sdk.**.model.* { <fields>; }
-keep class * extends com.google.gson.TypeAdapter

# https://github.com/square/okhttp/pull/6792
-dontwarn org.bouncycastle.jsse.**
-dontwarn org.conscrypt.*
-dontwarn org.openjsse.**

-keep class kotlinx.serialization.** { *; }
-keep @kotlinx.serialization.Serializable class * { *; }

-keep class * extends com.captures2024.soongan.core.common.base.UIIntent { *; }
-keep interface * extends com.captures2024.soongan.core.common.base.UIIntent { *; }
-keep class * extends com.captures2024.soongan.core.common.base.UISideEffect { *; }
-keep interface * extends com.captures2024.soongan.core.common.base.UISideEffect { *; }
-keep class * extends com.captures2024.soongan.core.common.base.UIState { *; }

-keep class * extends com.captures2024.soongan.core.common.base.BaseViewModel { *; }
