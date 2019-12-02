package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movielist.Api.RetrofitClient;
import com.example.movielist.FuncoesEstaticas.FuncoesEstaticas;
import com.example.movielist.Inteface.NodeServer;
import com.example.movielist.Inteface.Organization;
import com.example.movielist.Models.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements Organization {
    EditText login,senha;
    Button confimar;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }

        reconhecerElementos();
        reconhecerListeners();

    }
    @Override
    public void reconhecerElementos() {
        this.btnRegistrar = (Button) findViewById(R.id.cadastrar);
        this.confimar = (Button) findViewById(R.id.confimar);
        this.login = (EditText)findViewById(R.id.login);
        this.senha = (EditText)findViewById(R.id.senha);
    }

    @Override
    public void reconhecerListeners() {
        this.confimar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!FuncoesEstaticas.isConnected(getApplicationContext())){
                    Toast toast = Toast.makeText(getApplicationContext(), "Sem conexão", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                String strEmail = login.getText().toString();
                String strPassword = senha.getText().toString();
                //LoginAuth.login(strEmail, strPassword);

                NodeServer service = RetrofitClient.getRetrofitInstance().create(NodeServer.class);
                Call<Usuario> call = service.login(strEmail, strPassword);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if(response.isSuccessful()){
                            Log.e("ONRESPONSE","LOGADO COM SUCESSO");
                            Toast toast = Toast.makeText(getApplicationContext(), "Seja bem vindo " + response.body().getNome(), Toast.LENGTH_SHORT);
                            toast.show();
                            final String nome =response.body().getNome();
                            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(getString(R.string.pref_email), response.body().getEmail());
                            editor.putString(getString(R.string.pref_email), response.body().getEmail());
                            editor.putString(getString(R.string.pref_nome), response.body().getNome());

                            editor.apply();

//                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Log.e("ONRESPONSE","CREDENCIAIS INVÁLIDAS");
                            Toast toast = Toast.makeText(getApplicationContext(), "credencias invalidas " , Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Log.e("OSFAILURE", "NÃO OK");
                    }
                });
            }
        });

        this.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Cadastrar2.class);
                startActivityForResult(i, 1);
            }
        });
    }
}
