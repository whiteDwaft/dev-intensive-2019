package ru.skillbranch.dev_intensive.models
import ru.skillbranch.dev_intensive.utils.Utils
import java.util.*
 data class User(
    val id:String,
    var firstName:String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false)
{
//    var introBit:String

    constructor(id:String,firstName: String?,lastName: String?) : this(id,firstName, lastName, null)
    constructor(id:String):this(id, "john", "doe")
    init {
//        introBit = getIntro()
        println("It's Alive!!! \n" +
                " ${if(lastName==="doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName!!!"}")
    }
    companion object Factory{
        private var lastId: Int = -1
        fun makeUser(fullname:String?): User{
            lastId++

//            val parts: List<String>? = fullname?.split(" ")
//
//            val firstName = parts?.getOrNull(0)
//            val lastName = parts?.getOrNull(1)
            val (firstName, lastName) = Utils.parseFullName(fullname)
            return User("$lastId", firstName, lastName)
        }
    }
    class Builder{
        private var id:String = ""
        private var firstName:String? = ""
        private var lastName: String? = ""
        private var avatar: String? = ""
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = null
        private var isOnline: Boolean = false

        fun id(id:String) = apply{this.id = id}
        fun firstName(firstName: String?) = apply{this.firstName = firstName}
        fun lastName(lastName: String?) = apply{this.lastName = lastName}
        fun avatar(avatar: String?) = apply{this.avatar = avatar}
        fun rating(rating: Int) = apply{this.rating = rating}
        fun respect(respect: Int) = apply{this.respect = respect}
        fun lastVisit(lastVisit:Date) = apply{this.lastVisit = lastVisit}
        fun isOnline(isOnline:Boolean) = apply{this.isOnline = isOnline}


        fun build(): User{
            return User(id,firstName,lastName,avatar,rating,respect,lastVisit,isOnline)
        }
}

//    private fun getIntro() = """
//        tu tu tu tuuuuuuuu!!!
//        tu tu tu tuuuuuuuuuu...
//
//        tu tu tu tuuuuuuuu!!!
//        tu tu tu tuuuuuuuuuu...
//
//        ${"\n\n\n"}
//        $firstName $lastName
//    """.trimIndent()
//
//
//    fun printMe() =println("""
//    id: $id
//    firstName: $firstName
//    lastName:  $lastName
//    avatar:   $avatar
//    rating: $rating
//    respect:  $respect
//    lastVisit:  $lastVisit
//    isOnline: $isOnline
//        """)
    }
