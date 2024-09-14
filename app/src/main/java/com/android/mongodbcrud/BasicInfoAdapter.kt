package com.android.mongodbcrud

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.android.mongodbcrud.model.BasicInfo
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject


class BasicInfoAdapter(private var basicInfoList: List<BasicInfo>) :
    RecyclerView.Adapter<BasicInfoAdapter.BasicInfoViewHolder>() {

    class BasicInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAge: TextView = itemView.findViewById(R.id.tvAge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)
        return BasicInfoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BasicInfoViewHolder, position: Int) {
        val basicInfo = basicInfoList[position]
        holder.tvName.text = basicInfo.name
        holder.tvAge.text = basicInfo.age.toString()
    }

    override fun getItemCount(): Int {
        return basicInfoList.size
    }

    fun updateData(basicInfoList: List<BasicInfo>) {
        this.basicInfoList = basicInfoList
        notifyDataSetChanged()
    }
}