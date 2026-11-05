package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ActivitateTertiara extends AppCompatActivity {

    private List<Sticla> sticle=null;////////////////////////////////////////////////////////////////
    private int idModificat=0;/////////////////////////////////////////////////////////
    private SticlaAdapter adapter=null;/////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_tertiara);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        ListView listV = findViewById(R.id.listView1);
        if (bundle != null) {
            sticle = (List<Sticla>) bundle.getSerializable("list_key");
            if (sticle != null) {

//                ArrayAdapter<Sticla> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sticle);
//                listV.setAdapter(adapter);
                adapter=new SticlaAdapter(sticle, getApplicationContext(), R.layout.listviewitem_layout);
                listV.setAdapter(adapter);
                listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Toast toast=new Toast(ActivitateTertiara.this);
//                        toast=Toast.makeText(ActivitateTertiara.this,sticle.get(position).toString(),Toast.LENGTH_LONG);
//                        toast.show();
                        Intent intentModifica=new Intent(ActivitateTertiara.this, MainActivity.class);
                        intentModifica.putExtra("sticla_key",(Parcelable) sticle.get(position));
                        idModificat=position;
                        startActivityForResult(intentModifica,209);
                    }
                });

                listV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        sticle.remove(position);
                        listV.setAdapter(adapter);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==209)
            {
                sticle.set(idModificat,data.getParcelableExtra("sticla_key"));
                adapter.notifyDataSetChanged();
            }
        }
    }
}