package com.streakk.app

import java.time.LocalDate

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