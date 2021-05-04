package com.xiaoaxiao.graduatemanagementsystem.Utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore

fun Uri?.convertUriToPath(context: Context): String? {
    if (this == null)
        return null
    val schema = this.scheme!!
    if (schema.isEmpty() || ContentResolver.SCHEME_FILE == schema) {
        return this.path
    }
    if ("http" == schema)
        return this.toString()
    if (ContentResolver.SCHEME_CONTENT == schema) {
        val projection = arrayOf(MediaStore.MediaColumns.DATA)
        var cursor: Cursor? = null
        var filePath = ""
        try {
            cursor = context.contentResolver.query(this, projection, null, null, null)
            if (cursor!!.moveToFirst()) {
                filePath = cursor.getString(0)
            }
            cursor.close()
        } catch (e: Exception) {
            // do nothing
        } finally {
            try {
                cursor?.close()
            } catch (e2: Exception) {
                // do nothing
            }

        }
        if (filePath.isEmpty()) {
            try {
                val contentResolver = context.contentResolver
                val selection = MediaStore.Images.Media._ID + "= ?"
                var id = this.lastPathSegment!!
                if (Build.VERSION.SDK_INT >= 19 && !id.isEmpty() && id.contains(":")) {
                    id = id.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                }
                val selectionArgs = arrayOf(id)
                cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, null)
                if (cursor!!.moveToFirst()) {
                    filePath = cursor.getString(0)
                }
                cursor.close()

            } catch (e: Exception) {
                // do nothing
            } finally {
                try {
                    cursor?.close()
                } catch (e: Exception) {
                    // do nothing
                }

            }
        }
        return filePath
    }
    return null
}