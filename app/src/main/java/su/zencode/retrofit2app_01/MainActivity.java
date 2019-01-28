package su.zencode.retrofit2app_01;

import android.os.AsyncTask;
import android.support.annotation.CheckResult;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.sql.DataTruncation;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView mTextViewMain;
    Button mButtonMain;
    String mText;

    private static final String TAG = ".MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewMain = findViewById(R.id.text_view_main);

        mButtonMain = findViewById(R.id.button_main);
        mButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = callGitHub();
            }
        });





    }

    public String callGitHub() {

        new AsyncTask<Void,Void,String>() {

            @Override
            protected String doInBackground(Void... voids) {

                String toReturn = null;

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://api.github.com/users/loommoks")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String tmpString = response.body().string();
                    Log.d(TAG, "doInBackground() called with: voids = [" + tmpString + "]");
                    toReturn = tmpString;
                } catch (IOException e) {
                    Log.d(TAG, "doInBackground() called with: voids = [" + voids + "]");
                    e.printStackTrace();
                }

                return toReturn;
            }

            @Override
            protected void onPostExecute(String input) {
                mTextViewMain.setText(input);
                mText = input;
            }

        }.execute();

        return mText;
    }

    @CheckResult
    public String reGenerateString(@StringRes int resId) {
        String tmpString = getString(resId);
        return tmpString.replace("o","0");
    }
}
