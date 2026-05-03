package com.example.kopiqueapps.core

object Routes {
    const val LOGIN = "login"
    const val HOME = "home"
    const val DETAIL = "detail/{menuId}"

    fun detail(menuId: Int) = "detail/$menuId"
}
