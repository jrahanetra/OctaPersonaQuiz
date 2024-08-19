package mg.geit.trioQuizzers.ui
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Question(
    val id: Int,
    val idPersonnality: Int,
    val questionText: String,
    val options: List<Response>,
    var selectedOption: Int? = null
)

data class Response(
    val id: Int,
    val response: String,
    var isSelected: Boolean,
    var point: Int
)

object ListQuestions {
    private val personalityMap = mapOf(
        "bienfaiteur" to 1,
        "insoumis" to 2,
        "visionnaire" to 3,
        "célébrité" to 4,
        "médiateur" to 5,
        "conservateur" to 6,
        "épicurien" to 7,
        "leader" to 8
    )

    private val questionsJson = """
{
  "bienfaiteur": [
    "Q1 Je suis une personne généreuse qui donne et/ou prête souvent de l'argent à autrui.",
    "Q3 Je me retrouve régulièrement en train d'aider les autres, financièrement ou autrement.",
    "Q5 Malgré mes meilleurs efforts, quelque chose arrive souvent pour compliquer ma vie financière",
    "Q7 Je me retrouve à attendre d'être payé à cause des besoins ou des raisons d'une tierce personne.",
    "Q33 Etre serviable est plus motivant que de s'enrichir financièrement.",
    "Q35 Je peux me sentir amer du fait que je ne reçois pas plus pour tout ce que je fais.",
    "Q37 Je vais donner de mon temps à quelqu'un qui en a besoin même si je ne suis pas rémunéré.",
    "Q39 A l'extrême, je donne au point de m'endetter ou de me mettre en risque financièrement."
  ],
  "insoumis": [
    "Q2 J'aime évaluer les pour et contre de la prise d'un risque financier.",
    "Q4 Je suis ouvert à prendre un risque financier contre un gain financier potentiellement grand.",
    "Q6 Secrètement, je me sens plus intelligent, voire supérieur aux autres à propos de l'argent.",
    "Q8 J'aime prendre le dessus avec l'argent.",
    "Q34 A l'extrême, mes risques financiers peuvent compromettre ma santé financière.",
    "Q36 Je suis à l'aise avec des transactions financières complexes.",
    "Q38 M'enrichir financièrement est un jeu où je dois gagner.",
    "Q40 Mes décisions financières peuvent paraître risquées aux yeux des autres."
  ],
  "visionnaire": [
    "Q9 J'aime attirer l'argent de manière inhabituelle voire magique.",
    "Q11 Faire de l'argent n'est pas aussi important que de créer un mouvement, avoir un impact social ou contribuer au changement.",
    "Q13 Je vis une relation amour/haine avec l'argent.",
    "Q15 Je crois que de trop se concentrer sur l'argent est avide et non spirituel..",
    "Q41 Je crois que les personnes financièrement aisées possèdent des avantages injustes.",
    "Q43 Je me méfie des gens qui s'intéressent trop à l'argent.",
    "Q45 Les investissements que j'effectue doivent être avec des personnes ou entreprises qui ont les mêmes valeurs que moi.",
    "Q47 A l'extrême, je suis soutenu financièrement par quelqu'un ou quelque chose ( une entreprise, un conjoint, un parent, une carte de crédit) et pourtant je ressens de la rancœur ou perçois la situation comme injuste."
  ],
  "célébrité": [
    "Q10 J'aime dépenser de l'argent sur des choses qui rehaussent mon image ou me donnent une grande visibilité.",
    "Q12 Toujours avoir le meilleur ou être premier - voyager en première classe, posséder les derniers gadgets, avoir accès aux sections VIP - est important pour moi.",
    "Q14 Je projette une image de succès et d'aisance qui ne reflète pas toujours mon solde en banque.",
    "Q16 Il  est plus facile pour moi de dépenser pour de la joaillerie, des restaurants, des vacances, etc.. que d'épargner.",
    "Q42 A l'extrême, je m'endette et dépense allègrement dans l'optique d'être reconnu et d'attirer l'attention.",
    "Q44 Je suis à l'aise avec la gestion quotidienne bien que je puisse avoir des dettes ou trouver difficile d'épargner ou d'investir.",
    "Q46 J'aime être remarqué et j'utilise mes achats pour le faire.",
    "Q48 Etre reconnu comme donateur généreux aux oeuvres caritatives et causes sociales est important pour moi."
  ],
  "médiateur": [
    "Q17 C'est facile pour moi de ne pas penser à l'argent.",
    "Q19 Je me retrouve régulièrement dépendant d'autrui financièrement.",
    "Q21 J'évite souvent de faire face aux problèmes financiers, espérant qu'ils se règleront d'eux-mêmes.",
    "Q23 Je préfère qu'une autre personne s'occupe des détails financiers pour moi.",
    "Q49 Je souhaiterais ne pas avoir à penser à l'argent ni à le gérer.",
    "Q51 J'ai foi que, d'une certaine manière, les choses s'arrangeront toujours financièrement.",
    "Q53 Je ne ressens pas une forte connexion entre moi et l'argent.",
    "Q55 A l'extrême, je me sens submergé voire désarmé concernant l'argent et rêverais que le besoin d'argent disparaisse."
  ],
  "conservateur": [
    "Q18 J'aime épargner.",
    "Q20 Je trouve difficile voire douloureux de dépenser.",
    "Q22 Je m'assure de toujours vivre en dessous de mes moyens.",
    "Q24 Je trouve difficile de faire confiance à un tiers pour l'investissement de mon argent.",
    "Q50 A l'extrême, je me prive d'opportunités car je crains de dépenser ou d'investir.",
    "Q52 J'aime acheter en solde, en liquidation ou seconde main car je sais que je ferai une meilleure affaire.",
    "Q54 Epargner de grosses sommes me fait sentir en sécurité et protégé.",
    "Q56 Je ressens une forte connexion émotionnelle avec l'argent."
  ],
  "épicurien": [
    "Q25 J'aime dépenser fréquemment pour des objets car je ressens que je les mérite.",
    "Q27 La vie mérite d'être vécue au présent donc je ne vois pas l'intérêt d'épargner.",
    "Q29 Je ne trouve pas que l'argent soit si sérieux que ça.",
    "Q31 J'aime vivre des expériences luxueuses et/ou posséder beaucoup d'objets.",
    "Q57 Je suis intéressé à bien vivre, peu importe le coût.",
    "Q59 Puisque nous ne pouvons apporter notre argent dans l'au-delà, il doit être dépensé maintenant.",
    "Q61 Mes achats m'auto-valorisent, me font sentir bien.",
    "Q63 Je dépense impulsivement dans des situations critiques."
  ],
  "leader": [
    "Q26 Je ne sens jamais qu'il y a assez d'argent et je continue d'imaginer des façons d'en faire plus. *",
    "Q28 J'éprouve de la résistance à l'idée de diversifier ou de compliquer mes investissements.",
    "Q30 Je renonce à dépenser maintenant afin d'investir dans mon avenir.",
    "Q32 Ambitieux, je vois l'argent comme une mesure de mon succès.",
    "Q58 A l'extrême, je ne suis jamais satisfait de ce que je possède financièrement et m'efforce de gagner plus.",
    "Q60 Je suis plutôt catégorique en matière d'argent et de sa gestion.",
    "Q62 Je crois fermement que je serai heureux lorsque j'aurai plus d'argent ( et ce bien que j'en possède aujourd'hui plus qu' avant).",
    "Q64 Je crains de perdre le contrôle ou mon pouvoir sur l'argent."
  ]
}
"""

    private var questions = mutableListOf<Question>()
    init {
        val gson = Gson()
        val mapType = object : TypeToken<Map<String, List<String>>>() {}.type
        val questionsMap: Map<String, List<String>> = gson.fromJson(questionsJson, mapType)

        questionsMap.forEach { (personality, questionTexts) ->
            val idPersonnality = personalityMap[personality] ?: error("Personnalité pas trouvée")
            questionTexts.forEach { questionText ->
                val questionNumber = questionText.substringBefore(" ").removePrefix("Q").toInt()
                questions.add(
                    Question(
                        id = questionNumber, //Primary key
                        idPersonnality = idPersonnality, //Foreign key
                        questionText = questionText,
                        options = listOf(
                            Response(1, "Totalement en désaccord", false, 1),
                            Response(2, "En désaccord", false,2),
                            Response(3, "Neutre", false,3),
                            Response(4, "En accord", false,4),
                            Response(5, "Totalement en accord", false,5)
                        )
                    )
                )
            }
            questions = questions.sortedBy { question -> question.id }.toMutableList()
        }
    }

    /**
     * SELECTEUR AN PERSONALITY
     */
    fun totalPointPersonnality(id: Int): Int{
        var sumTotalPoint = 0
        questions.forEach{
            run {
                if(it.idPersonnality == id){
                    it.options.forEach{option ->
                        run {
                            if (option.isSelected) {
                                sumTotalPoint += option.point
                            }
                        }
                    }
                }
            }
        }
        return sumTotalPoint
    }

    /**
     * Obtenir la liste des questions de la classe
     */
    fun getQuestions() : MutableList<Question>{
        return questions
    }

    /**
     * Function pour séléctionner un profil
     */
    fun getPersonality(id: Int): String{
        return this.personalityMap.keys.toList()[id]
    }
    /**
     * Changer l'état d'option d'une question
     */
    fun changeIsSelectedStateOfResponse(indexOfQuestion: Int, indexOfOption: Int){
        questions[indexOfQuestion].options.forEach { option -> option.isSelected = false }
        questions[indexOfQuestion].options[indexOfOption].isSelected = true
    }
}

