package com.example.calculator;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView result;

    private String operand;

    private String operator;

    private Set<String> numbers;

    private Set<String> operators;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);
    }

    /**
     * Initialization of set of number values.
     */
    private void initNumbers() {
        numbers = new HashSet<String>();
        for (int i = 0; i < 10; i++) {
            numbers.add(Integer.toString(i));
        }
    }

    /**
     * Initialization of set of operators.
     */
    private void initOperators() {
        operators = new HashSet<String>();
        String[] ops = { "+", "-", "*", "/" };
        for (String operator : ops) {
            operators.add(operator);
        }
    }

    /**
     * Button click event handler.
     *
     * @param view
     *            clicked button
     * @return void
     */
    public void handleClick(View view) {
        Button clicked = (Button) view;
        String value = clicked.getText().toString();

        if (isNumerical(value)) {
            if (!isDefaultResult(result.getText().toString())) {
                value = result.getText().toString() + value;
            }
        } else if (isOperator(value)) {
            operand = result.getText().toString();
            operator = value;
        } else if (isClear(value)) {
            value = getString(R.string.result_default);
        } else {
            double a = Double.parseDouble(operand), b = Double
                    .parseDouble(result.getText().toString());

            if (operator.equals("+")) {
                value = Double.toString(a + b);
            }

            // Reset values.
            operator = null;
            operand = null;
        }

        result.setText(value);
    }

    /**
     * Test if value is the same as clear button's.
     *
     * @param value
     *            button value
     * @return true if button is clear button
     */
    private boolean isClear(String value) {
        return value.equals(getString(R.string.buttonClear));
    }

    /**
     * Test if value is operator.
     *
     * @param value
     *            button value
     * @return true if value is operator
     */
    private boolean isOperator(String value) {
        if (operators == null) {
            initOperators();
        }
        return operators.contains(value);
    }

    /**
     * Test if result was modified.
     *
     * @param value
     *            result value
     * @return true if result is default
     */
    private boolean isDefaultResult(String value) {
        return value.equals(getString(R.string.result_default));
    }

    /**
     * Test if value is numerical.
     *
     * @param value
     *            button's value
     * @return true if value is numerical
     */
    private boolean isNumerical(String value) {
        if (numbers == null) {
            initNumbers();
        }
        return numbers.contains(value);
    }

}