package com.streakk.app

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.content.IntentCompat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.widget.Toast
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.LifecycleEventObserver
import androidx.activity.compose.BackHandler
import androidx.fragment.app.FragmentActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.animateColorAsState
import kotlin.random.Random
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.togetherWith
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.layout
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import java.text.SimpleDateFormat
import java.util.Date
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import org.json.JSONArray
import org.json.JSONObject
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.combinedClickable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.view.WindowCompat
import android.Manifest
import android.content.ActivityNotFoundException
import android.util.Log
import android.app.Activity
import androidx.core.app.ActivityCompat
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.TextRange
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.app.AlarmManager
import android.app.KeyguardManager
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Vibrator
import android.os.VibratorManager
import android.os.VibrationEffect
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.IntentFilter
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.automirrored.filled.DriveFileMove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.res.painterResource
import java.io.File
import java.time.temporal.ChronoUnit

val DarkBg = Color(0xFF121218)
val CardBg = Color(0xFF23242C)
val ChipBg = Color(0xFF33343D)
val BlueAccent = Color(0xFF3B7BF5)
val TextGray = Color(0xFF8A8B93)

val Poppins = FontFamily(
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val Tajawal = FontFamily(
    Font(R.font.tajawal_regular, FontWeight.Normal),
    Font(R.font.tajawal_medium, FontWeight.Medium),
    Font(R.font.tajawal_bold, FontWeight.Bold),
    Font(R.font.tajawal_extrabold, FontWeight.ExtraBold)
)

private val defaultTypography = Typography()
val Baloo2Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = Poppins),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = Poppins),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = Poppins),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = Poppins),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = Poppins),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = Poppins),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = Poppins),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = Poppins),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = Poppins),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = Poppins),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = Poppins),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = Poppins),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = Poppins),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = Poppins),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = Poppins)
)

enum class Screen { HOME, TASKS, PDFS, SETTINGS }

enum class HabitStatus { ACTIVE, DONE, SKIPPED }

enum class DoItAt { ANYTIME, MORNING, AFTERNOON, EVENING }
enum class EndMode { OFF, DATE, DAYS }
enum class GoalType { DURATION, COUNT }

fun isHabitActiveOn(date: LocalDate, endMode: EndMode, endDate: LocalDate?, endAfterDays: Int?, createdAt: LocalDate): Boolean {
    return when (endMode) {
        EndMode.DATE -> endDate == null || !date.isAfter(endDate)
        EndMode.DAYS -> endAfterDays == null || !date.isAfter(createdAt.plusDays(endAfterDays.toLong()))
        EndMode.OFF -> !date.isAfter(createdAt.plusDays(45))
    }
}

object LaunchErrorState {
    var message by mutableStateOf<String?>(null)
}

fun safeLaunchPermission(context: Context, launch: () -> Unit) {
    try {
        launch()
    } catch (e: Exception) {

        Log.e("PermissionDebug", "Launch failed", e)
        LaunchErrorState.message = "${e.javaClass.name}\n\n${e.message}"
    }
}

@Composable
fun PermissionsRequiredDialog(
    showNotifications: Boolean,
    showStorage: Boolean,
    onAllowNotifications: () -> Unit,
    onAllowStorage: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            color = CardBg
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .navigationBarsPadding()
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF33343D))
                            .clickable { onDismiss() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Dismiss", tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                }
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(BlueAccent.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Shield, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(32.dp))
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    "Permission Required",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.height(20.dp))

                if (showNotifications) {
                    PermissionRequestRow(
                        icon = Icons.Default.Notifications,
                        title = "NOTIFICATIONS",
                        description = "So Streakk can send your habit reminders on time",
                        onAllow = onAllowNotifications
                    )
                    if (showStorage) Spacer(Modifier.height(20.dp))
                }
                if (showStorage) {
                    PermissionRequestRow(
                        icon = Icons.Default.Folder,
                        title = "MANAGE FILES",
                        description = "So Streakk can find your PDF files and save backups",
                        onAllow = onAllowStorage
                    )
                }
            }
        }
        }
    }
}

val HabitIconMap: Map<String, androidx.compose.ui.graphics.vector.ImageVector> = HabitIconOptions.toMap()
fun iconForKey(key: String): androidx.compose.ui.graphics.vector.ImageVector = HabitIconMap[key] ?: Icons.Filled.EmojiEmotions

data class Habit(
    val id: Long,
    val name: String,
    val iconEmoji: String = "smile",
    val colorHex: Long = 0xFF3B7BF5,
    val habitDays: Set<DayOfWeek> = DayOfWeek.values().toSet(),
    val doItAt: DoItAt = DoItAt.ANYTIME,
    val goalType: GoalType = GoalType.DURATION,
    val durationMinutes: Int = 15,
    val notificationsEnabled: Boolean = false,
    val endMode: EndMode = EndMode.OFF,
    val endDate: LocalDate? = null,
    val endAfterDays: Int? = null,
    val createdAt: LocalDate = LocalDate.now(),

    val frequency: String = "Daily",
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null
)

object HabitStorage {
    private const val PREFS_NAME = "habit_prefs"
    private const val KEY_HABITS = "habits_json"

    fun save(context: Context, habits: List<Habit>) {
        val array = JSONArray()
        habits.forEach { habit ->
            val obj = JSONObject()
            obj.put("id", habit.id)
            obj.put("name", habit.name)
            obj.put("iconEmoji", habit.iconEmoji)
            obj.put("colorHex", habit.colorHex)
            obj.put("habitDays", JSONArray(habit.habitDays.map { it.name }))
            obj.put("doItAt", habit.doItAt.name)
            obj.put("goalType", habit.goalType.name)
            obj.put("durationMinutes", habit.durationMinutes)
            obj.put("notificationsEnabled", habit.notificationsEnabled)
            obj.put("endMode", habit.endMode.name)
            obj.put("endDate", habit.endDate?.toString() ?: JSONObject.NULL)
            obj.put("endAfterDays", habit.endAfterDays ?: JSONObject.NULL)
            obj.put("createdAt", habit.createdAt.toString())
            obj.put("frequency", habit.frequency)
            obj.put("startTime", habit.startTime?.toString() ?: JSONObject.NULL)
            obj.put("endTime", habit.endTime?.toString() ?: JSONObject.NULL)
            array.put(obj)
        }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_HABITS, array.toString())
            .apply()
    }

    fun load(context: Context): List<Habit> {
        val json = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_HABITS, null) ?: return emptyList()
        val array = JSONArray(json)
        return (0 until array.length()).map { i ->
            val obj = array.getJSONObject(i)
            Habit(
                id = obj.getLong("id"),
                name = obj.getString("name"),
                iconEmoji = obj.getString("iconEmoji"),
                colorHex = obj.getLong("colorHex"),
                habitDays = (0 until obj.getJSONArray("habitDays").length())
                    .map { DayOfWeek.valueOf(obj.getJSONArray("habitDays").getString(it)) }
                    .toSet(),
                doItAt = DoItAt.valueOf(obj.getString("doItAt")),
                goalType = GoalType.valueOf(obj.getString("goalType")),
                durationMinutes = obj.getInt("durationMinutes"),
                notificationsEnabled = obj.getBoolean("notificationsEnabled"),
                endMode = EndMode.valueOf(obj.getString("endMode")),
                endDate = if (obj.isNull("endDate")) null else LocalDate.parse(obj.getString("endDate")),
                endAfterDays = if (obj.isNull("endAfterDays")) null else obj.getInt("endAfterDays"),
                createdAt = if (obj.has("createdAt") && !obj.isNull("createdAt")) LocalDate.parse(obj.getString("createdAt")) else LocalDate.now(),
                frequency = obj.getString("frequency"),
                startTime = if (obj.isNull("startTime")) null else LocalTime.parse(obj.getString("startTime")),
                endTime = if (obj.isNull("endTime")) null else LocalTime.parse(obj.getString("endTime"))
            )
        }
    }

    private const val KEY_STATUS = "habit_status_json"

    fun saveStatus(context: Context, statusMap: Map<Pair<Long, LocalDate>, HabitStatus>) {
        val array = JSONArray()
        statusMap.forEach { (key, value) ->
            val obj = JSONObject()
            obj.put("habitId", key.first)
            obj.put("date", key.second.toString())
            obj.put("status", value.name)
            array.put(obj)
        }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_STATUS, array.toString())
            .apply()
    }

    fun loadStatus(context: Context): MutableMap<Pair<Long, LocalDate>, HabitStatus> {
        val json = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_STATUS, null) ?: return mutableMapOf()
        val array = JSONArray(json)
        val result = mutableMapOf<Pair<Long, LocalDate>, HabitStatus>()
        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val key = obj.getLong("habitId") to LocalDate.parse(obj.getString("date"))
            result[key] = HabitStatus.valueOf(obj.getString("status"))
        }
        return result
    }
}

object HabitReminderScheduler {
    private const val CHANNEL_ID = "habit_reminders"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Habit reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Reminders for your scheduled habits"
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun pendingIntentFor(
        context: Context, habitId: Long, name: String, iconEmoji: String, hour: Int, minute: Int, daysCsv: String,
        endMode: EndMode, endDate: LocalDate?, endAfterDays: Int?, createdAt: LocalDate
    ): PendingIntent {
        val intent = Intent(context, HabitReminderReceiver::class.java).apply {
            putExtra("habitId", habitId)
            putExtra("habitName", name)
            putExtra("iconEmoji", iconEmoji)
            putExtra("hour", hour)
            putExtra("minute", minute)
            putExtra("daysCsv", daysCsv)
            putExtra("endMode", endMode.name)
            putExtra("endDate", endDate?.toString() ?: "")
            putExtra("endAfterDays", endAfterDays ?: -1)
            putExtra("createdAt", createdAt.toString())
        }
        return PendingIntent.getBroadcast(
            context,
            habitId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun nextOccurrenceMillis(days: Set<DayOfWeek>, hour: Int, minute: Int): Long? {
        if (days.isEmpty()) return null
        val now = LocalDateTime.now()
        for (offset in 0..7) {
            val candidateDate = now.toLocalDate().plusDays(offset.toLong())
            if (candidateDate.dayOfWeek !in days) continue
            val candidate = candidateDate.atTime(hour, minute)
            if (candidate.isAfter(now)) {
                return candidate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            }
        }
        return null
    }

    fun schedule(context: Context, habit: Habit) {
        cancel(context, habit.id)
        val start = habit.startTime ?: return
        if (!habit.notificationsEnabled) return
        val triggerAt = nextOccurrenceMillis(habit.habitDays, start.hour, start.minute) ?: return
        val triggerDate = Instant.ofEpochMilli(triggerAt).atZone(ZoneId.systemDefault()).toLocalDate()
        if (!isHabitActiveOn(triggerDate, habit.endMode, habit.endDate, habit.endAfterDays, habit.createdAt)) return
        val daysCsv = habit.habitDays.joinToString(",") { it.name }
        val pendingIntent = pendingIntentFor(
            context, habit.id, habit.name, habit.iconEmoji, start.hour, start.minute, daysCsv,
            habit.endMode, habit.endDate, habit.endAfterDays, habit.createdAt
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
                alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
            } else {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
            }
        } catch (e: SecurityException) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
        }
    }

    fun cancel(context: Context, habitId: Long) {
        val intent = Intent(context, HabitReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            habitId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    fun cancelSnooze(context: Context, habitId: Long) {
        val intent = Intent(context, HabitReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            (habitId.toInt() + 500000),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    fun showNotification(context: Context, habitId: Long, habitName: String, iconEmoji: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val message = "Don't break the chain — mark \"$habitName\" as done to keep your streak alive."

        val ringIntent = Intent(context, AlarmRingActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NO_USER_ACTION
            putExtra("title", habitName)
            putExtra("message", message)
            putExtra("isHabit", true)
            putExtra("habitId", habitId)
            putExtra("iconEmoji", iconEmoji)
        }
        val fullScreenPendingIntent = PendingIntent.getActivity(
            context,
            habitId.toInt(),
            ringIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val largeIcon = ContextCompat.getDrawable(context, R.mipmap.ic_launcher)?.toBitmap()
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setColor(android.graphics.Color.parseColor("#3B7BF5"))
            .setLargeIcon(largeIcon)
            .setContentTitle(habitName)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .setOngoing(true)
            .setFullScreenIntent(fullScreenPendingIntent, true)
            .setContentIntent(fullScreenPendingIntent)
            .build()
        NotificationManagerCompat.from(context).notify(habitId.toInt(), notification)
    }

    fun snooze(context: Context, habitId: Long, habitName: String, message: String) {
        val intent = Intent(context, HabitReminderReceiver::class.java).apply {
            putExtra("habitId", habitId)
            putExtra("habitName", habitName)
            putExtra("iconEmoji", "🙂")
            putExtra("snoozeOnly", true)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context, (habitId.toInt() + 500000), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val triggerAt = System.currentTimeMillis() + 10 * 60 * 1000
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        try {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
        } catch (e: SecurityException) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
        }
    }

    fun rescheduleAll(context: Context) {
        val habits = HabitStorage.load(context)
        habits.forEach { habit ->
            if (habit.notificationsEnabled && habit.startTime != null) {
                schedule(context, habit)
            }
        }
    }
}

class HabitReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.getBooleanExtra("isTodo", false)) {
            val todoId = intent.getLongExtra("todoId", -1L)
            val todoText = intent.getStringExtra("todoText") ?: return
            if (todoId == -1L) return
            TodoReminderScheduler.createNotificationChannel(context)
            TodoReminderScheduler.showNotification(context, todoId, todoText)
            return
        }
        val habitId = intent.getLongExtra("habitId", -1L)
        if (intent.getBooleanExtra("snoozeOnly", false)) {
            val habitName = intent.getStringExtra("habitName") ?: return
            val iconEmoji = intent.getStringExtra("iconEmoji") ?: "🙂"
            HabitReminderScheduler.createNotificationChannel(context)
            HabitReminderScheduler.showNotification(context, habitId, habitName, iconEmoji)

            HabitReminderScheduler.snooze(context, habitId, habitName, "")
            return
        }
        val habitName = intent.getStringExtra("habitName") ?: return
        val iconEmoji = intent.getStringExtra("iconEmoji") ?: "🙂"
        val hour = intent.getIntExtra("hour", 0)
        val minute = intent.getIntExtra("minute", 0)
        val daysCsv = intent.getStringExtra("daysCsv") ?: return
        if (habitId == -1L) return
        val endMode = EndMode.valueOf(intent.getStringExtra("endMode") ?: EndMode.OFF.name)
        val endDateStr = intent.getStringExtra("endDate") ?: ""
        val endDate = if (endDateStr.isBlank()) null else LocalDate.parse(endDateStr)
        val endAfterDaysInt = intent.getIntExtra("endAfterDays", -1)
        val endAfterDays = if (endAfterDaysInt == -1) null else endAfterDaysInt
        val createdAt = LocalDate.parse(intent.getStringExtra("createdAt") ?: LocalDate.now().toString())

        if (!isHabitActiveOn(LocalDate.now(), endMode, endDate, endAfterDays, createdAt)) return

        HabitReminderScheduler.createNotificationChannel(context)
        HabitReminderScheduler.showNotification(context, habitId, habitName, iconEmoji)

        val days = daysCsv.split(",").filter { it.isNotBlank() }.map { DayOfWeek.valueOf(it) }.toSet()
        val fakeHabit = Habit(
            id = habitId,
            name = habitName,
            iconEmoji = iconEmoji,
            habitDays = days,
            notificationsEnabled = true,
            startTime = LocalTime.of(hour, minute),
            endMode = endMode,
            endDate = endDate,
            endAfterDays = endAfterDays,
            createdAt = createdAt
        )
        HabitReminderScheduler.schedule(context, fakeHabit)
    }
}
class AlarmRingActivity : ComponentActivity() {
    private var ringtone: Ringtone? = null
    private var vibrator: Vibrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setShowWhenLocked(true)
        setTurnScreenOn(true)
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        keyguardManager.requestDismissKeyguard(this, null)

        val title = intent.getStringExtra("title") ?: "Reminder"
        val message = intent.getStringExtra("message") ?: ""
        val habitId = intent.getLongExtra("habitId", -1L)
        val iconEmoji = intent.getStringExtra("iconEmoji") ?: "🙂"

        startRinging()

        setContent {
            MaterialTheme(colorScheme = darkColorScheme(background = DarkBg), typography = Baloo2Typography) {
                AlarmRingScreen(
                    title = title,
                    message = message,
                    onDismiss = {
                        stopRinging()
                        HabitReminderScheduler.cancelSnooze(this, habitId)
                        NotificationManagerCompat.from(this).cancel(habitId.toInt())
                        finish()
                    },
                    onSnooze = {
                        stopRinging()
                        HabitReminderScheduler.snooze(this, habitId, title, message)
                        NotificationManagerCompat.from(this).cancel(habitId.toInt())
                        finish()
                    }
                )
            }
        }
    }

    private fun startRinging() {
        val uri = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_ALARM)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        ringtone = RingtoneManager.getRingtone(this, uri)
        ringtone?.isLooping = true
        ringtone?.play()

        vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
            vibratorManager?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }
        val pattern = longArrayOf(0, 500, 500)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator?.vibrate(VibrationEffect.createWaveform(pattern, 0))
        } else {
            @Suppress("DEPRECATION")
            vibrator?.vibrate(pattern, 0)
        }
    }

    private fun stopRinging() {
        ringtone?.stop()
        vibrator?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRinging()
    }
}

@Composable
fun AlarmRingScreen(title: String, message: String, onDismiss: () -> Unit, onSnooze: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("⏰", fontSize = 56.sp)
        Spacer(Modifier.height(24.dp))
        Text(title, color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(Modifier.height(8.dp))
        Text(message, color = TextGray, fontSize = 16.sp, textAlign = TextAlign.Center)
        Spacer(Modifier.height(48.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 104.dp)
        ) {
            Button(
                onClick = onSnooze,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CardBg, contentColor = Color.White)
            ) { Text("Snooze 10 min", fontWeight = FontWeight.Bold, color = Color.White) }
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BlueAccent, contentColor = Color.White)
            ) { Text("Dismiss", fontWeight = FontWeight.Bold, color = Color.White) }
        }
    }
}
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            HabitReminderScheduler.rescheduleAll(context)
            TodoReminderScheduler.rescheduleAll(context)
        }
    }
}

object StartupPreload {
    @Volatile var habits: List<Habit>? = null
    @Volatile var habitStatus: Map<Pair<Long, LocalDate>, HabitStatus>? = null
    @Volatile var todos: List<HomeTodoItem>? = null

    fun run(context: Context) {
        habits = runCatching { HabitStorage.load(context) }.getOrNull()
        habitStatus = runCatching { HabitStorage.loadStatus(context) }.getOrNull()
        todos = runCatching {
            val cutoff = LocalDate.now().minusDays(10)
            TodoStorage.load(context).filter { !it.date.isBefore(cutoff) }
        }.getOrNull()
    }
}

class MainActivity : FragmentActivity() {
    private var startupReady = false

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { !startupReady }
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            splashScreenView.view.animate()
                .alpha(0f)
                .setDuration(300)
                .withEndAction { splashScreenView.remove() }
                .start()
        }
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setBackgroundDrawable(android.graphics.drawable.ColorDrawable(0xFF121218.toInt()))
        HabitReminderScheduler.createNotificationChannel(this)
        lifecycleScope.launch {
            withContext(Dispatchers.IO) { StartupPreload.run(applicationContext) }
            setContent {
                MaterialTheme(colorScheme = darkColorScheme(background = DarkBg), typography = Baloo2Typography) {
                    AppRoot()
                }
            }
            startupReady = true
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
const val MAX_ACTIVE_HABITS = 7

@Composable
fun TopBanner(
    visible: Boolean,
    message: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(visible) {
        if (visible) {
            delay(2500)
            onDismiss()
        }
    }
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = slideInVertically(animationSpec = tween(300, easing = FastOutSlowInEasing)) { -it } + fadeIn(tween(300)),
        exit = slideOutVertically(animationSpec = tween(250, easing = FastOutLinearInEasing)) { -it } + fadeOut(tween(250))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .background(Color(0xFF353A4A))
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(12.dp))
            Text(message, color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun BottomNavBar(current: Screen, onSelect: (Screen) -> Unit) {
    val navBarContentHeight = 56.dp
    val bottomInset = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    NavigationBar(
        containerColor = Color(0xFF1C1D24),
        modifier = Modifier.height(navBarContentHeight + bottomInset)
    ) {
        NavigationBarItem(
            selected = current == Screen.HOME,
            onClick = { onSelect(Screen.HOME) },
            icon = { NavIconWithTooltip("Tasks") { Icon(Icons.Filled.CheckCircle, contentDescription = "Tasks", modifier = Modifier.size(22.dp)) } },
            label = null,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = BlueAccent,
                unselectedIconColor = TextGray,
                unselectedTextColor = TextGray
            )
        )
        NavigationBarItem(
            selected = current == Screen.TASKS,
            onClick = { onSelect(Screen.TASKS) },
            icon = { NavIconWithTooltip("Habits") { Icon(Icons.Default.Repeat, contentDescription = "Habits", modifier = Modifier.size(24.dp)) } },
            label = null,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = BlueAccent,
                unselectedIconColor = TextGray,
                unselectedTextColor = TextGray
            )
        )
        NavigationBarItem(
            selected = current == Screen.PDFS,
            onClick = { onSelect(Screen.PDFS) },
            icon = { NavIconWithTooltip("PDFs") { Icon(Icons.Filled.Description, contentDescription = "PDFs", modifier = Modifier.size(22.dp)) } },
            label = null,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = BlueAccent,
                unselectedIconColor = TextGray,
                unselectedTextColor = TextGray
            )
        )
    }
}

data class HomeTodoItem(
    val id: Long,
    val text: String,
    val date: LocalDate,
    val reminderTime: LocalTime? = null,
    val completed: Boolean = false
)

fun calculateTodoDayProgress(date: LocalDate, todos: List<HomeTodoItem>): Pair<Int, Int> {
    val dayTodos = todos.filter { it.date == date }
    val totalCount = dayTodos.size
    if (totalCount == 0) return 0 to 0
    val completedCount = dayTodos.count { it.completed }
    return completedCount to totalCount
}

@Composable
fun HomeTodoScreen(bottomContentPadding: Dp = 0.dp, firstDayOfWeek: DayOfWeek = DayOfWeek.MONDAY, todos: SnapshotStateList<HomeTodoItem>, soundOnComplete: Boolean = true, onTaskDeleted: () -> Unit = {}, onSettingsClick: () -> Unit = {}) {
    var showAddSheet by remember { mutableStateOf(false) }
    var editingTodo by remember { mutableStateOf<HomeTodoItem?>(null) }
    var menuOpenForTodoId by remember { mutableStateOf<Long?>(null) }
    val context = LocalContext.current
    var lastTapHintAt by remember { mutableStateOf(0L) }
    val pendingDoneIds = remember { mutableStateListOf<Long>() }
    val leavingIds = remember { mutableStateListOf<Long>() }
    val undoingIds = remember { mutableStateListOf<Long>() }

    val density = LocalDensity.current
    var headerHeight by remember { mutableStateOf(0.dp) }
    val today = remember { LocalDate.now() }
    val configuration = LocalConfiguration.current
    val currentLocale = remember(configuration) { configuration.locales[0] }
    val currentWeekMonday = remember(today, firstDayOfWeek) { weekStartFor(today, firstDayOfWeek) }
    val centerPage = 5000
    val pagerState = rememberPagerState(initialPage = centerPage) { centerPage * 2 }
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var scrollToTopTrigger by remember { mutableIntStateOf(0) }
    LaunchedEffect(scrollToTopTrigger) {
        if (scrollToTopTrigger > 0) listState.animateScrollToItem(0)
    }

    fun completeTodoWithAnimation(todoId: Long) {
        if (todoId in pendingDoneIds || todoId in undoingIds) return
        pendingDoneIds.add(todoId)
        if (soundOnComplete) CompletionSoundPlayer.play()
        coroutineScope.launch {
            delay(750)
            leavingIds.add(todoId)
            delay(220)
            val index = todos.indexOfFirst { it.id == todoId }
            if (index != -1) todos[index] = todos[index].copy(completed = true)
            pendingDoneIds.remove(todoId)
            leavingIds.remove(todoId)
        }
    }

    fun undoTodoWithAnimation(todoId: Long) {
        if (todoId in pendingDoneIds || todoId in undoingIds) return
        undoingIds.add(todoId)
        coroutineScope.launch {
            delay(350)
            leavingIds.add(todoId)
            delay(220)
            val index = todos.indexOfFirst { it.id == todoId }
            if (index != -1) todos[index] = todos[index].copy(completed = false)
            undoingIds.remove(todoId)
            leavingIds.remove(todoId)
        }
    }

    val weekOffset = pagerState.currentPage - centerPage
    val monday = remember(weekOffset) { currentWeekMonday.plusWeeks(weekOffset.toLong()) }

    var selectedDate by rememberSaveable { mutableStateOf(today) }

    fun changeDay(delta: Int) {
        val newDate = selectedDate.plusDays(delta.toLong())
        selectedDate = newDate
        val targetMonday = weekStartFor(newDate, firstDayOfWeek)
        val weeksBetween = java.time.temporal.ChronoUnit.WEEKS.between(currentWeekMonday, targetMonday)
        val targetPage = (centerPage + weeksBetween).toInt()
        if (targetPage != pagerState.currentPage) {
            coroutineScope.launch { pagerState.animateScrollToPage(targetPage) }
        }
    }
    var showMonthPicker by remember { mutableStateOf(false) }

    val titleText = when (selectedDate) {
        today -> "TODAY"
        today.minusDays(1) -> "YESTERDAY"
        today.plusDays(1) -> "TOMORROW"
        else -> selectedDate.format(DateTimeFormatter.ofPattern("MMM d", currentLocale)).uppercase()
    }
    val subtitleText = when {
        selectedDate.isBefore(today) -> {
            val (completed, total) = calculateTodoDayProgress(selectedDate, todos)
            if (total == 0) "Nothing Scheduled" else "${(completed * 100) / total}% Finished"
        }
        selectedDate == today ->
            selectedDate.format(DateTimeFormatter.ofPattern("MMM d"))
        else -> selectedDate.format(DateTimeFormatter.ofPattern("EEEE", currentLocale))
    }

    Box(modifier = Modifier.fillMaxSize().background(DarkBg)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = (headerHeight - 90.dp).coerceAtLeast(0.dp))
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(CardBg)
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .widthIn(max = 600.dp)
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        headerHeight = with(density) { coordinates.size.height.toDp() }
                    }
            ) {
                Spacer(Modifier.height(20.dp))

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(titleText, color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                        IconButton(onClick = onSettingsClick) {
                            Icon(
                                Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                    Text(
                        subtitleText,
                        color = TextGray,
                        fontSize = 16.sp,
                        fontFamily = Tajawal,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { showMonthPicker = true }
                    )
                }

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val weekdayLabels = (0..6).map { offset ->
                        DayOfWeek.of(((firstDayOfWeek.value - 1 + offset) % 7) + 1)
                            .getDisplayName(TextStyle.SHORT, currentLocale).uppercase()
                    }
                    weekdayLabels.forEachIndexed { index, label ->
                        Text(
                            label,
                            color = TextGray,
                            fontSize = 13.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(Modifier.height(6.dp))

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    val pageMonday = remember(page, currentWeekMonday) { currentWeekMonday.plusWeeks((page - centerPage).toLong()) }
                    val pageDays = remember(pageMonday) { (0..6).map { pageMonday.plusDays(it.toLong()) } }
                    val pageProgress by remember(pageDays) {
                        derivedStateOf {
                            pageDays.map { d -> if (d.isBefore(today)) calculateTodoDayProgress(d, todos) else 0 to 0 }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        pageDays.forEachIndexed { index, day ->
                            val isSelected = day == selectedDate
                            val isTodayDate = day == today
                            val isPastDate = day.isBefore(today)
                            val (dayCompleted, dayTotal) = pageProgress[index]
                            val dayProgressFraction = if (dayTotal == 0) 0f else dayCompleted.toFloat() / dayTotal.toFloat()
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier.size(40.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (isPastDate && dayTotal > 0) {
                                        Canvas(modifier = Modifier.size(34.dp)) {
                                            val strokeWidthPx = 4.5.dp.toPx()
                                            drawArc(
                                                color = Color(0xFF3A3B44),
                                                startAngle = -90f,
                                                sweepAngle = 360f,
                                                useCenter = false,
                                                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
                                            )
                                            drawArc(
                                                color = BlueAccent,
                                                startAngle = -90f,
                                                sweepAngle = 360f * dayProgressFraction,
                                                useCenter = false,
                                                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
                                            )
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .size(38.5.dp)
                                            .clip(CircleShape)
                                            .background(if (isTodayDate) BlueAccent else Color.Transparent)
                                            .clickable { selectedDate = day },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            "${day.dayOfMonth}",
                                            color = if (isSelected || isTodayDate) Color.White else TextGray,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        )
                                    }
                                }
                                if (isSelected) {
                                    Spacer(Modifier.height(4.dp))
                                    Box(
                                        Modifier
                                            .width(24.dp)
                                            .height(3.dp)
                                            .clip(RoundedCornerShape(2.dp))
                                            .background(BlueAccent)
                                    )
                                } else {
                                    Spacer(Modifier.height(7.dp))
                                }
                            }
                        }
                    }
                }

                if (showMonthPicker) {
                    MonthPickerDialog(
                        year = today.year,
                        onDismiss = { showMonthPicker = false },
                        onMonthSelected = { month ->
                            val firstOfMonth = LocalDate.of(today.year, month, 1)
                            val targetMonday = weekStartFor(firstOfMonth, firstDayOfWeek)
                            val weeksBetween = ChronoUnit.WEEKS.between(currentWeekMonday, targetMonday)
                            coroutineScope.launch {
                                pagerState.scrollToPage((centerPage + weeksBetween).toInt())
                            }
                            selectedDate = firstOfMonth
                            showMonthPicker = false
                        }
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .pointerInput(Unit) {
                        var totalDrag = 0f
                        detectHorizontalDragGestures(
                            onDragStart = { totalDrag = 0f },
                            onHorizontalDrag = { change, dragAmount ->
                                change.consume()
                                totalDrag += dragAmount
                            },
                            onDragEnd = {
                                val threshold = 80f
                                when {
                                    totalDrag <= -threshold -> changeDay(1)
                                    totalDrag >= threshold -> changeDay(-1)
                                }
                                totalDrag = 0f
                            }
                        )
                    }
            ) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = bottomContentPadding + 100.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (todos.none { it.date == selectedDate }) {
                    item(key = "empty_state") {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 48.dp, bottom = 48.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(ChipBg),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Description,
                                    contentDescription = null,
                                    tint = BlueAccent,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Spacer(Modifier.height(20.dp))
                            Text(
                                "Nothing added for this day",
                                color = TextGray,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                items(todos.filter { it.date == selectedDate }.sortedBy { it.completed }, key = { "${it.id}-${it.completed}" }) { todo ->
                    val isVisuallyDone = (todo.completed && todo.id !in undoingIds) || todo.id in pendingDoneIds
                    val todoTextColor by animateColorAsState(
                        targetValue = if (isVisuallyDone) TextGray else Color.White,
                        animationSpec = tween(350),
                        label = "todoTextColor"
                    )
                    val leaveAlpha by animateFloatAsState(
                        targetValue = if (todo.id in leavingIds) 0f else 1f,
                        animationSpec = tween(200),
                        label = "todoLeaveAlpha"
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItem(
                                fadeInSpec = tween(300),
                                placementSpec = tween(350, easing = FastOutSlowInEasing)
                            )
                            .alpha(leaveAlpha)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(14.dp), ambientColor = Color.Black, spotColor = Color.Black)
                                .clip(RoundedCornerShape(14.dp))
                                .background(ChipBg)
                                .combinedClickable(
                                    onClick = {
                                        val now = System.currentTimeMillis()
                                        if (menuOpenForTodoId == null && now - lastTapHintAt > 2000L) {
                                            val prefs = context.getSharedPreferences("streakk_hints", Context.MODE_PRIVATE)
                                            val shown = prefs.getInt("todo_hold_hint_count", 0)
                                            if (shown < 3) {
                                                prefs.edit().putInt("todo_hold_hint_count", shown + 1).apply()
                                                lastTapHintAt = now
                                                Toast.makeText(
                                                    context,
                                                    "Hold a task to edit, delete or move it",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    },
                                    onLongClick = {
                                        menuOpenForTodoId = todo.id
                                        context.getSharedPreferences("streakk_hints", Context.MODE_PRIVATE)
                                            .edit().putInt("todo_hold_hint_count", 3).apply()
                                    }
                                )
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val checkboxScale = remember(todo.id) { Animatable(1f) }
                            LaunchedEffect(todo.id in pendingDoneIds) {
                                if (todo.id in pendingDoneIds) {
                                    checkboxScale.snapTo(0.55f)
                                    checkboxScale.animateTo(
                                        1f,
                                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium)
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .scale(checkboxScale.value)
                                    .clip(CircleShape)
                                    .background(if (isVisuallyDone) BlueAccent else Color.Transparent)
                                    .border(width = 2.dp, color = if (isVisuallyDone) BlueAccent else TextGray, shape = CircleShape)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) {
                                        if (todo.completed) {
                                            undoTodoWithAnimation(todo.id)
                                        } else {
                                            completeTodoWithAnimation(todo.id)
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (isVisuallyDone) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                                }
                            }
                            Spacer(Modifier.width(12.dp))
                            AnimatedContent(
                                targetState = isVisuallyDone,
                                transitionSpec = { fadeIn(tween(250)) togetherWith fadeOut(tween(250)) },
                                label = "todoStrikeThrough"
                            ) { isDone ->
                                Text(
                                    todo.text,
                                    color = todoTextColor,
                                    fontSize = 16.sp,
                                    textDecoration = if (isDone) TextDecoration.LineThrough else null
                                )
                            }
                        }
                        if (todo.id in pendingDoneIds) {
                            CardPopperBurst(trigger = todo.id)
                        }
                    }
                    if (menuOpenForTodoId == todo.id) {
                        TodoOptionsSheet(
                            todo = todo,
                            onDismiss = { menuOpenForTodoId = null },
                            onEdit = {
                                editingTodo = todo
                                showAddSheet = true
                            },
                            onDelete = {
                                todos.removeAll { it.id == todo.id }
                                onTaskDeleted()
                            },
                            onShift = {
                                val index = todos.indexOfFirst { it.id == todo.id }
                                if (index != -1) todos[index] = todo.copy(date = todo.date.plusDays(1))
                            },
                            onDone = {
                                completeTodoWithAnimation(todo.id)
                            },
                            onUndo = {
                                undoTodoWithAnimation(todo.id)
                            }
                        )
                    }
                }
            }
            }
        }

        if (!selectedDate.isBefore(today)) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 20.dp, bottom = 20.dp + bottomContentPadding)
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(BlueAccent)
                    .clickable { showAddSheet = true },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add to-do", tint = Color.White, modifier = Modifier.size(28.dp))
            }
        }

    }

    AddSheetHost(isVisible = { showAddSheet }) {
        QuickAddTodoSheet(
            initialText = editingTodo?.text ?: "",
            initialReminderTime = editingTodo?.reminderTime,
            onDismiss = { showAddSheet = false; editingTodo = null },
            onSave = { text, reminderTime ->
                val currentEditing = editingTodo
                if (currentEditing != null) {
                    val index = todos.indexOfFirst { it.id == currentEditing.id }
                    if (index != -1) {
                        val reminderChanged = reminderTime != null && reminderTime != currentEditing.reminderTime
                        val newDate = if (reminderChanged && !currentEditing.completed && currentEditing.date.isBefore(LocalDate.now())) {
                            LocalDate.now()
                        } else {
                            currentEditing.date
                        }
                        todos[index] = currentEditing.copy(text = text, reminderTime = reminderTime, date = newDate)
                    }
                } else {
                    todos.add(0, HomeTodoItem(id = System.currentTimeMillis(), text = text, date = selectedDate, reminderTime = reminderTime))
                    scrollToTopTrigger++
                }
                showAddSheet = false
                editingTodo = null
            }
        )
    }}

data class ConfettiParticle(
    val angleDeg: Float,
    val speed: Float,
    val color: Color,
    val size: Float,
    val delayMs: Int,
    val isCircle: Boolean
)

@Composable
fun BoxScope.CardPopperBurst(trigger: Long, durationMs: Int = 800, originXDp: Dp = 27.dp) {
    val popperColors = listOf(
        Color(0xFFFFC542), Color(0xFF3B7BF5), Color(0xFFE55353),
        Color(0xFF4CD964), Color(0xFFFF6FB2), Color(0xFF8E6CFF), Color(0xFF2EE6D0)
    )
    val particles = remember(trigger) {
        List(42) {
            ConfettiParticle(
                angleDeg = Random.nextFloat() * 360f,
                speed = Random.nextFloat() * 0.5f + 0.75f,
                color = popperColors.random(),
                size = Random.nextFloat() * 5f + 4f,
                delayMs = Random.nextInt(0, 90),
                isCircle = Random.nextBoolean()
            )
        }
    }
    val progress = remember(trigger) { Animatable(0f) }
    LaunchedEffect(trigger) {
        progress.snapTo(0f)
        progress.animateTo(1f, animationSpec = tween(durationMs, easing = FastOutSlowInEasing))
    }
    Canvas(modifier = Modifier.matchParentSize().zIndex(1f)) {
        val originX = originXDp.toPx()
        val originY = size.height / 2f

        val ringT = (progress.value / 0.4f).coerceIn(0f, 1f)
        if (ringT > 0f && ringT < 1f) {
            drawCircle(
                color = Color.White.copy(alpha = (1f - ringT) * 0.5f),
                radius = 10f + ringT * 70f,
                center = Offset(originX, originY),
                style = Stroke(width = 3f)
            )
        }

        particles.forEach { p ->
            val t = (((progress.value * durationMs) - p.delayMs) / durationMs.toFloat()).coerceIn(0f, 1f)
            if (t > 0f) {
                val angleRad = Math.toRadians(p.angleDeg.toDouble())
                val distance = 150f * p.speed * t
                val gravity = 50f * t * t
                val x = originX + (cos(angleRad) * distance).toFloat()
                val y = originY + (sin(angleRad) * distance).toFloat() + gravity
                val alpha = (1f - t).coerceIn(0f, 1f)
                val particleSize = p.size * (1f - t * 0.25f)
                if (p.isCircle) {
                    drawCircle(color = p.color.copy(alpha = alpha), radius = particleSize / 2f, center = Offset(x, y))
                } else {
                    rotate(degrees = p.angleDeg + t * 280f, pivot = Offset(x, y)) {
                        drawRect(
                            color = p.color.copy(alpha = alpha),
                            topLeft = Offset(x - particleSize / 2f, y - particleSize / 2f),
                            size = Size(particleSize, particleSize * 1.5f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TodoOptionsSheet(
    todo: HomeTodoItem,
    onDismiss: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onShift: () -> Unit,
    onDone: () -> Unit,
    onUndo: () -> Unit
) {
    val todoSheetNestedScrollConnection = remember {
        object : NestedScrollConnection {
            var totalOverscroll = 0f
            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                if (available.y > 0f) {
                    totalOverscroll += available.y
                    if (totalOverscroll > 300f) {
                        onDismiss()
                    }
                } else {
                    totalOverscroll = 0f
                }
                return Offset.Zero
            }
        }
    }
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onDismiss() },
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .nestedScroll(todoSheetNestedScrollConnection)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {  },
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = CardBg
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .heightIn(max = 420.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        todo.text,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(Modifier.height(20.dp))
                    HorizontalDivider(color = Color(0xFF3A3B44))
                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        PdfSheetQuickAction(Icons.Default.Delete, "Delete", tint = Color(0xFFE55353)) { onDismiss(); onDelete() }
                        PdfSheetQuickAction(Icons.AutoMirrored.Filled.DriveFileMove, "Shift", enabled = !todo.completed) { onDismiss(); onShift() }
                        if (todo.completed) {
                            PdfSheetQuickAction(Icons.AutoMirrored.Filled.Undo, "Undo", tint = Color(0xFFFFC542)) { onDismiss(); onUndo() }
                        } else {
                            PdfSheetQuickAction(Icons.Default.Check, "Done", tint = Color(0xFF4CD964)) { onDismiss(); onDone() }
                        }
                        PdfSheetQuickAction(Icons.Default.Edit, "Edit") { onDismiss(); onEdit() }
                    }
                }
            }
        }
    }
}

@Composable
fun AddSheetHost(isVisible: () -> Boolean, content: @Composable () -> Unit) {
    if (isVisible()) {
        content()
    }
}

@Composable
fun SettingsCard(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBg),
        content = content
    )
}

@Composable
fun SettingsRow(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    iconBg: Color = Color.Transparent,
    subtitle: String? = null,
    trailingText: String? = null,
    titleColor: Color = Color.White,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.width(14.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = titleColor, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            if (subtitle != null) {
                Spacer(Modifier.height(2.dp))
                Text(subtitle, color = TextGray, fontSize = 13.sp)
            }
        }
        if (trailingText != null) {
            Text(
                trailingText,
                color = TextGray,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.widthIn(max = 140.dp)
            )
            Spacer(Modifier.width(6.dp))
        }
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGray)
    }
}
@Composable
fun PrivacyBadgeCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBg)
            .border(1.dp, Color(0xFF2A2B33), RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BlueAccent.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Shield, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(19.dp))
            }
            Spacer(Modifier.width(14.dp))
            Column {
                Text(
                    "PRIVACY FIRST",
                    color = BlueAccent,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Spacer(Modifier.height(3.dp))
                Text("Your data stays on this device", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Spacer(Modifier.height(18.dp))
        HorizontalDivider(color = Color(0xFF2A2B33))
        Spacer(Modifier.height(16.dp))
        PrivacyPoint("No internet access requested")
        Spacer(Modifier.height(12.dp))
        PrivacyPoint("No account or sign-in required")
        Spacer(Modifier.height(12.dp))
        PrivacyPoint("No ads or third-party tracking")
        Spacer(Modifier.height(12.dp))
        PrivacyPoint("All data is stored locally on this device")
    }
}

@Composable
fun PrivacyPoint(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(BlueAccent.copy(alpha = 0.14f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Check, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(12.dp))
        }
        Spacer(Modifier.width(10.dp))
        Text(text, color = TextGray, fontSize = 13.5.sp, lineHeight = 18.sp)
    }
}

@Composable
fun SettingsScreen(
    onDeleteAllData: () -> Unit = {},
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
    autoBackupEnabled: Boolean = true,
    onAutoBackupEnabledChange: (Boolean) -> Unit = {}
) {
    var showGeneralSettings by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var showDeleteConfirm by remember { mutableStateOf(false) }
    var showPrivacyPolicy by remember { mutableStateOf(false) }
    var showPermissions by remember { mutableStateOf(false) }
    var showBackup by remember { mutableStateOf(false) }

    AnimatedContent(
        targetState = showGeneralSettings,
        transitionSpec = {
            if (targetState) {
                (slideInHorizontally(animationSpec = tween(320)) { fullWidth -> fullWidth } + fadeIn(animationSpec = tween(320)))
                    .togetherWith(slideOutHorizontally(animationSpec = tween(320)) { fullWidth -> -fullWidth / 4 } + fadeOut(animationSpec = tween(320)))
            } else {
                (slideInHorizontally(animationSpec = tween(320)) { fullWidth -> -fullWidth / 4 } + fadeIn(animationSpec = tween(320)))
                    .togetherWith(slideOutHorizontally(animationSpec = tween(320)) { fullWidth -> fullWidth } + fadeOut(animationSpec = tween(320)))
            }
        },
        label = "generalSettingsTransition"
    ) { isGeneralSettings ->
    if (isGeneralSettings) {
        GeneralSettingsScreen(
            onBack = { showGeneralSettings = false },
            bottomContentPadding = bottomContentPadding,
            firstDayOfWeek = firstDayOfWeek,
            onFirstDayOfWeekChange = onFirstDayOfWeekChange,
            showStreakCount = showStreakCount,
            onShowStreakCountChange = onShowStreakCountChange,
            soundOnComplete = soundOnComplete,
            onSoundOnCompleteChange = onSoundOnCompleteChange,
            defaultSortOption = defaultSortOption,
            onDefaultSortOptionChange = onDefaultSortOptionChange,
            autoScanEnabled = autoScanEnabled,
            onAutoScanEnabledChange = onAutoScanEnabledChange,
            preferredPdfPackage = preferredPdfPackage,
            onPreferredPdfPackageChange = onPreferredPdfPackageChange,
            fileSizeUnit = fileSizeUnit,
            onFileSizeUnitChange = onFileSizeUnitChange,
            appLockEnabled = appLockEnabled,
            onAppLockEnabledChange = onAppLockEnabledChange,
            autoLockTimeout = autoLockTimeout,
            onAutoLockTimeoutChange = onAutoLockTimeoutChange
        )
    } else {
        AnimatedContent(
            targetState = showPrivacyPolicy,
            transitionSpec = {
                if (targetState) {
                    (slideInHorizontally(animationSpec = tween(320)) { fullWidth -> fullWidth } + fadeIn(animationSpec = tween(320)))
                        .togetherWith(slideOutHorizontally(animationSpec = tween(320)) { fullWidth -> -fullWidth / 4 } + fadeOut(animationSpec = tween(320)))
                } else {
                    (slideInHorizontally(animationSpec = tween(320)) { fullWidth -> -fullWidth / 4 } + fadeIn(animationSpec = tween(320)))
                        .togetherWith(slideOutHorizontally(animationSpec = tween(320)) { fullWidth -> fullWidth } + fadeOut(animationSpec = tween(320)))
                }
            },
            label = "privacyPolicyTransition"
        ) { isPrivacy ->
        if (isPrivacy) {
            PrivacyPolicyScreen(onBack = { showPrivacyPolicy = false }, bottomContentPadding = bottomContentPadding)
        } else {
        AnimatedContent(
            targetState = showPermissions,
            transitionSpec = {
                if (targetState) {
                    (slideInHorizontally(animationSpec = tween(320)) { fullWidth -> fullWidth } + fadeIn(animationSpec = tween(320)))
                        .togetherWith(slideOutHorizontally(animationSpec = tween(320)) { fullWidth -> -fullWidth / 4 } + fadeOut(animationSpec = tween(320)))
                } else {
                    (slideInHorizontally(animationSpec = tween(320)) { fullWidth -> -fullWidth / 4 } + fadeIn(animationSpec = tween(320)))
                        .togetherWith(slideOutHorizontally(animationSpec = tween(320)) { fullWidth -> fullWidth } + fadeOut(animationSpec = tween(320)))
                }
            },
            label = "permissionsTransition"
        ) { isPermissions ->
        if (isPermissions) {
            PermissionsScreen(onBack = { showPermissions = false }, bottomContentPadding = bottomContentPadding)
        } else {
        AnimatedContent(
            targetState = showBackup,
            transitionSpec = {
                if (targetState) {
                    (slideInHorizontally(animationSpec = tween(320)) { fullWidth -> fullWidth } + fadeIn(animationSpec = tween(320)))
                        .togetherWith(slideOutHorizontally(animationSpec = tween(320)) { fullWidth -> -fullWidth / 4 } + fadeOut(animationSpec = tween(320)))
                } else {
                    (slideInHorizontally(animationSpec = tween(320)) { fullWidth -> -fullWidth / 4 } + fadeIn(animationSpec = tween(320)))
                        .togetherWith(slideOutHorizontally(animationSpec = tween(320)) { fullWidth -> fullWidth } + fadeOut(animationSpec = tween(320)))
                }
            },
            label = "backupTransition"
        ) { isBackup ->
        if (isBackup) {
            BackupSettingsScreen(
                onBack = { showBackup = false },
                bottomContentPadding = bottomContentPadding,
                autoBackupEnabled = autoBackupEnabled,
                onAutoBackupEnabledChange = onAutoBackupEnabledChange
            )
        } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBg)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth().height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("SETTINGS", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            }
            Spacer(Modifier.height(20.dp))

            PrivacyBadgeCard()

            Spacer(Modifier.height(16.dp))

            SettingsCard {
                SettingsRow(
                    title = "Backup & restore",
                    icon = Icons.Default.Save,
                    iconBg = Color(0xFF3B7BF5),
                    onClick = { showBackup = true }
                )
                HorizontalDivider(color = Color(0xFF3A3B44))
                SettingsRow(
                    title = "Manage Permissions",
                    icon = Icons.Default.Alarm,
                    iconBg = Color(0xFF3B7BF5),
                    onClick = { showPermissions = true }
                )
                HorizontalDivider(color = Color(0xFF3A3B44))
                SettingsRow(
                    title = "General settings",
                    icon = Icons.Default.Settings,
                    iconBg = Color(0xFF3B7BF5),
                    onClick = { showGeneralSettings = true }
                )
            }

            Spacer(Modifier.height(16.dp))

            SettingsCard {
                SettingsRow(
                    title = "Share with friends",
                    icon = Icons.Default.Share,
                    iconBg = Color(0xFF3B7BF5),
                    onClick = {

                        val shareText = "I've been using Streakk to track my daily habits and to-do tasks. It's simple, keeps everything on your device, and doesn't need an account. Thought you might find it useful:\nhttps://github.com//AryanV4R/Streakk"
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, shareText)
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                    }
                )
                HorizontalDivider(color = Color(0xFF3A3B44))
                SettingsRow(
                    title = "Privacy Policy",
                    icon = Icons.Default.Info,
                    iconBg = Color(0xFF3B7BF5),
                    onClick = { showPrivacyPolicy = true }
                )
                HorizontalDivider(color = Color(0xFF3A3B44))
                SettingsRow(
                    title = "Feedback",
                    icon = Icons.Default.Edit,
                    iconBg = Color(0xFF3B7BF5),
                    onClick = {
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:idabhinavx@protonmail.com?subject=" + Uri.encode("Feedback - Streakk App"))
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("idabhinavx@protonmail.com"))
                            putExtra(Intent.EXTRA_SUBJECT, "Feedback - Streakk App")
                            setPackage("com.google.android.gm")
                        }
                        try {
                            context.startActivity(emailIntent)
                        } catch (e: ActivityNotFoundException) {

                            emailIntent.setPackage(null)
                            try {
                                context.startActivity(emailIntent)
                            } catch (e2: ActivityNotFoundException) {  }
                        }
                    }
                )
                HorizontalDivider(color = Color(0xFF3A3B44))
                SettingsRow(
                    title = "Star on GitHub",
                    icon = Icons.Default.Star,
                    iconBg = Color(0xFF3B7BF5),
                    onClick = {

                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/AryanV4R/Streakk")
                            )
                        )
                    }
                )
                HorizontalDivider(color = Color(0xFF3A3B44))
                SettingsRow(
                    title = "Delete all data",
                    icon = Icons.Default.Delete,
                    iconBg = Color(0xFFE05260),
                    titleColor = Color(0xFFE05260),
                    onClick = { showDeleteConfirm = true }
                )
            }

            Spacer(Modifier.height(20.dp))
            Text(
                "Version 1.1.0",
                color = TextGray,
                fontSize = 13.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(20.dp + bottomContentPadding))
        }

        if (showDeleteConfirm) {
            AlertDialog(
                onDismissRequest = { showDeleteConfirm = false },
                title = { Text("Delete all data?") },
                text = {
                    Text("This will permanently delete all your habits and to-do tasks from this app, including any auto backups saved on this phone. Your PDFs will not be affected. This cannot be undone.")
                },
                confirmButton = {
                    TextButton(onClick = {
                        onDeleteAllData()
                        showDeleteConfirm = false
                    }) {
                        Text("Delete", color = Color(0xFFE05260), fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteConfirm = false }) { Text("Cancel") }
                },
                containerColor = CardBg,
                titleContentColor = Color.White,
                textContentColor = TextGray
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
}
@Composable
fun PermissionsScreen(onBack: () -> Unit, bottomContentPadding: Dp = 0.dp) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    fun checkNotifications() = Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
        ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
    fun checkExactAlarm() = Build.VERSION.SDK_INT < Build.VERSION_CODES.S ||
        (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager).canScheduleExactAlarms()
    fun checkFullScreenIntent() = Build.VERSION.SDK_INT < Build.VERSION_CODES.UPSIDE_DOWN_CAKE ||
        (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).canUseFullScreenIntent()
    fun checkBatteryOptimization() =
        (context.getSystemService(Context.POWER_SERVICE) as PowerManager).isIgnoringBatteryOptimizations(context.packageName)

    var notificationsGranted by remember { mutableStateOf(checkNotifications()) }
    var exactAlarmGranted by remember { mutableStateOf(checkExactAlarm()) }
    var fullScreenIntentGranted by remember { mutableStateOf(checkFullScreenIntent()) }
    var batteryOptimizationGranted by remember { mutableStateOf(checkBatteryOptimization()) }
    var storageGranted by remember { mutableStateOf(hasStorageAccess(context)) }

    fun refreshAll() {
        notificationsGranted = checkNotifications()
        exactAlarmGranted = checkExactAlarm()
        fullScreenIntentGranted = checkFullScreenIntent()
        batteryOptimizationGranted = checkBatteryOptimization()
        storageGranted = hasStorageAccess(context)
    }

    val coroutineScope = rememberCoroutineScope()
    fun refreshAllDelayed() {
        refreshAll()

        coroutineScope.launch {
            delay(400)
            refreshAll()
        }
    }

    val settingsResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { refreshAllDelayed() }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                refreshAll()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
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
            Text("Manage Permissions", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(8.dp))
        Text(
            "These permissions are required to show habit reminders and display PDFs.",
            color = TextGray,
            fontSize = 13.sp
        )
        Spacer(Modifier.height(20.dp))

        SettingsCard {
            PermissionToggleRow(
                title = "Notifications",
                subtitle = "Required to show habit reminders",
                checked = notificationsGranted,
                onToggle = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        if (!notificationsGranted) {
                            val prefs = context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)
                            val alreadyRequested = prefs.getBoolean("notif_permission_requested", false)
                            if (!alreadyRequested) {
                                prefs.edit().putBoolean("notif_permission_requested", true).apply()

                                val activity = context as? Activity
                                if (activity != null) {
                                    ActivityCompat.requestPermissions(
                                        activity,
                                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                                        9001
                                    )
                                }
                            } else {
                                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                    .putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                                context.startActivity(intent)
                            }
                        } else {
                            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                .putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                            context.startActivity(intent)
                        }
                    }
                }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            PermissionToggleRow(
                title = "Alarms & reminders",
                subtitle = "Ensures reminders fire at the exact scheduled time",
                checked = exactAlarmGranted,
                onToggle = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                            .apply { data = Uri.parse("package:${context.packageName}") }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        safeLaunchPermission(context) { settingsResultLauncher.launch(intent) }
                    }
                }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            PermissionToggleRow(
                title = "Full-screen alerts",
                subtitle = "Lets reminders ring on top of the lock screen, like a real alarm",
                checked = fullScreenIntentGranted,
                onToggle = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                        val intent = Intent(Settings.ACTION_MANAGE_APP_USE_FULL_SCREEN_INTENT)
                            .apply { data = Uri.parse("package:${context.packageName}") }
                        safeLaunchPermission(context) { settingsResultLauncher.launch(intent) }
                    }
                }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            PermissionToggleRow(
                title = "Storage access",
                subtitle = "Required to find PDF files and save backups on your device",
                checked = storageGranted,
                onToggle = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        val intent = Intent(
                            Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                            Uri.parse("package:${context.packageName}")
                        )
                        context.startActivity(intent)
                    } else {
                        val activity = context as? Activity
                        if (activity != null) {
                            ActivityCompat.requestPermissions(
                                activity,
                                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                                9002
                            )
                        }
                    }
                }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            SettingsRow(
                title = "Battery optimization",
                subtitle = "Prevents the system from delaying or killing reminders in the background",
                onClick = {
                    val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                        .apply { data = Uri.parse("package:${context.packageName}") }
                    safeLaunchPermission(context) { settingsResultLauncher.launch(intent) }
                }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            SettingsRow(
                title = "Autostart / background",
                subtitle = "Required on devices like MIUI/Xiaomi for reliable reminders",
                onClick = {
                    try {
                        context.startActivity(autostartSettingsIntent(context))
                    } catch (e: Exception) {

                    }
                }
            )
        }
    }
}

@Composable
fun PermissionToggleRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(2.dp))
            Text(subtitle, color = TextGray, fontSize = 13.sp)
        }
        Switch(
            checked = checked,
            onCheckedChange = { onToggle(checked) },
            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = BlueAccent)
        )
    }
}

fun autostartSettingsIntent(context: Context): Intent {
    val manufacturer = Build.MANUFACTURER.lowercase()
    return when {
        manufacturer.contains("xiaomi") -> Intent().setComponent(
            android.content.ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")
        )
        manufacturer.contains("oppo") -> Intent().setComponent(
            android.content.ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")
        )
        manufacturer.contains("vivo") -> Intent().setComponent(
            android.content.ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")
        )
        manufacturer.contains("huawei") -> Intent().setComponent(
            android.content.ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity")
        )
        else -> Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.parse("package:${context.packageName}")
        }
    }
}

@Composable
fun FirstDayOfWeekDialog(current: DayOfWeek, onDismiss: () -> Unit, onSelect: (DayOfWeek) -> Unit) {
    val configuration = LocalConfiguration.current
    val currentLocale = remember(configuration) { configuration.locales[0] }
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp), color = CardBg) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("First day of week", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                listOf(DayOfWeek.MONDAY, DayOfWeek.SUNDAY).forEach { day ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(day) }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            day.getDisplayName(TextStyle.FULL, currentLocale),
                            color = Color.White,
                            fontSize = 16.sp
                        )
                        if (day == current) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = BlueAccent)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun <T> SingleChoiceDialog(
    title: String,
    options: List<T>,
    optionLabel: (T) -> String,
    current: T,
    onDismiss: () -> Unit,
    onSelect: (T) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp), color = CardBg) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(option) }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(optionLabel(option), color = Color.White, fontSize = 16.sp)
                        if (option == current) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = BlueAccent)
                        }
                    }
                }
            }
        }
    }
}

private fun launchFingerprintPrompt(
    activity: FragmentActivity,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val executor = ContextCompat.getMainExecutor(activity)
    val biometricPrompt = BiometricPrompt(
        activity,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onSuccess()
            }
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onError(errString.toString())
            }
        }
    )
    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Unlock App")
        .setSubtitle("Use your fingerprint to continue")
        .setNegativeButtonText("Cancel")
        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
        .build()
    biometricPrompt.authenticate(promptInfo)
}

@Composable
fun AppSplashScreen(onFirstFrame: () -> Unit = {}) {
    LaunchedEffect(Unit) {
        withFrameNanos { }
        onFirstFrame()
    }
    Box(
        modifier = Modifier.fillMaxSize().background(DarkBg),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(CardBg),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(44.dp)
                )
            }
            Spacer(Modifier.height(20.dp))
            Text("Streakk", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(Modifier.height(8.dp))
            Text("Build better habits, every day.", color = TextGray, fontSize = 14.sp, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun AppLockScreen(onUnlock: () -> Unit) {
    val context = LocalContext.current
    val activity = context as FragmentActivity

    fun triggerAuth() {
        val biometricManager = BiometricManager.from(context)
        if (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS) {
            launchFingerprintPrompt(
                activity = activity,
                onSuccess = { onUnlock() },
                onError = { }
            )
        } else {
            Toast.makeText(context, "Set up fingerprint unlock in your phone's settings first", Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(Unit) { triggerAuth() }

    BackHandler {  }

    Box(
        modifier = Modifier.fillMaxSize().background(DarkBg),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(CardBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Lock, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(30.dp))
            }
            Spacer(Modifier.height(20.dp))
            Text("App Locked", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(Modifier.height(8.dp))
            Text("Unlock with your fingerprint to continue.", color = TextGray, fontSize = 14.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = { triggerAuth() },
                colors = ButtonDefaults.buttonColors(containerColor = BlueAccent),
                shape = RoundedCornerShape(50)
            ) {
                Icon(Icons.Default.Fingerprint, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Unlock with Fingerprint")
            }
        }
    }
}

fun effectivePeriod(habit: Habit): DoItAt {
    val hour = habit.startTime?.hour
    return when {
        hour == null -> habit.doItAt
        hour in 6..11 -> DoItAt.MORNING
        hour in 12..16 -> DoItAt.AFTERNOON
        hour in 17..21 -> DoItAt.EVENING
        else -> DoItAt.ANYTIME
    }
}

fun calculateDayProgress(
    date: LocalDate,
    habits: List<Habit>,
    habitStatus: Map<Pair<Long, LocalDate>, HabitStatus>
): Pair<Int, Int> {
    val scheduledHabits = habits.filter { habit ->
        val withinEndRange = when (habit.endMode) {
            EndMode.DATE -> habit.endDate == null || !date.isAfter(habit.endDate)
            EndMode.DAYS -> habit.endAfterDays == null ||
                !date.isAfter(habit.createdAt.plusDays(habit.endAfterDays.toLong()))
            EndMode.OFF -> !date.isAfter(habit.createdAt.plusDays(30))
        }
        val notBeforeCreation = !date.isBefore(habit.createdAt)
        val isScheduled = date.dayOfWeek in habit.habitDays
        withinEndRange && notBeforeCreation && isScheduled
    }
    val totalCount = scheduledHabits.size
    if (totalCount == 0) return 0 to 0

    val completedHabits = scheduledHabits.count { habit ->
        val status = habitStatus[habit.id to date] ?: HabitStatus.ACTIVE
        status == HabitStatus.DONE || status == HabitStatus.SKIPPED
    }
    return completedHabits to totalCount
}

fun weekStartFor(date: LocalDate, firstDay: DayOfWeek): LocalDate {
    val diff = (date.dayOfWeek.value - firstDay.value + 7) % 7
    return date.minusDays(diff.toLong())
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TasksScreen(
    habits: List<Habit>,
    habitStatus: SnapshotStateMap<Pair<Long, LocalDate>, HabitStatus>,
    onAddHabit: (LocalDate) -> Unit,
    onEditHabit: (Habit) -> Unit,
    showDeletedBanner: Boolean,
    onDismissDeletedBanner: () -> Unit,
    showHabitLimitBanner: Boolean = false,
    onDismissHabitLimitBanner: () -> Unit = {},
    bottomContentPadding: Dp = 0.dp,
    firstDayOfWeek: DayOfWeek = DayOfWeek.MONDAY,
    showStreakCount: Boolean = true,
    soundOnComplete: Boolean = true
) {
    val density = LocalDensity.current
    var headerHeight by remember { mutableStateOf(0.dp) }
    val today = remember { LocalDate.now() }
    val streakByHabit by remember(habits, habitStatus, today) {
        derivedStateOf { habits.associate { it.id to computeStreak(it, habitStatus, today) } }
    }
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val currentLocale = remember(configuration) { configuration.locales[0] }
    val currentWeekMonday = remember(today, firstDayOfWeek) { weekStartFor(today, firstDayOfWeek) }
    val centerPage = 5000
    val pagerState = rememberPagerState(initialPage = centerPage) { centerPage * 2 }
    val coroutineScope = rememberCoroutineScope()

    val weekOffset = pagerState.currentPage - centerPage
    val monday = remember(weekOffset) { currentWeekMonday.plusWeeks(weekOffset.toLong()) }

    var selectedDate by rememberSaveable { mutableStateOf(today) }

    fun changeDay(delta: Int) {
        val newDate = selectedDate.plusDays(delta.toLong())
        selectedDate = newDate
        val targetMonday = weekStartFor(newDate, firstDayOfWeek)
        val weeksBetween = java.time.temporal.ChronoUnit.WEEKS.between(currentWeekMonday, targetMonday)
        val targetPage = (centerPage + weeksBetween).toInt()
        if (targetPage != pagerState.currentPage) {
            coroutineScope.launch { pagerState.animateScrollToPage(targetPage) }
        }
    }
    var selectedFilter by rememberSaveable { mutableStateOf("ALL") }
    var menuOpenForHabitId by remember { mutableStateOf<Long?>(null) }
    var skipConfirmHabit by remember { mutableStateOf<Habit?>(null) }
    var showMonthPicker by remember { mutableStateOf(false) }

    val titleText = when (selectedDate) {
        today -> "TODAY"
        today.minusDays(1) -> "YESTERDAY"
        today.plusDays(1) -> "TOMORROW"
        else -> selectedDate.format(DateTimeFormatter.ofPattern("MMM d", currentLocale)).uppercase()
    }
    val subtitleText = when {
        selectedDate.isBefore(today) -> {
            val (completed, total) = calculateDayProgress(selectedDate, habits, habitStatus)
            if (total == 0) "Not Scheduled" else "${(completed * 100) / total}% Finished"
        }
        selectedDate == today ->
            selectedDate.format(DateTimeFormatter.ofPattern("MMM d"))
        else -> selectedDate.format(DateTimeFormatter.ofPattern("EEEE", currentLocale))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = (headerHeight - 90.dp).coerceAtLeast(0.dp))
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(CardBg)
    )
    Column(
        modifier = Modifier
            .align(Alignment.TopCenter)
            .widthIn(max = 600.dp)
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    headerHeight = with(density) { coordinates.size.height.toDp() }
                }
        ) {
        Spacer(Modifier.height(20.dp))

        Column {
            Row(
                modifier = Modifier.fillMaxWidth().height(48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(titleText, color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                if (!selectedDate.isBefore(today)) {
                    IconButton(
                        onClick = { onAddHabit(selectedDate) }
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add habit",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
            Text(
                subtitleText,
                color = TextGray,
                fontSize = 16.sp,
                fontFamily = Tajawal,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { showMonthPicker = true }
            )
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val weekdayLabels = (0..6).map { offset ->
                DayOfWeek.of(((firstDayOfWeek.value - 1 + offset) % 7) + 1)
                    .getDisplayName(TextStyle.SHORT, currentLocale).uppercase()
            }
            weekdayLabels.forEachIndexed { index, label ->
                Text(
                    label,
                    color = TextGray,
                    fontSize = 13.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.height(6.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val pageMonday = remember(page, currentWeekMonday) { currentWeekMonday.plusWeeks((page - centerPage).toLong()) }
            val pageDays = remember(pageMonday) { (0..6).map { pageMonday.plusDays(it.toLong()) } }
            val pageProgress by remember(pageDays) {
                derivedStateOf {
                    pageDays.map { d -> if (d.isBefore(today)) calculateDayProgress(d, habits, habitStatus) else 0 to 0 }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                pageDays.forEachIndexed { index, day ->
                    val isSelected = day == selectedDate
                    val isTodayDate = day == today
                    val isPastDate = day.isBefore(today)
                    val (dayCompleted, dayTotal) = pageProgress[index]
                    val dayProgressFraction = if (dayTotal == 0) 0f else dayCompleted.toFloat() / dayTotal.toFloat()
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Box(
                            modifier = Modifier.size(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isPastDate && dayTotal > 0) {
                                Canvas(modifier = Modifier.size(34.dp)) {
                                    val strokeWidthPx = 4.5.dp.toPx()
                                    drawArc(
                                        color = Color(0xFF3A3B44),
                                        startAngle = -90f,
                                        sweepAngle = 360f,
                                        useCenter = false,
                                        style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
                                    )
                                    drawArc(
                                        color = BlueAccent,
                                        startAngle = -90f,
                                        sweepAngle = 360f * dayProgressFraction,
                                        useCenter = false,
                                        style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .size(38.5.dp)
                                    .clip(CircleShape)
                                    .background(if (isTodayDate) BlueAccent else Color.Transparent)
                                    .clickable { selectedDate = day },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "${day.dayOfMonth}",
                                    color = if (isSelected || isTodayDate) Color.White else TextGray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                        }
                        if (isSelected) {
                            Spacer(Modifier.height(4.dp))
                            Box(
                                Modifier
                                    .width(24.dp)
                                    .height(3.dp)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(BlueAccent)
                            )
                        } else {
                            Spacer(Modifier.height(7.dp))
                        }
                    }
                }
            }
        }

        if (showMonthPicker) {
            MonthPickerDialog(
                year = today.year,
                onDismiss = { showMonthPicker = false },
                onMonthSelected = { month ->
                    val firstOfMonth = LocalDate.of(today.year, month, 1)
                    val targetMonday = weekStartFor(firstOfMonth, firstDayOfWeek)
                    val weeksBetween = ChronoUnit.WEEKS.between(currentWeekMonday, targetMonday)
                    coroutineScope.launch {
                        pagerState.scrollToPage((centerPage + weeksBetween).toInt())
                    }
                    selectedDate = firstOfMonth
                    showMonthPicker = false
                }
            )
        }
        }

        Spacer(Modifier.height(24.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
            modifier = Modifier
                .layout { measurable, constraints ->
                    val bleed = 12.dp.roundToPx()
                    val placeable = measurable.measure(constraints.copy(maxWidth = constraints.maxWidth + bleed * 2))
                    layout(constraints.maxWidth, placeable.height) { placeable.place(-bleed, 0) }
                }
        ) {
            item { FilterChip("ALL", selectedFilter == "ALL", icon = Icons.Filled.Apps) { selectedFilter = "ALL" } }
            item { FilterChip("MORNING", selectedFilter == "MORNING", icon = Icons.Filled.WbTwilight) { selectedFilter = "MORNING" } }
            item { FilterChip("AFTERNOON", selectedFilter == "AFTERNOON", icon = Icons.Filled.LightMode) { selectedFilter = "AFTERNOON" } }
            item { FilterChip("EVENING", selectedFilter == "EVENING", icon = Icons.Filled.NightsStay) { selectedFilter = "EVENING" } }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .pointerInput(Unit) {
                    var totalDrag = 0f
                    detectHorizontalDragGestures(
                        onDragStart = { totalDrag = 0f },
                        onHorizontalDrag = { change, dragAmount ->
                            change.consume()
                            totalDrag += dragAmount
                        },
                        onDragEnd = {
                            val threshold = 80f
                            when {
                                totalDrag <= -threshold -> changeDay(1)
                                totalDrag >= threshold -> changeDay(-1)
                            }
                            totalDrag = 0f
                        }
                    )
                }
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = bottomContentPadding)
        ) {

        Spacer(Modifier.height(28.dp))

        val visibleHabits = habits.filter { habit ->
            val withinEndRange = isHabitActiveOn(selectedDate, habit.endMode, habit.endDate, habit.endAfterDays, habit.createdAt)
            val notBeforeCreation = !selectedDate.isBefore(habit.createdAt)
            val isScheduledToday = selectedDate.dayOfWeek in habit.habitDays
            val matchesTimeFilter = when (selectedFilter) {
                "ALL" -> true
                "MORNING" -> effectivePeriod(habit) == DoItAt.MORNING
                "AFTERNOON" -> effectivePeriod(habit) == DoItAt.AFTERNOON
                "EVENING" -> effectivePeriod(habit) == DoItAt.EVENING
                else -> true
            }
            withinEndRange && notBeforeCreation && isScheduledToday && matchesTimeFilter
        }

        if (visibleHabits.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, bottom = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Description,
                        contentDescription = null,
                        tint = BlueAccent,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(Modifier.height(20.dp))
                Text(
                    "Nothing scheduled for this day",
                    color = TextGray,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            DoItAt.values().forEach { period ->
                val habitsForPeriod = visibleHabits.filter { effectivePeriod(it) == period }
                if (habitsForPeriod.isNotEmpty()) {
                    Text(period.name, color = TextGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    habitsForPeriod.forEach { habit ->
                        val status = habitStatus[habit.id to selectedDate] ?: HabitStatus.ACTIVE
                        HabitCard(
                            habit = habit,
                            status = status,
                            date = selectedDate,
                            isToday = selectedDate == today,
                            isPast = selectedDate.isBefore(today),
                            streakCount = streakByHabit[habit.id] ?: 0,
                            showStreak = showStreakCount,
                            menuExpanded = menuOpenForHabitId == habit.id,
                            onToggleDone = {
    val newStatus = if (status == HabitStatus.DONE) HabitStatus.ACTIVE else HabitStatus.DONE
    habitStatus[habit.id to selectedDate] = newStatus
    if (newStatus == HabitStatus.DONE && soundOnComplete) CompletionSoundPlayer.play()
},
                            onOpenMenu = { menuOpenForHabitId = habit.id },
                            onDismissMenu = { menuOpenForHabitId = null },
                            onUndo = {
                                menuOpenForHabitId = null
                                habitStatus[habit.id to selectedDate] = HabitStatus.ACTIVE
                            },
                            onTakeADayOff = {
                                menuOpenForHabitId = null
                                skipConfirmHabit = habit
                            },
                            onEdit = {
                                menuOpenForHabitId = null
                                onEditHabit(habit)
                            }
                        )
                        Spacer(Modifier.height(14.dp))
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }

        }

        if (skipConfirmHabit != null) {
            SkipConfirmDialog(
                onDismiss = { skipConfirmHabit = null },
                onConfirm = {
                    habitStatus[skipConfirmHabit!!.id to selectedDate] = HabitStatus.SKIPPED
                    skipConfirmHabit = null
                }
            )
        }

        Spacer(Modifier.height(20.dp))
        }
        }
    }

        if (selectedDate != today) {
            val todayPillShape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50, topEndPercent = 0, bottomEndPercent = 0)
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 45.dp + bottomContentPadding)
                    .shadow(elevation = 10.dp, shape = todayPillShape, ambientColor = BlueAccent, spotColor = BlueAccent)
                    .clip(todayPillShape)
                    .background(BlueAccent)
                    .border(width = 1.5.dp, color = Color.White.copy(alpha = 0.25f), shape = todayPillShape)
                    .clickable {
                        selectedDate = today
                        coroutineScope.launch { pagerState.animateScrollToPage(centerPage) }
                    }
                    .padding(start = 14.dp, end = 20.dp, top = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val chevronIcon = if (selectedDate.isBefore(today)) Icons.Default.ChevronRight else Icons.Default.ChevronLeft
                Icon(chevronIcon, contentDescription = null, tint = Color.White)
                Spacer(Modifier.width(4.dp))
                Text("Today", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun HabitCard(
    habit: Habit,
    status: HabitStatus,
    date: LocalDate,
    isToday: Boolean,
    isPast: Boolean,
    streakCount: Int = 0,
    showStreak: Boolean = true,
    menuExpanded: Boolean,
    onToggleDone: () -> Unit,
    onOpenMenu: () -> Unit,
    onDismissMenu: () -> Unit,
    onUndo: () -> Unit,
    onTakeADayOff: () -> Unit,
    onEdit: () -> Unit
) {
    val isChecked = status == HabitStatus.DONE || status == HabitStatus.SKIPPED
    val cardColor = if (status == HabitStatus.ACTIVE) BlueAccent else CardBg
   val textColor = if (status == HabitStatus.ACTIVE) Color.White else TextGray

    var confettiTrigger by remember { mutableStateOf(0L) }
    val previousStatus = remember(date) { mutableStateOf(status) }
    LaunchedEffect(status, date) {
        if (status == HabitStatus.DONE && previousStatus.value != HabitStatus.DONE) {
            confettiTrigger = System.currentTimeMillis()
        }
        previousStatus.value = status
    }

    Box {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (isToday) {
                    Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(if (isChecked) BlueAccent else Color.Transparent)
                    .border(
                        width = if (isChecked) 0.dp else 2.dp,
                        color = if (isChecked) Color.Transparent else TextGray,
                        shape = CircleShape
                    )
                    .clickable { onToggleDone() },
                contentAlignment = Alignment.Center
            ) {
                if (isChecked) {
                    Icon(Icons.Default.Check, contentDescription = "Done", tint = Color.White, modifier = Modifier.size(10.dp))
                }
            }
            Spacer(Modifier.width(12.dp))
        }
        Box(modifier = Modifier.weight(1f)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(iconForKey(habit.iconEmoji), contentDescription = null, tint = textColor, modifier = Modifier.size(22.dp))
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(habit.name, color = textColor, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                if (showStreak && streakCount > 0) {
                                    Spacer(Modifier.width(6.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.White.copy(alpha = 0.18f))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Icon(Icons.Default.LocalFireDepartment, contentDescription = null, tint = Color(0xFFFF9800), modifier = Modifier.size(14.dp))
                                        Spacer(Modifier.width(2.dp))
                                        Text("x$streakCount", color = textColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                            when (status) {
                                HabitStatus.DONE -> Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = TextGray, modifier = Modifier.size(14.dp))
                                    Spacer(Modifier.width(4.dp))
                                    Text("Finished", color = TextGray, fontSize = 14.sp)
                                }
                                HabitStatus.SKIPPED -> Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.LocalCafe, contentDescription = null, tint = TextGray, modifier = Modifier.size(14.dp))
                                    Spacer(Modifier.width(4.dp))
                                    Text("Skipped", color = TextGray, fontSize = 14.sp)
                                }
                                HabitStatus.ACTIVE -> {
                                    if (isPast) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.Close, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                                            Spacer(Modifier.width(4.dp))
                                            Text("Missed", color = Color.White, fontSize = 14.sp)
                                        }
                                    } else if (habit.startTime != null) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(Color.White.copy(alpha = 0.15f))
                                                .padding(horizontal = 6.dp, vertical = 0.dp)
                                        ) {
                                            Icon(Icons.Default.AccessTime, contentDescription = null, tint = textColor, modifier = Modifier.size(13.dp))
                                            Spacer(Modifier.width(4.dp))
                                            Text(
                                                if (habit.endTime != null)
                                                    "${habit.startTime.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${habit.endTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
                                                else
                                                    habit.startTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                                                color = textColor,
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Text(
                        "•••",
                        color = textColor,
                        fontSize = 18.sp,
                        modifier = Modifier.clickable { onOpenMenu() }
                    )
                    if (menuExpanded) {
                        HabitOptionsSheet(
                            habit = habit,
                            status = status,
                            onDismiss = onDismissMenu,
                            onUndo = onUndo,
                            onTakeADayOff = onTakeADayOff,
                            onEdit = onEdit
                        )
                    }
                }
            }
        }
    }
    if (confettiTrigger != 0L) {
        CardPopperBurst(trigger = confettiTrigger, originXDp = 11.dp)
    }
    }
}

@Composable
fun HabitOptionsSheet(
    habit: Habit,
    status: HabitStatus,
    onDismiss: () -> Unit,
    onUndo: () -> Unit,
    onTakeADayOff: () -> Unit,
    onEdit: () -> Unit
) {
    val habitSheetNestedScrollConnection = remember {
        object : NestedScrollConnection {
            var totalOverscroll = 0f
            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                if (available.y > 0f) {
                    totalOverscroll += available.y
                    if (totalOverscroll > 300f) {
                        onDismiss()
                    }
                } else {
                    totalOverscroll = 0f
                }
                return Offset.Zero
            }
        }
    }
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onDismiss() },
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .nestedScroll(habitSheetNestedScrollConnection)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {  },
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = CardBg
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .heightIn(max = 420.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(iconForKey(habit.iconEmoji), contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                        Spacer(Modifier.width(12.dp))
                        Text(habit.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }

                    Spacer(Modifier.height(20.dp))
                    HorizontalDivider(color = Color(0xFF3A3B44))
                    Spacer(Modifier.height(16.dp))

                    val undoEnabled = status == HabitStatus.DONE || status == HabitStatus.SKIPPED
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        PdfSheetQuickAction(Icons.AutoMirrored.Filled.Undo, "Undo", enabled = undoEnabled) {
                            onDismiss(); onUndo()
                        }
                        PdfSheetQuickAction(Icons.Default.LocalCafe, "Skip") { onDismiss(); onTakeADayOff() }
                        PdfSheetQuickAction(Icons.Default.Edit, "Edit") { onDismiss(); onEdit() }
                    }
                }
            }
        }
    }
}

@Composable
fun SkipConfirmDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp), color = CardBg) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Take a day off?", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(14.dp))
                Text(
                    "Mark this habit as 'Skipped' if you have exact reason. The 'Skipped' status will not break your streak.\n\nDon't use it too many times :)",
                    color = TextGray,
                    fontSize = 15.sp
                )
                Spacer(Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF1C1D24))
                            .clickable { onDismiss() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Cancel", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(BlueAccent)
                            .clickable { onConfirm() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("YES", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteConfirmDialog(habitName: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp), color = CardBg) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Are you sure to delete this habit?", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(14.dp))
                Text(
                    "This will permanently remove '$habitName' and all of its history. This action cannot be undone.",
                    color = TextGray,
                    fontSize = 15.sp
                )
                Spacer(Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) { Text("CANCEL", color = BlueAccent, fontWeight = FontWeight.Bold) }
                    Spacer(Modifier.width(8.dp))
                    TextButton(onClick = onConfirm) { Text("OK", color = BlueAccent, fontWeight = FontWeight.Bold) }
                }
            }
        }
    }
}

@Composable
fun FilterChip(label: String, selected: Boolean, icon: androidx.compose.ui.graphics.vector.ImageVector? = null, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(if (selected) BlueAccent else ChipBg)
            .clickable { onClick() }
            .padding(horizontal = 18.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = if (selected) Color.White else TextGray,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(6.dp))
            }
            Text(label, color = if (selected) Color.White else TextGray, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
    }
}

data class PdfFile(
    val id: Long,
    val name: String,
    val path: String,
    val sizeBytes: Long,
    val dateModified: Long = 0L
)

fun hasStorageAccess(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Environment.isExternalStorageManager()
    } else {
        ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }
}

suspend fun loadDevicePdfs(context: Context): List<PdfFile> = withContext(Dispatchers.IO) {
    val result = mutableListOf<PdfFile>()
    val collection = MediaStore.Files.getContentUri("external")
    val projection = arrayOf(
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.DISPLAY_NAME,
        MediaStore.Files.FileColumns.DATA,
        MediaStore.Files.FileColumns.SIZE,
        MediaStore.Files.FileColumns.DATE_MODIFIED
    )
    val selection = "${MediaStore.Files.FileColumns.MIME_TYPE} = ?"
    val selectionArgs = arrayOf("application/pdf")
    val sortOrder = "${MediaStore.Files.FileColumns.DATE_MODIFIED} DESC"

    context.contentResolver.query(collection, projection, selection, selectionArgs, sortOrder)?.use { cursor ->
        val idCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
        val nameCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
        val pathCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
        val sizeCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
        val dateCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)
        while (cursor.moveToNext()) {
            result.add(
                PdfFile(
                    id = cursor.getLong(idCol),
                    name = cursor.getString(nameCol) ?: "Untitled.pdf",
                    path = cursor.getString(pathCol) ?: "",
                    sizeBytes = cursor.getLong(sizeCol),
                    dateModified = cursor.getLong(dateCol)
                )
            )
        }
    }
    result
}

fun formatFileSize(bytes: Long, unit: FileSizeUnit = FileSizeUnit.AUTO): String {
    val kb = bytes / 1024.0
    val mb = kb / 1024.0
    return when (unit) {
        FileSizeUnit.KB -> String.format(Locale.getDefault(), "%.2fKB", kb)
        FileSizeUnit.MB -> String.format(Locale.getDefault(), "%.2fMB", mb)
        FileSizeUnit.AUTO -> if (mb >= 1) String.format(Locale.getDefault(), "%.2fMB", mb)
            else String.format(Locale.getDefault(), "%.2fKB", kb)
    }
}

private val pdfDateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

fun formatPdfDate(dateModifiedSeconds: Long): String {
    return try {
        pdfDateFormatter.format(Date(dateModifiedSeconds * 1000))
    } catch (e: Exception) {
        ""
    }
}

fun openPdf(context: Context, pdf: PdfFile, preferredPackage: String? = null) {
    val contentUri = ContentUris.withAppendedId(MediaStore.Files.getContentUri("external"), pdf.id)
    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(contentUri, "application/pdf")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        if (preferredPackage != null) setPackage(preferredPackage)
    }
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {

        intent.setPackage(null)
        context.startActivity(intent)
    }
}

fun getPdfHandlerApps(context: Context): List<Pair<String, String>> {
    val pm = context.packageManager

    val mimeOnlyIntent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(Uri.parse("content://${context.packageName}.dummyprovider/dummy.pdf"), "application/pdf")
    }
    val fileExtensionIntent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(Uri.parse("file:///sdcard/dummy.pdf"), "application/pdf")
    }
    val genericMimeIntent = Intent(Intent.ACTION_VIEW).apply {
        type = "application/pdf"
    }
    return (pm.queryIntentActivities(mimeOnlyIntent, 0) +
        pm.queryIntentActivities(fileExtensionIntent, 0) +
        pm.queryIntentActivities(genericMimeIntent, 0))
        .map { it.activityInfo.packageName to it.loadLabel(pm).toString() }
        .distinctBy { it.first }
        .sortedBy { it.second.lowercase() }
}

fun showPdfDefaultAppChooser(context: Context, onAppChosen: (String) -> Unit) {

    val sampleUri = PdfsScreenState.pdfs.value.firstOrNull()?.let {
        ContentUris.withAppendedId(MediaStore.Files.getContentUri("external"), it.id)
    }
    val targetIntent = Intent(Intent.ACTION_VIEW).apply {
        if (sampleUri != null) {
            setDataAndType(sampleUri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        } else {
            setDataAndType(Uri.parse("content://${context.packageName}.dummyprovider/dummy.pdf"), "application/pdf")
        }
    }

    val chosenAction = "com.streakk.app.PDF_DEFAULT_APP_CHOSEN_${System.currentTimeMillis()}"
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(ctx: Context, resultIntent: Intent) {
            val chosen = IntentCompat.getParcelableExtra(resultIntent, Intent.EXTRA_CHOSEN_COMPONENT, ComponentName::class.java)
            chosen?.packageName?.let { onAppChosen(it) }
            try { context.unregisterReceiver(this) } catch (e: Exception) {}
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.registerReceiver(receiver, IntentFilter(chosenAction), Context.RECEIVER_NOT_EXPORTED)
    } else {
        context.registerReceiver(receiver, IntentFilter(chosenAction))
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        Intent(chosenAction).setPackage(context.packageName),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
    )

    val chooserIntent = Intent.createChooser(targetIntent, "Open PDFs with", pendingIntent.intentSender)
    try {
        context.startActivity(chooserIntent)
    } catch (e: Exception) {
        try { context.unregisterReceiver(receiver) } catch (e2: Exception) {}
    }
}

enum class PdfSortOption(val label: String) {
    NEWEST("Newest first"),
    OLDEST("Oldest first"),
    NAME_AZ("Name A-Z"),
    NAME_ZA("Name Z-A"),
    SIZE("Largest first")
}

fun sortPdfs(pdfs: List<PdfFile>, option: PdfSortOption): List<PdfFile> = when (option) {
    PdfSortOption.NEWEST -> pdfs.sortedByDescending { it.dateModified }
    PdfSortOption.OLDEST -> pdfs.sortedBy { it.dateModified }
    PdfSortOption.NAME_AZ -> pdfs.sortedBy { it.name.lowercase() }
    PdfSortOption.NAME_ZA -> pdfs.sortedByDescending { it.name.lowercase() }
    PdfSortOption.SIZE -> pdfs.sortedByDescending { it.sizeBytes }
}

object PdfFolderStorage {
    private const val PREFS_NAME = "pdf_folder_prefs"
    private const val KEY_FOLDERS = "pdf_folders_json"

    fun save(context: Context, folders: Set<String>) {
        val array = JSONArray()
        folders.forEach { array.put(it) }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_FOLDERS, array.toString())
            .apply()
    }

    fun load(context: Context): Set<String> {
        val json = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_FOLDERS, null) ?: return emptySet()
        val array = JSONArray(json)
        return (0 until array.length()).map { array.getString(it) }.toSet()
    }
}

object PdfFolderAssignmentStorage {
    private const val PREFS_NAME = "pdf_folder_assignment_prefs"
    private const val KEY_ASSIGNMENTS = "pdf_folder_assignments_json"

    fun save(context: Context, assignments: Map<Long, String>) {
        val obj = JSONObject()
        assignments.forEach { (id, folder) -> obj.put(id.toString(), folder) }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_ASSIGNMENTS, obj.toString())
            .apply()
    }

    fun load(context: Context): Map<Long, String> {
        val json = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_ASSIGNMENTS, null) ?: return emptyMap()
        val obj = JSONObject(json)
        val result = mutableMapOf<Long, String>()
        obj.keys().forEach { key -> result[key.toLong()] = obj.getString(key) }
        return result
    }
}

object PdfListCacheStorage {
    private const val PREFS_NAME = "pdf_list_cache_prefs"
    private const val KEY_LIST = "pdf_list_json"

    suspend fun save(context: Context, pdfs: List<PdfFile>): Unit = withContext(Dispatchers.IO) {
        val array = JSONArray()
        pdfs.forEach { pdf ->
            val obj = JSONObject()
            obj.put("id", pdf.id)
            obj.put("name", pdf.name)
            obj.put("path", pdf.path)
            obj.put("sizeBytes", pdf.sizeBytes)
            obj.put("dateModified", pdf.dateModified)
            array.put(obj)
        }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_LIST, array.toString())
            .apply()
    }

    suspend fun load(context: Context): List<PdfFile> = withContext(Dispatchers.IO) {
        val json = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_LIST, null) ?: return@withContext emptyList()
        try {
            val array = JSONArray(json)
            (0 until array.length()).map { i ->
                val obj = array.getJSONObject(i)
                PdfFile(
                    id = obj.getLong("id"),
                    name = obj.getString("name"),
                    path = obj.getString("path"),
                    sizeBytes = obj.getLong("sizeBytes"),
                    dateModified = obj.getLong("dateModified")
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}

fun deletePdf(context: Context, pdf: PdfFile): Boolean {
    return try {
        val contentUri = ContentUris.withAppendedId(MediaStore.Files.getContentUri("external"), pdf.id)
        context.contentResolver.delete(contentUri, null, null) > 0
    } catch (e: Exception) {
        false
    }
}

fun sharePdf(context: Context, pdf: PdfFile) {
    val contentUri = ContentUris.withAppendedId(MediaStore.Files.getContentUri("external"), pdf.id)
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "application/pdf"
        putExtra(Intent.EXTRA_STREAM, contentUri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(intent, "Share PDF"))
}

fun renamePdf(context: Context, pdf: PdfFile, newName: String): Boolean {
    return try {
        val contentUri = ContentUris.withAppendedId(MediaStore.Files.getContentUri("external"), pdf.id)
        val values = android.content.ContentValues().apply {
            put(MediaStore.Files.FileColumns.DISPLAY_NAME, newName)
        }
        context.contentResolver.update(contentUri, values, null, null) > 0
    } catch (e: Exception) {
        false
    }
}

fun printPdf(context: Context, pdf: PdfFile) {
    val printManager = context.getSystemService(Context.PRINT_SERVICE) as android.print.PrintManager
    val adapter = object : android.print.PrintDocumentAdapter() {
        override fun onLayout(
            oldAttributes: android.print.PrintAttributes?,
            newAttributes: android.print.PrintAttributes,
            cancellationSignal: android.os.CancellationSignal?,
            callback: LayoutResultCallback,
            extras: Bundle?
        ) {
            if (cancellationSignal?.isCanceled == true) {
                callback.onLayoutCancelled()
                return
            }
            val info = android.print.PrintDocumentInfo.Builder(pdf.name)
                .setContentType(android.print.PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .build()
            callback.onLayoutFinished(info, true)
        }

        override fun onWrite(
            pages: Array<out android.print.PageRange>?,
            destination: ParcelFileDescriptor,
            cancellationSignal: android.os.CancellationSignal?,
            callback: WriteResultCallback
        ) {
            try {
                File(pdf.path).inputStream().use { input ->
                    java.io.FileOutputStream(destination.fileDescriptor).use { output ->
                        input.copyTo(output)
                    }
                }
                callback.onWriteFinished(arrayOf(android.print.PageRange.ALL_PAGES))
            } catch (e: Exception) {
                callback.onWriteFailed(e.message)
            }
        }
    }
    printManager.print(pdf.name, adapter, android.print.PrintAttributes.Builder().build())
}

suspend fun renderPdfThumbnail(path: String): Bitmap? = withContext(Dispatchers.IO) {
    try {
        val file = File(path)
        if (!file.exists()) return@withContext null
        ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY).use { pfd ->
            PdfRenderer(pfd).use { renderer ->
                if (renderer.pageCount == 0) return@withContext null
                renderer.openPage(0).use { page ->

                    val targetWidth = 180
                    val scale = targetWidth.toFloat() / page.width
                    val targetHeight = (page.height * scale).toInt().coerceAtLeast(1)
                    val bitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888)
                    bitmap.eraseColor(android.graphics.Color.WHITE)
                    val matrix = android.graphics.Matrix().apply { setScale(scale, scale) }
                    page.render(bitmap, null, matrix, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                    bitmap
                }
            }
        }
    } catch (e: kotlinx.coroutines.CancellationException) {

        throw e
    } catch (e: Exception) {
        null
    }
}

val pdfRenderSemaphore = kotlinx.coroutines.sync.Semaphore(2)

object PdfThumbnailCache {
    private val cache = object : android.util.LruCache<Long, Bitmap>(
        (Runtime.getRuntime().maxMemory() / 1024 / 16).toInt()
    ) {
        override fun sizeOf(key: Long, value: Bitmap): Int = value.byteCount / 1024
    }

    fun get(pdfId: Long): Bitmap? = cache.get(pdfId)

    fun put(pdfId: Long, bitmap: Bitmap) {
        cache.put(pdfId, bitmap)
    }

    fun clear() {
        cache.evictAll()
    }
}

object PdfsScreenState {
    val pdfs = mutableStateOf<List<PdfFile>>(emptyList())
    val folders = mutableStateOf<Set<String>>(emptySet())
    val folderAssignments = mutableStateOf<Map<Long, String>>(emptyMap())
    val hasLoadedOnce = mutableStateOf(false)
}

@Composable
fun PdfsScreen(
    defaultSortOption: PdfSortOption = PdfSortOption.NEWEST,
    autoScanEnabled: Boolean = true,
    preferredPdfPackage: String? = null,
    fileSizeUnit: FileSizeUnit = FileSizeUnit.AUTO,
    onSortOptionPersist: (PdfSortOption) -> Unit = {},
    bottomContentPadding: Dp = 0.dp
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var hasPermission by remember { mutableStateOf(hasStorageAccess(context)) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                hasPermission = hasStorageAccess(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
    var pdfs by PdfsScreenState.pdfs
    var isLoading by remember { mutableStateOf(false) }

    var folders by PdfsScreenState.folders
    var folderAssignments by PdfsScreenState.folderAssignments
    var hasLoadedOnce by PdfsScreenState.hasLoadedOnce
    var openFolder by rememberSaveable { mutableStateOf<String?>(null) }
    var sortOption by remember { mutableStateOf(defaultSortOption) }
    LaunchedEffect(sortOption) { onSortOptionPersist(sortOption) }
    var showSortMenu by remember { mutableStateOf(false) }
    var showCreateFolderDialog by remember { mutableStateOf(false) }
    var selectionMode by remember { mutableStateOf(false) }
    val selectedIds = remember { mutableStateListOf<Long>() }
    var renameTarget by remember { mutableStateOf<PdfFile?>(null) }
    var deleteTarget by remember { mutableStateOf<PdfFile?>(null) }
    var moveTarget by remember { mutableStateOf<PdfFile?>(null) }
    var isSearching by rememberSaveable { mutableStateOf(false) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val searchFocusRequester = remember { FocusRequester() }

    LaunchedEffect(hasPermission) {
        if (hasPermission) {
            if (!hasLoadedOnce) {

                val cachedList = PdfListCacheStorage.load(context)
                if (cachedList.isNotEmpty()) {
                    pdfs = cachedList
                } else {
                    isLoading = true
                }
                folders = PdfFolderStorage.load(context)
                folderAssignments = PdfFolderAssignmentStorage.load(context)

                val freshList = loadDevicePdfs(context)
                if (freshList != pdfs) {
                    pdfs = freshList
                    PdfListCacheStorage.save(context, freshList)
                }
                isLoading = false
                hasLoadedOnce = true
            } else if (autoScanEnabled) {

                val freshList = loadDevicePdfs(context)
                if (freshList != pdfs) {
                    pdfs = freshList
                    PdfListCacheStorage.save(context, freshList)
                }
            }
        }
    }

    BackHandler(enabled = openFolder != null) { openFolder = null }
    BackHandler(enabled = isSearching) {
        isSearching = false
        searchQuery = ""
    }
    BackHandler(enabled = selectionMode) {
        selectionMode = false
        selectedIds.clear()
    }

    val sortedFolders = remember(folders, isSearching) {
        if (isSearching) emptyList() else folders.sortedBy { it.lowercase() }
    }
    val folderCounts = remember(folderAssignments) {
        folderAssignments.values.groupingBy { it }.eachCount()
    }
    val displayedPdfs = remember(pdfs, folderAssignments, openFolder, sortOption, isSearching, searchQuery) {
        val base = if (isSearching) {

            if (searchQuery.isBlank()) emptyList()
            else pdfs.filter { it.name.contains(searchQuery, ignoreCase = true) }
        } else if (openFolder == null) {
            pdfs.filter { folderAssignments[it.id] == null }
        } else {
            pdfs.filter { folderAssignments[it.id] == openFolder }
        }
        sortPdfs(base, sortOption)
    }

    if (showCreateFolderDialog) {
        CreateFolderDialog(
            onDismiss = { showCreateFolderDialog = false },
            onCreate = { folderName ->
                val updated = folders + folderName
                folders = updated
                PdfFolderStorage.save(context, updated)
                showCreateFolderDialog = false
            }
        )
    }

    renameTarget?.let { target ->
        RenamePdfDialog(
            currentName = target.name,
            onDismiss = { renameTarget = null },
            onConfirm = { newName ->
                if (renamePdf(context, target, newName)) {
                    pdfs = pdfs.map { if (it.id == target.id) it.copy(name = newName) else it }
                }
                renameTarget = null
            }
        )
    }

    deleteTarget?.let { target ->
        DeletePdfConfirmDialog(
            pdfName = target.name,
            onDismiss = { deleteTarget = null },
            onConfirm = {
                if (deletePdf(context, target)) {
                    pdfs = pdfs.filterNot { it.id == target.id }
                    selectedIds.remove(target.id)
                    if (folderAssignments.containsKey(target.id)) {
                        folderAssignments = folderAssignments - target.id
                        PdfFolderAssignmentStorage.save(context, folderAssignments)
                    }
                }
                deleteTarget = null
            }
        )
    }

    moveTarget?.let { target ->
        MoveToFolderSheet(
            pdfName = target.name,
            folders = sortedFolders,
            currentFolder = folderAssignments[target.id],
            onDismiss = { moveTarget = null },
            onMoveToFolder = { folderName ->
                folderAssignments = folderAssignments + (target.id to folderName)
                PdfFolderAssignmentStorage.save(context, folderAssignments)
                moveTarget = null
            },
            onCreateFolderAndMove = { newFolderName ->
                val updatedFolders = folders + newFolderName
                folders = updatedFolders
                PdfFolderStorage.save(context, updatedFolders)
                folderAssignments = folderAssignments + (target.id to newFolderName)
                PdfFolderAssignmentStorage.save(context, folderAssignments)
                moveTarget = null
            },
            onRemoveFromFolder = {
                folderAssignments = folderAssignments - target.id
                PdfFolderAssignmentStorage.save(context, folderAssignments)
                moveTarget = null
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(20.dp))

        if (isSearching) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Close search",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        isSearching = false
                        searchQuery = ""
                    }
                )
                Spacer(Modifier.width(12.dp))
                val searchInteractionSource = remember { MutableInteractionSource() }
                var searchFieldValue by remember { mutableStateOf(TextFieldValue(searchQuery, TextRange(searchQuery.length))) }
                LaunchedEffect(searchQuery) {
                    if (searchQuery != searchFieldValue.text) {
                        searchFieldValue = TextFieldValue(searchQuery, TextRange(searchQuery.length))
                    }
                }
                BasicTextField(
                    value = searchFieldValue,
                    onValueChange = { searchFieldValue = it; searchQuery = it.text },
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(searchFocusRequester),
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.White, fontSize = 16.sp),
                    cursorBrush = SolidColor(BlueAccent),
                    interactionSource = searchInteractionSource,
                    decorationBox = { innerTextField ->
                        OutlinedTextFieldDefaults.DecorationBox(
                            value = searchQuery,
                            innerTextField = innerTextField,
                            enabled = true,
                            singleLine = true,
                            visualTransformation = VisualTransformation.None,
                            interactionSource = searchInteractionSource,
                            placeholder = { Text("Search PDFs", color = TextGray) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedBorderColor = BlueAccent,
                                unfocusedBorderColor = Color(0xFF3A3B44),
                                cursorColor = BlueAccent
                            ),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                            container = {
                                OutlinedTextFieldDefaults.Container(
                                    enabled = true,
                                    isError = false,
                                    interactionSource = searchInteractionSource,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        focusedBorderColor = BlueAccent,
                                        unfocusedBorderColor = Color(0xFF3A3B44),
                                        cursorColor = BlueAccent
                                    ),
                                    shape = OutlinedTextFieldDefaults.shape
                                )
                            }
                        )
                    }
                )
                if (searchQuery.isNotEmpty()) {
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Clear search",
                        tint = TextGray,
                        modifier = Modifier.clickable { searchQuery = "" }
                    )
                }
            }
            LaunchedEffect(Unit) {
                searchFocusRequester.requestFocus()
            }
        } else if (selectionMode) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Cancel selection",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            selectionMode = false
                            selectedIds.clear()
                        }
                    )
                    Spacer(Modifier.width(12.dp))
                    Text("${selectedIds.size} selected", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Share,
                        contentDescription = "Share selected",
                        tint = Color.White,
                        modifier = Modifier
                            .clickable(enabled = selectedIds.isNotEmpty()) {
                                pdfs.filter { it.id in selectedIds }.forEach { sharePdf(context, it) }
                            }
                            .padding(end = 18.dp)
                    )
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete selected",
                        tint = Color.White,
                        modifier = Modifier.clickable(enabled = selectedIds.isNotEmpty()) {
                            pdfs.filter { it.id in selectedIds }.forEach { deletePdf(context, it) }
                            pdfs = pdfs.filterNot { it.id in selectedIds }
                            if (selectedIds.any { folderAssignments.containsKey(it) }) {
                                folderAssignments = folderAssignments - selectedIds.toSet()
                                PdfFolderAssignmentStorage.save(context, folderAssignments)
                            }
                            selectedIds.clear()
                            selectionMode = false
                        }
                    )
                }
            }
        } else if (openFolder != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.clickable { openFolder = null }
                )
                Spacer(Modifier.width(14.dp))
                Text(
                    openFolder ?: "",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Box {
                    Icon(
                        Icons.AutoMirrored.Filled.Sort,
                        contentDescription = "Sort",
                        tint = Color.White,
                        modifier = Modifier.clickable { showSortMenu = true }
                    )
                    DropdownMenu(expanded = showSortMenu, onDismissRequest = { showSortMenu = false }) {
                        PdfSortOption.values().forEach { option ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        option.label,
                                        color = if (option == sortOption) BlueAccent else Color.White,
                                        fontWeight = if (option == sortOption) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                onClick = {
                                    sortOption = option
                                    showSortMenu = false
                                }
                            )
                        }
                    }
                }
                Spacer(Modifier.width(18.dp))
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search PDFs",
                    tint = Color.White,
                    modifier = Modifier.clickable { isSearching = true }
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth().height(48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("PDFs", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.CreateNewFolder,
                        contentDescription = "Create folder",
                        tint = Color.White,
                        modifier = Modifier
                            .clickable { showCreateFolderDialog = true }
                            .padding(end = 18.dp)
                    )
                    Box {
                        Icon(
                            Icons.AutoMirrored.Filled.Sort,
                            contentDescription = "Sort",
                            tint = Color.White,
                            modifier = Modifier.clickable { showSortMenu = true }
                        )
                        DropdownMenu(expanded = showSortMenu, onDismissRequest = { showSortMenu = false }) {
                            PdfSortOption.values().forEach { option ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            option.label,
                                            color = if (option == sortOption) BlueAccent else Color.White,
                                            fontWeight = if (option == sortOption) FontWeight.Bold else FontWeight.Normal
                                        )
                                    },
                                    onClick = {
                                        sortOption = option
                                        showSortMenu = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(Modifier.width(18.dp))
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search PDFs",
                        tint = Color.White,
                        modifier = Modifier.clickable { isSearching = true }
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        when {
            !hasPermission -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(CardBg),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Description, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(30.dp))
                        }
                        Spacer(Modifier.height(20.dp))
                        Text(
                            "Storage Access Required",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Please grant storage permission to allow the app to display the PDF files saved on your device.",
                            color = TextGray,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(24.dp))
                        Button(
                            onClick = {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                    val intent = Intent(
                                        Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                                        Uri.parse("package:${context.packageName}")
                                    )
                                    context.startActivity(intent)
                                } else {
                                    val activity = context as? Activity
                                    if (activity != null) {
                                        ActivityCompat.requestPermissions(
                                            activity,
                                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                                            9003
                                        )
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = BlueAccent),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text("Grant Permission")
                        }
                    }
                }
            }
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = BlueAccent)
                }
            }
            pdfs.isEmpty() && folders.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(CardBg),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Description, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(30.dp))
                        }
                        Spacer(Modifier.height(20.dp))
                        Text(
                            "No PDFs Found",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "PDF files saved on your device will appear here.",
                            color = TextGray,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            openFolder != null && displayedPdfs.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(CardBg),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Description, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(30.dp))
                        }
                        Spacer(Modifier.height(20.dp))
                        Text(
                            "Folder is Empty",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "There are no PDFs in this folder yet.",
                            color = TextGray,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = bottomContentPadding + 12.dp)
                ) {
                    if (openFolder == null) {
                        items(sortedFolders, key = { it }) { folderName ->
                            FolderRow(
                                name = folderName,
                                count = folderCounts[folderName] ?: 0,
                                onClick = { openFolder = folderName }
                            )
                            Spacer(Modifier.height(12.dp))
                        }
                    }
                    items(displayedPdfs, key = { it.id }) { pdf ->
                        PdfRow(
                            pdf = pdf,
                            selectionMode = selectionMode,
                            selected = pdf.id in selectedIds,
                            fileSizeUnit = fileSizeUnit,
                            onClick = {
                                if (selectionMode) {
                                    if (pdf.id in selectedIds) selectedIds.remove(pdf.id) else selectedIds.add(pdf.id)
                                } else {
                                    openPdf(context, pdf, preferredPdfPackage)
                                }
                            },
                            onLongPress = {
                                if (!selectionMode) {
                                    selectionMode = true
                                    selectedIds.add(pdf.id)
                                }
                            },
                            onDelete = { deleteTarget = pdf },
                            onShare = { sharePdf(context, pdf) },
                            onRename = { renameTarget = pdf },
                            onPrint = { printPdf(context, pdf) },
                            onMove = { moveTarget = pdf }
                        )
                        Spacer(Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}
@Composable
fun CreateFolderDialog(onDismiss: () -> Unit, onCreate: (String) -> Unit) {
    var name by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) { focusRequester.requestFocus() }
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp), color = CardBg) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Create folder", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(14.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Folder name", color = TextGray) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = BlueAccent,
                        unfocusedBorderColor = Color(0xFF3A3B44)
                    )
                )
                Spacer(Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF1C1D24))
                            .clickable { onDismiss() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Cancel", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (name.isNotBlank()) BlueAccent else Color(0xFF33343D))
                            .clickable(enabled = name.isNotBlank()) { onCreate(name.trim()) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Create", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun FolderRow(name: String, count: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(CardBg)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Folder, contentDescription = null, tint = BlueAccent, modifier = Modifier.size(28.dp))
        Spacer(Modifier.width(14.dp))
        Text(name, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
        Text("$count", color = TextGray, fontSize = 13.sp)
    }
}

@Composable
fun RenamePdfDialog(currentName: String, onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    var name by remember { mutableStateOf(currentName) }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) { focusRequester.requestFocus() }
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp), color = CardBg) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Rename PDF", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(14.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = BlueAccent,
                        unfocusedBorderColor = Color(0xFF3A3B44)
                    )
                )
                Spacer(Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismiss) { Text("CANCEL", color = BlueAccent, fontWeight = FontWeight.Bold) }
                    Spacer(Modifier.width(8.dp))
                    TextButton(onClick = { if (name.isNotBlank()) onConfirm(name.trim()) }) {
                        Text("OK", color = BlueAccent, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun DeletePdfConfirmDialog(pdfName: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp), color = CardBg) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Delete this PDF?", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(14.dp))
                Text(
                    "'$pdfName' will be permanently deleted from your device. This action cannot be undone.",
                    color = TextGray,
                    fontSize = 15.sp
                )
                Spacer(Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismiss) { Text("CANCEL", color = BlueAccent, fontWeight = FontWeight.Bold) }
                    Spacer(Modifier.width(8.dp))
                    TextButton(onClick = onConfirm) { Text("DELETE", color = Color(0xFFB3261E), fontWeight = FontWeight.Bold) }
                }
            }
        }
    }
}

@Composable
fun MoveToFolderSheet(
    pdfName: String,
    folders: List<String>,
    currentFolder: String?,
    onDismiss: () -> Unit,
    onMoveToFolder: (String) -> Unit,
    onCreateFolderAndMove: (String) -> Unit,
    onRemoveFromFolder: () -> Unit
) {
    var creatingNew by remember { mutableStateOf(false) }
    var newFolderName by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onDismiss() },
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {  },
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = CardBg
            ) {
                Column(modifier = Modifier.padding(20.dp).heightIn(max = 480.dp)) {
                    if (!creatingNew) {
                        Text("Move \"$pdfName\"", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                        Spacer(Modifier.height(16.dp))
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable { creatingNew = true }
                                    .padding(vertical = 14.dp, horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.CreateNewFolder, contentDescription = null, tint = BlueAccent)
                                Spacer(Modifier.width(14.dp))
                                Text("New folder and move", color = BlueAccent, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            }
                            if (currentFolder != null) {
                                HorizontalDivider(color = Color(0xFF3A3B44))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(12.dp))
                                        .clickable { onRemoveFromFolder() }
                                        .padding(vertical = 14.dp, horizontal = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Close, contentDescription = null, tint = TextGray)
                                    Spacer(Modifier.width(14.dp))
                                    Text("Remove from folder", color = Color.White, fontSize = 15.sp)
                                }
                            }
                            if (folders.isNotEmpty()) {
                                HorizontalDivider(color = Color(0xFF3A3B44))
                            }
                            folders.forEach { folderName ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(12.dp))
                                        .clickable(enabled = folderName != currentFolder) { onMoveToFolder(folderName) }
                                        .padding(vertical = 14.dp, horizontal = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        Icons.Default.Folder,
                                        contentDescription = null,
                                        tint = if (folderName == currentFolder) TextGray else BlueAccent
                                    )
                                    Spacer(Modifier.width(14.dp))
                                    Text(
                                        folderName,
                                        color = if (folderName == currentFolder) TextGray else Color.White,
                                        fontSize = 15.sp,
                                        modifier = Modifier.weight(1f)
                                    )
                                    if (folderName == currentFolder) {
                                        Text("Current", color = TextGray, fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF1C1D24))
                                .clickable { onDismiss() },
                            contentAlignment = Alignment.Center
                        ) {
                            Text("CANCEL", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    } else {
                        LaunchedEffect(Unit) { focusRequester.requestFocus() }
                        Text("New folder", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(16.dp))
                        OutlinedTextField(
                            value = newFolderName,
                            onValueChange = { newFolderName = it },
                            placeholder = { Text("Folder name", color = TextGray) },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedBorderColor = BlueAccent,
                                unfocusedBorderColor = Color(0xFF3A3B44)
                            )
                        )
                        Spacer(Modifier.height(20.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF1C1D24))
                                    .clickable { creatingNew = false },
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Back", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (newFolderName.isNotBlank()) BlueAccent else Color(0xFF33343D))
                                    .clickable(enabled = newFolderName.isNotBlank()) {
                                        onCreateFolderAndMove(newFolderName.trim())
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Done", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PdfOptionsSheet(
    pdf: PdfFile,
    thumbnail: Bitmap?,
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
    onShare: () -> Unit,
    onRename: () -> Unit,
    onPrint: () -> Unit,
    onMove: () -> Unit
) {
    val pdfSheetNestedScrollConnection = remember {
        object : NestedScrollConnection {
            var totalOverscroll = 0f
            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                if (available.y > 0f) {
                    totalOverscroll += available.y
                    if (totalOverscroll > 300f) {
                        onDismiss()
                    }
                } else {
                    totalOverscroll = 0f
                }
                return Offset.Zero
            }
        }
    }
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onDismiss() },
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .nestedScroll(pdfSheetNestedScrollConnection)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {  },
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = CardBg
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .heightIn(max = 420.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .width(44.dp)
                                .height(62.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (thumbnail != null) Color.White else Color(0xFFB3261E)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (thumbnail != null) {
                                Image(
                                    bitmap = thumbnail.asImageBitmap(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(6.dp))
                                )
                            } else {
                                Text("PDF", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Spacer(Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(pdf.name, color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                            Spacer(Modifier.height(4.dp))
                            Text(pdf.path, color = TextGray, fontSize = 12.sp, maxLines = 2)
                        }
                    }

                    Spacer(Modifier.height(20.dp))
                    HorizontalDivider(color = Color(0xFF3A3B44))
                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        PdfSheetQuickAction(Icons.Default.Share, "Share") { onDismiss(); onShare() }
                        PdfSheetQuickAction(Icons.Default.DriveFileRenameOutline, "Rename") { onDismiss(); onRename() }
                        PdfSheetQuickAction(Icons.Default.Print, "Print") { onDismiss(); onPrint() }
                        PdfSheetQuickAction(Icons.AutoMirrored.Filled.DriveFileMove, "Move") { onDismiss(); onMove() }
                        PdfSheetQuickAction(Icons.Default.Delete, "Delete", tint = Color(0xFFE55353)) { onDismiss(); onDelete() }
                    }
                }
            }
        }
    }
}

@Composable
fun PdfSheetQuickAction(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    tint: Color = Color.White,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val effectiveTint = if (enabled) tint else Color(0xFF4A4B54)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(enabled = enabled) { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFF33343D)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = label, tint = effectiveTint, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.height(6.dp))
        Text(label, color = if (enabled) (if (tint == Color.White) TextGray else tint) else Color(0xFF4A4B54), fontSize = 12.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitScreen(existingHabit: Habit?, onBack: () -> Unit, onSave: (Habit) -> Unit, onDelete: () -> Unit, initialStartDate: LocalDate = LocalDate.now()) {
    val context = LocalContext.current
    var name by remember { mutableStateOf(existingHabit?.name ?: "") }
    var iconEmoji by remember { mutableStateOf(existingHabit?.iconEmoji ?: "🙂") }
    var colorHex by remember { mutableStateOf(existingHabit?.colorHex ?: 0xFF3B7BF5) }
    var showIconPicker by remember { mutableStateOf(false) }

    var habitDays by remember { mutableStateOf(existingHabit?.habitDays ?: DayOfWeek.values().toSet()) }
    var habitDaysExpanded by remember { mutableStateOf(true) }

    var doItAt by remember { mutableStateOf(existingHabit?.doItAt ?: DoItAt.ANYTIME) }
    var durationMinutes by remember { mutableStateOf(existingHabit?.durationMinutes ?: 15) }
    var startTime by remember { mutableStateOf(existingHabit?.startTime) }
    var endTime by remember { mutableStateOf(existingHabit?.endTime) }
    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }

    var notificationsEnabled by remember { mutableStateOf(existingHabit?.notificationsEnabled ?: false) }
    LaunchedEffect(startTime) {
        if (startTime == null) notificationsEnabled = false
    }
    var endMode by remember { mutableStateOf(existingHabit?.endMode ?: EndMode.OFF) }
    var endDate by remember { mutableStateOf(existingHabit?.endDate) }
    var endAfterDays by remember { mutableStateOf(existingHabit?.endAfterDays ?: 30) }

    var showDatePicker by remember { mutableStateOf(false) }
    var showDaysPicker by remember { mutableStateOf(false) }
    var showAdvanced by remember { mutableStateOf(false) }
    var showOptionsMenu by remember { mutableStateOf(false) }
    var showDeleteConfirm by remember { mutableStateOf(false) }

    BackHandler(onBack = onBack)

    Scaffold(
        containerColor = DarkBg,
        topBar = {
            TopAppBar(
                title = { Text(if (existingHabit != null) "Edit Habit" else "New Habit", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    if (existingHabit != null) {
                        Box {
                            IconButton(onClick = { showOptionsMenu = true }) {
                                Icon(Icons.Default.MoreVert, contentDescription = "Options", tint = Color.White)
                            }
                            DropdownMenu(
                                expanded = showOptionsMenu,
                                onDismissRequest = { showOptionsMenu = false },
                                modifier = Modifier.background(CardBg)
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Delete", color = Color(0xFFE55353), fontWeight = FontWeight.Bold) },
                                    onClick = {
                                        showOptionsMenu = false
                                        showDeleteConfirm = true
                                    }
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBg)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(10.dp))

            Text("HABIT NAME", color = TextGray, fontSize = 14.sp)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("e.g. Drink water", color = TextGray) },
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(colorHex))
                            .clickable { showIconPicker = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(iconForKey(iconEmoji), contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = BlueAccent,
                    unfocusedBorderColor = Color(0xFF3A3B44)
                )
            )

            if (showIconPicker) {
                IconPickerDialog(
                    currentIcon = iconEmoji,
                    onDismiss = { showIconPicker = false },
                    onSelect = { emoji ->
                        iconEmoji = emoji
                        showIconPicker = false
                    }
                )
            }

            Spacer(Modifier.height(24.dp))
Text("REPEAT", color = TextGray, fontSize = 13.sp)
Spacer(Modifier.height(8.dp))
HabitDaysCard(
    habitDays = habitDays,
    expanded = habitDaysExpanded,
    onHeaderClick = { habitDaysExpanded = !habitDaysExpanded },
    onDaysChange = { habitDays = it }
)

            Spacer(Modifier.height(24.dp))
Text("DO IT AT", color = TextGray, fontSize = 13.sp)
Spacer(Modifier.height(8.dp))
DoItAtGrid(selected = doItAt, onSelect = { doItAt = it })

Spacer(Modifier.height(24.dp))
Text("DURATION", color = TextGray, fontSize = 14.sp)
Spacer(Modifier.height(8.dp))
Column(
    modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(CardBg)
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { showStartTimePicker = true }.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Start time", color = Color.White, fontWeight = FontWeight.Medium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.AccessTime, contentDescription = null, tint = TextGray, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text(startTime?.format(DateTimeFormatter.ofPattern("HH:mm")) ?: "Select time", color = TextGray)
            Spacer(Modifier.width(if (startTime != null) 30.dp else 6.dp))
            if (startTime != null) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Clear start time",
                    tint = TextGray,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            startTime = null
                            endTime = null
                        }
                )
            } else {
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGray)
            }
        }
    }
    HorizontalDivider(color = Color(0xFF3A3B44))
    Row(
        modifier = Modifier.fillMaxWidth().clickable {
            if (startTime == null) {
                Toast.makeText(context, "Please select a start time first", Toast.LENGTH_SHORT).show()
            } else {
                showEndTimePicker = true
            }
        }.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("End time", color = if (startTime == null) TextGray else Color.White, fontWeight = FontWeight.Medium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.AccessTime, contentDescription = null, tint = TextGray, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text(endTime?.format(DateTimeFormatter.ofPattern("HH:mm")) ?: "Select time", color = TextGray)
            Spacer(Modifier.width(if (endTime != null) 30.dp else 6.dp))
            if (endTime != null) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Clear end time",
                    tint = TextGray,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { endTime = null }
                )
            } else {
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGray)
            }
        }
    }
}
            Spacer(Modifier.height(24.dp))
            Text("REMINDER", color = TextGray, fontSize = 14.sp)
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(CardBg)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Reminder for this habit", color = Color.White, fontWeight = FontWeight.Medium)
                    if (startTime == null) {
                        Spacer(Modifier.height(4.dp))
                        Text("Set a start time to enable reminders", color = TextGray, fontSize = 12.sp)
                    }
                }
                Switch(
                    checked = notificationsEnabled && startTime != null,
                    onCheckedChange = { checked ->
                        notificationsEnabled = checked
                        if (checked && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                            if (!alarmManager.canScheduleExactAlarms()) {
                                val settingsIntent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                                    data = Uri.parse("package:${context.packageName}")
                                }
                                context.startActivity(settingsIntent)
                            }
                        }
                    },
                    enabled = startTime != null,
                    colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = BlueAccent)
                )
            }

            Spacer(Modifier.height(24.dp))
Text("END ON", color = TextGray, fontSize = 13.sp)
Spacer(Modifier.height(8.dp))
Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
    listOf(EndMode.OFF to "OFF", EndMode.DATE to "DATE", EndMode.DAYS to "DAYS").forEach { (mode, label) ->
        Box(
            modifier = Modifier
                .weight(1f)
                .height(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(if (endMode == mode) BlueAccent else Color(0xFF33343D))
                .clickable { endMode = mode },
            contentAlignment = Alignment.Center
        ) {
            Text(label, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

Spacer(Modifier.height(12.dp))
when (endMode) {
    EndMode.DATE -> Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBg)
            .clickable { showDatePicker = true }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Last day is...", color = Color.White, fontWeight = FontWeight.Medium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                endDate?.format(DateTimeFormatter.ofPattern("MMM d, yyyy")) ?: "Select date",
                color = TextGray
            )
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGray)
        }
    }
    EndMode.DAYS -> Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBg)
            .clickable { showDaysPicker = true }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("After X days", color = Color.White, fontWeight = FontWeight.Medium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("$endAfterDays", color = TextGray)
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGray)
        }
    }
    EndMode.OFF -> {}
}

            Spacer(Modifier.height(36.dp))
            Button(
    onClick = {
        if (name.isNotBlank()) {
            onSave(
                Habit(
                    id = existingHabit?.id ?: System.currentTimeMillis(),
                    name = name,
                    iconEmoji = iconEmoji,
                    colorHex = colorHex,
                    habitDays = habitDays,
                    doItAt = doItAt,
                    durationMinutes = durationMinutes,
                    startTime = startTime,
                    endTime = endTime,
                    notificationsEnabled = notificationsEnabled,
                    endMode = endMode,
                    endDate = if (endMode == EndMode.DATE) endDate else null,
                    endAfterDays = if (endMode == EndMode.DAYS) endAfterDays else null,
                    createdAt = existingHabit?.createdAt ?: initialStartDate
                )
            )
        }
    },
                enabled = name.isNotBlank(),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BlueAccent)
            ) {
                Text(if (existingHabit != null) "SAVE CHANGES" else "SAVE HABIT", fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(30.dp))
        }
    }
    if (showDatePicker) {
        HabitDatePickerDialog(
            onDismiss = { showDatePicker = false },
            onConfirm = { endDate = it; showDatePicker = false }
        )
    }
    if (showDaysPicker) {
        DaysWheelPickerSheet(
            currentValue = endAfterDays,
            range = 1..365,
            onDismiss = { showDaysPicker = false },
            onSave = { endAfterDays = it; showDaysPicker = false }
        )
    }
    if (showStartTimePicker) {
        HabitTimePickerDialog(
            onDismiss = { showStartTimePicker = false },
            onConfirm = { startTime = it; showStartTimePicker = false }
        )
    }
    if (showEndTimePicker) {
        HabitTimePickerDialog(
            onDismiss = { showEndTimePicker = false },
            onConfirm = { endTime = it; showEndTimePicker = false }
        )
    }
    if (showDeleteConfirm) {
        DeleteConfirmDialog(
            habitName = existingHabit?.name ?: "",
            onDismiss = { showDeleteConfirm = false },
            onConfirm = {
                showDeleteConfirm = false
                onDelete()
            }
        )
    }
}

@Composable
fun IconPickerDialog(
    currentIcon: String,
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit
) {
    var selectedIcon by remember { mutableStateOf(currentIcon) }
    val iconPickerNestedScrollConnection = remember {
        object : NestedScrollConnection {
            var totalOverscroll = 0f
            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                if (available.y > 0f) {
                    totalOverscroll += available.y
                    if (totalOverscroll > 300f) {
                        onDismiss()
                    }
                } else {
                    totalOverscroll = 0f
                }
                return Offset.Zero
            }
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onDismiss() },
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .nestedScroll(iconPickerNestedScrollConnection)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {  },
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = CardBg
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Choose icon", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .heightIn(max = 198.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        HabitIconOptions.chunked(4).forEach { rowIcons ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                rowIcons.forEach { (key, vector) ->
                                    val isSelected = key == selectedIcon
                                    Box(
                                        modifier = Modifier
                                            .size(52.dp)
                                            .clip(CircleShape)
                                            .background(if (isSelected) BlueAccent else Color(0xFF33343D))
                                            .clickable(
                                                indication = null,
                                                interactionSource = remember { MutableInteractionSource() }
                                            ) { selectedIcon = key },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(vector, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                                    }
                                }
                            }
                            Spacer(Modifier.height(14.dp))
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xFF33343D))
                                .clickable { onDismiss() }
                                .padding(horizontal = 24.dp, vertical = 12.dp)
                        ) {
                            Text("CANCEL", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(BlueAccent)
                                .clickable { onSelect(selectedIcon) }
                                .padding(horizontal = 24.dp, vertical = 12.dp)
                        ) {
                            Text("SAVE", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DoItAtGrid(selected: DoItAt, onSelect: (DoItAt) -> Unit) {
    @Composable
    fun cell(label: String, value: DoItAt, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier) {
        val isSelected = selected == value
        Box(
            modifier = modifier
                .height(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(if (isSelected) BlueAccent else Color(0xFF33343D))
                .clickable { onSelect(value) },
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text(label, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        cell("All", DoItAt.ANYTIME, Icons.Filled.Apps, Modifier.weight(1f))
            cell("Morning", DoItAt.MORNING, Icons.Filled.WbTwilight, Modifier.weight(1f))
        }
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            cell("Afternoon", DoItAt.AFTERNOON, Icons.Filled.LightMode, Modifier.weight(1f))
            cell("Evening", DoItAt.EVENING, Icons.Filled.NightsStay, Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitTimePickerDialog(initialTime: LocalTime? = null, onDismiss: () -> Unit, onConfirm: (LocalTime) -> Unit) {
    val now = LocalTime.now()
    val state = rememberTimePickerState(
        initialHour = initialTime?.hour ?: now.hour,
        initialMinute = initialTime?.minute ?: now.minute,
        is24Hour = false
    )
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp), color = CardBg) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(state = state)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) { Text("Cancel") }
                    TextButton(onClick = { onConfirm(LocalTime.of(state.hour, state.minute)) }) { Text("OK") }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitDatePickerDialog(onDismiss: () -> Unit, onConfirm: (LocalDate) -> Unit) {
    val state = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                state.selectedDateMillis?.let { millis ->
                    val date = Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate()
                    onConfirm(date)
                }
            }) { Text("OK") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    ) {
        DatePicker(state = state)
    }
}
@Composable
fun HabitDaysCard(
    habitDays: Set<DayOfWeek>,
    expanded: Boolean,
    onHeaderClick: () -> Unit,
    onDaysChange: (Set<DayOfWeek>) -> Unit
) {
    val allDays = DayOfWeek.values().toList()
    val configuration = LocalConfiguration.current
    val currentLocale = remember(configuration) { configuration.locales[0] }
    val weekdaySet = setOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)
    val weekendSet = setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
    val summary = when {
        habitDays.size == 7 -> "Everyday"
        habitDays.isEmpty() -> "No days selected"
        habitDays == weekdaySet -> "Weekdays"
        habitDays == weekendSet -> "Weekend"
        else -> {
            val missing = allDays.filterNot { it in habitDays }
            if (missing.size <= 3) {
                "Everyday except " + missing.joinToString(", ") {
                    it.getDisplayName(TextStyle.SHORT, currentLocale)
                }
            } else {
                habitDays.sortedBy { it.value }.joinToString(", ") {
                    it.getDisplayName(TextStyle.SHORT, currentLocale)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onHeaderClick() }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Habit days", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(summary, color = TextGray, fontSize = 14.sp)
            }
            Icon(
                if (expanded) Icons.Default.ChevronRight else Icons.Default.ChevronRight,
                contentDescription = null,
                tint = TextGray
            )
        }

        if (expanded) {
            HorizontalDivider(color = Color(0xFF3A3B44))
            Column(modifier = Modifier.padding(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    allDays.forEach { day ->
                        val selected = day in habitDays
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (selected) BlueAccent else Color(0xFF33343D))
                                .clickable {
                                    onDaysChange(
                                        if (selected) habitDays - day else habitDays + day
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                day.getDisplayName(TextStyle.SHORT, currentLocale).take(1),
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                   }
            }
        }
    }
}
