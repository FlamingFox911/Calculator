package ca.brocku.as12ga.calculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {
    private static final String ZERO_STATE = "0";
    private enum OPERATOR {
        NONE,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE
    }
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
            R.id.Clr,
            R.id.mem,
            R.id.swap
    };
    private List<Button> bList;
    private boolean hasDecim = false;
    private boolean replace = true;
    private boolean isSetOp = false;
    private String input = "0";
    private OPERATOR currentOp = OPERATOR.NONE;
    private OPERATOR lastOp = OPERATOR.NONE;
    private BigDecimal valX = new BigDecimal ("0");
    private BigDecimal valY = new BigDecimal ("0");
    private BigDecimal mem = new BigDecimal ("0");
    private TextView output;

    // Kudos to vuhung3990 for clipboard snippet. For use in all APIs.
    private void setClipboard(String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Calculation", text);
            clipboard.setPrimaryClip(clip);
        }
        Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
    }

    private BigDecimal evaluate(){
        valY = new BigDecimal(output.getText().toString());
        switch(lastOp) {
            case NONE:
                valX = valY;
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
                    Toast.makeText(this, "ERROR: Divide by Zero. User intended to implode the universe.", Toast.LENGTH_LONG).show();
                    valX = new BigDecimal("0");
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
    public void onSaveInstanceState(Bundle b) {
        b.putBoolean("hasDecim", hasDecim);
        b.putBoolean("replace", replace);
        b.putBoolean("isSetOp", isSetOp);
        b.putString("input", input);
        b.putSerializable("currentOp", currentOp);
        b.putSerializable("lastOp", lastOp);
        b.putString("valX", valX.toPlainString());
        b.putString("valY", valY.toPlainString());
        b.putString("mem", mem.toPlainString());
        super.onSaveInstanceState(b);
    }

    @Override
    public void onRestoreInstanceState(Bundle b) {
        super.onRestoreInstanceState(b);
        hasDecim = b.getBoolean("hasDecim");
        replace = b.getBoolean("replace");
        isSetOp = b.getBoolean("isSetOp");
        input = b.getString("input");
        output.setText(b.getString("input"));
        currentOp = (OPERATOR) b.getSerializable("currentOp");
        lastOp = (OPERATOR) b.getSerializable("lastOp");
        valX = new BigDecimal(b.getString("valX"));
        valY = new BigDecimal(b.getString("valY"));
        mem = new BigDecimal(b.getString("mem"));
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
            if (id == R.id.mem){
                b.setOnLongClickListener(this);
            }
            bList.add(b);
        }
        output.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.output){
            setClipboard(input);
        }
        else if (v.getId() == R.id.mem){
            Toast.makeText(this, "Memory Recall", Toast.LENGTH_SHORT).show();
            output.setText(mem.stripTrailingZeros().toPlainString());
        }
        return true;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.one:
                if (replace) input = "";
                if (isSetOp) {
                    lastOp = currentOp;
                    isSetOp = false;
                }
                input += "1";
                replace = false;
                break;
            case R.id.two:
                if (replace) input = "";
                if (isSetOp) {
                    lastOp = currentOp;
                    isSetOp = false;
                }
                input += "2";
                replace = false;
                break;
            case R.id.three:
                if (replace) input = "";
                if (isSetOp) {
                    lastOp = currentOp;
                    isSetOp = false;
                }
                input += "3";
                replace = false;
                break;
            case R.id.four:
                if (replace) input = "";
                if (isSetOp) {
                    lastOp = currentOp;
                    isSetOp = false;
                }
                input += "4";
                replace = false;
                break;
            case R.id.five:
                if (replace) input = "";
                if (isSetOp) {
                    lastOp = currentOp;
                    isSetOp = false;
                }
                input += "5";
                replace = false;
                break;
            case R.id.six:
                if (replace) input = "";
                if (isSetOp) {
                    lastOp = currentOp;
                    isSetOp = false;
                }
                input += "6";
                replace = false;
                break;
            case R.id.seven:
                if (replace) input = "";
                if (isSetOp) {
                    lastOp = currentOp;
                    isSetOp = false;
                }
                input += "7";
                replace = false;
                break;
            case R.id.eight:
                if (replace) input = "";
                if (isSetOp) {
                    lastOp = currentOp;
                    isSetOp = false;
                }
                input += "8";
                replace = false;
                break;
            case R.id.nine:
                if (replace) input = "";
                if (isSetOp) {
                    lastOp = currentOp;
                    isSetOp = false;
                }
                input += "9";
                replace = false;
                break;
            case R.id.zero:
                if (replace) input = "";
                else replace = false;
                if (isSetOp) {
                    lastOp = currentOp;
                    isSetOp = false;
                }
                input += "0";
                break;
            case R.id.plus:
                currentOp = OPERATOR.PLUS;
                if (!isSetOp){
                    input = evaluate().toPlainString();
                    isSetOp = true;
                    hasDecim = false;
                    replace = true;
                }
                break;
            case R.id.minus:
                currentOp = OPERATOR.MINUS;
                if (!isSetOp){
                    input = evaluate().toPlainString();
                    isSetOp = true;
                    hasDecim = false;
                    replace = true;
                }
                break;
            case R.id.mult:
                currentOp = OPERATOR.MULTIPLY;
                if (!isSetOp){
                    input = evaluate().toPlainString();
                    isSetOp = true;
                    hasDecim = false;
                    replace = true;
                }
                break;
            case R.id.div:
                currentOp = OPERATOR.DIVIDE;
                if (!isSetOp){
                    input = evaluate().toPlainString();
                    isSetOp = true;
                    hasDecim = false;
                    replace = true;
                }
                break;
            case R.id.equal:
                currentOp = OPERATOR.NONE;
                if (!isSetOp){
                    input = evaluate().toPlainString();
                    isSetOp = true;
                    hasDecim = false;
                    replace = true;
                }
                break;
            case R.id.decim:
                if (!hasDecim){
                    if (isSetOp) {
                        input = "0";
                        lastOp = currentOp;
                        isSetOp = false;
                    }
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
                isSetOp = false;
                break;
            case R.id.Clr:
                Toast.makeText(this, "CLEAR", Toast.LENGTH_SHORT).show();
                input = ZERO_STATE;
                hasDecim = false;
                replace = true;
                break;
            case R.id.mem:
                Toast.makeText(this, "Copied to Memory. Hold M to Recall.", Toast.LENGTH_SHORT).show();
                mem = new BigDecimal(input);
                break;
            case R.id.swap:
                if (input.compareTo("0") != 0) {
                    input = input.startsWith("-") ? input.substring(1) : "-" + input;
                }
                break;
        }
        output.setText(input);
    }
}