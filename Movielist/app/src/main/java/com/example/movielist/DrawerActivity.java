package com.example.movielist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.movielist.Fragment.FavoritosFragment;
import com.example.movielist.Fragment.Home;
import com.example.movielist.Fragment.ListaFilmesFragment;
import com.google.android.material.navigation.NavigationView;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private TextView tvEmailDrawer,TOPO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Home()).commit();

        View header = navigationView.getHeaderView(0);
        tvEmailDrawer = header.findViewById(R.id.textView);
        TOPO = header.findViewById(R.id.TOPQ);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(getString(R.string.pref_email), "");
        SharedPreferences sharedPreferences2 = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        String result2 = sharedPreferences2.getString(getString(R.string.pref_nome), "");

        TOPO.setText(result2);
        tvEmailDrawer.setText(result);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_filmes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ListaFilmesFragment()).commit();
                break;

            case R.id.nav_favoritos:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        //new FavoritosFragment()).commit();
                Intent i = new Intent(getApplicationContext(), Favoritas.class);
                startActivity(i);

                break;
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Home()).commit();
                break;
        }

        return true;
    }
}
