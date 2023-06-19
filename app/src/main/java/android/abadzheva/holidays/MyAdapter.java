package android.abadzheva.holidays;

import android.abadzheva.holidays.model.ListHolidaysInfo;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyViewHolder> {

    final private ListItemClickListener mOnClickListener;
    public interface  ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }
    
    private ListHolidaysInfo listHolidaysInfo;
    private int mNumberItems;
    private static final String TAG = "MyAdapter";

    public MyAdapter(ListHolidaysInfo listHolidaysInfo, int length, ListItemClickListener listener){
        this.mOnClickListener = listener;
        this.mNumberItems = length;
        this.listHolidaysInfo = listHolidaysInfo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.holiday_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        Log.d(TAG, "onCreateViewHolder() called with: parent = [" + parent + "], viewType = [" + viewType + "]");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView holidayDate;
        private TextView holidayName;

        public MyViewHolder (View itemView){
            super(itemView);

            holidayDate = itemView.findViewById(R.id.holiday_date_text_view);
            holidayName = itemView.findViewById(R.id.holiday_name_text_view);
            holidayName.setOnClickListener(this);
        }

        void bind (int position){
            String holiday_date = listHolidaysInfo.listHolidaysInfo[position].getHoliday_date();
            String holiday_name = listHolidaysInfo.listHolidaysInfo[position].getHoliday_name();
            holidayDate.setText(holiday_date);
            holidayName.setText(holiday_name);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mOnClickListener.onListItemClick(position);
        }
    }
}
