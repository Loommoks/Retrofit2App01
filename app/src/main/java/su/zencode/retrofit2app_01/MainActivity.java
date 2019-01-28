package su.zencode.retrofit2app_01;

import android.support.annotation.CheckResult;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mTextViewMain;
    Button mButtonMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewMain = findViewById(R.id.text_view_main);

        mButtonMain = findViewById(R.id.button_main);
        mButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = reGenerateString(R.string.new_string);
                mTextViewMain.setText(text);
            }
        });

    }

    @CheckResult
    public String reGenerateString(@StringRes int resId) {
        String tmpString = getString(resId);
        return tmpString.replace("o","0");
    }
}
