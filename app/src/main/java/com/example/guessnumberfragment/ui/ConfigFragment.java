package com.example.guessnumberfragment.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.guessnumberfragment.R;
import com.example.guessnumberfragment.data.Game;
import com.example.guessnumberfragment.databinding.FragmentConfigBinding;
import com.example.guessnumberfragment.databinding.FragmentPlayBinding;

/**
 * <h1>Proyecto GuessNumberFragment</h1>
 * Este proyecto consiste en lo siguiente:
 * Introducimos nuesto nombre de usuario y un numero de intentos predeterminado, y mediante un boton pasamos a la siguiente actividad de nuestro juego
 * En esta segunda actividad debemos, mediante un edittext introducir numeros hasta intentar adivinar el numero secreto
 * En caso de que acertemos antes de que se nos acaben los intentos, o gastemos todos los intentos pasaremos a la tercera y ultima pantalla
 * En esta se muestran mensajes informativos como tu nombre de usuario, si has ganado o perdido o el número secreto en el caso de que hayas perdido
 * <p>
 * Esta activity recoge el nombre del usuario y el numero maximo de intentos mediante dos edittext y un boton
 *
 * @author Angel Rubio
 * @version 1.0
 */

public class ConfigFragment extends Fragment {

    private static final String TAG = "ConfigFragment";
    private FragmentConfigBinding binding;

    public ConfigFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ConfigFragment -> onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentConfigBinding.inflate(inflater);
        binding.setGame(new Game());

        binding.btStart.setOnClickListener(view -> sendData());

        Log.d(TAG, "ConfigFragment -> onCreateView()");

        return binding.getRoot();
    }

    public void sendData(View view) {
        sendData();
    }

    public void sendData() {
        if (TextUtils.isEmpty((binding.etName.getText().toString())) || (TextUtils.isEmpty((binding.etAttempts.getText().toString()))) || Integer.parseInt(binding.etAttempts.getText().toString()) == 0)
            return;
        else
            binding.setGame(new Game(binding.etName.getText().toString(), Integer.parseInt(binding.etAttempts.getText().toString())));

        Bundle bundle = new Bundle();
        bundle.putParcelable(Game.KEY, binding.getGame());
        NavHostFragment.findNavController(this).navigate(R.id.action_configFragment_to_playFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.d(TAG, "ConfigFragment -> OnDestroy()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "ConfigFragment -> onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "ConfigFragment -> OnResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "ConfigFragment -> OnPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "ConfigFragment -> onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ConfigFragment -> OnDestroy()");
    }
}