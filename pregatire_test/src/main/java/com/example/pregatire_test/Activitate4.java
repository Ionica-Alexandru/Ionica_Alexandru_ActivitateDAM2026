package com.example.pregatire_test;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Activitate4 extends AppCompatActivity {

    String dataSelectata = "";
    boolean running=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. AutoCompleteTextView
        AutoCompleteTextView actv = findViewById(R.id.autoCompleteTextView);
        String[] suge = getResources().getStringArray(R.array.Array2);
        ArrayAdapter<String> adaptor1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, suge);
        actv.setAdapter(adaptor1);

        // 2. SeekBar & TextView
        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView textVAl = findViewById(R.id.textVAL);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // CORICȚIE: Convertim int-ul în String
                textVAl.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 3. Chronometer
        Chronometer chronometer = findViewById(R.id.chrono);
        final long[] timeWhenStopped = {0}; // Memorează milisecundele parcurse până la pauză

        chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    // 1. Calculăm cât timp s-a scurs până în momentul pauzei
                    timeWhenStopped[0] = chronometer.getBase() - SystemClock.elapsedRealtime();
                    chronometer.stop();
                    running= false;
                } else {
                    // 2. Setăm noua bază scăzând timpul deja parcurs din timpul curent
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped[0]);
                    chronometer.start();
                    running = true;
                }
            }
        });

        // 4. DatePickerDialog
        Button buton = findViewById(R.id.button);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int an = c.get(Calendar.YEAR);
                int luna = c.get(Calendar.MONTH);
                int zi = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Activitate4.this,
                        new DatePickerDialog.OnDateSetListener() {////////////onDateChanged pe DatePicker
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                dataSelectata = dayOfMonth + "/" + (month + 1) + "/" + year;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year,month+1,dayOfMonth);
                                Toast.makeText(Activitate4.this, "Data: " + dataSelectata, Toast.LENGTH_SHORT).show();
                            }
                        }, an, luna, zi
                );
                datePickerDialog.show();
            }
        });

        // 5. TimePickerDialog
        Button buton2 = findViewById(R.id.button3);
        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int oraCurenta = c.get(Calendar.HOUR_OF_DAY);
                int minCurent = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Activitate4.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String oraSelectata = hourOfDay + ":" + minute;
                                Toast.makeText(Activitate4.this, "Ora: " + oraSelectata, Toast.LENGTH_SHORT).show();
                            }
                        }, oraCurenta, minCurent, false
                );
                timePickerDialog.show();
            }
        });

        // 6. Toolbar & ContextMenu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView txt6 = findViewById(R.id.textView6);
        registerForContextMenu(txt6);

        // 7. PopupMenu & AlertDialog
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Activitate4.this, button4);
                popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.opt_adauga) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Activitate4.this);
                            builder.setTitle("Confirmare");
                            builder.setMessage("Ești sigur că vrei să adaugi ceva?");

                            builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(Activitate4.this, "Element adăugat!", Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.setNegativeButton("Nu", null);

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            return true;
                        } else if (item.getItemId() == R.id.opt_despre) {
                            Toast.makeText(Activitate4.this, "Despre din Popup", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PopupMenu popupMenu = new PopupMenu(Activitate4.this, button4);
//                popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        if (item.getItemId() == R.id.opt_adauga) {
//
//                            // 1. Creăm obiectul Dialog obișnuit
//                            Dialog dialog = new Dialog(Activitate4.this);
//
//                            // 2. Îi atașăm layout-ul XML customizat (ex: dialog_custom.xml)
//                            dialog.setContentView(R.layout.dialog_custom);
//
//                            // 3. Găsim componentele din interiorul dialogului
//                            TextView tvTitlu = dialog.findViewById(R.id.tv_dialog_titlu);
//                            Button btnDa = dialog.findViewById(R.id.btn_dialog_da);
//                            Button btnNu = dialog.findViewById(R.id.btn_dialog_nu);
//
//                            // 4. Setăm acțiunile pentru butoane
//                            btnDa.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    Toast.makeText(Activitate4.this, "Element adăugat!", Toast.LENGTH_SHORT).show();
//                                    dialog.dismiss(); // Închidem dialogul manual
//                                }
//                            });
//
//                            btnNu.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    dialog.dismiss(); // Închidem dialogul
//                                }
//                            });
//
//                            // 5. Afișăm dialogul
//                            dialog.show();
//                            return true;
//
//                        } else if (item.getItemId() == R.id.opt_despre) {
//                            Toast.makeText(Activitate4.this, "Despre din Popup", Toast.LENGTH_SHORT).show();
//                            return true;
//                        }
//                        return false;
//                    }
//                });
//                popupMenu.show();
//            }
//        });

        // 8. Intent Predefinit (ACTION_VIEW)
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriWeb = Uri.parse("https://www.google.com");
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uriWeb);
                startActivity(intent2);
            }
        });
    }

    // Context Menu Callbacks
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Opțiuni elemente");
        menu.add(0, 101, 0, "Șterge");
        menu.add(0, 102, 0, "Editează");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == 101) {
            Toast.makeText(this, "A fost selectat Șterge", Toast.LENGTH_SHORT).show();
        } else if (id == 102) {
            Toast.makeText(this, "A fost selectat Editează", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    // Options Menu Callbacks
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opt_adauga) {
            Toast.makeText(this, "Adaugă din Toolbar", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.opt_despre) {
            Toast.makeText(this, "Despre din Toolbar", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}