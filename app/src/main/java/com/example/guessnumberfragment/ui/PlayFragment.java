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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.guessnumberfragment.R;
import com.example.guessnumberfragment.data.Game;
import com.example.guessnumberfragment.databinding.FragmentPlayBinding;

import java.util.Random;

/**
 * <h1>Proyecto GuessNumberFragment</h1>
 * Este proyecto consiste en lo siguiente:
 * Introducimos nuesto nombre de usuario y un numero de intentos predeterminado, y mediante un boton pasamos a la siguiente actividad de nuestro juego
 * En esta segunda actividad debemos, mediante un edittext introducir numeros hasta intentar adivinar el numero secreto
 * En caso de que acertemos antes de que se nos acaben los intentos, o gastemos todos los intentos pasaremos a la tercera y ultima pantalla
 * En esta se muestran mensajes informativos como tu nombre de usuario, si has ganado o perdido o el número secreto en el caso de que hayas perdido
 *
 * Esta activity es la principal del juego, en ella debemos intentar adivinar el numero secreto, en el numero de intentos que pusimos en la activity anterior
 * contamos con un edittext para poner el numero, un textview para informarte si el numero secreto es mayor o menor que el introducido, un boton para comprobar
 * y otro boton para reiniciar
 *
 * @author Angel Rubio
 * @version 1.0
 */

public class PlayFragment extends Fragment {

    private static final String TAG = "PlayFragment";
    private FragmentPlayBinding binding;
    private int randomNumber;

    public PlayFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "PlayFragment -> onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayBinding.inflate(inflater);
        binding.setGame(new Game());

        binding.btPlay.setOnClickListener(view -> Play());
        binding.btRetry.setOnClickListener(view -> Delete());

        if (getArguments().getParcelable(Game.KEY) instanceof Game)
            binding.setGame(getArguments().getParcelable(Game.KEY));

        GetRandomNumber();

        binding.btRetry.setEnabled(false);

        Toast.makeText(getContext(), String.valueOf(randomNumber), Toast.LENGTH_SHORT).show();

        Log.d(TAG, "PlayFragment -> onCreateView()");

        return binding.getRoot();
    }

    protected void Play(){
        if (TextUtils.isEmpty((binding.etNumber.getText().toString())))
            return;
        else
            PlayMethod();
    }

    protected void PlayMethod() {
        if (binding.getGame().getAttempts() <= binding.getGame().getNumberOfAttempts()) {
            binding.getGame().setRandomNumber(randomNumber);
            binding.getGame().setWinOrLose(0);
            EndActivity();
        }
        if (Integer.parseInt(binding.etNumber.getText().toString()) > randomNumber) {
            binding.getGame().Increment();
            binding.tvHighOrLow.setText("El numero buscado es menor");
            binding.btPlay.setEnabled(false);
            binding.btRetry.setEnabled(true);

        } else if (Integer.parseInt(binding.etNumber.getText().toString()) < randomNumber) {
            binding.getGame().Increment();
            binding.tvHighOrLow.setText("El numero buscado es mayor");
            binding.btPlay.setEnabled(false);
            binding.btRetry.setEnabled(true);
        } else {
            binding.getGame().setWinOrLose(1);
            EndActivity();
        }
    }

    protected void EndActivity() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Game.KEY, binding.getGame());
        NavHostFragment.findNavController(this).navigate(R.id.action_playFragment_to_endPlayFragment, bundle);
    }

    protected void Delete() {
        binding.etNumber.setText("");
        binding.tvHighOrLow.setText("");
        binding.btRetry.setEnabled(false);
        binding.btPlay.setEnabled(true);
    }

    protected void GetRandomNumber() {
        int min = 0;
        int max = 100;
        randomNumber = new Random().nextInt((max - min) + 1) + min;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.d(TAG, "PlayFragment -> OnDestroy()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "PlayFragment -> onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "PlayFragment -> OnResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "PlayFragment -> OnPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "PlayFragment -> onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "PlayFragment -> OnDestroy()");
    }
}