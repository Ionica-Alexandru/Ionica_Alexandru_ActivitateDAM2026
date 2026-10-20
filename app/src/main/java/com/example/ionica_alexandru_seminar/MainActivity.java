package com.example.ionica_alexandru_seminar;

import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {

    private static final String Tag = "Ciclu Activitate";
    String producator = Build.MANUFACTURER;
    String model = Build.MODEL;
    String numeDispozitiv = producator + " " + model;

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
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.e(Tag,"onStart() a dat eroare");
        Log.w(Tag,"onStart() a dat un avertisment");
        Log.d(Tag,"onStart() apelat");
        Log.i(Tag,"onStart() a fost apelat cu succes la ora "+LocalTime.now().toString());
        Log.v(Tag,"onStart() a fost apelat cu succes la ora "+LocalTime.now().toString()+" din modelul de telefon "+numeDispozitiv);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.e(Tag,"onResume() a dat eroare");
        Log.w(Tag,"onResume() a dat un avertisment");
        Log.d(Tag,"onResume() apelat");
        Log.i(Tag,"onResume() a fost apelat cu succes la ora "+LocalTime.now().toString());
        Log.v(Tag,"onResume() a fost apelat cu succes la ora "+LocalTime.now().toString()+" din modelul de telefon "+numeDispozitiv);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.e(Tag,"onPause() a dat eroare");
        Log.w(Tag,"onPause() a dat un avertisment");
        Log.d(Tag,"onPause() apelat");
        Log.i(Tag,"onPause() a fost apelat cu succes la ora "+LocalTime.now().toString());
        Log.v(Tag,"onPause() a fost apelat cu succes la ora "+LocalTime.now().toString()+" din modelul de telefon "+numeDispozitiv);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.e(Tag,"onStop() a dat eroare");
        Log.w(Tag,"onStop() a dat un avertisment");
        Log.d(Tag,"onStop() apelat");
        Log.i(Tag,"onStop() a fost apelat cu succes la ora "+LocalTime.now().toString());
        Log.v(Tag,"onStop() a fost apelat cu succes la ora "+LocalTime.now().toString()+" din modelul de telefon "+numeDispozitiv);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.e(Tag,"onDestroy() a dat eroare");
        Log.w(Tag,"onDestroy() a dat un avertisment");
        Log.d(Tag,"onDestroy() apelat");
        Log.i(Tag,"onDestroy() a fost apelat cu succes la ora "+LocalTime.now().toString());
        Log.v(Tag,"onDestroy() a fost apelat cu succes la ora "+LocalTime.now().toString()+" din modelul de telefon "+numeDispozitiv);
    }

}