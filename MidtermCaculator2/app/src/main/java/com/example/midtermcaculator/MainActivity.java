package com.example.midtermcaculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editNumber1;
    private EditText editNumber2;
    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;
    private Button btnExp;
    private Button btnMod;
    private Button btnSqr;
    private Button btnLog;
    private Button btnSin;
    private Button btnCos;
    private Button btnTan;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber1 = findViewById(R.id.editNumber1);
        editNumber2 = findViewById(R.id.editNumber2);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnExp = findViewById(R.id.btnExp);
        btnMod = findViewById(R.id.btnMod);
        btnSqr = findViewById(R.id.btnSqr);
        btnLog = findViewById(R.id.btnLog);
        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);

        txtResult = findViewById(R.id.txtResult);


        // Add the two numbers
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editNumber1.getText().toString().trim();
                String text2 = editNumber2.getText().toString().trim();

                if (text1.isEmpty() || text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter both numbers", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int value1 = Integer.parseInt(text1);
                    int value2 = Integer.parseInt(text2);
                    int sum = value1 + value2;
                    txtResult.setText("Sum: " + sum);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Subtract the two numbers
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editNumber1.getText().toString().trim();
                String text2 = editNumber2.getText().toString().trim();

                if (text1.isEmpty() || text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter both numbers", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int value1 = Integer.parseInt(text1);
                    int value2 = Integer.parseInt(text2);
                    int dif = value1 - value2;
                    txtResult.setText("Difference: " + dif);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // multiply the two numbers
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editNumber1.getText().toString().trim();
                String text2 = editNumber2.getText().toString().trim();

                if (text1.isEmpty() || text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter both numbers", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int value1 = Integer.parseInt(text1);
                    int value2 = Integer.parseInt(text2);
                    int prod = value1 * value2;
                    txtResult.setText("Product: " + prod);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // divide the two numbers
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editNumber1.getText().toString().trim();
                String text2 = editNumber2.getText().toString().trim();

                if (text1.isEmpty() || text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter both numbers", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double value1 = Integer.parseInt(text1);
                    double value2 = Integer.parseInt(text2);
                    double qut = value1 / value2;
                    txtResult.setText("Quotient: " + qut);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // exponentional
        btnExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editNumber1.getText().toString().trim();
                String text2 = editNumber2.getText().toString().trim();

                if (text1.isEmpty() || text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter both numbers", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int value1 = Integer.parseInt(text1);
                    int value2 = Integer.parseInt(text2);
                    double pow = Math.pow(value1 , value2);
                    txtResult.setText(value1+" to the power of "+value2+": " + pow);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // modulo
        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editNumber1.getText().toString().trim();
                String text2 = editNumber2.getText().toString().trim();

                if (text1.isEmpty() || text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter both numbers", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int value1 = Integer.parseInt(text1);
                    int value2 = Integer.parseInt(text2);
                    int rem = value1 % value2;
                    txtResult.setText("Remainder: " + rem);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //single value operations
        // Square root
        btnSqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editNumber1.getText().toString().trim();
                String text2 = editNumber2.getText().toString().trim();

                if (text1.isEmpty() && text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter one number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!text1.isEmpty() && !text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter only one number", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    if (!text1.isEmpty() && text2.isEmpty()) { //using first value
                        int value1 = Integer.parseInt(text1);
                        double sqr = Math.sqrt(value1);
                        txtResult.setText("Square Root: " + sqr);
                    }
                    if (text1.isEmpty() && !text2.isEmpty()) { //using first value
                        int value2 = Integer.parseInt(text2);
                        double sqr = Math.sqrt(value2);
                        txtResult.setText("Square Root: " + sqr);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Logarithm
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editNumber1.getText().toString().trim();
                String text2 = editNumber2.getText().toString().trim();

                if (text1.isEmpty() && text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter one number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!text1.isEmpty() && !text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter only one number", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    if (!text1.isEmpty() && text2.isEmpty()) { //using first value
                        int value1 = Integer.parseInt(text1);
                        double log = Math.log(value1);
                        txtResult.setText("Logarithm: " + log);
                    }
                    if (text1.isEmpty() && !text2.isEmpty()) { //using first value
                        int value2 = Integer.parseInt(text2);
                        double log = Math.log(value2);
                        txtResult.setText("Logarithm: " + log);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Sin
        btnSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editNumber1.getText().toString().trim();
                String text2 = editNumber2.getText().toString().trim();

                if (text1.isEmpty() && text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter one number", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    if (!text1.isEmpty() && text2.isEmpty()) { //using first value
                        int value1 = Integer.parseInt(text1);
                        double sin = Math.sin(value1);
                        txtResult.setText("Sin"+value1+": " + sin);
                    }
                    if (text1.isEmpty() && !text2.isEmpty()) { //using second value
                        int value2 = Integer.parseInt(text2);
                        double sin = Math.sin(value2);
                        txtResult.setText("Sin"+value2+": " + sin);
                    }
                    if (!text1.isEmpty() && !text2.isEmpty()) { //using both value
                        int value1 = Integer.parseInt(text1);
                        int value2 = Integer.parseInt(text2);
                        double sin = value1 * (Math.sin(value2));
                        txtResult.setText(value1+"Sin"+value2+": " + sin);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Cos
        btnCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editNumber1.getText().toString().trim();
                String text2 = editNumber2.getText().toString().trim();

                if (text1.isEmpty() && text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter one number", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    if (!text1.isEmpty() && text2.isEmpty()) { //using first value
                        int value1 = Integer.parseInt(text1);
                        double cos = Math.cos(value1);
                        txtResult.setText("Cos"+value1+": " + cos);
                    }
                    if (text1.isEmpty() && !text2.isEmpty()) { //using second value
                        int value2 = Integer.parseInt(text2);
                        double cos = Math.cos(value2);
                        txtResult.setText("Cos"+value2+": " + cos);
                    }
                    if (!text1.isEmpty() && !text2.isEmpty()) { //using both value
                        int value1 = Integer.parseInt(text1);
                        int value2 = Integer.parseInt(text2);
                        double cos = value1 * (Math.cos(value2));
                        txtResult.setText(value1+"Cos"+value2+": " + cos);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // tan
        btnTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editNumber1.getText().toString().trim();
                String text2 = editNumber2.getText().toString().trim();

                if (text1.isEmpty() && text2.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter one number", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    if (!text1.isEmpty() && text2.isEmpty()) { //using first value
                        int value1 = Integer.parseInt(text1);
                        double tan = Math.tan(value1);
                        txtResult.setText("Tan"+value1+": " + tan);
                    }
                    if (text1.isEmpty() && !text2.isEmpty()) { //using second value
                        int value2 = Integer.parseInt(text2);
                        double tan = Math.tan(value2);
                        txtResult.setText("Tan"+value2+": " + tan);
                    }
                    if (!text1.isEmpty() && !text2.isEmpty()) { //using both value
                        int value1 = Integer.parseInt(text1);
                        int value2 = Integer.parseInt(text2);
                        double tan = value1 * (Math.tan(value2));
                        txtResult.setText(value1+"Tan"+value2+": " + tan);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
