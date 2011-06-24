package kankan.wheel.widget.adapters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import kankan.wheel.demo.R;
import kankan.wheel.demo.R.id;
import kankan.wheel.demo.R.layout;

/**
 * Day adapter
 * 
 */
public class DayArrayAdapter extends AbstractWheelTextAdapter {
	// Count of days to be shown
	private final int daysCount = 364;

	// Calendar
	Calendar calendar;

	/**
	 * Constructor
	 */
	public DayArrayAdapter(Context context, Calendar calendar) {
		super(context, R.layout.time2_day, NO_RESOURCE);
		this.calendar = calendar;

		setItemTextResource(R.id.time2_monthday);
	}

	@Override
	public View getItem(int index, View cachedView, ViewGroup parent) {
		int day = -daysCount / 2 + index;
		Calendar newCalendar = (Calendar) calendar.clone();
		newCalendar.roll(Calendar.DAY_OF_YEAR, day);

		View view = super.getItem(index, cachedView, parent);

		TextView monthday = (TextView) view.findViewById(R.id.time2_monthday);
		DateFormat format = new SimpleDateFormat("M 月 dd 日");
		monthday.setText(format.format(newCalendar.getTime()));
		monthday.setTextColor(0xFF111111);

		return view;
	}

	@Override
	public int getItemsCount() {
		return daysCount + 1;
	}

	@Override
	protected CharSequence getItemText(int index) {
		return "";
	}
}
