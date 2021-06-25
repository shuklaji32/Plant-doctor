package com.example.plantdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SelectPlant extends AppCompatActivity {

    String platName="plantName";
    ImageView potato,tomato,brinjal,ladyfinger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plant);
        getSupportActionBar().setTitle("Select Plant");
        potato=findViewById(R.id.potato);
        tomato=findViewById(R.id.tomato);
        brinjal=findViewById(R.id.brinjal);
       ladyfinger=findViewById(R.id.ladyfinger);
       potato.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(SelectPlant.this,SelectImage.class);
               intent.putExtra(platName,"potato");
               startActivity(intent);
           }
       });
        tomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectPlant.this,SelectImage.class);
                intent.putExtra(platName,"tomato");
                startActivity(intent);
            }
        });
       brinjal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectPlant.this,SelectImage.class);
                intent.putExtra(platName,"brinjal");
                startActivity(intent);
            }
        });
        ladyfinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectPlant.this,SelectImage.class);
                intent.putExtra(platName,"ladyfinger");
                startActivity(intent);
            }
        });
    }
}