package com.paramgy.mowaslat.data.firestore;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.paramgy.mowaslat.data.model.pojos.Location;
import com.paramgy.mowaslat.data.model.pojos.Result;

import org.hashids.Hashids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FireStoreRepository {

    //Database Fields
    private FirebaseFirestore db;
    private CollectionReference locationsCollectionRef;
    private CollectionReference resultsCollectionRef;
    private CollectionReference ratingsCollectionRef;


    private static final String TAG = "Firestore";

    private static final String KEY_RESULT_CURRENT = "current";
    private static final String KEY_RESULT_DESTINATION = "destination";
    private static final String KEY_RESULT_METHOD = "method";

    public FireStoreRepository() {
        db = FirebaseFirestore.getInstance();
        locationsCollectionRef = db.collection("locations");
        resultsCollectionRef = db.collection("results");
        ratingsCollectionRef = db.collection("ratings");
    }

    public LiveData<Result> getResult(String current, String destination, int method) {
        MutableLiveData mutableLiveData = new MutableLiveData();
        resultsCollectionRef.whereEqualTo(KEY_RESULT_CURRENT, current)
                .whereEqualTo(KEY_RESULT_DESTINATION, destination)
                .whereEqualTo(KEY_RESULT_METHOD, method)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG, "onSuccess: Query");
                        Log.d(TAG, "onSuccess: is empty? " + queryDocumentSnapshots.isEmpty());
                        if (!queryDocumentSnapshots.isEmpty()) {
                            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                                Result result = doc.toObject(Result.class);
                                String documentID = doc.getId();
                                result.setDocumentID(documentID);
                                mutableLiveData.setValue(result);

                            }
                        } else {
                            Log.d(TAG, "onSuccess: Doc Doesn't Exist");
                            mutableLiveData.setValue(null);
                        }
                    }// end onSuccess
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, e.getMessage());
            }
        });
        return mutableLiveData;
    } //end getResult

    public LiveData<List<Location>> getLocations() {
        ArrayList<Location> locationsList = new ArrayList<>();
        MutableLiveData mutableLiveData = new MutableLiveData();
        locationsCollectionRef
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Location location = doc.toObject(Location.class);
                    locationsList.add(location);
                }

                mutableLiveData.setValue(locationsList);
            }//end onSuccess
        });
        return mutableLiveData;
    }// end getLocations


    // Rating method
    public void setResultRating(float rating, String resultID, String uniqueID) {
        // Hashing uniqueID
        Hashids hashids = new Hashids("com.paramgy.mowaslat");
        String hashUniqueID = hashids.encodeHex(uniqueID.substring(0, 7));
        String rateID = "#" + hashUniqueID + "#" + resultID;
        Log.d(TAG, "hashUniqueID = " + hashUniqueID);

        Map<String, Object> rate = new HashMap<>();
        rate.put("rate", rating);
        rate.put("resultID", resultID);

        DocumentReference docRef = ratingsCollectionRef.document(rateID);
        docRef.set(rate, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: Rating Done");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, e.getMessage());
            }
        });

    } // end set Rating

}
