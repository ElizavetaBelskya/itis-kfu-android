package ru.kpfu.itis.hometask4

import kotlin.random.Random

class RecyclerModelGenerator {
    private val listName = listOf<String>(
        "Favourites",
        "Best",
        "My playlist",
        "Best album",
        "I love it",
        "Heavy metal",
        "Hip-hop",
        "Electronic",
        "Pop music"
    );

    private val listAuthors = listOf<String>(
        "Metallica",
        "Ария",
        "Король и шут",
        "ДДТ",
        "Scorpions",
        "Pink floyd"
    )

    private val listImagePath = listOf<String>(
        "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/stylish-minimal-gold-album-song-cover-art-design-template-615cce43b81d0941008c60705c14709d_screen.jpg?ts=1635272591",
        "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/music-album-cover-design-template-1b27d21f12b3ce9dceaccc1e663da8a9_screen.jpg?ts=1561485221",
        "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/retro-neon-world-album-cover-video-design-template-a76b0d95118986d4e1326e138993c0ae_screen.jpg?ts=1635274330",
        "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/car-album-cd-cover-template-design-8567a354717be3a1148feeb774726db3_screen.jpg?ts=1616063023",
        "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/electronic-music-album-cover-template-design-12b51a1ad56ef5851bc8068dd8c79aaf_screen.jpg?ts=1599075720",
        "https://1757140519.rsc.cdn77.org/blog/wp-content/uploads/sites/2/2021/02/DouqiR_U4AAoziP-1024x1024.jpg",
        "http://www.etoday.ru/assets/2016/1/11/StarWarsAlbums00.jpg",
        "https://kartinkin.net/uploads/posts/2022-03/thumbs/1647931122_2-kartinkin-net-p-kartinki-dlya-alboma-muziki-3.jpg",
        "https://krot.info/uploads/posts/2022-01/1642651908_10-krot-info-p-oblozhka-art-23.jpg"
    )


    fun generateModel(maxId: Int): RecyclerModel {
        var nameRandIndex = Random.nextInt(listName.size)
        var authorRandIndex = Random.nextInt(listAuthors.size)
        var imagePathRandIndex = Random.nextInt(listImagePath.size)
        var typeRandIndex = Random.nextInt(RecyclerTypes.values().size)
        return RecyclerModel(
            maxId+1,
            listName[nameRandIndex],
            listAuthors[authorRandIndex],
            listImagePath[imagePathRandIndex],
            RecyclerTypes.values()[typeRandIndex], 1)
    }

}
