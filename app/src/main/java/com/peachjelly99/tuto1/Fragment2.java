package com.peachjelly99.tuto1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_2, container, false);
        GraphView graph=(GraphView) rootView.findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series =
                new LineGraphSeries<>();
        for (int i = 0; i < 10; i++) {

            float val = (float) (Math.random() * 10);
            series.appendData(new DataPoint(i, val),true,90);
        }

        graph.addSeries(series);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}