package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils

fun User.toUserView(): UserView {
    val nickName = Utils.transliteration("$firstName $lastName","@")//Самостоятельно
    val initials = Utils.toInitials(firstName,lastName)//Самостоятельно
    val status = if(lastVisit == null) "Еще не назу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit!!.humanizeDiff()}"

    return UserView(
        id,
        fullname = "$firstName, $lastName",
        nickname = nickName,
        initials = initials,
        avatar = avatar,
        status = status)

}

