package com.streakk.app.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {
    @get:Rule val rule = BaselineProfileRule()

    @Test
    fun generate() = rule.collect(packageName = "com.streakk.app") {
        pressHome()
        startActivityAndWait()
        device.waitForIdle()
        listOf("Tasks", "Habits", "PDFs").forEach { tab ->
            device.findObject(By.desc(tab))?.click()
            device.waitForIdle()
        }
        device.findObject(By.scrollable(true))?.let {
            it.setGestureMargin(device.displayWidth / 5)
            it.fling(Direction.DOWN)
            device.waitForIdle()
        }
        device.findObject(By.desc("Settings"))?.click()
        device.waitForIdle()
    }
}