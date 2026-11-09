package com.teorigy.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF101522)
private val Panel = Color(0xFF192235)
private val Accent = Color(0xFF8B9CFF)
private val Muted = Color(0xFF9AA6BD)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TeorigyApp() }
    }
}

data class ChatMessage(val text: String, val fromUser: Boolean)

@Composable
fun TeorigyApp() {
    var input by remember { mutableStateOf("") }
    var messages by remember {
        mutableStateOf(
            listOf(ChatMessage("Merhaba! Ben TEORIGY AI. Nasıl yardımcı olabilirim?", false))
        )
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Accent,
            background = Ink,
            surface = Panel,
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Ink).statusBarsPadding()
                .navigationBarsPadding()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(42.dp).background(Accent.copy(alpha = .16f), RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Accent)
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("TEORIGY AI", fontSize = 19.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Kişisel yapay zekâ asistanın", fontSize = 12.sp, color = Muted)
                }
                IconButton(onClick = { messages = listOf(ChatMessage("Yeni sohbet başlatıldı. Sana nasıl yardımcı olayım?", false)) }) {
                    Icon(Icons.Default.Add, contentDescription = "Yeni sohbet", tint = Color.White)
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Settings, contentDescription = "Ayarlar", tint = Muted)
                }
            }

            HorizontalDivider(color = Color.White.copy(alpha = .08f))

            if (messages.size == 1) {
                Column(
                    modifier = Modifier.weight(1f).fillMaxWidth().padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Düşüncelerini
birlikte gerçeğe
dönüştürelim.", fontSize = 30.sp,
                        lineHeight = 38.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(Modifier.height(12.dp))
                    Text("TEORIGY AI ile sohbet et, fikirlerini planla ve işlerini organize et.",
                        color = Muted, fontSize = 14.sp, lineHeight = 21.sp)
                    Spacer(Modifier.height(24.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        SuggestionChip("Fikir üret")
                        SuggestionChip("Plan oluştur")
                    }
                }
Sen gönderdin
Text(
    text = "Düşüncelerini\nbirlikte gerçeğe\ndönüştürelim.",
    fontSize = 30.sp,
    lineHeight = 38.sp,
    fontWeight = FontWeight.Bold,
    color = Color.White
)

Spacer(Modifier.height(12.dp))

Text(
    text = "TEORIGY AI ile sohbet et, fikirlerini planla ve işlerini organize et.",
    color = Muted,
    fontSize = 14.sp,
    lineHeight = 21.sp
)

Spacer(Modifier.height(24.dp))

Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    SuggestionChip("Fikir üret")
    SuggestionChip("Plan oluştur")
}
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentPadding = PaddingValues(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(messages) { message ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = if (message.fromUser) Arrangement.End else Arrangement.Start
                        ) {
                            Surface(
                                color = if (message.fromUser) Accent.copy(alpha = .20f) else Panel,
                                shape = RoundedCornerShape(18.dp),
                                modifier = Modifier.widthIn(max = 300.dp)
                            ) {
                                Text(message.text, modifier = Modifier.padding(14.dp),
                                    color = Color.White, fontSize = 14.sp, lineHeight = 20.sp)
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)
                    .background(Panel, RoundedCornerShape(24.dp)).padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.weight(1f).padding(vertical = 10.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.White, fontSize = 14.sp),
                    cursorBrush = androidx.compose.ui.graphics.SolidColor(Accent),
                    decorationBox = { inner ->
                        if (input.isEmpty()) Text("TEORIGY AI'ye yaz...", color = Muted, fontSize = 14.sp)
                        inner()
                    }
                )
                IconButton(
                    onClick = {
                        val prompt = input.trim()
                        if (prompt.isNotEmpty()) {
                            messages = messages + ChatMessage(prompt, true) +
                                ChatMessage("Mesajını aldım. Şu an başlangıç arayüzündeyim; yapay zekâ modeli ve ajan özellikleri sonraki aşamada bağlanacak.", false)
                            input = ""
                        }
                    },
                    enabled = input.isNotBlank(),
                    modifier = Modifier.size(42.dp).background(if (input.isNotBlank()) Accent else Accent.copy(alpha = .25f), CircleShape)
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Gönder", tint = Ink)
                }
            }
            Text("İlk sürüm • Yerel model henüz bağlanmadı",
                color = Muted.copy(alpha = .7f), fontSize = 10.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 8.dp))
        }
    }
}

@Composable
private fun SuggestionChip(label: String) {
    Surface(color = Panel, shape = RoundedCornerShape(50), tonalElevation = 0.dp) {
        Text(label, color = Muted, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp))
    }
}
