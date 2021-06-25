package com.example.plantdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plantdoctor.ml.Output;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;


public class SelectImage extends AppCompatActivity {
   Button selectImage,refresh,check;
   ImageView img;
   Uri uri;
   Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        getSupportActionBar().setTitle("Select Image");
        selectImage=findViewById(R.id.selectImage);
        refresh=findViewById(R.id.refresh);
        check=findViewById(R.id.check);
        img=findViewById(R.id.img);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCropActivity();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageBitmap(null);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          bitmap=Bitmap.createScaledBitmap(bitmap,256,256,true);
                try {
                    Output model = Output.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 256, 256, 3}, DataType.FLOAT32);
                    TensorImage tensorImage=new TensorImage(DataType.FLOAT32);
                    tensorImage.load(bitmap);
                    ByteBuffer byteBuffer=tensorImage.getBuffer();
                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    Output.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    // Releases model resources if no longer used.
                    model.close();
                   String s= outputFeature0.getFloatArray()[0]+" "+outputFeature0.getFloatArray()[1]+" "+outputFeature0.getFloatArray()[2]+" "
                    +outputFeature0.getFloatArray()[3]+" "+outputFeature0.getFloatArray()[4]+" "+outputFeature0.getFloatArray()[5]+" "
                    +outputFeature0.getFloatArray()[6]+" "+outputFeature0.getFloatArray()[7]+" "+outputFeature0.getFloatArray()[8]+" "+outputFeature0.getFloatArray()[9]+" "
                    +outputFeature0.getFloatArray()[10]+" "+outputFeature0.getFloatArray()[11]+" ";
                   String[] ans=s.split("\\s+");
                    Bundle b=new Bundle();
                    String key="key";
                    b.putStringArray(key, ans);
                    Intent i=new Intent(SelectImage.this, Result.class);
                    i.putExtras(b);
                    startActivity(i);
                } catch (IOException e) {
                    // TODO Handle the exception
                }
            }
        });
    }
    private void startCropActivity(){
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                try {
                    bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(),resultUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                img.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}