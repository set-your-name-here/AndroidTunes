package myself.synh.androidtunes.views.listeners

import myself.synh.androidtunes.models.entities.ResultItem

interface RecyclerListener {
    fun openInfo(album: ResultItem)
}