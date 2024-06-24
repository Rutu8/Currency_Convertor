package com.task.currency_convertor;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.task.currency_convertor.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    double fromValue = 0.0;
    String fromUnit = " ";
    double toValue = 0.0;
    String toUnit = " ";

    private final List<String> units = Arrays.asList(
            "INR",
            "USD"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initComponents();
    }

    private void initComponents() {
        Collections.sort(units);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, units);
        binding.toUnit.setAdapter(arrayAdapter);
        binding.fromUnit.setAdapter(arrayAdapter);

        binding.convertButton.setOnClickListener(view -> {
            try {
                fromValue = Double.parseDouble(binding.fromCurrency.getText().toString());
                fromUnit = binding.fromUnit.getSelectedItem().toString();
                toUnit = binding.toUnit.getSelectedItem().toString();
                convertValue();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void convertValue() {
        try {
            // Replace with actual conversion rates or API call to get the rates
            if (fromUnit.equalsIgnoreCase("INR") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue / 83.22;
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("INR")) {
                toValue = fromValue * 83.22;
            } else {
                Toast.makeText(this, "Conversion not available for selected currencies", Toast.LENGTH_LONG).show();
                return;
            }
            binding.toCurrency.setText(String.valueOf(toValue));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
