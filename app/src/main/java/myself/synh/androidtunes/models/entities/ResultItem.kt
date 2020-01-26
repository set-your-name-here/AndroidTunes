package myself.synh.androidtunes.models.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultItem(
    @SerializedName("wrapperType")
    var wrapperType: String, //The name of the object returned by the search request.
    @SerializedName("kind")
    var kind: String = String(), //The kind of content returned by the search request.
    @SerializedName("artistId")
    var artistId: Long = 0L, //A identifier of artist
    @SerializedName("collectionId")
    var collectionId: Long = 0L, //A identifier of collection
    @SerializedName("trackId")
    var trackId: Long = 0L, //A identifier of the track
    @SerializedName("artistName")
    var artistName: String, //The name of the artist returned by the search request.
    @SerializedName("collectionName")
    var collectionName: String, //The name of the album, TV season, audiobook, and so on returned by the search request.
    @SerializedName("trackName")
    var trackName: String, //The name of the track, song, video, TV episode, and so on returned by the search request.
    @SerializedName("artworkUrl60")
    var artworkUrl60: String = String(), //A URL for the artwork associated with the returned media type, sized to 100×100 pixels or 60×60 pixels.
    @SerializedName("artworkUrl100")
    var artworkUrl100: String = String(), //A URL for the artwork associated with the returned media type, sized to 100×100 pixels or 60×60 pixels.
    @SerializedName("collectionPrice")
    var collectionPrice: Float = 0f, //The price of the album, TV season, audiobook and etc.
    @SerializedName("currency")
    var currency: String = String(), //The currency of the price
    @SerializedName("trackCount")
    var trackCount: Int = 0, //A count of the tracks in album
    @SerializedName("trackTimeMillis")
    var trackTimeMillis: Long = 0L, //The returned track’s time in milliseconds.
    @SerializedName("primaryGenreName")
    var primaryGenreName: String = String(), //A genre of the tracks in album
    @SerializedName("copyright")
    var copyright: String = String() //The copyright value
): Parcelable