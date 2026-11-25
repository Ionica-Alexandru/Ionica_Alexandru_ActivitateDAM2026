package com.example.seminar4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActivitatePoze extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_poze);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<ImagineDomeniu> lista = new ArrayList<>();

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://gomagcdn.ro/domains/aidaart.ro/files/product/medium/vin-cu-eticheta-personalizata-pentru-cel-mai-bun-tata-750ml-copie-099687.png");
        linkuriImagini.add("https://www.mediafax.ro/wp-content/uploads/images/1/1706/9574490/1-apa-sticle-shutterstock.jpg");
        linkuriImagini.add("https://c.cdnmp.net/884888584/p/m/7/sticla-apa-din-plastic-750-ml~2148427.jpg");
        linkuriImagini.add("https://uniq-gift.ro/ug/wp-content/uploads/2020/08/Eticheta-de-vin-personalizata-cu-poza-cadou-pentru-ziua-de-nastere-iubita-ea-idei.jpg");
        linkuriImagini.add("https://www.shutterstock.com/image-photo/clear-glass-water-bottle-black-260nw-2801150065.jpg");

        List<Bitmap> imagini = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());//myLooper????

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuriImagini) {
                    HttpURLConnection con = null;
                    InputStream is = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (con != null) {
                            con.disconnect();
                        }
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (imagini.size() == linkuriImagini.size()) {
                            lista.add(new ImagineDomeniu("Sticla vin cuplu", imagini.get(0), linkuriImagini.get(0)));
                            lista.add(new ImagineDomeniu("3 sticle de plastic 330ml", imagini.get(1), linkuriImagini.get(1)));
                            lista.add(new ImagineDomeniu("Sticla de plastic pentru tuica de adus la scoala", imagini.get(2), linkuriImagini.get(2)));
                            lista.add(new ImagineDomeniu("Sticla de vin poza aniversare", imagini.get(3), linkuriImagini.get(3)));
                            lista.add(new ImagineDomeniu("Sticla de apa din sticla 500ml", imagini.get(4), linkuriImagini.get(4)));

                            ListView lv = findViewById(R.id.listViewPoze);
                            PozeAdapter pza = new PozeAdapter(lista, getApplicationContext(), R.layout.poze_desc_layout);
                            lv.setAdapter(pza);
                        }
                    }
                });
            }
        });
        executor.shutdown();//Merge si fara?

        ListView lv=findViewById(R.id.listViewPoze);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(getApplicationContext(), WebViewActivity.class);
                it.putExtra("link","https://www.emag.ro/search/sticla%20amuzanta?ref=effective_search");
                startActivity(it);
            }
        });
    }
}