package dduwcom.mobile.report.afinal.final_report_01_20151019;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    EditText etMonth;
    EditText etDay;
    EditText etTitle;
    EditText etPlace;
    EditText etContent;
    EditText etHappy;
    ImageView img;

    DailyHelper helper;
    Daily dailyDto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        helper = new DailyHelper(this);
        etMonth = findViewById(R.id.etMonth);
        etDay = findViewById(R.id.etDay);
        etPlace = findViewById(R.id.etPlace);
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        etHappy = findViewById(R.id.etHappy);
        img = findViewById(R.id.img);

        Intent intent = getIntent();
        dailyDto = (Daily)intent.getSerializableExtra("dayDto");

        etMonth.setText(String.valueOf(dailyDto.getMonth()));
        etDay.setText(String.valueOf(dailyDto.getDay()));
        etPlace.setText(dailyDto.getPlace());
        etTitle.setText(dailyDto.getTitle());
        etContent.setText(dailyDto.getContent());
        etContent.setRawInputType(InputType.TYPE_CLASS_TEXT);
        etHappy.setText(String.valueOf(dailyDto.getHappy()));

        int id = (int) dailyDto.get_id();
        switch (id) {
            case 1 :
                img.setImageResource(R.drawable.a);
                break;
            case 2 :
                img.setImageResource(R.drawable.d);
                break;
            case 3 :
                img.setImageResource(R.drawable.f);
                break;
            case 4 :
                img.setImageResource(R.drawable.g);
                break;
            case 5 :
                img.setImageResource(R.drawable.s);
                break;
        }
        Button btn = (Button)findViewById(R.id.btUpdate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int month = Integer.parseInt(etMonth.getText().toString());
                int day = Integer.parseInt(etDay.getText().toString());
                String place = etPlace.getText().toString();
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                int happy = Integer.parseInt(etHappy.getText().toString());

                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues row = new ContentValues();

                row.put(helper.COL_MONTH, month);
                row.put(helper.COL_DAY, day);
                row.put(helper.COL_PLACE, place);
                row.put(helper.COL_TITLE, title);
                row.put(helper.COL_CONTENT, content);
                row.put(helper.COL_HAPPY, happy);

                String whereClause = helper.COL_ID + "=?";
                String[] whereArgs = new String[]{String.valueOf(dailyDto.get_id())};

                long count = db.update(helper.TABLE_NAME, row, whereClause, whereArgs);

                if (count > 0) {
                    setResult(RESULT_OK, null);
                    helper.close();
                    finish();
                } else {
                    Toast.makeText(Update.this, "수정 실패", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    helper.close();
            }
            finish();
            }
        });

        Button btnC = (Button)findViewById(R.id.btCancel);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
