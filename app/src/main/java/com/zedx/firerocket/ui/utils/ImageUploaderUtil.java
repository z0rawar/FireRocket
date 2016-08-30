package com.zedx.firerocket.ui.utils;

// TODO: Auto-generated Javadoc

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * Created by nazmuddinmavliwala on 12/05/15.
 */
public class ImageUploaderUtil {

    /** The Constant rcCC. */
    public static final int rcCC = 33;
    
    /** The Constant IMAGE_NAME. */
    private static final String IMAGE_NAME = "user_profile_pic";
    
    /** The is cc. */
    static boolean isCC = false;
    
    /** The u image. */
    private static Bitmap uImage;

     /**
     * Gets the real path from uri.
     *
     * @param contentURI the content uri
     * @param context the context
     * @return the real path from uri
     */
    private static String getRealPathFromURI(String contentURI, Context context) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    /**
     * Gets the path.
     *
     * @param context the context
     * @param uri the uri
     * @return the path
     * @throws URISyntaxException the URI syntax exception
     */
    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return getRealPathFromURI(cursor.getString(column_index), context);
                }
            } catch (Exception e) {
                // Eat it
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return getRealPathFromURI(uri.getPath(), context);
        }
        return null;
    }

    /**
     * Gets the image uri.
     *
     * @param inContext the in context
     * @param inImage the in image
     * @return the image uri
     */
    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    /**
     * Gets the filename.
     *
     * @return the filename
     */
    public static String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "AasaanJobs/Profile photos");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + IMAGE_NAME + ".jpg");
        return uriSting;

    }

    /**
     * Calculate in sample size.
     *
     * @param options the options
     * @param reqWidth the req width
     * @param reqHeight the req height
     * @return the int
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }
        return inSampleSize;
    }


    public static Uri getCameraImageUri() {
        File imageDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"FireRocket");
        if (!imageDirectory.exists() && !imageDirectory.mkdirs()) {
            return null;
        } else {
            File imageFile = new File(imageDirectory,"image" + new Date().getTime() + ".png");
            return Uri.fromFile(imageFile);
        }
    }}
