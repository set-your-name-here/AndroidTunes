package myself.synh.androidtunes.views


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_list.*
import myself.synh.androidtunes.R
import myself.synh.androidtunes.models.entities.ResultItem
import myself.synh.androidtunes.viewmodels.ListViewModel
import myself.synh.androidtunes.viewmodels.factories.ListViewModelFactory
import myself.synh.androidtunes.views.listeners.ListListener

class ListFragment : Fragment(R.layout.fragment_list), ListListener {

    companion object {
        const val ALBUM_ID_TAG = "AlbumID"
    }

    private lateinit var listViewModel: ListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listViewModel = ViewModelProvider(this, ListViewModelFactory(context, this))
            .get(ListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Setup adapter and layout manager for recycler view with albums
        listRecyclerView.apply {
            adapter = listViewModel.listAdapter
            layoutManager = listViewModel.listLayoutManager
        }

        //Set listener for search editor
        listSearchEditText.setOnEditorActionListener { v, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    hideKeyboard()
                    val value = v.text.toString()
                    if (value.isNotBlank()) {
                        listViewModel.loadAlbumsByTerm(value)
                    }
                    true
                }
                else -> {
                    false
                }
            }
        }

        listRefreshLayout.setOnRefreshListener {
            listViewModel.reloadList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Remove adapter and layout manager for recycler view with albums
        listRecyclerView.apply {
            adapter = null
            layoutManager = null
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

    override fun showProgressBar() {
        listProgressBar.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        listProgressBar.visibility = GONE
    }

    override fun showEmptyList() {
        listEmptyResult.visibility = VISIBLE
    }

    override fun hideEmptyList() {
        listEmptyResult.visibility = GONE
    }

    override fun stopRefresh() {
        listRefreshLayout.isRefreshing = false
    }

    override fun showAlbumInfo(album: ResultItem) {
        findNavController().navigate(
            R.id.action_listFragment_to_albumFragment, bundleOf(
                ALBUM_ID_TAG to album.collectionId
            )
        )
    }

}
