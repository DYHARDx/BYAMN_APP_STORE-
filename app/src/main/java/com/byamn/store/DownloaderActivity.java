package com.byamn.store;

import android.Manifest;
import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.unity3d.ads.*;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class DownloaderActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private double RatingStar = 0;
	private String ProjectLink = "";
	private String PcdbStr = "";
	private HashMap<String, Object> UserName = new HashMap<>();
	private HashMap<String, Object> UserAvatar = new HashMap<>();
	private HashMap<String, Object> UserVerify = new HashMap<>();
	private double CommentCount = 0;
	private double Rate1 = 0;
	private double Rate2 = 0;
	private double Rate3 = 0;
	private double Rate4 = 0;
	private double Rate5 = 0;
	
	private ArrayList<HashMap<String, Object>> CommentsList = new ArrayList<>();
	
	private LinearLayout body;
	private ScrollView scroll;
	private LinearLayout scroll_in_body;
	private LinearLayout top_bar;
	private LinearLayout project_info;
	private LinearLayout download;
	private HorizontalScrollView categories_scroll;
	private LinearLayout my_comment;
	private ImageView back;
	private TextView top_title;
	private FrameLayout icon_and_progress;
	private LinearLayout project_info_inner;
	private ProgressBar progress;
	private CardView icon_card;
	private ImageView icon;
	private TextView project_name;
	private TextView publisher;
	private ImageView download_icon;
	private TextView download_title;
	private LinearLayout categories_scroll_body;
	private TextView project_category;
	private TextView category_project_recommended;
	private TextView category_project_editors_choice;
	private LinearLayout new_comment;
	private LinearLayout linear1;
	private LinearLayout top_layout;
	private LinearLayout description;
	private LinearLayout spc1;
	private LinearLayout spc2;
	private LinearLayout linear2;
	private TextView title;
	private ImageView imageview1;
	private LinearLayout whats_new;
	private TextView description_title;
	private TextView description_text;
	private TextView whats_new_title;
	private TextView whats_new_text;
	private TextView textview1;
	private LinearLayout version_layout;
	private LinearLayout size_layout;
	private LinearLayout release_date_layout;
	private LinearLayout compatibility;
	private TextView textview2;
	private TextView version_title;
	private LinearLayout version_space;
	private TextView version_text;
	private TextView size_title;
	private LinearLayout size_space;
	private TextView size_text;
	private TextView release_date_title;
	private LinearLayout release_date_space;
	private TextView release_date_text;
	private TextView compatibility_title;
	private LinearLayout compatibility_space;
	private TextView compatibility_text;
	
	private Intent intent = new Intent();
	private DatabaseReference pdb = _firebase.getReference("projects");
	private ChildEventListener _pdb_child_listener;
	private SharedPreferences save;
	private StorageReference pddb = _firebase_storage.getReference("projects/files");
	private OnCompleteListener<Uri> _pddb_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _pddb_download_success_listener;
	private OnSuccessListener _pddb_delete_success_listener;
	private OnProgressListener _pddb_upload_progress_listener;
	private OnProgressListener _pddb_download_progress_listener;
	private OnFailureListener _pddb_failure_listener;
	
	private TimerTask timer;
	private ObjectAnimator oa1 = new ObjectAnimator();
	private ObjectAnimator oa2 = new ObjectAnimator();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.downloader);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		scroll = findViewById(R.id.scroll);
		scroll_in_body = findViewById(R.id.scroll_in_body);
		top_bar = findViewById(R.id.top_bar);
		project_info = findViewById(R.id.project_info);
		download = findViewById(R.id.download);
		categories_scroll = findViewById(R.id.categories_scroll);
		my_comment = findViewById(R.id.my_comment);
		back = findViewById(R.id.back);
		top_title = findViewById(R.id.top_title);
		icon_and_progress = findViewById(R.id.icon_and_progress);
		project_info_inner = findViewById(R.id.project_info_inner);
		progress = findViewById(R.id.progress);
		icon_card = findViewById(R.id.icon_card);
		icon = findViewById(R.id.icon);
		project_name = findViewById(R.id.project_name);
		publisher = findViewById(R.id.publisher);
		download_icon = findViewById(R.id.download_icon);
		download_title = findViewById(R.id.download_title);
		categories_scroll_body = findViewById(R.id.categories_scroll_body);
		project_category = findViewById(R.id.project_category);
		category_project_recommended = findViewById(R.id.category_project_recommended);
		category_project_editors_choice = findViewById(R.id.category_project_editors_choice);
		new_comment = findViewById(R.id.new_comment);
		linear1 = findViewById(R.id.linear1);
		top_layout = findViewById(R.id.top_layout);
		description = findViewById(R.id.description);
		spc1 = findViewById(R.id.spc1);
		spc2 = findViewById(R.id.spc2);
		linear2 = findViewById(R.id.linear2);
		title = findViewById(R.id.title);
		imageview1 = findViewById(R.id.imageview1);
		whats_new = findViewById(R.id.whats_new);
		description_title = findViewById(R.id.description_title);
		description_text = findViewById(R.id.description_text);
		whats_new_title = findViewById(R.id.whats_new_title);
		whats_new_text = findViewById(R.id.whats_new_text);
		textview1 = findViewById(R.id.textview1);
		version_layout = findViewById(R.id.version_layout);
		size_layout = findViewById(R.id.size_layout);
		release_date_layout = findViewById(R.id.release_date_layout);
		compatibility = findViewById(R.id.compatibility);
		textview2 = findViewById(R.id.textview2);
		version_title = findViewById(R.id.version_title);
		version_space = findViewById(R.id.version_space);
		version_text = findViewById(R.id.version_text);
		size_title = findViewById(R.id.size_title);
		size_space = findViewById(R.id.size_space);
		size_text = findViewById(R.id.size_text);
		release_date_title = findViewById(R.id.release_date_title);
		release_date_space = findViewById(R.id.release_date_space);
		release_date_text = findViewById(R.id.release_date_text);
		compatibility_title = findViewById(R.id.compatibility_title);
		compatibility_space = findViewById(R.id.compatibility_space);
		compatibility_text = findViewById(R.id.compatibility_text);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				progress.setVisibility(View.VISIBLE);
				oa1.setTarget(icon_card);
				oa2.setTarget(icon_card);
				oa1.setPropertyName("scaleX");
				oa2.setPropertyName("scaleY");
				oa1.setDuration((int)(500));
				oa2.setDuration((int)(500));
				oa1.setFloatValues((float)(1), (float)(.7d));
				oa2.setFloatValues((float)(1), (float)(.7d));
				oa1.start();
				oa2.start();
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								intent.setAction(Intent.ACTION_VIEW);
								intent.setData(Uri.parse(ProjectLink));
								startActivity(intent);
								timer.cancel();
							}
						});
					}
				};
				_timer.schedule(timer, (int)(1500));
			}
		});
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		_pdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("key"))) {
					_UptimeLoadingDialog(false);
					ProjectLink = _childValue.get("project_file_link").toString();
					top_title.setText(_childValue.get("project_name").toString());
					project_name.setText(_childValue.get("project_name").toString());
					project_category.setText(_childValue.get("project_category").toString());
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("project_icon").toString())).into(icon);
					description_text.setText(_childValue.get("project_description").toString());
					whats_new_text.setText(_childValue.get("project_whats_new").toString());
					version_text.setText(_childValue.get("project_version").toString());
					size_text.setText(_childValue.get("project_size").toString());
					release_date_text.setText(_childValue.get("project_release_date").toString());
					compatibility_text.setText("Android 7+");
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
		pdb.addChildEventListener(_pdb_child_listener);
		
		_pddb_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_pddb_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				progress.setIndeterminate(false);
				progress.setProgress((int)_progressValue);
			}
		};
		
		_pddb_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_pddb_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				download.setVisibility(View.VISIBLE);
				oa1.setTarget(icon_card);
				oa2.setTarget(icon_card);
				oa1.setPropertyName("scaleX");
				oa2.setPropertyName("scaleY");
				oa1.setDuration((int)(500));
				oa2.setDuration((int)(500));
				oa1.setFloatValues((float)(.7d), (float)(1));
				oa2.setFloatValues((float)(.7d), (float)(1));
				oa1.start();
				oa2.start();
				progress.setVisibility(View.INVISIBLE);
				SketchwareUtil.CustomToast(getApplicationContext(), "Successfully Downloaded!", 0xFFFFFFFF, 16, 0xFF03A9F4, 12, SketchwareUtil.BOTTOM);
			}
		};
		
		_pddb_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_pddb_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		UnityAds.initialize(this, "5720336", false, false);
		UnityAds.initialize(this, "5720336", false, false);
		save.edit().putString("LastKey", getIntent().getStringExtra("key")).commit();
		progress.setVisibility(View.INVISIBLE);
		new_comment.setVisibility(View.VISIBLE);
		project_category.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)12, (int)2, 0xFF9E9E9E, Color.TRANSPARENT));
		category_project_recommended.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)12, (int)2, 0xFF9E9E9E, Color.TRANSPARENT));
		category_project_editors_choice.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)12, (int)2, 0xFF9E9E9E, Color.TRANSPARENT));
		download.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)14, 0xFF03A9F4));
		top_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), 0);
		project_name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), 0);
		project_name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), 0);
		_view_shadow(download, 25, "#03A9F4");
		_UptimeLoadingDialog(true);
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansregular.ttf"), 0);
		description_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), 0);
		whats_new_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansregular.ttf"), 0);
		UnityAds.show(DownloaderActivity.this, "Interstitial_Android");
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	public void _view_shadow(final View _view, final double _V, final String _color) {
		_view.setElevation((float)_V);
		_view.setOutlineAmbientShadowColor(Color.parseColor(_color));
		_view.setOutlineSpotShadowColor(Color.parseColor(_color));
	}
	
	
	public void _UptimeLoadingDialog(final boolean _visibility) {
		if (_visibility) {
			if (UptimeLoadingDialog== null){
					UptimeLoadingDialog = new ProgressDialog(this);
					UptimeLoadingDialog.setCancelable(false);
					UptimeLoadingDialog.setCanceledOnTouchOutside(false);
					
					UptimeLoadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
					UptimeLoadingDialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
					
			}
			UptimeLoadingDialog.show();
			UptimeLoadingDialog.setContentView(R.layout.loading);
			
			LinearLayout loading_bar_layout = (LinearLayout)UptimeLoadingDialog.findViewById(R.id.loading_bar_layout);
			
			
			loading_bar_layout.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
		} else {
			if (UptimeLoadingDialog != null){
				UptimeLoadingDialog.dismiss();
			}
		}
	}
	private ProgressDialog UptimeLoadingDialog;
	{
	}
	
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
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