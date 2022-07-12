package com.nandaiqbalh.suitmediamobiletest.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nandaiqbalh.suitmediamobiletest.R
import com.nandaiqbalh.suitmediamobiletest.data.response.UserItem


class UserAdapter(private var data: List<UserItem>,
                        private val listener: (UserItem) -> Unit)
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var contextAdapter:Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_user, parent, false)
        return ViewHolder(inflatedView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItem(data[position], listener, contextAdapter)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val ivPhoto: ImageView = view.findViewById(R.id.item_iv_photo)
        private val tvName: TextView = view.findViewById(R.id.item_tv_name)
        private val tvEmail:TextView = view.findViewById(R.id.item_tv_email)


        fun bindItem(data: UserItem, listener: (UserItem) -> Unit, context: Context){

            tvName.text = "${data.firstName} ${data.lastName}"
            tvEmail.text = data.email

            Glide.with(context)
                .load(data.avatar)
                .circleCrop()
                .into(ivPhoto)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }


}