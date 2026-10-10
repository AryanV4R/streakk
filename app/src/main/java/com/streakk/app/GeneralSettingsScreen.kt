package com.streakk.app

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.biometric.BiometricManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.format.TextStyle

@Composable
fun GeneralSettingsScreen(
    onBack: () -> Unit,
    bottomContentPadding: Dp = 0.dp,
    firstDayOfWeek: DayOfWeek = DayOfWeek.MONDAY,
    onFirstDayOfWeekChange: (DayOfWeek) -> Unit = {},
    showStreakCount: Boolean = true,
    onShowStreakCountChange: (Boolean) -> Unit = {},
    soundOnComplete: Boolean = true,
    onSoundOnCompleteChange: (Boolean) -> Unit = {},
    defaultSortOption: PdfSortOption = PdfSortOption.NEWEST,
    onDefaultSortOptionChange: (PdfSortOption) -> Unit = {},
    autoScanEnabled: Boolean = true,
    onAutoScanEnabledChange: (Boolean) -> Unit = {},
    preferredPdfPackage: String? = null,
    onPreferredPdfPackageChange: (String?) -> Unit = {},
    fileSizeUnit: FileSizeUnit = FileSizeUnit.AUTO,
    onFileSizeUnitChange: (FileSizeUnit) -> Unit = {},
    appLockEnabled: Boolean = false,
    onAppLockEnabledChange: (Boolean) -> Unit = {},
    autoLockTimeout: AutoLockTimeout = AutoLockTimeout.INSTANT,
    onAutoLockTimeoutChange: (AutoLockTimeout) -> Unit = {},
    hiddenTab: Screen = Screen.INBOX,
    onHiddenTabChange: (Screen) -> Unit = {},
    widgetSource: WidgetSource = WidgetSource.AUTO,
    onWidgetSourceChange: (WidgetSource) -> Unit = {}
) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val currentLocale = remember(configuration) { configuration.locales[0] }
    val coroutineScope = rememberCoroutineScope()
    var showFirstDayDialog by remember { mutableStateOf(false) }
    var showSortDialog by remember { mutableStateOf(false) }
    var showOpenWithDialog by remember { mutableStateOf(false) }
    var showFileSizeDialog by remember { mutableStateOf(false) }
    var showAutoLockDialog by remember { mutableStateOf(false) }
    var showClearCacheConfirm by remember { mutableStateOf(false) }
    var showCacheClearedBanner by remember { mutableStateOf(false) }
    var showHiddenTabSheet by remember { mutableStateOf(false) }
    var showWidgetSourceDialog by remember { mutableStateOf(false) }
    var preferredAppLabel by remember(preferredPdfPackage) {
        mutableStateOf(
            preferredPdfPackage?.let {
                try {
                    context.packageManager.getApplicationLabel(
                        context.packageManager.getApplicationInfo(
                            it,
                            0
                        )
                    ).toString()
                } catch (_: Exception) {
                    null
                }
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
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
                "General settings",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.height(24.dp))

        if (showCacheClearedBanner) {
            LaunchedEffect(Unit) {
                delay(2.seconds)
                showCacheClearedBanner = false
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text("Thumbnail cache cleared", color = Color.White, fontSize = 14.sp)
            }
        }

        Text("HABITS & TASKS", color = TextGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        SettingsCard {
            SettingsRow(
                title = "First day of week",
                trailingText = firstDayOfWeek.getDisplayName(TextStyle.FULL, currentLocale),
                onClick = { showFirstDayDialog = true }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            SettingsRow(
                title = "Sound on complete",
                trailingText = if (soundOnComplete) "On" else "Off",
                onClick = {
                    val newValue = !soundOnComplete
                    onSoundOnCompleteChange(newValue)
                    if (newValue) CompletionSoundPlayer.play()
                }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            SettingsRow(
                title = "Show streak count on cards",
                trailingText = if (showStreakCount) "On" else "Off",
                onClick = { onShowStreakCountChange(!showStreakCount) }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            SettingsRow(
                title = "Hidden tab",
                trailingText = tabLabel(hiddenTab),
                onClick = { showHiddenTabSheet = true }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            SettingsRow(
                title = "Widget shows",
                trailingText = widgetSource.label,
                onClick = { showWidgetSourceDialog = true }
            )
        }

        if (showFirstDayDialog) {
            FirstDayOfWeekDialog(
                current = firstDayOfWeek,
                onDismiss = { showFirstDayDialog = false },
                onSelect = { day ->
                    onFirstDayOfWeekChange(day)
                    showFirstDayDialog = false
                }
            )
        }

        Spacer(Modifier.height(20.dp))

        Text("PDF READER", color = TextGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        SettingsCard {
            SettingsRow(
                title = "Default sort order",
                trailingText = defaultSortOption.label,
                onClick = { showSortDialog = true }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            SettingsRow(
                title = "Open PDFs with",
                trailingText = preferredAppLabel ?: "Select app",
                onClick = { showOpenWithDialog = true }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            SettingsRow(
                title = "Auto-scan for new PDFs",
                trailingText = if (autoScanEnabled) "On" else "Off",
                onClick = { onAutoScanEnabledChange(!autoScanEnabled) }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            SettingsRow(title = "Clear thumbnail cache", onClick = { showClearCacheConfirm = true })
            HorizontalDivider(color = Color(0xFF3A3B44))
            SettingsRow(
                title = "Show file size in",
                trailingText = when (fileSizeUnit) {
                    FileSizeUnit.AUTO -> "Auto (MB/KB)"
                    FileSizeUnit.KB -> "Always KB"
                    FileSizeUnit.MB -> "Always MB"
                },
                onClick = { showFileSizeDialog = true }
            )
        }

        if (showHiddenTabSheet) {
            HiddenTabPickerSheet(
                hiddenTab = hiddenTab,
                onSelect = onHiddenTabChange,
                onDismiss = { showHiddenTabSheet = false }
            )
        }
                if (showWidgetSourceDialog) {
            SingleChoiceDialog(
                title = "Widget shows",
                options = WidgetSource.entries,
                optionLabel = {
                    when (it) {
                        WidgetSource.AUTO -> "Auto (follow first tab)"
                        WidgetSource.CALENDAR -> "To-Do Calendar"
                        WidgetSource.INBOX -> "Inbox"
                    }
                },
                current = widgetSource,
                onDismiss = { showWidgetSourceDialog = false },
                onSelect = {
                    onWidgetSourceChange(it)
                    showWidgetSourceDialog = false
                }
            )
        }
        if (showSortDialog) {
            SingleChoiceDialog(
                title = "Default sort order",
                options = PdfSortOption.entries,
                optionLabel = { it.label },
                current = defaultSortOption,
                onDismiss = { showSortDialog = false },
                onSelect = {
                    onDefaultSortOptionChange(it)
                    showSortDialog = false
                }
            )
        }

        if (showOpenWithDialog) {
            OpenWithDialog(
                apps = remember { getPdfHandlerApps(context) },
                current = preferredPdfPackage,
                onDismiss = { showOpenWithDialog = false },
                onSelect = {
                    onPreferredPdfPackageChange(it)
                    showOpenWithDialog = false
                }
            )
        }

        if (showFileSizeDialog) {
            SingleChoiceDialog(
                title = "Show file size in",
                options = FileSizeUnit.entries,
                optionLabel = {
                    when (it) {
                        FileSizeUnit.AUTO -> "Auto (MB/KB)"
                        FileSizeUnit.KB -> "Always KB"
                        FileSizeUnit.MB -> "Always MB"
                    }
                },
                current = fileSizeUnit,
                onDismiss = { showFileSizeDialog = false },
                onSelect = {
                    onFileSizeUnitChange(it)
                    showFileSizeDialog = false
                }
            )
        }

        if (showClearCacheConfirm) {
            AlertDialog(
                onDismissRequest = { showClearCacheConfirm = false },
                title = { Text("Clear thumbnail cache?") },
                text = { Text("PDF thumbnails will be re-generated the next time you open each file.") },
                confirmButton = {
                    TextButton(onClick = {
                        PdfThumbnailCache.clear()
                        coroutineScope.launch { PdfThumbnailDiskCache.clear(context) }
                        showClearCacheConfirm = false
                        showCacheClearedBanner = true
                    }) {
                        Text("Clear", color = Color(0xFFE05260), fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showClearCacheConfirm = false }) { Text("Cancel") }
                },
                containerColor = CardBg,
                titleContentColor = Color.White,
                textContentColor = TextGray
            )
        }

        Spacer(Modifier.height(20.dp))

        Text("SECURITY", color = TextGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        SettingsCard {
            SettingsRow(
                title = "App lock (Fingerprint)",
                trailingText = if (appLockEnabled) "On" else "Off",
                onClick = {
                    if (!appLockEnabled) {
                        val biometricManager = BiometricManager.from(context)
                        if (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS) {
                            onAppLockEnabledChange(true)
                        } else {
                            Toast.makeText(
                                context,
                                "Set up fingerprint unlock in your phone's settings first",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        onAppLockEnabledChange(false)
                    }
                }
            )
            if (appLockEnabled) {
                HorizontalDivider(color = Color(0xFF3A3B44))
                SettingsRow(
                    title = "Auto-lock",
                    trailingText = autoLockTimeout.label,
                    onClick = { showAutoLockDialog = true }
                )
            }
        }

        if (showAutoLockDialog) {
            AutoLockTimeoutSheet(
                current = autoLockTimeout,
                onDismiss = { showAutoLockDialog = false },
                onSelect = {
                    onAutoLockTimeoutChange(it)
                    showAutoLockDialog = false
                }
            )
        }
        Spacer(Modifier.height(20.dp + bottomContentPadding))
    }
}