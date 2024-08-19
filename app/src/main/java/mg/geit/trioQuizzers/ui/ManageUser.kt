package mg.geit.trioQuizzers.ui

data class User(
    val idUser: Int,
    val name: String,
    val email: String,
    val num: String,
    var result: Map<String, Int>
)

data class UserResponse(
    val userId: Int,
    val responses: MutableList<Question> = mutableListOf()
)

object ListUser {
    private var currentId = 0
    private fun nextId() = ++currentId

    private var resultQuiz: Map<String, Int> = mapOf()

    private var userList: MutableList<User> = mutableListOf(
        User(
            nextId(),
            "JasonRah",
            "jrahanetra@gmail.com",
            "0387777777",
            mapOf(
                "bienfaiteur" to 40,
                "leader" to 40,
                "médiateur" to 34,
                "visionnaire" to 30,
                "célébrité" to 20,
                "insoumis" to 10,
                "conservateur" to 0,
                "épicurien" to 0,
            )
        ),
        User(
            nextId(),
            "Anthony",
            "anthony@gmail.com",
            "0386666666",
            mapOf(
                "bienfaiteur" to 40,
                "célébrité" to 40,
                "médiateur" to 34,
                "leader" to 30,
                "visionnaire" to 30,
                "insoumis" to 15,
                "conservateur" to 12,
                "épicurien" to 12,
            )
        )
    )

    private val userResponses: MutableList<UserResponse> = mutableListOf()

    /**
     * Obtenir la liste User
     */
    fun getListUser(): MutableList<User> {
        return this.userList
    }

    /**
     * FONCTION POUR SÉLÉCTIONNER UN USER PAR SON ID
     * @param idUser : Int qui est l'id de l'user
     * @return User cela retourne l'user qui a pour id, idUSER donné en paramètre
     */
    fun selectUser(
        idUser: Int
    ): User {
        return userList.firstOrNull { it.idUser == idUser } ?: User(0, "", "", "", mapOf())
    }

    /**
     * FONCTION POUR SÉLÉCTIONNER UN USER PAR SON ID
     * @param nameStr : String qui est l'id de l'user
     * @return User cela retourne l'user qui a pour id, idUSER donné en paramètre
     */
    fun selectUserByName(
        nameStr: String
    ): User {
        return userList.firstOrNull { it.name == nameStr } ?: User(0, "", "", "", mapOf())
    }

    /**
     * FONCTION POUR SUPPRIMER UN USER PAR SON ID
     * @param idUser : Int qui est l'id de l'user à supprimer
     * @return MutableList<User> la liste à jour des users
     */
    fun removeUser(
        idUser: Int
    ): MutableList<User> {
        val produit = selectUser(idUser)
        produit.let {
            userList.remove(it)
        }
        return userList
    }

    /**
     * FONCTION POUR AJOUTER UN USER ET RETOURNE LA LISTE_USER
     * @param nomUser : String
     * @param emailUser : String
     * @param numUser : String
     * @return MutableList<User>
     */
    fun addUser(
        nomUser: String,
        emailUser: String,
        numUser: String
    ): String {
        userList.forEach {
            when {
                it.name == nomUser -> return "Vérifiez votre Nom (+4 de Caractères et unique)"
                it.email == emailUser -> return "Vérifiez votre email unique"
                it.num == numUser -> return "Vérifiez votre numéros (10 chiffres et unique)"
            }
        }
        val newUser = User(nextId(), nomUser, emailUser, numUser, resultQuiz)
        userList.add(newUser)
        userResponses.add(UserResponse(newUser.idUser))
        return "Ajouté avec succès"
    }
}