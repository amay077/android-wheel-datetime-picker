package kankan.wheel.widget;

import java.util.Calendar;
import java.util.Locale;

import kankan.wheel.demo.R;
import kankan.wheel.widget.adapters.DayArrayAdapter;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class DateTimeWheel extends LinearLayout {
	private Calendar calendar = Calendar.getInstance(Locale.JAPAN);
	
	private OnTimeChangedListener timeChangedListener = null;
	
	public DateTimeWheel(Context context) {
		this(context, null);
	}

	public DateTimeWheel(Context context, AttributeSet attrs) {
		super(context, attrs);

		setOrientation(VERTICAL);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.date_time_wheel, this, true);
		
		final WheelView hours = (WheelView) findViewById(R.id.hour);
		NumericWheelAdapter hourAdapter = new NumericWheelAdapter(context, 0, 23, "%2d 時");
		hourAdapter.setItemResource(R.layout.wheel_text_item);
		hourAdapter.setItemTextResource(R.id.text);
		hours.setViewAdapter(hourAdapter);
		hours.setCyclic(true);

		final WheelView mins = (WheelView) findViewById(R.id.mins);
		NumericWheelAdapter minAdapter = new NumericWheelAdapter(context, 0, 59, "%02d 分");
		minAdapter.setItemResource(R.layout.wheel_text_item);
		minAdapter.setItemTextResource(R.id.text);
		mins.setViewAdapter(minAdapter);
		mins.setCyclic(true);
		
		mins.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				calendar.add(Calendar.MINUTE, newValue - oldValue);
				
				fireTimeChanged(calendar.getTimeInMillis());
			}
		});

		// set current time
		hours.setCurrentItem(calendar.get(Calendar.HOUR));
		mins.setCurrentItem(calendar.get(Calendar.MINUTE));

		final WheelView day = (WheelView) findViewById(R.id.day);
		day.setViewAdapter(new DayArrayAdapter(context, calendar));
		day.setCyclic(true);
	}

	private void fireTimeChanged(long timeInMillis) {
		if (timeChangedListener != null) {
			timeChangedListener.onTimeChanged(timeInMillis);
		}
	}
	
	public void setOnTimeChangedListener(OnTimeChangedListener timeChangedListener) {
		this.timeChangedListener = timeChangedListener;
	}

	public interface OnTimeChangedListener {
		void onTimeChanged(long time);
	}

}
