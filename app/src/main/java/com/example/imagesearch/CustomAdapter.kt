package com.example.imagesearch

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.model.Hit
import kotlinx.android.synthetic.main.list_item.view.*
import java.io.File
import java.security.AccessController.getContext


class CustomAdapter(val dataList: MutableList<Hit>): RecyclerView.Adapter<Holder>() {


    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
context = parent.context
        return Holder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))

    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
      val data = dataList[position]

        val headline  = holder.itemView.heading
        val pic = holder.itemView.img
        val user = holder.itemView.author
        val loadBtn = holder.itemView.download

        val tags = data.tags.toString();
        val hashtagebi = tags.replace(", ","#",ignoreCase = true)
        val name  = data.user

        headline.text ="#"+hashtagebi.toString();
        user.text = "Author: "+name

        Glide.with(context).load(data.webformatURL).into(pic)



     loadBtn.setOnClickListener(){


         val filename = "filename.jpg"
         val downloadUrlOfImage = data.webformatURL
         val direct = File(
             Environment
                 .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                 .getAbsolutePath().toString() + "/" +"App photos"+ "/"
         )


         if (!direct.exists()) {
             direct.mkdir()

         }
         val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
         val downloadUri: Uri = Uri.parse(downloadUrlOfImage)
         val request = DownloadManager.Request(downloadUri)
         request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
             .setAllowedOverRoaming(false)
             .setTitle(filename)
             .setMimeType("image/jpeg")
             .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
             .setDestinationInExternalPublicDir(
                 Environment.DIRECTORY_PICTURES,
                 File.separator + "App photos" + File.separator.toString() + filename
             )

         dm.enqueue(request)

         Toast.makeText(context,"file downloaded",Toast.LENGTH_SHORT).show()


     }

pic.setOnClickListener(){

    val intent = Intent("custom-message")

      intent.putExtra("links",data.webformatURL);
       LocalBroadcastManager.getInstance(context).sendBroadcast(intent);




}

    }






}



