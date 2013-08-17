package com.teamcodeflux.devcup.android.festival.helper;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class ImageFileUriHelper {
    private static final String TAG = ImageFileUriHelper.class.getSimpleName();

    public static String getRealPathFromURI(Context context, Uri contentUri, String absoluteImagePath) {
        String[] mediaStore = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, mediaStore, null, null, null);

        String realPath = null;

        try {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            realPath = cursor.getString(column_index);
        } catch (Exception e) {
            Log.e(TAG, "No realPath");
            realPath = absoluteImagePath;
        }

        return realPath;
    }
}
