package com.example.calculator;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.function.BiFunction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calculate(R.id.plusBtn, (num1, num2) -> num1 + num2);
        calculate(R.id.minusBtn, (num1, num2) -> num1 - num2);
        calculate(R.id.multiplyBtn, (num1, num2) -> num1 * num2);
        calculate(R.id.divideBtn, (num1, num2) -> num1 / num2);
        clearBtn();

    }

    public void clearBtn(){
        Button button = findViewById(R.id.clearBtn);
        button.setOnClickListener(l->{
            Resources res = getResources();
            EditText num1 = findViewById(R.id.number1);
            EditText num2 = findViewById(R.id.number2);
            TextView answer = findViewById(R.id.answer);

            num1.setText("");
            num2.setText("");


            answer.setText(res.getString(R.string.answerLabelDefault));

        });
    }


    public void calculate(int id, BiFunction expression){
        Button addBtn = findViewById(id);
        addBtn.setOnClickListener(l -> {
            EditText number1 = findViewById(R.id.number1);
            EditText number2 = findViewById(R.id.number2);

            double double1 = Double.parseDouble(number1.getText().toString());
            double double2 = Double.parseDouble(number2.getText().toString());
            double answer = expression.apply(double1, double2);

            Resources res = getResources();
            TextView answerLabel = findViewById(R.id.answer);

            answerLabel.setText(res.getString(R.string.answerLabel, String.format("%.2f", answer)));
        });

    }

    public interface BiFunction {
        public double apply(double num1, double num2);
    }
}