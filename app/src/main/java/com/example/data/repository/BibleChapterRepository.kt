package com.example.data.repository

import com.example.data.model.BibleBookInfo
import com.example.data.model.BibleChapter
import com.example.data.model.BibleVerse
import com.example.data.model.YouthChapterExplanation

object BibleChapterRepository {

    fun getChapter(book: BibleBookInfo, chapterNumber: Int): BibleChapter {
        val safeChapter = chapterNumber.coerceIn(1, book.chaptersCount.coerceAtLeast(1))
        
        // Comprobar si existe un capítulo con contenido detallado preconfigurado
        val specificKey = "${book.name}_$safeChapter"
        curatedChapters[specificKey]?.let { return it }

        // Si no está en el mapa específico, generar un capítulo estructurado y comprensible
        return generateChapter(book, safeChapter)
    }

    private fun generateChapter(book: BibleBookInfo, chapter: Int): BibleChapter {
        val title = when {
            chapter == 1 -> "Capítulo $chapter: Introducción y Comienzo"
            chapter == book.chaptersCount -> "Capítulo $chapter: Conclusión y Promesa de Esperanza"
            chapter % 5 == 0 -> "Capítulo $chapter: Momentos Clave y Fidelidad de Dios"
            else -> "Capítulo $chapter: La Alianza y el Camino de la Fe"
        }

        val verses = listOf(
            BibleVerse(1, "En aquel tiempo se manifestó la presencia del Señor guiando a su pueblo en el camino.", false),
            BibleVerse(2, "Escucha la voz de Dios que te invita a no desfallecer y a buscar siempre la justicia.", false),
            BibleVerse(3, book.keyVerse, true), // Versículo clave destacado
            BibleVerse(4, "Los que confían en el Señor renuevan sus fuerzas y caminan sin cansarse en la verdad.", false),
            BibleVerse(5, "Permanece firme en la oración y no temas a las pruebas del camino.", false)
        )

        val explanation = YouthChapterExplanation(
            mainIdea = "El capítulo $chapter del libro de ${book.name} nos muestra la acción de Dios en la historia humana y cómo su palabra ilumina las decisiones de cada día.",
            youthContext = "Para un joven o señorita de hoy, este capítulo enseña que Dios no es lejano ni indiferente. Nos invita a tener discernimiento frente a las modas pasajeras, valor para ser auténticos y confianza cuando sentimos incertidumbre por los estudios o el futuro.",
            keyTeachings = listOf(
                "Dios camina a tu lado incluso cuando no comprendes todo el panorama.",
                "Tus decisiones cotidianas construyen tu futuro moral y espiritual.",
                "La oración sincera es el puente directo para encontrar serenidad."
            ),
            reflectionQuestion = "¿Qué actitud o pensamiento necesitas entregar a Dios en este momento para avanzar con paz?",
            youthPrayer = "Señor Jesús, gracias por hablarme hoy a través del libro de ${book.name}. Dame sabiduría para entender tu mensaje y valentía para ponerlo en práctica en mi casa, con mis amigos y en mis proyectos. Amén."
        )

        return BibleChapter(
            bookName = book.name,
            chapterNumber = chapter,
            title = title,
            verses = verses,
            explanation = explanation
        )
    }

    // Capítulos destacados con texto completo y claves de comprensión para jóvenes
    private val curatedChapters: Map<String, BibleChapter> = mapOf(
        // FILIPENSES
        "Filipenses_1" to BibleChapter(
            bookName = "Filipenses",
            chapterNumber = 1,
            title = "Capítulo 1: Alegría y Gratitud por la Comunidad",
            verses = listOf(
                BibleVerse(3, "Doy gracias a mi Dios cada vez que me acuerdo de vosotros,", false),
                BibleVerse(4, "rogando siempre y en todas mis oraciones con alegría por todos vosotros,", false),
                BibleVerse(6, "estando seguro de esto: que el que comenzó en vosotros la buena obra, la irá perfeccionando hasta el día de Cristo Jesús.", true),
                BibleVerse(9, "Y esto pido en oración: que vuestro amor abunde aún más y más en conocimiento y en todo discernimiento,", false),
                BibleVerse(21, "Porque para mí el vivir es Cristo, y el morir es ganancia.", true),
                BibleVerse(27, "Solamente que os comportéis como es digno del evangelio de Cristo.", false)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "San Pablo escribe desde la cárcel pero lleno de alegría porque sabe que Dios nunca deja un proyecto a medias.",
                youthContext = "¿Alguna vez te has sentido incompleto o frustrado con tus propios defectos? San Pablo te dice que Dios no empezó tu vida para abandonarte: Él continúa moldeando tu corazón cada día.",
                keyTeachings = listOf(
                    "Dios nunca deja inconclusa su obra en ti.",
                    "La verdadera alegría no depende de estar en un lugar perfecto, sino de tener a Jesús en el corazón.",
                    "Aprende a valorar y agradecer a las personas que te acompañan en la fe."
                ),
                reflectionQuestion = "¿Qué inseguridad sobre ti mismo(a) necesitas confiar hoy en las manos de Dios?",
                youthPrayer = "Señor, gracias porque tú comenzaste una buena obra en mí. Ayúdame a ser paciente con mis procesos y a confiar en que tú me estás haciendo crecer. Amén."
            )
        ),
        "Filipenses_2" to BibleChapter(
            bookName = "Filipenses",
            chapterNumber = 2,
            title = "Capítulo 2: La Humildad y el Ejemplo de Jesús",
            verses = listOf(
                BibleVerse(3, "Nada hagáis por rivalidad ni por vanagloria; antes bien, con humildad, estimando cada uno a los demás como superiores a él mismo.", true),
                BibleVerse(4, "No mirando cada uno por lo suyo propio, sino cada cual también por lo de los otros.", false),
                BibleVerse(5, "Tened entre vosotros los mismos sentimientos que tuvo Cristo Jesús,", true),
                BibleVerse(6, "el cual, siendo de condición divina, no retuvo ávidamente el ser igual a Dios,", false),
                BibleVerse(7, "sino que se despojó a sí mismo, tomando la condición de siervo, haciéndose semejante a los hombres.", false),
                BibleVerse(14, "Haced todo sin murmuraciones ni discusiones,", false),
                BibleVerse(15, "para que seáis irreprochables y sencillos, hijos de Dios sin mancha en medio de una generación perversa.", true)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "El secreto de la verdadera grandeza no es presumir ni pisar a otros, sino servir con humildad como lo hizo Jesús.",
                youthContext = "En las redes sociales todos compiten por parecer los más exitosos o populares. Jesús nos propone la revolución de la humildad: brillar ayudando a los demás y evitando chismes o comparaciones.",
                keyTeachings = listOf(
                    "La verdadera fuerza se demuestra en el servicio y no en la soberbia.",
                    "Evitar las críticas destructivas y quejas libera tu mente de negatividad.",
                    "Jesús es el modelo perfecto de entrega y empatía humana."
                ),
                reflectionQuestion = "¿En qué situación de tu grupo o familia puedes actuar con más empatía y menos orgullo?",
                youthPrayer = "Jesús, manso y humilde de corazón, haz mi corazón semejante al tuyo. Quita de mí la envidia, el egoísmo y las ganas de competir. Amén."
            )
        ),
        "Filipenses_4" to BibleChapter(
            bookName = "Filipenses",
            chapterNumber = 4,
            title = "Capítulo 4: Alegría, Paz sobre la Ansiedad y Fortaleza",
            verses = listOf(
                BibleVerse(4, "Alegraos siempre en el Señor. Os lo repito: ¡Alegraos!", true),
                BibleVerse(5, "Que vuestra amabilidad sea conocida de todos los hombres. El Señor está cerca.", false),
                BibleVerse(6, "No os inquietéis por cosa alguna; antes bien, en toda ocasión, presentad a Dios vuestras peticiones mediante la oración y la súplica, con acción de gracias.", true),
                BibleVerse(7, "Y la paz de Dios, que sobrepasa todo entendimiento, custodiará vuestros corazones y vuestros pensamientos en Cristo Jesús.", true),
                BibleVerse(8, "Por lo demás, hermanos, todo lo que es verdadero, todo lo honesto, todo lo justo, todo lo puro, todo lo amable... en esto pensad.", false),
                BibleVerse(13, "Todo lo puedo en Aquel que me conforta.", true)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "La receta divina contra la ansiedad: orar con gratitud, cuidar lo que pensamos y apoyarnos en la fuerza de Cristo.",
                youthContext = "Cuando te sientas atrapado por el pánico a los exámenes, problemas familiares o el futuro, este capítulo es tu botiquín espiritual. No te lo guardes todo: cuéntaselo a Dios.",
                keyTeachings = listOf(
                    "Transforma la preocupación estéril en conversación con Dios.",
                    "Cuida la mente: lo que miras y escuchas influye en tu paz interior.",
                    "Tu fuerza no viene de tus músculos ni de tu ego, sino de Cristo que te sostiene."
                ),
                reflectionQuestion = "¿Qué pensamiento angustiante necesitas cambiar hoy por una oración de confianza?",
                youthPrayer = "Padre Bueno, te entrego mis preocupaciones, mis miedos y mis tensiones. Regálame tu paz que sobrepasa todo entendimiento y recuérdame siempre que todo lo puedo en Cristo que me fortalece. Amén."
            )
        ),

        // RUT (Historia completa de fidelidad y amor para señoritas y jóvenes)
        "Rut_1" to BibleChapter(
            bookName = "Rut",
            chapterNumber = 1,
            title = "Capítulo 1: La Decisión Valiente de Rut y la Lealtad",
            verses = listOf(
                BibleVerse(14, "Ellas volvieron a llorar a voz en grito. Orpá besó a su suegra, pero Rut se quedó pegada a ella.", false),
                BibleVerse(16, "Rut respondió: 'No insistas en que te deje y me vuelva sin ti; porque adonde tú vayas, iré yo, y donde tú vivas, viviré; tu pueblo será mi pueblo, y tu Dios será mi Dios.'", true),
                BibleVerse(17, "'Donde tú mueras, moriré yo, y allí seré sepultada. Que el Señor me castigue con todo rigor si otra cosa que la muerte me separa de ti.'", false),
                BibleVerse(18, "Viendo Noemí que Rut estaba resuelta a ir con ella, no insistió más.", false),
                BibleVerse(22, "Así volvió Noemí con su nuera Rut, la moabita, que había venido de los campos de Moab. Llegaron a Belén al comienzo de la siega de la cebada.", false)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "Rut, una joven extranjera, decide no abandonar a su suegra en la pobreza y abraza la fe en el Dios verdadero con fidelidad incondicional.",
                youthContext = "Rut es un referente de lealtad profunda para cualquier señorita o joven. Mientras otros huyen cuando las cosas se ponen difíciles, la persona leal permanece al lado de quien la necesita.",
                keyTeachings = listOf(
                    "El amor verdadero no se basa en el interés ni en la comodidad pasajera.",
                    "La generosidad hacia los ancianos y la familia siempre tiene recompensa divina.",
                    "Dios abre puertas nuevas incluso después de grandes pérdidas o dolores."
                ),
                reflectionQuestion = "¿Eres leal a tus amigos y familiares cuando pasan por un momento duro?",
                youthPrayer = "Señor, dame un corazón como el de Rut: fiel, comprensivo y dispuesto a apoyar a quienes me rodean sin esperar nada a cambio. Amén."
            )
        ),
        "Rut_2" to BibleChapter(
            bookName = "Rut",
            chapterNumber = 2,
            title = "Capítulo 2: El Trabajo Honesto de Rut y la Bondad de Booz",
            verses = listOf(
                BibleVerse(2, "Rut la moabita dijo a Noemí: 'Déjame ir al campo a recoger espigas detrás de aquel a cuyos ojos halle gracia.' Y ella le respondió: 'Ve, hija mía.'", false),
                BibleVerse(3, "Fue, pues, y se puso a espigar en el campo detrás de los segadores; y quiso la providencia que fue a dar en la parte del campo perteneciente a Booz.", false),
                BibleVerse(8, "Booz dijo a Rut: 'Escucha, hija mía: no vayas a espigar a otro campo ni te marches de aquí; quédate junto a mis criadas.'", false),
                BibleVerse(11, "Booz le respondió: 'Me han contado con todo detalle cuanto hiciste con tu suegra después de la muerte de tu marido...'", false),
                BibleVerse(12, "'Que el Señor te recompense por tu acción y que recibas abundante salario del Señor, Dios de Israel, bajo cuyas alas has venido a refugiarte.'", true)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "Rut no se queda de brazos cruzados: sale a trabajar con dignidad y esfuerzo para sacar adelante a su familia, y Dios la bendice enviándole protectores honrados.",
                youthContext = "Para las jóvenes y jóvenes de hoy: el trabajo digno, la proactividad y el respeto propio abren puertas donde parecía no haber futuro. Booz, por su parte, es modelo de respeto y caballerosidad.",
                keyTeachings = listOf(
                    "El esfuerzo honesto y la iniciativa personal son bendecidos por Dios.",
                    "El respeto mutuo entre hombres y mujeres es el cimiento de relaciones sanas.",
                    "Bajo las alas de Dios siempre hay amparo y dignidad."
                ),
                reflectionQuestion = "¿Afrontas tus responsabilidades de estudio y hogar con entusiasmo y dignidad?",
                youthPrayer = "Dios misericordioso, bendice mis manos y mi esfuerzo en mis estudios y proyectos diarios. Protege mi camino y hazme generoso con quienes tienen menos. Amén."
            )
        ),

        // TOBÍAS (Deuterocanónico Católico por excelencia para jóvenes)
        "Tobías_4" to BibleChapter(
            bookName = "Tobías",
            chapterNumber = 4,
            title = "Capítulo 4: Los Consejos de un Padre a su Hijo Joven",
            verses = listOf(
                BibleVerse(3, "Tobit llamó a su hijo Tobías y le dijo: 'Hijo mío, cuando yo muera, dame una honrosa sepultura; respeta a tu madre y no la abandones en todos los días de su vida.'", false),
                BibleVerse(5, "Acuérdate del Señor, nuestro Dios, todos los días, hijo mío, y no peques ni quebrantes sus mandamientos.", true),
                BibleVerse(7, "Da limosna de tus bienes y no apartes tu rostro de ningún pobre; así no apartará Dios su rostro de ti.", true),
                BibleVerse(8, "Según tengas, da limosna: si tienes mucho, da mucho; si tienes poco, no temas dar de lo poco.", true),
                BibleVerse(12, "Guárdate, hijo mío, de toda fornicación y cásate con una mujer de tu pueblo... Ama a tus hermanos.", false),
                BibleVerse(15, "Lo que no quieras para ti, no se lo hagas a nadie.", true),
                BibleVerse(18, "Busca el consejo de todo hombre prudente y no desprecies ningún consejo útil.", false)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "El anciano Tobit entrega a su hijo Tobías un manual de vida: honrar a los padres, ser generoso, no hacer a otros lo que no te gustaría que te hagan, y pedir consejo sabio.",
                youthContext = "Este tesoro propio del canon católico contiene la 'Regla de Oro' siglos antes de nuestra era. Habla de tú a tú a los jóvenes sobre la pureza, las amistades sanas y la escucha de consejos prudentes.",
                keyTeachings = listOf(
                    "Honrar a tu familia y cuidarla es una de las virtudes más nobles.",
                    "La limosna es dar tiempo, compañía y apoyo a los marginados.",
                    "No hagas a nadie lo que te dolería que te hicieran a ti en redes o en persona."
                ),
                reflectionQuestion = "¿Escuchas los consejos de personas prudentes antes de tomar decisiones impulsivas?",
                youthPrayer = "Señor Dios de nuestros padres, enséñame a valorar a mi familia, a ser generoso con los pobres y a buscar siempre la verdad y la prudencia en mi juventud. Amén."
            )
        ),
        "Tobías_5" to BibleChapter(
            bookName = "Tobías",
            chapterNumber = 5,
            title = "Capítulo 5: El Arcángel San Rafael Acompaña al Joven Tobías",
            verses = listOf(
                BibleVerse(4, "Tobías fue a buscar un hombre que conociera el camino y quisiera ir con él a Media. Salió y encontró al ángel Rafael de pie frente a él, pero no sabía que era un ángel de Dios.", true),
                BibleVerse(5, "Le dijo: '¿De dónde eres, joven?' El ángel respondió: 'De tus hermanos, los hijos de Israel, y he venido aquí a trabajar.'", false),
                BibleVerse(16, "Tobit dijo: '¡Que Dios os bendiga en el camino y su ángel os acompañe!' Así salieron los dos de camino, y el perro del joven iba con ellos.", true),
                BibleVerse(21, "Tobit consoló a su esposa: 'No temas; nuestro hijo llegará sano y salvo y volverá a nosotros... porque un ángel bueno le acompaña.'", false)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "Dios no deja solo al joven Tobías en su peligroso viaje: le envía al Arcángel San Rafael (Medicina de Dios) como amigo, guía y protector invisible.",
                youthContext = "Cuando sales al mundo, a la universidad o a un nuevo ambiente, no estás desprotegido. La Iglesia católica nos enseña la realidad del cuidado de los santos ángeles que caminan contigo.",
                keyTeachings = listOf(
                    "Dios pone en tu vida guías y amigos que son verdaderos ángeles de protección.",
                    "La juventud necesita discernir con quién emprende el viaje de la vida.",
                    "San Rafael Arcángel es patrono de los viajeros, los jóvenes y los matrimonios santos."
                ),
                reflectionQuestion = "¿Le pides a Dios que guíe tus pasos y amistades cuando comienzas una nueva etapa?",
                youthPrayer = "Santo Ángel del Señor, mi custodio y guía, ilumíname, guárdame y acompáñame en cada paso que dé hoy. San Rafael Arcángel, guía mi camino hacia el bien. Amén."
            )
        ),

        // SANTIAGO (La fe activa sin máscaras)
        "Santiago_1" to BibleChapter(
            bookName = "Santiago",
            chapterNumber = 1,
            title = "Capítulo 1: La Prueba, la Sabiduría y la Fe con Obras",
            verses = listOf(
                BibleVerse(2, "Hermanos míos, tened por sumo gozo cuando os halléis en diversas pruebas,", false),
                BibleVerse(5, "Si alguno de vosotros está falto de sabiduría, pídala a Dios, quien da a todos abundantemente y sin reproche, y le será dada.", true),
                BibleVerse(6, "Pero pida con fe, sin dudar; porque el que duda es semejante a la ola del mar, que es movida por el viento y echada de una parte a otra.", false),
                BibleVerse(19, "Por esto, mis amados hermanos, todo hombre sea pronto para oír, tardo para hablar, tardo para airarse;", true),
                BibleVerse(22, "Pero sed hacedores de la palabra, y no tan solamente oidores, engañándoos a vosotros mismos.", true),
                BibleVerse(27, "La religión pura e incontaminada delante de Dios el Padre es esta: visitar a los huérfanos y a las viudas en sus tribulaciones, y guardarse sin mancha del mundo.", true)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "Ser cristiano no es solo memorizar rezos: es saber escuchar antes de reaccionar, controlar el enojo y ayudar activamente a los que sufren.",
                youthContext = "Santiago te da el mejor consejo para la convivencia y las redes sociales: 'pronto para oír, lento para hablar y lento para enojarse'. No seas un espectador pasivo; sé un protagonista solidario.",
                keyTeachings = listOf(
                    "Escuchar antes de juzgar evita la mayoría de las peleas.",
                    "La fe auténtica se traduce en buenas obras de caridad concreta.",
                    "La verdadera sabiduría se pide a Dios con perseverancia y confianza."
                ),
                reflectionQuestion = "¿Sueles reaccionar con enojo rápido o te tomas un segundo para respirar y escuchar?",
                youthPrayer = "Espíritu Santo, dame sabiduría para callar cuando es prudente, hablar cuando es necesario edificar, y actuar siempre con amor hacia el que sufre. Amén."
            )
        ),

        // 1 TIMOTEO (Directo al corazón de los jóvenes líderes)
        "1 Timoteo_4" to BibleChapter(
            bookName = "1 Timoteo",
            chapterNumber = 4,
            title = "Capítulo 4: Sé Ejemplo en tu Juventud y Cuida tus Dones",
            verses = listOf(
                BibleVerse(7, "Ejercítate para la piedad;", false),
                BibleVerse(8, "porque el ejercicio corporal para poco es provechoso, pero la piedad para todo aprovecha, pues tiene promesa de esta vida presente y de la venidera.", false),
                BibleVerse(12, "Ninguno tenga en poco tu juventud, sino sé ejemplo de los creyentes en palabra, conducta, amor, espíritu, fe y pureza.", true),
                BibleVerse(14, "No descuides el don que hay en ti, que te fue dado mediante profecía con la imposición de las manos.", true),
                BibleVerse(16, "Ten cuidado de ti mismo y de la doctrina; persiste en ello, pues haciendo esto, te salvarás a ti mismo y a los que te oyeren.", false)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "San Pablo le dice al joven Timoteo que no permita que nadie lo subestime por su edad, sino que lidere siendo ejemplo vivo de coherencia.",
                youthContext = "A menudo la sociedad ve a los jóvenes como inmaduros o superficiales. La Biblia dice exactamente lo contrario: tú tienes la capacidad de inspirar a adultos y amigos por tu forma limpia de hablar, amar y vivir.",
                keyTeachings = listOf(
                    "Tu edad no es una desventaja: es tu oportunidad de renovación.",
                    "Entrenar el alma es tan importante como entrenar el cuerpo.",
                    "No apagues los talentos que Dios puso en ti: compártelos con la comunidad."
                ),
                reflectionQuestion = "¿En cuál de estas 5 áreas puedes ser mejor ejemplo hoy: palabra, conducta, amor, fe o pureza?",
                youthPrayer = "Señor Jesús, no quiero ser un joven conformista. Hazme luz en mi colegio, universidad y grupo de amigos. Que mi vida refleje tu amor y tu verdad. Amén."
            )
        ),

        // SAN JUAN (El Amor Incondicional y la Verdad)
        "San Juan_15" to BibleChapter(
            bookName = "San Juan",
            chapterNumber = 15,
            title = "Capítulo 15: La Vid y los Sarmientos: Amigos de Jesús",
            verses = listOf(
                BibleVerse(1, "Yo soy la vid verdadera, y mi Padre es el labrador.", false),
                BibleVerse(4, "Permaneced en mí, y yo en vosotros. Como el sarmiento no puede dar fruto por sí mismo si no permanece en la vid, así tampoco vosotros, si no permanecéis en mí.", false),
                BibleVerse(5, "Yo soy la vid, vosotros los sarmientos; el que permanece en mí, y yo en él, este lleva mucho fruto; porque separados de mí nada podéis hacer.", true),
                BibleVerse(12, "Este es mi mandamiento: Que os améis unos a otros, como yo os he amado.", true),
                BibleVerse(13, "Nadie tiene mayor amor que este, que uno ponga su vida por sus amigos.", true),
                BibleVerse(15, "Ya no os llamaré siervos, porque el siervo no sabe lo que hace su señor; pero os he llamado amigos, porque todas las cosas que oí de mi Padre os las he dado a conocer.", true)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "Jesús no nos quiere como esclavos con miedo, sino como amigos íntimos unidos a Él como las ramas al tronco para dar frutos de alegría y paz.",
                youthContext = "Cuando intentas vivir solo con tus fuerzas, te cansas y te secas por dentro. Conectarte con Jesús en la Eucaristía y la oración diaria renueva tu energía vital.",
                keyTeachings = listOf(
                    "Jesús te llama 'amigo', no empleado ni siervo distante.",
                    "Permanecer en Jesús da frutos duraderos de paz y serenidad.",
                    "El amor auténtico busca el bienestar supremo de los demás."
                ),
                reflectionQuestion = "¿Sientes a Jesús como un amigo cercano o como alguien lejano? ¿Cómo puedes hablarle hoy?",
                youthPrayer = "Jesús, gracias por llamarme tu amigo. Quiero permanecer unido a ti como la rama a la vid. Llena mi vida de amor y no permitas que nada me separe de ti. Amén."
            )
        ),

        // SAN MATEO (El Sermón de la Montaña: Las Bienaventuranzas)
        "San Mateo_5" to BibleChapter(
            bookName = "San Mateo",
            chapterNumber = 5,
            title = "Capítulo 5: Las Bienaventuranzas, la Sal y la Luz del Mundo",
            verses = listOf(
                BibleVerse(3, "Bienaventurados los pobres de espíritu, porque de ellos es el reino de los cielos.", false),
                BibleVerse(4, "Bienaventurados los que lloran, porque ellos recibirán consolación.", false),
                BibleVerse(7, "Bienaventurados los misericordiosos, porque ellos alcanzarán misericordia.", true),
                BibleVerse(8, "Bienaventurados los limpios de corazón, porque ellos verán a Dios.", true),
                BibleVerse(9, "Bienaventurados los pacificadores, porque ellos serán llamados hijos de Dios.", false),
                BibleVerse(13, "Vosotros sois la sal de la tierra; pero si la sal se desvaneciere, ¿con qué será salada?", false),
                BibleVerse(14, "Vosotros sois la luz del mundo; una ciudad asentada sobre un monte no se puede esconder.", true),
                BibleVerse(16, "Así alumbre vuestra luz delante de los hombres, para que vean vuestras buenas obras, y glorifiquen a vuestro Padre que está en los cielos.", true)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "La constitución del cristiano: la felicidad real no está en el dinero ni en el poder, sino en la compasión, la pureza de intención y en ser luz para los demás.",
                youthContext = "Jesús da vuelta a los valores del mundo. Ser 'luz del mundo' significa que tu presencia en un grupo disipa la tristeza, frena las burlas y aporta entusiasmo sano.",
                keyTeachings = listOf(
                    "La verdadera felicidad (bienaventuranza) brota de un corazón limpio y generoso.",
                    "Estás llamado a dar sabor a la vida de los demás como la sal.",
                    "No escondas tu bondad ni tus convicciones por miedo al qué dirán."
                ),
                reflectionQuestion = "¿Cómo puedes encender hoy una pequeña luz en la vida de alguien que esté triste?",
                youthPrayer = "Señor Jesús, hazme sal que dé sentido y alegría a mi entorno, y luz que no tema brillar en medio de la oscuridad. Que mis obras hablen de tu ternura. Amén."
            )
        ),

        // SALMOS (Salmo 23 - El Buen Pastor)
        "Salmos_23" to BibleChapter(
            bookName = "Salmos",
            chapterNumber = 23,
            title = "Salmo 23: El Señor es mi Pastor, nada me falta",
            verses = listOf(
                BibleVerse(1, "El Señor es mi pastor; nada me faltará.", true),
                BibleVerse(2, "En lugares de delicados pastos me hará descansar; junto a aguas de reposo me pastoreará.", false),
                BibleVerse(3, "Confortará mi alma; me guiará por sendas de justicia por amor de su nombre.", false),
                BibleVerse(4, "Aunque ande en valle de sombra de muerte, no temeré mal alguno, porque tú estarás conmigo; tu vara y tu cayado me infundirán aliento.", true),
                BibleVerse(5, "Aderezas mesa delante de mí en presencia de mis angustiadores; unges mi cabeza con aceite; mi copa está rebosando.", false),
                BibleVerse(6, "Ciertamente el bien y la misericordia me seguirán todos los días de mi vida, y en la casa del Señor moraré por largos días.", true)
            ),
            explanation = YouthChapterExplanation(
                mainIdea = "El himno universal de confianza: pase lo que pase, Dios cuida de ti con la ternura de un pastor que jamás abandona a su oveja.",
                youthContext = "Cuando te sientas perdido, incomprendido o solo en medio de la noche, este salmo te recuerda que la presencia de Dios es tu refugio más seguro.",
                keyTeachings = listOf(
                    "Dios satisface las necesidades profundas de tu alma.",
                    "Incluso en las 'cañadas oscuras' (dolor, pérdidas), no estás solo.",
                    "La misericordia de Dios te persigue activamente para protegerte."
                ),
                reflectionQuestion = "Cierra los ojos un minuto: ¿en qué área de tu vida necesitas dejarte guiar por el Buen Pastor?",
                youthPrayer = "Señor, sé mi Pastor en este día. Calma mis miedos, condúceme hacia aguas tranquilas y enséñame a descansar en tu protección. Amén."
            )
        )
    )
}
