package com.example.quiz_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class EpimioActivity extends AppCompatActivity {

    private CheckBox cB_Saltra, cB_contaCov, cB_viaInter,cB_viaNal,cB_NigunaAnt;
    private Button bt_nexEpi;
    private boolean checkOne;
    private int puntaje;
    private String nombre, identificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epimio);

       cB_Saltra = findViewById(R.id.cB_SalTra);
       cB_contaCov = findViewById(R.id.cB_contaCov);
       cB_viaInter = findViewById(R.id.cB_viaInter);
       cB_viaNal = findViewById(R.id.cB_viaNal);
       cB_NigunaAnt = findViewById(R.id.cB_NigunaAnt);

       bt_nexEpi = findViewById(R.id.bt_nexEpi);
       checkOne = false;

       nombre = getIntent().getExtras().getString("nombre");
       identificacion = getIntent().getExtras().getString("identifi");

       puntaje = 0;

       validarCheck();

       bt_nexEpi.setOnClickListener(


               (v)->{
                   Log.e(">>>","Extras"+nombre+" : "+identificacion );
                   if(checkOne){
                       validarPuntaje();

                    Intent p = new Intent(this, SintoActivity.class);
                    p.putExtra("puntaje",puntaje);
                    p.putExtra("nombre",nombre);
                    p.putExtra("identifi",identificacion);
                    startActivity(p);
                    finish();
                   }else{
                       Toast toast = Toast.makeText(this,"Selecciona uno de los campos", Toast.LENGTH_LONG);
                       toast.show();
                   }
               }
       );
    }

    private void validarPuntaje() {
        if(cB_contaCov.isChecked()){
            puntaje += 3;
        }
        if(cB_Saltra.isChecked()){
            puntaje +=3;
        }
        if(cB_viaInter.isChecked()){
            puntaje += 3;
        }
        if(cB_viaNal.isChecked()){
            puntaje += 3;
        }
    }

    private void validarCheck() {
        new Thread(
             ()->{
                 while (true){
                     if(cB_contaCov.isChecked() || cB_NigunaAnt.isChecked() || cB_Saltra.isChecked() || cB_viaNal.isChecked() || cB_viaInter.isChecked()){
                         checkOne = true;
                         runOnUiThread(
                                 ()->{
                                     Drawable d = getResources().getDrawable(R.drawable.button_active);
                                     bt_nexEpi.setBackground(d);
                                 }
                         );
                     }else{
                         checkOne = false;
                         runOnUiThread(
                                 ()->{
                                     Drawable e = getResources().getDrawable(R.drawable.button_innactive);
                                     bt_nexEpi.setBackground(e);
                                 }
                         );
                     }
                     if(cB_NigunaAnt.isChecked()){
                         runOnUiThread(
                                 ()->{
                                     cB_viaNal.setChecked(false);
                                     cB_viaInter.setChecked(false);
                                     cB_contaCov.setChecked(false);
                                     cB_Saltra.setChecked(false);
                                 }
                         );
                     }
                     try {
                         Thread.sleep(150);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
        ).start();


    }
}