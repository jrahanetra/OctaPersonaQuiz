package mg.geit.trioQuizzers.ui

data class User(
    val idUser: Int,
    val name: String,
    val email: String,
    val num: String,
)

data class UserResponse(
    val userId: Int,
    val responses: MutableList<Question> = mutableListOf()
)

object ListUser {
    private var currentId = 0
    private fun nextId() = ++currentId

    private var userList: MutableList<User> = mutableListOf(
        User(
            nextId(),
        "JasonRah",
        "jrahanetra@gmail.com",
        "0387777777"
        ),
        User(
            nextId(),
        "Anthony",
        "anthony@gmail.com",
        "0386666666"
        )
    )

    private val userResponses: MutableList<UserResponse> = mutableListOf()

    /**
     * Obtenir la liste User
     */
    fun getListUser() : MutableList<User>{
        return this.userList
    }

    /**
     * FONCTION POUR SÉLÉCTIONNER UN USER PAR SON ID
     * @param idUser : Int qui est l'id de l'user
     * @return User cela retourne l'user qui a pour id, idUSER donné en paramètre
     */
    fun selectUser(
        idUser: Int
    ) : User{
        return userList.firstOrNull { it.idUser == idUser } ?: User(0, "", "", "")
    }

    /**
     * FONCTION POUR SUPPRIMER UN USER PAR SON ID
     * @param idUser : Int qui est l'id de l'user à supprimer
     * @return MutableList<User> la liste à jour des users
     */
    fun removeUser(
        idUser: Int
    ): MutableList<User>
    {
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
    ) : String
    {
        userList.forEach {
            when {
                it.name == nomUser -> return "Name already used"
                it.email == emailUser -> return "Email already used"
                it.num == numUser -> return "Number already used"
            }
        }
        val newUser = User(nextId(), nomUser, emailUser, numUser)
        userList.add(newUser)
        userResponses.add(UserResponse(newUser.idUser))
        return "Add successful"
    }

    /**
     * Associer les réponses d'un utilisateur
     */
    fun associateResponsesToUser(userId: Int, responses: List<Question>) {
        val userResponse = userResponses.firstOrNull { it.userId == userId }
        userResponse?.responses?.clear()
        userResponse?.responses?.addAll(responses)
    }

    /**
     * Obtenir les réponses d'un utilisateur
     */
    fun getResponsesForUser(userId: Int): List<Question>? {
        return userResponses.firstOrNull { it.userId == userId }?.responses
    }
}