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
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class DataActivity extends AppCompatActivity {

    RequestParams params;
    RequestParams params_2;
    AsyncHttpClient client;
    private static String url = "http://192.168.43.250:8080/Sports_ams/AppServlet";

    EditText val_1;
    EditText val_2;
    EditText val_3;
    EditText val_4;
    EditText val_5;
    EditText val_6;
    EditText val_7;
    EditText val_8;
    EditText val_9;
    EditText val_10;

    TextView attr_1;
    TextView attr_2;
    TextView attr_3;
    TextView attr_4;
    TextView attr_5;
    TextView attr_6;
    TextView attr_7;
    TextView attr_8;
    TextView attr_9;
    TextView attr_10;
    TextView cat_name;

    Button save;
    Button try_again;
    String message;
    String category;
    int count;
    public static final String EXTRA_MESSAGE = "key";

    char[] id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        val_1 = (EditText)findViewById(R.id.val_1);
        val_2 = (EditText)findViewById(R.id.val_2);
        val_3 = (EditText)findViewById(R.id.val_3);
        val_4 = (EditText)findViewById(R.id.val_4);
        val_5 = (EditText)findViewById(R.id.val_5);
        val_6 = (EditText)findViewById(R.id.val_6);
        val_7 = (EditText)findViewById(R.id.val_7);
        val_8 = (EditText)findViewById(R.id.val_8);
        val_9 = (EditText)findViewById(R.id.val_9);
        val_10 = (EditText)findViewById(R.id.val_10);

        attr_1 = (TextView)findViewById(R.id.attr_1);
        attr_2 = (TextView)findViewById(R.id.attr_2);
        attr_3 = (TextView)findViewById(R.id.attr_3);
        attr_4 = (TextView)findViewById(R.id.attr_4);
        attr_5 = (TextView)findViewById(R.id.attr_5);
        attr_6 = (TextView)findViewById(R.id.attr_6);
        attr_7 = (TextView)findViewById(R.id.attr_7);
        attr_8 = (TextView)findViewById(R.id.attr_8);
        attr_9 = (TextView)findViewById(R.id.attr_9);
        attr_10 = (TextView)findViewById(R.id.attr_10);
        cat_name = (TextView)findViewById(R.id.cat_name);

        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        id = message.toCharArray();
        AsyncHttpClient client = new AsyncHttpClient();
        params = new RequestParams();
        params.put("type","populate");
        String category_id = message.substring(0,5);
        String product_id = message.substring(5,10);
        params.put("category_id", category_id);
        params.put("product_id",product_id);

        client = new AsyncHttpClient();
        client.post(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    count = Integer.valueOf(response.get("count").toString());
                    category = response.get("category").toString();
                    try {
                        cat_name.setText(category);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    for (int cnt = 0;cnt < count;cnt++) {
                        populate_view(response.get("attribute_name_" + Integer.toString(cnt + 1)).toString(),response.get("attribute_val_" + Integer.toString(cnt + 1)).toString(),cnt+1);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

        });


    }

    private void populate_view(String name, String val, int i) {

        switch(i)
        {
            case 1:
                attr_1.setText(name);
                val_1.setText(val);
                break;
            case 2:
                attr_2.setText(name);
                val_2.setText(val);
                break;
            case 3:
                attr_3.setText(name);
                val_3.setText(val);
                break;
            case 4:
                attr_4.setText(name);
                val_4.setText(val);
                break;
            case 5:
                attr_5.setText(name);
                val_5.setText(val);
                break;
            case 6:
                attr_6.setText(name);
                val_6.setText(val);
                break;
            case 7:
                attr_7.setText(name);
                val_7.setText(val);
                break;
            case 8:
                attr_8.setText(name);
                val_8.setText(val);
                break;
            case 9:
                attr_9.setText(name);
                val_9.setText(val);
                break;
            case 10:
                attr_10.setText(name);
                val_10.setText(val);

        }
    }


    public void try_againclick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void saveonclick(View view) {
        AsyncHttpClient client = new AsyncHttpClient();
        params_2 = new RequestParams();
        params_2.put("type","update");
        params_2.put("table_name",category);
        params_2.put("count",Integer.toString(count));
        for (int cnt = 0;cnt < count;cnt++)
        {
            switch (cnt)
            {
                case 0:
                    params_2.put("attribute_name_"+Integer.toString(cnt+1),attr_1.getText().toString());
                    params_2.put("attribute_val_"+Integer.toString(cnt+1),val_1.getText().toString());
                    break;
                case 1:
                    params_2.put("attribute_name_"+Integer.toString(cnt+1),attr_2.getText().toString());
                    params_2.put("attribute_val_"+Integer.toString(cnt+1),val_2.getText().toString());
                    break;
                case 2:
                    params_2.put("attribute_name_"+Integer.toString(cnt+1),attr_3.getText().toString());
                    params_2.put("attribute_val_"+Integer.toString(cnt+1),val_3.getText().toString());
                    break;
                case 3:
                    params_2.put("attribute_name_"+Integer.toString(cnt+1),attr_4.getText().toString());
                    params_2.put("attribute_val_"+Integer.toString(cnt+1),val_4.getText().toString());
                    break;
                case 4:
                    params_2.put("attribute_name_"+Integer.toString(cnt+1),attr_5.getText().toString());
                    params_2.put("attribute_val_"+Integer.toString(cnt+1),val_5.getText().toString());
                    break;
                case 5:
                    params_2.put("attribute_name_"+Integer.toString(cnt+1),attr_6.getText().toString());
                    params_2.put("attribute_val_"+Integer.toString(cnt+1),val_6.getText().toString());
                    break;
                case 6:
                    params_2.put("attribute_name_"+Integer.toString(cnt+1),attr_7.getText().toString());
                    params_2.put("attribute_val_"+Integer.toString(cnt+1),val_7.getText().toString());
                    break;
                case 7:
                    params_2.put("attribute_name_"+Integer.toString(cnt+1),attr_8.getText().toString());
                    params_2.put("attribute_val_"+Integer.toString(cnt+1),val_8.getText().toString());
                    break;
                case 8:
                    params_2.put("attribute_name_"+Integer.toString(cnt+1),attr_9.getText().toString());
                    params_2.put("attribute_val_"+Integer.toString(cnt+1),val_9.getText().toString());
                    break;
                case 9:
                    params_2.put("attribute_name_"+Integer.toString(cnt+1),attr_10.getText().toString());
                    params_2.put("attribute_val_"+Integer.toString(cnt+1),val_10.getText().toString());
                    break;
            }
        }
        client = new AsyncHttpClient();
        client.post(url,params_2, new JsonHttpResponseHandler(){
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

    public void issueonClick(View view) {
        Intent intent = new Intent(this, namesActivity.class);
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}
