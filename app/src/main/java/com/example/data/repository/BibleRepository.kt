package com.example.data.repository

import com.example.data.model.BibleBookInfo
import com.example.data.model.BiblePassage
import com.example.data.model.Testament

object BibleRepository {

    // Explicación esencial del Canon Católico
    const val CANON_EXPLANATION =
        "La Biblia que usamos los cristianos católicos consta de 73 libros inspirados por Dios: 46 en el Antiguo Testamento y 27 en el Nuevo Testamento. Incluye los 7 libros deuterocanónicos (Tobías, Judit, 1 y 2 Macabeos, Sabiduría, Eclesiástico/Sirácida y Baruc) presentes en la versión griega Septuaginta que utilizaban los apóstoles y la Iglesia primitiva desde el siglo I."

    val lectioDivinaSteps = listOf(
        "1. Lectura (Lectio)" to "¿Qué dice el texto? Lee despacio, dejando que las palabras resuenen.",
        "2. Meditación (Meditatio)" to "¿Qué me dice Dios a mí hoy con este pasaje? Conéctalo con tu vida actual.",
        "3. Oración (Oratio)" to "¿Qué le respondo yo al Señor? Habla con Él con sinceridad.",
        "4. Acción (Contemplatio / Actio)" to "¿Qué cambio concreto haré hoy en mi entorno inspirado por esta palabra?"
    )

    // Catálogo de libros católicos clasificados
    val catholicBooks: List<BibleBookInfo> = listOf(
        // PENTATEUCO (5)
        BibleBookInfo("Génesis", "Gn", Testament.ANTIGUO, "Pentateuco", false, 50, "El origen del universo, la vida, la dignidad humana y el inicio de la alianza de Dios.", "Gn 1, 27: 'Y creó Dios al hombre a su imagen...'"),
        BibleBookInfo("Éxodo", "Éx", Testament.ANTIGUO, "Pentateuco", false, 40, "La liberación de la esclavitud, el paso por el desierto y la Alianza del Sinaí.", "Éx 14, 14: 'El Señor combatirá por vosotros, vosotros manteneos en calma.'"),
        BibleBookInfo("Levítico", "Lv", Testament.ANTIGUO, "Pentateuco", false, 27, "Leyes de santidad y comunión con Dios: ser santos porque Dios es santo.", "Lv 19, 18: 'Amarás a tu prójimo como a ti mismo.'"),
        BibleBookInfo("Números", "Nm", Testament.ANTIGUO, "Pentateuco", false, 36, "El viaje por el desierto y la bendición sacerdotal de la paz.", "Nm 6, 24-26: 'Que el Señor te bendiga y te proteja...'"),
        BibleBookInfo("Deuteronomio", "Dt", Testament.ANTIGUO, "Pentateuco", false, 34, "Discursos de Moisés: amar a Dios con todo el corazón, mente y fuerzas.", "Dt 31, 6: '¡Sed fuertes y valientes! No temáis...'"),

        // HISTÓRICOS (16)
        BibleBookInfo("Josué", "Jos", Testament.ANTIGUO, "Históricos", false, 24, "La entrada y conquista de la tierra prometida con valentía en Dios.", "Jos 1, 9: 'Sé fuerte y valiente; no temas ni te acobardes...'"),
        BibleBookInfo("Jueces", "Jue", Testament.ANTIGUO, "Históricos", false, 21, "Líderes que Dios suscita para salvar a su pueblo en momentos de crisis.", "Jue 6, 12: 'El Señor está contigo, valiente guerrero.'"),
        BibleBookInfo("Rut", "Rut", Testament.ANTIGUO, "Históricos", false, 4, "Preciosa historia de lealtad, amor familiar y fidelidad extranjera.", "Rut 1, 16: 'Adonde tú vayas, iré yo; tu Dios será mi Dios.'"),
        BibleBookInfo("1 Samuel", "1 S", Testament.ANTIGUO, "Históricos", false, 31, "La llamada del joven Samuel y la victoria del joven David frente a Goliat.", "1 S 16, 7: 'El hombre mira la apariencia, pero el Señor mira el corazón.'"),
        BibleBookInfo("2 Samuel", "2 S", Testament.ANTIGUO, "Históricos", false, 24, "El reinado de David, sus victorias y el perdón de Dios tras su arrepentimiento.", "2 S 22, 31: 'El camino de Dios es perfecto, la palabra del Señor es acrisolada.'"),
        BibleBookInfo("1 Reyes", "1 R", Testament.ANTIGUO, "Históricos", false, 22, "Salomón y la petición del don de la sabiduría; el profeta Elías en el susurro suave.", "1 R 19, 12: 'Y tras el fuego, un susurro de brisa suave.'"),
        BibleBookInfo("2 Reyes", "2 R", Testament.ANTIGUO, "Históricos", false, 25, "El ministerio profético de Eliseo y la historia de los reinos.", "2 R 6, 16: 'No temas, porque son más los que están con nosotros que con ellos.'"),
        BibleBookInfo("1 Crónicas", "1 Cro", Testament.ANTIGUO, "Históricos", false, 29, "Genealogías y alabanzas centradas en el templo y la oración.", "1 Cro 16, 11: 'Buscad al Señor y su poder, buscad siempre su rostro.'"),
        BibleBookInfo("2 Crónicas", "2 Cro", Testament.ANTIGUO, "Históricos", false, 36, "La construcción del Templo y los llamados a la reconciliación.", "2 Cro 7, 14: 'Si mi pueblo se humilla y reza... yo sanaré su tierra.'"),
        BibleBookInfo("Esdras", "Esd", Testament.ANTIGUO, "Históricos", false, 10, "El retorno del exilio y la reconstrucción comunitaria con fe.", "Esd 7, 10: 'Esdras había preparado su corazón para estudiar la Ley.'"),
        BibleBookInfo("Nehemías", "Neh", Testament.ANTIGUO, "Históricos", false, 13, "Reconstrucción de las murallas de Jerusalén y ánimo en la alegría del Señor.", "Neh 8, 10: 'No estéis tristes, porque el gozo del Señor es vuestra fuerza.'"),
        BibleBookInfo("Tobías", "Tob", Testament.ANTIGUO, "Históricos", true, 14, "Libro deuterocanónico católico: el arcángel San Rafael acompaña al joven Tobías, enseñando confianza, matrimonio santo y limosna.", "Tob 4, 7: 'Da limosna de tus bienes y no apartes tu rostro de ningún pobre.'"),
        BibleBookInfo("Judit", "Jdt", Testament.ANTIGUO, "Históricos", true, 16, "Libro deuterocanónico católico: valentía de una mujer que confía en Dios para salvar a su pueblo.", "Jdt 13, 19: 'Tu confianza no se apartará del corazón de los hombres.'"),
        BibleBookInfo("Ester", "Est", Testament.ANTIGUO, "Históricos", false, 10, "La reina Ester arriesga su vida con ayuno y oración para salvar a su pueblo.", "Est 4, 14: '¿Quién sabe si no has llegado al trono para un momento como este?'"),
        BibleBookInfo("1 Macabeos", "1 Mac", Testament.ANTIGUO, "Históricos", true, 16, "Libro deuterocanónico católico: la heroica resistencia de los jóvenes macabeos por defender su fe y libertad.", "1 Mac 3, 19: 'La victoria en el combate no depende de la multitud, sino de la fuerza que viene del Cielo.'"),
        BibleBookInfo("2 Macabeos", "2 Mac", Testament.ANTIGUO, "Históricos", true, 15, "Libro deuterocanónico católico: heroísmo de los 7 hermanos mártires y fe clara en la resurrección de los muertos.", "2 Mac 7, 28: 'Te ruego, hijo mío, que mires al cielo y a la tierra... Dios los creó de la nada.'"),

        // SAPIENCIALES Y POÉTICOS (7)
        BibleBookInfo("Job", "Job", Testament.ANTIGUO, "Sapienciales", false, 42, "El misterio del sufrimiento, la fe inquebrantable y el diálogo con Dios.", "Job 19, 25: 'Yo sé que mi Redentor vive, y que al fin se levantará sobre el polvo.'"),
        BibleBookInfo("Salmos", "Sal", Testament.ANTIGUO, "Sapienciales", false, 150, "El libro de oraciones por excelencia de la Iglesia católica: cantos de gozo, dolor, perdón y alabanza.", "Sal 23, 1: 'El Señor es mi pastor, nada me falta.'"),
        BibleBookInfo("Proverbios", "Pr", Testament.ANTIGUO, "Sapienciales", false, 31, "Consejos prácticos para la juventud sobre decisiones, amistades y prudencia.", "Pr 3, 5-6: 'Confía en el Señor de todo corazón y no te apoyes en tu propia prudencia.'"),
        BibleBookInfo("Eclesiastés", "Ecl", Testament.ANTIGUO, "Sapienciales", false, 12, "Reflexión profunda sobre el sentido de la vida, el tiempo y la eternidad.", "Ecl 3, 1: 'Todo tiene su momento oportuno; hay un tiempo para cada cosa bajo el cielo.'"),
        BibleBookInfo("Cantar de los Cantares", "Cant", Testament.ANTIGUO, "Sapienciales", false, 8, "Canto sublime al amor conyugal puro, alegoría del amor ardiente de Dios por su pueblo.", "Cant 8, 6: 'Ponme como un sello sobre tu corazón... porque fuerte como la muerte es el amor.'"),
        BibleBookInfo("Sabiduría", "Sab", Testament.ANTIGUO, "Sapienciales", true, 19, "Libro deuterocanónico católico: la inmortalidad del alma, el don de la Sabiduría que busca la juventud.", "Sab 7, 26: 'Es reflejo de la luz eterna, espejo sin mancha de la actividad de Dios.'"),
        BibleBookInfo("Eclesiástico (Sirácida)", "Sir", Testament.ANTIGUO, "Sapienciales", true, 51, "Libro deuterocanónico católico: manual ético y espiritual para jóvenes sobre la amistad leal, respeto y discernimiento.", "Sir 6, 14: 'El amigo fiel es un refugio seguro; el que lo encuentra, ha encontrado un tesoro.'"),

        // PROFÉTICOS (18)
        BibleBookInfo("Isaías", "Is", Testament.ANTIGUO, "Proféticos", false, 66, "El gran profeta mesiánico: anuncia a Emmanuel, al Siervo sufriente y la esperanza.", "Is 40, 31: 'Los que esperan en el Señor renuevan sus fuerzas; levantan el vuelo como las águilas.'"),
        BibleBookInfo("Jeremías", "Jr", Testament.ANTIGUO, "Proféticos", false, 52, "La vocación desde la juventud: 'No digas: soy un joven, porque a donde yo te envíe irás'.", "Jr 29, 11: 'Porque yo sé los planes que tengo para vosotros: planes de bienestar y no de calamidad.'"),
        BibleBookInfo("Lamentaciones", "Lm", Testament.ANTIGUO, "Proféticos", false, 5, "Elegías de dolor ante la caída de Jerusalén que desembocan en esperanza inmutable.", "Lm 3, 22-23: 'La misericordia del Señor no se agota, cada mañana se renueva.'"),
        BibleBookInfo("Baruc", "Bar", Testament.ANTIGUO, "Proféticos", true, 6, "Libro deuterocanónico católico: secretario del profeta Jeremías, llama a la conversión y ensalza la verdadera sabiduría.", "Bar 3, 9: 'Escucha, Israel, los mandatos de vida; atiende para aprender prudencia.'"),
        BibleBookInfo("Ezequiel", "Ez", Testament.ANTIGUO, "Proféticos", false, 48, "Visiones de gloria y la promesa de un corazón nuevo de carne en lugar de piedra.", "Ez 36, 26: 'Os daré un corazón nuevo y pondré en vosotros un espíritu nuevo.'"),
        BibleBookInfo("Daniel", "Dan", Testament.ANTIGUO, "Proféticos", false, 14, "Jóvenes firmes en su fe en Babilonia, visiones del Hijo del Hombre y los capítulos deuterocanónicos (canto de los 3 jóvenes, Susana).", "Dan 12, 3: 'Los sabios brillarán como el resplandor del firmamento.'"),
        BibleBookInfo("Oseas", "Os", Testament.ANTIGUO, "Proféticos", false, 14, "Dios que ama a su pueblo con ternura de esposo fiel y misericordioso.", "Os 6, 6: 'Amor quiero, no sacrificios; conocimiento de Dios más que holocaustos.'"),
        BibleBookInfo("Joel", "Jl", Testament.ANTIGUO, "Proféticos", false, 4, "La profecía del derramamiento del Espíritu Santo sobre jóvenes y ancianos cumplida en Pentecostés.", "Jl 3, 1: 'Derramaré mi Espíritu sobre toda carne; vuestros jóvenes verán visiones.'"),
        BibleBookInfo("Amós", "Am", Testament.ANTIGUO, "Proféticos", false, 9, "El clamor por la justicia social en favor de los débiles y oprimidos.", "Am 5, 24: 'Que fluya el derecho como las aguas, y la justicia como torrente inagotable.'"),
        BibleBookInfo("Abdías", "Abd", Testament.ANTIGUO, "Proféticos", false, 1, "Mensaje de juicio sobre la soberbia y victoria final del Reino del Señor.", "Abd 1, 21: 'Y el reino será del Señor.'"),
        BibleBookInfo("Jonás", "Jon", Testament.ANTIGUO, "Proféticos", false, 4, "El profeta reacio y la inmensa misericordia universal de Dios que acoge a todos.", "Jon 2, 8: 'Cuando mi vida se apagaba, me acordé del Señor.'"),
        BibleBookInfo("Miqueas", "Miq", Testament.ANTIGUO, "Proféticos", false, 7, "Anuncia que el Mesías nacerá en Belén y enseña qué pide Dios del ser humano.", "Miq 6, 8: 'Hombre, se te ha hecho saber lo que es bueno: practicar la justicia, amar la misericordia y caminar humildemente con tu Dios.'"),
        BibleBookInfo("Nahúm", "Nah", Testament.ANTIGUO, "Proféticos", false, 3, "Consuelo a los oprimidos: Dios es fortaleza en el día de la angustia.", "Nah 1, 7: 'Bueno es el Señor, refugio en el día de la tribulación.'"),
        BibleBookInfo("Habacuc", "Hab", Testament.ANTIGUO, "Proféticos", false, 3, "Diálogo honesto con Dios sobre el mal en el mundo y fe incondicional.", "Hab 3, 18: 'Yo me alegraré en el Señor, me gozaré en el Dios de mi salvación.'"),
        BibleBookInfo("Sofonías", "Sof", Testament.ANTIGUO, "Proféticos", false, 3, "El gozo de Dios que danza de alegría por su pueblo salvado.", "Sof 3, 17: 'El Señor tu Dios está en medio de ti: Él se gozará sobre ti con alegría.'"),
        BibleBookInfo("Hageo", "Hag", Testament.ANTIGUO, "Proféticos", false, 2, "Llamado a priorizar la presencia de Dios por encima de los lujos materiales.", "Hag 2, 4: '¡Ánimo, pueblo todo! Trabajad, pues yo estoy con vosotros.'"),
        BibleBookInfo("Zacarías", "Zac", Testament.ANTIGUO, "Proféticos", false, 14, "Anuncia la entrada humilde de Jesús montado en un burrito.", "Zac 9, 9: '¡Alégrate mucho, hija de Sión! He aquí que tu Rey viene a ti, humilde...'"),
        BibleBookInfo("Malaquías", "Mal", Testament.ANTIGUO, "Proféticos", false, 3, "El último profeta del Antiguo Testamento anunciando el Sol de justicia.", "Mal 3, 20: 'Para vosotros nacerá el Sol de justicia que traerá la salud en sus rayos.'"),

        // NUEVO TESTAMENTO: EVANGELIOS Y HECHOS (5)
        BibleBookInfo("San Mateo", "Mt", Testament.NUEVO, "Evangelios", false, 28, "El Evangelio del Reino: el Sermón de la Montaña, las Bienaventuranzas y el mandato misionero.", "Mt 5, 14: 'Vosotros sois la luz del mundo. No puede ocultarse una ciudad en lo alto de un monte.'"),
        BibleBookInfo("San Marcos", "Mc", Testament.NUEVO, "Evangelios", false, 16, "El Evangelio más directo y dinámico: Jesús como Siervo y Salvador en acción constante.", "Mc 10, 45: 'El Hijo del Hombre no vino a ser servido, sino a servir y dar su vida.'"),
        BibleBookInfo("San Lucas", "Lc", Testament.NUEVO, "Evangelios", false, 24, "El Evangelio de la misericordia, la ternura hacia los marginados, la Virgen María y la oración.", "Lc 1, 37-38: 'Porque para Dios no hay nada imposible. He aquí la sierva del Señor.'"),
        BibleBookInfo("San Juan", "Jn", Testament.NUEVO, "Evangelios", false, 21, "El Evangelio teológico y místico: Jesús es el Verbo hecho carne, la Luz, el Pan de Vida y el Buen Pastor.", "Jn 10, 10: 'Yo he venido para que tengan vida, y la tengan en abundancia.'"),
        BibleBookInfo("Hechos de los Apóstoles", "Hch", Testament.NUEVO, "Historia Apostólica", false, 28, "La acción arrolladora del Espíritu Santo en los primeros cristianos y la expansión de la Iglesia.", "Hch 1, 8: 'Recibiréis la fuerza del Espíritu Santo que vendrá sobre vosotros, y seréis mis testigos.'"),

        // CARTAS PAULINAS Y GENERALES (21)
        BibleBookInfo("Romanos", "Rom", Testament.NUEVO, "Cartas", false, 16, "La justificación por la fe, la gracia transformadora y la victoria en Cristo.", "Rom 8, 38-39: 'Ni la muerte ni la vida... podrá separarnos del amor de Dios.'"),
        BibleBookInfo("1 Corintios", "1 Cor", Testament.NUEVO, "Cartas", false, 16, "El cuerpo como templo del Espíritu Santo, la Eucaristía y el sublime himno al amor.", "1 Cor 13, 13: 'Ahora permanecen la fe, la esperanza y el amor; pero el mayor de ellos es el amor.'"),
        BibleBookInfo("2 Corintios", "2 Cor", Testament.NUEVO, "Cartas", false, 13, "El poder de Dios en la debilidad humana: consuelo y ministerio reconciliador.", "2 Cor 12, 9: 'Te basta mi gracia, porque mi poder se perfecciona en la debilidad.'"),
        BibleBookInfo("Gálatas", "Gál", Testament.NUEVO, "Cartas", false, 6, "La carta de la libertad cristiana: llamados a vivir según los frutos del Espíritu.", "Gál 5, 22-23: 'El fruto del Espíritu es amor, alegría, paz, paciencia, afabilidad...'"),
        BibleBookInfo("Efesios", "Ef", Testament.NUEVO, "Cartas", false, 6, "La Iglesia como cuerpo de Cristo y la armadura espiritual para mantenerse firmes.", "Ef 6, 10: 'Por lo demás, fortaleceos en el Señor y en la fuerza de su poder.'"),
        BibleBookInfo("Filipenses", "Flp", Testament.NUEVO, "Cartas", false, 4, "La carta de la alegría incondicional y la paz que sobrepasa todo entendimiento.", "Flp 4, 13: 'Todo lo puedo en Aquel que me conforta.'"),
        BibleBookInfo("Colosenses", "Col", Testament.NUEVO, "Cartas", false, 4, "Cristo centro y cabeza de toda la creación: renovados en Él.", "Col 3, 14: 'Y por encima de todo esto, revestíos del amor, que es el vínculo de la perfección.'"),
        BibleBookInfo("1 Tesalonicenses", "1 Tes", Testament.NUEVO, "Cartas", false, 5, "La esperanza en la venida del Señor y la llamada a vivir siempre alegres y orantes.", "1 Tes 5, 16-18: 'Estad siempre alegres. Orad sin cesar. Dad gracias en toda ocasión.'"),
        BibleBookInfo("2 Tesalonicenses", "2 Tes", Testament.NUEVO, "Cartas", false, 3, "Perseverancia ante las dificultades y el trabajo cotidiano con dignidad.", "2 Tes 3, 13: 'Vosotros, hermanos, no os canséis de hacer el bien.'"),
        BibleBookInfo("1 Timoteo", "1 Tim", Testament.NUEVO, "Cartas", false, 6, "Consejos pastorales directos para el joven líder Timoteo.", "1 Tim 4, 12: 'Que nadie tenga en poco tu juventud, sino sé ejemplo de los creyentes en palabra y conducta.'"),
        BibleBookInfo("2 Timoteo", "2 Tim", Testament.NUEVO, "Cartas", false, 4, "Testamento espiritual de San Pablo: no tener espíritu de cobardía sino de valentía.", "2 Tim 1, 7: 'Dios no nos ha dado un espíritu de cobardía, sino de fortaleza, de amor y de templanza.'"),
        BibleBookInfo("Tito", "Tit", Testament.NUEVO, "Cartas", false, 3, "Instrucciones prácticas para vivir coherentemente la fe en la sociedad.", "Tit 2, 7: 'Muéstrate en todo como un modelo de buenas obras.'"),
        BibleBookInfo("Filemón", "Flm", Testament.NUEVO, "Cartas", false, 1, "La fraternidad cristiana que rompe las barreras de clase y esclavitud.", "Flm 1, 16: 'Ya no como esclavo, sino como un hermano amado.'"),
        BibleBookInfo("Hebreos", "Heb", Testament.NUEVO, "Cartas", false, 13, "Jesucristo el Sumo Sacerdote eterno, mediador de la Nueva Alianza y el heroísmo de la fe.", "Heb 11, 1: 'La fe es la certeza de lo que se espera, la convicción de lo que no se ve.'"),
        BibleBookInfo("Santiago", "Sant", Testament.NUEVO, "Cartas", false, 5, "Fe práctica y viva: 'La fe sin obras está muerta'. Control de la lengua y sabiduría.", "Sant 1, 22: 'Poned en práctica la palabra y no os contentéis con sólo oírla.'"),
        BibleBookInfo("1 San Pedro", "1 Pe", Testament.NUEVO, "Cartas", false, 5, "Esperanza viva en medio de las pruebas: llamados a dar razón de nuestra esperanza con mansedumbre.", "1 Pe 3, 15: 'Estad siempre dispuestos a dar respuesta a todo el que os pida razón de vuestra esperanza.'"),
        BibleBookInfo("2 San Pedro", "2 Pe", Testament.NUEVO, "Cartas", false, 3, "Crecer en la gracia y en el conocimiento de nuestro Señor y Salvador.", "2 Pe 3, 18: 'Creced en la gracia y en el conocimiento de Jesucristo.'"),
        BibleBookInfo("1 San Juan", "1 Jn", Testament.NUEVO, "Cartas", false, 5, "Dios es Amor y Luz: quien ama a su hermano permanece en Dios.", "1 Jn 4, 18: 'En el amor no hay temor, sino que el amor perfecto expulsa el temor.'"),
        BibleBookInfo("2 San Juan", "2 Jn", Testament.NUEVO, "Cartas", false, 1, "Caminar en la verdad y en el mandamiento del amor fraterno.", "2 Jn 1, 6: 'Y este es el amor: que caminemos según sus mandamientos.'"),
        BibleBookInfo("3 San Juan", "3 Jn", Testament.NUEVO, "Cartas", false, 1, "Hospitalidad cristiana, cooperación con la verdad y bendición espiritual.", "3 Jn 1, 4: 'No tengo mayor alegría que oír que mis hijos caminan en la verdad.'"),
        BibleBookInfo("San Judas", "Jds", Testament.NUEVO, "Cartas", false, 1, "Conservarse firmes en el amor de Dios esperando la misericordia para vida eterna.", "Jds 1, 20: 'Construíos sobre el fundamento de vuestra santísima fe, orando en el Espíritu Santo.'"),

        // PROFECÍA NUEVO TESTAMENTO (1)
        BibleBookInfo("Apocalipsis", "Ap", Testament.NUEVO, "Profecía", false, 22, "El libro del triunfo definitivo de Cristo, el Cordero vencedor: enjugará toda lágrima y hará nuevas todas las cosas.", "Ap 21, 4: 'Enjugará toda lágrima de sus ojos; y ya no habrá muerte, ni duelo, ni llanto, ni dolor.'")
    )

    // Páginas y Pasajes Clave de la Biblia Católica para Jóvenes
    val curatedPassages: List<BiblePassage> = listOf(
        // Deuterocanónicos Clave
        BiblePassage(
            id = "pass_sir_6_14",
            reference = "Eclesiástico (Sirácida) 6, 14-17",
            book = "Eclesiástico (Sirácida)",
            testament = Testament.ANTIGUO,
            isDeuterocanonical = true,
            text = "Un amigo fiel es un refugio seguro: quien lo encuentra, ha encontrado un tesoro. Un amigo fiel no tiene precio, no hay medida para su valor. Un amigo fiel es bálsamo de vida: los que temen al Señor lo encontrarán. Quien teme al Señor orienta bien su amistad, pues según es él, así será su prójimo.",
            theme = "Amistad Verdadera y Lealtad",
            youthContext = "En un mundo donde los amigos a veces se miden por likes o conveniencia, este libro deuterocanónico católico te recuerda que la verdadera amistad es un tesoro sagrado que te cuida y te acerca a tu mejor versión.",
            reflectionPrompt = "¿Tus amistades actuales te impulsan a crecer o te restan paz? ¿Eres tú ese refugio leal para los demás?",
            tags = listOf("amistad", "lealtad", "amigos", "deuterocanónicos", "sirácida", "sabiduría")
        ),
        BiblePassage(
            id = "pass_sab_7_26",
            reference = "Sabiduría 7, 26-28",
            book = "Sabiduría",
            testament = Testament.ANTIGUO,
            isDeuterocanonical = true,
            text = "La Sabiduría es reflejo de la luz eterna, espejo sin mancha de la actividad de Dios e imagen de su bondad. Siendo una sola, todo lo puede; y permaneciendo en sí misma, todo lo renueva. Entra de generación en generación en las almas santas para hacer amigos de Dios y profetas.",
            theme = "El Don de la Sabiduría Divina",
            youthContext = "No se trata de sacar 10 en un examen ni de saberlo todo en internet, sino de tener claridad mental y paz para discernir lo que realmente vale la pena en tu vida.",
            reflectionPrompt = "Pide hoy este don del Espíritu Santo antes de tomar esa decisión que te preocupa.",
            tags = listOf("sabiduría", "discernimiento", "deuterocanónicos", "espíritu", "decisión")
        ),
        BiblePassage(
            id = "pass_tob_4_7",
            reference = "Tobías 4, 7-11",
            book = "Tobías",
            testament = Testament.ANTIGUO,
            isDeuterocanonical = true,
            text = "Hijo mío, da limosna según tus posibilidades: si tienes mucho, da mucho; si tienes poco, no temas dar de lo poco. Porque la limosna libra de la muerte y no permite caer en las tinieblas. Es una buena ofrenda ante el Altísimo.",
            theme = "Generosidad y Solidaridad Joven",
            youthContext = "El anciano Tobit aconseja a su joven hijo Tobías antes de su viaje. Dar no solo es dinero: es dar tiempo, escuchar al que está solo y no ser indiferente al dolor ajeno.",
            reflectionPrompt = "¿Qué gesto concreto de generosidad puedes tener hoy con alguien en tu casa o colegio?",
            tags = listOf("generosidad", "servicio", "solidaridad", "deuterocanónicos", "tobías")
        ),
        BiblePassage(
            id = "pass_1mac_3_19",
            reference = "1 Macabeos 3, 19",
            book = "1 Macabeos",
            testament = Testament.ANTIGUO,
            isDeuterocanonical = true,
            text = "La victoria en la batalla no depende de la multitud del ejército, sino de la fuerza que viene del Cielo.",
            theme = "Coraje y Valentía Espiritual",
            youthContext = "Judas Macabeo anima a un grupo de jóvenes ante un ejército abrumador. En tus luchas personales (inseguridades, adicciones, presiones), Dios es quien te sostiene.",
            reflectionPrompt = "¿Qué desafío parece gigante hoy en tu vida? Recuerda de dónde viene tu verdadera fuerza.",
            tags = listOf("valentía", "coraje", "fuerza", "lucha", "deuterocanónicos", "macabeos")
        ),

        // Sacramento del Bautismo en la Escritura
        BiblePassage(
            id = "pass_mt_28_19",
            reference = "San Mateo 28, 19-20",
            book = "San Mateo",
            testament = Testament.NUEVO,
            isDeuterocanonical = false,
            text = "Id, pues, y haced discípulos a todas las gentes, bautizándolas en el nombre del Padre y del Hijo y del Espíritu Santo, enseñándoles a guardar todo lo que yo os he mandado. Y he aquí que yo estoy con vosotros todos los días hasta el fin del mundo.",
            theme = "El Mandato del Bautismo e Identidad",
            youthContext = "El origen divino de tu Bautismo: fuiste sellado en el amor trinitario de Dios y cuentas con su presencia incondicional cada día, pase lo que pase.",
            reflectionPrompt = "Tu Bautismo te dio una familia global. ¿Sientes la compañía real de Jesús en tu rutina?",
            tags = listOf("bautismo", "sacramentos", "misión", "mateo", "identidad")
        ),
        BiblePassage(
            id = "pass_rom_6_3",
            reference = "Romanos 6, 3-4",
            book = "Romanos",
            testament = Testament.NUEVO,
            isDeuterocanonical = false,
            text = "¿O ignoráis que cuantos fuimos bautizados en Cristo Jesús, fuimos bautizados en su muerte? Fuimos, pues, con él sepultados por el bautismo en su muerte, a fin de que, al igual que Cristo resucitó de entre los muertos por la gloria del Padre, así también nosotros vivamos una vida nueva.",
            theme = "El Bautismo como Reset y Vida Nueva",
            youthContext = "El agua del Bautismo no es un rito anticuado: es tu reset absoluto. Sepulta la culpa y los errores para hacerte renacer con dignidad intacta.",
            reflectionPrompt = "¿Qué culpa o miedo necesitas dejar enterrado hoy para vivir la vida nueva que Dios te regala?",
            tags = listOf("bautismo", "vida nueva", "reset", "romanos", "gracia")
        ),

        // Sacramento de la Confirmación en la Escritura
        BiblePassage(
            id = "pass_hch_2_1",
            reference = "Hechos de los Apóstoles 2, 1-4",
            book = "Hechos de los Apóstoles",
            testament = Testament.NUEVO,
            isDeuterocanonical = false,
            text = "Al llegar el día de Pentecostés, estaban todos reunidos en un mismo lugar. De repente vino del cielo un ruido como el de una ráfaga de viento impetuoso, que llenó toda la casa en la que se encontraban... Se les aparecieron lenguas como de fuego y se posaron sobre cada uno de ellos. Todos quedaron llenos del Espíritu Santo.",
            theme = "Pentecostés: El Fuego de la Confirmación",
            youthContext = "Los apóstoles tenían miedo y estaban encerrados. Al recibir el Espíritu Santo, salieron con pasión a cambiar el mundo. Esa misma fuerza recibes en tu Confirmación.",
            reflectionPrompt = "¿Qué temores te paralizan para dar testimonio de lo que crees ante tus compañeros?",
            tags = listOf("confirmación", "espíritu santo", "pentecostés", "fuego", "sacramentos")
        ),
        BiblePassage(
            id = "pass_is_11_1",
            reference = "Isaías 11, 1-2",
            book = "Isaías",
            testament = Testament.ANTIGUO,
            isDeuterocanonical = false,
            text = "Brotará un renuevo del tronco de Jesé... Sobre él reposará el Espíritu del Señor: espíritu de sabiduría y de entendimiento, espíritu de consejo y de fortaleza, espíritu de ciencia y de temor del Señor.",
            theme = "Los 7 Dones del Espíritu Santo",
            youthContext = "El fundamento profético de los 7 dones que la Iglesia Católica imparte solemnemente en la Confirmación para guiar tus pasos en la juventud.",
            reflectionPrompt = "¿Cuál de los 7 dones sientes que necesitas pedir con más urgencia hoy?",
            tags = listOf("confirmación", "7 dones", "espíritu santo", "isaías", "sacramentos")
        ),

        // Sacramento de la Eucaristía en la Escritura
        BiblePassage(
            id = "pass_jn_6_51",
            reference = "San Juan 6, 51-56",
            book = "San Juan",
            testament = Testament.NUEVO,
            isDeuterocanonical = false,
            text = "Yo soy el pan vivo que ha bajado del cielo: si alguno come de este pan, vivirá para siempre; y el pan que yo daré es mi carne para la vida del mundo... El que come mi carne y bebe mi sangre permanece en mí y yo en él.",
            theme = "La Eucaristía: Presencia Real de Jesús",
            youthContext = "La Primera Comunión no es un evento de un día: es el banquete sagrado donde Jesús se hace alimento vivo para darte fuerzas cuando tus energías se agotan.",
            reflectionPrompt = "Cuando comulgas, Jesús vive en ti. ¿Cómo puedes reflejar su presencia viva hoy?",
            tags = listOf("eucaristía", "comunión", "pan de vida", "juan", "sacramentos")
        ),
        BiblePassage(
            id = "pass_1cor_11_23",
            reference = "1 Corintios 11, 23-26",
            book = "1 Corintios",
            testament = Testament.NUEVO,
            isDeuterocanonical = false,
            text = "Porque yo recibí del Señor lo que también os he transmitido: que el Señor Jesús, la noche en que era entregado, tomó pan, dio gracias, lo partió y dijo: 'Esto es mi cuerpo que se entrega por vosotros; haced esto en memoria mía'. Asimismo el cáliz... 'Este cáliz es la nueva alianza en mi sangre'.",
            theme = "La Institución de la Santa Misa",
            youthContext = "El relato litúrgico más antiguo de la Última Cena. Cada Misa católica actualiza de manera misteriosa y real este mismo sacrificio de amor por ti.",
            reflectionPrompt = "¿Cómo vives la Misa dominical? Pídele a Jesús que transforme tu manera de estar presente.",
            tags = listOf("eucaristía", "misa", "comunión", "corintios", "alianza")
        ),

        // Ansiedad, Miedo y Paz Interior
        BiblePassage(
            id = "pass_flp_4_6",
            reference = "Filipenses 4, 6-7",
            book = "Filipenses",
            testament = Testament.NUEVO,
            isDeuterocanonical = false,
            text = "No os inquietéis por cosa alguna; antes bien, en toda ocasión, presentad a Dios vuestras peticiones mediante la oración y la súplica, acompañadas de la acción de gracias. Y la paz de Dios, que sobrepasa todo entendimiento, custodiará vuestros corazones y vuestros pensamientos en Cristo Jesús.",
            theme = "Paz frente a la Ansiedad y el Estrés",
            youthContext = "El mejor antídoto contra el insomnio y la angustia por el futuro: cambiar la preocupación destructiva por una conversación honesta con Dios.",
            reflectionPrompt = "Entrega en tus manos abiertas todo lo que no puedes controlar ahora mismo.",
            tags = listOf("ansiedad", "paz", "estrés", "oración", "calma", "filipenses")
        ),
        BiblePassage(
            id = "pass_sal_23_1",
            reference = "Salmo 23 (22), 1-4",
            book = "Salmos",
            testament = Testament.ANTIGUO,
            isDeuterocanonical = false,
            text = "El Señor es mi pastor, nada me falta: en verdes praderas me hace recostar; me conduce hacia aguas de reposo y conforta mi alma... Aunque camine por cañadas oscuras, nada temo, porque tú vas conmigo: tu vara y tu cayado me sosiegan.",
            theme = "El Buen Pastor en Días Difíciles",
            youthContext = "La oración más amada del salterio católico. En tus momentos de soledad o bajón anímico, Dios no te observa desde lejos: camina a tu lado.",
            reflectionPrompt = "Respira hondo y repite mentalmente: 'Nada temo, porque Tú vas conmigo'.",
            tags = listOf("salmos", "paz", "protección", "pastor", "confianza")
        ),
        BiblePassage(
            id = "pass_mt_6_26",
            reference = "San Mateo 6, 26-34",
            book = "San Mateo",
            testament = Testament.NUEVO,
            isDeuterocanonical = false,
            text = "Mirad las aves del cielo: no siembran, ni siegan, ni recogen en graneros; y vuestro Padre celestial las alimenta. ¿No valéis vosotros mucho más que ellas? ¿Quién de vosotros, por mucho que se preocupe, puede añadir una sola hora al curso de su vida? Buscad primero el Reino de Dios y su justicia, y todo lo demás se os dará por añadidura.",
            theme = "Confianza en la Providencia frente al Futuro",
            youthContext = "Jesús hablando al corazón de quien se agobia por el dinero, los exámenes o qué será de su vida mañana. Eres infinitamente valioso para el Padre.",
            reflectionPrompt = "¿En qué área de tu vida necesitas soltar el control y confiar en Dios?",
            tags = listOf("confianza", "providencia", "futuro", "ansiedad", "mateo")
        ),

        // Autoestima, Dignidad e Identidad Joven
        BiblePassage(
            id = "pass_sal_139_13",
            reference = "Salmo 139 (138), 13-16",
            book = "Salmos",
            testament = Testament.ANTIGUO,
            isDeuterocanonical = false,
            text = "Tú creaste mis entrañas, me tejiste en el seno de mi madre. Te doy gracias porque me has formado de modo tan admirable; admirables son tus obras, mi alma lo sabe muy bien. No se te ocultaba mi osamenta cuando fui formado en lo secreto.",
            theme = "Obra Maestra de Dios: Tu Dignidad Única",
            youthContext = "Contra el veneno de la comparación constante en redes sociales: Dios te diseñó a mano, con un propósito único e irrepetible. No eres una fotocopia.",
            reflectionPrompt = "Mírate con los ojos de Dios: eres su diseño personal querido y amado.",
            tags = listOf("identidad", "autoestima", "salmos", "creación", "dignidad")
        ),
        BiblePassage(
            id = "pass_1tim_4_12",
            reference = "1 Timoteo 4, 12",
            book = "1 Timoteo",
            testament = Testament.NUEVO,
            isDeuterocanonical = false,
            text = "Que nadie tenga en poco tu juventud; antes bien, sé modelo para los creyentes en la palabra, en la conducta, en el amor, en la fe y en la pureza.",
            theme = "Juventud Protagonista, no Espectadora",
            youthContext = "San Pablo empodera al joven Timoteo: tu edad no es una limitación, es una oportunidad para inspirar con tu coherencia y entusiasmo a toda la comunidad.",
            reflectionPrompt = "¿En qué acción concreta puedes ser hoy un referente positivo para tus amigos?",
            tags = listOf("juventud", "liderazgo", "timoteo", "ejemplo", "conducta")
        ),
        BiblePassage(
            id = "pass_jr_1_5",
            reference = "Jeremías 1, 5-8",
            book = "Jeremías",
            testament = Testament.ANTIGUO,
            isDeuterocanonical = false,
            text = "Antes de haberte formado en el vientre materno, te conocía, y antes de que nacieras, te tenía consagrado... Yo respondí: '¡Ah, Señor Dios! Mira que no sé hablar, pues soy solo un muchacho'. Pero el Señor me dijo: 'No digas: Soy solo un muchacho, porque a dondequiera que yo te envíe irás, y dirás todo lo que yo te mande. No les tengas miedo, porque yo estoy contigo para librarte'.",
            theme = "Llamado a la Vocación desde Joven",
            youthContext = "Cuando sientes que no estás listo, que eres 'demasiado joven' o que no tienes habilidades suficientes, Dios te dice: Yo voy contigo.",
            reflectionPrompt = "¿A qué misión de ayuda o servicio sientes que Dios te está invitando?",
            tags = listOf("vocación", "jeremías", "misión", "valentía", "juventud")
        ),

        // Amor Verdadero, Perdón y Recomenzar
        BiblePassage(
            id = "pass_1cor_13_4",
            reference = "1 Corintios 13, 4-8",
            book = "1 Corintios",
            testament = Testament.NUEVO,
            isDeuterocanonical = false,
            text = "El amor es paciente, es servicial; el amor no tiene envidia, no es jactancioso, no se engríe; no es indecoroso, no busca su propio interés, no se irrita, no toma en cuenta el mal; no se alegra de la injusticia, sino que se goza con la verdad. Todo lo disculpa, todo lo cree, todo lo espera, todo lo soporta. El amor nunca falla.",
            theme = "La Definición del Amor Auténtico",
            youthContext = "El himno al amor que desmitifica el amor tóxico o puramente egoísta. Amar es una decisión generosa de buscar el bien del otro.",
            reflectionPrompt = "Sustituye mentalmente la palabra 'amor' por tu nombre: ¿qué características necesitas trabajar más?",
            tags = listOf("amor", "paciencia", "relaciones", "corintios", "perdón")
        ),
        BiblePassage(
            id = "pass_lc_15_20",
            reference = "San Lucas 15, 20-24",
            book = "San Lucas",
            testament = Testament.NUEVO,
            isDeuterocanonical = false,
            text = "Y levantándose, vino hacia su padre. Cuando todavía estaba lejos, su padre lo vio y se conmovió profundamente; y corriendo, se echó a su cuello y lo cubrió de besos... El padre dijo a sus siervos: 'Traed aprisa el mejor vestido y ponédselo, ponedle un anillo en la mano y sandalias en los pies... porque este hijo mío estaba muerto y ha vuelto a la vida'.",
            theme = "El Abrazo Incondicional del Padre (El Perdón)",
            youthContext = "La parábola del hijo pródigo: no importa qué tan lejos hayas caído o qué errores hayas cometido, el corazón de Dios siempre te espera con los brazos abiertos.",
            reflectionPrompt = "¿Hay algún error del pasado que todavía no te perdonas? Déjate abrazar por su misericordia.",
            tags = listOf("perdón", "misericordia", "padre", "lucas", "esperanza")
        )
    )

    // Búsqueda inteligente
    fun searchPassages(query: String, filter: String? = null): List<BiblePassage> {
        val cleanQuery = query.trim().lowercase()

        return curatedPassages.filter { passage ->
            val matchesFilter = when (filter) {
                null, "Todos" -> true
                "Deuterocanónicos" -> passage.isDeuterocanonical
                "Antiguo Testamento" -> passage.testament == Testament.ANTIGUO
                "Nuevo Testamento" -> passage.testament == Testament.NUEVO
                "Sacramentos" -> passage.tags.contains("sacramentos") || passage.tags.contains("bautismo") || passage.tags.contains("confirmación") || passage.tags.contains("eucaristía")
                "Ansiedad y Paz" -> passage.tags.contains("ansiedad") || passage.tags.contains("paz") || passage.tags.contains("calma")
                "Amistad" -> passage.tags.contains("amistad") || passage.tags.contains("lealtad")
                "Juventud" -> passage.tags.contains("juventud") || passage.tags.contains("liderazgo")
                else -> passage.theme.contains(filter, ignoreCase = true) || passage.tags.any { it.equals(filter, ignoreCase = true) }
            }

            val matchesQuery = if (cleanQuery.isEmpty()) {
                true
            } else {
                passage.reference.lowercase().contains(cleanQuery) ||
                passage.book.lowercase().contains(cleanQuery) ||
                passage.text.lowercase().contains(cleanQuery) ||
                passage.theme.lowercase().contains(cleanQuery) ||
                passage.youthContext.lowercase().contains(cleanQuery) ||
                passage.tags.any { it.lowercase().contains(cleanQuery) }
            }

            matchesFilter && matchesQuery
        }
    }

    // Búsqueda de libros
    fun searchBooks(query: String, testamentFilter: Testament? = null, deuterocanonicalOnly: Boolean = false): List<BibleBookInfo> {
        val cleanQuery = query.trim().lowercase()
        return catholicBooks.filter { book ->
            val matchesTestament = testamentFilter == null || book.testament == testamentFilter
            val matchesDeut = !deuterocanonicalOnly || book.isDeuterocanonical
            val matchesQuery = cleanQuery.isEmpty() ||
                    book.name.lowercase().contains(cleanQuery) ||
                    book.abbreviation.lowercase().contains(cleanQuery) ||
                    book.category.lowercase().contains(cleanQuery) ||
                    book.description.lowercase().contains(cleanQuery) ||
                    book.keyVerse.lowercase().contains(cleanQuery)

            matchesTestament && matchesDeut && matchesQuery
        }
    }
}
