package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        EditText txtBrand = findViewById(R.id.brand_ET);
        Spinner spinner = findViewById(R.id.tip_s);
        RadioGroup radioGroup = findViewById(R.id.rb_group);
        EditText txtPret = findViewById(R.id.pret_ET);
        List<CheckBox> cb_array = new ArrayList<>();
        cb_array.add(findViewById(R.id.carreffour_cb));
        cb_array.add(findViewById(R.id.auchan_cb));
        cb_array.add(findViewById(R.id.mega_cb));
        cb_array.add(findViewById(R.id.profi_cb));
        cb_array.add(findViewById(R.id.kaufland_cb));
        cb_array.add(findViewById(R.id.lidl_cb));
        cb_array.add(findViewById(R.id.lacocos_cb));
        RatingBar ratingBar = findViewById(R.id.nota_rb);

        Intent intent1=getIntent();
        if(intent1.hasExtra("sticla_key"))
        {
            Sticla st=intent1.getParcelableExtra("sticla_key");
            txtBrand.setText(st.brand);
            spinner.setSelection(st.tip_apa.ordinal());
            String V="";
            if(st.volum>=1000)
                V=st.volum/1000+"l";
            else
                V=st.volum+"ml";
            for(int i=0;i<radioGroup.getChildCount();i++)
            {
                RadioButton rb= (RadioButton) radioGroup.getChildAt(i);
                if(rb.getText().toString().equals(V))
                    rb.setChecked(true);
            }
            txtPret.setText(String.valueOf(st.pret));
            List<String> magazinelee=st.magazine;
            for(String m : magazinelee)
            {
                for(CheckBox cb : cb_array)
                {
                    if(m.equals(cb.getText()))
                        cb.setChecked(true);
                }
            }
            ratingBar.setRating(st.nota);
        }

        Button buton = findViewById(R.id.button_adauga);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String brandu = txtBrand.getText().toString();

                tip tipulApei = tip.valueOf(spinner.getSelectedItem().toString());

                int volumul = 0;
                int id_rb = radioGroup.getCheckedRadioButtonId();
                if (id_rb == R.id.mili_300)
                    volumul = 330;
                else if (id_rb == R.id.mili_500)
                    volumul = 500;
                else if (id_rb == R.id.litri_1)
                    volumul = 1000;
                else if (id_rb == R.id.litri_2)
                    volumul = 2000;

                double pretu = 0;
                try {
                    pretu = Double.parseDouble(txtPret.getText().toString());
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "Introdu un pret valid!", Toast.LENGTH_SHORT).show();
                }

                List<String> magazine = new ArrayList<>();

                for (CheckBox checkBox : cb_array) {
                    if (checkBox.isChecked()) {
                        magazine.add(checkBox.getText().toString());
                    }
                }

                float notaM = ratingBar.getRating();

                Sticla sticla = new Sticla(brandu, tipulApei, volumul, (float) pretu, magazine, notaM);

                if (getIntent().hasExtra("sticla_key")) {
                    Intent intentResult = new Intent();
                    intentResult.putExtra("sticla_key", (Parcelable) sticla);
                    setResult(RESULT_OK, intentResult);
                    finish();
                }
                else {

                    Intent intent2 = new Intent(MainActivity.this, ActivitatePrimara.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("brand_key", sticla.brand);
                    bundle.putString("tip_key", sticla.tip_apa.toString());
                    bundle.putInt("volum_key", sticla.volum);
                    bundle.putFloat("pret_key", sticla.pret);
                    bundle.putStringArrayList("magazine_key", (ArrayList<String>) sticla.magazine);
                    bundle.putFloat("nota_key", sticla.nota);

                    intent2.putExtras(bundle);
                    startActivity(intent2);

                }

            }
        });


        Switch switchul = findViewById(R.id.switch1);

        switchul.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                Double pretu;

                try {
                    pretu = Double.parseDouble(txtPret.getText().toString().trim());

                    if (isChecked) {
                        pretu = pretu + 0.5;
                    } else {
                        pretu = pretu - 0.5;
                    }
                    txtPret.setText(pretu.toString());

                } catch (NumberFormatException ex) {
                    Toast.makeText(MainActivity.this, "Introdu un pret valid!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}