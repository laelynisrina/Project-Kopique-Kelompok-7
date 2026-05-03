package com.example.kopiqueapps.feature.detail

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kopiqueapps.core.MenuData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    menuId: Int,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val menu = MenuData.findById(menuId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Menu", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF7C4DFF),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        if (menu == null) {
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                Text("Menu tidak ditemukan", color = Color.Gray)
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding).background(Color(0xFFFFFBFE)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(240.dp).background(Color(0xFFFFF0E6D3)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = menu.emoji, fontSize = 100.sp)
                }

                Column(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = menu.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF2C1A0E),
                            modifier = Modifier.weight(1f)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text("⭐", fontSize = 16.sp)
                            Text(text = menu.rating.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF333333))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = menu.price, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF7C4DFF))
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color(0xFFEEEEEE))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Deskripsi", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2C1A0E))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = menu.description,
                        fontSize = 14.sp,
                        color = Color(0xFF666666),
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, "Saya mau order ${menu.name} seharga ${menu.price} dari Kopique Coffee Shop!")
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Order via"))
                        },
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C4DFF))
                    ) {
                        Text("🛒 Pesan Sekarang", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = onBack,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF7C4DFF))
                    ) {
                        Text("Back", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}
