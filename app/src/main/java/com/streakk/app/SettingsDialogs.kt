package com.streakk.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.graphics.drawable.toBitmap

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
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
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
                    ) { }
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
                    Text(
                        "Open PDFs with",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
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
                                context.packageManager.getApplicationIcon(context.packageName)
                                    .toBitmap().asImageBitmap()
                            } catch (_: Exception) {
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
                                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
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
                                context.packageManager.getApplicationIcon(pkg).toBitmap()
                                    .asImageBitmap()
                            } catch (_: Exception) {
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
                                            .clip(
                                                androidx.compose.foundation.shape.RoundedCornerShape(
                                                    8.dp
                                                )
                                            )
                                    )
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(
                                                androidx.compose.foundation.shape.RoundedCornerShape(
                                                    8.dp
                                                )
                                            )
                                            .background(Color(0xFF33343D))
                                    )
                                }
                                Spacer(Modifier.width(14.dp))
                                Text(label, color = Color.White, fontSize = 16.sp)
                            }
                            if (current == pkg) {
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = null,
                                    tint = BlueAccent
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}