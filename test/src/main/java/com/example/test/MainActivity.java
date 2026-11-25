package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        EditText txtID=findViewById(R.id.ionica_alexandru_id);
        EditText txtModel=findViewById(R.id.ionica_alexandru_model);
        EditText txtSpatiu=findViewById(R.id.ionica_alexandru_spatiu);
        RadioGroup rgb=findViewById(R.id.ionica_alexandru_radioGroup);

        Button buton=findViewById(R.id.ionica_alexandru_button);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                try {
                    id=Integer.parseInt(txtID.getText().toString());
                } catch (NumberFormatException e) {
                    Toast toast=new Toast(MainActivity.this);
                    toast.setText("Introdu un id format numai din cifre");
                    toast.show();
                    return;
                }

                String model=txtModel.getText().toString();

                int spatiu;
                try {
                    spatiu=Integer.parseInt(txtSpatiu.getText().toString());
                } catch (NumberFormatException e) {
                    Toast toast=new Toast(MainActivity.this);
                    toast.setText("Spatiul este fromat numai din cifre!");
                    toast.show();
                    return;
                }

                int id_rb=rgb.getCheckedRadioButtonId();
                if(id_rb==-1)
                {
                    Toast toast=new Toast(MainActivity.this);
                    toast.setText("Selecteaza un sist de operare!");
                    toast.show();
                    return;
                }
                RadioButton rb=findViewById(id_rb);
                sist_op so= sist_op.valueOf(rb.getText().toString());

                Tableta tableta=new Tableta(id,model,spatiu,so);
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);

                Bundle bundle=new Bundle();
                bundle.putParcelable("tableta_key",tableta);

                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}