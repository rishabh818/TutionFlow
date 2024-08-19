package cg.tutorials.tutionflow.RecyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cg.tutorials.tutionflow.R

class BatchAdapter: RecyclerView.Adapter<BatchAdapter.ViewHolder>() {
    class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.batch_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
return 2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

}