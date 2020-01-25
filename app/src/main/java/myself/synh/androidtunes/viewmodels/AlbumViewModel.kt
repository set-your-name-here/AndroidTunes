package myself.synh.androidtunes.viewmodels

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import myself.synh.androidtunes.App
import myself.synh.androidtunes.R
import myself.synh.androidtunes.models.entities.AlbumDescriptionItem
import myself.synh.androidtunes.models.entities.ResultItem
import myself.synh.androidtunes.models.retrofit.RequestCountryCode
import myself.synh.androidtunes.models.retrofit.RequestMediaEntity
import myself.synh.androidtunes.viewmodels.adapters.AlbumDescriptionRecyclerAdapter
import myself.synh.androidtunes.viewmodels.adapters.AlbumRecyclerAdapter
import myself.synh.androidtunes.views.listeners.AlbumListener

class AlbumViewModel(var context: Context, collectionId: Long, listener: AlbumListener) :
    ViewModel() {

    companion object {
        private const val TEXT_SPACE_SEPARATOR = " "
        private const val TEXT_EMPTY = ""
    }

    var albumAdapter: AlbumRecyclerAdapter = AlbumRecyclerAdapter(ArrayList())
    var albumLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
    var album: ResultItem? = null
    var albumTracks: ArrayList<ResultItem> = ArrayList()
    var descriptionAdapter: AlbumDescriptionRecyclerAdapter = AlbumDescriptionRecyclerAdapter(
        ArrayList()
    )
    var descriptionLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

    private var albumDisposable = CompositeDisposable(
        App.retrofit
            .lookUp(
                id = collectionId,
                country = RequestCountryCode.RU.value,
                entity = RequestMediaEntity.SONG.value
            )
            .filter { result -> result.resultCount != 0 }
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                buildDescription(result.results[0])
                result.results.removeAt(0)
                buildTracks(result.results)
                listener.setupAlbumDescription()
            }
    )

    override fun onCleared() {
        super.onCleared()
        albumDisposable.clear()
    }

    private fun buildTracks(trackList: ArrayList<ResultItem>) {
        albumTracks = trackList
        albumAdapter.apply {
            tracks = trackList
            notifyDataSetChanged()
        }
    }

    private fun buildDescription(descriptionItem: ResultItem) {
        album = descriptionItem
        val descriptionItems = arrayListOf(
            AlbumDescriptionItem(
                title = context.resources.getString(R.string.list_album_description_artist_name),
                value = descriptionItem.artistName
            ),
            AlbumDescriptionItem(
                title = context.resources.getString(R.string.list_album_description_track_count),
                value = descriptionItem.trackCount.toString()
            ),
            AlbumDescriptionItem(
                title = context.resources.getString(R.string.list_album_description_genre),
                value = descriptionItem.primaryGenreName
            ),
            AlbumDescriptionItem(
                title = TEXT_EMPTY,
                value = StringBuilder()
                    .append(context.resources.getString(R.string.list_album_description_copyright))
                    .append(TEXT_SPACE_SEPARATOR)
                    .append(descriptionItem.copyright)
                    .toString()
            )
        )
        descriptionAdapter.items = descriptionItems
        descriptionAdapter.notifyDataSetChanged()
    }

}