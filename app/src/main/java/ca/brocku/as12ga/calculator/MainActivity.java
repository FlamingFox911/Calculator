package ca.brocku.as12ga.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private List<Button> bList;
    private static final int[] bIDs = {
            R.id.one,
            R.id.two,
            R.id.three,
            R.id.four,
            R.id.five,
            R.id.six,
            R.id.seven,
            R.id.eight,
            R.id.nine,
            R.id.zero,
            R.id.plus,
            R.id.minus,
            R.id.mult,
            R.id.div,
            R.id.equal,
            R.id.decim
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bList = new ArrayList<>(bIDs.length);
        for (int id : bIDs) {
            final Button b = (Button)findViewById(id);
            b.setOnClickListener(this);
            bList.add(b);
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.one:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.two:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.three:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.four:
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.five:
                Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.six:
                Toast.makeText(this, "6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.seven:
                Toast.makeText(this, "7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.eight:
                Toast.makeText(this, "8", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nine :
                Toast.makeText(this, "9", Toast.LENGTH_SHORT).show();
                break;
            case R.id.zero:
                Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
