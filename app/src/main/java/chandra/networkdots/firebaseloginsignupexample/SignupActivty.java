package chandra.networkdots.firebaseloginsignupexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivty extends AppCompatActivity {

    EditText emailS,passwordS;
    Button signUpP;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activty);

        emailS = findViewById(R.id.editText);
        passwordS = findViewById(R.id.passwordEdit);
        signUpP = findViewById(R.id.signupButton);
        mAuth = FirebaseAuth.getInstance();

        signUpP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        String email,password;

        email = emailS.getText().toString();
        password = passwordS.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
         mAuth.createUserWithEmailAndPassword(email,password)
                 .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             startActivity(new Intent(SignupActivty.this,MainActivity.class));
                             finish();
                         }else{
                             Toast.makeText(SignupActivty.this, "Error in registering user", Toast.LENGTH_SHORT).show();
                         }
                     }
                 }).addOnCanceledListener(new OnCanceledListener() {
                     @Override
                     public void onCanceled() {

                     }
                 });

    }
}