package com.zeroone.instagramclone_jetpackcompose.presentation.screen.add

import android.content.Context
import android.database.Cursor
import android.os.Environment
import android.provider.MediaStore

fun getImagePath(ctx: Context): MutableList<String> {

    val imgList: MutableList<String> = ArrayList()
    // in this method we are adding all our image paths
    // in our aerialist which we have created.
    // on below line we are checking if the device is having an sd card or not.
    val isSDPresent = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    if (isSDPresent) {
        // if the sd card is present we are creating a new list in
        // which we are getting our images data with their ids.
        val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED)
        // on below line we are creating a new
        // string to order our images by string.

        val orderBy = MediaStore.Images.Media.DATE_ADDED
        // this method will stores all the images
        // from the gallery in Cursor
        val cursor: Cursor? = ctx.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            columns,
            null,
            null,
            orderBy
        )

        // below line is to get total number of images
        val count: Int = cursor!!.count
        // on below line we are running a loop to add
        // the image file path in our array list.
        for (i in 0 until count) {
            cursor.moveToPosition(i)
            // on below line we are getting image file path
            val dataColumnIndex: Int = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
            // after that we are getting the image file path
            // and adding that path in our array list.
            imgList.add(cursor.getString(dataColumnIndex))
        }
        // after adding the data to our
        // array list we are closing our cursor.
        cursor.close()
    }
    return imgList

}