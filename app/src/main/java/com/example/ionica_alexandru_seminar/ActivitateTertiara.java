package com.example.ionica_alexandru_seminar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateTertiara extends AppCompatActivity {

    int n1=0;
    int n2=0;

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
        if(bundle!=null)
        {
            String mesaj=bundle.getString("m_key");
            int numar1=bundle.getInt("nr1");
            int numar2=bundle.getInt("nr2");
            n1=numar1;
            n2=numar2;

            Toast toast=new Toast(this);
            toast.setText(mesaj+" "+numar1+", "+numar2);
            toast.show();
        }

        Button button=findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(ActivitateTertiara.this,ActivitateSecundara.class);

                Bundle bundle2=new Bundle();
                bundle2.putString("m_key","Suma celor 2 numere transmise este: ");
                int suma=n1+n2;
                bundle2.putInt("s1",suma);

                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });
    }
}