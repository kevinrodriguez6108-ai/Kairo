package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.ChallengeCategory
import com.example.data.model.ChallengeNode
import com.example.data.model.ChallengeNodeType
import com.example.data.model.FaithEmblem
import com.example.data.model.FaithRankTier
import com.example.data.repository.ChallengeRepository
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.Screen

@Composable
fun ChallengesRankScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val completedCount by viewModel.completedChallengesCount.collectAsState()
    val dailyQuestionsCompletedToday by viewModel.dailyQuestionsCompletedToday.collectAsState()
    val isChallengeAntiCopyLocked by viewModel.isChallengeAntiCopyLocked.collectAsState()
    val antiCopyWarningShown by viewModel.antiCopyWarningShown.collectAsState()
    val currentRank by viewModel.currentFaithRank.collectAsState()
    val totalXp by viewModel.challengeXp.collectAsState()
    val currentStreak by viewModel.challengeStreak.collectAsState()
    val rankUpNotification by viewModel.rankUpCelebrationTier.collectAsState()
    val activeQuestionByNode by viewModel.activeQuestionByNode.collectAsState()
    val wrongAttemptsByNode by viewModel.wrongAttemptsByNode.collectAsState()

    val allChallenges = remember { ChallengeRepository.challenges }
    val allSpecialEmblems = remember { ChallengeRepository.specialEmblems }
    var activeChallengeToPlay by remember { mutableStateOf<ChallengeNode?>(null) }
    var showAllRanksDialog by remember { mutableStateOf(false) }
    var selectedEmblemDetail by remember { mutableStateOf<FaithEmblem?>(null) }

    val nextRank = FaithRankTier.nextRank(currentRank)
    val progressInTier = FaithRankTier.progressInCurrentRank(completedCount)
    val remainingForNextRank = FaithRankTier.challengesRemainingForNextRank(completedCount)
    val isDailyQuotaReached =
        dailyQuestionsCompletedToday >= FaithRankTier.MAX_DAILY_QUESTIONS || completedCount >= allChallenges.size

    // Dialog de celebración cuando sube de rango cada 5 retos
    rankUpNotification?.let { newRank ->
        RankUpCelebrationDialog(
            newRank = newRank,
            completedChallenges = completedCount,
            onDismiss = { viewModel.dismissRankUpCelebration() }
        )
    }

    // Dialog con la tabla completa de los 12 rangos y los 10 emblemas especiales
    if (showAllRanksDialog) {
        AllRanksLeagueDialog(
            currentRank = currentRank,
            completedCount = completedCount,
            onDismiss = { showAllRanksDialog = false }
        )
    }

    // Detalle de emblema especial
    selectedEmblemDetail?.let { emblem ->
        EmblemDetailDialog(
            emblem = emblem,
            isUnlocked = completedCount >= emblem.requiredChallenges,
            completedCount = completedCount,
            onDismiss = { selectedEmblemDetail = null }
        )
    }

    // Modal interactivo tipo Duolingo para responder pregunta o reto del día a día
    activeChallengeToPlay?.let { baseChallenge ->
        val currentChallengeQuestion = activeQuestionByNode[baseChallenge.id] ?: baseChallenge
        val wrongAttemptsForNode = wrongAttemptsByNode[baseChallenge.id] ?: 0
        val isAlreadyCompleted = baseChallenge.id <= completedCount
        DuolingoChallengePlayDialog(
            challenge = currentChallengeQuestion,
            wrongAttemptsCount = wrongAttemptsForNode,
            isAlreadyCompleted = isAlreadyCompleted,
            antiCopyWarningShown = antiCopyWarningShown,
            dailyQuestionIndex = (dailyQuestionsCompletedToday + 1).coerceAtMost(FaithRankTier.MAX_DAILY_QUESTIONS),
            onDismiss = {
                // Una vez que entra a una pregunta, NO puede cerrarla sin resolverla sí o sí
                viewModel.triggerAntiCopyWarning()
            },
            onChallengeCompleted = { earnedXp ->
                viewModel.completeChallengeNode(baseChallenge.id, earnedXp)
                activeChallengeToPlay = null
            },
            onWrongAttempt = {
                val nextQuestion = viewModel.recordWrongChallengeAttempt(baseChallenge.id)
                activeChallengeToPlay = nextQuestion
            }
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("challenges_rank_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // 0. PANEL DE 5 PREGUNTAS POR DÍA + BLOQUEO AL ENTRAR A UNA PREGUNTA
        item {
            DailyQuestionsAntiCopyCard(
                dailyQuestionsCompletedToday = dailyQuestionsCompletedToday,
                isChallengeAntiCopyLocked = isChallengeAntiCopyLocked,
                antiCopyWarningShown = antiCopyWarningShown,
                completedCount = completedCount,
                onReturnHome = { viewModel.navigateTo(Screen.Home) },
                onPlayNextDailyQuestion = {
                    if (!isDailyQuotaReached) {
                        val nextNodeId = (completedCount + 1).coerceAtMost(allChallenges.size)
                        activeChallengeToPlay = viewModel.startChallengeQuestion(nextNodeId)
                    }
                }
            )
        }

        // 1. HEADER TARJETA DE RANGO COMPETITIVO (12 RANGOS + EMBLEMA DE RANGO)
        item {
            RankHeaderBanner(
                currentRank = currentRank,
                nextRank = nextRank,
                completedCount = completedCount,
                dailyQuestionsCompletedToday = dailyQuestionsCompletedToday,
                progressInTier = progressInTier,
                remainingForNextRank = remainingForNextRank,
                totalXp = totalXp,
                currentStreak = currentStreak,
                onViewAllRanks = { showAllRanksDialog = true },
                onPlayCurrentChallenge = {
                    if (isDailyQuotaReached && completedCount < allChallenges.size) {
                        viewModel.navigateTo(Screen.Home)
                    } else {
                        val nextNodeId = (completedCount + 1).coerceAtMost(allChallenges.size)
                        activeChallengeToPlay = viewModel.startChallengeQuestion(nextNodeId)
                    }
                }
            )
        }

        // 2. VITRINA DE EMBLEMAS ESPECIALES DESBLOQUEABLES (10 INSIGNIAS)
        item {
            SpecialEmblemsShowcaseCard(
                emblems = allSpecialEmblems,
                completedCount = completedCount,
                onSelectEmblem = { selectedEmblemDetail = it },
                onViewAllRanks = { showAllRanksDialog = true }
            )
        }

        // 3. EXPLICACIÓN DE LAS 3 TEMÁTICAS DE PREGUNTAS (DE LA APP, HISTORIA DE JESÚS HASTA LA CRUZ Y JÓVENES)
        item {
            QuestionThemesSummaryBanner()
        }

        // 4. CAMINO DE NODOS ESTILO DUOLINGO AGRUPADO POR LAS 11 ETAPAS DE RANGO (55 PREGUNTAS)
        val challengesByTier = allChallenges.chunked(FaithRankTier.CHALLENGES_PER_RANK)
        challengesByTier.forEachIndexed { tierIndex, tierNodes ->
            val targetRankIndex = (tierIndex + 1).coerceAtMost(FaithRankTier.entries.lastIndex)
            val targetRank = FaithRankTier.entries[targetRankIndex]
            val startRank = FaithRankTier.entries[tierIndex.coerceAtMost(FaithRankTier.entries.lastIndex)]

            item(key = "section_header_$tierIndex") {
                TierSectionDivider(
                    stageNumber = tierIndex + 1,
                    fromRank = startRank,
                    toRank = targetRank,
                    isUnlocked = completedCount >= tierIndex * FaithRankTier.CHALLENGES_PER_RANK
                )
            }

            items(
                items = tierNodes,
                key = { "challenge_node_${it.id}" }
            ) { node ->
                val isCompleted = node.id <= completedCount
                val isLockedByDailyLimit =
                    node.id > completedCount && dailyQuestionsCompletedToday >= FaithRankTier.MAX_DAILY_QUESTIONS
                val isCurrentNext = node.id == completedCount + 1 && !isLockedByDailyLimit
                val isLocked = node.id > completedCount + 1 || isLockedByDailyLimit

                val activeNodeForPath = activeQuestionByNode[node.id] ?: node
                DuolingoPathNodeRow(
                    node = activeNodeForPath,
                    isCompleted = isCompleted,
                    isCurrentNext = isCurrentNext,
                    isLocked = isLocked,
                    isLockedByDailyLimit = isLockedByDailyLimit && node.id == completedCount + 1,
                    onClick = {
                        if (!isLocked) {
                            activeChallengeToPlay = viewModel.startChallengeQuestion(node.id)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun DailyQuestionsAntiCopyCard(
    dailyQuestionsCompletedToday: Int,
    isChallengeAntiCopyLocked: Boolean,
    antiCopyWarningShown: Boolean,
    completedCount: Int,
    onReturnHome: () -> Unit,
    onPlayNextDailyQuestion: () -> Unit
) {
    val maxDaily = FaithRankTier.MAX_DAILY_QUESTIONS
    val remainingToday = (maxDaily - dailyQuestionsCompletedToday).coerceAtLeast(0)
    val isDailyComplete =
        dailyQuestionsCompletedToday >= maxDaily || completedCount >= FaithRankTier.TOTAL_CHALLENGES

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDailyComplete) {
                Color(0xFF064E3B)
            } else {
                Color(0xFF1E1B4B)
            }
        ),
        border = BorderStroke(
            width = 2.dp,
            color = if (isDailyComplete) {
                Color(0xFF10B981)
            } else if (antiCopyWarningShown) {
                Color(0xFFEF4444)
            } else {
                Color(0xFFF59E0B)
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .testTag("daily_five_questions_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isDailyComplete) Icons.Default.CheckCircle else Icons.Default.Lock,
                        contentDescription = null,
                        tint = if (isDailyComplete) Color(0xFF34D399) else Color(0xFFFBBF24),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isDailyComplete) {
                            "¡5 DE 5 PREGUNTAS DE HOY COMPLETADAS!"
                        } else {
                            "5 PREGUNTAS POR DÍA • MODO ANTI-COPIA"
                        },
                        color = if (isDailyComplete) Color(0xFF34D399) else Color(0xFFFBBF24),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.8.sp
                    )
                }

                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color.White.copy(alpha = 0.14f)
                ) {
                    Text(
                        text = "$dailyQuestionsCompletedToday / $maxDaily HOY",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                            .testTag("daily_questions_counter_badge")
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 5 daily question step circles
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (qIndex in 1..maxDaily) {
                    val isDone = qIndex <= dailyQuestionsCompletedToday || completedCount >= FaithRankTier.TOTAL_CHALLENGES
                    val isCurrent = qIndex == dailyQuestionsCompletedToday + 1 && !isDailyComplete
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = when {
                            isDone -> Color(0xFF10B981)
                            isCurrent -> Color(0xFFF59E0B)
                            else -> Color.White.copy(alpha = 0.12f)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(34.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = if (isDone) "✓ P$qIndex" else "P$qIndex",
                                color = if (isDone || isCurrent) Color(0xFF0F172A) else Color.White.copy(alpha = 0.7f),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (isDailyComplete) {
                    "✅ Has terminado tus 5 preguntas de hoy. Mañana tendrás 5 preguntas nuevas para seguir subiendo de rango."
                } else {
                    "🔓 Desde esta pantalla de Retos puedes ir atrás o moverte a cualquier otra página libremente. 🔒 Cuando entres a una pregunta sí se bloqueará la salida y tendrás que resolverla sí o sí ($remainingToday restantes hoy)."
                },
                color = Color.White.copy(alpha = 0.92f),
                fontSize = 12.sp,
                lineHeight = 17.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.testTag("anti_copy_status_message")
            )

            AnimatedVisibility(
                visible = antiCopyWarningShown && isChallengeAntiCopyLocked,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFF7F1D1D),
                    border = BorderStroke(1.dp, Color(0xFFF87171)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .testTag("anti_copy_warning_banner")
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "⚠️ ¡Regreso al menú de inicio bloqueado para evitar copias!",
                            color = Color(0xFFFECACA),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "Debes completar tus 5 preguntas del día (llevas $dailyQuestionsCompletedToday de $maxDaily) antes de volver al menú principal o consultar las secciones de la app.",
                            color = Color.White,
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = onPlayNextDailyQuestion,
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFBBF24),
                                contentColor = Color(0xFF0F172A)
                            ),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                            modifier = Modifier.testTag("btn_answer_pending_daily_question")
                        ) {
                            Text(
                                text = "Responder Pregunta ${dailyQuestionsCompletedToday + 1} de $maxDaily",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
                }
            }

            if (isDailyComplete) {
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = onReturnHome,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF10B981),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("btn_return_home_from_challenges")
                ) {
                    Text(
                        text = "Volver al Menú de Inicio",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

@Composable
private fun RankHeaderBanner(
    currentRank: FaithRankTier,
    nextRank: FaithRankTier?,
    completedCount: Int,
    dailyQuestionsCompletedToday: Int,
    progressInTier: Int,
    remainingForNextRank: Int,
    totalXp: Int,
    currentStreak: Int,
    onViewAllRanks: () -> Unit,
    onPlayCurrentChallenge: () -> Unit
) {
    val rankPrimaryColor = Color(currentRank.primaryColorHex)
    val rankSecondaryColor = Color(currentRank.secondaryColorHex)

    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .testTag("rank_header_card")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF0F172A),
                            rankSecondaryColor.copy(alpha = 0.88f),
                            Color(0xFF1E293B)
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Top stats bar: Racha + XP + Botón Ver 12 Rangos y Emblemas
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = Color.White.copy(alpha = 0.14f)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocalFireDepartment,
                                    contentDescription = "Racha",
                                    tint = Color(0xFFFB923C),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Racha: $currentStreak",
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(50),
                            color = Color.White.copy(alpha = 0.14f)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Bolt,
                                    contentDescription = "Puntos XP",
                                    tint = Color(0xFFFBBF24),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "$totalXp XP",
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(50),
                        color = rankPrimaryColor.copy(alpha = 0.28f),
                        border = BorderStroke(1.dp, rankPrimaryColor),
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .clickable { onViewAllRanks() }
                            .testTag("btn_view_all_ranks")
                    ) {
                        Text(
                            text = "12 Rangos y Emblemas 🏆",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Emblem & Current Rank Title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Surface(
                        shape = CircleShape,
                        color = rankPrimaryColor.copy(alpha = 0.25f),
                        border = BorderStroke(3.dp, rankPrimaryColor),
                        modifier = Modifier.size(74.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = currentRank.badgeIconText,
                                fontSize = 34.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "RANGO #${currentRank.ordinal + 1} DE ${FaithRankTier.entries.size} • EMBLEMA: ${currentRank.emblemName.uppercase()}",
                            color = rankPrimaryColor,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.9.sp
                        )
                        Text(
                            text = "${currentRank.rankName} • ${currentRank.spiritualTitle}",
                            color = Color.White,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.testTag("current_rank_title")
                        )
                        Text(
                            text = currentRank.description,
                            color = Color.White.copy(alpha = 0.84f),
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 5-Step Progress toward Next Rank
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color.Black.copy(alpha = 0.28f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (nextRank != null) {
                                    "Próximo rango: ${nextRank.badgeIconText} ${nextRank.rankName} (${nextRank.emblemName})"
                                } else {
                                    "¡Rango Máximo LEYENDA KAIRÓS Alcanzado!"
                                },
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (nextRank != null) {
                                    "$progressInTier / ${FaithRankTier.CHALLENGES_PER_RANK} retos"
                                } else {
                                    "$completedCount / ${FaithRankTier.TOTAL_CHALLENGES} retos"
                                },
                                color = rankPrimaryColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.testTag("rank_progress_counter")
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            for (step in 1..FaithRankTier.CHALLENGES_PER_RANK) {
                                val filled = step <= progressInTier
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(8.dp)
                                        .clip(RoundedCornerShape(50))
                                        .background(
                                            if (filled) rankPrimaryColor
                                            else Color.White.copy(alpha = 0.2f)
                                        )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = if (nextRank != null) {
                                "Llevas $completedCount/${FaithRankTier.TOTAL_CHALLENGES} preguntas totales. Te ${if (remainingForNextRank == 1) "falta 1 pregunta" else "faltan $remainingForNextRank preguntas"} para el rango ${nextRank.rankName}."
                            } else {
                                "Has conquistado los 12 rangos y los 10 emblemas de Kairós. Puedes repasar cualquier pregunta cuando quieras."
                            },
                            color = Color.White.copy(alpha = 0.78f),
                            fontSize = 11.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = onPlayCurrentChallenge,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = rankPrimaryColor,
                        contentColor = Color(0xFF0F172A)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("btn_play_next_challenge")
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = when {
                            completedCount >= FaithRankTier.TOTAL_CHALLENGES ->
                                "Repasar Duelo de Leyenda Kairós"
                            dailyQuestionsCompletedToday >= FaithRankTier.MAX_DAILY_QUESTIONS ->
                                "5/5 Preguntas de Hoy Completadas • Volver al Inicio"
                            else -> "Responder Pregunta de Hoy (${dailyQuestionsCompletedToday + 1}/5) • Reto #${completedCount + 1}"
                        },
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

@Composable
private fun SpecialEmblemsShowcaseCard(
    emblems: List<FaithEmblem>,
    completedCount: Int,
    onSelectEmblem: (FaithEmblem) -> Unit,
    onViewAllRanks: () -> Unit
) {
    val unlockedCount = emblems.count { completedCount >= it.requiredChallenges }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .testTag("special_emblems_showcase_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "COLECCIÓN DE EMBLEMAS E INSIGNIAS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 0.8.sp
                    )
                    Text(
                        text = "Emblemas desbloqueados: $unlockedCount de ${emblems.size}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Surface(
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .clickable { onViewAllRanks() }
                ) {
                    Text(
                        text = "Ver Todos",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                emblems.forEach { emblem ->
                    val isUnlocked = completedCount >= emblem.requiredChallenges
                    val accent = Color(emblem.colorHex)

                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = if (isUnlocked) {
                            accent.copy(alpha = 0.14f)
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                        },
                        border = BorderStroke(
                            width = if (isUnlocked) 1.5.dp else 1.dp,
                            color = if (isUnlocked) accent else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                        ),
                        modifier = Modifier
                            .width(124.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { onSelectEmblem(emblem) }
                            .testTag("emblem_badge_${emblem.id}")
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = if (isUnlocked) accent.copy(alpha = 0.24f) else MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier.size(44.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = if (isUnlocked) emblem.iconEmoji else "🔒",
                                        fontSize = 21.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = emblem.title,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                color = if (isUnlocked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = if (isUnlocked) "✓ Desbloqueado" else "${emblem.requiredChallenges} preg.",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isUnlocked) accent else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuestionThemesSummaryBanner() {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.55f),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "55 Preguntas Diseñadas en 3 Ejes (5 por día • Sin Copia):",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ChallengeCategory.entries.forEach { cat ->
                    val catColor = Color(cat.colorHex)
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = catColor.copy(alpha = 0.14f),
                        border = BorderStroke(1.dp, catColor.copy(alpha = 0.4f)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${cat.badgeEmoji} ${cat.label}",
                            fontSize = 10.sp,
                            lineHeight = 13.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TierSectionDivider(
    stageNumber: Int,
    fromRank: FaithRankTier,
    toRank: FaithRankTier,
    isUnlocked: Boolean
) {
    val tierColor = Color(toRank.primaryColorHex)
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (isUnlocked) tierColor.copy(alpha = 0.16f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        border = BorderStroke(
            width = 1.dp,
            color = if (isUnlocked) tierColor.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "ETAPA $stageNumber • 5 PREGUNTAS PARA EMBLEMA ${toRank.emblemName.uppercase()}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (isUnlocked) tierColor else MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "De ${fromRank.rankName} a ${toRank.badgeIconText} ${toRank.rankName} (${toRank.spiritualTitle})",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Icon(
                imageVector = if (isUnlocked) Icons.Default.EmojiEvents else Icons.Default.Lock,
                contentDescription = null,
                tint = if (isUnlocked) tierColor else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DuolingoPathNodeRow(
    node: ChallengeNode,
    isCompleted: Boolean,
    isCurrentNext: Boolean,
    isLocked: Boolean,
    isLockedByDailyLimit: Boolean = false,
    onClick: () -> Unit
) {
    val horizontalOffsetDp = when (node.id % 4) {
        1 -> (-24).dp
        2 -> 0.dp
        3 -> 24.dp
        else -> 0.dp
    }

    val infiniteTransition = rememberInfiniteTransition(label = "active_node_pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isCurrentNext) 1.05f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    val isBoss = node.type == ChallengeNodeType.BOSS_RANK_UP
    val nodeAccentColor = when {
        isCompleted -> Color(0xFF10B981)
        isCurrentNext -> Color(node.rankTier.primaryColorHex)
        else -> MaterialTheme.colorScheme.outline
    }
    val categoryColor = Color(node.category.colorHex)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        if (node.id > 1) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(14.dp)
                    .background(
                        if (isCompleted || isCurrentNext) nodeAccentColor.copy(alpha = 0.6f)
                        else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                    )
            )
        }

        Card(
            shape = RoundedCornerShape(if (isBoss) 22.dp else 18.dp),
            colors = CardDefaults.cardColors(
                containerColor = when {
                    isCurrentNext -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.65f)
                    isCompleted -> MaterialTheme.colorScheme.surface
                    else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                }
            ),
            border = BorderStroke(
                width = if (isCurrentNext || isBoss) 2.5.dp else 1.dp,
                color = when {
                    isCurrentNext -> MaterialTheme.colorScheme.primary
                    isCompleted -> Color(0xFF10B981).copy(alpha = 0.65f)
                    else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                }
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = if (isCurrentNext) 5.dp else 1.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .offset(x = horizontalOffsetDp / 2)
                .scale(if (isCurrentNext) pulseScale else 1f)
                .clip(RoundedCornerShape(if (isBoss) 22.dp else 18.dp))
                .clickable(enabled = !isLocked, onClick = onClick)
                .testTag("challenge_node_${node.id}")
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = when {
                        isCompleted -> Color(0xFF10B981)
                        isCurrentNext -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    },
                    modifier = Modifier.size(if (isBoss) 56.dp else 48.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        when {
                            isCompleted -> Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Completado",
                                tint = Color.White,
                                modifier = Modifier.size(26.dp)
                            )
                            isLocked -> Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Bloqueado",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                modifier = Modifier.size(22.dp)
                            )
                            isBoss -> Icon(
                                imageVector = Icons.Default.EmojiEvents,
                                contentDescription = "Jefe de Rango",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                            node.type == ChallengeNodeType.DAILY_ACTION -> Icon(
                                imageVector = Icons.Default.VolunteerActivism,
                                contentDescription = "Reto Juvenil",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                            else -> Icon(
                                imageVector = Icons.Default.Quiz,
                                contentDescription = "Pregunta",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = categoryColor.copy(alpha = 0.16f)
                        ) {
                            Text(
                                text = "${node.category.badgeEmoji} ${node.category.label}",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = categoryColor,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        Text(
                            text = "+${node.difficulty.xpReward} XP",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "${node.id}. ${node.title}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = if (isLocked) {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )

                    Text(
                        text = when {
                            isCompleted -> "✓ Completado • Toca para repasar (${node.biblicalReference})"
                            isLockedByDailyLimit -> "🔒 Límite de 5 preguntas por día completado • Disponible mañana"
                            isCurrentNext -> "¡Disponible ahora! Toca para responder esta pregunta del día"
                            else -> "Completa la pregunta #${node.id - 1} para desbloquear"
                        },
                        fontSize = 12.sp,
                        fontWeight = if (isCurrentNext || isLockedByDailyLimit) FontWeight.Bold else FontWeight.Normal,
                        color = when {
                            isCompleted -> Color(0xFF10B981)
                            isLockedByDailyLimit -> Color(0xFFF59E0B)
                            isCurrentNext -> MaterialTheme.colorScheme.primary
                            else -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        }
                    )
                }

                if (isCurrentNext) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        Text(
                            text = "JUGAR",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DuolingoChallengePlayDialog(
    challenge: ChallengeNode,
    wrongAttemptsCount: Int,
    isAlreadyCompleted: Boolean,
    antiCopyWarningShown: Boolean,
    dailyQuestionIndex: Int,
    onDismiss: () -> Unit,
    onChallengeCompleted: (Int) -> Unit,
    onWrongAttempt: () -> Unit
) {
    var selectedOptionIndex by remember(challenge.id, challenge.promptOrQuestion, wrongAttemptsCount) {
        mutableIntStateOf(-1)
    }
    var hasCheckedAnswer by remember(challenge.id, challenge.promptOrQuestion, wrongAttemptsCount) {
        mutableStateOf(false)
    }
    var isAnswerCorrect by remember(challenge.id, challenge.promptOrQuestion, wrongAttemptsCount) {
        mutableStateOf(false)
    }
    var commitmentAccepted by remember(challenge.id, challenge.promptOrQuestion, wrongAttemptsCount) {
        mutableStateOf(challenge.dailyMissionCommitment == null)
    }

    val categoryColor = Color(challenge.category.colorHex)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .padding(vertical = 16.dp)
                .testTag("dialog_play_challenge")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = "PREGUNTA $dailyQuestionIndex/5 HOY • #${challenge.id}/${FaithRankTier.TOTAL_CHALLENGES}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = categoryColor.copy(alpha = 0.16f)
                        ) {
                            Text(
                                text = "${challenge.category.badgeEmoji} ${challenge.category.label}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = categoryColor,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.errorContainer
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Resolver sí o sí",
                                tint = MaterialTheme.colorScheme.onErrorContainer,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Resolver sí o sí",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                    }
                }

                AnimatedVisibility(
                    visible = antiCopyWarningShown && !isAnswerCorrect,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.errorContainer,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.error),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .testTag("question_locked_warning_banner")
                    ) {
                        Text(
                            text = "🔒 ¡Salida bloqueada! Una vez que entras a una pregunta tienes que resolverla sí o sí antes de ir atrás o cambiar de página.",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = challenge.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Aviso de rotación automática cuando se equivocó en el intento anterior
                AnimatedVisibility(
                    visible = wrongAttemptsCount > 0 && !isAnswerCorrect,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.85f),
                        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.error),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .testTag("auto_switched_question_banner")
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "🔄 ¡Te equivocaste! Nueva pregunta cargada automáticamente (Intento #${wrongAttemptsCount + 1})",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Cada vez que falles una de las 5 preguntas del día, saldrá otra pregunta distinta sucesivamente hasta que respondas la correcta.",
                                fontSize = 11.sp,
                                lineHeight = 15.sp,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Prompt / Question Box
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = challenge.promptOrQuestion,
                            fontSize = 15.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.testTag("challenge_question_text")
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Referencia: ${challenge.biblicalReference}",
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Selecciona la respuesta correcta:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 4 Selectable Option Cards
                challenge.options.forEachIndexed { index, optionText ->
                    val isSelected = selectedOptionIndex == index
                    val showCorrectHighlight = hasCheckedAnswer && isAnswerCorrect && index == challenge.correctOptionIndex
                    val showWrongHighlight = hasCheckedAnswer && !isAnswerCorrect && isSelected

                    val borderColor by animateColorAsState(
                        targetValue = when {
                            showCorrectHighlight -> Color(0xFF10B981)
                            showWrongHighlight -> MaterialTheme.colorScheme.error
                            isSelected -> MaterialTheme.colorScheme.primary
                            else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        },
                        label = "opt_border"
                    )

                    val containerColor by animateColorAsState(
                        targetValue = when {
                            showCorrectHighlight -> Color(0xFF10B981).copy(alpha = 0.16f)
                            showWrongHighlight -> MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f)
                            isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                            else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                        },
                        label = "opt_bg"
                    )

                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = containerColor),
                        border = BorderStroke(
                            width = if (isSelected || showCorrectHighlight) 2.dp else 1.dp,
                            color = borderColor
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable {
                                if (!isAnswerCorrect) {
                                    selectedOptionIndex = index
                                    hasCheckedAnswer = false
                                }
                            }
                            .testTag("challenge_option_$index")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (isSelected || showCorrectHighlight) {
                                    Icons.Default.CheckCircle
                                } else {
                                    Icons.Default.RadioButtonUnchecked
                                },
                                contentDescription = null,
                                tint = when {
                                    showCorrectHighlight -> Color(0xFF10B981)
                                    showWrongHighlight -> MaterialTheme.colorScheme.error
                                    isSelected -> MaterialTheme.colorScheme.primary
                                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                                },
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = optionText,
                                fontSize = 14.sp,
                                lineHeight = 19.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Si tiene compromiso práctico, mostrar casilla
                challenge.dailyMissionCommitment?.let { commitmentText ->
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = if (commitmentAccepted) {
                            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.65f)
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        },
                        border = BorderStroke(
                            width = 1.5.dp,
                            color = if (commitmentAccepted) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { commitmentAccepted = !commitmentAccepted }
                            .testTag("challenge_daily_commitment_toggle")
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (commitmentAccepted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                contentDescription = "Aceptar compromiso del día",
                                tint = if (commitmentAccepted) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "COMPROMISO JUVENIL DE HOY (Presiona para marcar):",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = commitmentText,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                // Feedback Box (Correct / Auto-Rotated Incorrect)
                AnimatedVisibility(
                    visible = hasCheckedAnswer || (wrongAttemptsCount > 0 && !isAnswerCorrect),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = if (isAnswerCorrect) {
                            Color(0xFF10B981).copy(alpha = 0.16f)
                        } else {
                            MaterialTheme.colorScheme.errorContainer
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                            .testTag("challenge_feedback_banner")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = if (isAnswerCorrect) {
                                    "¡Respuesta Correcta! 🎉"
                                } else {
                                    "Respuesta anterior incorrecta • Se cargó otra pregunta automáticamente (Intento #${wrongAttemptsCount + 1})"
                                },
                                fontSize = 14.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = if (isAnswerCorrect) Color(0xFF059669) else MaterialTheme.colorScheme.onErrorContainer
                            )
                            if (isAnswerCorrect) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = challenge.explanation,
                                    fontSize = 13.sp,
                                    lineHeight = 18.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (!isAnswerCorrect) {
                    Button(
                        onClick = {
                            if (selectedOptionIndex >= 0) {
                                if (selectedOptionIndex == challenge.correctOptionIndex) {
                                    hasCheckedAnswer = true
                                    isAnswerCorrect = true
                                } else {
                                    // Se equivocó: cambia automáticamente a otra pregunta sucesivamente hasta que responda la correcta
                                    onWrongAttempt()
                                }
                            }
                        },
                        enabled = selectedOptionIndex >= 0 && commitmentAccepted,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("btn_check_challenge_answer")
                    ) {
                        Text(
                            text = if (!commitmentAccepted) {
                                "Marca el compromiso del día para continuar"
                            } else {
                                "Comprobar Respuesta"
                            },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                } else {
                    Button(
                        onClick = {
                            onChallengeCompleted(challenge.difficulty.xpReward)
                        },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF10B981),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("btn_claim_challenge_reward")
                    ) {
                        Text(
                            text = if (isAlreadyCompleted) {
                                "¡Excelente repaso! Continuar"
                            } else {
                                "¡Reclamar +${challenge.difficulty.xpReward} XP y Avanzar!"
                            },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RankUpCelebrationDialog(
    newRank: FaithRankTier,
    completedChallenges: Int,
    onDismiss: () -> Unit
) {
    val tierColor = Color(newRank.primaryColorHex)

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0F172A)
            ),
            border = BorderStroke(2.5.dp, tierColor),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("dialog_rank_up_celebration")
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = tierColor.copy(alpha = 0.22f)
                ) {
                    Text(
                        text = "¡NUEVO RANGO Y EMBLEMA DESBLOQUEADO!",
                        color = tierColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.1.sp,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    shape = CircleShape,
                    color = tierColor.copy(alpha = 0.25f),
                    border = BorderStroke(3.dp, tierColor),
                    modifier = Modifier.size(96.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = newRank.badgeIconText,
                            fontSize = 48.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = newRank.rankName.uppercase(),
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "${newRank.spiritualTitle} • Emblema: ${newRank.emblemName}",
                    color = tierColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "¡Has superado $completedChallenges preguntas! ${newRank.description}",
                    color = Color.White.copy(alpha = 0.88f),
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = tierColor,
                        contentColor = Color(0xFF0F172A)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("btn_dismiss_rank_up")
                ) {
                    Text(
                        text = "¡Seguir subiendo de rango!",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

@Composable
private fun EmblemDetailDialog(
    emblem: FaithEmblem,
    isUnlocked: Boolean,
    completedCount: Int,
    onDismiss: () -> Unit
) {
    val accent = Color(emblem.colorHex)
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = BorderStroke(2.dp, if (isUnlocked) accent else MaterialTheme.colorScheme.outline),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(22.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = accent.copy(alpha = 0.2f),
                    modifier = Modifier.size(72.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = if (isUnlocked) emblem.iconEmoji else "🔒",
                            fontSize = 36.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = emblem.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = emblem.subtitle,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = accent
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = emblem.description,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = if (isUnlocked) {
                        "✓ ¡Emblema desbloqueado en tu colección!"
                    } else {
                        "🔒 Se desbloquea al completar ${emblem.requiredChallenges} preguntas (llevas $completedCount)."
                    },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isUnlocked) Color(0xFF10B981) else MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Entendido", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun AllRanksLeagueDialog(
    currentRank: FaithRankTier,
    completedCount: Int,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .padding(vertical = 16.dp)
                .testTag("dialog_all_ranks_league")
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "12 Rangos y Emblemas de Kairós",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Subes 1 rango y emblema cada 5 preguntas diarias",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 410.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(FaithRankTier.entries) { tier ->
                        val isCurrent = tier == currentRank
                        val isUnlocked = completedCount >= tier.minChallenges
                        val tierColor = Color(tier.primaryColorHex)

                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = if (isCurrent) {
                                tierColor.copy(alpha = 0.18f)
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            },
                            border = if (isCurrent) BorderStroke(2.dp, tierColor) else null,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = tier.badgeIconText,
                                    fontSize = 26.sp
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${tier.rankName} • ${tier.spiritualTitle}",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Emblema: ${tier.emblemName} • Requiere ${tier.minChallenges} preguntas",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = if (isUnlocked) tierColor else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Text(
                                    text = when {
                                        isCurrent -> "ACTUAL"
                                        isUnlocked -> "✓"
                                        else -> "🔒"
                                    },
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (isCurrent) tierColor else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cerrar", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
