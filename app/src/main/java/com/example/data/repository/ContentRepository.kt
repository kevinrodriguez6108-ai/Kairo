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
