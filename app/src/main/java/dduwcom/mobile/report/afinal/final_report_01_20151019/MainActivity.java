// 과제명: 다이어리 앱
// 분반: 01분반
// 학번: 20151019
// 성명: 오민경
// 제출일: 2018년 6월 28일


package dduwcom.mobile.report.afinal.final_report_01_20151019;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";
    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;

    private ArrayList<Daily> list = null;
    private DayAdapter adapter;
    private ListView listView;
    DailyHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView)findViewById(R.id.listView);
        list = new ArrayList<Daily>();
        helper = new DailyHelper(this);
        adapter = new DayAdapter(this, R.layout.cust, list);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("삭제");
                builder.setMessage(list.get(pos).getMonth() + "월 " + list.get(pos).getDay() + "일의 일기를 삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = helper.getWritableDatabase();
                        String whereClause = helper.COL_ID + "=?";
                        String[] whereArgs = new String[] {String.valueOf(list.get(pos).get_id())};
                        db.delete(helper.TABLE_NAME, whereClause, whereArgs);
                        db.close();
                        readAllDaily();
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();

                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Update.class);
                intent.putExtra("dayDto", list.get(position));
                startActivityForResult(intent, UPDATE_CODE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        readAllDaily();
        adapter.notifyDataSetChanged();
    }


    private void readAllDaily() {
        list.clear();

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + helper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            int month = cursor.getInt(cursor.getColumnIndex(helper.COL_MONTH));
            int day = cursor.getInt(cursor.getColumnIndex(helper.COL_DAY));
            String place = cursor.getString(cursor.getColumnIndex(helper.COL_PLACE));
            String title = cursor.getString(cursor.getColumnIndex(helper.COL_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(helper.COL_CONTENT));
            int happy = cursor.getInt(cursor.getColumnIndex(helper.COL_HAPPY));
            list.add(new Daily(id, month, day, place, title, content, happy));
        }
        cursor.close();
        helper.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UPDATE_CODE) {    // UpdateActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "일기 수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "일기 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == REQ_CODE) {  // AddActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "일기 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "일기 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onMenuItemClick(MenuItem item) {
        Intent intent;

        switch(item.getItemId()) {
            case R.id.menAdd:
                intent = new Intent(this, Add.class);
                startActivityForResult(intent, REQ_CODE);
                break;
            case R.id.menInfo:
                intent = new Intent(this, Info.class);
                startActivity(intent);
                break;

            case R.id.menSearch :
                intent = new Intent(this, Search.class);
                startActivity(intent);
                break;

            case R.id.menFin:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("앱 종료");
                builder.setMessage("앱을 종료하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        }
                );
                builder.setNegativeButton("취소", null);
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}