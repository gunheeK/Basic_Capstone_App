package com.peachjelly99.tuto1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        GraphView graph=(GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series =
                new LineGraphSeries<>();
        for (int i = 0; i < 10; i++) {

            float val = (float) (Math.random() * 10);
            series.appendData(new DataPoint(i, val),true,90);
        }


        graph.addSeries(series);
    }
}