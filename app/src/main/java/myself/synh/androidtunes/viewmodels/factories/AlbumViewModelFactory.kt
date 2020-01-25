package myself.synh.androidtunes.viewmodels.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import myself.synh.androidtunes.views.listeners.AlbumListener

class AlbumViewModelFactory(
    private var context: Context,
    private var id: Long,
    private var listener: AlbumListener
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Context::class.java,
            Long::class.java,
            AlbumListener::class.java
        ).newInstance(context, id, listener)
    }
}