package com.example.proyectocarlelfinal;

import android.app.Application;
import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.firebase.ui.auth.AuthUI.IdpConfig;
import com.google.firebase.auth.FirebaseUser;

public class SharedViewModel extends AndroidViewModel {

    private final Application app;
    private MutableLiveData<FirebaseUser> user = new MutableLiveData<>();


    public SharedViewModel(@NonNull Application application) {
        super(application);

        this.app = application;
    }

    public LiveData<FirebaseUser> getUser() {
        return user;
    }

    public void setUser(FirebaseUser passedUser) {
        user.postValue(passedUser);
    }
}
