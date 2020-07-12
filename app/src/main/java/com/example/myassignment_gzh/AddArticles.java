package com.example.myassignment_gzh;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddArticles extends AppCompatActivity {
    private ImageButton addYes;
    private ImageButton addNo;
    private ImageView addPicture;
    private EditText editcontent,title;
    private static final int CHOOSE_PHOTO = 0;
    AddArticleData datacontent = new AddArticleData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addarticle);

        Bmob.initialize(this, "77e60f5f6892f690500f840fb4d58cbf");

        addYes = (ImageButton) findViewById(R.id.addYes);
        addNo = (ImageButton) findViewById(R.id.addNo);
        addPicture = findViewById(R.id.addPicture);
        title = findViewById(R.id.title);
        editcontent = findViewById(R.id.editcontent);
        setTitle("我要发贴");
        addYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titlecontent = title.getText().toString();
                String textcontent = editcontent.getText().toString();
                datacontent.setTitle(titlecontent);
                datacontent.setTextcontent(textcontent);
                datacontent.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e==null){
                            Toast.makeText(AddArticles.this, "发贴成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddArticles.this, "发帖失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                finish();
            }
        });
        addNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });

    }
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (requestCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统使用该方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        //4.4以下系统使用该方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this, uri)){
            //如果是document类型的Uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.document".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        selection);
                Log.i(imagePath, "相册选择1");
            }else if("com.android.providers.downloads.documents".equals
                    (uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse(
                        "content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
                Log.i(imagePath, "相册选择2");
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的Uri，则使用普通方法处理
            imagePath = getImagePath(uri,null);
            Log.i(imagePath, "相册选择3");
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
            Log.i(imagePath, "相册选择4");
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        Log.i(imagePath, "相册选择5");
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection){
        String path = null;
        //通过uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.
                        Media.DATA));
            }
            cursor.close();
        }
        Log.i(path, "相册选择");
        return path;
    }

    private void displayImage(String imagePath){
        if(imagePath !=null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            addPicture.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this, "failed to get image", Toast.LENGTH_LONG).show();
        }
    }



}
