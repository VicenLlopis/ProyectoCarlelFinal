package com.example.proyectocarlelfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.proyectocarlelfinal.databinding.ActivityMainBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    private ActivityMainBinding binding;
    private SharedViewModel sharedViewModel;
    private ActivityResultLauncher<Intent> signInLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);




        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        signInLauncher = registerForActivityResult(
                new FirebaseAuthUIActivityResultContract(),
                (result) -> {
                    if (result.getResultCode() == RESULT_OK) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        sharedViewModel.setUser(user);
                    }
                });

    }


    @Override
    protected void onStart() {
        super.onStart();
       /* Locale userLocale = Locale.getDefault(); // Get the user's default locale
        String userLanguage = "es"; // Get the user's language

// Set the default locale of the application to the user's language
        Locale.setDefault(new Locale(userLanguage));

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder()
                        .setRequireName(true)
                        .setAllowNewAccounts(true)

                        .build());

        try {

            // Get a reference to the Firebase Realtime Database


            // Create an instance of the URL class with the database URL
            URL url = new URL("https://proyectocarles-default-rtdb.firebaseio.com/");

            // Create an instance of the HttpURLConnection class and set the request method
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Set the "X-Firebase-Locale" header value to the user's language or locale
            connection.setRequestProperty("X-Firebase-Locale", "en-US"); // set the header value to "en-US" or any other valid locale

            // Send the request and handle the response
            int responseCode = connection.getResponseCode();
            InputStream inputStream = (responseCode >= 200 && responseCode < 300) ? connection.getInputStream() : connection.getErrorStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            String responseBody = response.toString();
            // Handle the response body
            Log.e("Error malo",responseBody);
        } catch (Exception e) {
            // Handle exceptions
        }
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.setLanguageCode("es");
        Log.e("XXXX", String.valueOf(auth.getCurrentUser()));
        if (auth.getCurrentUser() == null) {
            Intent signInIntent =
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(
                                    Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build()
                                    )
                            )
                            .build();
            signInLauncher.launch(signInIntent);
        } else {
            sharedViewModel.setUser(auth.getCurrentUser());
        }*/
    }

}