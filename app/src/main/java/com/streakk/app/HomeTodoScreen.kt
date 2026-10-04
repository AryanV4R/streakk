package com.streakk.app

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun QuickAddTodoSheet(
    initialText: String = "",
    initialReminderTime: LocalTime? = null,
    onDismiss: () -> Unit,
    onSave: (String, LocalTime?) -> Unit
) {
    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = initialText,
                selection = TextRange(initialText.length)
            )
        )
    }
    val text = textFieldValue.text
    var reminderTime by remember { mutableStateOf(initialReminderTime) }
    var showTimePicker by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    var closeAction by remember { mutableStateOf<(() -> Unit)?>(null) }
    val exitProgress by animateFloatAsState(
        targetValue = if (closeAction != null) 1f else 0f,
        animationSpec = tween(durationMillis = 220, easing = FastOutLinearInEasing),
        label = "quickAddSheetExit"
    )
    var hasEntered by remember { mutableStateOf(false) }
    val enterProgress by animateFloatAsState(
        targetValue = if (hasEntered) 1f else 0f,
        animationSpec = tween(durationMillis = 180, easing = FastOutSlowInEasing),
        label = "quickAddSheetEnter"
    )
    LaunchedEffect(closeAction) {
        val action = closeAction
        if (action != null) {
            delay(240.milliseconds)
            action()
        }
    }
    val dismissWithAnimation: () -> Unit = {
        if (closeAction == null) closeAction = onDismiss
    }

    BackHandler(onBack = dismissWithAnimation)

    LaunchedEffect(Unit) {
        withFrameNanos {}
        withFrameNanos {}
        hasEntered = true
        snapshotFlow { enterProgress }.first { it >= 0.999f }
        repeat(20) {
            try {
                focusRequester.requestFocus()
                return@LaunchedEffect
            } catch (_: IllegalStateException) {
                delay(16.milliseconds)
            }
        }
    }

    Dialog(
        onDismissRequest = dismissWithAnimation,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        val currentOnDismiss by rememberUpdatedState(dismissWithAnimation)
        val dialogWindow = (LocalView.current.parent as? DialogWindowProvider)?.window
        SideEffect { dialogWindow?.setDimAmount(0f) }
        val dialogView = LocalView.current
        val hideDialogKeyboard: () -> Unit = {
            dialogWindow?.let { w ->
                WindowCompat.getInsetsController(w, dialogView)
                    .hide(WindowInsetsCompat.Type.ime())
            }
        }
        val imeInsets = WindowInsets.ime
        val imeDensity = LocalDensity.current
        LaunchedEffect(Unit) {
            var lastImeHeight = imeInsets.getBottom(imeDensity)
            var peakImeHeight = 0
            var keyboardHasRisen = false
            val closeTolerancePx = with(imeDensity) { 24.dp.toPx() }
            val openSettleMillis = 500L
            val startTime = System.currentTimeMillis()
            var dismissJob: Job? = null

            snapshotFlow { imeInsets.getBottom(imeDensity) }
                .collect { imeHeight ->
                    if (imeHeight > lastImeHeight) keyboardHasRisen = true
                    lastImeHeight = imeHeight
                    if (keyboardHasRisen) {
                        when {
                            imeHeight > peakImeHeight -> {
                                peakImeHeight = imeHeight
                                dismissJob?.cancel()
                                dismissJob = null
                            }
                            imeHeight >= peakImeHeight - closeTolerancePx -> {
                                dismissJob?.cancel()
                                dismissJob = null
                            }
                            else -> {
                                if (dismissJob == null &&
                                    System.currentTimeMillis() - startTime > openSettleMillis
                                ) {
                                    dismissJob = launch {
                                        delay(180.milliseconds)
                                        peakImeHeight = 0
                                        keyboardHasRisen = false
                                        dismissJob = null
                                        if (!showTimePicker) currentOnDismiss()
                                    }
                                }
                            }
                        }
                    }
                }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(Color.Black.copy(alpha = 0.6f * (1f - exitProgress)))
                }
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    hideDialogKeyboard()
                    dismissWithAnimation()
                },
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .windowInsetsPadding(WindowInsets.ime.union(WindowInsets.navigationBars))
                    .padding(bottom = 8.dp)
                    .graphicsLayer {
                        val shownFraction = enterProgress * (1f - exitProgress)
                        translationY = (1f - shownFraction) * size.height
                        alpha = shownFraction
                    }
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { /* absorb taps so sheet doesn't dismiss when tapped */ },
                shape = RoundedCornerShape(14.dp),
                color = CardBg
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 24.dp,
                        end = 20.dp,
                        bottom = 14.dp
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(Color.Transparent)
                                .border(width = 1.5.dp, color = TextGray, shape = CircleShape)
                        )
                        Spacer(Modifier.width(12.dp))
                        OutlinedTextField(
                            value = textFieldValue,
                            onValueChange = { textFieldValue = it },
                            placeholder = {
                                Text(
                                    "Add a new task...",
                                    color = TextGray,
                                    fontStyle = FontStyle.Italic
                                )
                            },
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 56.dp, max = 160.dp)
                                .focusRequester(focusRequester),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            )
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                                .background(
                                    if (reminderTime != null) BlueAccent else Color(
                                        0xFF33343D
                                    )
                                )
                                .padding(start = 8.dp, top = 6.dp, end = 10.dp, bottom = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier.clickable { showTimePicker = true },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Notifications,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                if (reminderTime != null) {
                                    Text(
                                        reminderTime!!.format(DateTimeFormatter.ofPattern("HH:mm")),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                } else {
                                    Text(
                                        "Set reminder",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                            if (reminderTime != null) {
                                Spacer(Modifier.width(10.dp))
                                Box(
                                    modifier = Modifier
                                        .width(1.dp)
                                        .height(14.dp)
                                        .background(Color.White.copy(alpha = 0.4f))
                                )
                                Spacer(Modifier.width(10.dp))
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Cancel reminder",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .clickable(
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        ) { reminderTime = null }
                                        .padding(horizontal = 4.dp, vertical = 3.dp)
                                        .size(16.dp)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                                .background(if (text.isNotBlank()) BlueAccent else Color(0xFF33343D))
                                .clickable(enabled = text.isNotBlank()) {
                                    keyboardController?.hide()
                                    if (closeAction == null) {
                                        closeAction = { onSave(text, reminderTime) }
                                    }
                                }
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text("Done", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }

    if (showTimePicker) {
        HabitTimePickerDialog(
            initialTime = reminderTime,
            onDismiss = { showTimePicker = false },
            onConfirm = { reminderTime = it; showTimePicker = false; keyboardController?.show() }
        )
    }
}