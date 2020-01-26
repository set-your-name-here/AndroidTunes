package myself.synh.androidtunes.viewmodels.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_list_item.view.*
import myself.synh.androidtunes.R
import myself.synh.androidtunes.models.entities.ResultItem
import myself.synh.androidtunes.views.listeners.RecyclerListener

class ListRecyclerAdapter(var items: ArrayList<ResultItem>, var listener: RecyclerListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ListRecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_list_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ListRecyclerViewHolder) {
            val item = items[position]
            val artistValue =
                "${holder.itemView.context.resources.getString(R.string.list_album_description_artist_name)} ${item.artistName}"
            holder.album.text = item.collectionName
            holder.artist.text = artistValue

            //Setup image of album
            if (item.artworkUrl60.isNotBlank()) {
                Picasso.get()
                    .load(item.artworkUrl100)
                    .into(holder.image)
            }
        }
    }

    inner class ListRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: AppCompatImageView = view.itemImage
        var album: AppCompatTextView = view.itemAlbumName
        var artist: AppCompatTextView = view.itemArtistName

        init {
            view.setOnClickListener {
                listener.openInfo(items[adapterPosition])
            }
        }
    }

}