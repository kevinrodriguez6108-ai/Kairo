package com.example.data.model

enum class FaithRankTier(
    val rankName: String,
    val spiritualTitle: String,
    val emblemName: String,
    val minChallenges: Int,
    val primaryColorHex: Long,
    val secondaryColorHex: Long,
    val badgeIconText: String,
    val description: String
) {
    BRONCE(
        rankName = "Bronce",
        spiritualTitle = "Buscador de Luz",
        emblemName = "Escudo del Buscador",
        minChallenges = 0,
        primaryColorHex = 0xFFCD7F32,
        secondaryColorHex = 0xFF8B4513,
        badgeIconText = "🛡️",
        description = "El inicio de tu aventura en Kairós. Completa tus primeras 5 preguntas para ascender a Plata."
    ),
    PLATA(
        rankName = "Plata",
        spiritualTitle = "Peregrino Fiel",
        emblemName = "Espadas del Peregrino",
        minChallenges = 5,
        primaryColorHex = 0xFF94A3B8,
        secondaryColorHex = 0xFF475569,
        badgeIconText = "⚔️",
        description = "Has dado tus primeros 5 pasos firmes conociendo la app y la fe. Tu constancia empieza a brillar."
    ),
    ORO(
        rankName = "Oro",
        spiritualTitle = "Discípulo Valiente",
        emblemName = "Corona del Discípulo",
        minChallenges = 10,
        primaryColorHex = 0xFFF59E0B,
        secondaryColorHex = 0xFFB45309,
        badgeIconText = "👑",
        description = "Dominas los Sacramentos y la autenticidad juvenil. 10 retos superados con valentía."
    ),
    PLATINO(
        rankName = "Platino",
        spiritualTitle = "Centinela de Cristo",
        emblemName = "Estrella Centinela",
        minChallenges = 15,
        primaryColorHex = 0xFF06B6D4,
        secondaryColorHex = 0xFF0E7490,
        badgeIconText = "💠",
        description = "Conoces cómo se unen fe y razón y sigues los pasos de Jesús. 15 retos conquistados."
    ),
    DIAMANTE(
        rankName = "Diamante",
        spiritualTitle = "Guerrero del Espíritu",
        emblemName = "Gema del Espíritu",
        minChallenges = 20,
        primaryColorHex = 0xFF3B82F6,
        secondaryColorHex = 0xFF1D4ED8,
        badgeIconText = "💎",
        description = "Forjado en la Palabra, el Diario y la Pausa consciente con Dios. 20 retos superados."
    ),
    ESMERALDA(
        rankName = "Esmeralda",
        spiritualTitle = "Sembrador de Esperanza",
        emblemName = "Reliquia Esmeralda",
        minChallenges = 25,
        primaryColorHex = 0xFF10B981,
        secondaryColorHex = 0xFF047857,
        badgeIconText = "❇️",
        description = "Caminas con Jesús hacia Jerusalén con alegría y criterio juvenil. 25 retos superados."
    ),
    ZAFIRO(
        rankName = "Zafiro",
        spiritualTitle = "Guardián de la Verdad",
        emblemName = "Sello de Zafiro",
        minChallenges = 30,
        primaryColorHex = 0xFF6366F1,
        secondaryColorHex = 0xFF4338CA,
        badgeIconText = "🔷",
        description = "Acompañas a Jesús en la Última Cena con humildad y amor fraterno. 30 retos superados."
    ),
    RUBI(
        rankName = "Rubí",
        spiritualTitle = "Corazón de Fuego",
        emblemName = "Fuego de Rubí",
        minChallenges = 35,
        primaryColorHex = 0xFFF43F5E,
        secondaryColorHex = 0xFFBE123C,
        badgeIconText = "❤️‍🔥",
        description = "Velas con Cristo en Getsemaní y vives la fortaleza de Filipenses 4:13. 35 retos superados."
    ),
    HEROICO(
        rankName = "Heroico",
        spiritualTitle = "Apóstol del Calvario",
        emblemName = "Llama Heroica",
        minChallenges = 40,
        primaryColorHex = 0xFFEF4444,
        secondaryColorHex = 0xFFB91C1C,
        badgeIconText = "🔥",
        description = "Fiel en el Vía Crucis junto a Jesús hasta el Gólgota. 40 retos de alta exigencia."
    ),
    MAESTRO(
        rankName = "Maestro",
        spiritualTitle = "Portador de Gracia",
        emblemName = "Astro de Maestro",
        minChallenges = 45,
        primaryColorHex = 0xFFA855F7,
        secondaryColorHex = 0xFF6B21A8,
        badgeIconText = "🌟",
        description = "Contemplas las Siete Palabras de Jesús en la Cruz y vives el perdón heroico. 45 retos."
    ),
    GRAN_MAESTRO(
        rankName = "Gran Maestro",
        spiritualTitle = "Sabio del Evangelio",
        emblemName = "Cáliz Gran Maestro",
        minChallenges = 50,
        primaryColorHex = 0xFFEAB308,
        secondaryColorHex = 0xFFCA8A04,
        badgeIconText = "🏆",
        description = "Has llegado desde la Cruz hasta la luz de la Resurrección dominando toda la app. 50 retos."
    ),
    LEYENDA_KAIROS(
        rankName = "Leyenda Kairós",
        spiritualTitle = "Leyenda Celestial Suprema",
        emblemName = "Alas de Leyenda Kairós",
        minChallenges = 55,
        primaryColorHex = 0xFFEC4899,
        secondaryColorHex = 0xFFBE185D,
        badgeIconText = "🦅",
        description = "¡La cima máxima! Has superado las 55 preguntas de la app, los retos juveniles y la historia de Jesús."
    );

    companion object {
        const val CHALLENGES_PER_RANK = 5
        const val MAX_DAILY_QUESTIONS = 5
        const val TOTAL_CHALLENGES = 55

        fun fromCompletedChallenges(completedCount: Int): FaithRankTier {
            val safeCount = completedCount.coerceAtLeast(0)
            return entries.lastOrNull { safeCount >= it.minChallenges } ?: BRONCE
        }

        fun nextRank(current: FaithRankTier): FaithRankTier? {
            val nextIndex = current.ordinal + 1
            return entries.getOrNull(nextIndex)
        }

        fun progressInCurrentRank(completedCount: Int): Int {
            val safeCount = completedCount.coerceAtLeast(0)
            if (safeCount >= entries.last().minChallenges) return CHALLENGES_PER_RANK
            return safeCount % CHALLENGES_PER_RANK
        }

        fun challengesRemainingForNextRank(completedCount: Int): Int {
            val safeCount = completedCount.coerceAtLeast(0)
            if (safeCount >= entries.last().minChallenges) return 0
            return CHALLENGES_PER_RANK - (safeCount % CHALLENGES_PER_RANK)
        }
    }
}

data class FaithEmblem(
    val id: String,
    val title: String,
    val subtitle: String,
    val iconEmoji: String,
    val requiredChallenges: Int,
    val colorHex: Long,
    val description: String
)

enum class ChallengeCategory(
    val label: String,
    val badgeEmoji: String,
    val colorHex: Long
) {
    CONTENIDO_APP("De la App Kairós", "📱", 0xFF3B82F6),
    HISTORIA_DE_JESUS("Historia de Jesús", "✝️", 0xFFEF4444),
    JOVENES_DIVERTIDA("Jóvenes • Divertida y Razonable", "😎", 0xFF10B981)
}

enum class ChallengeNodeType(val label: String) {
    QUIZ_QUESTION("Pregunta de Fe"),
    DAILY_ACTION("Reto Joven Razonable"),
    BOSS_RANK_UP("Duelo de Ascenso")
}

enum class ChallengeDifficulty(val label: String, val xpReward: Int) {
    BASICO("Nivel Básico", 25),
    INTERMEDIO("Nivel Intermedio", 40),
    DIFICIL("Nivel Difícil", 65),
    EXPERTO("Nivel Experto", 95),
    LEYENDA("Nivel Leyenda", 150)
}

data class ChallengeNode(
    val id: Int,
    val title: String,
    val subtitle: String,
    val type: ChallengeNodeType,
    val rankTier: FaithRankTier,
    val difficulty: ChallengeDifficulty,
    val promptOrQuestion: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String,
    val biblicalReference: String,
    val dailyMissionCommitment: String? = null,
    val category: ChallengeCategory = ChallengeCategory.CONTENIDO_APP
)
