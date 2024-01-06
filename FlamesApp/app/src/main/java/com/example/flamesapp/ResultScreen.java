package com.example.flamesapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;

public class ResultScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        Button exit_btn = findViewById(R.id.exit);
        Button calculate_results_btn = findViewById(R.id.calculate_results);

        TextView textView = findViewById(R.id.final_result);

        calculate_results_btn.setOnClickListener(view -> {

            String yourName = getIntent().getStringExtra("your_name");
            String partnerName = getIntent().getStringExtra("partner_name");

            yourName = capitalizeFirstLetter(yourName);
            partnerName = capitalizeFirstLetter(partnerName);

            String uniqueName = findUniqueName(
                    TextUtils.isEmpty(getIntent().getStringExtra("your_name")) ? "" : Objects.requireNonNull(getIntent().getStringExtra("your_name")),
                    getIntent().getStringExtra("partner_name")
            );

            uniqueName = uniqueName.toUpperCase(Locale.ROOT);


            String result = calculateResults(uniqueName);

            switch (result.charAt(0)) {
                case 'F':
                    textView.setText(MessageFormat.format("{0} and {1} are Friends💛", yourName, partnerName));
                    break;
                case 'L':
                    textView.setText(MessageFormat.format("{0} and {1} are Lovers❤️", yourName, partnerName));
                    break;
                case 'A':
                    textView.setText(MessageFormat.format("{0} and {1} are Affectionate towards each other💞", yourName, partnerName));
                    break;
                case 'M':
                    textView.setText(MessageFormat.format("{0} and {1} are Married or will be Married🧡", yourName, partnerName));
                    break;
                case 'E':
                    textView.setText(MessageFormat.format("{0} and {1} are Enemies⚔️", yourName, partnerName));
                    break;
                case 'S':
                    textView.setText(MessageFormat.format("{0} and {1} are Siblings🫱🏻‍🫲🏻", yourName, partnerName));
                    break;
            }
        });

        exit_btn.setOnClickListener(view -> {
            finishAffinity();
            System.exit(0);
        });
    }

    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    String findUniqueName(String name1, String name2) {
        name1 = name1.toUpperCase();
        name2 = name2.toUpperCase();

        StringBuilder uniqueName = new StringBuilder();

        for (int i = 0; i < name1.length(); i++) {
            char currentChar = name1.charAt(i);

            if (name2.indexOf(currentChar) == -1 && uniqueName.indexOf(Character.toString(currentChar)) == -1) {
                uniqueName.append(currentChar);
            }
        }

        for (int j = 0; j < name2.length(); j++) {
            char currentChar = name2.charAt(j);

            if (name1.indexOf(currentChar) == -1 && uniqueName.indexOf(Character.toString(currentChar)) == -1) {
                uniqueName.append(currentChar);
            }
        }

        return uniqueName.toString();
    }

    String calculateResults(@NonNull String name) {
        String FLAMES = "FLAMES";
        int flamesLength = FLAMES.length();
        int completeNameLength = name.length();
        int index = 0;

        while (flamesLength != 1) {
            index = completeNameLength % flamesLength;

            if (index == 0)
                FLAMES = FLAMES.substring(0, flamesLength - 1);
            else
                FLAMES = FLAMES.substring(index) + FLAMES.substring(0, index);

            flamesLength--;
        }

        return FLAMES;
    }

}