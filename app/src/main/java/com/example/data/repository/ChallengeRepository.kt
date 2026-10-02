package com.example.data.repository

import com.example.data.model.ChallengeCategory
import com.example.data.model.ChallengeDifficulty
import com.example.data.model.ChallengeNode
import com.example.data.model.ChallengeNodeType
import com.example.data.model.FaithEmblem
import com.example.data.model.FaithRankTier

object ChallengeRepository {

    val specialEmblems: List<FaithEmblem> = listOf(
        FaithEmblem(
            id = "emblem_explorador_app",
            title = "Explorador Kairós",
            subtitle = "Dominas el inicio de la App",
            iconEmoji = "📱",
            requiredChallenges = 5,
            colorHex = 0xFF3B82F6,
            description = "Conoces la Biblia de 73 libros, el Bautismo y la Primera Comunión dentro de la app."
        ),
        FaithEmblem(
            id = "emblem_bateria_pro",
            title = "Batería Pro del Espíritu",
            subtitle = "Confirmación y Originalidad",
            iconEmoji = "⚡",
            requiredChallenges = 10,
            colorHex = 0xFFF59E0B,
            description = "Superaste las preguntas sobre la Confirmación, las fiestas sanas y San Carlo Acutis."
        ),
        FaithEmblem(
            id = "emblem_mente_y_fe",
            title = "Fe & Ciencia (Lemaître)",
            subtitle = "Dilemas y Razón Juvenil",
            iconEmoji = "🔭",
            requiredChallenges = 15,
            colorHex = 0xFF06B6D4,
            description = "Sabes cómo se unen la ciencia y la fe, el primer milagro de Jesús en Caná y la valentía de Sofía."
        ),
        FaithEmblem(
            id = "emblem_guardian_canon",
            title = "Guardián de los 73 Libros",
            subtitle = "Biblia, Pausa y Diario",
            iconEmoji = "📖",
            requiredChallenges = 20,
            colorHex = 0xFF8B5CF6,
            description = "Dominas los 7 libros Deuterocanónicos, el Diario Espiritual y la Pausa de 2 minutos."
        ),
        FaithEmblem(
            id = "emblem_chispa_juvenil",
            title = "Joven con Chispa y Criterio",
            subtitle = "Divertido y Razonable",
            iconEmoji = "😎",
            requiredChallenges = 25,
            colorHex = 0xFF10B981,
            description = "Respondiste con humor e inteligencia los retos juveniles, los milagros de Jesús y el Domingo de Ramos."
        ),
        FaithEmblem(
            id = "emblem_cenaculo",
            title = "Discípulo del Cenáculo",
            subtitle = "La Última Cena de Jesús",
            iconEmoji = "🍞",
            requiredChallenges = 30,
            colorHex = 0xFF6366F1,
            description = "Acompañaste a Jesús en el lavatorio de los pies, la institución de la Eucaristía y el Mandamiento Nuevo."
        ),
        FaithEmblem(
            id = "emblem_getsemani",
            title = "Centinela de Getsemaní",
            subtitle = "Oración en la Prueba",
            iconEmoji = "🌿",
            requiredChallenges = 35,
            colorHex = 0xFFF43F5E,
            description = "Conoces la entrega de Jesús en el Huerto de los Olivos, el arrepentimiento de Pedro y Filipenses 4:13."
        ),
        FaithEmblem(
            id = "emblem_via_crucis",
            title = "Cirineo del Camino",
            subtitle = "El Vía Crucis hacia el Gólgota",
            iconEmoji = "🪵",
            requiredChallenges = 40,
            colorHex = 0xFFEF4444,
            description = "Recorriste el juicio ante Pilato, la corona de espinas y el camino al Calvario junto a Simón de Cirene."
        ),
        FaithEmblem(
            id = "emblem_cruz_calvario",
            title = "Testigo del Gólgota",
            subtitle = "El Último Día en que lo Crucificaron",
            iconEmoji = "✝️",
            requiredChallenges = 45,
            colorHex = 0xFFA855F7,
            description = "Meditaste las palabras de Jesús en la Cruz: el perdón, el Buen Ladrón, María y «¡Todo está cumplido!»."
        ),
        FaithEmblem(
            id = "emblem_corona_suprema",
            title = "Corona Suprema Sin Copia",
            subtitle = "55 Retos Conquistados",
            iconEmoji = "👑",
            requiredChallenges = 55,
            colorHex = 0xFFEC4899,
            description = "¡Máximo honor! Superaste las 55 preguntas de la app, la historia de Jesús y los retos juveniles sin copiar."
        )
    )

    val challenges: List<ChallengeNode> = listOf(
        // ==================== ETAPA 1: BRONCE -> PLATA (Retos 1 al 5) ====================
        ChallengeNode(
            id = 1,
            title = "El Canon de 73 Libros en la App",
            subtitle = "Reto 1 • Basado en la App: Biblia",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.BRONCE,
            difficulty = ChallengeDifficulty.BASICO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En la sección «Biblia Católica» de esta app puedes explorar todos los libros sagrados. ¿Cuántos libros conforman en total la Biblia Católica que aparece en Kairós?",
            options = listOf(
                "66 libros en total",
                "73 libros (46 del Antiguo Testamento, incluyendo los 7 Deuterocanónicos, y 27 del Nuevo Testamento)",
                "50 libros exactos",
                "82 libros antiguos"
            ),
            correctOptionIndex = 1,
            explanation = "¡Exacto! En la pestaña «73 Libros» de la app encuentras los 46 libros del Antiguo Testamento (con los 7 deuterocanónicos) y los 27 del Nuevo Testamento.",
            biblicalReference = "App Kairós • Biblia (2 Timoteo 3:16)"
        ),
        ChallengeNode(
            id = 2,
            title = "El «Modo Avión» al Despertar",
            subtitle = "Reto 2 • Jóvenes: Divertida y Razonable",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.BRONCE,
            difficulty = ChallengeDifficulty.BASICO,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "Suena tu alarma a las 7:00 AM y tu pulgar va en automático a abrir TikTok o WhatsApp con un ojo cerrado. Según el consejo del «Micro-minuto» en la sección Dilemas de la app, ¿qué es lo más razonable antes de que el algoritmo secuestre tu cerebro?",
            options = listOf(
                "Darle primero un «Micro-minuto» a Dios diciendo: «Jesús, te entrego mi día» y agradecer 3 cosas antes de mirar notificaciones",
                "Ver 45 minutos de videos de gatitos y rezar solo si se va el internet",
                "No levantarse nunca de la cama",
                "Enojarse con el despertador y no hablar con nadie"
            ),
            correctOptionIndex = 0,
            explanation = "En el Dilema sobre la oración de nuestra app se explica la técnica del «Micro-minuto»: entregarle tu primer pensamiento a Jesús ordena tu mente antes del ruido digital.",
            biblicalReference = "App Kairós • Dilemas (Salmo 5:4)",
            dailyMissionCommitment = "Hoy haré mi «Micro-minuto» con Jesús antes de revisar redes sociales."
        ),
        ChallengeNode(
            id = 3,
            title = "El Bautismo y el Algoritmo de Mateo",
            subtitle = "Reto 3 • Basado en la App: Sacramentos",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.BRONCE,
            difficulty = ChallengeDifficulty.BASICO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En el apartado «Sacramentos -> El Bautismo» de la app leemos la historia de Mateo (19 años), quien sufría ansiedad cuando sus fotos en Instagram tenían pocos likes. ¿Qué le enseñó el sacerdote al tocar el agua bendita?",
            options = listOf(
                "Que debía comprar seguidores para sentirse mejor",
                "«Tu valor fue fijado en la Cruz y en la fuente bautismal, no en el algoritmo»: eres hijo amado de Dios con un sello imborrable",
                "Que cerrara todas sus amistades y se fuera a una cueva",
                "Que el Bautismo es solo una fiesta familiar para tomar fotos"
            ),
            correctOptionIndex = 1,
            explanation = "Como dice la analogía del «Pasaporte Divino» en la app: el Bautismo instala en tu alma la identidad innegociable de hijo amado de Dios.",
            biblicalReference = "App Kairós • Bautismo (Marcos 1:11)"
        ),
        ChallengeNode(
            id = 4,
            title = "De Nazaret a Belén: Nace Jesús",
            subtitle = "Reto 4 • Historia de Jesús (Inicio)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.BRONCE,
            difficulty = ChallengeDifficulty.BASICO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Comenzamos la historia de Jesús: tras el «Sí» de la Virgen María al Arcángel Gabriel en Nazaret (la Anunciación), José y María viajaron por el censo. ¿Dónde nació Jesús y por qué fue recostado en un pesebre?",
            options = listOf(
                "En un palacio de Roma rodeado de soldados",
                "En Belén de Judea, y fue recostado en un pesebre porque no había lugar para ellos en la posada",
                "En Egipto junto a las pirámides",
                "En Atenas entre filósofos griegos"
            ),
            correctOptionIndex = 1,
            explanation = "El Rey del Universo eligió nacer con total sencillez en Belén («Casa del Pan»), anunciado primero a los pastores humildes.",
            biblicalReference = "Lucas 2:4-7"
        ),
        ChallengeNode(
            id = 5,
            title = "Duelo de Ascenso a PLATA ⚔️",
            subtitle = "Reto 5 • Jefe de Rango (App: Eucaristía)",
            type = ChallengeNodeType.BOSS_RANK_UP,
            rankTier = FaithRankTier.BRONCE,
            difficulty = ChallengeDifficulty.INTERMEDIO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "DUELO DE ASCENSO A RANGO PLATA: En la sección «Primera Comunión (Eucaristía)» de la app la llamamos «El Superalimento del Alma» y leemos la historia de Lucas en su semana de exámenes. ¿Qué ocurre realmente en la Eucaristía?",
            options = listOf(
                "Es solamente un recuerdo simbólico como mirar una foto antigua",
                "El pan y el vino consagrados son realmente el Cuerpo, Sangre, Alma y Divinidad de Jesús vivo que nos abraza desde dentro",
                "Es un premio exclusivo para personas que jamás cometen errores",
                "Es solo una tradición cultural dominical"
            ),
            correctOptionIndex = 1,
            explanation = "¡Ascendiste a PLATA y desbloqueaste el emblema Explorador Kairós! Como cita la app: «La Eucaristía no es premio para los perfectos, sino medicina para los débiles».",
            biblicalReference = "App Kairós • Eucaristía (Juan 6:54-56)"
        ),

        // ==================== ETAPA 2: PLATA -> ORO (Retos 6 al 10) ====================
        ChallengeNode(
            id = 6,
            title = "La Confirmación: ¿Graduación o Batería Pro?",
            subtitle = "Reto 6 • Basado en la App: Sacramentos",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.PLATA,
            difficulty = ChallengeDifficulty.INTERMEDIO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En la pantalla «La Confirmación» de la app se desmiente un error que muchos jóvenes repiten. Según la analogía de la app, ¿qué es en verdad la Confirmación?",
            options = listOf(
                "Es la «graduación de la Iglesia» para despedirse y no volver a Misa",
                "Es pasar del banquillo de suplentes a jugador titular: el «Botón de Activación y Batería Pro» con los 7 dones del Espíritu Santo",
                "Un trámite obligatorio solo para tener padrinos",
                "Una ceremonia sin relación con la vida diaria"
            ),
            correctOptionIndex = 1,
            explanation = "¡Muy bien! En la app explicamos que la Confirmación es tu Pentecostés personal para ser protagonista valiente, no espectador.",
            biblicalReference = "App Kairós • Confirmación (Hechos 1:8)"
        ),
        ChallengeNode(
            id = 7,
            title = "El Libro Bajo la Almohada a las 11:59 PM",
            subtitle = "Reto 7 • Jóvenes: Divertida y Razonable",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.PLATA,
            difficulty = ChallengeDifficulty.INTERMEDIO,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "Tienes examen mañana a primera hora, no abriste el cuaderno en todo el mes y a las 11:59 PM pones el libro bajo la almohada rezando para que el Espíritu Santo te descargue el PDF por Bluetooth en la mente. Según la «Oración antes de un Examen» y los Dilemas de la app, ¿qué es lo razonable?",
            options = listOf(
                "Confiar en que Dios hará magia sin que yo estudie ni una página",
                "Estudiar con responsabilidad y rezar: «Señor, Tú conoces el esfuerzo que he puesto en estudiar; disipa mi ansiedad y ayúdame a recordar lo aprendido»",
                "Copiarle todo al compañero de al lado",
                "Faltar a clases cada vez que haya examen"
            ),
            correctOptionIndex = 1,
            explanation = "Como dice la analogía de nuestra app: «Rezar por un examen no te exime de sentarte a estudiar». ¡Dios bendice tu esfuerzo honesto y te da paz!",
            biblicalReference = "App Kairós • Oraciones y Dilemas",
            dailyMissionCommitment = "Hoy pondré mi mejor esfuerzo en mis estudios o deberes ofreciendo mi trabajo a Dios."
        ),
        ChallengeNode(
            id = 8,
            title = "Jesús a los 12 Años y el Río Jordán",
            subtitle = "Reto 8 • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.PLATA,
            difficulty = ChallengeDifficulty.INTERMEDIO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Siguiendo la historia de Jesús: a los 12 años fue hallado en el Templo entre los doctores de la Ley, creció trabajando en Nazaret y a los 30 años inició su vida pública. ¿Quién lo bautizó en el río Jordán cuando se abrieron los cielos?",
            options = listOf(
                "El emperador César Augusto",
                "San Juan Bautista, mientras el Espíritu Santo descendía como paloma y la voz del Padre decía: «Este es mi Hijo amado»",
                "Nicodemo en secreto",
                "El sumo sacerdote Caifás"
            ),
            correctOptionIndex = 1,
            explanation = "Aunque Jesús no tenía pecado, quiso sumergirse en el Jordán con Juan Bautista para santificar las aguas y mostrarnos el camino de la humildad.",
            biblicalReference = "Mateo 3:13-17 • Lucas 2:46-49"
        ),
        ChallengeNode(
            id = 9,
            title = "¿Ser Católico es Aburrido y Sin Fiesta?",
            subtitle = "Reto 9 • Basado en la App: Dilemas",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.PLATA,
            difficulty = ChallengeDifficulty.INTERMEDIO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En el Dilema «¿Ser católico significa ser aburrido y no poder salir de fiesta?» de nuestra app, ¿qué analogía moderna se usa para explicar para qué sirven los mandamientos de Dios cuando sales a divertirte?",
            options = listOf(
                "Son como una jaula que prohíbe reír, bailar o tener amigos",
                "Son como los barandales en un mirador alto: no te quitan la vista ni la diversión, sino que te protegen para disfrutar sin caerte al precipicio",
                "Dicen que toda música y deporte son malos",
                "Solo permiten divertirse una vez al año"
            ),
            correctOptionIndex = 1,
            explanation = "¡Tal cual aparece en la app! La verdadera fiesta te deja al día siguiente con el corazón en paz, recuerdos bonitos y sin resaca moral.",
            biblicalReference = "App Kairós • Dilemas (San Juan Bosco)"
        ),
        ChallengeNode(
            id = 10,
            title = "Duelo de Ascenso a ORO 👑",
            subtitle = "Reto 10 • Jefe de Rango (App: Chispas y Oraciones)",
            type = ChallengeNodeType.BOSS_RANK_UP,
            rankTier = FaithRankTier.PLATA,
            difficulty = ChallengeDifficulty.INTERMEDIO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "DUELO DE ASCENSO A RANGO ORO: En la primera «Chispa del Día» y en la última oración de la app aparece San Carlo Acutis (1991-2006), joven programador católico. ¿Cuál fue su frase más famosa y cómo llamaba a la Eucaristía?",
            options = listOf(
                "«Todos nacen como originales, pero muchos mueren como fotocopias», y llamaba a la Eucaristía «mi autopista hacia el Cielo»",
                "«Lo único que importa es volverse viral a cualquier precio»",
                "«El internet no sirve para hablar de Dios»",
                "«Solo los adultos mayores pueden ser santos»"
            ),
            correctOptionIndex = 0,
            explanation = "¡Subiste a rango ORO y desbloqueaste el emblema Batería Pro! Carlo Acutis demostró que se puede ser joven, amar la tecnología y ser profundamente santo.",
            biblicalReference = "App Kairós • Chispas y Oraciones (Isaías 43:1)"
        ),

        // ==================== ETAPA 3: ORO -> PLATINO (Retos 11 al 15) ====================
        ChallengeNode(
            id = 11,
            title = "El Sacerdote del Big Bang y la Genética",
            subtitle = "Reto 11 • Basado en la App: Dilemas",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ORO,
            difficulty = ChallengeDifficulty.INTERMEDIO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En el primer Dilema de la app («¿La Iglesia Católica y la ciencia están en guerra?»), se menciona a dos grandes científicos católicos que revolucionaron la ciencia mundial. ¿Quiénes son?",
            options = listOf(
                "Ninguno, porque la app dice que la ciencia está prohibida",
                "El sacerdote y astrofísico Georges Lemaître (quien formuló la teoría del Big Bang) y el monje agustino Gregor Mendel (padre de la genética moderna)",
                "Dos emperadores romanos del siglo I",
                "Dos influencers de redes sociales"
            ),
            correctOptionIndex = 1,
            explanation = "Como enseña la app: «La ciencia explica CÓMO funciona el universo; la fe revela POR QUÉ y PARA QUÉ existes».",
            biblicalReference = "App Kairós • Dilemas: Razón y Ciencia"
        ),
        ChallengeNode(
            id = 12,
            title = "Cuando le haces «Ghosting» a Dios",
            subtitle = "Reto 12 • Jóvenes: Divertida y Razonable",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.ORO,
            difficulty = ChallengeDifficulty.INTERMEDIO,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "Imagina tener un amigo al que dejas «en visto» durante 8 meses y solo le escribes: «Hola perdido, ¿me prestas dinero?». A veces tratamos así a Dios. Según el Dilema de la app sobre la oración, ¿con qué se compara el orar con constancia aunque estés distraído?",
            options = listOf(
                "Con ponerse bajo los rayos del sol: no requiere discursos poéticos falsos, sino estar presente con honestidad y dejar que Dios ilumine tu alma",
                "Con recitar 500 palabras difíciles sin pensar en lo que dices",
                "Con hablarle a Dios únicamente cuando sientes «mariposas espirituales»",
                "Con esperar a no tener ningún problema para rezar"
            ),
            correctOptionIndex = 0,
            explanation = "La app enseña que la oración no es una emoción pasajera, sino una cita de amistad fiel y sincera con quien más te quiere.",
            biblicalReference = "App Kairós • Dilemas: Espiritualidad",
            dailyMissionCommitment = "Hoy hablaré con Jesús 2 minutos con total sinceridad, como con mi mejor amigo."
        ),
        ChallengeNode(
            id = 13,
            title = "El Desierto y el Primer Milagro en Caná",
            subtitle = "Reto 13 • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ORO,
            difficulty = ChallengeDifficulty.DIFICIL,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Tras ser bautizado, Jesús ayunó 40 días en el desierto venciendo las tentaciones y luego fue con su madre María y sus discípulos a una boda en Caná de Galilea. Cuando se acabó el vino, ¿qué dijo María a los sirvientes y qué milagro hizo Jesús?",
            options = listOf(
                "Les dijo que cancelaran la boda y se fueran a casa",
                "María dijo: «Hagan todo lo que Él les diga», y Jesús convirtió el agua de seis tinajas en el mejor vino de la boda",
                "Jesús multiplicó monedas de oro para comprar vino",
                "Jesús hizo llover en medio del salón"
            ),
            correctOptionIndex = 1,
            explanation = "En las bodas de Caná Jesús realizó su primer signo milagroso por intercesión de María, manifestando su gloria y fortaleciendo la fe de sus discípulos.",
            biblicalReference = "Juan 2:1-11"
        ),
        ChallengeNode(
            id = 14,
            title = "Sofía y la Valentía en la Fiesta",
            subtitle = "Reto 14 • Basado en la App: Confirmación",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ORO,
            difficulty = ChallengeDifficulty.DIFICIL,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En la sección «La Confirmación» de la app leemos la historia real de Sofía (17 años, preparatoria): en una fiesta un grupo empezó a viralizar fotos denigrantes de una compañera ausente. ¿Qué dones del Espíritu Santo activó Sofía y qué ocurrió?",
            options = listOf(
                "Se rió con los demás por miedo a perder popularidad",
                "Activó los dones de Fortaleza y Consejo: dijo firmemente que eso era cruel e inaceptable, apoyó a la chica e inspiró a otros tres a borrar las fotos",
                "Subió las fotos a otra red social",
                "Se quedó callada y se fue sin ayudar"
            ),
            correctOptionIndex = 1,
            explanation = "Como resume Sofía en la app: «El Espíritu Santo no te hace invisible; te da el coraje para ser la persona que otros necesitan ver».",
            biblicalReference = "App Kairós • Confirmación (Historia de Sofía)"
        ),
        ChallengeNode(
            id = 15,
            title = "Duelo de Ascenso a PLATINO 💠",
            subtitle = "Reto 15 • Jefe de Rango (Historia de Jesús)",
            type = ChallengeNodeType.BOSS_RANK_UP,
            rankTier = FaithRankTier.ORO,
            difficulty = ChallengeDifficulty.DIFICIL,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "DUELO DE ASCENSO A PLATINO: Jesús eligió a los 12 Apóstoles para que estuvieran con Él y enviarlos a predicar. Cuando preguntó en Cesarea de Filipo «¿Quién dicen ustedes que soy yo?», Simón respondió: «Tú eres el Mesías, el Hijo de Dios vivo». ¿Qué le prometió Jesús entonces (Mateo 16:18-19)?",
            options = listOf(
                "«Tú eres Pedro (Piedra), y sobre esta piedra edificaré mi Iglesia, y el poder del infierno no prevalecerá contra ella; te daré las llaves del Reino de los Cielos»",
                "«Te haré emperador político de Roma»",
                "«Nunca pasarás por ninguna prueba ni dificultad»",
                "«Cada apóstol fundará una religión diferente»"
            ),
            correctOptionIndex = 0,
            explanation = "¡Ascendiste a PLATINO y desbloqueaste el emblema Fe & Ciencia! Cristo fundó su Iglesia sobre la roca de la fe apostólica con Pedro al frente.",
            biblicalReference = "Mateo 16:16-19"
        ),

        // ==================== ETAPA 4: PLATINO -> DIAMANTE (Retos 16 al 20) ====================
        ChallengeNode(
            id = 16,
            title = "Los 7 Libros Deuterocanónicos en la App",
            subtitle = "Reto 16 • Basado en la App: Biblia",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.PLATINO,
            difficulty = ChallengeDifficulty.DIFICIL,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "Si usas el filtro «Deuterocanónicos» en la sección Biblia de nuestra app, verás los 7 libros del Antiguo Testamento propios del canon católico. ¿Cuáles son esos 7 libros?",
            options = listOf(
                "Génesis, Éxodo, Levítico, Números, Deuteronomio, Josué y Jueces",
                "Tobías, Judit, Sabiduría, Eclesiástico (Sirácida), Baruc, 1 Macabeos y 2 Macabeos",
                "Mateo, Marcos, Lucas, Juan, Hechos, Romanos y Apocalipsis",
                "Salmos, Proverbios, Job, Rut, Ester, Esdras y Nehemías"
            ),
            correctOptionIndex = 1,
            explanation = "En la pestaña «Guía Católica» de nuestra Biblia en la app se explica por qué estos 7 libros forman parte de la Biblia usada por los Apóstoles desde el siglo I.",
            biblicalReference = "App Kairós • Biblia (Deuterocanónicos)"
        ),
        ChallengeNode(
            id = 17,
            title = "El «No es por Hablar Mal, PERO...»",
            subtitle = "Reto 17 • Jóvenes: Divertida y Razonable",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.PLATINO,
            difficulty = ChallengeDifficulty.DIFICIL,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "Clásico momento juvenil: alguien en el grupo dice «No soy chismoso, PERO...» y empieza a destruir la reputación de alguien que no está. Según la cuarta Chispa del Día de la app (San Juan Pablo II: «La verdad os hará libres»), ¿qué hace un joven con carácter?",
            options = listOf(
                "Dice «no» con amabilidad a la conversación de chismes o cambia el tema, porque sabe que quien critica a otros contigo, mañana te criticará a ti",
                "Graba audio y lo reenvía a otros 5 grupos para tener más chisme",
                "Inventa detalles extras para que la historia suene más épica",
                "Se burla para caerle bien al líder del grupo"
            ),
            correctOptionIndex = 0,
            explanation = "La micro-misión de San Juan Pablo II en la pantalla principal de la app lo pide literalmente: «Di 'no' con amabilidad a una conversación de chismes o críticas destructivas hoy».",
            biblicalReference = "App Kairós • Chispa 4 (Juan 8:32)",
            dailyMissionCommitment = "Hoy frenaré cualquier chisme o crítica destructiva hablando siempre bien o guardando respeto."
        ),
        ChallengeNode(
            id = 18,
            title = "El Confesonario: El Hospital del Alma",
            subtitle = "Reto 18 • Basado en la App: Dilemas",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.PLATINO,
            difficulty = ChallengeDifficulty.DIFICIL,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En el Dilema «¿Por qué confesarme con un sacerdote y no directo con Dios?» de la app, se explica cómo actúa el sacerdote y con qué lugar se compara el confesonario. ¿Cuál es la respuesta de la app?",
            options = listOf(
                "Es un tribunal para regañarte y publicar tus errores",
                "El confesonario es el «hospital del alma»: el sacerdote actúa «in persona Christi» bajo secreto sacramental absoluto para darte el abrazo real del perdón de Jesús (Juan 20:23)",
                "Es un invento medieval sin base en el Evangelio",
                "Solo sirve para quienes nunca se arrepienten"
            ),
            correctOptionIndex = 1,
            explanation = "Como dice la cita del Papa Francisco en ese Dilema de la app: «Dios no se cansa nunca de perdonar, somos nosotros los que nos cansamos de acudir a su misericordia».",
            biblicalReference = "App Kairós • Dilemas (Juan 20:21-23)"
        ),
        ChallengeNode(
            id = 19,
            title = "Los 5 Panes y Jesús Caminando sobre el Mar",
            subtitle = "Reto 19 • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.PLATINO,
            difficulty = ChallengeDifficulty.DIFICIL,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Durante su ministerio en Galilea, Jesús alimentó a más de 5,000 personas multiplicando 5 panes y 2 peces. Esa noche los discípulos iban en la barca contra el viento y Jesús fue hacia ellos caminando sobre el mar. ¿Qué pasó cuando Pedro salió a caminar sobre el agua?",
            options = listOf(
                "Caminó mientras miraba a Jesús, pero al ver la violencia del viento tuvo miedo, empezó a hundirse y gritó «¡Señor, sálvame!», y Jesús lo tomó de la mano",
                "Pedro cruzó todo el lago corriendo sin dudar",
                "Jesús dejó que Pedro se hundiera para castigarlo",
                "Era solo un espejismo en la orilla"
            ),
            correctOptionIndex = 0,
            explanation = "Cuando fijas tu mirada en Jesús caminas por encima de las tormentas; y si flaqueas y lo llamas, Su mano te sostiene al instante.",
            biblicalReference = "Mateo 14:13-33"
        ),
        ChallengeNode(
            id = 20,
            title = "Duelo de Ascenso a DIAMANTE 💎",
            subtitle = "Reto 20 • Jefe de Rango (App: Pausa y Diario)",
            type = ChallengeNodeType.BOSS_RANK_UP,
            rankTier = FaithRankTier.PLATINO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "DUELO DE ASCENSO A DIAMANTE: En nuestra app Kairós tienes la «Pausa de 2 Minutos con Dios» (120 segundos de respiración y paz) y la sección «Mi Diario Espiritual». ¿Cuáles son las 4 categorías en las que puedes guardar tus notas en el Diario de la app?",
            options = listOf(
                "Deportes, Videojuegos, Comida y Películas",
                "Gratitud, Petición de Oración, Lectura Espiritual y Reflexión Sacramental",
                "Matemáticas, Historia, Química y Literatura",
                "Quejas, Chismes, Excusas y Olvidos"
            ),
            correctOptionIndex = 1,
            explanation = "¡Ascendiste a DIAMANTE y ganaste el emblema Guardián de los 73 Libros! Conoces a fondo las herramientas espirituales de Kairós.",
            biblicalReference = "App Kairós • Mi Diario y Pausa"
        ),

        // ==================== ETAPA 5: DIAMANTE -> ESMERALDA (Retos 21 al 25) ====================
        ChallengeNode(
            id = 21,
            title = "Salud Mental, Ansiedad y Fe en la App",
            subtitle = "Reto 21 • Basado en la App: Dilemas",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.DIAMANTE,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En el Dilema «Tengo ansiedad y tristeza profunda: ¿Basta con rezar o necesito terapia?» de la app, se cita el libro deuterocanónico de Eclesiástico 38:1. ¿Qué enseña la app sobre ir al psicólogo o médico?",
            options = listOf(
                "Que tener ansiedad es un castigo divino y está prohibido ir al médico",
                "Que la fe y la psicología van de la mano: tener ansiedad o depresión NO es falta de fe, y pedir ayuda profesional es de personas fuertes («Honra al médico, pues también a él lo creó el Señor»)",
                "Que la psicología reemplaza por completo el sentido de la vida",
                "Que debes ocultar lo que sientes y no contárselo a nadie"
            ),
            correctOptionIndex = 1,
            explanation = "Como explica la app: somos cuerpo, mente y espíritu. La fe te da esperanza y sentido profundo; la terapia te da herramientas clínicas para sanar.",
            biblicalReference = "App Kairós • Dilemas (Eclesiástico 38:1)"
        ),
        ChallengeNode(
            id = 22,
            title = "El «Personaje Principal» vs. Ayudar sin Cámaras",
            subtitle = "Reto 22 • Jóvenes: Divertida y Razonable",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.DIAMANTE,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "Hoy está de moda hacer una buena acción solo si hay alguien grabando en 4K con música emotiva de fondo para subirlo a Reels. Pero en la Chispa #1 de la app hay una micro-misión muy clara. ¿Cuál es?",
            options = listOf(
                "«Haz hoy una buena acción anónima por alguien sin publicar nada en redes ni contárselo a nadie»",
                "Cobrar por cada favor que hagas en tu casa",
                "Ayudar únicamente si te garantizan 10,000 vistas",
                "No ayudar a nadie para no cansarte"
            ),
            correctOptionIndex = 0,
            explanation = "El bien que haces en silencio sin buscar aplausos ni likes tiene un valor infinito ante los ojos de Dios (Mateo 6:3-4).",
            biblicalReference = "App Kairós • Chispa 1 (Mateo 6:3-4)",
            dailyMissionCommitment = "Hoy haré un favor o acto de bondad 100% anónimo sin presumirlo."
        ),
        ChallengeNode(
            id = 23,
            title = "La Resurrección de Lázaro en Betania",
            subtitle = "Reto 23 • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.DIAMANTE,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Poco antes de su Pasión, Jesús fue a Betania donde su amigo Lázaro llevaba cuatro días muerto en el sepulcro. Jesús lloró conmovido y antes de gritar «¡Lázaro, sal afuera!», ¿qué gran revelación le dijo a Marta en Juan 11:25?",
            options = listOf(
                "«Ya es demasiado tarde y nada puedo hacer»",
                "«Yo soy la Resurrección y la Vida; el que cree en mí, aunque muera, vivirá»",
                "«La muerte es el final definitivo del ser humano»",
                "«Solo los ricos entrarán en el Reino»"
            ),
            correctOptionIndex = 1,
            explanation = "Este milagro estremeció a toda Jerusalén y mostró que Jesús tiene poder absoluto sobre la vida y la muerte.",
            biblicalReference = "Juan 11:25-44"
        ),
        ChallengeNode(
            id = 24,
            title = "El «Filtro Anti-Superficialidad» y el «GPS Moral»",
            subtitle = "Reto 24 • Basado en la App: 7 Dones",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.DIAMANTE,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En el apartado «Confirmación» de la app se explican los 7 Dones del Espíritu Santo con una traducción juvenil. ¿Cuáles son los dos dones que la app traduce como «Filtro anti-superficialidad» y «Tu GPS moral»?",
            options = listOf(
                "Sabiduría («Filtro anti-superficialidad») y Consejo («Tu GPS moral» para saber qué camino tomar en las encrucijadas)",
                "Fama y Fortuna",
                "Velocidad y Memoria",
                "Orgullo y Simpatía"
            ),
            correctOptionIndex = 0,
            explanation = "En las tarjetas desplegables de Confirmación de la app también vemos Entendimiento («Empatía nivel experto») y Fortaleza («Resiliencia de acero»).",
            biblicalReference = "App Kairós • Confirmación (Isaías 11:2)"
        ),
        ChallengeNode(
            id = 25,
            title = "Duelo de Ascenso a ESMERALDA ❇️",
            subtitle = "Reto 25 • Jefe de Rango (Historia de Jesús)",
            type = ChallengeNodeType.BOSS_RANK_UP,
            rankTier = FaithRankTier.DIAMANTE,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "DUELO DE ASCENSO A ESMERALDA: Comienza la última semana de Jesús antes de su crucifixión (Domingo de Ramos). ¿Cómo entró Jesús en Jerusalén y qué aclamaba la multitud con ramas de palma y olivo?",
            options = listOf(
                "Entró en un carro de guerra con un ejército armado para derrocar a Pilato",
                "Entró con humildad montado en un burrito como Rey de Paz, mientras la gente aclamaba: «¡Hosanna al Hijo de David! ¡Bendito el que viene en nombre del Señor!»",
                "Entró disfrazado de noche sin que nadie lo viera",
                "Se negó a entrar a Jerusalén por temor"
            ),
            correctOptionIndex = 1,
            explanation = "¡Ascendiste al nuevo rango ESMERALDA y desbloqueaste el emblema Joven con Chispa! Jesús entró como Rey manso y humilde dispuesto a dar su vida.",
            biblicalReference = "Mateo 21:1-9"
        ),

        // ==================== ETAPA 6: ESMERALDA -> ZAFIRO (Retos 26 al 30) ====================
        ChallengeNode(
            id = 26,
            title = "Dudas de Fe: ¿Pecado o Dolores de Crecimiento?",
            subtitle = "Reto 26 • Basado en la App: Dilemas",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ESMERALDA,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En el Dilema «Tengo muchas dudas sobre la religión: ¿Estoy perdiendo mi fe o pecando?» de nuestra app, ¿con qué analogía del cuerpo humano se comparan las dudas honestas cuando buscas respuestas?",
            options = listOf(
                "Con los «dolores de crecimiento en los huesos»: señal de que tu fe infantil está creciendo para convertirse en una fe madura y sólida",
                "Con una enfermedad incurable que te obliga a dejar de pensar",
                "Con una prohibición de hacer preguntas en la Iglesia",
                "Con aceptar cualquier mito de videos de 15 segundos sin investigar"
            ),
            correctOptionIndex = 0,
            explanation = "¡Exacto! La app recomienda no quedarse en la duda perezosa, sino investigar con seriedad (como en el YouCat o leyendo a C.S. Lewis).",
            biblicalReference = "App Kairós • Dilemas: Crecimiento"
        ),
        ChallengeNode(
            id = 27,
            title = "Hasta los Peces Muertos Siguen la Corriente",
            subtitle = "Reto 27 • Jóvenes: Divertida y Razonable",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.ESMERALDA,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "Cuando alguien te presiona diciendo: «¡Ándale, hazlo, absolutamente TODOS lo hacen!», ¿por qué tener el don de Fortaleza («Resiliencia de acero» en la app) es la respuesta más inteligente y con más estilo?",
            options = listOf(
                "Porque para dejarse arrastrar por la corriente basta con estar muerto; en cambio, para nadar contracorriente y mantener tus principios sin perder la alegría se necesita estar muy vivo",
                "Porque siempre hay que imitar a la mayoría aunque termine mal",
                "Porque decir que «no» a algo dañino te quita personalidad",
                "Porque la opinión de desconocidos vale más que tu conciencia"
            ),
            correctOptionIndex = 0,
            explanation = "Como dice San Juan Pablo II en la app: la verdadera libertad es tener la fuerza interior de elegir lo que construye tu bien sin ser esclavo del «qué dirán».",
            biblicalReference = "App Kairós • Fortaleza (Romanos 12:2)",
            dailyMissionCommitment = "Hoy seré fiel a mis valores con alegría aunque me toque ir contracorriente."
        ),
        ChallengeNode(
            id = 28,
            title = "Jueves Santo: Jesús Lava los Pies a los Doce",
            subtitle = "Reto 28 • Historia de Jesús (Víspera de la Cruz)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ESMERALDA,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Llegamos a la noche anterior a su crucifixión. En el Cenáculo, sabiendo Jesús que había llegado su hora de pasar de este mundo al Padre, se levantó de la cena, se ató una toalla y lavó los pies a sus discípulos. ¿Qué lección les dejó con este acto?",
            options = listOf(
                "Que los líderes deben exigir que todos les sirvan de rodillas",
                "«Si yo, que soy el Señor y el Maestro, les he lavado los pies, también ustedes deben lavarse los pies unos a otros»: la grandeza está en el servicio humilde",
                "Que era solo un protocolo de higiene antes de cenar",
                "Que Pedro no podía ser parte de sus amigos"
            ),
            correctOptionIndex = 1,
            explanation = "En la noche en que iba a ser entregado, Jesús nos dejó la toalla del servicio como distintivo del verdadero discípulo.",
            biblicalReference = "Juan 13:1-15"
        ),
        ChallengeNode(
            id = 29,
            title = "La Última Cena y el Mandamiento Nuevo",
            subtitle = "Reto 29 • Historia de Jesús (Víspera de la Cruz)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ESMERALDA,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "En esa misma Última Cena, Jesús tomó el pan diciendo: «Tomen y coman, esto es mi Cuerpo», y el cáliz diciendo: «Esta es mi Sangre de la Alianza, que será derramada por muchos», instituyendo la Eucaristía y el Sacerdocio. ¿Qué «Mandamiento Nuevo» les dio esa noche?",
            options = listOf(
                "«Ámense los unos a los otros; como yo los he amado, así también ámense los unos a los otros. En esto conocerán todos que son mis discípulos»",
                "«Conquisten por la fuerza a todas las naciones»",
                "«Amen solo a quienes piensen igual que ustedes»",
                "«Acumulen riquezas en la tierra»"
            ),
            correctOptionIndex = 0,
            explanation = "La medida del amor cristiano ya no es solo «como a ti mismo», sino «como Yo los he amado»: hasta dar la vida.",
            biblicalReference = "Juan 13:34-35 • Lucas 22:19-20"
        ),
        ChallengeNode(
            id = 30,
            title = "Duelo de Ascenso a ZAFIRO 🔷",
            subtitle = "Reto 30 • Jefe de Rango (Historia de Jesús)",
            type = ChallengeNodeType.BOSS_RANK_UP,
            rankTier = FaithRankTier.ESMERALDA,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "DUELO DE ASCENSO A ZAFIRO: Durante la Última Cena, Jesús anunció con tristeza que uno de los Doce lo iba a entregar. ¿Por cuántas monedas de plata pactó Judas Iscariote entregarlo, y qué le predijo Jesús a Pedro esa noche?",
            options = listOf(
                "Por 1,000 monedas de oro, y le dijo a Pedro que nunca tendría miedo",
                "Judas lo entregó por 30 monedas de plata, y Jesús advirtió a Pedro: «Te aseguro que hoy, esta misma noche, antes de que el gallo cante, me habrás negado tres veces»",
                "Por un terreno en Galilea, y Pedro se fue de viaje",
                "Judas no era parte de los doce apóstoles"
            ),
            correctOptionIndex = 1,
            explanation = "¡Ascendiste a ZAFIRO y desbloqueaste el emblema Discípulo del Cenáculo! Jesús conocía la fragilidad de sus amigos y aun así dio la vida por ellos.",
            biblicalReference = "Mateo 26:14-15, 34"
        ),

        // ==================== ETAPA 7: ZAFIRO -> RUBÍ (Retos 31 al 35) ====================
        ChallengeNode(
            id = 31,
            title = "La Agonía de Jesús en el Huerto de Getsemaní",
            subtitle = "Reto 31 • Historia de Jesús (Noche del Jueves)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ZAFIRO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Al terminar la Última Cena, Jesús salió hacia el Monte de los Olivos, al huerto de Getsemaní. Sentía una tristeza mortal y en su agonía oraba tan intensamente que su sudor caía a tierra como gotas de sangre. ¿Qué palabras de fidelidad dijo al Padre?",
            options = listOf(
                "«Padre, si quieres, aparta de mí este cáliz; pero no se haga mi voluntad, sino la tuya»",
                "«Padre, envía ahora mismo doce legiones de ángeles para destruir el mundo»",
                "«Me arrepiento de haber venido a salvar a la humanidad»",
                "«Huiré de Jerusalén antes de que lleguen los soldados»"
            ),
            correctOptionIndex = 0,
            explanation = "En Getsemaní, Jesús venció con su «Sí» libre y amoroso al Padre donde Adán había caído por desobediencia.",
            biblicalReference = "Lucas 22:39-44"
        ),
        ChallengeNode(
            id = 32,
            title = "El Arresto en el Huerto y la Oreja de Malco",
            subtitle = "Reto 32 • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ZAFIRO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Guiados por Judas, llegaron los guardias con espadas y antorchas para arrestar a Jesús en Getsemaní. Judas lo señaló con un beso. Pedro sacó una espada e hirió en la oreja derecha a Malco, siervo del sumo sacerdote. ¿Cómo reaccionó Jesús?",
            options = listOf(
                "Animó a todos los apóstoles a pelear con espadas",
                "Dijo a Pedro: «Vuelve tu espada a su sitio», tocó la oreja de Malco y lo sanó milagrosamente antes de dejarse atar",
                "Huyó corriendo entre los olivos",
                "Invocó fuego del cielo contra los guardias"
            ),
            correctOptionIndex = 1,
            explanation = "Incluso en el momento en que lo arrestaban injustamente, el último milagro de curación de Jesús antes de la Cruz fue sanar a uno de los que venían a prenderlo.",
            biblicalReference = "Lucas 22:47-51 • Juan 18:10-11"
        ),
        ChallengeNode(
            id = 33,
            title = "El «Filtro de la Paz» en las Decisiones",
            subtitle = "Reto 33 • Jóvenes: Divertida y Razonable",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.ZAFIRO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "En el apartado «Oraciones» de la app está la «Oración para Tomar una Decisión Difícil». Cuando estás en un dilema juvenil (una amistad, una fiesta o una decisión moral), ¿cuál es la prueba más razonable para saber si vas por buen camino?",
            options = listOf(
                "Preguntarte: «¿Esto me acerca al amor auténtico, a la verdad y me deja paz en el corazón, o me obliga a llevar doble vida y mentir?»",
                "Lanzar una moneda al aire sin pensar en las consecuencias",
                "Elegir siempre lo más impulsivo aunque lastime a otros",
                "Hacer lo que te dé más miedo al qué dirán"
            ),
            correctOptionIndex = 0,
            explanation = "Tal como reza la «Oración para Tomar una Decisión Difícil» en nuestra app: «Muéstrame cuál es el camino que me acerca más al amor auténtico, a la verdad y a mi mejor versión».",
            biblicalReference = "App Kairós • Oraciones: Discernimiento",
            dailyMissionCommitment = "Hoy tomaré mis decisiones buscando la verdad y la paz de Dios en mi conciencia."
        ),
        ChallengeNode(
            id = 34,
            title = "Las Tres Negaciones de Pedro y la Mirada de Cristo",
            subtitle = "Reto 34 • Historia de Jesús (Madrugada del Viernes)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ZAFIRO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Mientras interrogaban y golpeaban a Jesús en casa del sumo sacerdote Caifás, Pedro estaba en el patio junto al fuego. Tres veces lo reconocieron y tres veces Pedro negó conocer a Jesús. En ese instante cantó el gallo. ¿Qué pasó entonces según Lucas 22:61-62?",
            options = listOf(
                "El Señor se volvió y miró a Pedro con infinito amor; Pedro recordó su palabra y, saliendo afuera, lloró amargamente arrepentido",
                "Jesús le gritó desde lejos que jamás lo perdonaría",
                "Pedro se unió a los soldados romanos",
                "Pedro nunca volvió a hablar con los demás apóstoles"
            ),
            correctOptionIndex = 0,
            explanation = "Las lágrimas de Pedro fueron lágrimas de arrepentimiento y confianza en la misericordia de Jesús; por eso, tras resucitar, Jesús le preguntó tres veces: «¿Me amas?».",
            biblicalReference = "Lucas 22:54-62"
        ),
        ChallengeNode(
            id = 35,
            title = "Duelo de Ascenso a RUBÍ ❤️‍🔥",
            subtitle = "Reto 35 • Jefe de Rango (App: Lector Bíblico)",
            type = ChallengeNodeType.BOSS_RANK_UP,
            rankTier = FaithRankTier.ZAFIRO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "DUELO DE ASCENSO A RUBÍ: En el lector de capítulos completos de la Biblia dentro de Kairós, destacan Filipenses 4 y el libro deuterocanónico de Tobías 4 (los consejos del padre Tobit a su hijo joven). ¿Qué enseñan estos dos capítulos de la app?",
            options = listOf(
                "En Filipenses 4:13: «Todo lo puedo en Cristo que me fortalece»; y en Tobías 4: «No hagas a nadie lo que aborreces, da limosna de tus bienes sin volver la cara a ningún pobre y bendice siempre al Señor»",
                "Que solo debemos preocuparnos por nosotros mismos",
                "Que la alegría cristiana depende de no tener nunca dificultades",
                "Que el Antiguo Testamento no tiene consejos para jóvenes"
            ),
            correctOptionIndex = 0,
            explanation = "¡Ascendiste al rango RUBÍ y ganaste el emblema Centinela de Getsemaní! Conoces al detalle los capítulos bíblicos explicados para jóvenes en Kairós.",
            biblicalReference = "App Kairós • Filipenses 4:13 y Tobías 4:7-15"
        ),

        // ==================== ETAPA 8: RUBÍ -> HEROICO (Retos 36 al 40) ====================
        ChallengeNode(
            id = 36,
            title = "El Último Día: Jesús ante Poncio Pilato y Barrabás",
            subtitle = "Reto 36 • Historia de Jesús (Viernes Santo)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.RUBI,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Amanece el último día antes de que crucificaran a Jesús. Lo llevan atado ante el gobernador romano Poncio Pilato. Jesús le declara: «Mi Reino no es de este mundo... Yo para esto he nacido: para dar testimonio de la verdad». Pilato no halla delito en Él, pero ofrece liberar a un preso por la Pascua. ¿A quién eligió la multitud instigada?",
            options = listOf(
                "Pidieron que soltara a Barrabás (un agitador y homicida) y que crucificaran a Jesús, mientras Pilato se lavaba las manos",
                "Pidieron liberar a Jesús con honores",
                "Pilato defendió a Jesús hasta el final y lo dejó libre",
                "Liberaron a ambos presos en paz"
            ),
            correctOptionIndex = 0,
            explanation = "El Inocente ocupó el lugar del culpable Barrabás, imagen de cómo Cristo tomó nuestro lugar para librarnos de la condena del pecado.",
            biblicalReference = "Juan 18:36-40 • Mateo 27:15-26"
        ),
        ChallengeNode(
            id = 37,
            title = "La Flagelación y la Corona de Espinas",
            subtitle = "Reto 37 • Historia de Jesús (Viernes Santo)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.RUBI,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "En la mañana de aquel último día, Pilato mandó azotar (flagelar) a Jesús. Los soldados romanos trenzaron una corona de espinas, se la pusieron en la cabeza con un manto púrpura burlándose: «¡Salve, Rey de los judíos!», y Pilato lo mostró diciendo «¡Aquí tienen al hombre!» (Ecce Homo). ¿Qué profetizaba Isaías 53:5 sobre este sufrimiento?",
            options = listOf(
                "«Él fue traspasado por nuestras rebeliones, triturado por nuestras culpas, y por sus llagas hemos sido nosotros curados»",
                "«El Mesías nunca sufriría ningún dolor humano»",
                "«El Rey vendría únicamente a castigar a los pecadores»",
                "«Sus sufrimientos fueron por casualidad sin sentido»"
            ),
            correctOptionIndex = 0,
            explanation = "Cada espina y cada llaga de Jesús en aquel último día fue aceptada libremente por amor a ti y a toda la humanidad.",
            biblicalReference = "Juan 19:1-5 • Isaías 53:4-5"
        ),
        ChallengeNode(
            id = 38,
            title = "Batería al 1% en el Celular vs. 1% en el Alma",
            subtitle = "Reto 38 • Jóvenes: Divertida y Razonable",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.RUBI,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "Dato curioso juvenil: cuando tu celular llega al 1% de batería, corres por toda la casa saltando muebles para enchufarlo. Pero cuando tu paz interior está al 1%, seguimos scrolleando a las 2:00 AM. Según la historia de Lucas (20 años, arquitectura) en la app, ¿cuál es el cargador real cuando no puedes más?",
            options = listOf(
                "Frenar la prisa, buscar 15 minutos de silencio frente a Jesús Eucaristía o hacer la Pausa de Calma de la app diciéndole: «Señor, no puedo solo, llévame Tú»",
                "Tomar 6 bebidas energéticas y discutir en comentarios de internet",
                "Aislarse de toda la familia y amigos",
                "Pretender que nunca nos cansamos"
            ),
            correctOptionIndex = 0,
            explanation = "En el apartado «Eucaristía» de la app, Lucas cuenta: «No cambiaron mis entregas de la universidad, pero tras esos 15 minutos con Jesús yo ya no estaba solo para enfrentarlas».",
            biblicalReference = "App Kairós • Eucaristía (Mateo 11:28)",
            dailyMissionCommitment = "Hoy recargaré mi paz interior haciendo una pausa de oración silenciosa con Jesús."
        ),
        ChallengeNode(
            id = 39,
            title = "Camino al Calvario: Simón de Cirene carga la Cruz",
            subtitle = "Reto 39 • Historia de Jesús (Viernes Santo)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.RUBI,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Tras la sentencia de crucifixión, Jesús cargó su pesada Cruz por las calles de Jerusalén hacia el monte Gólgota (Calvario). Exhausto por la pérdida de sangre, los soldados obligaron a un hombre que volvía del campo a ayudar a Jesús a llevar la Cruz, mientras las mujeres lloraban por Él. ¿Quién fue ese hombre?",
            options = listOf(
                "Simón de Cirene (el Cirineo), padre de Alejandro y de Rufo",
                "Herodes Antipas",
                "El centurión Cornelio",
                "Zaqueo el publicano"
            ),
            correctOptionIndex = 0,
            explanation = "Simón de Cirene empezó llevando la Cruz a la fuerza, pero caminar al lado de Jesús transformó su vida y la de sus hijos (nombrados como cristianos conocidos en Marcos 15:21).",
            biblicalReference = "Marcos 15:21 • Lucas 23:26-28"
        ),
        ChallengeNode(
            id = 40,
            title = "Duelo de Ascenso a HEROICO 🔥",
            subtitle = "Reto 40 • Jefe de Rango (App: Cita del Día)",
            type = ChallengeNodeType.BOSS_RANK_UP,
            rankTier = FaithRankTier.RUBI,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "DUELO DE ASCENSO A HEROICO: Al abrir la app Kairós y en la tarjeta superior de la pantalla «Hoy» encuentras la «Cita del Día». ¿Qué elementos incluye cada Cita del Día en nuestra app y qué puedes hacer con ella?",
            options = listOf(
                "Incluye el versículo bíblico, su categoría, una reflexión juvenil y una oración breve; y puedes guardarla en «Mi Diario», compartirla con amigos o cambiar a la siguiente cita",
                "Solo muestra un número de página sin texto",
                "Es un anuncio publicitario",
                "No permite guardarla ni compartirla"
            ),
            correctOptionIndex = 0,
            explanation = "¡Ascendiste a rango HEROICO y ganaste el emblema Cirineo del Camino! Dominas cada función de la app y avanzas firme hacia la cumbre.",
            biblicalReference = "App Kairós • Cita del Día"
        ),

        // ==================== ETAPA 9: HEROICO -> MAESTRO (Retos 41 al 45) ====================
        ChallengeNode(
            id = 41,
            title = "La Crucifixión en el Gólgota: «Padre, perdónalos»",
            subtitle = "Reto 41 • Historia de Jesús (El Último Día en la Cruz)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.HEROICO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Llegados al monte Gólgota («Lugar de la Calavera»), crucificaron a Jesús clavándolo de manos y pies entre dos ladrones, y pusieron sobre su cabeza el letrero: «Jesús Nazareno, el Rey de los Judíos» (INRI). En medio de aquel dolor extremo, ¿cuál fue la primera palabra de Jesús en la Cruz (Lucas 23:34)?",
            options = listOf(
                "«Padre, perdónalos, porque no saben lo que hacen»",
                "«Que caiga castigo inmediato sobre mis verdugos»",
                "«Solo perdono a mis amigos cercanos»",
                "«No perdonaré a quienes se burlan al pie de la cruz»"
            ),
            correctOptionIndex = 0,
            explanation = "En el momento más duro de la historia humana, Jesús respondió al odio con el perdón más puro, excusando incluso a sus verdugos ante el Padre.",
            biblicalReference = "Lucas 23:33-34"
        ),
        ChallengeNode(
            id = 42,
            title = "El Buen Ladrón en la Cruz: «Hoy estarás conmigo»",
            subtitle = "Reto 42 • Historia de Jesús (El Último Día en la Cruz)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.HEROICO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Mientras los soldados sorteaban su túnica y uno de los malhechores crucificados lo insultaba, el otro (el Buen Ladrón, San Dimas) reconoció su culpa, defendió que Jesús no había hecho ningún mal y le dijo: «Jesús, acuérdate de mí cuando llegues a tu Reino». ¿Qué le respondió Jesús desde la Cruz?",
            options = listOf(
                "«Ya es demasiado tarde para arrepentirse en el último día»",
                "«En verdad te digo: hoy estarás conmigo en el Paraíso»",
                "«Primero debes esperar mil años»",
                "«No puedo escucharte en este momento»"
            ),
            correctOptionIndex = 1,
            explanation = "El Buen Ladrón se «robó el Cielo» en el último minuto de su vida con un acto de humildad y fe en Jesús Crucificado.",
            biblicalReference = "Lucas 23:39-43"
        ),
        ChallengeNode(
            id = 43,
            title = "Perdonar no es Amnesia: Es Soltar la Mochila de Piedras",
            subtitle = "Reto 43 • Jóvenes: Divertida y Razonable",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.HEROICO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "A veces cuando alguien nos falla pensamos: «Le voy a guardar rencor hasta el año 2090 para que sufra», mientras la otra persona duerme tranquila y tú sigues cargando el enojo. Mirando a Jesús en la Cruz y la sección Eucaristía («Fuerza para Perdonar») de la app, ¿por qué perdonar es lo más inteligente?",
            options = listOf(
                "Porque guardar rencor es como cargar una mochila llena de piedras las 24 horas: perdonar con la fuerza de Dios no es justificar el mal, sino liberar tu corazón para vivir en paz",
                "Porque el odio hace bien a la salud",
                "Porque hay que vengarse el doble en redes sociales",
                "Porque nunca debemos aceptar una disculpa sincera"
            ),
            correctOptionIndex = 0,
            explanation = "En el apartado de Eucaristía de la app leemos: «Comulgar te da la capacidad de perdonar a quien te lastimó, cuando tus fuerzas no alcanzan».",
            biblicalReference = "App Kairós • Eucaristía (Colosenses 3:13)",
            dailyMissionCommitment = "Hoy soltaré cualquier rencor entregándoselo a Jesús en la Cruz para recuperar mi paz."
        ),
        ChallengeNode(
            id = 44,
            title = "María y Juan al Pie de la Cruz",
            subtitle = "Reto 44 • Historia de Jesús (El Último Día en la Cruz)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.HEROICO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "En aquel último día en el Calvario, junto a la Cruz de Jesús estaban de pie su madre María, María de Cleofás, María Magdalena y el discípulo a quien Jesús amaba (San Juan). Antes de morir, ¿qué nos entregó Jesús al mirar a su Madre y al discípulo en Juan 19:26-27?",
            options = listOf(
                "Dijo a su Madre: «Mujer, ahí tienes a tu hijo», y luego dijo al discípulo: «Ahí tienes a tu madre»; y desde aquella hora el discípulo la recibió en su casa",
                "Les pidió que se marcharan rápido del monte Calvario",
                "Les entregó bienes materiales y monedas",
                "Les dijo que olvidaran todo lo ocurrido"
            ),
            correctOptionIndex = 0,
            explanation = "En Juan estábamos representados todos los discípulos: antes de dar su último aliento, Jesús nos dio su tesoro más querido, a su propia Madre María.",
            biblicalReference = "Juan 19:25-27"
        ),
        ChallengeNode(
            id = 45,
            title = "Duelo de Ascenso a MAESTRO 🌟",
            subtitle = "Reto 45 • Jefe de Rango (El Último Día en la Cruz)",
            type = ChallengeNodeType.BOSS_RANK_UP,
            rankTier = FaithRankTier.HEROICO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "DUELO DE ASCENSO A MAESTRO: Desde el mediodía hasta las tres de la tarde toda la tierra quedó en tinieblas. Jesús clamó el Salmo 22 («Dios mío, Dios mío, ¿por qué me has abandonado?»), dijo «Tengo sed» y, tras probar el vinagre en una caña de hisopo, pronunció la palabra que anuncia que nuestra salvación fue completada hasta el final (Juan 19:30). ¿Qué dijo?",
            options = listOf(
                "«¡Todo está cumplido!» (Consummatum est)",
                "«Mi misión quedó incompleta»",
                "«Bájenme ahora de la cruz»",
                "«Todo fue en vano»"
            ),
            correctOptionIndex = 0,
            explanation = "¡Ascendiste al rango MAESTRO y desbloqueaste el emblema Testigo del Gólgota! «¡Todo está cumplido!» no es un grito de derrota, sino el grito victorioso del Amor que cumplió toda la obra de nuestra redención.",
            biblicalReference = "Juan 19:28-30"
        ),

        // ==================== ETAPA 10: MAESTRO -> GRAN MAESTRO (Retos 46 al 50) ====================
        ChallengeNode(
            id = 46,
            title = "«En tus manos encomiendo mi espíritu» y el Centurión",
            subtitle = "Reto 46 • Historia de Jesús (Muerte en la Cruz)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "A las tres de la tarde (hora nona) de aquel Viernes Santo, Jesús clamó con voz potente su séptima y última palabra: «¡Padre, en tus manos encomiendo mi espíritu!», e inclinando la cabeza entregó el espíritu. El velo del Templo se rasgó en dos de arriba abajo. ¿Qué confesó el centurión romano que estaba frente a la Cruz al verlo morir así?",
            options = listOf(
                "«¡Verdaderamente este hombre era Hijo de Dios!»",
                "«Fue un prisionero común más del imperio»",
                "«Roma ha vencido para siempre»",
                "«Nadie recordará su nombre mañana»"
            ),
            correctOptionIndex = 0,
            explanation = "Un soldado pagano acostumbrado a ejecuciones quedó sobrecogido por el amor, el perdón y la majestad con que Jesús entregó su vida en la Cruz, reconociéndolo como el Hijo de Dios.",
            biblicalReference = "Lucas 23:44-46 • Marcos 15:38-39"
        ),
        ChallengeNode(
            id = 47,
            title = "La Lanza en el Costado y la Sepultura de Jesús",
            subtitle = "Reto 47 • Historia de Jesús (Tarde del Viernes Santo)",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Al atardecer del día en que lo crucificaron, como Jesús ya había muerto, no le quebraron las piernas (cumpliendo la Escritura sobre el Cordero Pascual), sino que un soldado le abrió el costado con una lanza, y al instante brotó sangre y agua. ¿Quiénes pidieron el cuerpo de Jesús a Pilato para sepultarlo en un sepulcro nuevo?",
            options = listOf(
                "José de Arimatea y Nicodemo (quien llevó mirra y áloe), envolviendo su cuerpo en una sábana limpia con la Virgen María y las santas mujeres",
                "Los soldados de la guardia de Herodes",
                "Caifás y Anás",
                "Poncio Pilato en persona"
            ),
            correctOptionIndex = 0,
            explanation = "Del costado abierto de Cristo en la Cruz brotaron sangre y agua (signo de la Eucaristía y el Bautismo), y José de Arimatea junto con Nicodemo vencieron el miedo para darle digna sepultura antes del sábado.",
            biblicalReference = "Juan 19:31-42"
        ),
        ChallengeNode(
            id = 48,
            title = "Pier Giorgio Frassati en la App: «Hacia lo Alto»",
            subtitle = "Reto 48 • Basado en la App: Chispas del Día",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En la segunda «Chispa del Día» de nuestra app («La aventura de la santidad») aparece San Pier Giorgio Frassati (1901-1925) junto con Filipenses 4:13. ¿Cómo define esa tarjeta de la app la fe católica para los jóvenes?",
            options = listOf(
                "«La fe católica no es una lista de prohibiciones aburridas para ancianos; es la aventura más audaz para corazones jóvenes que no se conforman con vidas mediocres»",
                "«La fe consiste en no hacer deporte ni tener amigos»",
                "«La santidad es imposible en el siglo XXI»",
                "«Vivir con Jesús es conformarse con el mínimo esfuerzo»"
            ),
            correctOptionIndex = 0,
            explanation = "Frassati era deportista, universitario alegre y servidor incansable de los pobres; su lema era «Verso l'alto» (Hacia lo alto).",
            biblicalReference = "App Kairós • Chispa 2 (Filipenses 4:13)"
        ),
        ChallengeNode(
            id = 49,
            title = "El Sentido del «Modo Sin Copia» en Kairós",
            subtitle = "Reto 49 • Jóvenes: Divertida y Razonable",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "En este apartado de Retos tienes 5 preguntas por día y el regreso al menú de inicio se bloquea hasta terminar tus 5 preguntas para que no copies. Pensándolo con humor y lógica juvenil, ¿por qué hacer trampa copiando respuestas es engañarse a uno mismo?",
            options = listOf(
                "Porque un rango ganado con trampa no vale nada: la verdadera victoria es aprender explorando la app antes de entrar y demostrar con honestidad lo que ya vive en tu mente y tu corazón",
                "Porque copiar te hace más sabio",
                "Porque la honestidad solo importa cuando alguien te vigila con cámaras",
                "Porque dar respuestas al azar sin leer es mejor"
            ),
            correctOptionIndex = 0,
            explanation = "Como dice la Chispa #3 de la app (Santa Teresita): «El que es fiel en lo muy poco, también en lo más es fiel» (Lucas 16:10).",
            biblicalReference = "App Kairós • Modo Sin Copia (Lucas 16:10)",
            dailyMissionCommitment = "Hoy actuaré con total honestidad y juego limpio en mis estudios, mi casa y mi fe."
        ),
        ChallengeNode(
            id = 50,
            title = "Duelo de Ascenso a GRAN MAESTRO 🏆",
            subtitle = "Reto 50 • Jefe de Rango (De la Cruz a la Victoria)",
            type = ChallengeNodeType.BOSS_RANK_UP,
            rankTier = FaithRankTier.MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "DUELO DE ASCENSO A GRAN MAESTRO: Aunque acompañamos a Jesús hasta el último día en que lo crucificaron y sepultaron el Viernes Santo, la Cruz no fue el punto final. Al amanecer del tercer día (Domingo), ¿qué encontraron las mujeres y los apóstoles en el sepulcro?",
            options = listOf(
                "La piedra corrida, el sepulcro vacío y el anuncio luminoso: «¿Por qué buscan entre los muertos al que vive? ¡No está aquí, ha resucitado como lo había dicho!»",
                "El sepulcro cerrado para siempre sin esperanza",
                "Que los discípulos habían olvidado a Jesús",
                "Que la muerte tuvo la última palabra"
            ),
            correctOptionIndex = 0,
            explanation = "¡Ascendiste a GRAN MAESTRO! El mismo Jesús que fue crucificado por amor venció a la muerte al tercer día y vive para siempre en medio de nosotros.",
            biblicalReference = "Lucas 24:1-7 • Marcos 16:6"
        ),

        // ==================== ETAPA 11: GRAN MAESTRO -> LEYENDA KAIRÓS (Retos 51 al 55) ====================
        ChallengeNode(
            id = 51,
            title = "Perfil, Cuentas y Modo Nocturno en Kairós",
            subtitle = "Reto 51 • Basado en la App: Tu Espacio",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.GRAN_MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En la app Kairós puedes personalizar tu perfil («Varon / Mujer» y «Mayor / Menor»), administrar cuentas registradas (cambiar o eliminar cuenta), ajustar el tamaño de letra de la Biblia y activar el Modo Nocturno. ¿Para qué fueron diseñadas todas estas funciones?",
            options = listOf(
                "Para ofrecerte una experiencia juvenil personalizada, accesible y cómoda para leer la Palabra de Dios y hacer tu examen de conciencia incluso antes de dormir",
                "Para complicar el uso de la aplicación",
                "Para bloquear la lectura de los capítulos bíblicos",
                "Solo por decoración sin utilidad práctica"
            ),
            correctOptionIndex = 0,
            explanation = "Cada detalle de Kairós está pensado para que tu encuentro diario con Dios sea cercano, personal y adaptado a tu ritmo.",
            biblicalReference = "App Kairós • Perfil y Modo Nocturno (Salmo 4:9)"
        ),
        ChallengeNode(
            id = 52,
            title = "Los Discípulos de Emaús y la Fracción del Pan",
            subtitle = "Reto 52 • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.GRAN_MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Después de que crucificaron a Jesús, dos discípulos caminaban desanimados hacia la aldea de Emaús diciendo: «Nosotros esperábamos que Él fuera el liberador». Jesús resucitado se acercó a caminar con ellos y les explicó las Escrituras. ¿Cuándo lo reconocieron y qué dijeron?",
            options = listOf(
                "Lo reconocieron al partir el pan en la mesa, y se dijeron: «¿No ardía nuestro corazón dentro de nosotros mientras nos hablaba en el camino y nos explicaba las Escrituras?»",
                "Nunca lo reconocieron y siguieron tristes",
                "Lo reconocieron porque llevaba una corona de oro física",
                "Se quedaron en Emaús sin contárselo a los Apóstoles"
            ),
            correctOptionIndex = 0,
            explanation = "El relato de Emaús (Lucas 24:13-35) es el espejo de cada Misa: primero escuchamos la Palabra que enciende el corazón y luego reconocemos a Jesús vivo en el Pan partido.",
            biblicalReference = "Lucas 24:13-35"
        ),
        ChallengeNode(
            id = 53,
            title = "Ser Luz entre Jóvenes sin Ser «Raro ni Intenso»",
            subtitle = "Reto 53 • Jóvenes: Divertida y Razonable",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.GRAN_MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "A veces alguien cree que para compartir la fe entre jóvenes hay que actuar como robot o regañar a todo el mundo. Pero según toda nuestra app Kairós (Bautismo, Confirmación, Eucaristía y Dilemas), ¿cómo brilla de verdad un joven católico hoy?",
            options = listOf(
                "Con alegría auténtica, buen humor, lealtad a toda prueba con sus amigos, mente abierta a la verdad y un corazón que sabe escuchar y amar sin juzgar",
                "Mirando a los demás por encima del hombro con cara de limón",
                "Ocultando su fe por vergüenza cuando está en grupo",
                "Discutiendo agresivamente en redes sociales"
            ),
            correctOptionIndex = 0,
            explanation = "Como decía San Juan Bosco (citado en los Dilemas de la app): «Estar siempre alegre, hacer el bien y dejar cantar a los gorriones».",
            biblicalReference = "App Kairós • Mateo 5:14-16",
            dailyMissionCommitment = "Hoy contagiaré alegría, respeto y paz verdadera a mis amigos y familia."
        ),
        ChallengeNode(
            id = 54,
            title = "El Mensaje Central de Kairós",
            subtitle = "Reto 54 • Basado en la App: Síntesis Total",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.GRAN_MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En el banner principal de inicio de la app («Hoy»), debajo de tu saludo personalizado, aparece el lema que resume toda la experiencia de Kairós. ¿Cuál es esa frase?",
            options = listOf(
                "«Tu fe no es una teoría: es tu mayor aventura.»",
                "«La fe es solo un libro de historia antigua.»",
                "«Cumple por obligación sin preguntar por qué.»",
                "«Cada quien está solo en el universo.»"
            ),
            correctOptionIndex = 0,
            explanation = "¡Atención al detalle nivel Leyenda! En el banner de inicio de Kairós siempre te recibe el lema: «Tu fe no es una teoría: es tu mayor aventura».",
            biblicalReference = "App Kairós • Pantalla Principal (Hoy)"
        ),
        ChallengeNode(
            id = 55,
            title = "Duelo Supremo Final: LEYENDA KAIRÓS 🦅",
            subtitle = "Reto 55 • Jefe Final (La Cruz de Jesús y la App)",
            type = ChallengeNodeType.BOSS_RANK_UP,
            rankTier = FaithRankTier.GRAN_MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "DUELO FINAL DE ASCENSO A LEYENDA KAIRÓS: Hemos recorrido todo lo que está en la app, los retos juveniles y la historia de Jesús hasta el último día en que lo crucificaron en el Calvario. Según las palabras de Jesús en Juan 15:13 y su entrega en la Cruz, ¿cuál es la prueba suprema del amor verdadero?",
            options = listOf(
                "«Nadie tiene amor más grande que el que da la vida por sus amigos»: amar a Dios sobre todas las cosas y entregarse por los demás como Jesús nos amó hasta el extremo en la Cruz",
                "Buscar únicamente la comodidad personal sin comprometerse con nadie",
                "Amar solo cuando no cuesta ningún esfuerzo",
                "Guardar la fe encerrada sin obras de amor"
            ),
            correctOptionIndex = 0,
            explanation = "¡¡FELICIDADES, LEYENDA CELESTIAL KAIRÓS 🦅👑!! Has superado las 55 preguntas de la app, la historia de Jesús hasta la Cruz y los retos juveniles, desbloqueando todos los rangos y emblemas.",
            biblicalReference = "Juan 15:13 • Juan 13:1 • Mateo 22:37-40",
            dailyMissionCommitment = "Vivo como Leyenda Kairós: unido a Jesús, fiel a la verdad y sirviendo con alegría cada día."
        )
    )

    // Banco adicional de preguntas de reemplazo cuando el usuario se equivoca en alguna de las 5 del día
    val extraReplacementQuestions: List<ChallengeNode> = listOf(
        ChallengeNode(
            id = 101,
            title = "Los 4 Puntos del Bautismo en Kairós",
            subtitle = "Pregunta de Reemplazo • App: Bautismo",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.BRONCE,
            difficulty = ChallengeDifficulty.BASICO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En el apartado «El Bautismo» de la app aparecen 4 tarjetas con los puntos clave de este sacramento. ¿Cuáles son esos 4 puntos?",
            options = listOf(
                "Identidad Inquebrantable, Borrón y Cuenta Nueva, Comunidad Global y Luz en la Oscuridad",
                "Fama, Dinero, Seguidores y Comodidad",
                "Silencio, Soledad, Miedo y Rutina",
                "Exámenes, Tareas, Reglas y Castigos"
            ),
            correctOptionIndex = 0,
            explanation = "¡Correcto! Esos son exactamente los 4 pilares explicados en la sección de Bautismo de Kairós.",
            biblicalReference = "App Kairós • Bautismo (Gálatas 3:26-27)"
        ),
        ChallengeNode(
            id = 102,
            title = "Los Reyes Magos de Oriente en Belén",
            subtitle = "Pregunta de Reemplazo • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.BRONCE,
            difficulty = ChallengeDifficulty.BASICO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Guiados por una estrella, unos sabios (Magos) de Oriente llegaron a Belén para adorar al Niño Jesús (Mateo 2:11). ¿Qué tres regalos le ofrecieron y qué significaban?",
            options = listOf(
                "Oro (como a Rey), Incienso (como a verdadero Dios) y Mirra (anunciando su humanidad y futura Pasión)",
                "Plata, bronce y piedras preciosas de Roma",
                "Pan, agua y sal del desierto",
                "Pergaminos griegos y espadas"
            ),
            correctOptionIndex = 0,
            explanation = "Desde su infancia en Belén, los dones de los Magos proclamaban a Jesús como Rey, Dios y Salvador que daría su vida.",
            biblicalReference = "Mateo 2:1-11"
        ),
        ChallengeNode(
            id = 103,
            title = "El «Detrás de Cámaras» de las Redes Sociales",
            subtitle = "Pregunta de Reemplazo • Jóvenes",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.BRONCE,
            difficulty = ChallengeDifficulty.BASICO,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "Abres Instagram y parece que todos tienen vidas de película en un yate mientras tú estás estudiando en pijama. Según la sección Bautismo de la app, ¿qué es lo más razonable para no caer en la trampa de compararte?",
            options = listOf(
                "Recordar que nadie sube sus fracasos a redes y que tu valor real es único e infinito ante Dios, no medido por apariencias",
                "Inventar una vida falsa en internet para impresionar a desconocidos",
                "Enojarte con tu familia por no tener un yate",
                "Pasar 8 horas comparándote con influencers"
            ),
            correctOptionIndex = 0,
            explanation = "Como descubrió Mateo en nuestra app: «Ya soy amado infinitamente por Dios; no tengo que actuar para agradar al algoritmo».",
            biblicalReference = "App Kairós • Bautismo (Salmo 139:14)"
        ),
        ChallengeNode(
            id = 104,
            title = "El Don de Ciencia y Piedad en la App",
            subtitle = "Pregunta de Reemplazo • App: Confirmación",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.PLATA,
            difficulty = ChallengeDifficulty.INTERMEDIO,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En la pantalla «La Confirmación» de la app, dentro de los 7 Dones del Espíritu Santo, ¿cómo se traducen al lenguaje joven los dones de «Ciencia» y «Piedad»?",
            options = listOf(
                "Ciencia como «Curiosidad iluminada: ver a Dios en el cosmos y el estudio» y Piedad como «Amistad real con Dios: hablarle con naturalidad sin poses»",
                "Ciencia como aprobar sin estudiar y Piedad como tener lástima",
                "Como dos materias escolares aburridas",
                "Como dones exclusivos para científicos mayores"
            ),
            correctOptionIndex = 0,
            explanation = "En Kairós cada uno de los 7 dones tiene su traducción juvenil práctica para vivirlo hoy.",
            biblicalReference = "App Kairós • Confirmación (Isaías 11:2)"
        ),
        ChallengeNode(
            id = 105,
            title = "Jesús Calma la Tempestad en el Mar",
            subtitle = "Pregunta de Reemplazo • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.PLATA,
            difficulty = ChallengeDifficulty.INTERMEDIO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Cruzando el mar de Galilea, se levantó un gran temporal y las olas cubrían la barca mientras Jesús dormía en la popa. Los discípulos lo despertaron asustados. ¿Qué hizo Jesús (Marcos 4:39)?",
            options = listOf(
                "Se levantó, increpó al viento y dijo al mar: «¡Silencio, enmudece!», y el viento cesó y sobrevino una gran calma",
                "Saltó de la barca y los dejó solos",
                "Les dijo que la tormenta nunca terminaría",
                "Pidió ayuda a otra embarcación romana"
            ),
            correctOptionIndex = 0,
            explanation = "Con Cristo en la barca de tu vida, ninguna tormenta tiene la última palabra.",
            biblicalReference = "Marcos 4:35-41"
        ),
        ChallengeNode(
            id = 106,
            title = "El Audio de 8 Minutos Cuando Estás Enojado",
            subtitle = "Pregunta de Reemplazo • Jóvenes",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.ORO,
            difficulty = ChallengeDifficulty.INTERMEDIO,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "Alguien te provoca por chat y estás a punto de mandar un audio furioso de 8 minutos del que te arrepentirás en media hora. Según la «Pausa de 2 Minutos» de la app y Santiago 1:19, ¿qué es lo más sabio?",
            options = listOf(
                "Soltar el celular, respirar hondo 2 minutos con Dios («Inhala paz... Exhala prisa...») y responder solo cuando tengas la mente en calma",
                "Mandar 15 mensajes en mayúsculas y bloquear a todo el mundo",
                "Publicar indirectas en tus estados cada 5 minutos",
                "Romper el teléfono del coraje"
            ),
            correctOptionIndex = 0,
            explanation = "Dos minutos de pausa consciente con Dios evitan palabras impulsivas que lastiman amistades.",
            biblicalReference = "App Kairós • Pausa (Santiago 1:19)"
        ),
        ChallengeNode(
            id = 107,
            title = "La Parábola del Buen Samaritano",
            subtitle = "Pregunta de Reemplazo • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ORO,
            difficulty = ChallengeDifficulty.DIFICIL,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Cuando le preguntaron a Jesús «¿Quién es mi prójimo?», contó la parábola de un hombre asaltado en el camino de Jerusalén a Jericó. Un sacerdote y un levita pasaron de largo, pero un samaritano se compadeció y curó sus heridas. ¿Qué enseñanza final dio Jesús?",
            options = listOf(
                "«Ve y haz tú lo mismo»: el amor al prójimo se demuestra con compasión activa hacia todo el que sufre, sin distinción",
                "Que solo debemos ayudar a quienes nos caen bien",
                "Que es mejor no detenerse nunca por nadie",
                "Que la caridad es solo dar consejos de lejos"
            ),
            correctOptionIndex = 0,
            explanation = "Jesús mismo es el verdadero Buen Samaritano de la humanidad que venda nuestras heridas y paga por nuestra salvación.",
            biblicalReference = "Lucas 10:29-37"
        ),
        ChallengeNode(
            id = 108,
            title = "La Transfiguración de Jesús en el Monte Tabor",
            subtitle = "Pregunta de Reemplazo • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.PLATINO,
            difficulty = ChallengeDifficulty.DIFICIL,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Poco antes de subir a Jerusalén para su Pasión y muerte en la Cruz, Jesús subió a un monte alto con Pedro, Santiago y Juan y se transfiguró ante ellos con rostro radiante como el sol. ¿Quiénes aparecieron hablando con Jesús sobre su partida en Jerusalén?",
            options = listOf(
                "Moisés y Elías, mientras desde la nube luminosa la voz del Padre decía: «Este es mi Hijo amado; escúchenlo»",
                "Abrahán y Noé",
                "Salomón e Isaías",
                "Juan Bautista y Zacarías"
            ),
            correctOptionIndex = 0,
            explanation = "Jesús mostró un destello de su gloria divina a sus amigos para fortalecer su fe antes del escándalo de la Cruz.",
            biblicalReference = "Lucas 9:28-36"
        ),
        ChallengeNode(
            id = 109,
            title = "La Unción en Betania Antes de la Pasión",
            subtitle = "Pregunta de Reemplazo • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.DIAMANTE,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Seis días antes de la Pascua en que fue crucificado, Jesús cenó en Betania. María tomó un perfume de nardo puro muy valioso, ungió los pies de Jesús y los secó con sus cabellos. Cuando Judas Iscariote protestó, ¿qué respondió Jesús?",
            options = listOf(
                "«Déjala; lo tenía guardado para el día de mi sepultura»",
                "Le dio la razón a Judas y rechazó el gesto de amor",
                "Pidió que vendieran la casa entera",
                "Se marchó de Betania enojado"
            ),
            correctOptionIndex = 0,
            explanation = "Mientras Judas calculaba el precio del dinero con codicia, María ofreció a Jesús un gesto de amor sin medida antes de su muerte.",
            biblicalReference = "Juan 12:1-8"
        ),
        ChallengeNode(
            id = 110,
            title = "Jesús ante el Sumo Sacerdote Caifás",
            subtitle = "Pregunta de Reemplazo • Historia de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ESMERALDA,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "En la noche en que fue arrestado en Getsemaní, llevaron a Jesús ante Caifás y el Sanedrín. Cuando Caifás le preguntó solemnemente: «¿Eres tú el Mesías, el Hijo del Bendito?», ¿qué respondió Jesús con valentía (Marcos 14:62)?",
            options = listOf(
                "«Yo soy, y verán al Hijo del hombre sentado a la derecha del Poder y venir entre las nubes del cielo»",
                "Negó ser el Mesías para que lo dejaran libre",
                "Guardó silencio sin revelar su identidad",
                "Pidió clemencia al tribunal"
            ),
            correctOptionIndex = 0,
            explanation = "Jesús proclamó abiertamente su divinidad ante el tribunal, entregando su vida por decir la verdad.",
            biblicalReference = "Marcos 14:61-64"
        ),
        ChallengeNode(
            id = 111,
            title = "El Silencio de Jesús ante Herodes Antipas",
            subtitle = "Pregunta de Reemplazo • Último Día de Jesús",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.ZAFIRO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "En la mañana del día en que lo crucificaron, Pilato envió a Jesús ante Herodes Antipas, quien quería verlo hacer algún milagro por pura curiosidad como si fuera un espectáculo de magia. ¿Cómo respondió Jesús ante Herodes (Lucas 23:8-11)?",
            options = listOf(
                "Jesús no le respondió ni una sola palabra ni hizo trucos para entretenerlo, soportando con dignidad las burlas antes de ser devuelto a Pilato",
                "Hizo tres milagros para complacer a la corte de Herodes",
                "Debatió durante dos horas con los soldados",
                "Le prometió un reino político"
            ),
            correctOptionIndex = 0,
            explanation = "Los milagros de Jesús brotan siempre de la compasión y para despertar la fe, jamás para alimentar la vanidad o el espectáculo.",
            biblicalReference = "Lucas 23:8-12"
        ),
        ChallengeNode(
            id = 112,
            title = "El Letrero de la Cruz en Tres Idiomas (INRI)",
            subtitle = "Pregunta de Reemplazo • El Último Día en la Cruz",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.HEROICO,
            difficulty = ChallengeDifficulty.EXPERTO,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Cuando crucificaron a Jesús en el Gólgota, Pilato redactó un letrero y lo puso sobre la Cruz: «Jesús Nazareno, el Rey de los Judíos» (INRI). ¿En qué tres idiomas estaba escrito según Juan 19:20?",
            options = listOf(
                "En hebreo (arameo), en latín y en griego, proclamando ante todas las culturas que Cristo reina desde la Cruz",
                "Únicamente en egipcio antiguo",
                "En persa y babilonio",
                "Solo en signos numéricos romanos"
            ),
            correctOptionIndex = 0,
            explanation = "El hebreo (lengua de la religión), el griego (lengua de la cultura) y el latín (lengua del imperio) anunciaban al mundo entero al verdadero Rey.",
            biblicalReference = "Juan 19:19-22"
        ),
        ChallengeNode(
            id = 113,
            title = "La Túnica sin Costura al Pie de la Cruz",
            subtitle = "Pregunta de Reemplazo • El Último Día en la Cruz",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.HISTORIA_DE_JESUS,
            promptOrQuestion = "Después de clavar a Jesús en la Cruz, los soldados tomaron su túnica, que era sin costura, tejida de una sola pieza de arriba abajo. ¿Qué decidieron hacer con ella cumpliendo la profecía del Salmo 22:19?",
            options = listOf(
                "Dijeron: «No la rompamos, sino echemos suertes a ver a quién le toca», símbolo de la unidad indivisible de la Iglesia de Cristo",
                "La cortaron en diez pedazos pequeños",
                "La quemaron junto al monte Calvario",
                "La devolvieron al Templo de Jerusalén"
            ),
            correctOptionIndex = 0,
            explanation = "San Juan vio en la túnica inconsútil (sin costura) de Jesús al pie de la Cruz el signo de la unidad de la Iglesia que nadie debe dividir.",
            biblicalReference = "Juan 19:23-24"
        ),
        ChallengeNode(
            id = 114,
            title = "El Don de Temor de Dios en Lenguaje Joven",
            subtitle = "Pregunta de Reemplazo • App: Confirmación",
            type = ChallengeNodeType.QUIZ_QUESTION,
            rankTier = FaithRankTier.GRAN_MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.CONTENIDO_APP,
            promptOrQuestion = "En el apartado «Confirmación» de nuestra app se explica el séptimo don del Espíritu Santo: «Temor de Dios». ¿Cómo lo define la app para los jóvenes?",
            options = listOf(
                "«Cero miedo al castigo; es el asombro reverente y el miedo sano a lastimar a Quien te ama con locura»",
                "Tener pánico y esconderse de Dios",
                "Pensar que Dios está esperando a que falles para castigarte",
                "No acercarse nunca a la oración"
            ),
            correctOptionIndex = 0,
            explanation = "El don de Temor de Dios es la delicadeza de un hijo que ama tanto a su Padre que cuida lo sagrado con todo el corazón.",
            biblicalReference = "App Kairós • Confirmación (7 Dones)"
        ),
        ChallengeNode(
            id = 115,
            title = "Fe Inteligente vs. «Lo Vi en un Video de 15 Segundos»",
            subtitle = "Pregunta de Reemplazo • Jóvenes",
            type = ChallengeNodeType.DAILY_ACTION,
            rankTier = FaithRankTier.GRAN_MAESTRO,
            difficulty = ChallengeDifficulty.LEYENDA,
            category = ChallengeCategory.JOVENES_DIVERTIDA,
            promptOrQuestion = "Ves un video viral con música misteriosa inventando teorías falsas sobre Jesús o la Iglesia. Según el consejo práctico del Dilema «Dudas de Fe» en la app Kairós, ¿qué hace un joven con criterio propio?",
            options = listOf(
                "No tragarse mitos de videos de 15 segundos sin contrastar fuentes: investigar en la Biblia Católica, el YouCat o autores serios como C.S. Lewis",
                "Creer cualquier rumor de internet sin verificar nada",
                "Dejar de pensar y abandonar sus convicciones por moda",
                "Compartir noticias falsas sin leerlas"
            ),
            correctOptionIndex = 0,
            explanation = "Como enseña la app: «Dudar con honestidad e investigar en fuentes serias es el umbral de una fe mucho más sólida».",
            biblicalReference = "App Kairós • Dilemas: Crecimiento"
        )
    )

    fun getChallengeById(id: Int): ChallengeNode {
        return challenges.find { it.id == id } ?: challenges.first()
    }

    /**
     * Cuando el usuario se equivoca en una de las 5 preguntas del día, devuelve automáticamente
     * otra pregunta distinta (y así sucesivamente en cada fallo hasta que responda la correcta).
     */
    fun getReplacementQuestion(
        originalChallengeId: Int,
        failedAttemptCount: Int,
        alreadySeenPrompts: Set<String> = emptySet()
    ): ChallengeNode {
        val original = getChallengeById(originalChallengeId)
        val combinedPool = extraReplacementQuestions + challenges.filter { it.id != originalChallengeId }
        val unseenPool = combinedPool.filter { it.promptOrQuestion !in alreadySeenPrompts }
        val candidateList = if (unseenPool.isNotEmpty()) unseenPool else combinedPool
        val safeIndex = ((originalChallengeId * 5) + failedAttemptCount.coerceAtLeast(1) - 1) % candidateList.size
        val picked = candidateList[safeIndex]

        return picked.copy(
            id = original.id,
            type = original.type,
            rankTier = original.rankTier,
            difficulty = original.difficulty,
            subtitle = "Pregunta #${original.id} (Nueva pregunta por fallo #$failedAttemptCount) • ${picked.category.label}",
            dailyMissionCommitment = null
        )
    }

    fun getUnlockedEmblems(completedCount: Int): List<FaithEmblem> {
        return specialEmblems.filter { completedCount >= it.requiredChallenges }
    }
}
