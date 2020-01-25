package myself.synh.androidtunes.views.listeners

import myself.synh.androidtunes.models.entities.ResultItem

interface ListListener {

    fun showProgressBar()
    fun hideProgressBar()
    fun showEmptyList()
    fun hideEmptyList()
    fun stopRefresh()
    fun showAlbumInfo(album: ResultItem)

}