package com.example.data.model

data class HolySpiritGift(
    val name: String,
    val biblicalContext: String,
    val youthTranslation: String,
    val dailyExample: String,
    val iconName: String
)

data class SacramentKeyPoint(
    val title: String,
    val subtitle: String,
    val description: String,
    val iconType: String
)

data class RealLifeStory(
    val title: String,
    val youthName: String,
    val ageOrContext: String,
    val dilemma: String,
    val transformation: String,
    val takeawayQuote: String
)

enum class SacramentType(
    val title: String,
    val subtitle: String,
    val slogan: String,
    val modernAnalogyTitle: String,
    val modernAnalogyText: String,
    val deepMeaning: String,
    val modernRelevance: String,
    val keyPoints: List<SacramentKeyPoint>,
    val gifts: List<HolySpiritGift> = emptyList(),
    val story: RealLifeStory,
    val reflectionPrompts: List<String>,
    val activatingPrayer: String
) {
    BAUTISMO(
        title = "El Bautismo",
        subtitle = "Puerta de Entrada y Reset de Identidad",
        slogan = "Hijo/a amado/a incondicionalmente: tu valor es innegociable.",
        modernAnalogyTitle = "El Pasaporte Divino y la Actualización Definitiva",
        modernAnalogyText = "Imagina estrenar un smartphone: viene limpio de virus y con las mejores configuraciones de fábrica. El Bautismo es el 'reset' definitivo de Dios en tu vida. Te borra toda herida de origen y te instala el chip de hijo o hija amada de Dios para siempre. No es un mero trámite de tus padres ni una fiesta de vestidos blancos; es tu pasaporte permanente de ciudadanía celestial. Ningún error futuro, ningún 'unfollow' ni rechazo humano puede revocar esa identidad grabada a fuego en tu alma.",
        deepMeaning = "El Bautismo nos sumerge en la Pascua de Jesús: morir a la soledad, el egoísmo y la desesperanza, para renacer a una vida luminosa. Nos hace templos vivos del Espíritu Santo y miembros de una familia global (la Iglesia) que no te juzga por tu currículum, sino que te abraza por quien eres.",
        modernRelevance = "En un mundo saturado de métricas, 'likes' y comparaciones en redes sociales, el Bautismo es el antídoto contra el síndrome del impostor: antes de que logres cualquier meta o cometas cualquier fallo, Dios ya te miró y dijo: 'Tú eres mi hijo amado, en ti tengo mi complacencia'. Tu dignidad no está en venta.",
        keyPoints = listOf(
            SacramentKeyPoint(
                title = "Identidad Inquebrantable",
                subtitle = "Sello indeleble",
                description = "Llevas una marca espiritual eterna. Eres de Dios, pase lo que pase.",
                iconType = "badge"
            ),
            SacramentKeyPoint(
                title = "Borrón y Cuenta Nueva",
                subtitle = "Gracia purificadora",
                description = "El agua limpia el pasado y te regala la libertad de empezar de cero cada mañana.",
                iconType = "water"
            ),
            SacramentKeyPoint(
                title = "Comunidad Global",
                subtitle = "Nunca caminas solo",
                description = "Formas parte de millones de jóvenes en todo el planeta con el mismo llamado a amar.",
                iconType = "people"
            ),
            SacramentKeyPoint(
                title = "Luz en la Oscuridad",
                subtitle = "La vela bautismal",
                description = "Estás llamado a encender esperanza en tus amigos, tu salón de clases y tu familia.",
                iconType = "light"
            )
        ),
        story = RealLifeStory(
            title = "Mateo y el final de la comparación",
            youthName = "Mateo",
            ageOrContext = "19 años, estudiante universitario",
            dilemma = "Vivía obsesionado con la validación en Instagram y sentía un vacío demoledor cuando sus publicaciones no tenían interacción o cuando desaprobaba una materia.",
            transformation = "Durante un retiro, el sacerdote le pidió que tocara el agua bendita y recordara la fecha de su bautismo: 'Tu valor fue fijado en la Cruz y en la fuente bautismal, no en el algoritmo'. Esa verdad desmontó su ansiedad social y le dio paz para ser auténtico.",
            takeawayQuote = "Descubrí que ya soy amado infinitamente. Ya no tengo que actuar para agradar al mundo."
        ),
        reflectionPrompts = listOf(
            "¿En qué momentos sientes que necesitas demostrar tu valor a los demás?",
            "Si supieras con certeza total que Dios te ama sin condiciones hoy, ¿qué miedo te atreverías a soltar?",
            "¿Cómo puedes ser agua limpia y luz para alguien que la está pasando mal esta semana?"
        ),
        activatingPrayer = "Señor Jesús, gracias por el regalo inmenso de mi Bautismo. Cuando el mundo me diga que no soy suficiente, recuérdame que llevo tu nombre y que soy tu hijo amado. Renueva hoy en mí el fuego de tu Espíritu para vivir con valentía, pureza y alegría auténtica. Amén."
    ),

    CONFIRMACION(
        title = "La Confirmación",
        subtitle = "Madurez Espiritual y los 7 Dones",
        slogan = "De espectador a protagonista: el Espíritu Santo te viste de poder.",
        modernAnalogyTitle = "El Botón de Activación y la Batería Pro",
        modernAnalogyText = "Muchos piensan equivocadamente que la Confirmación es la 'graduación de la Iglesia' (hacerla y no volver más). ¡Es exactamente lo opuesto! Es pasar del banquillo de suplentes a ser el jugador titular en el partido más importante. Es como cuando a un auto le activas el motor turbo, o como recibir una armadura de alta tecnología: el Espíritu Santo desciende con sus 7 dones para que no te quiebres ante la presión social, sino que seas líder de luz donde haya oscuridad.",
        deepMeaning = "La Confirmación sella y perfecciona la gracia del Bautismo. Mediante la imposición de manos y el Santo Crisma, recibes el mismo Pentecostés que transformó a unos apóstoles asustados y encerrados en hombres valientes capaces de transformar el mundo entero.",
        modernRelevance = "Los jóvenes de hoy enfrentan presiones brutales: ansiedad por el futuro, soledad conectada, adicciones silenciosas y dilemas éticos diarios. La Confirmación no es una clase teórica: es el GPS interior que te ayuda a tomar decisiones sabias y la fuerza de voluntad para ser fiel a tus ideales.",
        keyPoints = listOf(
            SacramentKeyPoint(
                title = "Pentecostés Personal",
                subtitle = "Fuego que renueva",
                description = "El Espíritu Santo habita en ti con fuerza real, transformando tus miedos en audacia.",
                iconType = "fire"
            ),
            SacramentKeyPoint(
                title = "Santo Crisma",
                subtitle = "Ungido para una misión",
                description = "El aceite perfumado significa que dejas un rastro de bondad y paz dondequiera que vas.",
                iconType = "shield"
            ),
            SacramentKeyPoint(
                title = "Voz Profética Juvenil",
                subtitle = "Valentía moral",
                description = "Capacidad para alzar la voz por los vulnerables y defender la verdad con cariño.",
                iconType = "speaker"
            ),
            SacramentKeyPoint(
                title = "Misión en el Mundo",
                subtitle = "Fe en acción",
                description = "No es quedarse encerrado en un templo, sino transformar tu universidad, trabajo y redes.",
                iconType = "rocket"
            )
        ),
        gifts = listOf(
            HolySpiritGift(
                name = "Sabiduría",
                biblicalContext = "Ver la realidad desde la perspectiva de Dios",
                youthTranslation = "Filtro anti-superficialidad: no tragarte las mentiras que te venden la felicidad como consumo rápido.",
                dailyExample = "Elegir la paz interior y los valores duraderos sobre la euforia pasajera de una noche.",
                iconName = "eye"
            ),
            HolySpiritGift(
                name = "Entendimiento",
                biblicalContext = "Comprender a fondo la verdad y el corazón humano",
                youthTranslation = "Empatía nivel experto: conectar con el dolor de los demás y entender el sentido de tu vida.",
                dailyExample = "Escuchar a un amigo que todos juzgan y notar lo que realmente está sufriendo por dentro.",
                iconName = "heart"
            ),
            HolySpiritGift(
                name = "Consejo",
                biblicalContext = "Discernimiento y guía en las encrucijadas",
                youthTranslation = "Tu GPS moral: saber qué camino tomar cuando tus emociones o tus amigos te jalan a otro lado.",
                dailyExample = "Saber retirarte a tiempo de un ambiente tóxico y aconsejar con ternura a quien está perdido.",
                iconName = "compass"
            ),
            HolySpiritGift(
                name = "Fortaleza",
                biblicalContext = "Fuerza sobrenatural para perseverar ante la prueba",
                youthTranslation = "Resiliencia de acero: el poder de decir 'no' a la presión de grupo sin avergonzarte.",
                dailyExample = "Mantenerte leal a tus principios cuando todos se burlan o toman el camino fácil.",
                iconName = "arm"
            ),
            HolySpiritGift(
                name = "Ciencia",
                biblicalContext = "Descubrir la huella del Creador en la creación y el estudio",
                youthTranslation = "Curiosidad iluminada: ver a Dios en el cosmos, la biología, la tecnología y el arte.",
                dailyExample = "Estudiar con pasión sabiendo que tu carrera profesional es tu forma de mejorar el mundo.",
                iconName = "planet"
            ),
            HolySpiritGift(
                name = "Piedad",
                biblicalContext = "Relación filial de ternura y confianza con el Padre",
                youthTranslation = "Amistad real con Dios: hablarle con naturalidad, como le hablas a tu mejor amigo, sin poses.",
                dailyExample = "Rezar de noche con tus propias palabras, compartiendo tus alegrías, dudas y frustraciones.",
                iconName = "hands"
            ),
            HolySpiritGift(
                name = "Temor de Dios",
                biblicalContext = "Asombro reverente y respeto profundo a su Amor",
                youthTranslation = "Cero miedo al castigo; es el miedo sano a lastimar a quien te ama con locura.",
                dailyExample = "Cuidar tu cuerpo, tu mente y tus relaciones con la delicadeza que merece lo sagrado.",
                iconName = "sparkle"
            )
        ),
        story = RealLifeStory(
            title = "Sofía y la valentía en la fiesta",
            youthName = "Sofía",
            ageOrContext = "17 años, preparatoria",
            dilemma = "En una fiesta, un grupo comenzó a viralizar fotos denigrantes de una compañera ausente. Todos se reían por miedo a ser excluidos del grupo popular.",
            transformation = "Sofía sintió un impulso de Fortaleza y Consejo: se levantó, dijo firmemente que eso era cruel e inaceptable, y apoyó a la chica al día siguiente. Su postura inspiró a otros tres a borrar las fotos.",
            takeawayQuote = "El Espíritu Santo no te hace invisible; te da el coraje para ser la persona que otros necesitan ver."
        ),
        reflectionPrompts = listOf(
            "¿Cuál de los 7 dones del Espíritu Santo necesitas activar con más urgencia hoy?",
            "¿Te cuesta manifestar tus convicciones cuando tus amigos van en otra dirección? ¿Por qué?",
            "¿Qué causa o injusticia en tu entorno crees que Dios te llama a combatir?"
        ),
        activatingPrayer = "Ven, Espíritu Santo, llena los corazones de tus fieles y enciende en ellos el fuego de tu amor. Sopla con fuerza sobre mis miedos, dame tu don de Fortaleza para vencer la cobardía, y tu don de Sabiduría para elegir el bien. Hazme testigo valiente y alegre en medio de mis amigos. Amén."
    ),

    EUCARISTIA(
        title = "La Primera Comunión (Eucaristía)",
        subtitle = "El Superalimento y el Encuentro Real",
        slogan = "No es un símbolo ni un recuerdo: es Jesús vivo abrazándote desde dentro.",
        modernAnalogyTitle = "El Superalimento del Alma y Conexión Directa",
        modernAnalogyText = "Si tu mejor amigo se va a vivir al otro lado del mundo, te deja fotos o mensajes. Pero si pudiera dejarte su presencia real y un abrazo que te llene de energía, lo haría sin dudar. Eso hizo Jesús en la última cena: sabiendo que nos cansaríamos en el camino de la vida, no nos dejó un manual ni un monumento; se hizo Pan para entrar en nuestro interior. Es el superalimento de alta densidad espiritual que recarga tu batería cuando el estrés, la soledad o el cansancio te agotan.",
        deepMeaning = "La Eucaristía es fuente y cumbre de toda la vida cristiana. Cada Misa no es una repetición del Calvario, sino la actualización del sacrificio de amor donde el cielo y la tierra se abrazan. Al comulgar, no transformas a Cristo en ti: ¡Él te transforma a ti a su semejanza!",
        modernRelevance = "Vivimos en la era de los contactos superficiales y la comida rápida emocional. La Eucaristía ofrece intimidad genuina: 15 minutos de silencio en la Misa o frente al Sagrario ordenan tu mente más que horas de scroll en el teléfono. Es el antídoto contra el aislamiento.",
        keyPoints = listOf(
            SacramentKeyPoint(
                title = "Presencia Real",
                subtitle = "Cuerpo, Sangre, Alma y Divinidad",
                description = "Jesús no está en modo metáfora: está vivo, latiendo y disponible para ti en cada sagrario.",
                iconType = "heart"
            ),
            SacramentKeyPoint(
                title = "Sanación Interior",
                subtitle = "Medicina para los cansados",
                description = "El Papa Francisco dice: 'La Eucaristía no es premio para los perfectos, sino medicina para los débiles'.",
                iconType = "heal"
            ),
            SacramentKeyPoint(
                title = "Fuerza para Perdonar",
                subtitle = "Amor sacrificial",
                description = "Comulgar te da la capacidad de perdonar a quien te lastimó, cuando tus fuerzas no alcanzan.",
                iconType = "handshake"
            ),
            SacramentKeyPoint(
                title = "Fraternidad Viva",
                subtitle = "Un solo cuerpo",
                description = "Nos une con los pobres y necesitados, impulsándonos a ser pan partido para los demás.",
                iconType = "bread"
            )
        ),
        story = RealLifeStory(
            title = "Lucas y la semana de exámenes finales",
            youthName = "Lucas",
            ageOrContext = "20 años, estudiante de arquitectura",
            dilemma = "Agotado por entregas nocturnas, una ruptura sentimental reciente y un ataque de ansiedad, sentía que no podía continuar.",
            transformation = "Entró a una capilla universitaria a mediodía, comulgó en la Misa de las 12 y se quedó 15 minutos en silencio. Sintió una calidez y serenidad que no experimentaba en meses: 'No cambiaron mis entregas, pero yo ya no estaba solo para enfrentarlas'.",
            takeawayQuote = "Comulgar es mirarle a los ojos a quien nunca te abandona y decirle: 'Señor, no puedo solo, llévame Tú'."
        ),
        reflectionPrompts = listOf(
            "¿Cómo vives actualmente la Misa de domingo? ¿Qué te ayuda o te distrae?",
            "Si la Eucaristía es el abrazo real de Jesús, ¿qué herida o preocupación necesitas entregarle en tu próxima comunión?",
            "¿Cómo puedes ser 'pan bueno' y refugio para un amigo que se siente solo o excluido?"
        ),
        activatingPrayer = "Señor Jesús, presente real en el Santísimo Sacramento del Altar: gracias por no dejarme huérfano y quedarte como alimento humilde en la Eucaristía. Ven a mi corazón, sana mis angustias, limpia mis egoísmos y dame tu fuerza para amar con generosidad. Haz de mi vida una ofrenda de paz y servicio. Amén."
    );

    val isConfirmation: Boolean get() = this == CONFIRMACION
}
