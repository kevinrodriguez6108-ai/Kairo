package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class CatholicEssentialCategory(
    val label: String,
    val emoji: String
) {
    ALL("Todo", "✨"),
    SACRAMENTS_7("Los 7 Sacramentos", "🕊️"),
    MASS_AND_CONFESSION("Misa y Confesión", "⛪"),
    COMMANDMENTS("Mandamientos", "📜"),
    MERCY_AND_VIRTUES("Misericordia y Virtudes", "❤️"),
    YOUTH_APOLOGETICS("Preguntas y Santos Jóvenes", "🛡️")
}

data class CatholicEssentialItem(
    val id: String,
    val category: CatholicEssentialCategory,
    val badge: String,
    val title: String,
    val subtitle: String,
    val summaryForYouth: String,
    val bulletPoints: List<String>,
    val practicalTip: String,
    val biblicalOrCatechismRef: String
)

object CatholicEssentialsRepository {
    val items: List<CatholicEssentialItem> = listOf(
        CatholicEssentialItem(
            id = "los_7_sacramentos_mapa",
            category = CatholicEssentialCategory.SACRAMENTS_7,
            badge = "BASE DE LA FE",
            title = "Los 7 Sacramentos de la Iglesia Católica",
            subtitle = "Cómo se dividen y por qué acompañan toda tu vida",
            summaryForYouth = "Además del Bautismo, la Eucaristía y la Confirmación (Iniciación Cristiana), Cristo instituyó 7 sacramentos en total para darte su gracia en cada etapa de tu vida:",
            bulletPoints = listOf(
                "1. INICIACIÓN CRISTIANA (Nacer, crecer y alimentarse en la fe): Bautismo (nos hace hijos de Dios), Confirmación (nos da la plenitud del Espíritu Santo) y Eucaristía (el Cuerpo y la Sangre real de Cristo).",
                "2. SACRAMENTOS DE CURACIÓN (Sanar el alma y el cuerpo): Reconciliación o Confesión (perdona los pecados cometidos después del Bautismo y devuelve la paz) y Unción de los Enfermos (fortaleza y consuelo en la enfermedad grave o vejez).",
                "3. SACRAMENTOS DE SERVICIO A LA COMUNIDAD (Misión y vocación): Orden Sacerdotal (consagra a obispos, presbíteros y diáconos para servir al Pueblo de Dios) y Matrimonio (alianza santa de amor fiel entre un hombre y una mujer)."
            ),
            practicalTip = "Recuerda: el Bautismo, la Confirmación y el Orden Sacerdotal imprimen un «carácter sacramental» indeleble en el alma y solo se reciben una vez en la vida.",
            biblicalOrCatechismRef = "Catecismo de la Iglesia Católica, n. 1210-1211"
        ),
        CatholicEssentialItem(
            id = "guia_confesion_5_pasos",
            category = CatholicEssentialCategory.MASS_AND_CONFESSION,
            badge = "GUÍA PRÁCTICA",
            title = "Cómo Confesarse Bien en 5 Pasos (Sin Miedo)",
            subtitle = "El Sacramento de la Reconciliación explicado claro para jóvenes",
            summaryForYouth = "Confesarse no es ir a que te regañen: es ir al abrazo del Padre que te perdona y reinicia tu corazón limpio. Para hacer una buena confesión se necesitan 5 pasos:",
            bulletPoints = listOf(
                "Paso 1 • Examen de conciencia: Antes de entrar, repasa en silencio qué cosas te alejaron de Dios, de los demás o de ti mismo desde tu última confesión.",
                "Paso 2 • Dolor de los pecados (Contrición): Sentir sincero pesar en el corazón por haber fallado al amor de Dios.",
                "Paso 3 • Propósito de enmienda: Tomar la decisión firme y sincera de querer mejorar y evitar las ocasiones de pecado.",
                "Paso 4 • Decir los pecados al sacerdote: Al iniciar dices «Ave María Purísima / Bendígame Padre porque he pecado», indicas hace cuánto fue tu última confesión y dices tus pecados con claridad y sin callar ninguno por vergüenza.",
                "Paso 5 • Cumplir la penitencia: Al recibir la absolución («Yo te absuelvo de tus pecados en el nombre del Padre, y del Hijo, y del Espíritu Santo»), respondes «Amén» y cumples la oración u obra que te dejó el sacerdote."
            ),
            practicalTip = "El sacerdote está obligado por el «Sigilo Sacramental»: jamás, bajo ninguna circunstancia ni amenaza, puede revelar nada de lo que escuchó en confesión.",
            biblicalOrCatechismRef = "Juan 20, 22-23 • Catecismo n. 1450-1460"
        ),
        CatholicEssentialItem(
            id = "partes_de_la_santa_misa",
            category = CatholicEssentialCategory.MASS_AND_CONFESSION,
            badge = "LITURGIA VIVA",
            title = "Las 4 Partes de la Santa Misa (Para Vivirla al 100%)",
            subtitle = "Entiende qué pasa en cada momento del altar",
            summaryForYouth = "Cuando entiendes lo que ocurre en la Misa, deja de parecerte rutinaria: estás literalmente en el Calvario y en la Resurrección con Jesús vivo.",
            bulletPoints = listOf(
                "1. Ritos Iniciales: Nos reunimos como familia. Incluye la Procesión de entrada, Señal de la Cruz, Acto Penitencial («Yo confieso...» para pedir perdón), el himno del Gloria y la Oración Colecta.",
                "2. Liturgia de la Palabra: Dios nos habla hoy. Escuchamos la Primera Lectura (Antiguo Testamento o Hechos), el Salmo Responsorial, la Segunda Lectura (cartas de los Apóstoles), el Evangelio (de pie porque habla Cristo Rey), la Homilía, el Credo y la Oración de los Fieles.",
                "3. Liturgia Eucarística: El corazón de la Misa. Presentación de las ofrendas (pan y vino), Prefacio y canto del «Santo», la Consagración (donde por el poder del Espíritu Santo el pan y el vino se convierten realmente en el Cuerpo y la Sangre de Cristo), el Padre Nuestro, el saludo de la Paz, el Cordero de Dios y la Sagrada Comunión.",
                "4. Rito de Conclusión: El sacerdote nos da la Bendición final de la Santísima Trinidad y nos envía: «Pueden ir en paz» (llevar a Jesús a tu colegio, universidad, trabajo y familia)."
            ),
            practicalTip = "Para comulgar debes estar en estado de gracia (sin pecado mortal consciente; si lo hay, confiésate antes) y guardar 1 hora de ayuno eucarístico (agua y medicinas no rompen el ayuno).",
            biblicalOrCatechismRef = "Lucas 22, 19-20 • Catecismo n. 1346-1355"
        ),
        CatholicEssentialItem(
            id = "diez_mandamientos_jovenes",
            category = CatholicEssentialCategory.COMMANDMENTS,
            badge = "BRÚJULA MORAL",
            title = "Los 10 Mandamientos de la Ley de Dios",
            subtitle = "No son prohibiciones para quitarte libertad, sino señales para no estrellarte",
            summaryForYouth = "Jesús los resumió en dos: amar a Dios sobre todas las cosas y al prójimo como a ti mismo. Los primeros 3 hablan de tu relación con Dios y los otros 7 del amor y respeto al prójimo:",
            bulletPoints = listOf(
                "1. Amarás a Dios sobre todas las cosas (no poner el dinero, la fama, el celular o el ego como ídolos por encima de Dios).",
                "2. No tomarás el Nombre de Dios en vano (hablar de Dios y de lo sagrado con respeto y cumplir las promesas).",
                "3. Santificarás las fiestas (participar en la Misa dominical, descansar y dedicar tiempo a Dios y a la familia).",
                "4. Honrarás a tu padre y a tu madre (respetar, valorar, escuchar y cuidar a tus padres y mayores).",
                "5. No matarás (cuidar la vida humana desde la concepción hasta la muerte natural, rechazar el bullying, el odio, las drogas y la violencia).",
                "6. No cometerás actos impuros y 9. No consentirás pensamientos ni deseos impuros (vivir la sexualidad, el cuerpo y el afecto con respeto, pureza y verdadero amor, sin usar a las personas como objetos).",
                "7. No robarás y 10. No codiciarás los bienes ajenos (ser honesto en el estudio y trabajo, no hacer trampa y vencer la envidia).",
                "8. No darás falso testimonio ni mentirás (hablar con la verdad, no difamar en redes sociales ni destruir la reputación de nadie con chismes)."
            ),
            practicalTip = "Antes de publicar o compartir algo sobre alguien en redes, pásalo por el 8.º Mandamiento: ¿es verdad, hace bien y respeta su dignidad?",
            biblicalOrCatechismRef = "Éxodo 20, 1-17 • Mateo 22, 37-40"
        ),
        CatholicEssentialItem(
            id = "cinco_mandamientos_iglesia",
            category = CatholicEssentialCategory.COMMANDMENTS,
            badge = "VIDA CATÓLICA",
            title = "Los 5 Mandamientos de la Santa Madre Iglesia",
            subtitle = "El entrenamiento mínimo para mantener viva tu fe durante el año",
            summaryForYouth = "Así como un deportista tiene un plan mínimo de entrenamiento, la Iglesia propone 5 compromisos concretos para todo católico:",
            bulletPoints = listOf(
                "1.º Participar en la Misa entera todos los domingos y fiestas de guardar.",
                "2.º Confesar los pecados mortales al menos una vez al año (y siempre que se esté en peligro de muerte o antes de comulgar si no se está en gracia).",
                "3.º Comulgar al menos por Pascua de Resurrección (preparado en gracia de Dios).",
                "4.º Ayunar y abstenerse de comer carne cuando lo manda la Santa Madre Iglesia (como el Miércoles de Ceniza y el Viernes Santo).",
                "5.º Ayudar a la Iglesia en sus necesidades materiales y en sus obras de caridad y evangelización según las posibilidades de cada uno."
            ),
            practicalTip = "Las principales Fiestas de Guardar (además de cada domingo) incluyen Navidad (25 dic), Santa María Madre de Dios (1 ene), Inmaculada Concepción (8 dic) y las que indique tu conferencia episcopal.",
            biblicalOrCatechismRef = "Catecismo de la Iglesia Católica, n. 2041-2043"
        ),
        CatholicEssentialItem(
            id = "obras_de_misericordia_14",
            category = CatholicEssentialCategory.MERCY_AND_VIRTUES,
            badge = "FE EN ACCIÓN",
            title = "Las 14 Obras de Misericordia (7 Corporales + 7 Espirituales)",
            subtitle = "Cómo amar a Jesús en las personas que te rodean todos los días",
            summaryForYouth = "Jesús dijo: «Cada vez que lo hicieron con el más pequeño de mis hermanos, conmigo lo hicieron» (Mt 25, 40). Son 7 para el cuerpo y 7 para el alma:",
            bulletPoints = listOf(
                "7 CORPORALES: 1) Visitar a los enfermos • 2) Dar de comer al hambriento • 3) Dar de beber al sediento • 4) Dar posada al peregrino • 5) Vestir al desnudo • 6) Visitar a los presos • 7) Enterrar a los difuntos.",
                "7 ESPIRITUALES: 1) Enseñar al que no sabe • 2) Dar buen consejo al que lo necesita • 3) Corregir al que se equivoca con caridad • 4) Perdonar las injurias de corazón • 5) Consolar al triste (escuchar a ese amigo que pasa por depresión o soledad) • 6) Sufrir con paciencia los defectos del prójimo • 7) Rogar a Dios por los vivos y por los difuntos."
            ),
            practicalTip = "Esta semana elige 1 obra corporal (ej. compartir comida o ropa en buen estado) y 1 espiritual (ej. escribirle y escuchar a un compañero que esté triste o solo).",
            biblicalOrCatechismRef = "Mateo 25, 31-46 • Catecismo n. 2447"
        ),
        CatholicEssentialItem(
            id = "virtudes_y_frutos_espiritu",
            category = CatholicEssentialCategory.MERCY_AND_VIRTUES,
            badge = "CRECIMIENTO INTERIOR",
            title = "Las 7 Virtudes y los 12 Frutos del Espíritu Santo",
            subtitle = "Hábitos del corazón que forjan un carácter fuerte y libre",
            summaryForYouth = "Una virtud es una disposición habitual y firme para hacer el bien. Los católicos distinguimos las Virtudes Teologales, las Cardinales y los Frutos del Espíritu Santo:",
            bulletPoints = listOf(
                "3 VIRTUDES TEOLOGALES (nos conectan directamente con Dios): Fe (creer y confiar en Dios), Esperanza (anhelar el Reino de los Cielos y confiar en sus promesas) y Caridad (amar a Dios sobre todo y al prójimo por amor a Dios).",
                "4 VIRTUDES CARDINALES (ejes de una vida recta): Prudencia (discernir el verdadero bien en cada circunstancia), Justicia (dar a Dios y al prójimo lo que les corresponde), Fortaleza (firmeza ante las dificultades y tentaciones) y Templanza (dominio propio sobre los impulsos y placeres).",
                "12 FRUTOS DEL ESPÍRITU SANTO: Caridad, Gozo (Alegría), Paz, Paciencia, Longanimidad (Perseverancia), Bondad, Benignidad, Mansedumbre, Fidelidad, Modestia, Continencia y Castidad."
            ),
            practicalTip = "Cuando sientas ansiedad o falta de control, pide en oración la virtud de la Templanza y los frutos de Paz y Dominio propio.",
            biblicalOrCatechismRef = "Gálatas 5, 22-23 • Catecismo n. 1803-1832"
        ),
        CatholicEssentialItem(
            id = "dudas_frecuentes_catolicos_jovenes",
            category = CatholicEssentialCategory.YOUTH_APOLOGETICS,
            badge = "APOLOGÉTICA JOVEN",
            title = "5 Dudas que Todo Joven Católico Debe Saber Responder",
            subtitle = "Respuestas claras cuando te pregunten por tu fe en el colegio o universidad",
            summaryForYouth = "Muchos jóvenes dudan o no saben qué responder cuando les cuestionan su fe católica. Aquí tienes la verdad explicada de forma sencilla:",
            bulletPoints = listOf(
                "1. ¿Los católicos adoran a María o a los santos? → ¡NO! La adoración (latría) es exclusiva para Dios (Padre, Hijo y Espíritu Santo). A los santos y a la Virgen María los veneramos (los honramos como amigos de Dios y modelos de vida) y les pedimos que recen por nosotros, igual que le pides a un amigo que rece por ti.",
                "2. ¿Por qué los católicos tienen imágenes o cruces? → No adoramos estatuas ni madera; son recordatorios visibles de Jesús y de sus amigos en el Cielo, igual que llevas la foto de tu mamá o de alguien que amas en la billetera o en tu celular.",
                "3. ¿La ciencia y la fe católica están peleadas? → ¡Falso! La Iglesia fundó las primeras universidades. El padre de la genética moderna (Gregor Mendel) era monje agustino y el autor de la teoría del Big Bang (Georges Lemaître) era sacerdote católico. La ciencia explica el «cómo» funciona el universo; la fe revela el «Quién» y el «para qué».",
                "4. ¿Qué es el Santo Rosario? → Es un resumen del Evangelio donde meditas la vida de Jesús (Misterios Gozosos, Luminosos, Dolorosos y Gloriosos) tomado de la mano de su Madre María.",
                "5. ¿Qué es la Adoración al Santísimo Sacramento? → Es estar frente a Jesús realmente presente en la Hostia consagrada expuesta en la Custodia, hablándole de corazón a corazón como al mejor amigo."
            ),
            practicalTip = "Como decía San Pedro: «Estén siempre dispuestos a dar razón de su esperanza a todo el que se la pida, pero con mansedumbre y respeto» (1 Pe 3, 15).",
            biblicalOrCatechismRef = "1 Pedro 3, 15 • Catecismo n. 956, 2132"
        ),
        CatholicEssentialItem(
            id = "santos_jovenes_modelos_actuales",
            category = CatholicEssentialCategory.YOUTH_APOLOGETICS,
            badge = "HÉROES REALES",
            title = "Santos Jóvenes que Vivieron como Tú",
            subtitle = "La santidad no es para gente aburrida: es atreverse a brillar al máximo",
            summaryForYouth = "Estos jóvenes usaron tenis, hicieron deporte, programaron en computadora, tuvieron amigos y demostraron que se puede ser joven y profundamente de Dios hoy:",
            bulletPoints = listOf(
                "San Carlo Acutis (15 años, 1991-2006): Programador apasionado por la informática, los videojuegos y el fútbol. Llamaba a la Eucaristía «mi autopista hacia el Cielo» y dejó una frase inolvidable: «Todos nacen como originales, pero muchos mueren como fotocopias».",
                "Beato Pier Giorgio Frassati (24 años, 1901-1925): Alpinista, estudiante universitario alegre y servidor incansable de los pobres. Su lema para los jóvenes era «Verso l'alto» («Hacia lo alto»: no conformarse con una vida mediocre).",
                "Santa Teresita del Niño Jesús (24 años): Descubrió el «Caminito de la infancia espiritual»: hacer las cosas pequeñas de cada día con un amor extraordinario.",
                "Beata Chiara Luce Badano (18 años, 1971-1990): Joven deportista llena de luz que incluso en medio de la enfermedad repetía: «Si tú lo quieres, Jesús, yo también lo quiero»."
            ),
            practicalTip = "Pídele hoy a San Carlo Acutis que te enseñe a usar la tecnología, el estudio y tu juventud para dejar huella y nunca ser una fotocopia.",
            biblicalOrCatechismRef = "1 Timoteo 4, 12 («Que nadie menosprecie tu juventud»)"
        )
    )
}

@Composable
fun CatholicEssentialsSection(
    onSaveTopicToJournal: (CatholicEssentialItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf(CatholicEssentialCategory.ALL) }
    val filteredItems = remember(selectedCategory) {
        if (selectedCategory == CatholicEssentialCategory.ALL) {
            CatholicEssentialsRepository.items
        } else {
            CatholicEssentialsRepository.items.filter { it.category == selectedCategory }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("catholic_essentials_section"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Banner de encabezado de la sección
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.65f)
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("catholic_essentials_header_card")
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(38.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Church,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "GUÍA ESENCIAL KAIRÓS",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 0.8.sp
                        )
                        Text(
                            text = "Lo que todo Joven y Católico debe saber",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Además de profundizar en el Bautismo, la Eucaristía y la Confirmación, aquí tienes los pilares fundamentales de la fe católica explicados de forma clara, práctica y directa para jóvenes y católicos en general.",
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Chips de filtro por categoría
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CatholicEssentialCategory.entries.forEach { category ->
                        val isSelected = selectedCategory == category
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedCategory = category },
                            label = {
                                Text(
                                    text = "${category.emoji} ${category.label}",
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            modifier = Modifier.testTag("chip_essential_${category.name.lowercase()}")
                        )
                    }
                }
            }
        }

        // Tarjetas desplegables con todo el contenido esencial
        filteredItems.forEachIndexed { index, item ->
            CatholicEssentialExpandableCard(
                item = item,
                initiallyExpanded = index == 0,
                onSaveToJournal = { onSaveTopicToJournal(item) }
            )
        }
    }
}

@Composable
private fun CatholicEssentialExpandableCard(
    item: CatholicEssentialItem,
    initiallyExpanded: Boolean,
    onSaveToJournal: () -> Unit
) {
    var expanded by remember(item.id) { mutableStateOf(initiallyExpanded) }

    val categoryIcon: ImageVector = when (item.category) {
        CatholicEssentialCategory.SACRAMENTS_7 -> Icons.Default.AutoAwesome
        CatholicEssentialCategory.MASS_AND_CONFESSION -> Icons.Default.Church
        CatholicEssentialCategory.COMMANDMENTS -> Icons.Default.Gavel
        CatholicEssentialCategory.MERCY_AND_VIRTUES -> Icons.Default.Favorite
        CatholicEssentialCategory.YOUTH_APOLOGETICS -> Icons.Default.HelpOutline
        CatholicEssentialCategory.ALL -> Icons.Default.MenuBook
    }

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (expanded) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.45f)
            } else {
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable { expanded = !expanded }
            .testTag("essential_card_${item.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = categoryIcon,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = "${item.category.emoji} ${item.badge}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = item.subtitle,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Contraer" else "Expandir",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = item.summaryForYouth,
                        fontSize = 13.sp,
                        lineHeight = 19.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Lista de puntos clave
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        item.bulletPoints.forEach { bullet ->
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Verified,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = bullet,
                                        fontSize = 13.sp,
                                        lineHeight = 19.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Consejo práctico para vivirlo hoy
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.55f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.35f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "💡 CONSEJO PRÁCTICO PARA TU VIDA DIARIA",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.secondary,
                                letterSpacing = 0.6.sp
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = item.practicalTip,
                                fontSize = 12.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Referencia: ${item.biblicalOrCatechismRef}",
                                fontSize = 11.sp,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedButton(
                        onClick = onSaveToJournal,
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btn_save_essential_${item.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.BookmarkAdd,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Guardar este tema en mi Diario Espiritual",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
