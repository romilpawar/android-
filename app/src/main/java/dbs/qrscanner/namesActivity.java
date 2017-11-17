package dbs.qrscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class namesActivity extends AppCompatActivity {

    TextView Enter_roll_no;
    EditText roll_no;
    Button issue;
    Button back;
    RequestParams params;
    AsyncHttpClient client;
    String message;
    private static String url = "http://192.168.43.250:8080/Sports_ams/AppServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);

        Enter_roll_no=(TextView)findViewById(R.id.textView3);
        issue = (Button)findViewById(R.id.issue_confirm);
        back= (Button)findViewById(R.id.return_main);
        roll_no=(EditText)findViewById(R.id.roll_no);
        Intent intent = getIntent();
        message = intent.getStringExtra(DataActivity.EXTRA_MESSAGE);
    }

    public void return_home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void issue_confirm(View view) {
        AsyncHttpClient client = new AsyncHttpClient();
        params = new RequestParams();
        params.put("type","issue");
        params.put("code",message);
        params.put("issuer",roll_no.getText().toString());
        client =new AsyncHttpClient();
        client.post(url,params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }
}
