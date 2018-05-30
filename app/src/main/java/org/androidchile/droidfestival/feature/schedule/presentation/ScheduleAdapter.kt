package org.androidchile.droidfestival.feature.schedule.presentation

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_schedule.view.*
import org.androidchile.droidfestival.R
import org.androidchile.droidfestival.fundation.extension.inflate
import javax.inject.Inject
import kotlin.properties.Delegates

class ScheduleAdapter
@Inject constructor() : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    internal var collection: List<ScheduleView> by Delegates.observable(emptyList()) {
        _, _, _ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.row_schedule))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position])

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(scheduleView: ScheduleView) {
            itemView.title.text = scheduleView.title
            itemView.description.text = scheduleView.description
        }
    }
}
