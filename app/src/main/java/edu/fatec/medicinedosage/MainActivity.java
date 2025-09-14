package edu.fatec.medicinedosage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText edtMedicine, edtWeight, edtDoseKg, edtnDays, edtMg, edtMlUn, edtMaxDose, edtFirstDose;
    Spinner spiDoses, spiTypeConc;
    TextView txtMg, txtMlUn;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        setContentView(R.layout.activity_main);

        edtMedicine = (EditText) findViewById(R.id.edtMedicine);
        edtWeight = (EditText) findViewById(R.id.edtWeight);
        edtDoseKg = (EditText) findViewById(R.id.edtDoseKg);
        edtnDays = (EditText) findViewById(R.id.edtnDays);
        edtMg = (EditText) findViewById(R.id.edtMg);
        edtMlUn = (EditText) findViewById(R.id.edtMlUn);

        spiDoses = (Spinner) findViewById(R.id.spiDoses);
        spiTypeConc = (Spinner) findViewById(R.id.spiTypeConc);

        txtMg = (TextView) findViewById(R.id.txtMg);
        txtMlUn = (TextView) findViewById(R.id.txtMlUn);

        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        spiTypeConc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstDoseText = edtFirstDose.getText().toString().trim();
                int dosesPerDay = Integer.parseInt(edtnDays.getText().toString().trim());

                List<String> horarios = calculateDoseTimes(firstDoseText, dosesPerDay);

                // Exibir na tela
                txtMlUn.setText("Horários: " + horarios.toString());

                // Log para teste
                for (String h : horarios) {
                    Log.d("DOSE_TIME", "Horário: " + h);
                }
            }
        });

    }



    private Float calculateDose(Float weight, Float doseKg){
        return weight * doseKg;

    }

    private Float calculateDiaryDose(Float dose, Integer nTimes){
        return dose * nTimes;
    }

    private List<String> calculateDoseTimes(String firstDoseText, int dosesPerDay) {
        List<String> times = new ArrayList<>();

        try {
            // Parse da hora inicial (HH:mm)
            LocalTime firstDose = LocalTime.parse(firstDoseText);

            int interval = 24 / dosesPerDay;

            for (int i = 0; i < dosesPerDay; i++) {
                LocalTime doseTime = firstDose.plusHours((long) i * interval);
                times.add(doseTime.toString()); // retorna no formato 08:00
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return times;
    }




}