package myself.synh.androidtunes.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import myself.synh.androidtunes.App
import myself.synh.androidtunes.R
import myself.synh.androidtunes.models.entities.ResultItem
import myself.synh.androidtunes.models.retrofit.RequestCountryCode
import myself.synh.androidtunes.models.retrofit.RequestMediaEntity
import myself.synh.androidtunes.models.retrofit.RequestMediaType
import myself.synh.androidtunes.viewmodels.adapters.ListRecyclerAdapter
import myself.synh.androidtunes.views.listeners.ListListener
import myself.synh.androidtunes.views.listeners.RecyclerListener
import java.util.*
import kotlin.collections.ArrayList

class ListViewModel(var context: Context, private var listener: ListListener) :
    ViewModel(), RecyclerListener {

    companion object {
        private var LOAD_TAG = "AlbumsLoader"
        private var LOAD_ERROR_LOG_MESSAGE = "Loading error"
    }

    private var listDisposable = CompositeDisposable()
    private var listLoadErrorToast = Toast.makeText(
        context,
        context.resources.getString(R.string.list_error_value),
        Toast.LENGTH_SHORT
    )
    private var listLastSearchTerm: String = ""

    var listAdapter: ListRecyclerAdapter = ListRecyclerAdapter(ArrayList(), this)
    var listLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

    fun loadAlbumsByTerm(term: String) {
        if (term.isNotBlank()) {
            listener.showProgressBar()
            listener.hideEmptyList()
            reformatTerm(term)
            listLastSearchTerm = term
            listDisposable.add(
                App.retrofit
                    .search(
                        term = term,
                        country = RequestCountryCode.RU.value,
                        media = RequestMediaType.MUSIC.value,
                        entity = RequestMediaEntity.ALBUM.value
                    )
                    .map { result ->
                        if (result.resultCount != 0) {
                            result.results.sortBy { resultItem -> resultItem.collectionName }
                        }
                        return@map result
                    }
                    .subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        if (result.resultCount != 0) {
                            listAdapter.items = result.results
                            listAdapter.notifyDataSetChanged()
                        } else {
                            listAdapter.items = ArrayList()
                            listAdapter.notifyDataSetChanged()
                            listener.showEmptyList()
                        }
                        listener.hideProgressBar()
                        listener.stopRefresh()
                    }, { t ->
                        Log.e(LOAD_TAG, LOAD_ERROR_LOG_MESSAGE, t)
                        listLoadErrorToast.show()
//                        listener.hideProgressBar()
                        listener.stopRefresh()
                    })
            )
        }
    }

    private fun reformatTerm(term: String) {
        term.toLowerCase(Locale.ROOT)
        term.replace(" ", "+")
    }

    fun reloadList() {
        if (listLastSearchTerm.isNotBlank()) {
            loadAlbumsByTerm(listLastSearchTerm)
        } else {
            listener.stopRefresh()
        }
    }

    override fun onCleared() {
        super.onCleared()
        listDisposable.clear()
    }

    override fun openInfo(album: ResultItem) {
        listener.showAlbumInfo(album)
    }

}