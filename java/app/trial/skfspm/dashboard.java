package app.trial.skfspm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    Button queue, pending, completed, logout;
    TextView logtext, username;
    AlertDialog.Builder cnfrm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        queue = findViewById(R.id.button);
        pending = findViewById(R.id.button2);
        completed = findViewById(R.id.button7);
        logout = findViewById(R.id.button8);
        cnfrm = new AlertDialog.Builder(this);
        logtext = findViewById(R.id.textView1);
        username = findViewById(R.id.textView);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("USERNAME");
        username.setText("Hello " + userName + "!");
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Working Time - %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        queue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, custlist.class);
                startActivity(intent);
            }
        });


        pending.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, pending_task.class);
                startActivity(intent);
            }
        });

        completed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, completed_task.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                cnfrm.setMessage("Do you want to Logout?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(dashboard.this, Login.class);
                        startActivity(intent);
                    }
                }).
                        setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = cnfrm.create();
                alert.setTitle("Logout");
                alert.show();
            }
        });


    }

    public void startChronometer(View v) {
        logtext.setVisibility(View.INVISIBLE);

        chronometer.setVisibility(View.VISIBLE);

        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);

            chronometer.start();
            running = true;
        }
    }
}