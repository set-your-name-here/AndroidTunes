package myself.synh.androidtunes.views


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album.*
import myself.synh.androidtunes.R
import myself.synh.androidtunes.viewmodels.AlbumViewModel
import myself.synh.androidtunes.viewmodels.factories.AlbumViewModelFactory
import myself.synh.androidtunes.views.listeners.AlbumListener

class AlbumFragment : Fragment(R.layout.fragment_album), AlbumListener {

    companion object{
        private const val DP_IMAGE_SIZE = 200
    }

    private lateinit var albumViewModel: AlbumViewModel
    private var albumCollectionId: Long = 0L

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let { bundle ->
            albumCollectionId = bundle.getLong(ListFragment.ALBUM_ID_TAG)
        }
        albumViewModel =
            ViewModelProvider(this, AlbumViewModelFactory(context, albumCollectionId, this))
                .get(AlbumViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumToolbar.setupWithNavController(findNavController())
        setupAlbumTracks()
    }

    override fun setupAlbumDescription() {
        albumViewModel.album?.let { a ->
            albumTitle.text = a.collectionName
            val px = convertDpToPx(DP_IMAGE_SIZE)
            Picasso.get()
                .load(a.artworkUrl100)
                .resize(px, px)
                .into(albumCover)
        }
    }

    private fun setupAlbumTracks() {
        albumRecyclerView.apply {
            adapter = albumViewModel.albumAdapter
            layoutManager = albumViewModel.albumLayoutManager
        }
    }

    private fun convertDpToPx(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()
}
