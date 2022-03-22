package com.techmeet.mercari.Utils;

import static android.os.ParcelFileDescriptor.MODE_APPEND;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

public class SaveImage {

    public static Uri addToFav(Context context, String dirName, String fileName, Bitmap bitmap) {

        String resultPath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) +
                dirName + fileName + ".jpg";
        Log.e("resultpath", resultPath);
        new File(resultPath).getParentFile().mkdir();

        Uri uri;

        if (Build.VERSION.SDK_INT < 29) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "Photo");
            values.put(MediaStore.Images.Media.DESCRIPTION, "Edited");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.DATE_ADDED, fileName);
            values.put("_data", resultPath);

            ContentResolver cr = context.getContentResolver();
            uri = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            try {
                OutputStream fileOutputStream = new FileOutputStream(resultPath);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                if (fileOutputStream != null) {
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }

        } else {
            OutputStream fos = null;
            File file = new File(resultPath);

            final String relativeLocation = Environment.DIRECTORY_PICTURES;
            final ContentValues contentValues = new ContentValues();

            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, relativeLocation + "/" + dirName);
            contentValues.put(MediaStore.MediaColumns.TITLE, "Photo");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
            contentValues.put(MediaStore.MediaColumns.DATE_TAKEN, System.currentTimeMillis());
            contentValues.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis());
            contentValues.put(MediaStore.MediaColumns.BUCKET_ID, file.toString().toLowerCase(Locale.US).hashCode());
            contentValues.put(MediaStore.MediaColumns.BUCKET_DISPLAY_NAME, fileName);
            contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, fileName + ".jpg");

            final ContentResolver resolver = context.getContentResolver();
            final Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            uri = resolver.insert(contentUri, contentValues);

            try {
                fos = resolver.openOutputStream(uri);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fos != null) {

            }
        }
        try {
            Uri uri1 = uri;
            uri = insertInPrivateStorage(context,uri);
            deleteUri(context, uri1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uri;
    }

    private static Uri insertInPrivateStorage(Context context, Uri uri) throws IOException {
        String path = getRealPathFromURI(context, uri);
        String name = getFileName(context, uri);

        @SuppressLint("WrongConstant")
        FileOutputStream fos = context.openFileOutput(name, MODE_APPEND);

        File file = new File(path);

        byte[] bytes = FileUtils.readFileToByteArray(file);


        fos.write(bytes);
        fos.close();

        Uri uri1 = Uri.fromFile(context.getFileStreamPath(name));

        return uri1;
    }


    @SuppressLint("Range")
    private static String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private static String getRealPathFromURI(Context context, Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null,
                null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return null;
    }

    private static void deleteUri(Context context, Uri uri) {
        File fdelete = new File(getRealPathFromURI(context, uri));
        if (fdelete.exists()) {
            int r = context.getContentResolver().delete(uri, null, null);
            if (r > 0) {
            } else {
            }

        }
    }

}
