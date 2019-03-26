package com.paramgy.mowaslat.view.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.google.android.material.navigation.NavigationView;
import com.paramgy.mowaslat.data.firestore.FirestoreLocationsCallback;

public interface MainActivityInterface extends View.OnClickListener, AdapterView.OnItemSelectedListener, FirestoreLocationsCallback, RadioGroup.OnCheckedChangeListener, NavigationView.OnNavigationItemSelectedListener {



}
