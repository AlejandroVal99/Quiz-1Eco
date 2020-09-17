package com.example.quiz_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button bt_goRegister;
    private TextView list_encues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_goRegister = findViewById(R.id.bt_goRegister);
        list_encues = findViewById(R.id.tx_listUsers);

        bt_goRegister.setOnClickListener(
                (v)->{

                    Intent i = new Intent(this,RegisterActivity.class);
                    startActivity(i);
                }
        );


    }

    @Override
    protected void onResume() {
        SharedPreferences preferences = getSharedPreferences("EncuRea", MODE_PRIVATE);
        String encuestas = preferences.getString("EncuRea","");
        String lista[] = encuestas.split(",",0);
        list_encues.setText("");
        if(lista.length>0){
            for (int i = 0; i < lista.length;i++){
              list_encues.append(lista[i]+"\n");
            }
        }

        super.onResume();
    }
}