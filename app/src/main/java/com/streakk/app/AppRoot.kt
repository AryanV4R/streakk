package com.streakk.app

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.edit
import androidx.core.net.toUri
import androidx.compose.runtime.mutableLongStateOf
import kotlin.time.Duration.Companion.milliseconds
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.concurrent.ConcurrentHashMap

@Composable
fun AppRoot() {
    val context = LocalContext.current

    var showPermissionDialog by remember { mutableStateOf(false) }
    var dialogWantsNotifications by remember { mutableStateOf(false) }
    var dialogWantsStorage by remember { mutableStateOf(false) }
    var pendingPermissionAction by remember { mutableStateOf<String?>(null) }
    var showOnboarding by remember {
        val onboardingPrefs = context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)
        mutableStateOf(
            !onboardingPrefs.getBoolean("onboarding_done", false) &&
                !SettingsStorage.hasSeenTutorial(context)
        )
    }

    fun checkAndShowPermissionDialog() {
        val needsNotifications = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        val needsStorage = !hasStorageAccess(context)
        if (needsNotifications || needsStorage) {
            dialogWantsNotifications = needsNotifications
            dialogWantsStorage = needsStorage
            showPermissionDialog = true
        }
    }

    LaunchedEffect(Unit) {

        if (!showOnboarding) {
            checkAndShowPermissionDialog()
        }
        withContext(Dispatchers.Default) { HabitReminderScheduler.rescheduleAll(context) }
    }

    LaunchedEffect(pendingPermissionAction) {
        when (pendingPermissionAction) {
            "notifications" -> {
                delay(300.milliseconds)
                context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)
                    .edit { putBoolean("notif_permission_requested", true) }

                val activity = context as? Activity
                if (activity != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        9001
                    )
                }
                pendingPermissionAction = null
            }

            "storage" -> {
                delay(300.milliseconds)
                val intent = Intent(
                    Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                    "package:${context.packageName}".toUri()
                )

                context.startActivity(intent)
                pendingPermissionAction = null
            }

            else -> {}
        }
    }

    if (showPermissionDialog) {
        PermissionsRequiredDialog(
            showNotifications = dialogWantsNotifications,
            showStorage = dialogWantsStorage,
            onAllowNotifications = {
                dialogWantsNotifications = false
                if (!dialogWantsStorage) showPermissionDialog = false
                pendingPermissionAction = "notifications"
            },
            onAllowStorage = {
                dialogWantsStorage = false
                if (!dialogWantsNotifications) showPermissionDialog = false
                pendingPermissionAction = "storage"
            },
            onDismiss = { showPermissionDialog = false }
        )
    }

    LaunchErrorState.message?.let { fullMessage ->
        AlertDialog(
            onDismissRequest = { LaunchErrorState.message = null },
            title = { Text("Launch error (debug)") },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    SelectionContainer {
                        Text(fullMessage, fontSize = 13.sp)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { LaunchErrorState.message = null }) { Text("OK") }
            }
        )
    }

    LaunchErrorState.message?.let { fullMessage ->
        AlertDialog(
            onDismissRequest = { LaunchErrorState.message = null },
            title = { Text("Launch error (debug)") },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    SelectionContainer {
                        Text(fullMessage, fontSize = 13.sp)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { LaunchErrorState.message = null }) { Text("OK") }
            }
        )
    }

    LaunchErrorState.message?.let { fullMessage ->
        AlertDialog(
            onDismissRequest = { LaunchErrorState.message = null },
            title = { Text("Launch error (debug)") },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    SelectionContainer {
                        Text(fullMessage, fontSize = 13.sp)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { LaunchErrorState.message = null }) { Text("OK") }
            }
        )
    }

    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    var showAddHabit by remember { mutableStateOf(false) }
    BackHandler(enabled = !showAddHabit && (currentScreen == Screen.TASKS || currentScreen == Screen.SETTINGS)) {
        currentScreen = Screen.HOME
    }
    var editingHabit by remember { mutableStateOf<Habit?>(null) }
    var newHabitStartDate by remember { mutableStateOf(LocalDate.now()) }
    var showDeletedBanner by remember { mutableStateOf(false) }
    var showHabitLimitBanner by remember { mutableStateOf(false) }
    var showTodoDeletedBanner by remember { mutableStateOf(false) }
    var showDeleteAllBanner by remember { mutableStateOf(false) }
        val habits = remember {
            mutableStateListOf<Habit>().apply {
                addAll(
                    StartupPreload.habits ?: HabitStorage.load(
                        context
                    )
                )
            }
        }
    val habitStatus = remember {
        mutableStateMapOf<Pair<Long, LocalDate>, HabitStatus>().apply {
            putAll(
                StartupPreload.habitStatus ?: HabitStorage.loadStatus(context)
            )
        }
    }
    val todos = remember { 
        mutableStateListOf<HomeTodoItem>().apply { 
            addAll( 
                (StartupPreload.todos ?: TodoStorage.load( 
                    context 
                )).filter { !it.date.isBefore(LocalDate.now().minusDays(10)) } 
            ) 
        } 
    }
    var firstDayOfWeek by remember { mutableStateOf(SettingsStorage.loadFirstDayOfWeek(context)) }
    var showStreakCount by remember { mutableStateOf(SettingsStorage.loadShowStreakCount(context)) }
    var defaultSortOption by remember { mutableStateOf(SettingsStorage.loadDefaultSortOrder(context)) }
    var autoScanEnabled by remember { mutableStateOf(SettingsStorage.loadAutoScan(context)) }
    var preferredPdfPackage by remember { mutableStateOf(SettingsStorage.loadPreferredPdfApp(context)) }
    var fileSizeUnit by remember { mutableStateOf(SettingsStorage.loadFileSizeUnit(context)) }
    var soundOnComplete by remember { mutableStateOf(SettingsStorage.loadSoundOnComplete(context)) }
    var appLockEnabled by remember { mutableStateOf(SettingsStorage.loadAppLockEnabled(context)) }
    var autoLockTimeout by remember { mutableStateOf(SettingsStorage.loadAutoLockTimeout(context)) }
    var isAppUnlocked by remember { mutableStateOf(!SettingsStorage.loadAppLockEnabled(context)) }
    var lastBackgroundedAt by remember { mutableLongStateOf(0L) }
    var autoBackupEnabled by remember { mutableStateOf(SettingsStorage.loadAutoBackupEnabled(context)) }
    var restoreOffer by remember { mutableStateOf<BackupData?>(null) }
    var encryptedRestorePending by remember { mutableStateOf(false) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    val previousTodos = remember { ConcurrentHashMap<Long, HomeTodoItem>() }

    fun persistTodos(snapshot: List<HomeTodoItem>) {
        TodoStorage.save(context, snapshot)
        TodoWidgetUpdater.refresh(context)
        val currentIds = snapshot.map { it.id }.toSet()
        previousTodos.keys.filter { it !in currentIds }.forEach {
            TodoReminderScheduler.cancel(context, it)
            previousTodos.remove(it)
        }
        snapshot.forEach { todo ->
            val prev = previousTodos[todo.id]
            if (prev != todo && (todo.reminderTime != null || prev?.reminderTime != null)) {
                TodoReminderScheduler.schedule(context, todo)
            }
            previousTodos[todo.id] = todo
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { habits.toList() }.collectLatest { snapshot ->
            delay(300.milliseconds)
            withContext(Dispatchers.IO) { HabitStorage.save(context, snapshot) }
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { todos.toList() }.collectLatest { snapshot ->
            delay(300.milliseconds)
            withContext(Dispatchers.IO) { persistTodos(snapshot) }
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { habitStatus.toMap() }.collectLatest { snapshot ->
            delay(300.milliseconds)
            withContext(Dispatchers.IO) { HabitStorage.saveStatus(context, snapshot) }
        }
    }

    LaunchedEffect(firstDayOfWeek) {
        SettingsStorage.saveFirstDayOfWeek(context, firstDayOfWeek)
    }

    LaunchedEffect(showStreakCount) {
        SettingsStorage.saveShowStreakCount(context, showStreakCount)
    }

    LaunchedEffect(defaultSortOption) {
        SettingsStorage.saveDefaultSortOrder(context, defaultSortOption)
    }

    LaunchedEffect(autoScanEnabled) {
        SettingsStorage.saveAutoScan(context, autoScanEnabled)
    }

    LaunchedEffect(preferredPdfPackage) {
        SettingsStorage.savePreferredPdfApp(context, preferredPdfPackage)
    }

    LaunchedEffect(fileSizeUnit) {
        SettingsStorage.saveFileSizeUnit(context, fileSizeUnit)
    }

    LaunchedEffect(Unit) {
        CompletionSoundPlayer.init(context)
    }
    DisposableEffect(Unit) {
        onDispose { CompletionSoundPlayer.release() }
    }

    LaunchedEffect(soundOnComplete) {
        SettingsStorage.saveSoundOnComplete(context, soundOnComplete)
    }

    LaunchedEffect(appLockEnabled) {
        SettingsStorage.saveAppLockEnabled(context, appLockEnabled)
    }

    LaunchedEffect(autoLockTimeout) {
        SettingsStorage.saveAutoLockTimeout(context, autoLockTimeout)
    }

    LaunchedEffect(autoBackupEnabled) {
        SettingsStorage.saveAutoBackupEnabled(context, autoBackupEnabled)
    }
    val pendingImport = BackupBridge.pending
    LaunchedEffect(pendingImport) {
        val data = pendingImport?.let { imported ->
            imported.copy(todos = imported.todos.filter { !it.date.isBefore(LocalDate.now().minusDays(10)) })
        } ?: return@LaunchedEffect

        habits.forEach { HabitReminderScheduler.cancel(context, it.id) }
        habits.clear()
        habits.addAll(data.habits)
        habitStatus.clear()
        habitStatus.putAll(data.habitStatus)
        todos.clear()
        todos.addAll(data.todos)

        firstDayOfWeek = data.firstDayOfWeek
        showStreakCount = data.showStreakCount
        soundOnComplete = data.soundOnComplete
        defaultSortOption = data.defaultSortOption
        autoScanEnabled = data.autoScanEnabled
        fileSizeUnit = data.fileSizeUnit

        PdfsScreenState.folders.value = data.pdfFolders
        PdfsScreenState.folderAssignments.value = data.pdfFolderAssignments

        withContext(Dispatchers.IO) {
            HabitStorage.save(context, data.habits)
            HabitStorage.saveStatus(context, data.habitStatus)
            persistTodos(data.todos)
            PdfFolderStorage.save(context, data.pdfFolders)
            PdfFolderAssignmentStorage.save(context, data.pdfFolderAssignments)
        }

        data.habits.forEach { habit ->
            if (habit.notificationsEnabled && habit.startTime != null) {
                HabitReminderScheduler.schedule(context, habit)
            }
        }
        BackupBridge.pending = null
    }
    LaunchedEffect(showOnboarding) {
        if (showOnboarding) return@LaunchedEffect
        val restorePrefs = context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)
        if (restorePrefs.getBoolean("restore_offer_done", false)) return@LaunchedEffect
        if (habits.isNotEmpty() || todos.isNotEmpty()) {
            restorePrefs.edit { putBoolean("restore_offer_done", true) }
            return@LaunchedEffect
        }
        if (!hasStorageAccess(context)) return@LaunchedEffect
        val found = withContext(Dispatchers.IO) { BackupManager.findLatestAutoBackup() }
        restoreOffer = found
            ?.let { it.copy(todos = it.todos.filter { t -> !t.date.isBefore(LocalDate.now().minusDays(10)) }) }
            ?.takeIf { it.habits.isNotEmpty() || it.todos.isNotEmpty() }
            if (restoreOffer == null) {
            encryptedRestorePending = withContext(Dispatchers.IO) { BackupManager.hasEncryptedAutoBackup() }
        }
    }
    DisposableEffect(lifecycleOwner, appLockEnabled, autoLockTimeout) {
        val lockObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> {
                    HabitStorage.save(context, habits.toList())
                    HabitStorage.saveStatus(context, habitStatus.toMap())
                    persistTodos(todos.toList())
                    if (autoBackupEnabled && hasStorageAccess(context)) {
                        coroutineScope.launch(Dispatchers.IO) {
                            if (BackupManager.writeAutoBackup(context)) {
                                SettingsStorage.saveLastBackupAt(context, System.currentTimeMillis())
                            }
                        }
                    }
                    if (appLockEnabled) {
                        lastBackgroundedAt = System.currentTimeMillis()
                    }
                }

                Lifecycle.Event.ON_START -> {
                    if (appLockEnabled && lastBackgroundedAt != 0L) {
                        val elapsed = System.currentTimeMillis() - lastBackgroundedAt
                        if (elapsed >= autoLockTimeout.millis) {
                            isAppUnlocked = false
                        }
                    }
                    // Widget may have changed todos while app was in background
                    coroutineScope.launch {
                        val fresh = withContext(Dispatchers.IO) {
                            TodoStorage.load(context)
                                .filter { !it.date.isBefore(LocalDate.now().minusDays(10)) }
                        }
                        if (fresh != todos.toList()) {
                            todos.clear()
                            todos.addAll(fresh)
                        }
                    }
                }

                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(lockObserver)
        onDispose { lifecycleOwner.lifecycle.removeObserver(lockObserver) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        AnimatedContent(
            targetState = appLockEnabled && !isAppUnlocked,
            transitionSpec = {
                fadeIn(animationSpec = tween(400)) togetherWith fadeOut(animationSpec = tween(400))
            },
            label = "lockScreenTransition"
        ) { isLocked ->
            if (isLocked) {
                AppLockScreen(onUnlock = { isAppUnlocked = true })
            } else {
                AnimatedContent(
                    targetState = showAddHabit,
                    transitionSpec = {
                        val slideSpec = spring<IntOffset>(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        )
                        val fadeSpec = tween<Float>(220, easing = FastOutSlowInEasing)
                        if (targetState) {
                            (slideInHorizontally(animationSpec = slideSpec) { fullWidth -> fullWidth } + fadeIn(
                                animationSpec = fadeSpec
                            ))
                                .togetherWith(slideOutHorizontally(animationSpec = slideSpec) { fullWidth -> -fullWidth / 4 } + fadeOut(
                                    animationSpec = fadeSpec
                                ))
                        } else {
                            (slideInHorizontally(animationSpec = slideSpec) { fullWidth -> -fullWidth / 4 } + fadeIn(
                                animationSpec = fadeSpec
                            ))
                                .togetherWith(slideOutHorizontally(animationSpec = slideSpec) { fullWidth -> fullWidth } + fadeOut(
                                    animationSpec = fadeSpec
                                ))
                        }
                    },
                    label = "addHabitTransition"
                ) { isAddHabit ->
                    if (isAddHabit) {
                        AddHabitScreen(
                            existingHabit = editingHabit,
                            initialStartDate = newHabitStartDate,
                            onBack = {
                                showAddHabit = false
                                editingHabit = null
                            },
                            onSave = { habit ->
                                val index = habits.indexOfFirst { it.id == habit.id }
                                if (index != -1) {
                                    habits[index] = habit
                                } else if (habits.size < MAX_ACTIVE_HABITS) {
                                    habits.add(habit)
                                }
                                if (habit.notificationsEnabled && habit.startTime != null) {
                                    HabitReminderScheduler.schedule(context, habit)
                                } else {
                                    HabitReminderScheduler.cancel(context, habit.id)
                                }
                                showAddHabit = false
                                editingHabit = null
                            },
                            onDelete = {
                                val habitId = editingHabit?.id
                                if (habitId != null) {
                                    habits.removeAll { it.id == habitId }
                                    val keysToRemove =
                                        habitStatus.keys.filter { it.first == habitId }
                                    keysToRemove.forEach { habitStatus.remove(it) }
                                    HabitReminderScheduler.cancel(context, habitId)
                                }
                                showAddHabit = false
                                editingHabit = null
                                showDeletedBanner = true
                            }
                        )
                    } else {
                        Scaffold(
                            containerColor = DarkBg,
                            bottomBar = { BottomNavBar(currentScreen) { currentScreen = it } }
                        ) { padding ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        top = padding.calculateTopPadding(),
                                        start = padding.calculateStartPadding(LocalLayoutDirection.current),
                                        end = padding.calculateEndPadding(LocalLayoutDirection.current)
                                    )
                            ) {
                                val saveableStateHolder = rememberSaveableStateHolder()
                                Crossfade(
                                    targetState = currentScreen,
                                    animationSpec = tween(220),
                                    label = "tabSwitchTransition"
                                ) { screen ->
                                    saveableStateHolder.SaveableStateProvider(screen.name) {
                                        when (screen) {
                                            Screen.HOME -> HomeTodoScreen(
                                                bottomContentPadding = padding.calculateBottomPadding(),
                                                firstDayOfWeek = firstDayOfWeek,
                                                todos = todos,
                                                soundOnComplete = soundOnComplete,
                                                onTaskDeleted = { showTodoDeletedBanner = true },
                                                onSettingsClick = {
                                                    currentScreen = Screen.SETTINGS
                                                }
                                            )

                                            Screen.TASKS -> TasksScreen(
                                                habits = habits,
                                                habitStatus = habitStatus,
                                                onAddHabit = { startDate ->
                                                    if (habits.size >= MAX_ACTIVE_HABITS) {
                                                        showHabitLimitBanner = true
                                                    } else {
                                                        editingHabit = null
                                                        newHabitStartDate = startDate
                                                        showAddHabit = true
                                                    }
                                                },
                                                onEditHabit = { habit ->
                                                    editingHabit = habit; showAddHabit = true
                                                },
                                                showDeletedBanner = showDeletedBanner,
                                                onDismissDeletedBanner = {
                                                    showDeletedBanner = false
                                                },
                                                showHabitLimitBanner = showHabitLimitBanner,
                                                onDismissHabitLimitBanner = {
                                                    showHabitLimitBanner = false
                                                },
                                                bottomContentPadding = padding.calculateBottomPadding(),
                                                firstDayOfWeek = firstDayOfWeek,
                                                showStreakCount = showStreakCount,
                                                soundOnComplete = soundOnComplete
                                            )

                                            Screen.PDFS -> PdfsScreen(
                                                defaultSortOption = defaultSortOption,
                                                autoScanEnabled = autoScanEnabled,
                                                preferredPdfPackage = preferredPdfPackage,
                                                fileSizeUnit = fileSizeUnit,
                                                onSortOptionPersist = { defaultSortOption = it },
                                                bottomContentPadding = padding.calculateBottomPadding()
                                            )

                                            Screen.SETTINGS -> SettingsScreen(
                                                onDeleteAllData = {
                                                    habits.clear()
                                                    habitStatus.clear()
                                                    todos.clear()
                                                    showDeleteAllBanner = true
                                                    coroutineScope.launch(Dispatchers.IO) {
                                                        BackupManager.deleteAllBackups(context)
                                                        SettingsStorage.saveLastBackupAt(context, 0L)
                                                    }
                                                },
                                                bottomContentPadding = padding.calculateBottomPadding(),
                                                firstDayOfWeek = firstDayOfWeek,
                                                onFirstDayOfWeekChange = { firstDayOfWeek = it },
                                                showStreakCount = showStreakCount,
                                                onShowStreakCountChange = { showStreakCount = it },
                                                soundOnComplete = soundOnComplete,
                                                onSoundOnCompleteChange = { soundOnComplete = it },
                                                defaultSortOption = defaultSortOption,
                                                onDefaultSortOptionChange = {
                                                    defaultSortOption = it
                                                },
                                                autoScanEnabled = autoScanEnabled,
                                                onAutoScanEnabledChange = { autoScanEnabled = it },
                                                preferredPdfPackage = preferredPdfPackage,
                                                onPreferredPdfPackageChange = {
                                                    preferredPdfPackage = it
                                                },
                                                fileSizeUnit = fileSizeUnit,
                                                onFileSizeUnitChange = { fileSizeUnit = it },
                                                appLockEnabled = appLockEnabled,
                                                onAppLockEnabledChange = { appLockEnabled = it },
                                                autoLockTimeout = autoLockTimeout,
                                                onAutoLockTimeoutChange = { autoLockTimeout = it },
                                                autoBackupEnabled = autoBackupEnabled,
                                                onAutoBackupEnabledChange = { autoBackupEnabled = it }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        TopBanner(
            visible = showDeletedBanner,
            message = "Habit deleted successfully",
            icon = Icons.Default.CheckCircle,
            iconTint = Color(0xFF4CAF50),
            onDismiss = { showDeletedBanner = false },
            modifier = Modifier.align(Alignment.TopCenter)
        )
        TopBanner(
            visible = showTodoDeletedBanner,
            message = "Task deleted successfully",
            icon = Icons.Default.CheckCircle,
            iconTint = Color(0xFF4CAF50),
            onDismiss = { showTodoDeletedBanner = false },
            modifier = Modifier.align(Alignment.TopCenter)
        )
        TopBanner(
            visible = showDeleteAllBanner,
            message = "All data deleted successfully",
            icon = Icons.Default.CheckCircle,
            iconTint = Color(0xFF4CAF50),
            onDismiss = { showDeleteAllBanner = false },
            modifier = Modifier.align(Alignment.TopCenter)
        )
        TopBanner(
            visible = showHabitLimitBanner,
            message = "Maximum $MAX_ACTIVE_HABITS habits reached — delete one to add a new habit",
            icon = Icons.Default.Info,
            iconTint = Color(0xFFFFA726),
            onDismiss = { showHabitLimitBanner = false },
            modifier = Modifier.align(Alignment.TopCenter)
        )
        restoreOffer?.let { offer ->
            AlertDialog(
                onDismissRequest = { },
                title = { Text("Backup found") },
                text = {
                    Text(
                        "We found a backup on this phone with ${offer.habits.size} habits and " +
                            "${offer.todos.size} tasks. Restore it?"
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)
                            .edit { putBoolean("restore_offer_done", true) }
                        BackupBridge.pending = offer
                        restoreOffer = null
                    }) {
                        Text("Restore", color = BlueAccent)
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)
                            .edit { putBoolean("restore_offer_done", true) }
                        restoreOffer = null
                    }) {
                        Text("Skip")
                    }
                },
                containerColor = CardBg,
                titleContentColor = Color.White,
                textContentColor = TextGray
            )
        }
                if (encryptedRestorePending) {
            EnterBackupPasswordDialog(
                onDismiss = {
                    context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)
                        .edit { putBoolean("restore_offer_done", true) }
                    encryptedRestorePending = false
                },
                onSubmit = { password ->
                    val data = withContext(Dispatchers.IO) {
                        BackupManager.restoreEncryptedAutoBackup(context, password)
                    }
                    if (data != null) {
                        context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)
                            .edit { putBoolean("restore_offer_done", true) }
                        BackupBridge.pending = data
                        encryptedRestorePending = false
                    }
                    data != null
                }
            )
        }
        if (showOnboarding) {
            OnboardingScreen(
                onFinished = {
                    context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)
                        .edit { putBoolean("onboarding_done", true) }
                    showOnboarding = false
                }
            )
        }
    }
}