package myself.synh.androidtunes.viewmodels.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_album_item.view.*
import myself.synh.androidtunes.R
import myself.synh.androidtunes.models.entities.ResultItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AlbumRecyclerAdapter(var tracks: ArrayList<ResultItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        AlbumRecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_album_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = tracks.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AlbumRecyclerViewHolder) {
            val track = tracks[position]
            holder.name.text = track.trackName
            holder.time.text = formatTime(track.trackTimeMillis)
        }
    }

    inner class AlbumRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: AppCompatTextView = view.trackName
        var time: AppCompatTextView = view.trackTimes
    }

    private fun formatTime(timestamp: Long): String{
        val simpleDateFormat = SimpleDateFormat("mm:ss", Locale.ROOT)
        val time = Date(timestamp)
        return simpleDateFormat.format(time)
    }

}