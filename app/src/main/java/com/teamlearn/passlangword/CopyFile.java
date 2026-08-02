package com.teamlearn.passlangword;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile {
    public void copyAssetToInternalStorage(Context context, String AssetsFileName, String PathAndNameToSave) {
        AssetManager assetManager = context.getAssets();
        InputStream assetInputStream = null;
        OutputStream outputStream = null;

        try {
            assetInputStream = assetManager.open(AssetsFileName);

            File outFile = new File(context.getFilesDir(), AssetsFileName);
            outputStream = new FileOutputStream(PathAndNameToSave);


            byte[] buffer = new byte[1024];
            int length;
            while ((length = assetInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (assetInputStream != null) {
                try {
                    assetInputStream.close();} catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void copyDBFile(Context context, String AssetsFileName, String PathToSave) throws IOException {
        InputStream mInput = context.getAssets().open(AssetsFileName);
        OutputStream mOutput = new FileOutputStream(PathToSave);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }
}
