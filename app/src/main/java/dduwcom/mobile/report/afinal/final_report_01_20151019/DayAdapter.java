package dduwcom.mobile.report.afinal.final_report_01_20151019;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DayAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Daily> list;
    private LayoutInflater layoutInflater;

    public DayAdapter (Context context, int layout, ArrayList<Daily> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        if(convertView == null) {
            convertView = layoutInflater.inflate(layout, parent, false);
        }
        ImageView img = (ImageView)convertView.findViewById(R.id.imageView);
  //      TextView id = (TextView)convertView.findViewById(R.id.tvNo);
        TextView month = (TextView)convertView.findViewById(R.id.tvMonth);
        TextView day = (TextView)convertView.findViewById(R.id.tvDay);
  //      TextView place = (TextView)convertView.findViewById(R.id.tvPlace);
        TextView title = (TextView)convertView.findViewById(R.id.tvTitle);
   //     TextView content = (TextView)convertView.findViewById(R.id.tvContent);
        TextView happy = (TextView)convertView.findViewById(R.id.tvHappy);

       // img.setImageBitmap();
     //   id.setText(Long.valueOf(list.get(pos).get_id()).toString());
        month.setText(Integer.valueOf(list.get(pos).getMonth()).toString() + "월");
        day.setText(Integer.valueOf(list.get(pos).getDay()).toString() + "일");
   //     place.setText(list.get(pos).getPlace());
        title.setText(list.get(pos).getTitle());
  //      content.setText(list.get(pos).getContent());
        happy.setText(Integer.valueOf(list.get(pos).getHappy()).toString() + "%");

        int id = (int) list.get(pos).get_id();
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
        return convertView;
    }
}
