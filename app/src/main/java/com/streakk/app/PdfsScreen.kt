package com.streakk.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

    val context = LocalContext.current
    var thumbnail by remember(pdf.id) {
        mutableStateOf(PdfThumbnailCache.get(pdf.id))
    }

    LaunchedEffect(pdf.id) {
        if (thumbnail != null) return@LaunchedEffect
        val fromDisk = PdfThumbnailDiskCache.get(context, pdf.id)
        if (fromDisk != null) {
            PdfThumbnailCache.put(pdf.id, fromDisk)
            thumbnail = fromDisk
            return@LaunchedEffect
        }
        pdfRenderSemaphore.acquire()
        val bmp = try {
            renderPdfThumbnail(pdf.path)
        } finally {
            pdfRenderSemaphore.release()
        }
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
                .height(62.dp)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(6.dp))
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
                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(6.dp))
                )
            } else {
                Text("PDF", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                pdf.name,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2
            )
            Spacer(Modifier.height(4.dp))
            val dateAndSizeText = remember(pdf.dateModified, pdf.sizeBytes, fileSizeUnit) {
                "${formatPdfDate(pdf.dateModified)}  •  ${
                    formatFileSize(
                        pdf.sizeBytes,
                        fileSizeUnit
                    )
                }"
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