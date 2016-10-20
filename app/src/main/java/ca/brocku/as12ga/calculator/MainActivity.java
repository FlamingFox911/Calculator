package ca.brocku.as12ga.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class MainActivity extends Activity implements View.OnClickListener {
    private static final String ZERO_STATE = "0";

    private List<Button> bList;
    private boolean hasDecim = false;
    private String input = "0";
    private TextView output;
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
            R.id.decim,
            R.id.AC,
            R.id.Clr
    };

    private String uniToStr(int unicode){
        return new String(Character.toChars(unicode));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView)findViewById(R.id.output);
        bList = new ArrayList<>(bIDs.length);
        for (int id : bIDs) {
            final Button b = (Button)findViewById(id);
            b.setOnClickListener(this);
            bList.add(b);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.one:
                if (Objects.equals(input, ZERO_STATE)) input = "";
                input += "1";
                break;
            case R.id.two:
                if (Objects.equals(input, ZERO_STATE)) input = "";
                input += "2";
                break;
            case R.id.three:
                if (Objects.equals(input, ZERO_STATE)) input = "";
                input += "3";
                break;
            case R.id.four:
                if (Objects.equals(input, ZERO_STATE)) input = "";
                input += "4";
                break;
            case R.id.five:
                if (Objects.equals(input, ZERO_STATE)) input = "";
                input += "5";
                break;
            case R.id.six:
                if (Objects.equals(input, ZERO_STATE)) input = "";
                input += "6";
                break;
            case R.id.seven:
                if (Objects.equals(input, ZERO_STATE)) input = "";
                input += "7";
                break;
            case R.id.eight:
                if (Objects.equals(input, ZERO_STATE)) input = "";
                input += "8";
                break;
            case R.id.nine :
                if (Objects.equals(input, ZERO_STATE)) input = "";
                input += "9";
                break;
            case R.id.zero:
                if (Objects.equals(input, ZERO_STATE)) input = "";
                input += "0";
                break;
            case R.id.plus:
                Toast.makeText(this, uniToStr(0x002B), Toast.LENGTH_SHORT).show();
                break;
            case R.id.minus:
                Toast.makeText(this, uniToStr(0x2212), Toast.LENGTH_SHORT).show();
                break;
            case R.id.mult:
                Toast.makeText(this, uniToStr(0x00D7), Toast.LENGTH_SHORT).show();
                break;
            case R.id.div:
                Toast.makeText(this, uniToStr(0x00F7), Toast.LENGTH_SHORT).show();
                break;
            case R.id.equal:
                Toast.makeText(this, uniToStr(0x003D), Toast.LENGTH_SHORT).show();
                break;
            case R.id.decim:
                if (!hasDecim){
                    input += ".";
                    hasDecim = true;
                }
                break;
            case R.id.AC:
                Toast.makeText(this, "ALL CLEAR", Toast.LENGTH_SHORT).show();
                input = ZERO_STATE;
                hasDecim = false;
                break;
            case R.id.Clr:
                Toast.makeText(this, "CLEAR", Toast.LENGTH_SHORT).show();
                input = ZERO_STATE;
                hasDecim = false;
                break;
        }
        output.setText(input);
    }
}