package app.trial.skfspm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class taskDetail extends AppCompatActivity implements View.OnClickListener {
    Button deny;
    String qrbar;
    EditText txt1, txt2, txt3, txt4, txt5, txt6;
//    String text1, text2, text3, text4, text5, text6;


    private static HttpURLConnection connection = null;
    private static HttpURLConnection writeconn = null;

    private static List<String> session = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        deny = findViewById(R.id.button5);
        deny.setOnClickListener(this);
        txt1 = findViewById(R.id.t1);
        txt2 = findViewById(R.id.t2);
        txt3 = findViewById(R.id.t3);
        txt4 = findViewById(R.id.t4);
        txt5 = findViewById(R.id.t5);
        txt6 = findViewById(R.id.t6);




    }


    @Override
    public void onClick(View v) {
//        scancode();
        new GetMethodDemo().execute("https://thinkawm.com/api/movedorder/update.php" );


    }


    public void scancode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }


//    public class GetMethodDemo extends AsyncTask<String , Void ,String> {
//        String server_response;
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            URL url;
//            HttpURLConnection urlConnection = null;
//
//            try {
//                url = new URL(strings[0]);
//                urlConnection = (HttpURLConnection) url.openConnection();
//
//                urlConnection.setRequestMethod("POST");
//                urlConnection.setDoInput(true);
//                urlConnection.setDoOutput(true);
//                urlConnection.connect();
//                String text1 = txt1.getText().toString();
//                String text2 = txt2.getText().toString();
//                String text3 = txt3.getText().toString();
//                String text4 = txt4.getText().toString();
//                String text5 = txt5.getText().toString();
//                String text6 = txt6.getText().toString();
//                JSONObject jsonObjectFinal = new JSONObject();
//                    jsonObjectFinal.put("prodorder", text1);
//
//                jsonObjectFinal.put("rejreason", text2);
//                    jsonObjectFinal.put("rejby", text3);
//                    jsonObjectFinal.put("rejdate", text4);
//                    jsonObjectFinal.put("rejtime", text5);
//                    jsonObjectFinal.put("idletime", text6);
//
//                OutputStream os = urlConnection.getOutputStream();
//                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
//                    writer.write(jsonObjectFinal.toString());
//                    writer.flush();
//                    writer.close();
//                    os.close();
//                    urlConnection.connect();
//
//                int responseCode = urlConnection.getResponseCode();
//
//                if(responseCode == HttpURLConnection.HTTP_OK){
//                    server_response = readStream(urlConnection.getInputStream());
//                    Log.v("CatalogClient", server_response);
//                }
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException | JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            Log.e("Response", "" + server_response);
//
//
//        }
//    }
//
//    private String readStream(InputStream in) {
//        BufferedReader reader = null;
//        StringBuffer response = new StringBuffer();
//        try {
//            reader = new BufferedReader(new InputStreamReader(in));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return response.toString();
//    }

    public class GetMethodDemo extends AsyncTask<String , Void ,String> {
        String server_response;

        @Override
        protected String doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("PUT");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.connect();

                JSONObject jsonObjectFinal = new JSONObject();
                jsonObjectFinal.put("status", "denied");



                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                writer.write(jsonObjectFinal.toString());
                writer.flush();
                writer.close();
                os.close();
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    server_response = readStream(urlConnection.getInputStream());
                    Log.v("CatalogClient", server_response);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.e("Response", "" + server_response);


        }
    }

// Converting InputStream to String

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null) {
//            if (result.getContents() != null) {
//                qrbar = result.getContents();
//
//                try {
//
//                    java.net.URL url = new URL("https://thinkawm.com/api/postatus/create.php");
//
//                    writeconn = (HttpURLConnection) url.openConnection();
//                    writeconn.setRequestMethod("POST");
//                    writeconn.setDoInput(true);
//                    writeconn.setDoOutput(true);
//                    writeconn.setRequestProperty("Content-Type", "application/json");
//                    writeconn.setRequestProperty("charset", "utf-8");
//                    writeconn.connect();
//
//                    JSONObject jsonObjectFinal = new JSONObject();
//                    jsonObjectFinal.put("prodorder", "ZOR");
//                    jsonObjectFinal.put("rejreason", qrbar);
//                    jsonObjectFinal.put("rejby", "01");
//                    jsonObjectFinal.put("rejdate", "01");
//                    jsonObjectFinal.put("rejtime", "100");
//                    jsonObjectFinal.put("idletime", "Created via Merchandiser Mobile App");
//
//
//                    OutputStream os = writeconn.getOutputStream();
//                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
//                    writer.write(jsonObjectFinal.toString());
//                    writer.flush();
//                    writer.close();
//                    os.close();
//                    writeconn.connect();
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } finally {
//                    connection.disconnect();
//                }
//            } else {
//                Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
//            }
//
//        } else {
//            super.onActivityResult(requestCode, requestCode, data);
//        }
//    }

}
