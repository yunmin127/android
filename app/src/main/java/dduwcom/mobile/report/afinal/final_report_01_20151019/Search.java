package dduwcom.mobile.report.afinal.final_report_01_20151019;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Search extends AppCompatActivity {
    DailyHelper helper;

    EditText key;
    Button button;
    Button button2;
    Button buttonRe;
    Button buttonFin;
    TextView textView;

    String s;
    int m;
    int d;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        helper = new DailyHelper(this);

        key = (EditText)findViewById(R.id.etKey);
        textView = (TextView)findViewById(R.id.tvString);

        button = (Button)findViewById(R.id.btnStitle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = key.getText().toString();

                SQLiteDatabase db = helper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + helper.TABLE_NAME, null);

                while(cursor.moveToNext()) {
                    if(s.equals(cursor.getString(cursor.getColumnIndex(helper.COL_TITLE)).toString())) {
                        m = cursor.getInt(cursor.getColumnIndex(helper.COL_MONTH));
                        d = cursor.getInt(cursor.getColumnIndex(helper.COL_DAY));
                        break;
                    }
                }

                if(m == 0 || d == 0) {
                    textView.setText("검색 결과가 없습니다.");
                } else {
                    textView.setText(m + "월 " + d + "일의 일기입니다.");
                }
            }
        });

        button2 = (Button)findViewById(R.id.btnSplace);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = key.getText().toString();

                SQLiteDatabase db = helper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + helper.TABLE_NAME, null);

                while(cursor.moveToNext()) {
                    if(s.equals(cursor.getString(cursor.getColumnIndex(helper.COL_PLACE)).toString())) {
                        m = cursor.getInt(cursor.getColumnIndex(helper.COL_MONTH));
                        d = cursor.getInt(cursor.getColumnIndex(helper.COL_DAY));
                        break;
                    }
                }

                if(m == 0 || d == 0) {
                    textView.setText("검색 결과가 없습니다.");
                } else {
                    textView.setText(m + "월 " + d + "일의 일기입니다.");
                }
            }
        });

        buttonRe = (Button)findViewById(R.id.btnRe);
        buttonRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key.setHint("검색어를 다시 입력하세요");
                textView.setText("");
            }
        });

        buttonFin = (Button)findViewById(R.id.btnSfin);
        buttonFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
