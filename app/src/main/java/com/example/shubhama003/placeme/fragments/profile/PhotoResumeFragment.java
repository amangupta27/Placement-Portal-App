package com.example.shubhama003.placeme.fragments.profile;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Utility;
import com.example.shubhama003.placeme.activities.Main2Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static android.app.Activity.RESULT_OK;


public class PhotoResumeFragment extends Fragment {

    Button selectPic,uploadPic,selectRes,uploadRes,downloadRes;
    ProgressBar progressBar,pbare;
    TextView tv;
    TextView prog,filename,prog2;
    Uri uri,resuri;
    String rname;
    ImageView pic;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE =7;
    private final int PICK_IMAGE_REQUEST = 1;
    private final int PICK_FILE_REQUEST = 2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.photo_resume, container, false);
        progressBar =(ProgressBar)v.findViewById(R.id.progressBarUpload);
        selectPic = (Button)v.findViewById(R.id.selectpic);
        uploadPic = (Button)v.findViewById(R.id.updatepic);
        selectRes = (Button)v.findViewById(R.id.selectres);
        uploadRes = (Button)v.findViewById(R.id.uploaldres);
        pbare = v.findViewById(R.id.pbar5);
        prog2 = v.findViewById(R.id.prog2);
        tv = v.findViewById(R.id.pupdate);
        prog = (TextView)v.findViewById(R.id.progress);
        pic = (ImageView)v.findViewById(R.id.imageView2);
        filename = (TextView)v.findViewById(R.id.filename);
        downloadRes = v.findViewById(R.id.downloadres);

        if(Utility.x2.getUrl()!=null && !Utility.x2.getUrl().equals(""))
        {tv.setText("Photo Update not\n      available");
            selectPic.setVisibility(View.GONE);
        }
        if(Utility.x2.getResName()!=null && !Utility.x2.getUrl().equals(""))
        {
            filename.setText(Utility.x2.getResName());
        }

        if(Utility.x2.getUrl()!=null &&!Utility.x2.getUrl().equals("")) {
            Picasso.with(getContext())
                    .load(Utility.x2.getUrl())
                    .placeholder(R.drawable.logomnnit)
                    .resize(150,150)
                    .centerCrop()
                    .transform(new CropCircleTransformation())
                    .into(pic);
        }

        if(Utility.x2.getResurl()==null || Utility.x2.getResurl().isEmpty()){
            downloadRes.setVisibility(View.GONE);
        }
        downloadRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager downloadManager = Main2Activity.downloadManager;
                Uri uri = Uri.parse(Utility.x2.getResurl());
                DownloadManager.Request r = new DownloadManager.Request(uri);
                r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long ref = downloadManager.enqueue(r);
                Toast.makeText(getContext(),"Download Started",Toast.LENGTH_SHORT).show();
            }
        });
       /* if(Utility.x2.getStatus().equals("1"))
        {
            tv.setText("Photo Update not\n      available");
            selectPic.setVisibility(View.GONE);
            filename.setText(Utility.x2.getResName());
            Picasso.with(getContext())
                    .load(Utility.x2.getUrl())
                    .placeholder(R.drawable.logomnnit)
                    .resize(150,150)
                    .centerCrop()
                    .transform(new CropCircleTransformation())
                    .into(pic);
        } */

        selectPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPicture();
            }
        });

        uploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPicture();
            }
        });

        selectRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectResume();
            }
        });

        uploadRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadResume();
            }
        });

        return v;
    }

    private void selectPicture() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to read the contacts
                }

                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                return;
            }
        }

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void uploadPicture(){
        pic.setAlpha( 0.5f);
        progressBar.setVisibility(View.VISIBLE);
        prog.setVisibility(View.VISIBLE);
        if(uri==null)
            return;

        UploadTask uploadTask;
        Bitmap cbitmap=null;
        try {
            cbitmap = new Compressor(getContext()).compressToBitmap(new File(uri.getPath()));
        } catch (IOException e) {
            Log.d("qa","a");
            e.printStackTrace();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(cbitmap!=null) {
            cbitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images" + "/" + Utility.x2.getRegistration());
            uploadTask = storageReference.putBytes(data);


        }
        else{
            Log.d("qa","a");
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images" + "/" + Utility.x2.getRegistration());
            uploadTask = storageReference.putFile(uri);
        }
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                        Utility.x2.setUrl(downloadUrl.toString());
                                        if(Utility.x2.isFilled()){
                                            Utility.x2.setStatus("1");
                                        }
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("Users").child(Utility.x2.getRegistration()).child("Url")
                                                .setValue(downloadUrl.toString()).addOnCompleteListener(
                                                new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {


                                                            progressBar.setVisibility(View.INVISIBLE);
                                                            uploadPic.setVisibility(View.INVISIBLE);
                                                            prog.setVisibility(View.INVISIBLE);
                                                            pic.setAlpha(1.0f);
                                                            Toast.makeText(getContext(), "Sucsessfully uploaded", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            Log.d("URL_UPDATE", "Failed");
                                                        }
                                                    }
                                                });
                                    }

                                }

        );
        // Observe state change events such as progress, pause, and resume
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                prog.setText(String.valueOf(progress).substring(0, 3) + " %");
            }
        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(), "paused", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectResume(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to read the contacts
                }

                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                return;
            }
        }

        Intent intent = new Intent();
        intent.setType("file/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_FILE_REQUEST);
    }
    private void uploadResume() {
        filename.setVisibility(View.GONE);
        pbare.setVisibility(View.VISIBLE);
        prog2.setVisibility(View.VISIBLE);
        if(resuri==null)
            return;
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("files" + "/" + Utility.x2.getRegistration());
        UploadTask uploadTask = storageReference.putFile(resuri);
        Utility.x2.setResName(rname);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                        Utility.x2.setResurl(downloadUrl.toString());
                                        if(Utility.x2.isFilled())
                                        {
                                            Utility.x2.setStatus("1");
                                        }
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("Users").child(Utility.x2.getRegistration())
                                                .setValue(Utility.x2).addOnCompleteListener(

                                                        new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {

                                                            Toast.makeText(getContext(),"Sucsessfully uploaded",Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            Log.d("URL_UPDATE","Failed");
                                                        }
                                                    }
                                                });
                                    }

                                }

        );
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>(){
            @Override
            public void onProgress (UploadTask.TaskSnapshot taskSnapshot){
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                prog2.setText(String.valueOf(progress).substring(0,3)+" %");
            }
        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused (UploadTask.TaskSnapshot taskSnapshot){
                Toast.makeText(getContext(), "paused", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                uploadPic.setVisibility(View.VISIBLE);
                uri = data.getData();
                Log.d("AMRE", "showing selected");
                Picasso.with(getContext())
                        .load(uri)
                        .placeholder(R.drawable.logomnnit)
                        .resize(150, 150)
                        .centerCrop()
                        .transform(new CropCircleTransformation())
                        .into(pic);
            }
            else if(requestCode == PICK_FILE_REQUEST){
                resuri=data.getData();
                File file = new File(String.valueOf(resuri.getPath()));
                rname = file.getName();
                filename.setText(rname);

            }
        }
    }

}