package com.example.project;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class MainViewModel extends ViewModel {

    private static final String TAG = "pineapple";
    private MutableLiveData<ArrayList<String>> currentDeckData = new MutableLiveData<ArrayList<String>>();
    private MutableLiveData<ArrayList<String>> favorites = new MutableLiveData<ArrayList<String>>();
    private ArrayList<String> currentDeck = new ArrayList<String>();
    private ArrayList<String> favoriteDeck = new ArrayList<String>();
    public boolean isTravelSelected;
    public boolean isAnimalSelected;

    public ArrayList<String> animalDeck = new ArrayList<String>(Arrays.asList(
            "Do you have any pets? What are their names?",
            "Are you a cat person or a dog person?",
            "What is your spirit animal? (The animal who is most similar to your personality.)",
            "What is your favorite animal?",
            "What is your favorite magical or mythological creature?",
            "What famous animal movie character do you like the most?",
            "Did you have a stuffed animal as a child? What was its name?",
            "Are there any specific animals you are afraid of? Why?",
            "What is the funniest thing one of your pets have done?",
            "What is your favorite zoo animal?",
            "If you were a fish, what type would you be?",
            "If you had your human body, but the head of an animal, what animal would you pick?"
    ));
    public ArrayList<String> dislikedAnimal = new ArrayList<String>();

    ArrayList<String> travelDeck = new ArrayList<String>(Arrays.asList(
            "What’s the best trip (traveling wise) you ever had?",
            "What’s your favorite thing about the place where you live?",
            "If you could live anywhere in the world for a year, where would it be?",
            "Where is your favorite vacation spot?",
            "What’s your favorite seat on an airplane?",
            "Have you ever been on a cruise? Where did you go?",
            "Do you enjoy the outdoors? What’s your favorite outdoor activity?",
            "How many countries have you visited outside your own?",
            "What is your favorite weekend trip?",
            "Who is your favorite person to travel with?",
            "What is your favorite theme park?",
            "In your opinion, what is the most beautiful place on earth?",
            "Beach, safari, or forest vacation?"
    ));
    public ArrayList<String> dislikedTravel = new ArrayList<String>();

    public MainViewModel() {
        selectAnimalDeck();
    }

    public LiveData<ArrayList<String>> getCurrentDeck() { return currentDeckData; }

    public ArrayList<String> getFavorites() { return favorites.getValue() != null ? favorites.getValue() : new ArrayList<String>(); }

    public void selectAnimalDeck() {
        currentDeckData.setValue(animalDeck);
        isTravelSelected = false;
        isAnimalSelected = true;
    }

    public void selectTravelDeck() {
        currentDeckData.setValue(travelDeck);
        isTravelSelected = true;
        isAnimalSelected = false;
    }

    public void dislikeAnimal(int i) {
        dislikedAnimal.add(animalDeck.get(i));
        animalDeck.remove(i);
    }

    public void dislikeTravel(int i) {
        dislikedTravel.add(travelDeck.get(i));
        travelDeck.remove(i);
    }

    public void shuffleAnimal() {
        animalDeck.addAll(dislikedAnimal);
        dislikedAnimal.clear();
    }

    public void shuffleTravel() {
        travelDeck.addAll(dislikedTravel);
        dislikedTravel.clear();
    }

    public void favoriteQuestion(String s) {
        boolean bool = favoriteDeck.contains(s);
        if (!favoriteDeck.contains(s)) {
            favoriteDeck.add(s);
            favorites.setValue(favoriteDeck);
        }
    }

    public void unFavoriteQuestion(String s) {
        if (favoriteDeck.contains(s)) {
            favoriteDeck.remove(s);
            favorites.setValue(favoriteDeck);
        }
    }



}
