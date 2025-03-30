package com.byamn.store;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class HomeActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String version = "";
	private String title = "";
	private String message = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout body;
	private LinearLayout linear1;
	private LinearLayout linear;
	private LinearLayout bottom;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private ImageView imageview1;
	private TextView textview6;
	private ImageView imageview2;
	private RecyclerView recyclerview1;
	private ProgressBar progressbar1;
	private LinearLayout navigation;
	private LinearLayout settings;
	private LinearLayout vitrin;
	private LinearLayout arrino;
	private LinearLayout messanger;
	private LinearLayout home;
	private ImageView imageview68;
	private TextView textview1;
	private ImageView imageview70;
	private TextView textview2;
	private ImageView imageview71;
	private TextView textview3;
	private ImageView imageview72;
	private TextView textview4;
	private ImageView imageview73;
	private TextView textview5;
	
	private Intent intent = new Intent();
	private TimerTask t;
	private DatabaseReference notify = _firebase.getReference("notify");
	private ChildEventListener _notify_child_listener;
	private SharedPreferences tp;
	private AlertDialog.Builder d;
	private Intent i = new Intent();
	private DatabaseReference pdb = _firebase.getReference("projects");
	private ChildEventListener _pdb_child_listener;
	private SharedPreferences file;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		linear1 = findViewById(R.id.linear1);
		linear = findViewById(R.id.linear);
		bottom = findViewById(R.id.bottom);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		imageview1 = findViewById(R.id.imageview1);
		textview6 = findViewById(R.id.textview6);
		imageview2 = findViewById(R.id.imageview2);
		recyclerview1 = findViewById(R.id.recyclerview1);
		progressbar1 = findViewById(R.id.progressbar1);
		navigation = findViewById(R.id.navigation);
		settings = findViewById(R.id.settings);
		vitrin = findViewById(R.id.vitrin);
		arrino = findViewById(R.id.arrino);
		messanger = findViewById(R.id.messanger);
		home = findViewById(R.id.home);
		imageview68 = findViewById(R.id.imageview68);
		textview1 = findViewById(R.id.textview1);
		imageview70 = findViewById(R.id.imageview70);
		textview2 = findViewById(R.id.textview2);
		imageview71 = findViewById(R.id.imageview71);
		textview3 = findViewById(R.id.textview3);
		imageview72 = findViewById(R.id.imageview72);
		textview4 = findViewById(R.id.textview4);
		imageview73 = findViewById(R.id.imageview73);
		textview5 = findViewById(R.id.textview5);
		tp = getSharedPreferences("tp", Activity.MODE_PRIVATE);
		d = new AlertDialog.Builder(this);
		file = getSharedPreferences("file", Activity.MODE_PRIVATE);
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), SearchActivity.class);
				startActivity(i);
			}
		});
		
		vitrin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), NotiCActivity.class);
				startActivity(i);
			}
		});
		
		arrino.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (file.getString("onetime", "").equals("true")) {
					i.setClass(getApplicationContext(), ChatActivity.class);
					startActivity(i);
				} else {
					file.edit().putString("onetime", "true").commit();
					d.setTitle("Warning ⚠️");
					d.setMessage("If You Have Unlimited Data Then Watch Shorts Video...");
					d.setPositiveButton("Sure!", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							i.setClass(getApplicationContext(), ShortsActivity.class);
							startActivity(i);
						}
					});
					d.create().show();
				}
			}
		});
		
		messanger.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), CourseActivity.class);
				startActivity(intent);
			}
		});
		
		home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), AboutActivity.class);
				startActivity(i);
			}
		});
		
		_notify_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				version = _childValue.get("version").toString();
				title = _childValue.get("title").toString();
				message = _childValue.get("message").toString();
				if (tp.getString(_childKey, "").contains("true")) {
					
				} else {
					d.setTitle(title.concat("\n".concat("Version:-".concat(version))));
					d.setMessage(message);
					d.setCancelable(false);
					d.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							tp.edit().putString(_childKey, "true").commit();
							i.setAction(Intent.ACTION_VIEW);
							i.setData(Uri.parse(_childValue.get("link").toString()));
							startActivity(i);
						}
					});
					d.create().show();
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		notify.addChildEventListener(_notify_child_listener);
		
		_pdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		pdb.addChildEventListener(_pdb_child_listener);
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		_Custom_Loading(true);
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_Custom_Loading(false);
					}
				});
			}
		};
		_timer.schedule(t, (int)(2999));
		UnityAds.initialize(this, "5720336", false, false);
		UnityAds.initialize(this, "5720336", false, false);
		_roundedCorners(navigation, 26, 26, 26, 26, "#FFFFFF", 2, "#0E0E0E", 3, "");
		pdb.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				listmap = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						listmap.add(_map);
					}
				} catch (Exception _e) {
					_e.printStackTrace();
				}
				recyclerview1.setAdapter(new Recyclerview1Adapter(listmap));
				_reverse(listmap);
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		pdb.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				listmap = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						listmap.add(_map);
					}
				} catch (Exception _e) {
					_e.printStackTrace();
				}
				if (listmap.size() == 0) {
					progressbar1.setVisibility(View.VISIBLE);
					recyclerview1.setVisibility(View.GONE);
				} else {
					progressbar1.setVisibility(View.GONE);
					recyclerview1.setVisibility(View.VISIBLE);
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		GridLayoutManager gridlayoutManager= new GridLayoutManager(getApplicationContext(), 3, GridLayoutManager.VERTICAL,true); gridlayoutManager.setReverseLayout(false); 
		recyclerview1.setLayoutManager(gridlayoutManager);
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
	}
	
	
	public void _Custom_Loading(final boolean _ifShow) {
		if (_ifShow) {
			if (coreprog == null){
				coreprog = new ProgressDialog(this);
				coreprog.setCancelable(false);
				coreprog.setCanceledOnTouchOutside(false);
				
				coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
				
			}
			coreprog.setMessage(null);
			coreprog.show();
			View _view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
			LinearLayout linear_base = (LinearLayout) _view.findViewById(R.id.linear_base);
			
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.TRANSPARENT);
			gd.setCornerRadius(25);
			linear_base.setBackground(gd);
			coreprog.setContentView(_view);
		} else {
			if (coreprog != null){
				coreprog.dismiss();
			}
		}
	}
	private ProgressDialog coreprog;
	{
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
	
	
	public void _ICC(final ImageView _img, final String _c1, final String _c2) {
		_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
	}
	
	
	public void _reverse(final ArrayList<HashMap<String, Object>> _mapname) {
		Collections.reverse(_mapname);
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.projects_grid, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final androidx.cardview.widget.CardView card = _view.findViewById(R.id.card);
			final LinearLayout body = _view.findViewById(R.id.body);
			final androidx.cardview.widget.CardView project_icon_card = _view.findViewById(R.id.project_icon_card);
			final TextView project_name = _view.findViewById(R.id.project_name);
			final TextView version = _view.findViewById(R.id.version);
			final ImageView project_icon = _view.findViewById(R.id.project_icon);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			card.setCardBackgroundColor(0xFFECEFF1);
			Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("project_icon").toString())).into(project_icon);
			project_name.setText(_data.get((int)_position).get("project_name").toString());
			version.setText(_data.get((int)_position).get("project_size").toString().concat(" • ".concat(_data.get((int)_position).get("project_version").toString())));
			body.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intent.setClass(getApplicationContext(), DownloaderActivity.class);
					startActivity(intent);
					intent.putExtra("key", _data.get((int)_position).get("key").toString());
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
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