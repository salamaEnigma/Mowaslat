package com.paramgy.mowaslat.features.main.contracts;

import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.google.android.material.navigation.NavigationView;
import com.paramgy.mowaslat.data.firestore.callbacks.FirestoreLocationsCallback;

public interface MainViewContract extends View.OnClickListener, AdapterView.OnItemSelectedListener, FirestoreLocationsCallback, RadioGroup.OnCheckedChangeListener, NavigationView.OnNavigationItemSelectedListener {
}
