package myself.synh.androidtunes.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import myself.synh.androidtunes.App
import myself.synh.androidtunes.models.entities.ResultItem
import myself.synh.androidtunes.models.retrofit.RequestCountryCode
import myself.synh.androidtunes.models.retrofit.RequestMediaEntity
import myself.synh.androidtunes.viewmodels.adapters.AlbumDescriptionRecyclerAdapter
import myself.synh.androidtunes.viewmodels.adapters.AlbumRecyclerAdapter
import myself.synh.androidtunes.views.listeners.AlbumListener

class AlbumViewModel(context: Context, collectionId: Long, listener: AlbumListener) : ViewModel() {

    var albumAdapter: AlbumRecyclerAdapter = AlbumRecyclerAdapter(ArrayList())
    var albumLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
    var album: ResultItem? = null
    var albumTracks: ArrayList<ResultItem> = ArrayList()
    var descriptionAdapter: AlbumDescriptionRecyclerAdapter = AlbumDescriptionRecyclerAdapter(
        ArrayList()
    )

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
                albumTracks = result.results
                albumAdapter.apply {
                    tracks = result.results
                    notifyDataSetChanged()
                }
                listener.setupAlbumDescription()
            }
    )

    override fun onCleared() {
        super.onCleared()
        albumDisposable.clear()
    }

    private fun buildDescription(descriptionItem: ResultItem) {
        album = descriptionItem
    }

}