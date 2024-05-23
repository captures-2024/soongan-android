package com.captures2024.soongan.feature.signIn

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.captures2024.soongan.feature.signIn.ui.SignInDefaultScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class SignInDefaultScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    internal fun `구글_로그인_버튼이_존재하는지_확인`() {
        composeRule.setContent {
            SignInDefaultScreen()
        }

        Thread.sleep(1000)

        composeRule.onNodeWithText("Google Sign In").assertExists()
    }

    @Test
    internal fun `구글_로그인_버튼이_클릭_이벤트_확인`() {
        var result = false
        composeRule.setContent {
            SignInDefaultScreen(
                onClickGoogleSignIn = {
                    result = true
                }
            )
        }

        Thread.sleep(1000)

        composeRule.onNodeWithText("Google Sign In").performClick()

        Assert.assertTrue(result)
    }

    @Test
    internal fun `카카오_로그인_버튼이_존재하는지_확인`() {
        composeRule.setContent {
            SignInDefaultScreen()
        }

        Thread.sleep(1000)

        composeRule.onNodeWithText("Kakao Sign In").assertExists()
    }

    @Test
    internal fun `카카오_로그인_버튼이_클릭_이벤트_확인`() {
        var result = false
        composeRule.setContent {
            SignInDefaultScreen(
                onClickKakaoSignIn = {
                    result = true
                }
            )
        }

        Thread.sleep(1000)

        composeRule.onNodeWithText("Kakao Sign In").performClick()

        Assert.assertTrue(result)
    }

}