package com.streakk.app

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrivacyPolicyScreen(onBack: () -> Unit, bottomContentPadding: Dp = 0.dp) {
    BackHandler(onBack = onBack)
    val bodyColor = Color(0xFFC5C6CE)

    @Composable
    fun sectionTitle(text: String) {
        Text(text, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
    }

    @Composable
    fun sectionBody(text: String) {
        Text(text, color = bodyColor, fontSize = 14.sp, lineHeight = 21.sp)
        Spacer(Modifier.height(20.dp))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(bottom = bottomContentPadding)
    ) {
        Spacer(Modifier.height(20.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.clickable { onBack() }
            )
            Spacer(Modifier.width(16.dp))
            Text(
                "Privacy Policy",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.height(8.dp))
        Text("Last updated: October 2026", color = TextGray, fontSize = 13.sp)
        Spacer(Modifier.height(24.dp))

        sectionBody(
            "Streakk (\"we\", \"our\", \"the app\") is a habit tracking, to-do list, and PDF reading app. " +
                    "This Privacy Policy explains what information the app handles and how, in plain language."
        )

        sectionTitle("1. Data We Collect")
        sectionBody(
            "Streakk does not collect, transmit, or sell any personal data to us or to any third party. " +
                    "We do not run our own servers and have no access to your habits, tasks, or files. " +
                    "Everything you create in the app stays on your device."
        )

        sectionTitle("2. Local Storage")
        sectionBody(
            "Your habits, streaks, to-do tasks, and app preferences are saved locally on your device using " +
                    "Android's standard app storage. This data is only readable by Streakk and is removed " +
                    "automatically if you uninstall the app, or manually at any time using \"Delete all data\" " +
                    "in Settings. Streakk also automatically saves up to three recent backup copies of your " +
                    "habits, tasks and settings as files in the Documents/Streakk folder on your device, so you " +
                    "can restore them after reinstalling the app. These files are not sent anywhere by Streakk " +
                    "and are not removed when you uninstall the app; like any file in your Documents folder, " +
                    "they may be visible to other apps you have given file access to. You can turn this off in " +
                    "Settings → Backup & restore. You can also export your data to a file using \"Export data\" and import it " +
                    "again later; that file is saved wherever you choose, stays under your control, and is " +
                    "never sent anywhere by Streakk. If Android's device backup is turned on, Android may also " +
                    "keep an encrypted copy of this data in your own Google account."
        )

        sectionTitle("3. Permissions")
        sectionBody(
            "Streakk requests storage access to let you browse and open PDF files that already " +
                    "exist on your device, and to save and read the backup copies of your own Streakk data " +
                    "in your Documents/Streakk folder. We do not read, copy, or upload the contents of your PDF files — the app simply " +
                    "displays files you choose to open. If you enable habit or task reminders, they are scheduled locally " +
                    "on your device and are never sent anywhere."
        )

        sectionTitle("4. Third-Party Services")
        sectionBody(
            "The \"Feedback\", \"Rate us\", and \"Share with friends\" options in Settings open your " +
                    "device's email app or the Google Play Store. These are standard Android system actions — " +
                    "Streakk does not share any of your app data with them, and any information you choose to " +
                    "send (for example, in a feedback email) is handled directly by that app, under its own " +
                    "privacy policy."
        )

        sectionTitle("5. Children's Privacy")
        sectionBody(
            "Streakk does not knowingly collect any information from anyone, including children, " +
                    "because the app does not collect information at all. It can be used by any age group."
        )

        sectionTitle("6. Your Control Over Your Data")
        sectionBody(
            "Since all data lives only on your device, you are always in full control of it. You can " +
                    "erase everything at any time from Settings → Delete all data, which also removes the " +
                    "backup copies Streakk saved. Uninstalling the app removes its private data, but backup " +
                    "files in your Documents/Streakk folder stay on your device until you delete them yourself. " +
                    "You can turn off automatic backups at any time in Settings → Backup & restore."
        )

        sectionTitle("7. Changes to This Policy")
        sectionBody(
            "If this policy is ever updated, the new version will be posted here with a revised " +
                    "\"Last updated\" date at the top of this page."
        )

        sectionTitle("8. Contact Us")
        sectionBody(
            "If you have any questions about this Privacy Policy, reach out at idabhinavx@protonmail\u200B.com."
        )

        Spacer(Modifier.height(20.dp))
    }
}