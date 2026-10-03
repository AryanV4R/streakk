package com.streakk.app

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate
import java.time.LocalTime

object TodoStorage {
    private const val PREFS_NAME = "todo_prefs"
    private const val KEY_TODOS = "todos_json"

    fun save(context: Context, todos: List<HomeTodoItem>) {
        val array = JSONArray()
        todos.forEach { todo ->
            val obj = JSONObject()
            obj.put("id", todo.id)
            obj.put("text", todo.text)
            obj.put("date", todo.date.toString())
            obj.put("reminderTime", todo.reminderTime?.toString() ?: JSONObject.NULL)
            obj.put("completed", todo.completed)
            array.put(obj)
        }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_TODOS, array.toString())
            .apply()
    }

    fun load(context: Context): List<HomeTodoItem> {
        val json = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_TODOS, null) ?: return emptyList()
        val array = JSONArray(json)
        return (0 until array.length()).map { i ->
            val obj = array.getJSONObject(i)
            HomeTodoItem(
                id = obj.getLong("id"),
                text = obj.getString("text"),
                date = LocalDate.parse(obj.getString("date")),
                reminderTime = if (obj.isNull("reminderTime")) null else LocalTime.parse(obj.getString("reminderTime")),
                completed = obj.getBoolean("completed")
            )
        }
    }
}