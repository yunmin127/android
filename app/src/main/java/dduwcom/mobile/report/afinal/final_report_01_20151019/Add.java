package dduwcom.mobile.report.afinal.final_report_01_20151019;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    private static int PICK_IMAGE_REQUEST = 1;

    EditText etMonth;
    EditText etDay;
    EditText etPlace;
    EditText etTitle;
    EditText etContent;
    EditText etHappy;
    ImageView imageView;
    DailyHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        etMonth = findViewById(R.id.etMonth);
        etDay = findViewById(R.id.etDay);
        etPlace = findViewById(R.id.etPlace);
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        etHappy = findViewById(R.id.etHappy);
        imageView = findViewById(R.id.imageView2);

        helper = new DailyHelper(this);

        Button btnim = (Button)findViewById(R.id.btn_im);
        btnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //ACTION_PIC과 차이점?
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);



            }
        });

        Button btnAdd = (Button)findViewById(R.id.btAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues value = new ContentValues();

                String month = etMonth.getText().toString();
                String day = etDay.getText().toString();
                String place = etPlace.getText().toString();
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                String happy = etHappy.getText().toString();

                if (month.equals("") || day.equals("") || place.equals("") || title.equals("") || content.equals("") || happy.equals("")) {
                    Toast.makeText(Add.this, "모든 항목을 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    value.put(helper.COL_MONTH, month);
                    value.put(helper.COL_DAY, day);
                    value.put(helper.COL_PLACE, place);
                    value.put(helper.COL_TITLE, title);
                    value.put(helper.COL_CONTENT, content);
                    value.put(helper.COL_HAPPY, happy);

                    long count = db.insert(helper.TABLE_NAME, null, value);

                    if (count > 0) {
                        setResult(RESULT_OK, null);
                        helper.close();
                        finish();
                    } else {
                        Toast.makeText(Add.this, "추가 실패", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        helper.close();
                    }
                }
            }
        });

        Button btnCan = (Button)findViewById(R.id.btCancel);
        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //이미지를 하나 골랐을때
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                //data에서 절대경로로 이미지를 가져옴
                Uri uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                //이미지가 한계이상(?) 크면 불러 오지 못하므로 사이즈를 줄여 준다.
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);

                imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(scaled);

            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


}