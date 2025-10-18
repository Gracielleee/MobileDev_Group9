package com.example.mobiledev_unifime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_frame);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityLogin.this, ActivityCreateAccount.class);
            startActivity(intent);
        });

        // Get reference to the button
        Button loginButton = findViewById(R.id.loginButton);

        // Set click listener
        loginButton.setOnClickListener(v -> {
            // Intent to move to the next frame
            Log.d("Button Click", "Create Account Button Clicked");
            Intent intent = new Intent(ActivityLogin.this, ActivitySignIn.class);
            startActivity(intent);
        });
    }
}
