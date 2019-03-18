package com.paramgy.mowaslatdemo.view.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.google.android.material.navigation.NavigationView;
import com.paramgy.mowaslatdemo.data.model.Location;

import java.util.List;

import androidx.lifecycle.Observer;

public interface MainActivityInterface extends View.OnClickListener, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener,
        Observer<List<Location>>, NavigationView.OnNavigationItemSelectedListener {



}
