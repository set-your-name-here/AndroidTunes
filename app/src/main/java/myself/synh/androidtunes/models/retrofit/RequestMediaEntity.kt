package myself.synh.androidtunes.models.retrofit

enum class RequestMediaEntity(val value: String){

    //For media type - podcast
    PODCAST_AUTHOR("podcastAuthor"),
    PODCAST("podcast"),

    //For media type - music
    MUSIC_ARTIST("musicArtist"),
    MUSIC_TRACK("musicTrack"),
    ALBUM("album"),
    MIX("mix"),
    SONG("song"),

    //For media type - audio books
    BOOK_AUTHOR("audiobookAuthor"),
    AUDIO_BOOK("audiobook"),

}