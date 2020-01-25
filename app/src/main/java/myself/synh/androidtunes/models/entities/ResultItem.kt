package myself.synh.androidtunes.models.entities

data class ResultItem(
    var wrapperType: String,
    var kind: String = String(),
    var artistId: Long,
    var collectionId: Long,
    var artistName: String,
    var collectionName: String,
    var artworkUrl60: String = String(),
    var artworkUrl100: String = String(),
    var collectionPrice: Float = 0f,
    var currency: String = String()
)