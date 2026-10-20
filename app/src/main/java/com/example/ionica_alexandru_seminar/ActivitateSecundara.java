package com.example.ionica_alexandru_seminar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.nio.BufferUnderflowException;

public class ActivitateSecundara extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_secundara);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivitateSecundara.this, ActivitateTertiara.class);

                Bundle bundle=new Bundle();
                bundle.putString("m_key","Numere transmise:");
                bundle.putInt("nr1",14);
                bundle.putInt("nr2",25);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Intent intent2=getIntent();
        Bundle bundle2=intent2.getExtras();
        if(bundle2!=null)
        {
            String mesaj=bundle2.getString("m_key");
            int val=bundle2.getInt("s1");

            Toast toast=new Toast(this);
            toast.setText(mesaj+val);
            toast.show();
        }
    }
}