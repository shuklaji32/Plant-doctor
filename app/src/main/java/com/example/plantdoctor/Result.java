package com.example.plantdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Result extends AppCompatActivity {
     TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result=findViewById(R.id.result);
        Bundle b=this.getIntent().getExtras();
        String[] array=b.getStringArray("key");
        String[] diseaseName={"Potato___Early_blight","Potato___Late_blight","Potato___healthy","Tomato___Bacterial_spot","Tomato___Early_blight",
                "Tomato___Late_blight","Tomato___Leaf_Mold","Tomato___Septoria_leaf_spot","Tomato___Spider_mites Two-spotted_spider_mite",
                "Tomato___Target_Spot","Tomato___Tomato_mosaic_virus","Tomato___healthy"};
        HashMap<String,String> hm=new HashMap<>();
        for(int i=0;i<12;i++){
           hm.put(diseaseName[i],array[i]);
        }
        String ans="";
        for(Map.Entry<String,String> mp:hm.entrySet()){
            if(!mp.getValue().equals("0.0")){
                ans=mp.getKey();
            }
        }
        result.setText(ans);
    }
}