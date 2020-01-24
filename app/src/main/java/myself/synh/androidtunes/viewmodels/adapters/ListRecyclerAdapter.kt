package myself.synh.androidtunes.viewmodels.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import myself.synh.androidtunes.R
import myself.synh.androidtunes.models.entities.ResultItem

class ListRecyclerAdapter(var items: ArrayList<ResultItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

        }
    }

    inner class ListRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

}