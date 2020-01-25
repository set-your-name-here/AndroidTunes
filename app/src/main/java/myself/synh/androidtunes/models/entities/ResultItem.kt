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
    var artistId: Long = 0L,
    @SerializedName("collectionId")
    var collectionId: Long = 0L,
    @SerializedName("trackId")
    var trackId: Long = 0L,
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
    var collectionPrice: Float = 0f,
    @SerializedName("currency")
    var currency: String = String(),
    @SerializedName("trackCount")
    var trackCount: Int = 0,
    @SerializedName("trackTimeMillis")
    var trackTimeMillis: Long = 0L, //The returned track’s time in milliseconds.
    @SerializedName("primaryGenreName")
    var primaryGenreName: String = String(),
    @SerializedName("copyright")
    var copyright: String = String()
): Parcelable