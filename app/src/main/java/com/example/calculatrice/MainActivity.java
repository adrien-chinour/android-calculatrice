package com.example.calculatrice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private double calcul;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View view) {
        Button buttonElt = (Button) view;
        TextView textView = findViewById(R.id.textView);

        String button = buttonElt.getText().toString();
        String number = textView.getText().toString();
        String value = "";

        if (button.equals("C")) {
            resetCalculatrice();
        } else if (button.equals("+") || button.equals("*") || button.equals("/") || button.equals("-")) {
            if (number.length() != 0) this.calcul = Double.valueOf(number);
            this.operator = button;
        } else {
            value = number + button;
        }

        textView.setText(value);
    }

    /**
     * Action liée au click sur le bouton "=" va lancer le résultat de l'opéation
     *
     * @param view : bouton cliqué
     */
    public void resultClick(View view) {
        TextView textView = findViewById(R.id.textView);
        String value = textView.getText().toString();
        double result;

        try {
            result = calculate(value);
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        String resultString = Double.toString(result);
        textView.setText(resultString);
        this.operator = "";
    }

    /**
     * Calcul le résultat de l'opération operator entre value et la valeur courante de notre affichage
     *
     * @param value : la valeur courante de l'affichage
     * @return double resultat de l'operation
     */
    private double calculate(String value) throws IllegalArgumentException {

        if (value.isEmpty()) {
            throw new IllegalArgumentException("Aucun nombre entré.");
        }

        Double number = Double.valueOf(value);

        switch (this.operator) {
            case "+":
                return this.calcul + number;
            case "-":
                return this.calcul - number;
            case "/":
                return this.calcul / number;
            case "*":
                return this.calcul * number;
            default:
                throw new IllegalArgumentException("Aucune opération n'a été éffectué");
        }
    }

    private void resetCalculatrice() {
        this.operator = "";
        this.calcul = 0;
    }


}
