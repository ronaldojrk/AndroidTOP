package com.example.movielist.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielist.DrawerActivity;
import com.example.movielist.Login;
import com.example.movielist.PesquisarFIlme;
import com.example.movielist.R;
import com.example.movielist.Splash.Splash;

public class Home extends Fragment {



    Button pesquisar1,menu1,sair;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menu1 = (Button) view.findViewById(R.id.menufavoritos1);
        sair = (Button) view.findViewById(R.id.sairdinamic2);
        pesquisar1 = (Button) view.findViewById(R.id.pesquisardinamica1);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        pesquisar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PesquisarFIlme.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DrawerActivity.class);
                startActivity(i);

            }
        });
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Login.class);
                startActivity(i);
               // getActivity().getFragmentManager().popBackStack();
                getActivity().finish();

            }
        });
    }
}
