package eskool.com.eskoolapp.Attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Objects;

import eskool.com.eskoolapp.R;

public class View_attendance extends AppCompatActivity {
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        calendar=(CalendarView) findViewById(R.id.calender);
        long selectedDate = calendar.getDate();
        String s = Objects.toString(selectedDate, null);
        Log.d("wqwq",s);
    }
}
