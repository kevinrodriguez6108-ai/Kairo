package com.example.data.repository

import com.example.data.model.DailySpark
import com.example.data.model.Dilemma
import com.example.data.model.PrayerItem
import com.example.data.model.SacramentType

object ContentRepository {

    val dailySparks = listOf(
        DailySpark(
            title = "Originales, no fotocopias",
            biblicalVerse = "\"No temas, porque yo te he redimido; te he llamado por tu nombre, mío eres tú.\" — Isaías 43:1",
            reflection = "En un mundo que te presiona para copiar tendencias, estéticas y opiniones prestadas, tu mayor revolución es ser tú mismo con Dios. No naciste para encajar en el molde de nadie: Dios pensó en ti con un brillo irrepetible.",
            authorOrSaint = "Beato Carlo Acutis (1991 - 2006)",
            microMission = "Haz hoy una buena acción anónima por alguien sin publicar nada en redes ni contárselo a nadie."
        ),
        DailySpark(
            title = "La aventura de la santidad",
            biblicalVerse = "\"Todo lo puedo en Cristo que me fortalece.\" — Filipenses 4:13",
            reflection = "La fe católica no es una lista de prohibiciones aburridas para ancianos; es la aventura más audaz para corazones jóvenes que no se conforman con vidas mediocres. Quien vive con Jesús vive al máximo.",
            authorOrSaint = "San Pier Giorgio Frassati (1901 - 1925)",
            microMission = "Dedica 2 minutos de silencio absoluto antes de dormir para agradecer tres cosas concretas de tu día."
        ),
        DailySpark(
            title = "Pequeños gestos, infinito amor",
            biblicalVerse = "\"El que es fiel en lo muy poco, también en lo más es fiel.\" — Lucas 16:10",
            reflection = "No necesitas realizar hazañas extraordinarias para cambiar el mundo. Una sonrisa sincera a quien está triste, escuchar con atención y perdonar con prontitud tienen un peso eterno en el corazón de Dios.",
            authorOrSaint = "Santa Teresita del Niño Jesús",
            microMission = "Mándale un mensaje a un amigo o familiar con el que hace tiempo no hablas para preguntarle cómo está de verdad."
        ),
        DailySpark(
            title = "La libertad auténtica",
            biblicalVerse = "\"La verdad os hará libres.\" — Juan 8:32",
            reflection = "Libertad no es hacer cualquier capricho que te esclavice después, sino tener la fuerza interior de elegir siempre lo que construye tu bien y el de quienes amas. La fe te hace libre de la tiranía de la opinión ajena.",
            authorOrSaint = "San Juan Pablo II",
            microMission = "Di 'no' con amabilidad a una conversación de chismes o críticas destructivas hoy."
        )
    )

    val dilemmas = listOf(
        Dilemma(
            id = "ciencia_fe",
            category = "Razón y Ciencia",
            question = "¿La Iglesia Católica y la ciencia están en guerra?",
            shortAnswer = "¡Falso mito! La ciencia explica CÓMO funciona el universo; la fe revela POR QUÉ y PARA QUÉ existes.",
            fullExplanation = "La Iglesia Católica no solo no teme a la ciencia, sino que ha sido su gran impulsora histórica. ¿Sabías que la teoría del Big Bang fue formulada por un sacerdote católico y astrofísico, Georges Lemaître? ¿O que el padre de la genética moderna fue el monje agustino Gregor Mendel? Fe y razón son, en palabras de Juan Pablo II, 'como dos alas con las cuales el espíritu humano se eleva hacia la contemplación de la verdad'. Dios creó las leyes de la física y la biología; estudiarlas es maravillarse con su firma.",
            modernAnalogy = "Imagina que recibes una carta de amor en un papel artesanal. La ciencia analiza la composición química de la tinta y la fibra del papel. La fe lee el mensaje de amor que quien lo escribió quiso transmitirte. Ambas son verdaderas y se complementan.",
            practicalTip = "Estudia y usa tu inteligencia al máximo. Descubrir la verdad científica es otra manera hermosa de alabar al Creador.",
            saintOrQuote = "«La ciencia sin fe es coja, la fe sin ciencia es ciega» — Albert Einstein / Georges Lemaître"
        ),
        Dilemma(
            id = "sufrimiento_mal",
            category = "Razón y Ciencia",
            question = "Si Dios es bueno y todopoderoso, ¿por qué permite el sufrimiento y la maldad?",
            shortAnswer = "Dios no creó el mal; nos dio libertad real para amar, y en Cristo entró en nuestro dolor para transformarlo.",
            fullExplanation = "Gran parte del sufrimiento del mundo nace del mal uso de la libertad humana (egoísmo, violencia, injusticia). Si Dios nos hubiera programado como robots incapaces de elegir, tampoco seríamos capaces de amar libremente. Y frente al dolor físico o las pérdidas que no entendemos, la respuesta cristiana no es una teoría fría: es la Cruz. Dios no miró nuestro sufrimiento desde lejos; se hizo hombre en Jesús, lloró, sufrió la injusticia y resucitó para que ninguna lágrima sea en vano.",
            modernAnalogy = "Como el reverso de un tapiz bordado: desde abajo solo vemos nudos e hilos cortados sin sentido, pero desde la mirada del Artesano cada hilo forma parte de una obra maestra de redención.",
            practicalTip = "Cuando pases por una prueba dura, no te aísles ni te pelees con Dios: desahógate con Él frente al Sagrario y convierte tu dolor en empatía hacia quienes sufren.",
            saintOrQuote = "«Dios es tan poderoso que sabe sacar un bien mayor incluso del mal que nosotros provocamos» — San Agustín"
        ),
        Dilemma(
            id = "evolucion_creacion",
            category = "Razón y Ciencia",
            question = "¿Un católico puede aceptar la evolución biológica o contradice al Génesis?",
            shortAnswer = "No hay contradicción: el Génesis enseña QUIÉN creó todo por amor, no es un manual técnico de biología.",
            fullExplanation = "La Iglesia Católica enseña que los primeros capítulos del Génesis usan un lenguaje simbólico y poético profundo para revelar verdades eternas: que Dios es el único Creador, que todo lo creó bueno y que el ser humano fue hecho a su imagen y semejanza con un alma espiritual inmortal. Papas como Pío XII, San Juan Pablo II, Benedicto XVI y Francisco han explicado que el desarrollo evolutivo de las especies puede ser perfectamente el mecanismo físico que Dios diseñó, mientras que el alma espiritual de cada ser humano es creada directamente por Dios.",
            modernAnalogy = "Un programador genial no tiene que dibujar píxel por píxel a mano cada segundo; puede escribir un código maestro capaz de desplegar mundos enteros en evolución.",
            practicalTip = "Lee la Biblia preguntándote qué verdad salvífica quiere enseñarte Dios en cada libro según su género literario.",
            saintOrQuote = "«La Biblia nos enseña cómo ir al Cielo, no cómo van los cielos» — Galileo Galilei / Cardenal Baronio"
        ),
        Dilemma(
            id = "confesion",
            category = "Sacramentos",
            question = "¿Por qué confesarme con un sacerdote y no directo con Dios?",
            shortAnswer = "Porque los seres humanos necesitamos escuchar el perdón con oídos reales y sentir el abrazo de la reconciliación.",
            fullExplanation = "Claro que puedes pedirle perdón a Dios en tu oración privada, ¡y debes hacerlo! Pero Jesús mismo instituyó la confesión cuando dijo a sus apóstoles: 'A quienes les perdonéis los pecados, les quedan perdonados' (Juan 20:23). El sacerdote actúa 'in persona Christi' (en lugar de Cristo) y bajo secreto de confesión absoluto (secreto sacramental inviolable). Al hablar en voz alta, rompes el poder que la culpa tiene en secreto en tu mente, recibes consejo espiritual y escuchas las palabras más liberadoras del mundo: 'Yo te absuelvo de tus pecados'.",
            modernAnalogy = "Cuando tienes una fractura o una infección grave, no te quedas en tu cuarto diciendo 'me autocuraré con buena vibra': vas con un médico especialista que te limpia la herida y aplica medicina. El confesonario es el hospital del alma.",
            practicalTip = "No vayas con miedo al juicio. Un buen sacerdote no está para regañarte, sino para alegrarse de tu regreso como el padre del hijo pródigo.",
            saintOrQuote = "«Dios no se cansa nunca de perdonar, somos nosotros los que nos cansamos de acudir a su misericordia» — Papa Francisco"
        ),
        Dilemma(
            id = "misa_domingo_flojera",
            category = "Sacramentos",
            question = "¿Por qué ir a Misa el domingo si puedo rezar en mi cuarto o siento que me aburro?",
            shortAnswer = "Rezar en tu cuarto es hablar con Jesús por teléfono; ir a Misa es abrazarlo en persona y cenar con Él en familia.",
            fullExplanation = "Nadie se salva solo: la fe cristiana es comunitaria. Además, en tu cuarto puedes hacer oración mental muy valiosa, pero solo en la Santa Misa ocurre el milagro de la Consagración donde Jesús se entrega realmente en Cuerpo, Sangre, Alma y Divinidad. Cuando nos aburrimos en Misa, casi siempre es porque no comprendemos lo que está pasando en el altar o vamos como espectadores pasivos en vez de llevar nuestras luchas, alegrías y proyectos para ofrecerlos sobre la patena.",
            modernAnalogy = "Decir 'amo a mi mejor amigo o a mi mamá, pero nunca quiero verlos en persona ni ir a su cena de cumpleaños, me basta con pensar en ellos desde mi cama' enfriaría cualquier relación real.",
            practicalTip = "Este domingo llega 5 minutos antes, lee las lecturas del día en tu celular y en el Ofertorio pon mentalmente en el altar tu semana entera.",
            saintOrQuote = "«Si conociéramos el valor de la Santa Misa, moriríamos de alegría» — San Juan María Vianney"
        ),
        Dilemma(
            id = "eucaristia_simbolo_o_real",
            category = "Sacramentos",
            question = "¿La Hostia consagrada es solo un símbolo o realmente está Jesús vivo ahí?",
            shortAnswer = "Es Presencia Real: dejó de ser pan común para convertirse en el Cuerpo vivo de Cristo resucitado.",
            fullExplanation = "En el capítulo 6 del Evangelio de San Juan, cuando Jesús dijo 'Mi carne es verdadera comida y mi sangre es verdadera bebida', muchos discípulos se escandalizaron y se fueron. Si hubiera sido una metáfora, Jesús los habría llamado para aclarar que era solo un símbolo; en cambio, reafirmó su palabra. Aunque nuestros ojos siguen viendo la apariencia (especies) de pan y vino para no asustarnos y respetar nuestra fe libre, su sustancia íntima se transforma por el poder del Espíritu Santo (Transubstanciación).",
            modernAnalogy = "La luz ultravioleta o el Wi-Fi no se ven a simple vista con los ojos, pero transforman todo lo que conectan. En la Eucaristía, bajo la humilde apariencia de pan, late el Corazón mismo de Dios.",
            practicalTip = "Cuando pases frente a una iglesia o entres al Sagrario, haz la genuflexión con calma y amor mirando la lamparita roja encendida: ¡Jesús te está esperando ahí!",
            saintOrQuote = "«La Eucaristía es mi autopista hacia el Cielo» — San Carlo Acutis"
        ),
        Dilemma(
            id = "noviazgo_cristiano",
            category = "Amor y Noviazgo",
            question = "¿Cómo vivir un noviazgo sano y con propósito en una época de relaciones desechables?",
            shortAnswer = "Un buen noviazgo no es para 'pasar el rato' ni usar al otro, sino para caminar juntos haciéndose mejores personas.",
            fullExplanation = "Hoy se normalizan los 'casi algo' sin compromiso donde nadie quiere responsabilizarse del corazón del otro. La visión católica del noviazgo es revolucionaria: amar a alguien es querer su bien verdadero y ayudarlo a llegar al Cielo. Un noviazgo sano se construye sobre tres pilares: amistad profunda y comunicación honesta, respeto mutuo al cuerpo y a los tiempos de madurez, y poner a Dios en el centro como la cuerda firme que une a los dos.",
            modernAnalogy = "Un edificio alto no se empieza por el penthouse ni por la decoración; se empieza por cimientos firmes de confianza, amistad y valores compartidos. Sin cimientos, a la primera tormenta todo se derrumba.",
            practicalTip = "Pregúntate con sinceridad: ¿esta relación me acerca más a Dios, a mi paz y a mi mejor versión, o me apaga y me quita la paz?",
            saintOrQuote = "«El amor no es un sentimiento pasajero; es una decisión libre de buscar el bien del otro» — San Juan Pablo II (Teología del Cuerpo)"
        ),
        Dilemma(
            id = "pureza_castidad_hoy",
            category = "Amor y Noviazgo",
            question = "¿La castidad y la pureza están pasadas de moda o reprimen el amor?",
            shortAnswer = "La castidad no es un 'no' al amor, sino un gran 'SÍ' a amar a las personas sin reducirlas a objetos de placer.",
            fullExplanation = "El mundo suele confundir castidad con represión fría. En realidad, como enseña el Catecismo, la castidad es la integración lograda de la sexualidad en la persona: ser dueño de tus impulsos en lugar de ser esclavo de ellos. La pureza de corazón te permite mirar a otra persona con respeto sagrado, valorando su alma, su historia y su dignidad, antes que usarla para una satisfacción egoísta o momentánea.",
            modernAnalogy = "El fuego en una chimenea da calor a todo el hogar; el mismo fuego suelto sin control sobre la alfombra incendia y destruye la casa. La pureza cuida el fuego del amor para que ilumine y no queme.",
            practicalTip = "Cuida lo que consumen tus ojos en internet y redes. Un corazón limpio ve con claridad y ama con una fuerza que nadie puede comprar.",
            saintOrQuote = "«Bienaventurados los limpios de corazón, porque ellos verán a Dios» — Mateo 5:8"
        ),
        Dilemma(
            id = "corazon_roto_ruptura",
            category = "Amor y Noviazgo",
            question = "Terminé una relación y siento que el corazón se me parte: ¿dónde está Dios en mi desamor?",
            shortAnswer = "Dios está recogiendo cada pedazo de tu corazón y protegiéndote para una historia donde seas amado de verdad.",
            fullExplanation = "El duelo por una ruptura amorosa o una ilusión rota duele físicamente, y Jesús no minimiza tu llanto. Muchas veces una puerta que se cierra con dolor es la forma en que Dios te libra de un camino donde no ibas a ser plenamente feliz ni valorado. Tu valor infinito no depende de que alguien haya sabido quedarse o no: tu valor está sellado por la Sangre de Cristo, que dio su vida entera por ti.",
            modernAnalogy = "Cuando el jardinero poda una rama que estaba enferma o torcida, el árbol parece quedar herido por unos días, pero gracias a esa poda en primavera brotan las flores más sanas y fuertes.",
            practicalTip = "Aplica contacto cero si te hace daño seguir mirando sus redes, llora lo que necesites frente a Jesús y rodéate de tus amigos y familia que te quieren bien.",
            saintOrQuote = "«El Señor está cerca de los quebrantados de corazón y salva a los de espíritu abatido» — Salmo 34:18"
        ),
        Dilemma(
            id = "redes_comparacion_autoestima",
            category = "Redes y Presión Social",
            question = "Me comparo todo el tiempo en TikTok e Instagram y siento que mi vida no vale tanto: ¿cómo sanar?",
            shortAnswer = "Estás comparando el 'detrás de cámaras' real de tu vida con el 'tráiler editado con filtros' de los demás.",
            fullExplanation = "Las redes sociales están diseñadas con algoritmos que premian la apariencia perfecta, el lujo y la validación por likes. Cuando mides tu dignidad por vistas o seguidores, entregas tu paz interior a desconocidos. Para Dios, tú no eres un número de estadísticas: eres un hijo único e irrepetible. La verdadera autoestima cristiana consiste en mirarte con los ojos con los que Dios te mira.",
            modernAnalogy = "Nadie va a un museo a quejarse de que un cuadro de Van Gogh no se parece a una escultura de Miguel Ángel. Cada obra de arte tiene su propia belleza irrepetible; compararte destruye tu originalidad.",
            practicalTip = "Haz un ayuno digital de 30 minutos antes de dormir, deja de seguir cuentas que disparen tu inseguridad y agradece 3 dones reales que Dios puso en ti.",
            saintOrQuote = "«Todos nacen como originales, pero muchos mueren como fotocopias» — San Carlo Acutis"
        ),
        Dilemma(
            id = "presion_grupo_burlas",
            category = "Redes y Presión Social",
            question = "Mis amigos se burlan porque soy católico o me presionan a hacer cosas que van contra mis valores: ¿qué hago?",
            shortAnswer = "Los peces muertos siguen la corriente del río; solo los peces vivos tienen la fuerza de nadar contracorriente.",
            fullExplanation = "Tener fe auténtica en el colegio, la universidad o el grupo de amigos requiere valentía (el don de Fortaleza del Espíritu Santo). Quienes se burlan de la fe muchas veces lo hacen por ignorancia o porque en el fondo admiran la paz y firmeza que ellos no tienen. No necesitas discutir con agresividad ni dar sermones: tu mejor defensa es una sonrisa segura, un 'no, gracias, yo paso' dicho con personalidad y una coherencia que hable más fuerte que mil palabras.",
            modernAnalogy = "Un faro en medio de la tormenta no se apaga porque las olas golpeen la roca; al contrario, cuando más oscura está la noche, más útil y necesaria es su luz para los barcos que están perdidos.",
            practicalTip = "Busca también al menos uno o dos amigos que compartan tu fe (en tu parroquia o grupo juvenil) para caminar acompañados: la brasa sola se apaga, juntas mantienen el fuego.",
            saintOrQuote = "«No tengan miedo de ir contracorriente cuando les quieran robar la esperanza» — Papa Francisco"
        ),
        Dilemma(
            id = "chismes_cancelacion_redes",
            category = "Redes y Presión Social",
            question = "¿Qué tan grave es participar en chismes, 'hate' o burlas virales en redes sociales?",
            shortAnswer = "Destruir la fama o la dignidad de alguien con la lengua o el teclado hiere gravemente el corazón de Dios.",
            fullExplanation = "El Octavo Mandamiento prohíbe mentir, calumniar y difamar. Hoy es facilísimo esconderse detrás de una pantalla para reírse de un meme humillante sobre un compañero, reenviar un rumor o sumarse al 'hate' colectivo. Pero detrás de cada pantalla hay un alma por la que Cristo murió. San Felipe Neri enseñó que las palabras lanzadas al viento son como plumas de una almohada soltadas desde un campanario: una vez que vuelan, es imposible recogerlas todas.",
            modernAnalogy = "Un comentario cruel en internet es como apretar un tubo de pasta dental: sacarla toma un segundo de impulso, pero volver a meterla dentro del tubo es imposible.",
            practicalTip = "Aplica los 3 filtros antes de hablar o comentar: 1) ¿Sé con certeza que es verdad? 2) ¿Es bueno y edificante? 3) ¿Es necesario decirlo? Si no pasa los 3, cállalo o frena el chisme.",
            saintOrQuote = "«No salga de vuestra boca palabra dañina, sino la que sea buena para edificar» — Efesios 4:29"
        ),
        Dilemma(
            id = "perdonar_herida_profunda",
            category = "Perdón y Familia",
            question = "¿Cómo perdonar a alguien que me traicionó o me hizo mucho daño si todavía me duele?",
            shortAnswer = "Perdonar no es olvidar por arte de magia ni decir 'no pasó nada'; es decidir soltar el veneno del rencor.",
            fullExplanation = "Mucha gente cree que no ha perdonado porque al recordar el hecho todavía siente dolor. ¡Pero el perdón no es un sentimiento, es una decisión de la voluntad! Tampoco significa permitir que te sigan maltratando (poner límites sanos es totalmente cristiano). Perdonar es entregarle la justicia a Dios y renunciar a la venganza para que tu corazón deje de estar encadenado a la persona que te hirió.",
            modernAnalogy = "Guardar rencor es como tomar veneno todos los días esperando que le haga daño a la otra persona. Perdonar es abrir la jaula: y al abrirla descubres que el prisionero que quedaba libre eras tú.",
            practicalTip = "Si aún no sientes ganas de perdonar, haz esta oración sincera: 'Jesús, hoy no puedo perdonar con mis fuerzas, pero quiero querer perdonar. Pon tu perdón en mi corazón y bendice a esa persona'.",
            saintOrQuote = "«El perdón es la fragancia que derrama la violeta en el talón que la aplastó»"
        ),
        Dilemma(
            id = "peleas_con_padres",
            category = "Perdón y Familia",
            question = "¿Cómo honrar a mi padre y a mi madre cuando siento que no me entienden o hay discusiones en casa?",
            shortAnswer = "Honrar a los padres no significa que sean perfectos, sino tratarlos con respeto, paciencia y gratitud.",
            fullExplanation = "Al crecer en la adolescencia y juventud es normal notar los defectos, cansancios o heridas emocionales de nuestros padres. Ellos también son seres humanos aprendiendo a vivir y muchas veces cargan presiones económicas o laborales silenciosas. El Cuarto Mandamiento nos invita a no responder con gritos ni desprecios, a valorar sus sacrificios y a dialogar con calma cuando las aguas estén tranquilas, no en medio del enojo.",
            modernAnalogy = "Cuando la señal de radio tiene interferencia, gritar más fuerte contra el micrófono solo produce más ruido; sintonizar la frecuencia con calma permite que el mensaje llegue claro.",
            practicalTip = "Hoy sorprende a tus papás o abuelos con un gesto concreto sin que te lo pidan: lavar los platos, darles un abrazo o preguntarles '¿Cómo estuvo tu día hoy?'.",
            saintOrQuote = "«Hijo, cuida de tu padre en su vejez, y en su vida no le causes tristeza» — Eclesiástico 3:12"
        ),
        Dilemma(
            id = "amistades_toxicas",
            category = "Perdón y Familia",
            question = "¿Ser buen cristiano significa aguantar amistades tóxicas que me manipulan o me arrastran hacia abajo?",
            shortAnswer = "¡No! Amar al prójimo como a ti mismo incluye cuidar tu alma y saber tomar distancia con caridad.",
            fullExplanation = "Jesús nos manda amar a todos y desear el bien incluso de quienes nos tratan mal, pero distinguir entre 'amar a todos' y 'hacer de cualquiera tu confidente íntimo' es parte de la virtud de la Prudencia. Si un grupo o supuesto amigo te manipula, te humilla, se aprovecha de ti o te empuja constantemente a perder tu paz y caer en vicios, tomar una distancia sana y respetuosa no es egoísmo ni falta de caridad: es responsabilidad con tu vida.",
            modernAnalogy = "Si estás en la orilla de una piscina y alguien que no sabe nadar te jala con fuerza hacia el fondo sin dejarse ayudar, tirarte sin salvavidas hará que se ahoguen los dos. A veces hay que ayudar y rezar desde tierra firme.",
            practicalTip = "Puedes rezar por una persona y tratarla con educación sin darle las llaves de tu intimidad ni de tus decisiones.",
            saintOrQuote = "«El que encuentra un amigo fiel, encuentra un tesoro; y el que teme al Señor dirige bien su amistad» — Eclesiástico 6:14-17"
        ),
        Dilemma(
            id = "aburrimiento_fiesta",
            category = "Estilo de Vida",
            question = "¿Ser católico significa ser aburrido y no poder salir de fiesta?",
            shortAnswer = "¡Para nada! Jesús empezó sus milagros públicos en una fiesta de bodas y disfrutaba comer con amigos.",
            fullExplanation = "El catolicismo celebra la vida, el arte, la música, la buena comida y la amistad sincera. Lo que la fe te pide no es que no te diviertas, sino que no te destruyas ni destruyas a otros en el proceso. La cultura actual muchas veces confunde 'diversión' con anestesiarse de excesos para tapar vacíos. La verdadera fiesta es la que al día siguiente te deja con el corazón en paz, recuerdos bonitos y sin resaca moral ni culpa.",
            modernAnalogy = "Los barandales en un mirador alto no están para quitarte la vista ni la diversión; están para que puedas disfrutar del paisaje sin caerte al precipicio. Los mandamientos son los barandales que protegen tu felicidad.",
            practicalTip = "Sal, ríe, baila y haz deporte. Pero cuida de tus amigos, pon límites sanos y sé quien lleva luz y seguridad a cualquier reunión.",
            saintOrQuote = "«Estar siempre alegre, hacer el bien y dejar cantar a los gorriones» — San Juan Bosco"
        ),
        Dilemma(
            id = "oracion_vacia",
            category = "Espiritualidad",
            question = "¿Cómo orar si me distraigo a los 30 segundos o siento que le hablo a la pared?",
            shortAnswer = "La oración no es una emoción sentimental; es una cita de amistad fiel.",
            fullExplanation = "Es completamente normal que la mente vuele y que no siempre sientas 'mariposas espirituales'. Si solo hablas con tus amigos cuando estás emocionado, esa amistad es frágil. La oración madura consiste en estar presente, con honestidad brutal: 'Señor, hoy tengo flojera, estoy distraído, tengo ansiedad por mis exámenes'. A Dios no le interesan los discursos poéticos falsos; le interesa tu verdad. Incluso 2 minutos de respirar en silencio diciendo el nombre de Jesús tienen un impacto inmenso.",
            modernAnalogy = "Tomar sol no requiere que hagas ningún esfuerzo físico: basta con ponerte bajo sus rayos y dejarte calentar. La oración es poner tu alma bajo los rayos del sol de Dios.",
            practicalTip = "Prueba la técnica del 'Micro-minuto': cada vez que abras tu teléfono por primera vez en la mañana o entres a tu cuarto, di una frase corta de corazón: 'Jesús, te entrego mi día'.",
            saintOrQuote = "«Para mí, la oración es un impulso del corazón, una simple mirada dirigida al cielo» — Santa Teresita del Niño Jesús"
        ),
        Dilemma(
            id = "dios_no_responde",
            category = "Espiritualidad",
            question = "Le pedí algo a Dios con toda mi fe y no pasó lo que quería: ¿acaso no me escuchó?",
            shortAnswer = "Dios siempre escucha tu oración, pero como Padre sabio responde de 3 maneras: 'Sí', 'Todavía no' o 'Tengo algo mucho mejor para ti'.",
            fullExplanation = "Dios no es una máquina expendedora donde metes una moneda de rezos y sale exactamente tu capricho. Él ve el mapa completo de tu vida de aquí a la eternidad, mientras que nosotros solo vemos el minuto presente. Incluso Jesús en el Huerto de los Olivos oró diciendo: 'Padre, si es posible, pase de mí este cáliz; pero no se haga mi voluntad, sino la tuya'. Muchas veces con los años agradecemos a Dios que no nos haya concedido algo que en su momento creíamos indispensable.",
            modernAnalogy = "Un niño pequeño llora porque su papá no lo deja jugar con un cuchillo brillante o comer diez bolsas de dulces; el niño cree que su papá no lo escucha, pero el padre lo está cuidando por amor.",
            practicalTip = "Al final de tus peticiones, añade siempre con confianza: 'Señor, concédeme esto si es para mi bien y mi salvación, o dame la gracia de descubrir tu plan'.",
            saintOrQuote = "«Dios no te niega lo que le pides para castigarte, sino para prepararte un don más grande» — San Agustín"
        ),
        Dilemma(
            id = "virgen_maria_santos",
            category = "Espiritualidad",
            question = "¿Por qué los católicos aman tanto a la Virgen María y a los santos? ¿No le quita eso lugar a Jesús?",
            shortAnswer = "Amar a la Madre del Rey y a sus mejores amigos nunca compite con el Rey; al contrario, nos lleva directo a Él.",
            fullExplanation = "En la Cruz, el mismo Jesús nos entregó a María como Madre: 'Ahí tienes a tu madre' (Juan 19:27), y en las bodas de Caná la última frase de María en el Evangelio es: 'Hagan todo lo que Él les diga'. María nunca se queda con la atención: es como la luna que refleja la luz del Sol que es Cristo. Y los santos son nuestros hermanos mayores que ya llegaron a la meta del Cielo y siguen orando e intercediendo por nosotros ante Dios.",
            modernAnalogy = "Cuando admiras la obra maestra de un pintor y elogias su belleza, no estás ofendiendo al pintor: ¡lo estás llenando de orgullo! María es la obra maestra de Dios.",
            practicalTip = "Cuando te cueste concentrarte para orar, reza una decena del Rosario pidiéndole a María que te preste su corazón para amar más a Jesús.",
            saintOrQuote = "«A Jesús siempre se va y se 'vuelve' por María» — San Josemaría Escrivá / San Luis María Grignion de Montfort"
        ),
        Dilemma(
            id = "descubrir_vocacion_futuro",
            category = "Vocación y Propósito",
            question = "Tengo miedo al futuro y no sé qué estudiar ni cuál es la voluntad de Dios para mi vida: ¿cómo descubrir mi vocación?",
            shortAnswer = "Tu vocación es el lugar donde tus talentos y pasiones se encuentran con las necesidades reales del mundo.",
            fullExplanation = "Dios no juega a las escondidas con tu futuro para ver si adivinas un acertijo secreto. Él puso semillas de dones, capacidades e inclinaciones nobles dentro de tu propio corazón. La vocación tiene tres niveles: 1) La vocación universal al amor y a la santidad (para todos); 2) Tu estado de vida (matrimonio, vida consagrada o sacerdocio); y 3) Tu misión profesional o de servicio. Dios guía a un barco que está navegando, no a uno que se queda paralizado por el miedo en el puerto.",
            modernAnalogy = "El GPS del celular no te muestra el detalle de cada curva de un viaje de 500 km de golpe; te ilumina los próximos 200 metros. Cuando das el paso con fe hoy, Dios te ilumina el siguiente tramo.",
            practicalTip = "Haz una lista de 3 cosas que haces bien, 3 cosas que te dan paz al servir a otros y pídele luz al Espíritu Santo frente al Santísimo.",
            saintOrQuote = "«Si son lo que Dios quiere que sean, prenderán fuego al mundo entero» — Santa Catalina de Siena"
        ),
        Dilemma(
            id = "caer_en_el_mismo_pecado",
            category = "Vocación y Propósito",
            question = "Me da vergüenza acercarme a Dios porque siempre caigo en el mismo error: ¿tiene sentido seguir intentándolo?",
            shortAnswer = "¡Mil veces sí! El santo no es el que nunca se cae, sino el que nunca deja de levantarse de la mano de Dios.",
            fullExplanation = "La trampa favorita del enemigo es susurrarte antes de caer: 'No pasa nada, Dios perdona todo', y justo después de caer cambiarte el discurso a: 'Eres un hipócrita, siempre caes en lo mismo, ya ni te confieses'. ¡Rechaza esa mentira! A Dios no le sorprende tu fragilidad; Él ya conocía todas tus caídas antes de subir a la Cruz y aun así dio su vida por ti. Cada vez que te levantas con humildad y vuelves a confesarte, vences al orgullo y tu voluntad se fortalece.",
            modernAnalogy = "Un bebé que aprende a caminar se cae cien veces en la sala, y sus padres jamás le dicen 'ya no sirves para caminar'; lo aplauden y le tienden los brazos cada vez que se vuelve a poner de pie.",
            practicalTip = "Identifica qué horario, estado de ánimo (cansancio, soledad, estrés) o situación dispara tu caída y corta la ocasión antes de que empiece.",
            saintOrQuote = "«En la vida espiritual, quien no avanza retrocede; pero si caes, levántate enseguida con humildad» — San Francisco de Sales"
        ),
        Dilemma(
            id = "salud_mental",
            category = "Bienestar y Psique",
            question = "Tengo ansiedad y tristeza profunda: ¿Basta con rezar o necesito terapia?",
            shortAnswer = "Dios obra a través de la medicina y los profesionales de la salud. La fe y la psicología van de la mano.",
            fullExplanation = "Tener depresión, ansiedad o ataques de pánico NO es falta de fe ni castigo de Dios. Somos seres integrales: cuerpo, mente y espíritu. Si te rompes una pierna, vas al traumatólogo y rezas para que la cirugía salga bien. De la misma manera, si tu química cerebral o tu salud emocional están desbordadas, ir al psicólogo o psiquiatra es un acto de valentía y humildad bendecido por Dios. La fe te da el sentido profundo y la esperanza; la terapia te da herramientas psicológicas para sanar.",
            modernAnalogy = "Rezar por un examen no te exime de sentarte a estudiar. Rezar por tu paz mental no te exime de buscar las herramientas médicas que Dios puso en el mundo para cuidarte.",
            practicalTip = "Si estás sufriendo, habla con tus padres o un profesional de confianza hoy mismo. Pedir ayuda es de personas fuertes.",
            saintOrQuote = "«Honra al médico por sus servicios, pues también a él lo creó el Señor» — Eclesiástico 38:1"
        ),
        Dilemma(
            id = "soledad_vacio_interior",
            category = "Bienestar y Psique",
            question = "Estoy rodeado de gente y notificaciones, pero me siento profundamente solo: ¿cómo llenar ese vacío?",
            shortAnswer = "Tienes un corazón con medidas de infinito: nada finito (likes, compras o fiestas) puede llenarlo por completo.",
            fullExplanation = "Vivimos en la generación más hiperconectada digitalmente y, al mismo tiempo, con mayor índice de soledad juvenil. Las conexiones superficiales de pantalla nos dejan hambrientos de encuentros reales donde podamos mostrarnos sin máscaras. Además, hay una sed en lo más hondo de tu alma que ningún ser humano ni éxito material puede saciar del todo, porque fuiste creado por Dios y para Dios.",
            modernAnalogy = "Si intentas abrir una cerradura con veinte llaves equivocadas, solo rasparás el metal; únicamente la llave que fue diseñada por el fabricante encaja sin forzar y abre la puerta. Esa llave es el amor de Dios.",
            practicalTip = "Transforma tu soledad aislada en 'soledad habitada': sal a caminar o sirve en un voluntariado donde ayudes a otros cara a cara, y conversa con Jesús como tu amigo más íntimo.",
            saintOrQuote = "«Nos hiciste, Señor, para Ti, y nuestro corazón está inquieto hasta que descanse en Ti» — San Agustín"
        ),
        Dilemma(
            id = "dudas_fe",
            category = "Crecimiento",
            question = "Tengo muchas dudas sobre la religión: ¿Estoy perdiendo mi fe o pecando?",
            shortAnswer = "¡Tener dudas no es pecar! Es la señal de que tu fe infantil está creciendo para convertirse en una fe madura.",
            fullExplanation = "Quien no piensa nunca, no tiene dudas; pero quien piensa y madura se hace preguntas profundas. Grandes santos como la Madre Teresa de Calcuta tuvieron noches oscuras de dudas durante años. Lo peligroso no es dudar, sino quedarse en la duda perezosa sin investigar ni buscar respuestas serias. La Iglesia Católica tiene 2,000 años de filosofía, teología y respuestas inteligentes a las preguntas más duras sobre el dolor, la justicia y la existencia.",
            modernAnalogy = "Los dolores de crecimiento en los huesos ocurren cuando el cuerpo se expande. Las dudas son los dolores de crecimiento de tu fe cuando el traje de niño ya no te queda.",
            practicalTip = "No te tragues mitos de videos de 15 segundos sin contrastar fuentes. Lee autores como C.S. Lewis, investiga el Catecismo Joven (YouCat) o conversa con un líder espiritual cercano.",
            saintOrQuote = "«Dudar con honestidad es a menudo el umbral de una fe mucho más sólida»"
        )
    )

    val youthPrayers = listOf(
        PrayerItem(
            id = "antes_examen",
            title = "Oración antes de un Examen o Reto",
            situation = "Para calmar la mente y enfocar los conocimientos",
            content = "Señor Jesús, Tú conoces el esfuerzo que he puesto en estudiar y también los nervios que siento en este momento. Te pido que envíes a tu Santo Espíritu: disipa mi ansiedad, aclara mi mente y ayúdame a recordar lo aprendido. Dame serenidad para responder con sabiduría y honestidad. Que este examen sea una oportunidad para crecer y glorificarte. Pongo el resultado en tus manos. Amén.",
            category = "Estudios y Retos"
        ),
        PrayerItem(
            id = "paz_ansiedad",
            title = "Oración en Momentos de Ansiedad y Agobio",
            situation = "Cuando el pecho se cierra y los pensamientos abruman",
            content = "Jesús, Príncipe de la Paz: en este instante en que mis pensamientos se aceleran y el pecho se me aprieta, decido poner una pausa. Respiro profundo en tu presencia. Tú me dijiste: 'No se turbe vuestro corazón ni tenga miedo'. Reprendo en tu nombre todo espíritu de angustia y desesperanza. Eres mi refugio seguro. Lléname de tu calma que sobrepasa todo entendimiento. Confío en Ti. Amén.",
            category = "Paz Interior"
        ),
        PrayerItem(
            id = "tomar_decision",
            title = "Oración para Tomar una Decisión Difícil",
            situation = "Vocación, carrera, amistades o dilemas morales",
            content = "Espíritu Santo, Luz del alma: me encuentro ante una encrucijada y no quiero dejarme guiar por el impulso ciego ni por el miedo al qué dirán. Envíame tu don de Sabiduría y de Consejo. Muéstrame cuál es el camino que me acerca más al amor auténtico, a la verdad y a mi mejor versión. Dame la valentía de elegir lo correcto, aunque cueste. Sé Tú mi brújula hoy y siempre. Amén.",
            category = "Discernimiento"
        ),
        PrayerItem(
            id = "agradecimiento_noche",
            title = "Oración de Gratitud al Final del Día",
            situation = "Para cerrar el día en paz y reconciliación",
            content = "Padre Bueno, el día ha terminado y antes de cerrar los ojos vengo a decirte gracias. Gracias por el aire que respiré, por la comida en mi mesa, por las risas compartidas y por los desafíos que me enseñaron a ser más fuerte. Si hoy caí o lastimé a alguien con mis palabras, perdóname; mañana prometo empezar de nuevo con tu gracia. Cuida a mi familia, a mis amigos y regálame un descanso reparador. En tus manos encomiendo mi noche. Amén.",
            category = "Noche"
        ),
        PrayerItem(
            id = "carlos_acutis",
            title = "Oración inspirada en San Carlo Acutis",
            situation = "Para vivir una vida auténtica y enamorada de la Eucaristía",
            content = "Señor Dios nuestro, que hiciste del joven Carlo Acutis un testigo radiante de pureza, alegría y amor a los más necesitados a través de la tecnología: concédenos su misma pasión por la Eucaristía como nuestra 'autopista hacia el cielo'. Ayúdanos a no morir como fotocopias, sino a brillar con el proyecto único de amor que soñaste para cada uno de nosotros. Amén.",
            category = "Testimonio Juvenil"
        )
    )

    fun getSacramentsList(): List<SacramentType> = SacramentType.values().toList()
}
