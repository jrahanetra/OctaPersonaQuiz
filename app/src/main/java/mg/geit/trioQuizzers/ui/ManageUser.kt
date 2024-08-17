package mg.geit.trioQuizzers.ui

data class User(
    val idUser: Int,
    val name: String,
    val email: String,
    val num: String
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
        var user1 = User(0,"","","")
        for(user in userList){
            if (user.idUser == idUser){
                user1 = user
            }
        }
        return user1
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
                it.name == nomUser -> return "Nom déjà utilisé"
                it.email == emailUser -> return "Email déjà utilisé"
                it.num == numUser -> return "Numéros déjà utilisé"
            }
        }
        return "Utilisateur ajouté avec succés"
    }
}