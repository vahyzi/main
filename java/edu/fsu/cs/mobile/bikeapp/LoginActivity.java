package edu.fsu.cs.mobile.bikeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

/**
 * From conversation with Zach Yannes and Firebase Docs
 * https://firebase.google.com/docs/auth/android/firebaseui
 */
public class LoginActivity extends AppCompatActivity {

    private String TAG = "LoginActivity";

    private int AUTH_SIGN_IN = 9001;
    List<AuthUI.IdpConfig> providers =
            Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        // TODO: Check auth
        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers).build(), AUTH_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTH_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                finish();
                Log.d("Info", "");
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference riderRef = db.collection("riders");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email = user.getEmail();
                DocumentReference userInfo = riderRef.document(email);
                userInfo.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Log.d(TAG, "No bike information", task.getException());
                                //startActivity(new Intent(getApplicationContext(), AddBikeInfo.class));
                            }
                        } else {
                            Log.d(TAG, "THINGS ARE VERY WRONG", task.getException());
                        }
                    }
                });
            }
        }
    }
}
