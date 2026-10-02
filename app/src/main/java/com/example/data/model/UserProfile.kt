package com.example.data.model

enum class AppLaunchStage {
    CINEMATIC,
    LOGIN,
    MAIN_APP
}

enum class GenderIdentity(
    val label: String,
    val subtitle: String,
    val greetingPrefix: String
) {
    JOVEN(
        label = "Varon",
        subtitle = "Hijo de Dios • Hermano en la fe",
        greetingPrefix = "Bienvenido"
    ),
    SENORITA(
        label = "Mujer",
        subtitle = "Hija de Dios • Hermana en la fe",
        greetingPrefix = "Bienvenida"
    );

    companion object {
        fun fromStoredString(value: String?): GenderIdentity? {
            if (value.isNullOrBlank()) return null
            val normalized = value.trim()
            return entries.firstOrNull {
                it.name.equals(normalized, ignoreCase = true) ||
                    it.label.equals(normalized, ignoreCase = true)
            } ?: when (normalized.uppercase()) {
                "VARON", "VARÓN", "JOVEN", "HOMBRE", "MASCULINO" -> JOVEN
                "MUJER", "SENORITA", "SEÑORITA", "FEMENINO" -> SENORITA
                else -> null
            }
        }
    }
}

enum class AgeCategory(
    val label: String,
    val subtitle: String,
    val description: String
) {
    MAYOR(
        label = "Mayor",
        subtitle = "Mayor de edad (18+ años)",
        description = "Etapa universitaria, laboral o vocacional"
    ),
    MENOR(
        label = "Menor",
        subtitle = "Menor de edad (< 18 años)",
        description = "Etapa escolar, adolescencia y confirmación"
    );

    companion object {
        fun fromStoredString(value: String?): AgeCategory? {
            if (value.isNullOrBlank()) return null
            val normalized = value.trim()
            return entries.firstOrNull {
                it.name.equals(normalized, ignoreCase = true) ||
                    it.label.equals(normalized, ignoreCase = true)
            }
        }
    }
}

data class UserProfile(
    val fullName: String,
    val gender: GenderIdentity,
    val ageCategory: AgeCategory,
    val photoUri: String? = null
) {
    val firstName: String
        get() = fullName.trim().split("\\s+".toRegex()).firstOrNull().orEmpty().ifBlank { fullName }

    val personalizedGreeting: String
        get() = "¡${gender.greetingPrefix}, $firstName!"

    val badgeSummary: String
        get() = "${gender.label} • ${ageCategory.label}"

    val hasPhoto: Boolean
        get() = !photoUri.isNullOrBlank()

    val isCreatorAccount: Boolean
        get() = isCreatorName(fullName)

    companion object {
        fun normalizeUsername(name: String?): String {
            if (name.isNullOrBlank()) return ""
            return name.trim()
                .lowercase()
                .replace(Regex("\\s+"), " ")
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ü", "u")
        }

        fun isCreatorName(name: String?): Boolean {
            val normalized = normalizeUsername(name)
            if (normalized.isBlank()) return false
            return normalized == "kevin abraham" ||
                normalized == "kevin abraham rodriguez" ||
                normalized.startsWith("kevin abraham ")
        }

        fun isSameUsername(nameA: String?, nameB: String?): Boolean {
            val normA = normalizeUsername(nameA)
            val normB = normalizeUsername(nameB)
            if (normA.isBlank() || normB.isBlank()) return false
            if (isCreatorName(normA) && isCreatorName(normB)) return true
            return normA == normB
        }
    }
}

data class CreatorMessage(
    val id: Long,
    val senderName: String,
    val senderBadge: String,
    val messageText: String,
    val formattedDate: String,
    val creatorReplyText: String? = null,
    val creatorReplyDate: String? = null,
    val replyFirstSeenAtMillis: Long? = null
) {
    val hasCreatorReply: Boolean
        get() = !creatorReplyText.isNullOrBlank()

    fun isExpiredAfterViewing(nowMillis: Long = System.currentTimeMillis()): Boolean {
        val seenAt = replyFirstSeenAtMillis ?: return false
        return hasCreatorReply && (nowMillis - seenAt) >= REPLY_EXPIRATION_DURATION_MS
    }

    fun remainingHoursUntilDeletion(nowMillis: Long = System.currentTimeMillis()): Int {
        val seenAt = replyFirstSeenAtMillis ?: return 24
        val remainingMs = (REPLY_EXPIRATION_DURATION_MS - (nowMillis - seenAt)).coerceAtLeast(0L)
        return ((remainingMs + 3_599_999L) / 3_600_000L).toInt()
    }

    companion object {
        const val REPLY_EXPIRATION_DURATION_MS: Long = 24L * 60L * 60L * 1000L
    }
}



