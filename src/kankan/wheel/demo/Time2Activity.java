package kankan.wheel.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import kankan.wheel.demo.R;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;
import kankan.wheel.widget.adapters.DayArrayAdapter;
import kankan.wheel.widget.adapters.NumericWheelAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Time2Activity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.time2_layout);

		final WheelView hours = (WheelView) findViewById(R.id.hour);
		NumericWheelAdapter hourAdapter = new NumericWheelAdapter(this, 0, 23,
				"%2d 時");
		hourAdapter.setItemResource(R.layout.wheel_text_item);
		hourAdapter.setItemTextResource(R.id.text);
		hours.setViewAdapter(hourAdapter);
		hours.setCyclic(true);

		final WheelView mins = (WheelView) findViewById(R.id.mins);
		NumericWheelAdapter minAdapter = new NumericWheelAdapter(this, 0, 59,
				"%02d 分");
		minAdapter.setItemResource(R.layout.wheel_text_item);
		minAdapter.setItemTextResource(R.id.text);
		mins.setViewAdapter(minAdapter);
		mins.setCyclic(true);

		// set current time
		Calendar calendar = Calendar.getInstance(Locale.JAPAN);
		hours.setCurrentItem(calendar.get(Calendar.HOUR));
		mins.setCurrentItem(calendar.get(Calendar.MINUTE));

		final WheelView day = (WheelView) findViewById(R.id.day);
		day.setViewAdapter(new DayArrayAdapter(this, calendar));
		day.setCyclic(true);
	}
}
