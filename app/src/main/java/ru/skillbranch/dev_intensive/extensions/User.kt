package ru.skillbranch.dev_intensive.extensions

import ru.skillbranch.dev_intensive.models.User
import ru.skillbranch.dev_intensive.models.UserView
import ru.skillbranch.dev_intensive.utils.Utils
import java.util.*

fun User.toUserView(): UserView {
    val nickName = Utils.transliteration("$firstName $lastName")//Самостоятельно
    val initials = Utils.toInitials(firstName,lastName)//Самостоятельно
    val status = if(lastVisit == null) "Еще не назу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()}"

    return UserView(
        id,
        fullname = "$firstName, $lastName",
        nickname = nickName,
        initials = initials,
        avatar = avatar,
        status = status)

}

