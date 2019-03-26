package com.paramgy.mowaslat.data.repository;

import com.paramgy.mowaslat.data.model.Location;

import java.util.List;

public interface FirestoreCallback {
   void dataCallback (List<Location> locations);
}
