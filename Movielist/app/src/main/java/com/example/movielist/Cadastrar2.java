package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movielist.Api.RetrofitClient;
import com.example.movielist.FuncoesEstaticas.FuncoesEstaticas;
import com.example.movielist.Inteface.NodeServer;
import com.example.movielist.Inteface.Organization;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastrar2 extends AppCompatActivity implements Organization {
    EditText editEmail;
    EditText editNome;
    EditText editSenha;
    Button btnRegisterCriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar2);
        reconhecerElementos();
        reconhecerListeners();
    }

    @Override
    public void reconhecerElementos() {
        this.editEmail = (EditText) findViewById(R.id.emailCA);
        this.editNome = (EditText) findViewById(R.id.loginCA);
        this.editSenha = (EditText) findViewById(R.id.senhaCA);
        this.btnRegisterCriar = (Button) findViewById(R.id.cadastrarCA);
    }

    @Override
    public void reconhecerListeners() {

        this.btnRegisterCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!FuncoesEstaticas.isConnected(getApplicationContext())){
                    Toast toast = Toast.makeText(getApplicationContext(), "Sem conexão", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                String email =  editEmail.getText().toString();
                String nome =  editNome.getText().toString();

                String senha =  editSenha.getText().toString();


                Usuario usuario = new Usuario(nome,email, senha);
                Log.i("PRINT", usuario.getNome());

                NodeServer service = RetrofitClient.getRetrofitInstance().create(NodeServer.class);
                Call<Usuario> call = service.criarsessions(nome,email, senha);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if(response.isSuccessful()){
                            Log.i("PRINT", "Criado");

                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("nome",nome);
                            returnIntent.putExtra("email",email);
                            setResult(Activity.RESULT_OK,returnIntent);
                            finish();
                        }else{
                            Log.i("PRINT", "NÃO Criado");
                        }

                    }
                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Log.e("CriarUsuario", t.getMessage());
                    }
                });
            }
        });
    }
}
