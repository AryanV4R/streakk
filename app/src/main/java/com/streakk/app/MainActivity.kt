package com.streakk.app

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.widget.Toast
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.RunCircle
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.Pool
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.animation.Crossfade
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.Animatable
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
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.abs
import kotlin.math.roundToInt
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
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import java.text.SimpleDateFormat
import java.util.Date
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import org.json.JSONArray
import org.json.JSONObject
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.window.DialogWindowProvider
import android.view.WindowManager
import androidx.core.view.WindowCompat
import android.Manifest
import android.content.ActivityNotFoundException
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.media.AudioAttributes
import android.media.SoundPool
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.IntentFilter
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPosition
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
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.DriveFileMove
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.res.painterResource
import java.io.File

// ---------- Colors ----------
val DarkBg = Color(0xFF121218)
val CardBg = Color(0xFF23242C)
val ChipBg = Color(0xFF23242C)
val BlueAccent = Color(0xFF3B7BF5)
val TextGray = Color(0xFF8A8B93)

// ---------- Font ----------
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

// ---------- Data ----------
enum class Screen { TASKS, PDFS, SETTINGS }

enum class HabitStatus { ACTIVE, DONE, SKIPPED }

enum class DoItAt { ANYTIME, MORNING, AFTERNOON, EVENING }
enum class EndMode { OFF, DATE, DAYS }
enum class GoalType { DURATION, COUNT } // extend later if needed

// Habit ka end-condition check karta hai — TasksScreen filter aur reminder scheduler
// dono isi single function ko use karte hain, taaki dono jagah ka logic hamesha sync rahe.
fun isHabitActiveOn(date: LocalDate, endMode: EndMode, endDate: LocalDate?, endAfterDays: Int?, createdAt: LocalDate): Boolean {
    return when (endMode) {
        EndMode.DATE -> endDate == null || !date.isAfter(endDate)
        EndMode.DAYS -> endAfterDays == null || !date.isAfter(createdAt.plusDays(endAfterDays.toLong()))
        EndMode.OFF -> !date.isAfter(createdAt.plusDays(45))
    }
}

// ---------- Habit icon registry (Material Symbols, replaces raw emoji) ----------
val HabitIconOptions: List<Pair<String, androidx.compose.ui.graphics.vector.ImageVector>> = listOf(
    "smile" to Icons.Filled.EmojiEmotions,
    "water" to Icons.Filled.WaterDrop,
    "run" to Icons.Filled.DirectionsRun,
    "book" to Icons.Filled.MenuBook,
    "meditate" to Icons.Filled.SelfImprovement,
    "food" to Icons.Filled.Restaurant,
    "sleep" to Icons.Filled.Bedtime,
    "workout" to Icons.Filled.FitnessCenter,
    "yoga" to Icons.Filled.Accessibility,
    "music" to Icons.Filled.MusicNote,
    "study" to Icons.Filled.School,
    "work" to Icons.Filled.Work,
    "code" to Icons.Filled.Code,
    "art" to Icons.Filled.Palette,
    "shopping" to Icons.Filled.ShoppingCart,
    "cleaning" to Icons.Filled.CleaningServices,
    "pet" to Icons.Filled.Pets,
    "phone" to Icons.Filled.PhoneAndroid,
    "journal" to Icons.Filled.EditNote,
    "money" to Icons.Filled.AttachMoney,
    "plant" to Icons.Filled.LocalFlorist,
    "coffee" to Icons.Filled.LocalCafe,
    "bike" to Icons.Filled.DirectionsBike,
    "swim" to Icons.Filled.Pool,
    "movie" to Icons.Filled.Movie,
    "game" to Icons.Filled.SportsEsports,
    "travel" to Icons.Filled.Flight,
    "star" to Icons.Filled.Star
)
val HabitIconMap: Map<String, androidx.compose.ui.graphics.vector.ImageVector> = HabitIconOptions.toMap()
fun iconForKey(key: String): androidx.compose.ui.graphics.vector.ImageVector = HabitIconMap[key] ?: Icons.Filled.EmojiEmotions

data class Habit(
    val id: Long,
    val name: String,
    val iconEmoji: String = "smile",
    val colorHex: Long = 0xFF3B7BF5,
    val habitDays: Set<DayOfWeek> = DayOfWeek.values().toSet(), // "Everyday" default
    val doItAt: DoItAt = DoItAt.ANYTIME,
    val goalType: GoalType = GoalType.DURATION,
    val durationMinutes: Int = 15,
    val notificationsEnabled: Boolean = false,
    val endMode: EndMode = EndMode.OFF,
    val endDate: LocalDate? = null,
    val endAfterDays: Int? = null,
    val createdAt: LocalDate = LocalDate.now(),
    // legacy fields kept so old habits don't break — safe to remove later
    val frequency: String = "Daily",
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null
)

data class TodoTask(
    val id: Long,
    val text: String,
    val date: LocalDate,
    val reminderEnabled: Boolean = false,
    val reminderTime: LocalTime? = null,
    val completed: Boolean = false
)
// ---------- Persistence ----------
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
// ---- Habit status (done/skipped) persistence ----
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

// ---------- Habit reminder notifications ----------
// AlarmManager natively "sirf in weekdays pe repeat karo" support nahi karta,
// isliye har reminder ek single exact one-shot alarm hai. Jab wo fire hota hai,
// HabitReminderReceiver notification dikhata hai aur turant agla occurrence khud
// schedule kar deta hai — sab kuch Intent extras mein carry hota hai, isliye
// app-process kill hone ke baad bhi ye chain reliably chalti rehti hai.
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

    // "days" mein se next date/time (aaj ya aage) dhoondhta hai jo hour:minute pe pade.
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

    fun showNotification(context: Context, habitId: Long, habitName: String, iconEmoji: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val openAppIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            habitId.toInt(),
            openAppIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val largeIcon = ContextCompat.getDrawable(context, R.mipmap.ic_launcher)?.toBitmap()
        val message = "Don't break the chain — mark \"$habitName\" as done to keep your streak alive."
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setColor(android.graphics.Color.parseColor("#3B7BF5"))
            .setLargeIcon(largeIcon)
            .setContentTitle(habitName)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(contentPendingIntent)
            .build()
        NotificationManagerCompat.from(context).notify(habitId.toInt(), notification)
    }

    // Reboot pe AlarmManager saare alarms bhula deta hai, isliye ye sabko wapas schedule karta hai.
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
        val habitId = intent.getLongExtra("habitId", -1L)
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

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            HabitReminderScheduler.rescheduleAll(context)
        }
    }
}
// ---------- To-do reminder notifications ----------
// Habit reminders se simpler hai: ek to-do ek hi specific date se bandha hota
// hai, isliye ye hamesha ek single one-shot alarm hai — repeat/reschedule
// karne ki zaroorat nahi (habit ke opposite).
object TodoReminderScheduler {
    private const val CHANNEL_ID = "todo_reminders"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "To-do reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Reminders for your to-do tasks"
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun pendingIntentFor(context: Context, todoId: Long, text: String): PendingIntent {
        val intent = Intent(context, TodoReminderReceiver::class.java).apply {
            putExtra("todoId", todoId)
            putExtra("todoText", text)
        }
        return PendingIntent.getBroadcast(
            context,
            todoId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    fun schedule(context: Context, todo: TodoTask) {
        cancel(context, todo.id)
        val time = todo.reminderTime ?: return
        if (!todo.reminderEnabled) return
        val triggerAt = todo.date.atTime(time).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        if (triggerAt <= System.currentTimeMillis()) return // waqt beet chuka, alarm mat lagao
        val pendingIntent = pendingIntentFor(context, todo.id, todo.text)
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

    fun cancel(context: Context, todoId: Long) {
        val intent = Intent(context, TodoReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            todoId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    fun showNotification(context: Context, todoId: Long, todoText: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val openAppIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            todoId.toInt(),
            openAppIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val largeIcon = ContextCompat.getDrawable(context, R.mipmap.ic_launcher)?.toBitmap()
        val message = "Don't forget: $todoText"
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setColor(android.graphics.Color.parseColor("#3B7BF5"))
            .setLargeIcon(largeIcon)
            .setContentTitle("Task Reminder")
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(contentPendingIntent)
            .build()
        NotificationManagerCompat.from(context).notify(("todo_" + todoId).hashCode(), notification)
    }

    // Reboot pe AlarmManager saare alarms bhula deta hai, isliye ye sabko wapas schedule karta hai.
    fun rescheduleAll(context: Context) {
        val todos = TodoStorage.load(context)
        todos.forEach { todo ->
            if (todo.reminderEnabled && todo.reminderTime != null && !todo.completed) {
                schedule(context, todo)
            }
        }
    }
}

class TodoReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val todoId = intent.getLongExtra("todoId", -1L)
        val todoText = intent.getStringExtra("todoText") ?: return
        if (todoId == -1L) return
        TodoReminderScheduler.createNotificationChannel(context)
        TodoReminderScheduler.showNotification(context, todoId, todoText)
    }
}
object TodoStorage {
    private const val PREFS_NAME = "habit_prefs"
    private const val KEY_TODOS = "todos_json"

    fun save(context: Context, todos: List<TodoTask>) {
        val array = JSONArray()
        todos.forEach { todo ->
            val obj = JSONObject()
            obj.put("id", todo.id)
            obj.put("text", todo.text)
            obj.put("date", todo.date.toString())
            obj.put("reminderEnabled", todo.reminderEnabled)
            obj.put("reminderTime", todo.reminderTime?.toString() ?: JSONObject.NULL)
            obj.put("completed", todo.completed)
            array.put(obj)
        }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_TODOS, array.toString())
            .apply()
    }

    fun load(context: Context): List<TodoTask> {
        val json = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_TODOS, null) ?: return emptyList()
        val array = JSONArray(json)
        return (0 until array.length()).map { i ->
            val obj = array.getJSONObject(i)
            TodoTask(
                id = obj.getLong("id"),
                text = obj.getString("text"),
                date = LocalDate.parse(obj.getString("date")),
                reminderEnabled = obj.getBoolean("reminderEnabled"),
                reminderTime = if (obj.isNull("reminderTime")) null else LocalTime.parse(obj.getString("reminderTime")),
                completed = obj.getBoolean("completed")
            )
        }
    }
}
object CompletionSoundPlayer {
    private var soundPool: SoundPool? = null
    private var soundId: Int = 0
    private var loaded = false

    fun init(context: Context) {
        if (soundPool != null) return
        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder().setMaxStreams(1).setAudioAttributes(attrs).build()
        soundId = soundPool!!.load(context, R.raw.task_complete, 1)
        soundPool!!.setOnLoadCompleteListener { _, _, status -> loaded = status == 0 }
    }

    fun play() {
        if (loaded) soundPool?.play(soundId, 1f, 1f, 1, 0, 1f)
    }

    fun release() {
        soundPool?.release()
        soundPool = null
        loaded = false
    }
}
object SettingsStorage {
    private const val PREFS_NAME = "habit_prefs"
    private const val KEY_FIRST_DAY = "first_day_of_week"
    private const val KEY_SHOW_STREAK = "show_streak_count"

    fun loadFirstDayOfWeek(context: Context): DayOfWeek {
        val name = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_FIRST_DAY, DayOfWeek.MONDAY.name)
        return DayOfWeek.valueOf(name ?: DayOfWeek.MONDAY.name)
    }

    fun saveFirstDayOfWeek(context: Context, day: DayOfWeek) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_FIRST_DAY, day.name)
            .apply()
    }

    fun loadShowStreakCount(context: Context): Boolean {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_SHOW_STREAK, true)
    }

    fun saveShowStreakCount(context: Context, value: Boolean) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_SHOW_STREAK, value)
            .apply()
    }

    private const val KEY_SOUND_ON_COMPLETE = "sound_on_complete"

    fun loadSoundOnComplete(context: Context): Boolean {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_SOUND_ON_COMPLETE, true)
    }

    fun saveSoundOnComplete(context: Context, value: Boolean) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_SOUND_ON_COMPLETE, value)
            .apply()
    }

    private const val KEY_SORT_ORDER = "pdf_default_sort"
    private const val KEY_AUTO_SCAN = "pdf_auto_scan"
    private const val KEY_PDF_APP = "pdf_preferred_app"
    private const val KEY_FILE_SIZE_UNIT = "pdf_file_size_unit"

    fun loadDefaultSortOrder(context: Context): PdfSortOption {
        val name = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_SORT_ORDER, PdfSortOption.NEWEST.name)
        return try { PdfSortOption.valueOf(name ?: PdfSortOption.NEWEST.name) } catch (e: Exception) { PdfSortOption.NEWEST }
    }

    fun saveDefaultSortOrder(context: Context, option: PdfSortOption) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_SORT_ORDER, option.name)
            .apply()
    }

    fun loadAutoScan(context: Context): Boolean {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_AUTO_SCAN, true)
    }

    fun saveAutoScan(context: Context, value: Boolean) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_AUTO_SCAN, value)
            .apply()
    }

    fun loadPreferredPdfApp(context: Context): String? {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_PDF_APP, null)
    }

    fun savePreferredPdfApp(context: Context, packageName: String?) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_PDF_APP, packageName)
            .apply()
    }

    fun loadFileSizeUnit(context: Context): FileSizeUnit {
        val name = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_FILE_SIZE_UNIT, FileSizeUnit.AUTO.name)
        return try { FileSizeUnit.valueOf(name ?: FileSizeUnit.AUTO.name) } catch (e: Exception) { FileSizeUnit.AUTO }
    }

    fun saveFileSizeUnit(context: Context, unit: FileSizeUnit) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_FILE_SIZE_UNIT, unit.name)
            .apply()
    }

    private const val KEY_APP_LOCK_ENABLED = "app_lock_enabled"
    private const val KEY_AUTO_LOCK_TIMEOUT = "auto_lock_timeout"

    fun loadAppLockEnabled(context: Context): Boolean {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_APP_LOCK_ENABLED, false)
    }

    fun saveAppLockEnabled(context: Context, value: Boolean) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_APP_LOCK_ENABLED, value)
            .apply()
    }

    fun loadAutoLockTimeout(context: Context): AutoLockTimeout {
        val name = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_AUTO_LOCK_TIMEOUT, AutoLockTimeout.INSTANT.name)
        return try { AutoLockTimeout.valueOf(name ?: AutoLockTimeout.INSTANT.name) } catch (e: Exception) { AutoLockTimeout.INSTANT }
    }

    fun saveAutoLockTimeout(context: Context, option: AutoLockTimeout) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_AUTO_LOCK_TIMEOUT, option.name)
            .apply()
    }
}
class MainActivity : FragmentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setBackgroundDrawable(android.graphics.drawable.ColorDrawable(0xFF121218.toInt()))
        HabitReminderScheduler.createNotificationChannel(this)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme(background = DarkBg), typography = Baloo2Typography) {
                var showSplash by remember { mutableStateOf(true) }
                LaunchedEffect(Unit) {
                    delay(900)
                    showSplash = false
                }
                AnimatedContent(
                    targetState = showSplash,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(400)) togetherWith fadeOut(animationSpec = tween(400))
                    },
                    label = "splashToHomeTransition"
                ) { isSplash ->
                    if (isSplash) {
                        AppSplashScreen()
                    } else {
                        AppRoot()
                    }
                }
            }
        }
    }
}

// ---------- Root: handles navigation ----------
@OptIn(ExperimentalAnimationApi::class)
const val MAX_ACTIVE_HABITS = 7

@Composable
fun AppRoot() {
    val context = LocalContext.current

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { /* denied ho to bhi habit save hoti rahegi, bas notification silently nahi dikhega */ }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)
                .edit().putBoolean("notif_permission_requested", true).apply()
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
        HabitReminderScheduler.rescheduleAll(context)
        TodoReminderScheduler.rescheduleAll(context)
    }

    var currentScreen by remember { mutableStateOf(Screen.TASKS) }
    var showAddHabit by remember { mutableStateOf(false) }
    BackHandler(enabled = !showAddHabit && currentScreen != Screen.TASKS) {
        currentScreen = Screen.TASKS
    }
    var editingHabit by remember { mutableStateOf<Habit?>(null) }
    var showDeletedBanner by remember { mutableStateOf(false) }
    var showHabitLimitBanner by remember { mutableStateOf(false) }
        val habits = remember {
        mutableStateListOf<Habit>().apply { addAll(HabitStorage.load(context)) }
    }
    val habitStatus = remember {
        mutableStateMapOf<Pair<Long, LocalDate>, HabitStatus>().apply { putAll(HabitStorage.loadStatus(context)) }
    }
    val todos = remember {
        mutableStateListOf<TodoTask>().apply { addAll(TodoStorage.load(context)) }
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
    var lastBackgroundedAt by remember { mutableStateOf(0L) }
    val lifecycleOwner = LocalLifecycleOwner.current

    // habits list change hote hi disk pe save
    LaunchedEffect(habits.toList()) {
        HabitStorage.save(context, habits)
    }

    // habit status (done/skipped) change hote hi disk pe save
    LaunchedEffect(habitStatus.toMap()) {
        HabitStorage.saveStatus(context, habitStatus)
    }

    // todos change hote hi disk pe save
    LaunchedEffect(todos.toList()) {
        TodoStorage.save(context, todos)
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

    DisposableEffect(lifecycleOwner, appLockEnabled, autoLockTimeout) {
        val lockObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> {
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
            if (targetState) {
                (slideInHorizontally(animationSpec = tween(320)) { fullWidth -> fullWidth } + fadeIn(animationSpec = tween(320)))
                    .togetherWith(slideOutHorizontally(animationSpec = tween(320)) { fullWidth -> -fullWidth / 4 } + fadeOut(animationSpec = tween(320)))
            } else {
                (slideInHorizontally(animationSpec = tween(320)) { fullWidth -> -fullWidth / 4 } + fadeIn(animationSpec = tween(320)))
                    .togetherWith(slideOutHorizontally(animationSpec = tween(320)) { fullWidth -> fullWidth } + fadeOut(animationSpec = tween(320)))
            }
        },
        label = "addHabitTransition"
    ) { isAddHabit ->
        if (isAddHabit) {
            AddHabitScreen(
                existingHabit = editingHabit,
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
                        val keysToRemove = habitStatus.keys.filter { it.first == habitId }
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
                    Crossfade(
                        targetState = currentScreen,
                        animationSpec = tween(220),
                        label = "tabSwitchTransition"
                    ) { screen ->
                    when (screen) {
                                            Screen.TASKS -> TasksScreen(
                            habits = habits,
                            habitStatus = habitStatus,
                            todos = todos,
                            onAddHabit = {
                                if (habits.size >= MAX_ACTIVE_HABITS) {
                                    showHabitLimitBanner = true
                                } else {
                                    editingHabit = null
                                    showAddHabit = true
                                }
                            },
                            onEditHabit = { habit -> editingHabit = habit; showAddHabit = true },
                            showDeletedBanner = showDeletedBanner,
                            onDismissDeletedBanner = { showDeletedBanner = false },
                            showHabitLimitBanner = showHabitLimitBanner,
                            onDismissHabitLimitBanner = { showHabitLimitBanner = false },
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
                            },
                            bottomContentPadding = padding.calculateBottomPadding(),
                            firstDayOfWeek = firstDayOfWeek,
                            onFirstDayOfWeekChange = { firstDayOfWeek = it },
                            showStreakCount = showStreakCount,
                            onShowStreakCountChange = { showStreakCount = it },
                            soundOnComplete = soundOnComplete,
                            onSoundOnCompleteChange = { soundOnComplete = it },
                            defaultSortOption = defaultSortOption,
                            onDefaultSortOptionChange = { defaultSortOption = it },
                            autoScanEnabled = autoScanEnabled,
                            onAutoScanEnabledChange = { autoScanEnabled = it },
                            preferredPdfPackage = preferredPdfPackage,
                            onPreferredPdfPackageChange = { preferredPdfPackage = it },
                            fileSizeUnit = fileSizeUnit,
                            onFileSizeUnitChange = { fileSizeUnit = it },
                            appLockEnabled = appLockEnabled,
                            onAppLockEnabledChange = { appLockEnabled = it },
                            autoLockTimeout = autoLockTimeout,
                            onAutoLockTimeoutChange = { autoLockTimeout = it }
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
fun BottomNavBar(current: Screen, onSelect: (Screen) -> Unit) {
    NavigationBar(
        containerColor = Color(0xFF1C1D24),
        modifier = Modifier.height(64.dp)
    ) {
        NavigationBarItem(
            selected = current == Screen.TASKS,
            onClick = { onSelect(Screen.TASKS) },
            icon = { Icon(Icons.Filled.CheckCircle, contentDescription = "Tasks", modifier = Modifier.size(22.dp)) },
            label = { Text("Tasks", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
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
            icon = { Icon(Icons.Filled.Description, contentDescription = "PDFs", modifier = Modifier.size(22.dp)) },
            label = { Text("PDFs", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                indicatorColor = BlueAccent,
                unselectedIconColor = TextGray,
                unselectedTextColor = TextGray
            )
        )
        NavigationBarItem(
            selected = current == Screen.SETTINGS,
            onClick = { onSelect(Screen.SETTINGS) },
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Me", modifier = Modifier.size(22.dp)) },
            label = { Text("Me", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
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

// ---------- Settings shared row/card building blocks ----------
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
// ---------- ME / Settings screen ----------
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
    onAutoLockTimeoutChange: (AutoLockTimeout) -> Unit = {}
) {
    var showGeneralSettings by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var showDeleteConfirm by remember { mutableStateOf(false) }
    var showPrivacyPolicy by remember { mutableStateOf(false) }
    var showPermissions by remember { mutableStateOf(false) }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBg)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(20.dp))
            Text("ME", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(20.dp))

            PrivacyBadgeCard()

            Spacer(Modifier.height(16.dp))

            SettingsCard {
                SettingsRow(
                    title = "Permissions needed",
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
                        // TESTING ONLY: same placeholder Play Store link used in "Rate us" above.
                        // Swap the id in both places once your real app is published.
                        val shareText = "Building better habits, one day at a time 💪 Try Streakk — a simple habit & to-do tracker:\nhttps://play.google.com/store/apps/details?id=com.whatsapp"
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
                            data = Uri.parse("mailto:idabvx@protonmail.com?subject=" + Uri.encode("Feedback - Streakk App"))
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("idabvx@protonmail.com"))
                            putExtra(Intent.EXTRA_SUBJECT, "Feedback - Streakk App")
                            setPackage("com.google.android.gm")
                        }
                        try {
                            context.startActivity(emailIntent)
                        } catch (e: ActivityNotFoundException) {
                            // Gmail not installed on this device -> fall back to any mail app
                            emailIntent.setPackage(null)
                            try {
                                context.startActivity(emailIntent)
                            } catch (e2: ActivityNotFoundException) { /* no mail app at all */ }
                        }
                    }
                )
                HorizontalDivider(color = Color(0xFF3A3B44))
                SettingsRow(
                    title = "Rate us",
                    icon = Icons.Default.Star,
                    iconBg = Color(0xFF3B7BF5),
                    onClick = {
                        // TESTING ONLY: pointing at a popular app's Play Store page.
                        // Swap "com.whatsapp" for your real applicationId once published.
                        val playIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=com.whatsapp")
                        )
                        try {
                            context.startActivity(playIntent)
                        } catch (e: ActivityNotFoundException) {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                                )
                            )
                        }
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
                "Version 0.0.1",
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
                    Text("This will permanently delete all your habits and to-dos from this app. Your PDFs will not be affected. This cannot be undone.")
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
@Composable
fun PermissionsScreen(onBack: () -> Unit, bottomContentPadding: Dp = 0.dp) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    fun checkNotifications() = Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
        ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
    fun checkExactAlarm() = Build.VERSION.SDK_INT < Build.VERSION_CODES.S ||
        (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager).canScheduleExactAlarms()

    var notificationsGranted by remember { mutableStateOf(checkNotifications()) }
    var exactAlarmGranted by remember { mutableStateOf(checkExactAlarm()) }
    var storageGranted by remember { mutableStateOf(hasStorageAccess(context)) }

    // Settings app se wapas aane pe har permission ka status turant refresh karo.
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                notificationsGranted = checkNotifications()
                exactAlarmGranted = checkExactAlarm()
                storageGranted = hasStorageAccess(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> notificationsGranted = granted }

    val storageActivityLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { storageGranted = hasStorageAccess(context) }

    val legacyStorageLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> storageGranted = granted }

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
                Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.clickable { onBack() }
            )
            Spacer(Modifier.width(16.dp))
            Text("Permissions needed", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
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
                                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
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
                        context.startActivity(intent)
                    }
                }
            )
            HorizontalDivider(color = Color(0xFF3A3B44))
            PermissionToggleRow(
                title = "Storage access",
                subtitle = "Required to locate PDF files on your device",
                checked = storageGranted,
                onToggle = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        val intent = Intent(
                            Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                            Uri.parse("package:${context.packageName}")
                        )
                        storageActivityLauncher.launch(intent)
                    } else {
                        legacyStorageLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
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
                        // This specific settings screen isn't available on this device — fail silently instead of crashing.
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

// Har OEM apna "Autostart" screen alag jagah chhupa ke rakhta hai, aur Android
// ke paas iske liye koi universal API nahi hai. Jaani-maani component names try
// karte hain; agar match na ho to app ki normal App Info settings pe le jaate hain.
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
                Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.clickable { onBack() }
            )
            Spacer(Modifier.width(16.dp))
            Text("Privacy Policy", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(8.dp))
        Text("Last updated: September 2026", color = TextGray, fontSize = 13.sp)
        Spacer(Modifier.height(24.dp))

        sectionBody(
            "Streakk (\"we\", \"our\", \"the app\") is a habit, to-do, and PDF reading app. " +
            "This Privacy Policy explains what information the app handles and how, in plain language."
        )

        sectionTitle("1. Data We Collect")
        sectionBody(
            "Streakk does not collect, transmit, or sell any personal data to us or to any third party. " +
            "We do not run our own servers and have no access to your habits, to-dos, or files. " +
            "Everything you create in the app stays on your device."
        )

        sectionTitle("2. Local Storage")
        sectionBody(
            "Your habits, streaks, to-dos, and app preferences are saved locally on your device using " +
            "Android's standard app storage. This data is only readable by Streakk and is removed " +
            "automatically if you uninstall the app, or manually at any time using \"Delete all data\" " +
            "in Settings."
        )

        sectionTitle("3. Permissions")
        sectionBody(
            "Streakk requests storage access solely to let you browse and open PDF files that already " +
            "exist on your device. We do not read, copy, or upload any file contents — the app simply " +
            "displays files you choose to open. If you enable habit reminders, they are scheduled locally " +
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
            "erase everything at any time from Settings → Delete all data, or by uninstalling the app."
        )

        sectionTitle("7. Changes to This Policy")
        sectionBody(
            "If this policy is ever updated, the new version will be posted here with a revised " +
            "\"Last updated\" date at the top of this page."
        )

        sectionTitle("8. Contact Us")
        sectionBody(
            "If you have any questions about this Privacy Policy, reach out at idabvx@protonmail.com."
        )

        Spacer(Modifier.height(20.dp))
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

@Composable
fun OpenWithDialog(
    apps: List<Pair<String, String>>,
    current: String?,
    onDismiss: () -> Unit,
    onSelect: (String?) -> Unit
) {
    val context = LocalContext.current
    val sheetNestedScrollConnection = remember {
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
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { /* consume clicks so they don't fall through to the outer dismiss */ }
                    .nestedScroll(sheetNestedScrollConnection),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = CardBg
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .heightIn(max = 420.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text("Open PDFs with", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(null) }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val ownAppIcon = remember {
                            try {
                                context.packageManager.getApplicationIcon(context.packageName).toBitmap().asImageBitmap()
                            } catch (e: Exception) {
                                null
                            }
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (ownAppIcon != null) {
                                Image(
                                    bitmap = ownAppIcon,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFF33343D))
                                )
                            }
                            Spacer(Modifier.width(14.dp))
                            Text("Ask every time", color = Color.White, fontSize = 16.sp)
                        }
                        if (current == null) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = BlueAccent)
                        }
                    }
                    apps.forEach { (pkg, label) ->
                        val iconBitmap = remember(pkg) {
                            try {
                                context.packageManager.getApplicationIcon(pkg).toBitmap().asImageBitmap()
                            } catch (e: Exception) {
                                null
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelect(pkg) }
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (iconBitmap != null) {
                                    Image(
                                        bitmap = iconBitmap,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                    )
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color(0xFF33343D))
                                    )
                                }
                                Spacer(Modifier.width(14.dp))
                                Text(label, color = Color.White, fontSize = 16.sp)
                            }
                            if (current == pkg) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = BlueAccent)
                            }
                        }
                    }
                }
            }
        }
    }
}

// ---------- App Lock (fingerprint only) ----------
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

// ---------- Custom Splash Screen ----------
@Composable
fun AppSplashScreen() {
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

    BackHandler { /* block back button while locked */ }

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

@Composable
fun AutoLockTimeoutSheet(
    current: AutoLockTimeout,
    onDismiss: () -> Unit,
    onSelect: (AutoLockTimeout) -> Unit
) {
    var dragOffsetY by remember { mutableStateOf(0f) }
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
                    .offset { IntOffset(0, dragOffsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onVerticalDrag = { change, dragAmount ->
                                if (dragAmount > 0) {
                                    dragOffsetY = (dragOffsetY + dragAmount).coerceAtLeast(0f)
                                    change.consume()
                                }
                            },
                            onDragEnd = {
                                if (dragOffsetY > 150f) onDismiss() else dragOffsetY = 0f
                            },
                            onDragCancel = { dragOffsetY = 0f }
                        )
                    }
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { /* absorb taps so sheet doesn't dismiss when tapped */ },
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = CardBg
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Auto-lock", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(4.dp))
                    Text("Lock the app after it's in the background for:", color = TextGray, fontSize = 13.sp)
                    Spacer(Modifier.height(12.dp))
                    AutoLockTimeout.values().forEach { option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onSelect(option) }
                                .padding(vertical = 14.dp, horizontal = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(option.label, color = Color.White, fontSize = 16.sp)
                            if (option == current) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = BlueAccent)
                            }
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                }
            }
        }
    }
}

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
    onAutoLockTimeoutChange: (AutoLockTimeout) -> Unit = {}
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
    var preferredAppLabel by remember(preferredPdfPackage) {
        mutableStateOf(
            preferredPdfPackage?.let {
                try {
                    context.packageManager.getApplicationLabel(context.packageManager.getApplicationInfo(it, 0)).toString()
                } catch (e: Exception) { null }
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
                Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.clickable { onBack() }
            )
            Spacer(Modifier.width(16.dp))
            Text("General settings", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(24.dp))

        if (showCacheClearedBanner) {
            LaunchedEffect(showCacheClearedBanner) {
                delay(2000)
                showCacheClearedBanner = false
            }
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 12.dp)) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF4CAF50), modifier = Modifier.size(18.dp))
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

        if (showSortDialog) {
            SingleChoiceDialog(
                title = "Default sort order",
                options = PdfSortOption.values().toList(),
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
                options = FileSizeUnit.values().toList(),
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
                            Toast.makeText(context, "Set up fingerprint unlock in your phone's settings first", Toast.LENGTH_LONG).show()
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

// ---------- TASKS screen ----------
// Single source of truth for "which period a habit belongs to":
// agar habit ka startTime set hai to usi ke hour se period decide hoga,
// warna habit.doItAt field use hoga. Filter aur header grouping dono isi ko use karenge.
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

// Returns (completedCount, totalCount) for a given date, counting habits scheduled
// on that date (respecting habitDays / endMode / createdAt) + todos due that date.
// A habit counts as "completed" if it's DONE or SKIPPED (both count toward progress).
fun calculateDayProgress(
    date: LocalDate,
    habits: List<Habit>,
    habitStatus: Map<Pair<Long, LocalDate>, HabitStatus>,
    todos: List<TodoTask>
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
    val scheduledTodos = todos.filter { it.date == date }
    val totalCount = scheduledHabits.size + scheduledTodos.size
    if (totalCount == 0) return 0 to 0

    val completedHabits = scheduledHabits.count { habit ->
        val status = habitStatus[habit.id to date] ?: HabitStatus.ACTIVE
        status == HabitStatus.DONE || status == HabitStatus.SKIPPED
    }
    val completedTodos = scheduledTodos.count { it.completed }
    return (completedHabits + completedTodos) to totalCount
}

// Diye gaye date ke us hafte ka start-date deta hai, jiska pehla din "firstDay" hai.
// .with(DayOfWeek.X) sirf Monday ke liye sahi kaam karta - ye function Sunday-start
// (ya kisi bhi din-start) ke liye bhi sahi peeche ka date nikalta hai.
fun weekStartFor(date: LocalDate, firstDay: DayOfWeek): LocalDate {
    val diff = (date.dayOfWeek.value - firstDay.value + 7) % 7
    return date.minusDays(diff.toLong())
}

// Ek habit ka current streak (consecutive scheduled din jo Done/Skipped rahe hain,
// aaj/kal se peeche count karke) nikalta hai.
fun computeStreak(
    habit: Habit,
    habitStatus: Map<Pair<Long, LocalDate>, HabitStatus>,
    today: LocalDate
): Int {
    var streak = 0
    val todayStatus = habitStatus[habit.id to today] ?: HabitStatus.ACTIVE
    var date = if (todayStatus == HabitStatus.DONE || todayStatus == HabitStatus.SKIPPED) today else today.minusDays(1)
    while (!date.isBefore(habit.createdAt)) {
        if (date.dayOfWeek in habit.habitDays) {
            val status = habitStatus[habit.id to date] ?: HabitStatus.ACTIVE
            if (status == HabitStatus.DONE || status == HabitStatus.SKIPPED) {
                streak++
            } else {
                break
            }
        }
        date = date.minusDays(1)
    }
    return streak
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TasksScreen(
    habits: List<Habit>,
    habitStatus: SnapshotStateMap<Pair<Long, LocalDate>, HabitStatus>,
    todos: SnapshotStateList<TodoTask>,
    onAddHabit: () -> Unit,
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
    val today = remember { LocalDate.now() }
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val currentLocale = remember(configuration) { configuration.locales[0] }
    val currentWeekMonday = remember(today, firstDayOfWeek) { weekStartFor(today, firstDayOfWeek) }
    val centerPage = 5000
    val pagerState = rememberPagerState(initialPage = centerPage) { centerPage * 2 }
    val coroutineScope = rememberCoroutineScope()

    val weekOffset = pagerState.currentPage - centerPage
    val monday = remember(weekOffset) { currentWeekMonday.plusWeeks(weekOffset.toLong()) }

    var selectedDate by remember { mutableStateOf(today) }

    // Swipe-to-change-date: moves selectedDate by exactly 1 day, and
    // silently syncs the week-pager to the right week if the day crosses
    // a week boundary (e.g. Sunday -> next Monday is still just 1 day).
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
    var selectedFilter by remember { mutableStateOf("ALL") }
    var menuOpenForHabitId by remember { mutableStateOf<Long?>(null) }
    var skipConfirmHabit by remember { mutableStateOf<Habit?>(null) }
    var showMonthPicker by remember { mutableStateOf(false) }
    var showAddTodoSheet by remember { mutableStateOf(false) }

    val titleText = when (selectedDate) {
        today -> "TODAY"
        today.minusDays(1) -> "YESTERDAY"
        today.plusDays(1) -> "TOMORROW"
        else -> selectedDate.format(DateTimeFormatter.ofPattern("MMM d", currentLocale)).uppercase()
    }
    val subtitleText = when {
        selectedDate.isBefore(today) -> {
            val (completed, total) = calculateDayProgress(selectedDate, habits, habitStatus, todos)
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        if (showDeletedBanner) {
            LaunchedEffect(showDeletedBanner) {
                delay(2500)
                onDismissDeletedBanner()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF4CAF50), modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("Habit deleted successfully", color = Color.White, fontSize = 15.sp)
            }
        }

        if (showHabitLimitBanner) {
            LaunchedEffect(showHabitLimitBanner) {
                delay(2500)
                onDismissHabitLimitBanner()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Info, contentDescription = null, tint = Color(0xFFFFA726), modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("Maximum $MAX_ACTIVE_HABITS habits reached — delete one to add a new habit", color = Color.White, fontSize = 15.sp)
            }
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(titleText, color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                                                Text(
                    subtitleText,
                    color = TextGray,
                    fontSize = 16.sp,
                    fontFamily = Tajawal,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { showMonthPicker = true }
                )
            }
            if (!selectedDate.isBefore(today)) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(CardBg)
                        .clickable { onAddHabit() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add habit", tint = Color.White)
                }
            }
        }

        Spacer(Modifier.height(20.dp))

                // Fixed weekday-name header - never moves
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
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
                    modifier = Modifier.width(40.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.height(6.dp))

        // Only the DATES swipe - full week always visible, no cutoff
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val pageMonday = currentWeekMonday.plusWeeks((page - centerPage).toLong())
            val pageDays = (0..6).map { pageMonday.plusDays(it.toLong()) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                pageDays.forEachIndexed { index, day ->
                    val isSelected = day == selectedDate
                    val isTodayDate = day == today
                    val isPastDate = day.isBefore(today)
                    val (dayCompleted, dayTotal) = if (isPastDate) calculateDayProgress(day, habits, habitStatus, todos) else 0 to 0
                    val dayProgressFraction = if (dayTotal == 0) 0f else dayCompleted.toFloat() / dayTotal.toFloat()
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(40.dp)
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
                                    .background(if (isTodayDate) Color(0xFF33343D) else Color.Transparent)
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
                    val weeksBetween = java.time.temporal.ChronoUnit.WEEKS.between(currentWeekMonday, targetMonday)
                    coroutineScope.launch {
                        pagerState.scrollToPage((centerPage + weeksBetween).toInt())
                    }
                    selectedDate = firstOfMonth
                    showMonthPicker = false
                }
            )
        }

        Spacer(Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            FilterChip("ALL", selectedFilter == "ALL", icon = Icons.Filled.Apps) { selectedFilter = "ALL" }
            FilterChip("MORNING", selectedFilter == "MORNING", icon = Icons.Filled.WbTwilight) { selectedFilter = "MORNING" }
            FilterChip("AFTERNOON", selectedFilter == "AFTERNOON", icon = Icons.Filled.LightMode) { selectedFilter = "AFTERNOON" }
            FilterChip("EVENING", selectedFilter == "EVENING", icon = Icons.Filled.NightsStay) { selectedFilter = "EVENING" }
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
                            val threshold = 80f // px — kam karo to zyada sensitive, zyada karo to kam
                            when {
                                totalDrag <= -threshold -> changeDay(1)   // left swipe -> next day
                                totalDrag >= threshold -> changeDay(-1)   // right swipe -> previous day
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
                        isToday = selectedDate == today,
                        isPast = selectedDate.isBefore(today),
                        streakCount = computeStreak(habit, habitStatus, today),
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

        val visibleTodos = if (selectedFilter == "ALL") todos.filter { it.date == selectedDate } else emptyList()
        if (visibleTodos.isNotEmpty()) {
            Spacer(Modifier.height(10.dp))
            Text("TO-DO", color = TextGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            visibleTodos.forEach { todo ->
                TodoCard(
                    todo = todo,
                    isToday = selectedDate == today,
                    isPast = selectedDate.isBefore(today),
                    onToggleDone = {
                        val index = todos.indexOfFirst { it.id == todo.id }
                        if (index != -1) {
                            val updated = todo.copy(completed = !todo.completed)
                            todos[index] = updated
                            if (updated.completed) {
                                TodoReminderScheduler.cancel(context, todo.id)
                                if (soundOnComplete) CompletionSoundPlayer.play()
                            } else if (updated.reminderEnabled && updated.reminderTime != null) {
                                TodoReminderScheduler.schedule(context, updated)
                            }
                        }
                    },
                    onDelete = {
                        todos.removeAll { it.id == todo.id }
                        TodoReminderScheduler.cancel(context, todo.id)
                    },
                    onEditSave = { newText, reminder, reminderTime ->
                        val index = todos.indexOfFirst { it.id == todo.id }
                        if (index != -1) {
                            val updated = todo.copy(text = newText, reminderEnabled = reminder, reminderTime = reminderTime)
                            todos[index] = updated
                            if (reminder && reminderTime != null) {
                                TodoReminderScheduler.schedule(context, updated)
                            } else {
                                TodoReminderScheduler.cancel(context, todo.id)
                            }
                        }
                    }
                )
                Spacer(Modifier.height(14.dp))
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
            val todayPillShape = RoundedCornerShape(topStart = 50f, bottomStart = 50f, topEnd = 0f, bottomEnd = 0f)
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 104.dp + bottomContentPadding)
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

        if (!selectedDate.isBefore(today)) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 20.dp, bottom = 20.dp + bottomContentPadding)
                    .size(60.dp)
                    .shadow(elevation = 14.dp, shape = CircleShape, ambientColor = BlueAccent, spotColor = BlueAccent)
                    .clip(CircleShape)
                    .background(BlueAccent)
                    .border(width = 2.dp, color = Color.White.copy(alpha = 0.25f), shape = CircleShape)
                    .combinedClickable(
                        onClick = { showAddTodoSheet = true },
                        onLongClick = { onAddHabit() }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White, modifier = Modifier.size(28.dp))
            }
        }

        if (showAddTodoSheet) {
            AddTodoSheet(
                onDismiss = { showAddTodoSheet = false },
                onSave = { text, reminder, reminderTime ->
                    val newTodo = TodoTask(id = System.currentTimeMillis(), text = text, date = selectedDate, reminderEnabled = reminder, reminderTime = reminderTime)
                    todos.add(newTodo)
                    if (reminder && reminderTime != null) {
                        TodoReminderScheduler.schedule(context, newTodo)
                    }
                    showAddTodoSheet = false
                }
            )
        }
    }
}

@Composable
fun HabitCard(
    habit: Habit,
    status: HabitStatus,
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
                                        Text("🔥", fontSize = 12.sp)
                                        Spacer(Modifier.width(2.dp))
                                        Text("$streakCount", color = textColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
    var dragOffsetY by remember { mutableStateOf(0f) }
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
                    .offset { IntOffset(0, dragOffsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onVerticalDrag = { change, dragAmount ->
                                if (dragAmount > 0) {
                                    dragOffsetY = (dragOffsetY + dragAmount).coerceAtLeast(0f)
                                    change.consume()
                                }
                            },
                            onDragEnd = {
                                if (dragOffsetY > 150f) onDismiss() else dragOffsetY = 0f
                            },
                            onDragCancel = { dragOffsetY = 0f }
                        )
                    }
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { /* absorb taps so sheet doesn't dismiss when tapped */ },
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = CardBg
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // ---- Header: emoji + habit name (same sizes as the habit card) ----
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(iconForKey(habit.iconEmoji), contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                        Spacer(Modifier.width(12.dp))
                        Text(habit.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }

                    Spacer(Modifier.height(20.dp))
                    HorizontalDivider(color = Color(0xFF3A3B44))
                    Spacer(Modifier.height(16.dp))

                    // ---- Quick actions row ----
                    val undoEnabled = status == HabitStatus.DONE || status == HabitStatus.SKIPPED
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        PdfSheetQuickAction(Icons.Default.Undo, "Undo", enabled = undoEnabled) {
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
fun TodoOptionsSheet(
    todo: TodoTask,
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    var dragOffsetY by remember { mutableStateOf(0f) }
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
                    .offset { IntOffset(0, dragOffsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onVerticalDrag = { change, dragAmount ->
                                if (dragAmount > 0) {
                                    dragOffsetY = (dragOffsetY + dragAmount).coerceAtLeast(0f)
                                    change.consume()
                                }
                            },
                            onDragEnd = {
                                if (dragOffsetY > 150f) onDismiss() else dragOffsetY = 0f
                            },
                            onDragCancel = { dragOffsetY = 0f }
                        )
                    }
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { /* absorb taps so sheet doesn't dismiss when tapped */ },
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = CardBg
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.EditNote, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                        Spacer(Modifier.width(12.dp))
                        Text(todo.text, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp, maxLines = 1)
                    }

                    Spacer(Modifier.height(20.dp))
                    HorizontalDivider(color = Color(0xFF3A3B44))
                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        PdfSheetQuickAction(Icons.Default.Delete, "Delete", tint = Color(0xFFE55353)) {
                            onDismiss(); onDelete()
                        }
                        PdfSheetQuickAction(Icons.Default.Edit, "Edit") {
                            onDismiss(); onEdit()
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun TodoCard(todo: TodoTask, isToday: Boolean, isPast: Boolean, onToggleDone: () -> Unit, onDelete: () -> Unit, onEditSave: (String, Boolean, LocalTime?) -> Unit) {
    val cardColor = if (todo.completed) CardBg else BlueAccent
    val textColor = if (todo.completed) TextGray else Color.White
    var showOptionsSheet by remember { mutableStateOf(false) }
    var showEditSheet by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        if (!isPast) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(if (todo.completed) BlueAccent else Color.Transparent)
                    .border(
                        width = if (todo.completed) 0.dp else 2.dp,
                        color = if (todo.completed) Color.Transparent else TextGray,
                        shape = CircleShape
                    )
                    .clickable { onToggleDone() },
                contentAlignment = Alignment.Center
            ) {
                if (todo.completed) {
                    Icon(Icons.Default.Check, contentDescription = "Done", tint = Color.White, modifier = Modifier.size(10.dp))
                }
            }
            Spacer(Modifier.width(12.dp))
        }
        Card(
            modifier = Modifier
                .weight(1f)
                .height(72.dp)
                .combinedClickable(
                    onClick = {},
                    onLongClick = { showOptionsSheet = true }
                ),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        todo.text,
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textDecoration = if (todo.completed) TextDecoration.LineThrough else TextDecoration.None
                    )
                    if (!todo.completed && isPast) {
                        Text("Missed", color = Color.White, fontSize = 14.sp)
                    }
                }
            }
        }
    }

    if (showOptionsSheet) {
        TodoOptionsSheet(
            todo = todo,
            onDismiss = { showOptionsSheet = false },
            onDelete = onDelete,
            onEdit = {
                showOptionsSheet = false
                showEditSheet = true
            }
        )
    }

    if (showEditSheet) {
        AddTodoSheet(
            existingTodo = todo,
            onDismiss = { showEditSheet = false },
            onSave = { newText, reminder, reminderTime ->
                onEditSave(newText, reminder, reminderTime)
                showEditSheet = false
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddTodoSheet(existingTodo: TodoTask? = null, onDismiss: () -> Unit, onSave: (String, Boolean, LocalTime?) -> Unit) {
    var text by remember {
        mutableStateOf(
            TextFieldValue(
                text = existingTodo?.text ?: "",
                selection = TextRange((existingTodo?.text ?: "").length)
            )
        )
    }
    var reminderTime by remember { mutableStateOf(existingTodo?.reminderTime) }
    var showTimePicker by remember { mutableStateOf(false) }
    var suppressImeClose by remember { mutableStateOf(false) }
    var isClosing by remember { mutableStateOf(false) }
    var isAppInForeground by remember { mutableStateOf(true) }
    var resumeGraceActive by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val imeVisible = WindowInsets.isImeVisible
    var wasImeVisible by remember { mutableStateOf(false) }
    val offsetY = remember { Animatable(400f) }
    val scrimAlpha = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current
    // Live keyboard height - ye har frame apne aap update hoti hai jaise-jaise keyboard khulta/band hota hai
    val liveImeHeightDp = WindowInsets.ime.getBottom(density) / density.density
    // Resume ke turant baad system ke insets ek-do frame ke liye settle ho rahe hote hain,
    // isliye us chhoti si window ke dauraan height ko freeze rakhte hain taaki flicker na dikhe
    var imeHeightDp by remember { mutableStateOf(liveImeHeightDp) }
    LaunchedEffect(liveImeHeightDp, resumeGraceActive) {
        if (!resumeGraceActive) {
            imeHeightDp = liveImeHeightDp
        }
    }

    // App background (Recents) me jaate hi keyboard hide hota hai, aur resume hote waqt
    // kabhi-kabhi keyboard ek pal ke liye "flicker" karta hai - dono cases me ye user ka
    // "close karo" wala action nahi hai, isliye in dono waqt auto-close ko ignore karna hai
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    isAppInForeground = false
                    // Focus abhi bhi "active" na rahe - warna Android resume hote hi khud
                    // keyboard wapas laane ki koshish karega aur turant band kar dega (flicker)
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
                Lifecycle.Event.ON_RESUME -> {
                    isAppInForeground = true
                    resumeGraceActive = true
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(resumeGraceActive) {
        if (resumeGraceActive) {
            delay(500)
            resumeGraceActive = false
        }
    }

    fun revealSheet() {
        coroutineScope.launch { offsetY.animateTo(0f, tween(220)) }
        coroutineScope.launch { scrimAlpha.animateTo(0.5f, tween(220)) }
    }

    fun closeSheet() {
        if (isClosing) return
        isClosing = true
        coroutineScope.launch {
            val hideOffset = launch { offsetY.animateTo(400f, tween(260)) }
            launch { scrimAlpha.animateTo(0f, tween(260)) }
            hideOffset.join()
            onDismiss()
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        delay(300)
        if (offsetY.value != 0f) revealSheet()
    }
    LaunchedEffect(showTimePicker) {
        if (showTimePicker) {
            suppressImeClose = true
        } else if (suppressImeClose) {
            delay(150)
            wasImeVisible = imeVisible
            suppressImeClose = false
        }
    }
    LaunchedEffect(imeVisible) {
        if (isClosing) return@LaunchedEffect
        if (imeVisible) {
            wasImeVisible = true
            revealSheet()
        } else if (wasImeVisible && !suppressImeClose && isAppInForeground && !resumeGraceActive) {
            closeSheet()
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
    BackHandler(onBack = ::closeSheet)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = scrimAlpha.value))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { closeSheet() },
        contentAlignment = Alignment.BottomCenter
    ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 8.dp)
                    .offset(y = (offsetY.value - imeHeightDp).dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { /* absorb taps so sheet doesn't dismiss when tapped */ },
                shape = RoundedCornerShape(14.dp),
                color = CardBg
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp, top = 24.dp, end = 20.dp, bottom = 14.dp)
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
                            value = text,
                            onValueChange = { text = it },
                            placeholder = {
                                Text("Add a task...", color = TextGray, fontStyle = FontStyle.Italic)
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
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (reminderTime != null) BlueAccent else Color(0xFF33343D))
                                .padding(start = 8.dp, top = 6.dp, end = 10.dp, bottom = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier.clickable { showTimePicker = true },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(6.dp))
                                if (reminderTime != null) {
                                    Text(
                                        reminderTime!!.format(DateTimeFormatter.ofPattern("HH:mm")),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                } else {
                                    Text("Set reminder", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                }
                            }
                            if (reminderTime != null) {
                                Spacer(Modifier.width(8.dp))
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
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (text.text.isNotBlank()) BlueAccent else Color(0xFF33343D))
                                .clickable(enabled = text.text.isNotBlank()) { onSave(text.text, reminderTime != null, reminderTime) }
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
@Composable
fun MonthPickerDialog(year: Int, onDismiss: () -> Unit, onMonthSelected: (Int) -> Unit) {
    val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp), color = CardBg) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("$year", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                months.forEachIndexed { index, monthName ->
                    Text(
                        monthName,
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onMonthSelected(index + 1) }
                            .padding(vertical = 12.dp)
                    )
                }
            }
        }
    }
}
// ---------- PDFs data + storage query ----------
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

enum class FileSizeUnit { AUTO, KB, MB }

enum class AutoLockTimeout(val label: String, val millis: Long) {
    INSTANT("Instant", 0L),
    ONE_MIN("After 1 min", 60_000L),
    FIVE_MIN("After 5 mins", 5 * 60_000L),
    TEN_MIN("After 10 mins", 10 * 60_000L)
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

// Reused across calls instead of allocating a new SimpleDateFormat every time -
// safe because Compose recomposition always runs on the main thread (single-threaded).
private val pdfDateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

// MediaStore's DATE_MODIFIED column is in epoch SECONDS, not millis.
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
        context.startActivity(intent) // koi preferred app na ho to system "Open with" chooser dikhega
    } catch (e: ActivityNotFoundException) {
        // preferred app shayad uninstall ho chuka hai - normal chooser pe fallback karo
        intent.setPackage(null)
        context.startActivity(intent)
    }
}

// Device pe installed sabhi apps jo PDF khol sakte hain, unki (packageName, label) list deta hai
fun getPdfHandlerApps(context: Context): List<Pair<String, String>> {
    val pm = context.packageManager
    // Query with a few different intent shapes since some PDF viewers only declare
    // intent-filters for specific URI schemes/extensions rather than a bare mimeType
    // match - combining results here avoids the list coming back thin or empty.
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

// Settings se "Open PDFs with" tap karne par Android ka asli "Open with" chooser dikhata hai
// (bilkul waisa hi jaisa kabhi-kabhi ek PDF open karte waqt dikhta hai). User jo bhi app
// choose kare, us package ko onAppChosen callback ke through wapas deta hai taaki naya
// preferred/default app set kiya ja sake — chahe pehle koi aur app default set ho.
fun showPdfDefaultAppChooser(context: Context, onAppChosen: (String) -> Unit) {
    // Agar device pe koi real PDF scan ho chuka hai to usi ko sample data ke roop me use karo,
    // taaki chosen app crash na kare - warna sirf mime type ke saath generic intent bhej do.
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

    // Har tap par ek unique action banao taaki purane receivers se clash na ho
    val chosenAction = "com.streakk.app.PDF_DEFAULT_APP_CHOSEN_${System.currentTimeMillis()}"
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(ctx: Context, resultIntent: Intent) {
            val chosen = resultIntent.getParcelableExtra<ComponentName>(Intent.EXTRA_CHOSEN_COMPONENT)
            chosen?.packageName?.let { onAppChosen(it) }
            try { context.unregisterReceiver(this) } catch (e: Exception) {}
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.registerReceiver(receiver, IntentFilter(chosenAction), Context.RECEIVER_NOT_EXPORTED)
    } else {
        context.registerReceiver(receiver, IntentFilter(chosenAction))
    }

    // FLAG_MUTABLE zaroori hai kyunki system isi PendingIntent me EXTRA_CHOSEN_COMPONENT daalta hai
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

// ---------- PDF sort options ----------
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

// ---------- PDF folders storage (app-level virtual folders) ----------
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

// ---------- PDF <-> folder assignment storage (which pdf sits in which folder) ----------
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

// ---------- PDF list disk cache (survives app restart) ----------
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

// ---------- PDF file actions: delete / share / rename / print ----------
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

// ---------- PDF first-page thumbnail ----------
suspend fun renderPdfThumbnail(path: String): Bitmap? = withContext(Dispatchers.IO) {
    try {
        val file = File(path)
        if (!file.exists()) return@withContext null
        ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY).use { pfd ->
            PdfRenderer(pfd).use { renderer ->
                if (renderer.pageCount == 0) return@withContext null
                renderer.openPage(0).use { page ->
                    // List row mein sirf ~52x64dp jagah dikhti hai — poore page ko
                    // 2x resolution pe render karna wasteful hai. Ek chhota fixed
                    // target width use karo (jaisa real PDF-reader apps karte hain).
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
        // Row fast-scroll se screen ke bahar gaya, coroutine cancel hui —
        // ye genuine failure nahi hai, isko aage propagate hone do taaki
        // Compose sahi tarah se cancel kare aur agli baar fresh try ho.
        throw e
    } catch (e: Exception) {
        null
    }
}

// ---------- PDF thumbnail in-memory cache ----------
// Keeps decoded first-page bitmaps alive across recompositions/scroll so LazyColumn
// re-entering a row (which disposes + recreates PdfRow) doesn't re-render from disk.
object PdfThumbnailCache {
    private val cache = android.util.LruCache<Long, Bitmap>(60) // ~60 thumbnails cap, tune as needed

    fun get(pdfId: Long): Bitmap? = cache.get(pdfId)

    fun put(pdfId: Long, bitmap: Bitmap) {
        cache.put(pdfId, bitmap)
    }

    fun clear() {
        cache.evictAll()
    }
}

// ---------- PDF thumbnail disk cache (survives app restart) ----------
// PdfThumbnailCache (above) is in-memory only and dies with the process.
// This writes the decoded first-page bitmap to cacheDir once, so next app
// launch reads a small PNG instead of re-running PdfRenderer from scratch.
object PdfThumbnailDiskCache {
    private fun dir(context: Context): File {
        val d = File(context.cacheDir, "pdf_thumbs")
        if (!d.exists()) d.mkdirs()
        return d
    }

    suspend fun get(context: Context, pdfId: Long): Bitmap? = withContext(Dispatchers.IO) {
        val file = File(dir(context), "$pdfId.png")
        if (!file.exists()) return@withContext null
        try {
            android.graphics.BitmapFactory.decodeFile(file.absolutePath)
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (e: Exception) {
            null
        }
    }

    // Synchronous variant, used ONLY to seed initial Compose state on first
    // composition (cold app start). These PNGs are tiny (~180px wide list
    // thumbnails), so a direct main-thread decode is fast enough to avoid
    // the one-frame "flash to red fallback" that an async read would cause.
    fun getSync(context: Context, pdfId: Long): Bitmap? {
        val file = File(dir(context), "$pdfId.png")
        if (!file.exists()) return null
        return try {
            android.graphics.BitmapFactory.decodeFile(file.absolutePath)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun put(context: Context, pdfId: Long, bitmap: Bitmap): Unit = withContext(Dispatchers.IO) {
        try {
            java.io.FileOutputStream(File(dir(context), "$pdfId.png")).use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
            }
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (e: Exception) {
            // cache-write failed, thumbnail will just re-render next time — not fatal
        }
    }

    suspend fun clear(context: Context): Unit = withContext(Dispatchers.IO) {
        try {
            dir(context).listFiles()?.forEach { it.delete() }
        } catch (e: Exception) {
            // ignore
        }
    }
}

// ---------- PDFs screen state (survives leaving/re-entering the PDFs tab) ----------
// PdfsScreen() itself gets torn down every time you switch tabs, so a plain
// remember{} inside it resets on every visit. Backing it with this singleton's
// MutableState instances means the SAME state objects are reused across visits,
// so the expensive device scan only needs to run once per app session.
object PdfsScreenState {
    val pdfs = mutableStateOf<List<PdfFile>>(emptyList())
    val folders = mutableStateOf<Set<String>>(emptySet())
    val folderAssignments = mutableStateOf<Map<Long, String>>(emptyMap())
    val hasLoadedOnce = mutableStateOf(false)
}

// ---------- PDFs screen ----------
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
    var hasPermission by remember { mutableStateOf(hasStorageAccess(context)) }
    var pdfs by PdfsScreenState.pdfs
    var isLoading by remember { mutableStateOf(false) }

    var folders by PdfsScreenState.folders
    var folderAssignments by PdfsScreenState.folderAssignments
    var hasLoadedOnce by PdfsScreenState.hasLoadedOnce
    var openFolder by remember { mutableStateOf<String?>(null) }
    var sortOption by remember { mutableStateOf(defaultSortOption) }
    LaunchedEffect(sortOption) { onSortOptionPersist(sortOption) }
    var showSortMenu by remember { mutableStateOf(false) }
    var showCreateFolderDialog by remember { mutableStateOf(false) }
    var selectionMode by remember { mutableStateOf(false) }
    val selectedIds = remember { mutableStateListOf<Long>() }
    var renameTarget by remember { mutableStateOf<PdfFile?>(null) }
    var deleteTarget by remember { mutableStateOf<PdfFile?>(null) }
    var moveTarget by remember { mutableStateOf<PdfFile?>(null) }
    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val searchFocusRequester = remember { FocusRequester() }

    val legacyPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> hasPermission = granted }

    val allFilesAccessLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { hasPermission = hasStorageAccess(context) }

    LaunchedEffect(hasPermission) {
        if (hasPermission) {
            if (!hasLoadedOnce) {
                // Pichle app-session ka cached list turant dikhao — koi spinner/blank
                // screen nahi, jab tak fresh MediaStore query background mein chal rahi hai.
                val cachedList = PdfListCacheStorage.load(context)
                if (cachedList.isNotEmpty()) {
                    pdfs = cachedList
                } else {
                    isLoading = true
                }
                folders = PdfFolderStorage.load(context)
                folderAssignments = PdfFolderAssignmentStorage.load(context)

                val freshList = loadDevicePdfs(context)
                pdfs = freshList
                PdfListCacheStorage.save(context, freshList)
                isLoading = false
                hasLoadedOnce = true
            } else if (autoScanEnabled) {
                // Tab dobara khula hai - background me chup-chaap naye PDFs ke liye rescan karo
                val freshList = loadDevicePdfs(context)
                pdfs = freshList
                PdfListCacheStorage.save(context, freshList)
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
    val displayedPdfs = remember(pdfs, folderAssignments, openFolder, sortOption, isSearching, searchQuery) {
        val base = if (isSearching) {
            // Search poore device ke PDFs mein hoti hai, current folder tak
            // limited nahi — jaisa Google Files / WPS jaisi apps mein hota hai.
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
                    Icons.Default.ArrowBack,
                    contentDescription = "Close search",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        isSearching = false
                        searchQuery = ""
                    }
                )
                Spacer(Modifier.width(12.dp))
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(searchFocusRequester),
                    placeholder = { Text("Search PDFs", color = TextGray) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = BlueAccent,
                        unfocusedBorderColor = Color(0xFF3A3B44),
                        cursorColor = BlueAccent
                    )
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
                    Icons.Default.ArrowBack,
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
                        Icons.Default.Sort,
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("PDFs", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
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
                            Icons.Default.Sort,
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
                                    allFilesAccessLauncher.launch(intent)
                                } else {
                                    legacyPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
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
                        items(sortedFolders) { folderName ->
                            FolderRow(
                                name = folderName,
                                count = folderAssignments.values.count { it == folderName },
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
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
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
    var dragOffsetY by remember { mutableStateOf(0f) }
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
                    .offset { IntOffset(0, dragOffsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onVerticalDrag = { change, dragAmount ->
                                if (dragAmount > 0) {
                                    dragOffsetY = (dragOffsetY + dragAmount).coerceAtLeast(0f)
                                    change.consume()
                                }
                            },
                            onDragEnd = {
                                if (dragOffsetY > 150f) onDismiss() else dragOffsetY = 0f
                            },
                            onDragCancel = { dragOffsetY = 0f }
                        )
                    }
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { /* absorb taps so sheet doesn't dismiss when tapped */ },
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = CardBg
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // ---- Header: thumbnail + name + path ----
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

                    // ---- Quick actions row ----
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        PdfSheetQuickAction(Icons.Default.Share, "Share") { onDismiss(); onShare() }
                        PdfSheetQuickAction(Icons.Default.DriveFileRenameOutline, "Rename") { onDismiss(); onRename() }
                        PdfSheetQuickAction(Icons.Default.Print, "Print") { onDismiss(); onPrint() }
                        PdfSheetQuickAction(Icons.Default.DriveFileMove, "Move") { onDismiss(); onMove() }
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

@Composable
fun PdfRow(
    pdf: PdfFile,
    selectionMode: Boolean,
    selected: Boolean,
    fileSizeUnit: FileSizeUnit = FileSizeUnit.AUTO,
    onClick: () -> Unit,
    onLongPress: () -> Unit,
    onDelete: () -> Unit,
    onShare: () -> Unit,
    onRename: () -> Unit,
    onPrint: () -> Unit,
    onMove: () -> Unit
) {
        var showOptionsSheet by remember { mutableStateOf(false) }
    // Seed synchronously from the in-memory cache so an already-decoded thumbnail
    // renders on the very first frame — no red-fallback flash while the effect below
    // gets dispatched. This is what kills the "reloads every time I switch tabs" feel.
    val context = LocalContext.current
    var thumbnail by remember(pdf.id) {
        mutableStateOf(
            PdfThumbnailCache.get(pdf.id) ?: PdfThumbnailDiskCache.getSync(context, pdf.id)?.also {
                PdfThumbnailCache.put(pdf.id, it)
            }
        )
    }

    LaunchedEffect(pdf.id) {
        if (thumbnail != null) return@LaunchedEffect // already showing memory- or disk-cached bitmap, nothing to do
        val bmp = renderPdfThumbnail(pdf.path)
        if (bmp != null) {
            PdfThumbnailCache.put(pdf.id, bmp)
            PdfThumbnailDiskCache.put(context, pdf.id, bmp)
        }
        thumbnail = bmp
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(CardBg)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongPress
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (selectionMode) {
            Icon(
                if (selected) Icons.Default.CheckBox else Icons.Default.CheckBoxOutlineBlank,
                contentDescription = null,
                tint = if (selected) BlueAccent else TextGray,
                modifier = Modifier.padding(end = 10.dp)
            )
        }
        Box(
            modifier = Modifier
                .width(44.dp)
                .height(62.dp) // A4 ratio (1 : 1.414) applied to 44dp width
                .clip(RoundedCornerShape(6.dp))
                .background(if (thumbnail != null) Color.White else Color(0xFFB3261E)),
            contentAlignment = Alignment.Center
        ) {
            val bmp = thumbnail
            if (bmp != null) {
                Image(
                    bitmap = bmp.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp))
                )
            } else {
                Text("PDF", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(pdf.name, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium, maxLines = 2)
            Spacer(Modifier.height(4.dp))
                 val dateAndSizeText = remember(pdf.dateModified, pdf.sizeBytes, fileSizeUnit) {
                "${formatPdfDate(pdf.dateModified)}  •  ${formatFileSize(pdf.sizeBytes, fileSizeUnit)}"
            }
            Text(
                dateAndSizeText,
                color = TextGray,
                fontSize = 12.sp,
                maxLines = 1
            )
        }

        if (!selectionMode) {
            Spacer(Modifier.width(6.dp))
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = TextGray,
                modifier = Modifier.clickable { showOptionsSheet = true }
            )
        }
    }

    if (showOptionsSheet) {
        PdfOptionsSheet(
            pdf = pdf,
            thumbnail = thumbnail,
            onDismiss = { showOptionsSheet = false },
            onDelete = onDelete,
            onShare = onShare,
            onRename = onRename,
            onPrint = onPrint,
            onMove = onMove
        )
    }
}

// ---------- ADD HABIT screen ----------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitScreen(existingHabit: Habit?, onBack: () -> Unit, onSave: (Habit) -> Unit, onDelete: () -> Unit) {
    val context = LocalContext.current
    var name by remember { mutableStateOf(existingHabit?.name ?: "") }
    var iconEmoji by remember { mutableStateOf(existingHabit?.iconEmoji ?: "🙂") }
    var colorHex by remember { mutableStateOf(existingHabit?.colorHex ?: 0xFF3B7BF5) }
    var showIconPicker by remember { mutableStateOf(false) }

    var habitDays by remember { mutableStateOf(existingHabit?.habitDays ?: DayOfWeek.values().toSet()) }
    var habitDaysExpanded by remember { mutableStateOf(true) }   // <-- replaces the "extra page"

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
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
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
    EndMode.OFF -> {} // nothing shown, matches screenshot
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
                    createdAt = existingHabit?.createdAt ?: LocalDate.now()
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
    var dragOffsetY by remember { mutableStateOf(0f) }

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
                    .offset { IntOffset(0, dragOffsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onVerticalDrag = { change, dragAmount ->
                                if (dragAmount > 0) {
                                    dragOffsetY = (dragOffsetY + dragAmount).coerceAtLeast(0f)
                                    change.consume()
                                }
                            },
                            onDragEnd = {
                                if (dragOffsetY > 150f) onDismiss() else dragOffsetY = 0f
                            },
                            onDragCancel = { dragOffsetY = 0f }
                        )
                    }
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { /* absorb taps so sheet doesn't dismiss when tapped */ },
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
    val summary = when {
        habitDays.size == 7 -> "Everyday"
        habitDays.isEmpty() -> "No days selected"
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
                if (expanded) Icons.Default.ChevronRight else Icons.Default.ChevronRight, // swap for a rotate/expand icon if you have one
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
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DaysWheelPickerSheet(
    currentValue: Int,
    range: IntRange,
    onDismiss: () -> Unit,
    onSave: (Int) -> Unit
) {
    val items = range.toList()
    val itemHeight = 40.dp
    val visibleCount = 5 // shows 2 above + selected + 2 below, matches screenshot
    val startIndex = items.indexOf(currentValue).coerceAtLeast(0)
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)
    val snapFlingBehavior = rememberSnapFlingBehavior(
        SnapLayoutInfoProvider(listState, SnapPosition.Center)
    )
    val centeredIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
            layoutInfo.visibleItemsInfo
                .minByOrNull { kotlin.math.abs((it.offset + it.size / 2) - viewportCenter) }
                ?.index ?: startIndex
        }
    }
    val selected = items.getOrElse(centeredIndex) { currentValue }

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
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { /* absorb taps so sheet doesn't dismiss when tapped */ },
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = Color(0xFF2A2B33)
            ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.clickable { onDismiss() }
                    )
                }
                Text(
                    "Days",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight * visibleCount),
                    contentAlignment = Alignment.Center
                ) {
                    // Center highlight lines — fixed width (~5 digits), centered; marks start/end of center row
                    HorizontalDivider(
                        color = BlueAccent.copy(alpha = 0.5f),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .width(90.dp)
                            .offset(y = itemHeight * 2)
                    )
                    HorizontalDivider(
                        color = BlueAccent.copy(alpha = 0.5f),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .width(90.dp)
                            .offset(y = itemHeight * 3)
                    )
                    LazyColumn(
                        state = listState,
                        flingBehavior = snapFlingBehavior,
                        contentPadding = PaddingValues(vertical = itemHeight * 2),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(items.size) { idx ->
                            val value = items[idx]
                            val isCenter = value == selected
                            Box(
                                modifier = Modifier.fillMaxWidth().height(itemHeight),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "$value",
                                    color = if (isCenter) Color.White else TextGray,
                                    fontWeight = if (isCenter) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = if (isCenter) 18.sp else 14.sp
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFF3A3B44))
                            .clickable { onDismiss() },
                        contentAlignment = Alignment.Center
                    ) { Text("BACK", color = Color.White, fontWeight = FontWeight.Bold) }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .clip(RoundedCornerShape(50))
                            .background(BlueAccent)
                            .clickable { onSave(selected) },
                        contentAlignment = Alignment.Center
                    ) { Text("SAVE", color = Color.White, fontWeight = FontWeight.Bold) }
                }
            }
        }
    }
}
}