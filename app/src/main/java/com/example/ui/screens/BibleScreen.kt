package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.data.model.BiblePassage
import com.example.data.model.Testament
import com.example.data.repository.BibleRepository
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.Screen

@Composable
fun BibleScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val searchQuery by viewModel.bibleSearchQuery.collectAsState()
    val activeFilter by viewModel.bibleFilter.collectAsState()
    val selectedTab by viewModel.bibleTab.collectAsState()
    val selectedBibleBook by viewModel.selectedBibleBook.collectAsState()
    val context = LocalContext.current

    // Si hay un libro bíblico abierto, mostrar la pantalla de lectura completa de todos sus capítulos
    if (selectedBibleBook != null) {
        BibleBookChapterReader(
            viewModel = viewModel,
            book = selectedBibleBook!!,
            modifier = modifier
        )
        return
    }

    val searchTabs = listOf("Pasajes & Temas", "Los 73 Libros", "¿Por qué 73 libros?")

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("bible_screen")
    ) {
        // Top Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(34.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Biblia Católica",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Los 73 libros sagrados y páginas de luz para tu vida",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Search Bar Input
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.setBibleSearchQuery(it) },
                placeholder = { Text("Buscar versículo, libro o tema (paz, amistad, deuterocanónicos...)", fontSize = 13.sp) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.setBibleSearchQuery("") }) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "Limpiar búsqueda")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_bible_search")
            )
        }

        // Secondary Tabs
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.testTag("bible_tab_row")
        ) {
            searchTabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { viewModel.setBibleTab(index) },
                    text = {
                        Text(
                            text = title,
                            fontSize = 13.sp,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    modifier = Modifier.testTag("bible_subtab_$index")
                )
            }
        }

        when (selectedTab) {
            0 -> PassagesSearchTab(
                viewModel = viewModel,
                searchQuery = searchQuery,
                activeFilter = activeFilter,
                context = context
            )
            1 -> BooksCatalogTab(
                searchQuery = searchQuery,
                onOpenBook = { book -> viewModel.openBibleBook(book) }
            )
            2 -> CatholicCanonGuideTab(
                onNavigateToPassages = { viewModel.setBibleTab(0) }
            )
        }
    }
}

@Composable
fun PassagesSearchTab(
    viewModel: MainViewModel,
    searchQuery: String,
    activeFilter: String,
    context: Context
) {
    val quickFilters = listOf(
        "Todos",
        "Deuterocanónicos",
        "Ansiedad y Paz",
        "Amistad",
        "Sacramentos",
        "Juventud",
        "Antiguo Testamento",
        "Nuevo Testamento"
    )

    val passages = remember(searchQuery, activeFilter) {
        BibleRepository.searchPassages(searchQuery, activeFilter)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Filter Chips Row
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .testTag("bible_filter_chips_row"),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(quickFilters) { filter ->
                val isSelected = activeFilter == filter
                FilterChip(
                    selected = isSelected,
                    onClick = { viewModel.setBibleFilter(filter) },
                    label = { Text(filter, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.testTag("filter_chip_${filter.lowercase().replace(" ", "_")}")
                )
            }
        }

        // Passages Count Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${passages.size} pasajes encontrados",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (searchQuery.isNotEmpty() || activeFilter != "Todos") {
                Text(
                    text = "Restablecer filtros",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        viewModel.setBibleSearchQuery("")
                        viewModel.setBibleFilter("Todos")
                    }
                )
            }
        }

        if (passages.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "No se encontraron pasajes",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Prueba buscando términos como 'paz', 'amor', 'amigo', 'fuerza' o seleccionando 'Todos'.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(top = 8.dp, bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(passages, key = { it.id }) { passage ->
                    val matchingBook = remember(passage.book) {
                        BibleRepository.catholicBooks.find {
                            passage.book.contains(it.name, ignoreCase = true) || it.name.contains(passage.book, ignoreCase = true)
                        }
                    }
                    val chapterNumber = remember(passage.reference) {
                        passage.reference
                            .substringAfterLast(" ", "")
                            .substringBefore(",")
                            .toIntOrNull()
                            ?: passage.reference
                                .replace(Regex("[^0-9, ]"), "")
                                .trim()
                                .substringBefore(",")
                                .trim()
                                .toIntOrNull()
                            ?: 1
                    }

                    BiblePassageCard(
                        passage = passage,
                        onReadFullChapter = if (matchingBook != null) {
                            { viewModel.openBibleBook(matchingBook, chapterNumber) }
                        } else null,
                        onCopy = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Versículo Bíblico", "${passage.reference}\n«${passage.text}»")
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "¡Versículo copiado al portapapeles!", Toast.LENGTH_SHORT).show()
                        },
                        onSaveToJournal = {
                            viewModel.savePassageToJournal(passage)
                            viewModel.navigateTo(Screen.Journal)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BiblePassageCard(
    passage: BiblePassage,
    onReadFullChapter: (() -> Unit)? = null,
    onCopy: () -> Unit,
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
            .testTag("passage_card_${passage.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Badges Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                ) {
                    Text(
                        text = passage.theme.uppercase(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                if (passage.isDeuterocanonical) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "CANON CATÓLICO",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Reference Title
            Text(
                text = passage.reference,
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Sacred Scripture Text
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "«${passage.text}»",
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(12.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Youth Context & Modern Relevance
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    imageVector = Icons.Default.Lightbulb,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(18.dp)
                        .padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = passage.youthContext,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Reflection Prompt
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = passage.reflectionPrompt,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons (Read Full Chapter + Copy + Save to Journal)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (onReadFullChapter != null) {
                    OutlinedButton(
                        onClick = onReadFullChapter,
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("btn_read_chapter_${passage.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Leer Capítulos", fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                    }
                } else {
                    Spacer(modifier = Modifier.width(1.dp))
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedButton(
                        onClick = onCopy,
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("btn_copy_passage_${passage.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Copiar", fontSize = 11.sp)
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Button(
                        onClick = onSaveToJournal,
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("btn_journal_passage_${passage.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.BookmarkAdd,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Diario", fontSize = 11.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun BooksCatalogTab(
    searchQuery: String,
    onOpenBook: (BibleBookInfo) -> Unit
) {
    var selectedTestament by remember { mutableStateOf<Testament?>(null) }
    var deuterocanonicalOnly by remember { mutableStateOf(false) }

    val books = remember(searchQuery, selectedTestament, deuterocanonicalOnly) {
        BibleRepository.searchBooks(searchQuery, selectedTestament, deuterocanonicalOnly)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Filter Buttons (All 73, AT 46, NT 27, Deuterocanónicos 7)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedTestament == null && !deuterocanonicalOnly,
                    onClick = {
                        selectedTestament = null
                        deuterocanonicalOnly = false
                    },
                    label = { Text("Los 73 Libros", fontSize = 12.sp) }
                )
            }
            item {
                FilterChip(
                    selected = selectedTestament == Testament.ANTIGUO && !deuterocanonicalOnly,
                    onClick = {
                        selectedTestament = Testament.ANTIGUO
                        deuterocanonicalOnly = false
                    },
                    label = { Text("Antiguo Testamento (46)", fontSize = 12.sp) }
                )
            }
            item {
                FilterChip(
                    selected = selectedTestament == Testament.NUEVO && !deuterocanonicalOnly,
                    onClick = {
                        selectedTestament = Testament.NUEVO
                        deuterocanonicalOnly = false
                    },
                    label = { Text("Nuevo Testamento (27)", fontSize = 12.sp) }
                )
            }
            item {
                FilterChip(
                    selected = deuterocanonicalOnly,
                    onClick = {
                        deuterocanonicalOnly = !deuterocanonicalOnly
                        if (deuterocanonicalOnly) selectedTestament = null
                    },
                    label = { Text("Deuterocanónicos (7)", fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                        selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                    )
                )
            }
        }

        // List of Catholic Books
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 6.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(books, key = { it.name }) { book ->
                BibleBookItemCard(
                    book = book,
                    onOpenBook = onOpenBook
                )
            }
        }
    }
}

@Composable
fun BibleBookItemCard(
    book: BibleBookInfo,
    onOpenBook: (BibleBookInfo) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { expanded = !expanded }
            .testTag("book_card_${book.abbreviation.lowercase()}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onOpenBook(book) }
                ) {
                    Surface(
                        shape = CircleShape,
                        color = if (book.isDeuterocanonical) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = book.abbreviation,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (book.isDeuterocanonical) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = book.name,
                                fontSize = 16.sp,
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
                            text = "${book.category} • ${book.chaptersCount} capítulos • ${if (book.testament == Testament.ANTIGUO) "AT" else "NT"}",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    androidx.compose.material3.FilledTonalButton(
                        onClick = { onOpenBook(book) },
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.testTag("btn_quick_read_${book.abbreviation.lowercase()}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Leer", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    IconButton(
                        onClick = { expanded = !expanded },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = if (expanded) "Menos" else "Más",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    Text(
                        text = book.description,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Versículo clave: ${book.keyVerse}",
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = { onOpenBook(book) },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btn_read_book_${book.abbreviation.lowercase()}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Leer los ${book.chaptersCount} capítulos completos", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun CatholicCanonGuideTab(
    onNavigateToPassages: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Explicación de los 73 Libros y Deuterocanónicos
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "¿Por qué la Biblia Católica tiene 73 libros?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "A diferencia de las Biblias protestantes (que tienen 66 libros), la Iglesia Católica conserva los 73 libros completos inspirados por Dios:\n\n" +
                                "• 46 libros en el Antiguo Testamento.\n" +
                                "• 27 libros en el Nuevo Testamento.\n\n" +
                                "Los 7 libros adicionales se llaman 'deuterocanónicos' (Tobías, Judit, 1 y 2 Macabeos, Sabiduría, Eclesiástico/Sirácida y Baruc). " +
                                "Estaban incluidos en la versión griega Septuaginta (LXX), que era la Biblia que utilizaban los apóstoles y los primeros cristianos en todo el mundo mediterráneo. " +
                                "Fueron confirmados solemnemente en los Concilios de Roma (382 d.C.), Hipona (393 d.C.), Cartago (397 d.C.) y Trento (1546).",
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Lectio Divina en 4 Pasos para Jóvenes
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.VolunteerActivism,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Cómo orar con la Biblia: Lectio Divina",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "El método milenario que transforma la lectura en un encuentro vivo con Dios en 4 pasos:",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    BibleRepository.lectioDivinaSteps.forEach { (stepTitle, stepDesc) ->
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = stepTitle,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = stepDesc,
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = onNavigateToPassages,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Explorar pasajes para meditar")
                    }
                }
            }
        }

        // La Biblia en la Santa Misa Católica
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "La Biblia en la Santa Misa",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "En cada Misa católica se proclama la Palabra de Dios en la 'Liturgia de la Palabra':\n\n" +
                                "1. Primera Lectura: Generalmente del Antiguo Testamento (nos muestra la promesa).\n" +
                                "2. Salmo Responsorial: Cantado o rezado en comunidad con las oraciones de David.\n" +
                                "3. Segunda Lectura: De las cartas apostólicas (consejos para vivir hoy).\n" +
                                "4. Santo Evangelio: De pie, en señal de respeto, escuchando la voz viva de Jesucristo.",
                        fontSize = 13.sp,
                        lineHeight = 19.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
