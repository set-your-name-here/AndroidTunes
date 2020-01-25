package myself.synh.androidtunes.viewmodels.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_album_description_item.view.*
import myself.synh.androidtunes.R
import myself.synh.androidtunes.models.entities.AlbumDescriptionItem

class AlbumDescriptionRecyclerAdapter(var items: ArrayList<AlbumDescriptionItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        AlbumDescriptionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_album_description_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AlbumDescriptionViewHolder) {
            val item = items[position]
            holder.title.text = item.title
            holder.value.text = item.value
        }
    }

    inner class AlbumDescriptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: AppCompatTextView = view.descriptionTitle
        var value: AppCompatTextView = view.descriptionValue
    }

}