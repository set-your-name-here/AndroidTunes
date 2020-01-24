package myself.synh.androidtunes.viewmodels.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import myself.synh.androidtunes.views.listeners.ListListener

class ListViewModelFactory(private var context: Context, private var listener: ListListener) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Context::class.java, ListListener::class.java)
            .newInstance(context, listener)
    }

}