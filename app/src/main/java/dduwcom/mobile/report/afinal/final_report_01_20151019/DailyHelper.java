package dduwcom.mobile.report.afinal.final_report_01_20151019;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DailyHelper extends SQLiteOpenHelper {
    final static String TAG = "DailyHelper";
    final static String DB_NAME = "daily.db";
    public final static String TABLE_NAME = "daily_table";
    public final static String COL_ID = "_id";
    public final static String COL_MONTH = "month";
    public final static String COL_DAY = "day";
    public final static String COL_PLACE = "place";
    public final static String COL_TITLE = "title";
    public final static String COL_CONTENT = "content";
    public final static String COL_HAPPY = "happy";

    public DailyHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " + COL_MONTH + " NUMBER, " + COL_DAY + " NUMBER, "
                + COL_PLACE + " TEXT, " + COL_TITLE + " TEXT, " + COL_CONTENT + " TEXT, " + COL_HAPPY + " NUMBER)";
        db.execSQL(sql);
        db.execSQL("insert into " + TABLE_NAME + " values (null, 6, 20, '낙산공원', '야경보러 간 날', '야경을 보러 갔다왔다. 서울의 야경은 참 예쁜 것 같다.', 99);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 6, 21, '강남', '인형', '인형을 선물로 받았다. 기분이 너무너무 좋다!', 100);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 6, 22, '학교', '하늘이 예뻤던 날', '우연히 하늘을 봤는데 하늘이 참 예뻤다. 기분이 좋아졌다.', 89);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 6, 23, '롯데시네마', '오션스8', '혼자 영화를 보고 왔다. 영화 진짜 재밌었다. 앤 헤서웨이 예쁘다.', 79);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 6, 24, '집', '일요일 기념 치킨', '알바가 끝나고 치킨을 시켜 먹었다.', 88);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
