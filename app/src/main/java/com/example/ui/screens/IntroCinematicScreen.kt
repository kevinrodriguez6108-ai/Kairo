package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import kotlin.math.ceil
import kotlinx.coroutines.delay

private data class CinematicScene(
    val actLabel: String,
    val headline: String,
    val subtitle: String,
    val scriptureQuote: String,
    val scriptureReference: String
)

private val cinematicScenes = listOf(
    CinematicScene(
        actLabel = "ACTO I • EL LLAMADO",
        headline = "En medio del ruido del mundo...",
        subtitle = "Dios conoce tu nombre, tus batallas y los sueños más profundos de tu corazón.",
        scriptureQuote = "«Porque yo sé muy bien los planes que tengo para ustedes: planes de paz y no de desgracia, para darles un futuro y una esperanza.»",
        scriptureReference = "Jeremías 29:11"
    ),
    CinematicScene(
        actLabel = "ACTO II • LA PROMESA",
        headline = "No caminas en soledad",
        subtitle = "Tu juventud es tierra sagrada. La fe católica es tu brújula, tu refugio y tu fuerza viva.",
        scriptureQuote = "«Que nadie menosprecie tu juventud; sé más bien ejemplo para los creyentes en la palabra, en la conducta, en el amor y en la fe.»",
        scriptureReference = "1 Timoteo 4:12"
    ),
    CinematicScene(
        actLabel = "ACTO III • TU TIEMPO DE GRACIA",
        headline = "Bienvenido a Kairós",
        subtitle = "El momento oportuno de Dios para transformar tu historia comienza hoy.",
        scriptureQuote = "«¡Cristo vive y te quiere vivo! Él es la eterna juventud que renueva tu alma cada mañana.»",
        scriptureReference = "Christus Vivit • Papa Francisco"
    )
)

@Composable
fun IntroCinematicScreen(
    onCinematicFinished: () -> Unit,
    modifier: Modifier = Modifier,
    totalDurationMs: Int = 10_000
) {
    val stepMs = 100
    var elapsedMs by remember { mutableIntStateOf(0) }

    LaunchedEffect(totalDurationMs) {
        elapsedMs = 0
        while (elapsedMs < totalDurationMs) {
            delay(stepMs.toLong())
            elapsedMs += stepMs
        }
        onCinematicFinished()
    }

    val progress = (elapsedMs.toFloat() / totalDurationMs.toFloat()).coerceIn(0f, 1f)
    val secondsRemaining = ceil((totalDurationMs - elapsedMs).coerceAtLeast(0) / 1000.0).toInt()
    val currentSceneIndex = when {
        progress < 0.34f -> 0
        progress < 0.68f -> 1
        else -> 2
    }
    val currentScene = cinematicScenes[currentSceneIndex]

    val infiniteTransition = rememberInfiniteTransition(label = "cinematic_glow")
    val haloScale by infiniteTransition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.14f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "halo_scale"
    )
    val rayAlpha by infiniteTransition.animateFloat(
        initialValue = 0.18f,
        targetValue = 0.42f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ray_alpha"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF070D1F))
            .testTag("intro_cinematic_screen")
    ) {
        // Background Hero Image with slow zoom & deep midnight-gold vignette
        Image(
            painter = painterResource(id = R.drawable.img_hero_banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .scale(1f + (progress * 0.08f))
        )

        // Deep atmospheric gradient overlay + celestial light rays
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF070D1F).copy(alpha = 0.78f),
                            Color(0xFF0F1E45).copy(alpha = 0.85f),
                            Color(0xFF060B19).copy(alpha = 0.96f)
                        )
                    )
                )
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width * 0.5f, size.height * 0.28f)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFBBF24).copy(alpha = rayAlpha),
                        Color(0xFF3B82F6).copy(alpha = rayAlpha * 0.45f),
                        Color.Transparent
                    ),
                    center = center,
                    radius = size.minDimension * 0.65f
                ),
                center = center,
                radius = size.minDimension * 0.65f
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top bar: Cinematic badge + countdown timer + skip button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color.White.copy(alpha = 0.12f),
                    modifier = Modifier.testTag("cinematic_timer_badge")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = Color(0xFFFBBF24),
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "INTRO KAIRÓS • ${secondsRemaining}s",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.8.sp
                        )
                    }
                }

                OutlinedButton(
                    onClick = onCinematicFinished,
                    shape = RoundedCornerShape(50),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        Color.White.copy(alpha = 0.35f)
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("btn_skip_cinematic")
                ) {
                    Text(
                        text = "Omitir",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Omitir cinemática",
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            // Centerpiece: Pulsing Sacred Emblem + Animated 3-Act Storytelling
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(120.dp)
                ) {
                    // Outer glowing ring
                    Box(
                        modifier = Modifier
                            .size(116.dp)
                            .scale(haloScale)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFFFBBF24).copy(alpha = 0.45f),
                                        Color.Transparent
                                    )
                                )
                            )
                    )

                    Surface(
                        shape = CircleShape,
                        color = Color(0xFF1E3A8A),
                        modifier = Modifier
                            .size(88.dp)
                            .border(
                                width = 2.5.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFFFBBF24), Color(0xFF93C5FD))
                                ),
                                shape = CircleShape
                            )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_app_icon),
                            contentDescription = "Emblema Kairós",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                AnimatedContent(
                    targetState = currentScene,
                    transitionSpec = {
                        (fadeIn(tween(500)) + scaleIn(initialScale = 0.95f, animationSpec = tween(500)))
                            .togetherWith(fadeOut(tween(350)))
                    },
                    label = "cinematic_scene_transition"
                ) { scene ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = Color(0xFFFBBF24).copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = scene.actLabel,
                                color = Color(0xFFFDE68A),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 1.2.sp,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = scene.headline,
                            color = Color.White,
                            fontSize = 28.sp,
                            lineHeight = 34.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.testTag("cinematic_headline")
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = scene.subtitle,
                            color = Color(0xFFE2E8F0),
                            fontSize = 15.sp,
                            lineHeight = 22.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(22.dp))

                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Color.White.copy(alpha = 0.09f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFFBBF24).copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(20.dp)
                                )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(18.dp)
                            ) {
                                Text(
                                    text = scene.scriptureQuote,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 14.sp,
                                    lineHeight = 21.sp,
                                    fontStyle = FontStyle.Italic,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "— ${scene.scriptureReference}",
                                    color = Color(0xFFFBBF24),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // Bottom section: 3-Act step pills + 10-second linear progress bar
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    cinematicScenes.indices.forEach { idx ->
                        val isActive = idx == currentSceneIndex
                        Box(
                            modifier = Modifier
                                .height(6.dp)
                                .width(if (isActive) 28.dp else 10.dp)
                                .clip(RoundedCornerShape(50))
                                .background(
                                    if (isActive) Color(0xFFFBBF24)
                                    else Color.White.copy(alpha = 0.3f)
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                LinearProgressIndicator(
                    progress = { progress },
                    color = Color(0xFFFBBF24),
                    trackColor = Color.White.copy(alpha = 0.2f),
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .testTag("cinematic_progress_bar")
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Preparando tu espacio de fe... (${secondsRemaining} s restantes)",
                    color = Color(0xFFCBD5E1),
                    fontSize = 12.sp
                )
            }
        }
    }
}
