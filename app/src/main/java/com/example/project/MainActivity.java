package com.example.project;

import android.os.Bundle;
import android.widget.TextView;

import com.daprlabs.cardstack.SwipeDeck;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.project.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public MainViewModel viewModel;

    private SwipeDeck cardStack;
    private ArrayList<String> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);;

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //grab stack ui and questions from ViewModel
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);
        questionList = viewModel.getCurrentDeck().getValue();

        final StackAdapter adapter = new StackAdapter(questionList, this, viewModel);
        cardStack.setAdapter(adapter);
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                if (viewModel.isAnimalSelected) {
                    viewModel.dislikeAnimal(position);
                }
                else if (viewModel.isTravelSelected) {
                    viewModel.dislikeTravel(position);
                }
            }

            @Override
            public void cardSwipedRight(int position) {

            }

            @Override
            public void cardsDepleted() {

            }

            @Override
            public void cardActionDown() {
//                TextView textView = (TextView) cardStack.getSelectedView().findViewById(R.id.textView);
//                viewModel.unFavoriteQuestion(textView.getText().toString());
            }

            @Override
            public void cardActionUp() {
//                TextView textView = (TextView) cardStack.getSelectedView().findViewById(R.id.textView);
//                viewModel.favoriteQuestion(textView.getText().toString());
            }
        });

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}