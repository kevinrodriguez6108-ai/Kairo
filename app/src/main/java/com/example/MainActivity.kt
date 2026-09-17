package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.DailyQuoteDialog
import com.example.ui.screens.BibleScreen
import com.example.ui.screens.DilemmasScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.JournalScreen
import com.example.ui.screens.PrayerScreen
import com.example.ui.screens.SacramentsScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.Screen

class MainActivity : ComponentActivity() {
  private val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        KairosApp(viewModel = viewModel)
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KairosApp(viewModel: MainViewModel) {
  val currentScreen by viewModel.currentScreen.collectAsState()
  val showDailyQuoteDialog by viewModel.showDailyQuoteDialog.collectAsState()
  val dailyQuote by viewModel.dailyQuote.collectAsState()
  val dailyQuoteSaved by viewModel.dailyQuoteSaved.collectAsState()

  if (showDailyQuoteDialog) {
    DailyQuoteDialog(
      quote = dailyQuote,
      isSavedToJournal = dailyQuoteSaved,
      onDismiss = { viewModel.dismissDailyQuoteDialog() },
      onNextQuote = { viewModel.nextDailyQuote() },
      onSaveToJournal = { viewModel.saveDailyQuoteToJournal(it) }
    )
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    contentWindowInsets = WindowInsets.safeDrawing,
    topBar = {
      TopAppBar(
        navigationIcon = {
          if (currentScreen is Screen.Bible) {
            IconButton(
              onClick = { viewModel.navigateTo(Screen.Home) },
              modifier = Modifier.testTag("btn_back_from_bible")
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver al inicio"
              )
            }
          }
        },
        title = {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
              shape = CircleShape,
              color = MaterialTheme.colorScheme.primaryContainer,
              modifier = Modifier.size(34.dp)
            ) {
              Image(
                painter = painterResource(id = R.drawable.img_app_icon),
                contentDescription = "Logo Kairós",
                modifier = Modifier
                  .fillMaxSize()
                  .clip(CircleShape)
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
              text = if (currentScreen is Screen.Bible) "Biblia Católica" else "Kairós",
              fontSize = 20.sp,
              fontWeight = FontWeight.ExtraBold,
              color = MaterialTheme.colorScheme.onSurface
            )
          }
        },
        actions = {
          IconButton(
            onClick = { viewModel.showDailyQuoteDialog() },
            modifier = Modifier.testTag("btn_top_bar_daily_quote")
          ) {
            Icon(
              imageVector = Icons.Default.FormatQuote,
              contentDescription = "Cita del Día",
              tint = MaterialTheme.colorScheme.primary
            )
          }
          IconButton(
            onClick = { viewModel.navigateTo(Screen.Bible) },
            modifier = Modifier.testTag("btn_top_bar_search_bible")
          ) {
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = "Buscar en la Biblia Católica",
              tint = if (currentScreen is Screen.Bible) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier.testTag("app_top_bar")
      )
    },
    bottomBar = {
      NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.testTag("main_navigation_bar")
      ) {
        NavigationBarItem(
          selected = currentScreen is Screen.Home,
          onClick = { viewModel.navigateTo(Screen.Home) },
          icon = { Icon(Icons.Default.Home, contentDescription = "Hoy") },
          label = { Text("Hoy", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
          colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.primary
          ),
          modifier = Modifier.testTag("nav_item_home")
        )

        NavigationBarItem(
          selected = currentScreen is Screen.Sacraments,
          onClick = { viewModel.navigateTo(Screen.Sacraments) },
          icon = { Icon(Icons.Default.Shield, contentDescription = "Sacramentos") },
          label = { Text("Sacramentos", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
          colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.primary
          ),
          modifier = Modifier.testTag("nav_item_sacraments")
        )

        NavigationBarItem(
          selected = currentScreen is Screen.Dilemmas,
          onClick = { viewModel.navigateTo(Screen.Dilemmas) },
          icon = { Icon(Icons.Default.Lightbulb, contentDescription = "Dilemas") },
          label = { Text("Dilemas", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
          colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.primary
          ),
          modifier = Modifier.testTag("nav_item_dilemmas")
        )

        NavigationBarItem(
          selected = currentScreen is Screen.Journal,
          onClick = { viewModel.navigateTo(Screen.Journal) },
          icon = { Icon(Icons.Default.MenuBook, contentDescription = "Mi Diario") },
          label = { Text("Diario", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
          colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.primary
          ),
          modifier = Modifier.testTag("nav_item_journal")
        )

        NavigationBarItem(
          selected = currentScreen is Screen.Prayers,
          onClick = { viewModel.navigateTo(Screen.Prayers) },
          icon = { Icon(Icons.Default.SelfImprovement, contentDescription = "Pausa") },
          label = { Text("Pausa", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
          colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.primary
          ),
          modifier = Modifier.testTag("nav_item_prayers")
        )
      }
    }
  ) { innerPadding ->
    Crossfade(
      targetState = currentScreen,
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
      label = "screen_crossfade"
    ) { screen ->
      when (screen) {
        is Screen.Home -> HomeScreen(viewModel = viewModel)
        is Screen.Sacraments -> SacramentsScreen(viewModel = viewModel)
        is Screen.Dilemmas -> DilemmasScreen(viewModel = viewModel)
        is Screen.Bible -> BibleScreen(viewModel = viewModel)
        is Screen.Journal -> JournalScreen(viewModel = viewModel)
        is Screen.Prayers -> PrayerScreen(viewModel = viewModel)
      }
    }
  }
}

