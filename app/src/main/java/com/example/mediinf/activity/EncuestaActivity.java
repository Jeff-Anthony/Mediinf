package com.example.mediinf.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mediinf.R;

public class EncuestaActivity extends AppCompatActivity {

    private SharedPreferences sp;

    private EditText majorInput;

    private RadioGroup genderRadioGroup;

    private CheckBox policyCheckbox;

    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);

        sp = PreferenceManager.getDefaultSharedPreferences(this);

        // Carrera
        majorInput = findViewById(R.id.major_input);
        majorInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String major = s.toString();
                sp.edit().putString("major", major).commit();
            }
        });

        // Recuperar el valor de la SP
        String major = sp.getString("major", null);
        if(major != null) {
            majorInput.setText(major);
        }

        // Género
        genderRadioGroup = findViewById(R.id.gender_group);
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.male_radio:
                        sp.edit().putString("gender", "M").commit();
                        break;
                    case R.id.female_radio:
                        sp.edit().putString("gender", "F").commit();
                        break;
                }
            }
        });

        // Recuperar el valor de la SP
        String gender = sp.getString("gender", null);
        if(gender != null) {
            if("M".equals(gender)) {
                genderRadioGroup.check(R.id.male_radio);
            }else if("F".equals(gender)) {
                genderRadioGroup.check(R.id.female_radio);
            }

        }

        // Política de Privacidad
        policyCheckbox = findViewById(R.id.policy_checkbox);
        policyCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sp.edit().putBoolean("policy", isChecked).commit();
            }
        });

        // Recuperar el valor de la SP
        boolean policy = sp.getBoolean("policy", false);
        if(policy) {
            policyCheckbox.setChecked(policy);
        }

        // Botón enviar
        sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Enciando encuenta al servidor...
                Toast.makeText(EncuestaActivity.this, "Encuesta enviada con Exito!", Toast.LENGTH_SHORT).show();

                sp.edit().remove("major").remove("gender").remove("policy").commit();

                finish();
            }
        });

    }
}
