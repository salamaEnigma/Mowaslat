package com.paramgy.mowaslat.features.main.contracts;

import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.google.android.material.navigation.NavigationView;
import com.paramgy.mowaslat.data.model.pojos.Location;

import java.util.List;

import androidx.lifecycle.Observer;

public interface MainViewContract extends View.OnClickListener, AdapterView.OnItemSelectedListener, Observer<List<Location>>, RadioGroup.OnCheckedChangeListener, NavigationView.OnNavigationItemSelectedListener {
}
