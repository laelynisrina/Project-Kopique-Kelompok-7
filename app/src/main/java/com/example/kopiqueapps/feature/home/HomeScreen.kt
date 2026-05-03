package com.example.kopiqueapps.feature.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kopiqueapps.core.MenuData
import com.example.kopiqueapps.core.MenuItem

@Composable
fun HomeScreen(
    onMenuClick: (Int) -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBFE)),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(Color(0xFF7C4DFF)),
                contentAlignment = Alignment.CenterStart
            ) {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text("Kopique", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                    Text("Selamat datang di Kopique ☕", fontSize = 14.sp, color = Color.White.copy(alpha = 0.85f))
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            MenuSection(title = "🏆 Best Seller", items = MenuData.bestSeller, context = context, onItemClick = onMenuClick)
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            MenuSection(title = "☕ Coffee", items = MenuData.coffeeMenu, context = context, onItemClick = onMenuClick)
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            MenuSection(title = "🍵 Non Coffee", items = MenuData.nonCoffeeMenu, context = context, onItemClick = onMenuClick)
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, "Ayo pakai aplikasi KopiqueApps untuk kemudahan transaksi!")
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Bagikan via"))
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF7C4DFF))
                ) {
                    Text("📤 Share", fontWeight = FontWeight.Medium)
                }

                OutlinedButton(
                    onClick = {
                        val mapsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/Y4G7A24dRoz8RVEa6"))
                        context.startActivity(mapsIntent)
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF7C4DFF))
                ) {
                    Text("📍 Lokasi", fontWeight = FontWeight.Medium)
                }
            }
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        item {
            TextButton(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                Text("Logout", color = Color(0xFFE53935), fontWeight = FontWeight.Medium, fontSize = 15.sp)
            }
        }
    }
}

@Composable
fun MenuSection(
    title: String,
    items: List<MenuItem>,
    context: Context,
    onItemClick: (Int) -> Unit
) {
    Column {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF4E2C),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { item ->
                MenuCard(item = item, context = context, onItemClick = onItemClick)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun MenuCard(
    item: MenuItem,
    context: Context,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier.width(160.dp).clickable { onItemClick(item.id) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(Color(0xFFFFF0E6D3))
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = item.emoji, fontSize = 40.sp)
            }

            Column(modifier = Modifier.padding(all = 10.dp)) {
                Text(
                    text = item.name,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C1A0E),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = item.price, fontSize = 12.sp, color = Color(0xFFFF8B5E), fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("⭐", fontSize = 11.sp)
                    Text(text = item.rating.toString(), fontSize = 11.sp, color = Color(0xFF666666))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onItemClick(item.id) },
                    modifier = Modifier.fillMaxWidth().height(32.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C4DFF)),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "🛒 Order", fontSize = 11.sp, color = Color.White, fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    OutlinedButton(
                        onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, "Coba ${item.name} di Kopique seharga ${item.price}! ⭐${item.rating}")
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Bagikan via"))
                        },
                        modifier = Modifier.weight(1f).height(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF7C4DFF))
                    ) {
                        Text("📤", fontSize = 13.sp)
                    }
                    OutlinedButton(
                        onClick = {
                            val mapsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/Y4G7A24dRoz8RVEa6"))
                            context.startActivity(mapsIntent)
                        },
                        modifier = Modifier.weight(1f).height(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFF8B5E))
                    ) {
                        Text("📍", fontSize = 13.sp)
                    }
                }
            }
        }
    }
}
