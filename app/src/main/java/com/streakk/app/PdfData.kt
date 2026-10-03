package com.streakk.app

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

enum class FileSizeUnit { AUTO, KB, MB }
object PdfThumbnailDiskCache {
    private fun dir(context: Context): File {
        val d = File(context.cacheDir, "pdf_thumbs")
        if (!d.exists()) d.mkdirs()
        return d
    }

    suspend fun get(context: Context, pdfId: Long): Bitmap? = withContext(Dispatchers.IO) {
        val file = File(dir(context), "$pdfId.jpg")
        if (!file.exists()) return@withContext null
        try {
            BitmapFactory.decodeFile(file.absolutePath)
        } catch (e: CancellationException) {
            throw e
        } catch (_: Exception) {
            null
        }
    }
    suspend fun put(context: Context, pdfId: Long, bitmap: Bitmap): Unit =
        withContext(Dispatchers.IO) {
            try {
                FileOutputStream(File(dir(context), "$pdfId.jpg")).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)
                }
            } catch (e: CancellationException) {
                throw e
            } catch (_: Exception) {

            }
        }

    suspend fun clear(context: Context): Unit = withContext(Dispatchers.IO) {
        try {
            dir(context).listFiles()?.forEach { it.delete() }
        } catch (_: Exception) {

        }
    }
}