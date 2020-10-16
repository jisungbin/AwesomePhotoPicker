package me.sungbin.awesomephotopicker.library.util

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore


/**
 * Created by SungBin on 2020-10-16.
 */

object PhotoUtil {
    fun getAllPath(
        context: Context,
        fileExtensionsFilter: FileExtensions,
        customFileFilter: FileExtensions // todo: filter
    ): ArrayList<Uri> {
        val uriExternal = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor?
        val columnIndexId: Int
        val list = ArrayList<Uri>()
        val projection = arrayOf(MediaStore.Images.Media._ID)
        cursor = context.contentResolver.query(uriExternal, projection, null, null, null)
        cursor?.let {
            columnIndexId = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                val imageId = cursor.getLong(columnIndexId).toString()
                val imageUri = Uri.withAppendedPath(uriExternal, imageId)
                list.add(imageUri)
            }
            cursor.close()
        }
        return list
    }

    fun getFileExtension(filePath: String) =
        filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length)

    fun createCustomFileFilter() = true
}