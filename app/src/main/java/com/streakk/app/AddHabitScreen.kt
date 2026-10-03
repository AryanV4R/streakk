package com.streakk.app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlin.math.abs

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
    val visibleCount = 5
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
                .minByOrNull { abs((it.offset + it.size / 2) - viewportCenter) }
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
                    ) { },
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = Color(0xFF2A2B33)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp),
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
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(50))
                                .background(Color(0xFF3A3B44))
                                .clickable { onDismiss() },
                            contentAlignment = Alignment.Center
                        ) { Text("BACK", color = Color.White, fontWeight = FontWeight.Bold) }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(52.dp)
                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(50))
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