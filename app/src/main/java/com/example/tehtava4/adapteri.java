package com.example.tehtava4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class adapteri extends ArrayAdapter<luokka> {

    private Context context;
    ArrayList<luokka> dataset;
    luokka luokka;


    public adapteri(@NonNull Context context, int resource, @NonNull List<luokka> objects) {
        super(context, resource, objects);
        this.dataset = (ArrayList<luokka>) objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layoutti, parent, false);

        TextView teksti1 = view.findViewById(R.id.teksti1);
        TextView teksti2 = view.findViewById(R.id.teksti2);

        teksti1.setText(dataset.get(position).getTitle());
        teksti2.setText(dataset.get(position).getBody());
        return view;
    }

}
