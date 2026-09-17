package com.example.data.repository

import com.example.data.model.DailyQuote
import java.util.Calendar

object DailyQuoteRepository {

    val quotes: List<DailyQuote> = listOf(
        DailyQuote(
            id = "flp_4_13",
            text = "Todo lo puedo en Cristo que me fortalece.",
            reference = "Filipenses 4, 13",
            book = "Filipenses",
            category = "Fortaleza y Superación",
            reflection = "Cuando sientas que tus fuerzas se agotan o que las exigencias del día te sobrepasan, recuerda que tu motor principal no son tus ganas humanas, sino la presencia viva de Jesús en ti.",
            shortPrayer = "Jesús, dame hoy tu fuerza cuando sienta que la mía no alcanza. En Ti confío."
        ),
        DailyQuote(
            id = "is_40_31",
            text = "Los que esperan en el Señor renuevan sus fuerzas; levantan el vuelo como las águilas, corren y no se fatigan, caminan y no se cansan.",
            reference = "Isaías 40, 31",
            book = "Isaías",
            category = "Esperanza y Renovación",
            reflection = "Esperar en Dios no es sentarse pasivo, sino descansar el corazón en Su fidelidad para levantarte con un empuje nuevo que trasciende cualquier cansancio.",
            shortPrayer = "Señor, renueva mi entusiasmo y mi ánimo en este nuevo día que me regalas."
        ),
        DailyQuote(
            id = "jr_29_11",
            text = "Porque yo sé bien los planes que tengo para vosotros —oráculo del Señor—: planes de bienestar y no de calamidad, de daros un porvenir y una esperanza.",
            reference = "Jeremías 29, 11",
            book = "Jeremías",
            category = "Confianza y Futuro",
            reflection = "El futuro a veces genera incertidumbre o ansiedad por qué camino elegir. Dios tiene un proyecto de luz y plenitud pensado a tu medida. Da el paso de hoy en paz.",
            shortPrayer = "Padre Bueno, pongo mis dudas y mi porvenir en tus manos providenciales."
        ),
        DailyQuote(
            id = "sal_23_1",
            text = "El Señor es mi pastor, nada me falta. En verdes praderas me hace recostar; me conduce hacia aguas de reposo y reconforta mi alma.",
            reference = "Salmo 23 (22), 1-3",
            book = "Salmos",
            category = "Paz Interior y Protección",
            reflection = "En medio de la prisa y el ruido de la vida cotidiana, el Buen Pastor te invita a hacer una pausa. Nada de lo que verdaderamente necesitas te faltará hoy.",
            shortPrayer = "Señor Jesús, sé mi guía hoy y sosiega las tormentas de mi corazón."
        ),
        DailyQuote(
            id = "1tim_4_12",
            text = "Que nadie tenga en poco tu juventud; antes bien, sé modelo para los creyentes en la palabra, en la conducta, en el amor, en la fe y en la pureza.",
            reference = "1 Timoteo 4, 12",
            book = "1 Timoteo",
            category = "Juventud y Misión",
            reflection = "Ser joven no es una sala de espera para empezar a transformar el mundo: es el momento perfecto para inspirar con tu alegría, lealtad y rectitud a quienes te rodean.",
            shortPrayer = "Espíritu Santo, hazme luz y testimonio auténtico ante mis amigos y familia."
        ),
        DailyQuote(
            id = "jn_15_13",
            text = "Nadie tiene mayor amor que este: dar la vida por sus amigos.",
            reference = "San Juan 15, 13",
            book = "San Juan",
            category = "Amor y Amistad",
            reflection = "Dar la vida no significa necesariamente morir físicamente; se traduce en dar tu tiempo, perdonar con generosidad y estar incondicionalmente para un amigo que lo necesita.",
            shortPrayer = "Jesús, enséñame a amar sin cálculos y a ser un amigo fiel y leal."
        ),
        DailyQuote(
            id = "rom_8_38",
            text = "Pues estoy convencido de que ni la muerte ni la vida, ni ángeles ni principados... ni criatura alguna podrá separarnos del amor de Dios manifestado en Cristo Jesús.",
            reference = "Romanos 8, 38-39",
            book = "Romanos",
            category = "Amor Incondicional",
            reflection = "No hay error, fracaso ni tropiezo que pueda romper el abrazo con el que Dios te sostiene. Su misericordia es inquebrantable.",
            shortPrayer = "Gracias, Señor, porque tu amor por mí es inagotable e invencible."
        ),
        DailyQuote(
            id = "mt_5_14",
            text = "Vosotros sois la luz del mundo. No puede ocultarse una ciudad situada en lo alto de un monte. Brille así vuestra luz ante los hombres.",
            reference = "San Mateo 5, 14.16",
            book = "San Mateo",
            category = "Identidad y Testimonio",
            reflection = "No apagues tus convicciones para encajar en modas pasajeras. Fuiste creado para alumbrar rincones donde hay soledad, tristeza o indiferencia.",
            shortPrayer = "Señor, que mi forma de actuar hoy sea un reflejo claro de tu bondad y verdad."
        ),
        DailyQuote(
            id = "sir_6_14",
            text = "Un amigo fiel es un refugio seguro: quien lo encuentra, ha encontrado un tesoro.",
            reference = "Eclesiástico (Sirácida) 6, 14",
            book = "Eclesiástico",
            category = "Sabiduría y Amistad",
            reflection = "La amistad sana y leal es uno de los regalos más preciosos de Dios. Valora y cuida a esas personas que sacan la versión más noble y honesta de ti.",
            shortPrayer = "Dios de la vida, bendice a mis amistades y ayúdame a ser refugio para ellos."
        ),
        DailyQuote(
            id = "jos_1_9",
            text = "¿No te lo he mandado yo? ¡Sé fuerte y valiente! No temas ni te acobardes, porque el Señor tu Dios estará contigo dondequiera que vayas.",
            reference = "Josué 1, 9",
            book = "Josué",
            category = "Valentía y Coraje",
            reflection = "Tener valentía no es la ausencia de miedo, sino avanzar sabiendo que Dios camina contigo. Enfréntate hoy a tus retos con la cabeza en alto.",
            shortPrayer = "Señor, destierra mis inseguridades y dame la osadía de dar lo mejor de mí."
        ),
        DailyQuote(
            id = "sal_139_14",
            text = "Te doy gracias porque me has formado de modo tan admirable; admirables son tus obras, mi alma lo sabe muy bien.",
            reference = "Salmo 139 (138), 14",
            book = "Salmos",
            category = "Autoestima y Dignidad",
            reflection = "No te compares con las apariencias de otros. Dios te pensó con cariño único, con dones específicos y con una dignidad sagrada que nadie te puede arrebatar.",
            shortPrayer = "Gracias, Padre, por crearme y amarme tal como soy. Ayúdame a valorarme."
        ),
        DailyQuote(
            id = "lc_1_37",
            text = "Porque para Dios no hay nada imposible.",
            reference = "San Lucas 1, 37",
            book = "San Lucas",
            category = "Fe y Milagros",
            reflection = "Donde la lógica humana ve un muro sin salida, la gracia de Dios abre puertas insospechadas. Confía tu mayor preocupación en Sus manos.",
            shortPrayer = "Creo en tu poder, Señor; aumenta mi fe en los momentos de mayor duda."
        ),
        DailyQuote(
            id = "pr_3_5",
            text = "Confía en el Señor con todo tu corazón y no te apoyes en tu propia inteligencia. Reconócele en todos tus caminos, y Él allanará tus senderos.",
            reference = "Proverbios 3, 5-6",
            book = "Proverbios",
            category = "Discernimiento y Guía",
            reflection = "Nuestros cálculos a veces se quedan cortos. Cuando consultas tus planes con Dios en la oración, Él te concede la lucidez para elegir siempre lo correcto.",
            shortPrayer = "Espíritu Santo, alinea mis decisiones de hoy con tu voluntad santa."
        ),
        DailyQuote(
            id = "gal_5_22",
            text = "En cambio, el fruto del Espíritu es amor, alegría, paz, paciencia, afabilidad, bondad, fidelidad, mansedumbre y templanza.",
            reference = "Gálatas 5, 22-23",
            book = "Gálatas",
            category = "Vida Espiritual y Paz",
            reflection = "El éxito espiritual no se mide en aplausos exteriores, sino en la serenidad interior, la ternura con los demás y el dominio propio en momentos tensos.",
            shortPrayer = "Ven, Espíritu Santo, e inunda mi trato hacia los demás de bondad y paciencia."
        ),
        DailyQuote(
            id = "2tim_1_7",
            text = "Porque Dios no nos ha dado un espíritu de cobardía, sino de fortaleza, de amor y de templanza.",
            reference = "2 Timoteo 1, 7",
            book = "2 Timoteo",
            category = "Fuerza Espiritual",
            reflection = "El miedo paraliza y aísla; el Espíritu de Dios ensancha el corazón y te despierta la determinación de actuar con amor y claridad.",
            shortPrayer = "Señor, que tu amor perfecto expulse de mí cualquier temor que me limite."
        ),
        DailyQuote(
            id = "jn_14_27",
            text = "La paz os dejo, mi paz os doy; no os la doy yo como la da el mundo. No se turbe vuestro corazón ni se acobarde.",
            reference = "San Juan 14, 27",
            book = "San Juan",
            category = "Paz Interior",
            reflection = "La paz de Jesús no depende de que todo afuera esté en calma perfecta; es una roca profunda en tu interior capaz de resistir cualquier oleaje.",
            shortPrayer = "Príncipe de la Paz, aquieta mi mente y regálame tu serenidad hoy."
        ),
        DailyQuote(
            id = "sab_7_26",
            text = "La Sabiduría es reflejo de la luz eterna, espejo sin mancha de la actividad de Dios e imagen de su bondad.",
            reference = "Sabiduría 7, 26",
            book = "Sabiduría",
            category = "Luz y Claridad",
            reflection = "Buscar la sabiduría es elegir la verdad por encima de la conveniencia rápida. Quien camina en la luz de Dios nunca tropieza a oscuras.",
            shortPrayer = "Dame, Señor, la sabiduría que viene de lo alto para vivir con rectitud."
        ),
        DailyQuote(
            id = "sal_46_2",
            text = "Dios es nuestro refugio y fortaleza, socorro siempre a punto en las angustias. Por eso no tememos si la tierra se conmueve.",
            reference = "Salmo 46 (45), 2-3",
            book = "Salmos",
            category = "Refugio y Calma",
            reflection = "Cuando sientas que tu entorno es inestable o que las circunstancias cambian rápido, aférrate al Ancla que nunca cede: Dios.",
            shortPrayer = "En Ti me refugio, Dios mío; sé mi roca firme en este día."
        ),
        DailyQuote(
            id = "mt_11_28",
            text = "Venid a mí todos los que estáis cansados y agobiados, y yo os haré descansar.",
            reference = "San Mateo 11, 28",
            book = "San Mateo",
            category = "Consuelo y Descanso",
            reflection = "No necesitas fingir que eres invencible frente a Jesús. Puedes llegar cansado, herido o confundido: Él te recibe y aligera tu carga.",
            shortPrayer = "Jesús, traigo mi cansancio y mis afanes a tus pies para descansar en Ti."
        ),
        DailyQuote(
            id = "1pe_5_7",
            text = "Descargad en Él todas vuestras preocupaciones, porque Él cuida de vosotros.",
            reference = "1 San Pedro 5, 7",
            book = "1 San Pedro",
            category = "Confianza y Calma",
            reflection = "No naciste para cargar sobre tus hombros el peso del mundo entero. Suelta la ansiedad en las manos del Padre que cuida de ti con ternura.",
            shortPrayer = "Te entrego mis agobios, Señor. Sé que Tú cuidas de mí a cada instante."
        ),
        DailyQuote(
            id = "is_43_1",
            text = "No temas, porque yo te he redimido; te he llamado por tu nombre: tú eres mío.",
            reference = "Isaías 43, 1",
            book = "Isaías",
            category = "Identidad y Pertenencia",
            reflection = "Para el mundo puedes ser una estadística o un usuario más; para Dios tienes un nombre único, una historia irrepetible y un valor infinito.",
            shortPrayer = "Señor, gracias por llamarme por mi nombre y amarme sin condiciones."
        ),
        DailyQuote(
            id = "col_3_14",
            text = "Y por encima de todo esto, revestíos del amor, que es el vínculo de la unidad perfecta.",
            reference = "Colosenses 3, 14",
            book = "Colosenses",
            category = "Fraternidad y Bondad",
            reflection = "La ropa más bella que puedes vestir hoy es la amabilidad sincera, la capacidad de disculpar y la generosidad de corazón.",
            shortPrayer = "Jesús, viste mi corazón de compasión y ternura para tratar a los demás."
        ),
        DailyQuote(
            id = "sal_119_105",
            text = "Lámpara es tu palabra para mis pasos, y una luz resplandeciente en mi camino.",
            reference = "Salmo 119 (118), 105",
            book = "Salmos",
            category = "Guía y Dirección",
            reflection = "En un mundo saturado de opiniones contradictorias, la Palabra de Dios es la brújula clara que ilumina el siguiente paso que debes dar.",
            shortPrayer = "Guía mis pasos, Señor, con la luz viva de tu Evangelio."
        ),
        DailyQuote(
            id = "sant_1_5",
            text = "Si a alguno de vosotros le falta sabiduría, pídala a Dios, que da a todos con generosidad y sin reproche, y le será dada.",
            reference = "Santiago 1, 5",
            book = "Santiago",
            category = "Oración y Petición",
            reflection = "Nunca tengas vergüenza de pedir ayuda al Cielo. A Dios le alegra cuando acudes con humildad a pedirle luz para entender y actuar bien.",
            shortPrayer = "Padre Bueno, derrama sobre mí el don del buen consejo y la prudencia."
        ),
        DailyQuote(
            id = "sal_27_1",
            text = "El Señor es mi luz y mi salvación, ¿a quién temeré? El Señor es la defensa de mi vida, ¿quién me hará temblar?",
            reference = "Salmo 27 (26), 1",
            book = "Salmos",
            category = "Seguridad en Dios",
            reflection = "Cuando Dios es tu fundamento, las opiniones ajenas, las presiones sociales y los temores pierden su fuerza. Camina con serenidad.",
            shortPrayer = "Tú eres mi luz y mi fortaleza, Señor. Nada temo contigo a mi lado."
        ),
        DailyQuote(
            id = "miq_6_8",
            text = "Se te ha hecho saber, hombre, lo que es bueno: practicar la justicia, amar con lealtad y caminar humildemente con tu Dios.",
            reference = "Miqueas 6, 8",
            book = "Miqueas",
            category = "Vida Coherente",
            reflection = "La vida cristiana es profunda y sencilla a la vez: sé justo con el débil, sé leal con quienes confían en ti y vive en diálogo humilde con Dios.",
            shortPrayer = "Ayúdame, Señor, a vivir hoy con autenticidad, justicia y corazón humilde."
        ),
        DailyQuote(
            id = "1jn_4_18",
            text = "En el amor no hay temor; al contrario, el amor perfecto expulsa el temor.",
            reference = "1 San Juan 4, 18",
            book = "1 San Juan",
            category = "Paz y Amor",
            reflection = "El amor auténtico no chantajea ni asusta: libera, sana y construye puentes de confianza duraderos.",
            shortPrayer = "Lléname de tu amor perfecto, Jesús, para desterrar todo miedo de mi corazón."
        ),
        DailyQuote(
            id = "ef_2_10",
            text = "Porque somos obra suya, creados en Cristo Jesús para buenas obras, que Dios preparó de antemano para que anduviésemos en ellas.",
            reference = "Efesios 2, 10",
            book = "Efesios",
            category = "Propósito de Vida",
            reflection = "Eres la obra maestra de Dios. Tu vida tiene un propósito trascendente: hacer el bien y sembrar esperanza a tu alrededor.",
            shortPrayer = "Señor, úsame hoy como instrumento de tu paz y de tus buenas obras."
        ),
        DailyQuote(
            id = "sof_3_17",
            text = "El Señor tu Dios está en medio de ti: un Salvador poderoso. Se gozará sobre ti con alegría; te renovará en su amor.",
            reference = "Sofonías 3, 17",
            book = "Sofonías",
            category = "Alegría y Ternura",
            reflection = "Dios no te mira con ceño fruncido ni con reproche: celebra tu existencia y se alegra con amor paternal al verte sonreír y crecer.",
            shortPrayer = "Renueva mi corazón hoy con la alegría viva de tu presencia, Señor."
        ),
        DailyQuote(
            id = "jn_8_12",
            text = "Yo soy la luz del mundo; el que me sigue no andará en tinieblas, sino que tendrá la luz de la vida.",
            reference = "San Juan 8, 12",
            book = "San Juan",
            category = "Luz y Verdad",
            reflection = "Caminar con Jesús te da claridad moral y sentido profundo en cada circunstancia. Enciende tu jornada siguiendo Sus huellas.",
            shortPrayer = "Jesús, Luz del mundo, disipa toda oscuridad y guía mis pasos hoy. Amén."
        )
    )

    fun getTodayQuote(): DailyQuote {
        val calendar = Calendar.getInstance()
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val index = (dayOfYear - 1).coerceAtLeast(0) % quotes.size
        return quotes[index]
    }

    fun getQuoteByIndex(index: Int): DailyQuote {
        val validIndex = ((index % quotes.size) + quotes.size) % quotes.size
        return quotes[validIndex]
    }

    fun getNextQuote(currentId: String): DailyQuote {
        val currentIndex = quotes.indexOfFirst { it.id == currentId }
        val nextIndex = if (currentIndex != -1) (currentIndex + 1) % quotes.size else 0
        return quotes[nextIndex]
    }
}
