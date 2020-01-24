package myself.synh.androidtunes.views


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_list.*
import myself.synh.androidtunes.R
import myself.synh.androidtunes.viewmodels.ListViewModel
import myself.synh.androidtunes.viewmodels.factories.ListViewModelFactory
import myself.synh.androidtunes.views.listeners.ListListener

class ListFragment : Fragment(R.layout.fragment_list), ListListener {

    private lateinit var listViewModel: ListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listViewModel = ViewModelProvider(this, ListViewModelFactory(context, this))
            .get(ListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listSearchEditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    hideKeyboard()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun hideKeyboard() {
        activity?.apply {
            val inputManager =
                (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            this.currentFocus?.let { view ->
                inputManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

}
