package com.example.quiz_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
        private Button bt_registrar;
        private EditText et_Name, et_Identif;
        private String nombre, identification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bt_registrar = findViewById(R.id.bt_Register);
        et_Name = findViewById(R.id.et_Name);
        et_Identif = findViewById(R.id.et_Identif);

        bt_registrar.setOnClickListener(
                (v)->{
                    boolean isEmpty = et_Name.getText().toString().trim().isEmpty() ||  et_Identif.getText().toString().trim().isEmpty();


                    if(!isEmpty ) {
                        if(!validarPreferences()){
                            nombre = et_Name.getText().toString();
                            identification = et_Identif.getText().toString();
                            Intent i = new Intent(this, EpimioActivity.class);
                            i.putExtra("nombre", nombre);
                            i.putExtra("identifi", identification);
                            startActivity(i);
                        }else{
                            Toast toast = Toast.makeText(this,"Identificacion ya registrada", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }else{
                        Toast toast = Toast.makeText(this,"Llena todos los campos", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
        );
    }
    public boolean validarPreferences(){
        SharedPreferences preferences = getSharedPreferences("identifi",MODE_PRIVATE);
        String identifi = preferences.getString("identifi","");

        if(identifi.contains(et_Identif.getText().toString())){
            return true;
        }
      return false;
    }

    }
