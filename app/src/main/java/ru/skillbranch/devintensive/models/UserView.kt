package ru.skillbranch.devintensive.models

class UserView(
    val id: String,
    val fullname: String,
    val nickname: String,
    var avatar:String? = null,
    var status:String? = "offline",
    val initials:String?
)
{
    fun printMe(){
        println("""
            id: $id
            fullName: $fullname
            nickName; $nickname
            avatar: $avatar
            statis: $status
            initials: $initials
        """.trimIndent())
    }
}