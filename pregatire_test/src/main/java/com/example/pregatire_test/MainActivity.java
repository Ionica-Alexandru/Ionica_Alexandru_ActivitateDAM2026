package com.example.pregatire_test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Spital> spitale=new ArrayList<>();
    int idModificat=0;
    SpitalAdapter spitalAdapter=null;
    boolean first=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv=findViewById(R.id.listView);
        spitalAdapter=new SpitalAdapter(spitale,getApplicationContext(),R.layout.spital_listview_item);
        lv.setAdapter(spitalAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2=new Intent(MainActivity.this,Activtiate_secundara.class);
                intent2.putExtra("spitale_key", spitale.get(position));
                idModificat=position;
                startActivityForResult(intent2,202);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                spitale.remove(position);
                spitalAdapter.notifyDataSetChanged();

                return true;
            }
        });

        Button buton1=findViewById(R.id.button1);
        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Activitate2.class);
                startActivity(intent);
            }
        });

        Button buton2=findViewById(R.id.button2);
        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Activtiate_secundara.class);
                startActivityForResult(intent, 201);
            }
        });

        Button buton6=findViewById(R.id.button6);
        buton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Activitate4.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(first)
           first=false;
        else {
            Log.d("LifecycleCheck", "Aplicatia a revenit in prim plan!");
            String mesaj = getResources().getString(R.string.re_welcome);//////////////////////////////////
            Toast toast = new Toast(this);
            toast.setText(mesaj);
            toast.show();
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        TextView txt1=findViewById(R.id.txt1);
        txt1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        txt1.setPadding(10,20,30,10);

        TextView txt2=findViewById(R.id.txt2);
        txt2.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==201 && data!=null)
            {
                Spital spital=data.getParcelableExtra("spital");
                int ok=0;
//                TextView tx=findViewById(R.id.textView9);
//                tx.setText(spital.nume);
                if(spitale!=null)
                    ok=1;
                spitale.add(spital);
//                if(ok==1)
//                {
//                    ListView lv=findViewById(R.id.listView);
//                    lv.setAdapter(spitalAdapter);
//                }
                spitalAdapter.notifyDataSetChanged();
            }
            if(requestCode==202 && data!=null)
            {
                Spital spital=data.getParcelableExtra("spitale_key");
                spitale.set(idModificat,spital);
                spitalAdapter.notifyDataSetChanged();
            }
        }
    }
}