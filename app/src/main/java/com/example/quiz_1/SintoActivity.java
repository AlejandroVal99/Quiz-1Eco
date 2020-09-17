package com.example.quiz_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class SintoActivity extends AppCompatActivity {

    private CheckBox cB_NinSin,cB_DifRes, cB_Fiebre,cB_DolG,cB_CongN,cB_Tos, cB_Fatiga;
    private Button bt_Finish;
    private int puntaje;
    private boolean checkOne;
    private String nombre,identificacion;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinto);

        cB_NinSin = findViewById(R.id.cB_NinSin);
        cB_DifRes = findViewById(R.id.cB_DifRes);
        cB_Fiebre = findViewById(R.id.cB_Fiebre);
        cB_DolG = findViewById(R.id.cB_DolG);
        cB_CongN = findViewById(R.id.cB_CongN);
        cB_Tos = findViewById(R.id.cB_Tos);
        cB_Fatiga = findViewById(R.id.cB_Fatiga);

        bt_Finish = findViewById(R.id.bt_Finish);

        puntaje = getIntent().getExtras().getInt("puntaje");
        nombre = getIntent().getExtras().getString("nombre");
        identificacion = getIntent().getExtras().getString("identifi");


        validarChecks();

        bt_Finish.setOnClickListener(
                (v)->{

                    if(checkOne){
                        Intent i = new Intent(this, MainActivity.class);
                        validarPuntaje();
                        registerShared();
                       // Log.e("Puntaje",": "+puntaje );
                        //Log.e(">>>","Nombre: "+nombre+"----Identifi: "+identificacion );
                        startActivity(i);
                        finish();
                    }else{
                        Toast toast = Toast.makeText(this,"Selecciona uno de los campos", Toast.LENGTH_LONG);
                        toast.show();
                    }

                }
        );


    }

    private void registerShared() {
        SharedPreferences preferences= getSharedPreferences("EncuRea",MODE_PRIVATE);
        SharedPreferences preferences2 = getSharedPreferences("identifi",MODE_PRIVATE);

        String registro = preferences.getString("EncuRea","");
        String nuevoRegis = registro + ""+nombre+": "+puntaje+",";

        preferences.edit().putString("EncuRea",nuevoRegis).apply();

        String registroIde = preferences2.getString("identifi","No identificadas");
        String nuevaIde = registroIde+""+identificacion+":";
        preferences2.edit().putString("identifi",nuevaIde).apply();
    }

    private void validarPuntaje() {

        if(cB_Tos.isChecked()){
            puntaje+=4;
        }
        if(cB_Fiebre.isChecked()){
            puntaje+=4;
        }
        if(cB_DifRes.isChecked()){
            puntaje+=4;
        }
        if(cB_Fatiga.isChecked()){
            puntaje+=4;
        }
        if(cB_CongN.isChecked()){
            puntaje+=4;
        }
        if(cB_DolG.isChecked()){
            puntaje+=4;
        }
    }

    private void validarChecks() {

        new Thread(
                ()-> {
                    while (true) {
                        if (cB_NinSin.isChecked() || cB_CongN.isChecked() || cB_DifRes.isChecked() || cB_DolG.isChecked() || cB_Fatiga.isChecked() || cB_Fiebre.isChecked() || cB_Tos.isChecked()) {
                            checkOne = true;
                            runOnUiThread(
                                    () -> {
                                        Drawable d = getResources().getDrawable(R.drawable.button_active);
                                        bt_Finish.setBackground(d);
                                    }
                            );
                        } else {
                            checkOne = false;
                            runOnUiThread(
                                    () -> {
                                        Drawable e = getResources().getDrawable(R.drawable.button_innactive);
                                        bt_Finish.setBackground(e);
                                    }
                            );
                        }
                        if (cB_NinSin.isChecked()) {
                            runOnUiThread(
                                    () -> {
                                        cB_Fatiga.setChecked(false);
                                        cB_DolG.setChecked(false);
                                        cB_DifRes.setChecked(false);
                                        cB_CongN.setChecked(false);
                                        cB_Tos.setChecked(false);
                                        cB_Fiebre.setChecked(false);
                                    }
                            );
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
    }
}