package com.example.guessnumberfragment.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guessnumberfragment.R;
import com.example.guessnumberfragment.data.Game;
import com.example.guessnumberfragment.databinding.FragmentEndPlayBinding;


/**
 * <h1>Proyecto GuessNumberFragment</h1>
 * Este proyecto consiste en lo siguiente:
 * Introducimos nuesto nombre de usuario y un numero de intentos predeterminado, y mediante un boton pasamos a la siguiente actividad de nuestro juego
 * En esta segunda actividad debemos, mediante un edittext introducir numeros hasta intentar adivinar el numero secreto
 * En caso de que acertemos antes de que se nos acaben los intentos, o gastemos todos los intentos pasaremos a la tercera y ultima pantalla
 * En esta se muestran mensajes informativos como tu nombre de usuario, si has ganado o perdido o el número secreto en el caso de que hayas perdido
 *
 * Esta activity recoge todos los datos y los muestra por pantalla segun si has ganado o has perdido
 *
 * @author Angel Rubio
 * @version 1.0
 */

public class EndPlayFragment extends Fragment {

    private static final String TAG = "EndPlayFragment";
    private FragmentEndPlayBinding binding;


    public EndPlayFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "EndPlayFragment -> onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEndPlayBinding.inflate(inflater);

        binding.btConfigFragment.setOnClickListener(view -> NavHostFragment.findNavController(this).navigate(R.id.action_endPlayFragment_to_configFragment));

        Log.d(TAG, "EndPlayFragment -> onCreateView()");

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments().getParcelable(Game.KEY) instanceof Game)
            binding.setGame(getArguments().getParcelable(Game.KEY));

        EndGame();
    }

    protected void EndGame(){
        if (binding.getGame().getWinOrLose() == 0){
            binding.tvWinOrLose.setText("Oh no " + binding.getGame().getName() + " has perdido :( ");
            binding.tvNumberOfAttempts.setText(String.valueOf("Has hecho un total de " + binding.getGame().getNumberOfAttempts() + " intentos"));
            binding.tvNumber.setText(String.valueOf("El número era el " + binding.getGame().getRandomNumber()));
        } else{
            binding.tvWinOrLose.setText("Felicidades " + binding.getGame().getName() + " has ganado :) ");
            binding.tvNumberOfAttempts.setText("Has hecho un total de " + binding.getGame().getNumberOfAttempts() + " intentos");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.d(TAG, "EndPlayFragment -> OnDestroy()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "EndPlayFragment -> onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "EndPlayFragment -> OnResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "EndPlayFragment -> OnPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "EndPlayFragment -> onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "EndPlayFragment -> OnDestroy()");
    }

}