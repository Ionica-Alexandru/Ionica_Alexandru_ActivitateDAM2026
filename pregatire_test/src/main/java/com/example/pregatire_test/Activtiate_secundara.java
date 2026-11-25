package com.example.pregatire_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Activtiate_secundara extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activtiate_secundara);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar=findViewById(R.id.TOOLBAR);
        setSupportActionBar(toolbar);

        SeekBar seekBar=findViewById(R.id.NRPATURI);
        TextView nrPatView=findViewById(R.id.ViewNrpat);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nrPatView.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        EditText txtNume=findViewById(R.id.NUME);
        Spinner spinner=findViewById(R.id.TIPUL);
        EditText txtPret=findViewById(R.id.PRETUL);
        List<CheckBox> cb=new ArrayList<>();
        cb.add(findViewById(R.id.pneomologie));
        cb.add(findViewById(R.id.ati));
        cb.add(findViewById(R.id.oncologie));
        cb.add(findViewById(R.id.pneumopule));
        cb.add(findViewById(R.id.dermatologie));
        cb.add(findViewById(R.id.upu));
        cb.add(findViewById(R.id.reanimare));
        RatingBar rb=findViewById(R.id.NOTA);

        Intent intent1=getIntent();
        if(intent1.hasExtra("spitale_key"))
        {
            Bundle bundle=intent1.getExtras();
            Spital spt=bundle.getParcelable("spitale_key");

            txtNume.setText(spt.nume);
            spinner.setSelection(spt.tipul_spitalului.ordinal());
            seekBar.setProgress(spt.paturi);
            txtPret.setText(String.valueOf(spt.pret_consultatie));

            for(String sect : spt.sectii)
            {
                for(CheckBox checkBox : cb)
                {
                    if(checkBox.getText().equals(sect))
                    {
                        checkBox.setChecked(true);
                    }
                }
            }

            rb.setRating((float) spt.nota);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.opt_adauga)
        {
            EditText txtNume=findViewById(R.id.NUME);
            Spinner spinner=findViewById(R.id.TIPUL);
            SeekBar seekBar=findViewById(R.id.NRPATURI);
            EditText txtPret=findViewById(R.id.PRETUL);
            List<CheckBox> cb=new ArrayList<>();
            cb.add(findViewById(R.id.pneomologie));
            cb.add(findViewById(R.id.ati));
            cb.add(findViewById(R.id.oncologie));
            cb.add(findViewById(R.id.pneumopule));
            cb.add(findViewById(R.id.dermatologie));
            cb.add(findViewById(R.id.upu));
            cb.add(findViewById(R.id.reanimare));
            RatingBar rb=findViewById(R.id.NOTA);

            String nume=txtNume.getText().toString();
            tip tipul= tip.valueOf(spinner.getSelectedItem().toString());
            int nr_p=seekBar.getProgress();

            float pret=0;
            try {
                pret= Float.parseFloat(txtPret.getText().toString());
            } catch (NumberFormatException e) {
                Toast toast=new Toast(Activtiate_secundara.this);
                toast.setText("Introdu un numar ca lumea");
                toast.show();
                return true;
            }

            List<String> sectii=new ArrayList<>();
            for(CheckBox checkBox : cb)
            {
                if(checkBox.isChecked())
                    sectii.add(checkBox.getText().toString());
            }

            double nota=rb.getRating();
            Spital spital = new Spital(nume, tipul, nr_p, pret, sectii, nota);

            if(getIntent().hasExtra("spitale_key")) {
                Intent intentResult=new Intent();
                intentResult.putExtra("spitale_key",spital);
                setResult(RESULT_OK,intentResult);
                finish();
            }
            else
            {
                Intent intent = new Intent();
                intent.putExtra("spital", spital);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }
        else if(id==R.id.opt_despre)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(Activtiate_secundara.this);
            builder.setTitle("Info");
            builder.setMessage("Aceasta este o aplicatie dezvoltata de Alexandru Ionica. Este ora 23:15 cand scriu asta si sunt obosit. Ce amuzanta este facultatea");
            builder.setPositiveButton("Ok",null);
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}