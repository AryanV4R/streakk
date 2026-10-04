package com.streakk.app

import android.content.Context
import java.time.DayOfWeek

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

    private const val KEY_AUTO_BACKUP_ENABLED = "auto_backup_enabled"
    private const val KEY_LAST_BACKUP_AT = "last_backup_at"

    fun loadAutoBackupEnabled(context: Context): Boolean {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_AUTO_BACKUP_ENABLED, true)
    }

    fun saveAutoBackupEnabled(context: Context, value: Boolean) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_AUTO_BACKUP_ENABLED, value)
            .apply()
    }

    fun loadLastBackupAt(context: Context): Long {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getLong(KEY_LAST_BACKUP_AT, 0L)
    }

    fun saveLastBackupAt(context: Context, millis: Long) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putLong(KEY_LAST_BACKUP_AT, millis)
            .apply()
    }

    fun hasSeenTutorial(context: Context): Boolean {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean("has_seen_tutorial", false)
    }

    fun setHasSeenTutorial(context: Context, value: Boolean) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean("has_seen_tutorial", value)
            .apply()
    }
}