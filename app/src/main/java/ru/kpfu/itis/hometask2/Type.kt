package ru.kpfu.itis.hometask2

data class Type (val name: String,
                 val description: String,
                 val pathToImage: String,
                 var isClicked: Boolean = false)
