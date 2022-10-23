package ru.kpfu.itis.hometask4

data class RecyclerModel(
    val id: Int,
    val name: String,
    val information: String?,
    val pathToImage: String,
    val type: RecyclerTypes,
    var state: Int = 1
)
