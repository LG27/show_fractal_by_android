package com.lg.fractal.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.lg.fractal.R;

public class TreeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tree);
//   ((Button)(findViewById(R.id.next))).setOnClickListener(new OnClickListener() {
//    
//    @Override
//    public void onClick(View v) {
//        startActivity(new Intent(TreeActivity.this,.class));
//    }
//});
    }

}
