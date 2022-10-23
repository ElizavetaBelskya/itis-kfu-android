package ru.kpfu.itis.hometask4

object ListRepository {
    var itemsList = mutableListOf<RecyclerModel>(
        RecyclerModel(1, "My favourites", null, "https://cdn-icons-png.flaticon.com/512/2912/2912305.png",
        RecyclerTypes.FAVOURITE_TYPE),
        RecyclerModel(2, "Я хотел быть на дискотеке", null, "https://marketplace.canva.com/EAEfl2fVfFc/1/0/1600w/canva-%D1%80%D0%BE%D0%B7%D0%BE%D0%B2%D1%8B%D0%B9-%D0%B8-%D1%84%D0%B8%D0%BE%D0%BB%D0%B5%D1%82%D0%BE%D0%B2%D1%8B%D0%B9-%D0%BE%D0%BA%D0%BD%D0%BE-90-%D0%B5-%D0%B0%D0%BD%D0%B8%D0%BC%D0%B5-%D0%B4%D0%BB%D1%8F-%D0%B2%D0%B5%D1%87%D0%B5%D1%80%D0%B8%D0%BD%D0%BA%D0%B8-%D1%82%D0%B0%D0%BD%D1%86%D0%B5%D0%B2%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9-%D0%B2-%D1%81%D1%82%D0%B8%D0%BB%D0%B5-%D0%BF%D0%BE%D0%BF-%D0%BE%D0%B1%D0%BB%D0%BE%D0%B6%D0%BA%D0%B0-%D0%BF%D0%BB%D0%B5%D0%B9%D0%BB%D0%B8%D1%81%D1%82%D0%B0-kuWCua6IFXg.jpg",
        RecyclerTypes.FAVOURITE_TYPE),
        RecyclerModel(3, "MUSIC", null, "https://tgram.ru/wiki/channels/image/mytoplist.jpg",
        RecyclerTypes.FAVOURITE_TYPE),
        RecyclerModel(4, "Свежие биты", null, "https://marketplace.canva.com/EAEfmIfaCTI/1/0/1600w/canva-%D0%BD%D0%B5%D0%BE%D0%BD%D0%BE%D0%B2%D1%8B%D0%B9-%D0%B7%D0%B5%D0%BB%D0%B5%D0%BD%D1%8B%D0%B9-%D0%B8-%D1%87%D0%B5%D1%80%D0%BD%D1%8B%D0%B9-%D0%B1%D1%80%D0%BE%D1%81%D0%BA%D0%B8%D0%B9-%D0%B3%D0%BE%D1%80%D0%BE%D0%B4%D1%81%D0%BA%D0%BE%D0%B9-%D1%80%D1%8D%D0%BF-%D1%85%D0%B8%D0%BF-%D1%85%D0%BE%D0%BF-%D0%BE%D0%B1%D0%BB%D0%BE%D0%B6%D0%BA%D0%B0-%D0%BF%D0%BB%D0%B5%D0%B9%D0%BB%D0%B8%D1%81%D1%82%D0%B0-dA6Fip3JYcA.jpg",
        RecyclerTypes.FAVOURITE_TYPE),

        RecyclerModel(5, "Playlists", null, "https://cdn-icons-png.flaticon.com/512/3415/3415652.png",
        RecyclerTypes.MENU_TYPE),
        RecyclerModel(6, "Tracks", null, "https://cdn-icons-png.flaticon.com/512/7513/7513150.png",
        RecyclerTypes.MENU_TYPE),
        RecyclerModel(7, "Albums", null, "https://cdn-icons-png.flaticon.com/512/4020/4020800.png",
        RecyclerTypes.MENU_TYPE),
        RecyclerModel(8, "Artists", null, "https://cdn-icons-png.flaticon.com/512/856/856471.png",
        RecyclerTypes.MENU_TYPE),
        RecyclerModel(9, "Podcasts", null, "https://cdn-icons-png.flaticon.com/512/2882/2882867.png",
        RecyclerTypes.MENU_TYPE),
        RecyclerModel(10, "Downloaded", null, "https://cdn-icons-png.flaticon.com/512/54/54993.png",
        RecyclerTypes.MENU_TYPE),
        RecyclerModel(11, "For kids", null, "https://cdn-icons-png.flaticon.com/512/8267/8267662.png",
        RecyclerTypes.MENU_TYPE),

        RecyclerModel(12, "INFINITY", "playingtheangel", "https://images.genius.com/a5ebbdf4bcea66418e2a4efe5c42378f.1000x1000x1.jpg",
        RecyclerTypes.ALBUM_TYPE),
        RecyclerModel(13, "Горгород", "Oxxxymiron", "https://upload.wikimedia.org/wikipedia/commons/3/3d/Cover_of_Gorgorod.jpg",
        RecyclerTypes.ALBUM_TYPE ),
        RecyclerModel(14, "Meteora", "Linkin Park", "https://upload.wikimedia.org/wikipedia/ru/b/bf/Meteora.jpg",
        RecyclerTypes.ALBUM_TYPE),
        RecyclerModel(15, "YAKUZA", "playingtheangel", "https://i.scdn.co/image/ab67616d0000b273ee99b709925d4f89006fa0e9",
        RecyclerTypes.ALBUM_TYPE),
        RecyclerModel(16, "ECLIPSE", "pyrokinesis", "https://is3-ssl.mzstatic.com/image/thumb/Music125/v4/53/5a/a2/535aa2b1-5ea3-d363-7732-7d0b0c2e957d/cover.jpg/600x600bf-60.jpg",
        RecyclerTypes.ALBUM_TYPE),
        RecyclerModel(17, "Kamikaze", "Eminem", "https://upload.wikimedia.org/wikipedia/ru/1/1c/Eminem-kamikaze.jpg",
        RecyclerTypes.ALBUM_TYPE)
    )

}
