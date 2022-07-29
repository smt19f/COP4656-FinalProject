package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class StackAdapter extends BaseAdapter {

    private ArrayList<String> questionData;
    private Context context;
    public MainViewModel viewModel;

    public StackAdapter(ArrayList<String> newQuestionData, Context newContext, MainViewModel newViewModel) {
        questionData = newQuestionData;
        context = newContext;
        viewModel = newViewModel;
    }

    @Override
    public int getCount() {
        return questionData.size();
    }

    @Override
    public Object getItem(int i) {
        return questionData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.question_item, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.textView)).setText(questionData.get(i));
        Switch switch1 = view.findViewById(R.id.switch1);



        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    viewModel.favoriteQuestion(questionData.get(i));
                }
                else {
                    viewModel.unFavoriteQuestion(questionData.get(i));
                }
            }
        });

        return view;
    }
}
