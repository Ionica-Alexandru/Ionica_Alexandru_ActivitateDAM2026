package com.example.seminar4;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ListAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivitatePrimara extends AppCompatActivity {

    static List<Sticla> sticlele=new ArrayList<>();
    List<String> stringuri_sticle=new ArrayList<>();
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


            Sticla sticla=bundle.getParcelable("sticla_key");
            sticlele.add(sticla);
            stringuri_sticle= sticlele.stream()
                    .map(st -> st.toString())
                    .collect(Collectors.toList());
//            TextView txtDesc=findViewById(R.id.textView4);
//            txtDesc.setVisibility(VISIBLE);
//            txtDesc.setText(sticla.toString());
//            ArrayAdapter<MainActivity.Sticla> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,sticlele);

//            listV.setAdapter(adapter);
        }

        Button buton=findViewById(R.id.button);
        buton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivitatePrimara.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button buton2=findViewById(R.id.button2);
        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(ActivitatePrimara.this,ActivitateTertiara.class);

                Bundle bundle2=new Bundle();
                bundle2.putParcelableArrayList("list_key", (ArrayList<? extends Parcelable>) sticlele);
                intent2.putExtras(bundle2);
                startActivity(intent2);

            }
        });

        Button buton3=findViewById(R.id.buttonPoze);
        buton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivitatePrimara.this,ActivitatePoze.class);
                startActivity(intent);
            }
        });
    }


}