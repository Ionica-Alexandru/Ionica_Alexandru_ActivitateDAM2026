package com.example.seminar4;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ActivitatePrimara extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_primara);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null)
        {
            String brand=bundle.getString("brand_key");
            MainActivity.tip tipulApei= MainActivity.tip.valueOf(bundle.getString("tip_key"));
            int volumul=bundle.getInt("volum_key");
            float pretu=bundle.getFloat("pret_key");
            List<String> magazinele=new ArrayList<>();
            magazinele=bundle.getStringArrayList("magazine_key");
            float nota=bundle.getFloat("nota_key");

            MainActivity.Sticla sticla=new MainActivity.Sticla(brand,tipulApei,volumul,pretu,magazinele,nota);
            TextView txtDesc=findViewById(R.id.textView4);
            txtDesc.setVisibility(VISIBLE);
            txtDesc.setText(sticla.toString());
        }

        Button buton=findViewById(R.id.button);
        buton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivitatePrimara.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


}