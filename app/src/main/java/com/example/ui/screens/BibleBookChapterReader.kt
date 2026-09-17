package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.BibleBookInfo
import com.example.data.model.BibleChapter
import com.example.data.model.BibleVerse
import com.example.data.model.Testament
import com.example.data.model.YouthChapterExplanation
import com.example.data.repository.BibleChapterRepository
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibleBookChapterReader(
    viewModel: MainViewModel,
    book: BibleBookInfo,
    modifier: Modifier = Modifier
) {
    val currentChapterNum by viewModel.selectedChapterNumber.collectAsState()
    val textScale by viewModel.bibleTextScale.collectAsState()
    val readerSection by viewModel.chapterReaderSection.collectAsState()
    val context = LocalContext.current

    val currentChapter = remember(book, currentChapterNum) {
        BibleChapterRepository.getChapter(book, currentChapterNum)
    }

    var showChapterPickerModal by remember { mutableStateOf(false) }

    val chapterListState = rememberLazyListState()

    // Auto-scroll chapter selector when chapter changes
    LaunchedEffect(currentChapterNum) {
        val targetIndex = (currentChapterNum - 1).coerceAtLeast(0)
        chapterListState.animateScrollToItem(targetIndex)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.closeBibleBook() },
                        modifier = Modifier.testTag("btn_close_book_reader")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver al catálogo"
                        )
                    }
                },
                title = {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = book.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if (book.isDeuterocanonical) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        text = "Deuterocanónico",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                        Text(
                            text = "${book.chaptersCount} capítulos • ${if (book.testament == Testament.ANTIGUO) "Antiguo Testamento" else "Nuevo Testamento"}",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    // Quick Chapter Picker dialog trigger
                    IconButton(
                        onClick = { showChapterPickerModal = true },
                        modifier = Modifier.testTag("btn_open_chapter_picker")
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.padding(2.dp)
                        ) {
                            Text(
                                text = "Cap. $currentChapterNum",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }

                    // Text Size Toggle
                    IconButton(
                        onClick = {
                            val nextScale = when (textScale) {
                                0.9f -> 1.0f
                                1.0f -> 1.18f
                                1.18f -> 1.35f
                                else -> 0.9f
                            }
                            viewModel.setBibleTextScale(nextScale)
                        },
                        modifier = Modifier.testTag("btn_toggle_text_size")
                    ) {
                        Icon(
                            imageVector = Icons.Default.FormatSize,
                            contentDescription = "Cambiar tamaño de texto"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            // Bottom Chapter Navigator
            Surface(
                tonalElevation = 6.dp,
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Previous Chapter Button
                    OutlinedButton(
                        onClick = { viewModel.previousBibleChapter() },
                        enabled = currentChapterNum > 1,
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("btn_prev_chapter")
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Cap. ${currentChapterNum - 1}", fontSize = 12.sp)
                    }

                    Text(
                        text = "Cap. $currentChapterNum de ${book.chaptersCount}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    // Next Chapter Button
                    Button(
                        onClick = { viewModel.nextBibleChapter() },
                        enabled = currentChapterNum < book.chaptersCount,
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("btn_next_chapter")
                    ) {
                        Text("Cap. ${currentChapterNum + 1}", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        },
        modifier = modifier
            .fillMaxSize()
            .testTag("bible_book_chapter_reader")
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Chapter Bar (Todos sus capítulos completos accesibles)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "TODOS LOS CAPÍTULOS (${book.chaptersCount})",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        letterSpacing = 0.8.sp
                    )
                    Text(
                        text = "Toca para leer",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable { showChapterPickerModal = true }
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                LazyRow(
                    state = chapterListState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("chapter_selector_row"),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(book.chaptersCount) { index ->
                        val chapterNum = index + 1
                        val isSelected = chapterNum == currentChapterNum

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                            shadowElevation = if (isSelected) 2.dp else 0.dp,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { viewModel.selectBibleChapter(chapterNum) }
                                .testTag("chip_chapter_$chapterNum")
                        ) {
                            Text(
                                text = "Cap. $chapterNum",
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }

            // Reader Tabs: 0: Lectura & Explicación, 1: Solo Versículos, 2: Para Entender Hoy
            TabRow(
                selectedTabIndex = readerSection,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.testTag("chapter_reader_tab_row")
            ) {
                Tab(
                    selected = readerSection == 0,
                    onClick = { viewModel.setChapterReaderSection(0) },
                    text = { Text("Lectura & Claves", fontSize = 12.sp, fontWeight = if (readerSection == 0) FontWeight.Bold else FontWeight.Normal) }
                )
                Tab(
                    selected = readerSection == 1,
                    onClick = { viewModel.setChapterReaderSection(1) },
                    text = { Text("Solo Versículos", fontSize = 12.sp, fontWeight = if (readerSection == 1) FontWeight.Bold else FontWeight.Normal) }
                )
                Tab(
                    selected = readerSection == 2,
                    onClick = { viewModel.setChapterReaderSection(2) },
                    text = { Text("Para Entender Hoy", fontSize = 12.sp, fontWeight = if (readerSection == 2) FontWeight.Bold else FontWeight.Normal) }
                )
            }

            // Main Chapter Body
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(top = 14.dp, bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Chapter Header Title Card
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${book.name.uppercase()} • CAPÍTULO $currentChapterNum",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    letterSpacing = 0.8.sp
                                )

                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        text = "${currentChapter.verses.size} versículos clave",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = currentChapter.title,
                                fontSize = (18 * textScale).sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // Show Verses (if in section 0 or 1)
                if (readerSection == 0 || readerSection == 1) {
                    item {
                        Card(
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.MenuBook,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Texto Bíblico",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }

                                    // Copy Chapter Button
                                    OutlinedButton(
                                        onClick = {
                                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                            val textToCopy = "${book.name} $currentChapterNum\n" + currentChapter.verses.joinToString("\n") { "${it.number}. ${it.text}" }
                                            val clip = ClipData.newPlainText("Capítulo Bíblico", textToCopy)
                                            clipboard.setPrimaryClip(clip)
                                            Toast.makeText(context, "Capítulo copiado al portapapeles", Toast.LENGTH_SHORT).show()
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                        modifier = Modifier.testTag("btn_copy_chapter")
                                    ) {
                                        Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Copiar", fontSize = 11.sp)
                                    }
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                currentChapter.verses.forEach { verse ->
                                    VerseItemRow(
                                        verse = verse,
                                        textScale = textScale
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }
                        }
                    }
                }

                // Show Youth Explanation "Para Entender Hoy" (if in section 0 or 2)
                if (readerSection == 0 || readerSection == 2) {
                    item {
                        YouthExplanationCard(
                            explanation = currentChapter.explanation,
                            bookName = book.name,
                            chapterNum = currentChapterNum,
                            textScale = textScale,
                            onSaveToJournal = {
                                viewModel.saveChapterToJournal(currentChapter)
                                viewModel.navigateTo(Screen.Journal)
                            }
                        )
                    }
                }
            }
        }
    }

    // Modal Dialog to Quickly Jump to Any Chapter (1 to N)
    if (showChapterPickerModal) {
        AlertDialog(
            onDismissRequest = { showChapterPickerModal = false },
            title = {
                Text(
                    text = "Seleccionar Capítulo de ${book.name}",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Elige un capítulo para comenzar a leer (1 a ${book.chaptersCount}):",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                    ) {
                        items(book.chaptersCount) { index ->
                            val chNum = index + 1
                            val isSelected = chNum == currentChapterNum

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
                                    .clickable {
                                        viewModel.selectBibleChapter(chNum)
                                        showChapterPickerModal = false
                                    }
                                    .padding(horizontal = 12.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Capítulo $chNum",
                                    fontSize = 14.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Seleccionado",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showChapterPickerModal = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@Composable
fun VerseItemRow(
    verse: BibleVerse,
    textScale: Float
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (verse.isHighlight) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f) else Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(if (verse.isHighlight) 10.dp else 4.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                shape = CircleShape,
                color = if (verse.isHighlight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.size(24.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "${verse.number}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (verse.isHighlight) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = verse.text,
                fontSize = (15 * textScale).sp,
                lineHeight = (22 * textScale).sp,
                fontWeight = if (verse.isHighlight) FontWeight.SemiBold else FontWeight.Normal,
                fontStyle = if (verse.isHighlight) FontStyle.Italic else FontStyle.Normal,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun YouthExplanationCard(
    explanation: YouthChapterExplanation,
    bookName: String,
    chapterNum: Int,
    textScale: Float,
    onSaveToJournal: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("card_youth_explanation")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            // Header Banner
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.size(34.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "PARA ENTENDERLO HOY",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        letterSpacing = 0.8.sp
                    )
                    Text(
                        text = "Claves para tu vida cotidiana",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Main Idea
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "¿De qué trata este capítulo?",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = explanation.mainIdea,
                        fontSize = (13 * textScale).sp,
                        lineHeight = (19 * textScale).sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Youth Context
            Text(
                text = "¿Cómo se aplica a un joven o señorita hoy?",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = explanation.youthContext,
                fontSize = (13 * textScale).sp,
                lineHeight = (19 * textScale).sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 3 Key Teachings
            Text(
                text = "Puntos clave para recordar:",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(6.dp))
            explanation.keyTeachings.forEachIndexed { i, teaching ->
                Row(
                    modifier = Modifier.padding(vertical = 3.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = teaching,
                        fontSize = (12 * textScale).sp,
                        lineHeight = (18 * textScale).sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Reflection Question Box
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Pregunta para tu corazón:",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = explanation.reflectionQuestion,
                            fontSize = (13 * textScale).sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Youth Prayer
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.35f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.VolunteerActivism,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Oración para cerrar la lectura:",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "«${explanation.youthPrayer}»",
                            fontSize = (12 * textScale).sp,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Save to Journal Button
            Button(
                onClick = onSaveToJournal,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("btn_save_chapter_to_journal")
            ) {
                Icon(imageVector = Icons.Default.BookmarkAdd, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Llevar esta reflexión a Mi Diario")
            }
        }
    }
}
