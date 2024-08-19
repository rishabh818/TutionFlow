package cg.tutorials.tutionflow.RecyclerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cg.tutorials.tutionflow.BatchData
import cg.tutorials.tutionflow.R
import cg.tutorials.tutionflow.interfaces.BatchRecyclerInterface

class BatchRecyclerAdapter(context: Context, var batchList:ArrayList<BatchData>,var batchRecyclerInterface: BatchRecyclerInterface): RecyclerView.Adapter<BatchRecyclerAdapter.ViewHolder>(){
    class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        var name_:TextView?= view.findViewById(R.id.tvNameBatch)
        var class_:TextView? = view.findViewById(R.id.tvClassBatch)
        var BtnDelete : ImageView? = view.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.batch_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return batchList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name_?.text = batchList[position].batchName
        holder.class_?.text = batchList[position].batchClass
        holder.BtnDelete?.setOnClickListener {
            batchRecyclerInterface.delete(position)
        }
    }

}