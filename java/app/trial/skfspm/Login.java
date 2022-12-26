package app.trial.skfspm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import static android.provider.Telephony.Mms.Part.TEXT;

public class Login extends AppCompatActivity {

    TextInputLayout regUname, regPword;
    Button Loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        regUname = findViewById(R.id.login_Username);
        regPword = findViewById(R.id.login_Password);
        Loginbutton = findViewById(R.id.login_button);


        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = regUname.getEditText().getText().toString();
                String password = regPword.getEditText().getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("KEY", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    editor.putString(TEXT, username);
                }
                editor.apply();

                if (username.equals("LG01") && password.equals("1") ||
                        username.equals("LG02") && password.equals("2") || username.equals("LG03") && password.equals("3")) {
                    Intent intent = new Intent(Login.this, dashboard.class);
                    intent.putExtra("USERNAME", username);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(Login.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }

            }


        });


    }
}