package ca.brocku.as12ga.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class MainActivity extends Activity implements View.OnClickListener {
    private static final String ZERO_STATE = "0";

    private List<Button> bList;
    private boolean hasDecim = false;
    private boolean replace = true;
    private String input = "0";
    private enum OPERATOR {
        NONE,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE
    }
    private OPERATOR currentOp = OPERATOR.NONE;
    private OPERATOR lastOp = OPERATOR.NONE;
    private BigDecimal valX = new BigDecimal ("0");
    private BigDecimal valY = new BigDecimal ("0");
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

    private BigDecimal evaluate(){
        valY = new BigDecimal(output.getText().toString());
        switch(lastOp) {
            case NONE:
                break;
            case PLUS:
                valX = valX.add(valY);
                break;
            case MINUS:
                valX = valX.subtract(valY);
                break;
            case MULTIPLY:
                valX = valX.multiply(valY);
                break;
            case DIVIDE:
                if (valY.compareTo(BigDecimal.ZERO) == 0){
                    Toast.makeText(this, "ERROR: Divide by Zero. User intended to implode the universe.", Toast.LENGTH_SHORT).show();
                }
                else {
                    valX = valX.divide(valY, 20, BigDecimal.ROUND_HALF_UP);
                }
                break;
        }
        valX = valX.stripTrailingZeros();
        return valX;
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

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.one:
                if (replace) input = "";
                input += "1";
                replace = false;
                break;
            case R.id.two:
                if (replace) input = "";
                input += "2";
                replace = false;
                break;
            case R.id.three:
                if (replace) input = "";
                input += "3";
                replace = false;
                break;
            case R.id.four:
                if (replace) input = "";
                input += "4";
                replace = false;
                break;
            case R.id.five:
                if (replace) input = "";
                input += "5";
                replace = false;
                break;
            case R.id.six:
                if (replace) input = "";
                input += "6";
                replace = false;
                break;
            case R.id.seven:
                if (replace) input = "";
                input += "7";
                replace = false;
                break;
            case R.id.eight:
                if (replace) input = "";
                input += "8";
                replace = false;
                break;
            case R.id.nine :
                if (replace) input = "";
                input += "9";
                replace = false;
                break;
            case R.id.zero:
                if (replace) input = "";
                else replace = false;
                input += "0";
                break;
            case R.id.plus:
                currentOp = OPERATOR.PLUS;
                input = evaluate().toPlainString();
                lastOp = currentOp;
                hasDecim = false;
                replace = true;
                break;
            case R.id.minus:
                currentOp = OPERATOR.MINUS;
                input = evaluate().toPlainString();
                lastOp = currentOp;
                hasDecim = false;
                replace = true;
                break;
            case R.id.mult:
                currentOp = OPERATOR.MULTIPLY;
                input = evaluate().toPlainString();
                lastOp = currentOp;
                hasDecim = false;
                replace = true;
                break;
            case R.id.div:
                currentOp = OPERATOR.DIVIDE;
                input = evaluate().toPlainString();
                lastOp = currentOp;
                hasDecim = false;
                replace = true;
                break;
            case R.id.equal:
                currentOp = OPERATOR.NONE;
                input = evaluate().toPlainString();
                lastOp = currentOp;
                hasDecim = false;
                replace = true;
                break;
            case R.id.decim:
                if (!hasDecim){
                    input += ".";
                    hasDecim = true;
                    replace = false;
                }
                break;
            case R.id.AC:
                Toast.makeText(this, "ALL CLEAR", Toast.LENGTH_SHORT).show();
                input = ZERO_STATE;
                currentOp = OPERATOR.NONE;
                lastOp = OPERATOR.NONE;
                valX = new BigDecimal("0");
                hasDecim = false;
                replace = true;
                break;
            case R.id.Clr:
                Toast.makeText(this, "CLEAR", Toast.LENGTH_SHORT).show();
                input = ZERO_STATE;
                hasDecim = false;
                replace = true;
                break;
        }
        output.setText(input);
    }
}