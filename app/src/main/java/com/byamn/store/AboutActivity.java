package com.byamn.store;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.FirebaseApp;
import com.unity3d.ads.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class AboutActivity extends AppCompatActivity {
	
	private String share = "";
	
	private ScrollView vscroll1;
	private LinearLayout linear2;
	private LinearLayout d1;
	private LinearLayout linear4;
	private TextView textview27;
	private LinearLayout linear6;
	private LinearLayout linear5;
	private LinearLayout lik;
	private LinearLayout linear14;
	private LinearLayout linear18;
	private LinearLayout linear26;
	private LinearLayout linear35;
	private LinearLayout linear69;
	private LinearLayout d2;
	private LinearLayout linear67;
	private TextView textview2;
	private TextView textview3;
	private ImageView imageview1;
	private LinearLayout linear9;
	private TextView textview4;
	private TextView textview5;
	private ImageView imageview4;
	private LinearLayout linear15;
	private TextView textview10;
	private TextView textview11;
	private ImageView imageview6;
	private LinearLayout linear19;
	private TextView textview14;
	private ImageView imageview10;
	private LinearLayout linear27;
	private TextView textview18;
	private ImageView imageview14;
	private LinearLayout linear36;
	private TextView textview23;
	private TextView textview24;
	private ImageView imageview17;
	private LinearLayout linear70;
	private TextView textview28;
	private TextView textview29;
	private TextView textview19;
	private LinearLayout linear37;
	private ImageView imageview15;
	private LinearLayout linear38;
	private TextView textview25;
	private TextView textview26;
	
	private Intent opinikol = new Intent();
	private Intent i = new Intent();
	private Intent yt = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.about);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		linear2 = findViewById(R.id.linear2);
		d1 = findViewById(R.id.d1);
		linear4 = findViewById(R.id.linear4);
		textview27 = findViewById(R.id.textview27);
		linear6 = findViewById(R.id.linear6);
		linear5 = findViewById(R.id.linear5);
		lik = findViewById(R.id.lik);
		linear14 = findViewById(R.id.linear14);
		linear18 = findViewById(R.id.linear18);
		linear26 = findViewById(R.id.linear26);
		linear35 = findViewById(R.id.linear35);
		linear69 = findViewById(R.id.linear69);
		d2 = findViewById(R.id.d2);
		linear67 = findViewById(R.id.linear67);
		textview2 = findViewById(R.id.textview2);
		textview3 = findViewById(R.id.textview3);
		imageview1 = findViewById(R.id.imageview1);
		linear9 = findViewById(R.id.linear9);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		imageview4 = findViewById(R.id.imageview4);
		linear15 = findViewById(R.id.linear15);
		textview10 = findViewById(R.id.textview10);
		textview11 = findViewById(R.id.textview11);
		imageview6 = findViewById(R.id.imageview6);
		linear19 = findViewById(R.id.linear19);
		textview14 = findViewById(R.id.textview14);
		imageview10 = findViewById(R.id.imageview10);
		linear27 = findViewById(R.id.linear27);
		textview18 = findViewById(R.id.textview18);
		imageview14 = findViewById(R.id.imageview14);
		linear36 = findViewById(R.id.linear36);
		textview23 = findViewById(R.id.textview23);
		textview24 = findViewById(R.id.textview24);
		imageview17 = findViewById(R.id.imageview17);
		linear70 = findViewById(R.id.linear70);
		textview28 = findViewById(R.id.textview28);
		textview29 = findViewById(R.id.textview29);
		textview19 = findViewById(R.id.textview19);
		linear37 = findViewById(R.id.linear37);
		imageview15 = findViewById(R.id.imageview15);
		linear38 = findViewById(R.id.linear38);
		textview25 = findViewById(R.id.textview25);
		textview26 = findViewById(R.id.textview26);
		
		lik.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "version is latest");
				_clickAnim(lik);
			}
		});
		
		linear14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://sites.google.com/view/byamn-store-app/home"));
				startActivity(i);
				_clickAnim(linear14);
			}
		});
		
		linear18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnim(linear18);
				share = "BYAMN APP STORE :- \nGet Free Premium App & No Ads \nhttps://sites.google.com/view/byamn-store-app";
				Intent i = new Intent(android.content.Intent.ACTION_SEND);
				i.setType("text/plain");
				i.putExtra(android.content.Intent.EXTRA_TEXT,share);
				startActivity(Intent.createChooser(i,"Shareusing"));
			}
		});
		
		linear26.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnim(linear26);
				yt.setAction(Intent.ACTION_VIEW);
				yt.setData(Uri.parse("https://youtube.com/@byamn_app"));
				startActivity(yt);
			}
		});
		
		linear35.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				opinikol.setAction(Intent.ACTION_VIEW);
				opinikol.setData(Uri.parse("https://t.me/dyhard_app"));
				startActivity(opinikol);
			}
		});
		
		linear69.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				opinikol.setAction(Intent.ACTION_VIEW);
				opinikol.setData(Uri.parse("https://www.instagram.com/byamn_7?igsh=Y204bG9mNjlxM25k"));
				startActivity(opinikol);
			}
		});
		
		linear37.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://forms.gle/5yXXLHv1QjrGi1pD7"));
				startActivity(i);
				_clickAnim(linear37);
			}
		});
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		_removeScollBar(vscroll1);
		_roundedCorners(linear5, 26, 26, 26, 26, "#FFFFFF", 0, "#FFFFFF", 3, "");
		_roundedCorners(lik, 26, 26, 26, 26, "#FFFFFF", 0, "#FFFFFF", 3, "");
		_roundedCorners(linear14, 26, 26, 26, 26, "#FFFFFF", 0, "#FFFFFF", 5, "");
		_roundedCorners(linear18, 26, 26, 26, 26, "#FFFFFF", 0, "#FFFFFF", 5, "");
		_roundedCorners(linear26, 26, 26, 26, 26, "#FFFFFF", 0, "#FFFFFF", 3, "");
		_roundedCorners(linear6, 26, 26, 26, 26, "#FFFFFF", 0, "#FFFFFF", 3, "");
		_roundedCorners(linear37, 26, 26, 26, 26, "#FFFFFF", 0, "#FFFFFF", 3, "");
		_roundedCorners(linear69, 26, 26, 26, 26, "#FFFFFF", 0, "#FFFFFF", 3, "");
		_roundedCorners(linear35, 26, 26, 26, 26, "#FFFFFF", 0, "#FFFFFF", 3, "");
	}
	
	public void _removeScollBar(final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
	}
	
	
	public void _roundedCorners(final View _view, final double _one, final double _two, final double _three, final double _four, final String _color, final double _stroke, final String _stColor, final double _num, final String _NOTES) {
		Double left_top = _one;
		Double right_top = _two;
		Double right_bottom = _three;
		Double left_bottom = _four;
		android.graphics.drawable.GradientDrawable s = new android.graphics.drawable.GradientDrawable();
		s.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
		s.setCornerRadii(new float[] {left_top.floatValue(),left_top.floatValue(), right_top.floatValue(),right_top.floatValue(), left_bottom.floatValue(),left_bottom.floatValue(), right_bottom.floatValue(),right_bottom.floatValue()});
		s.setColor(Color.parseColor(_color));
		s.setStroke((int)_stroke, Color.parseColor(_stColor));
		_view.setBackground(s);
		_view.setElevation((int)_num);
	}
	
	
	public void _clickAnim(final View _view) {
		_clickAnimation(_view);
	}
	
	
	public void _clickAnimation(final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(300);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}