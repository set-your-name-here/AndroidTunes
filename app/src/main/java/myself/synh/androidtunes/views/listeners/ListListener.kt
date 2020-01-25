package myself.synh.androidtunes.views.listeners

interface ListListener {

    fun showProgressBar()
    fun hideProgressBar()
    fun showEmptyList()
    fun hideEmptyList()
    fun stopRefresh()

}