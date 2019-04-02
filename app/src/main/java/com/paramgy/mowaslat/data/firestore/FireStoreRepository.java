package com.paramgy.mowaslat.data.firestore;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.paramgy.mowaslat.data.model.Location;
import com.paramgy.mowaslat.data.model.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;

public class FireStoreRepository {

    //Database Fields
    FirebaseFirestore db;
    CollectionReference locationsCollectionRef;
    CollectionReference resultsCollectionRef;
    CollectionReference ratingsCollectionRef;

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

    public void getResult(FirestoreResultCallback callback, String current, String destination, int method) {
        Log.d(TAG, "getResult: "+ current);
        Log.d(TAG, "getResult: "+ destination);
        Log.d(TAG, "getResult: "+ method);
        resultsCollectionRef.whereEqualTo(KEY_RESULT_CURRENT, current)
                .whereEqualTo(KEY_RESULT_DESTINATION, destination)
                .whereEqualTo(KEY_RESULT_METHOD, method)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d(TAG, "onSuccess: Query");
                Log.d(TAG, "onSuccess: is empty? "+queryDocumentSnapshots.isEmpty());
                if(!queryDocumentSnapshots.isEmpty()) {
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                        Result result = doc.toObject(Result.class);
                        String documentID = doc.getId();
                        result.setDocumentID(documentID);
                        callback.resultCallback(result);
                    }
                }else {
                    Log.d(TAG, "onSuccess: Doc Doesn't Exist");
                    callback.resultCallback(null);
                }
            }// end onSuccess
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, e.getMessage());
            }
        });
    } //end getResult

    public void getLocations(FirestoreLocationsCallback callback) {
        ArrayList<Location> locationsList = new ArrayList<>();
        locationsCollectionRef
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Location location = doc.toObject(Location.class);
                    locationsList.add(location);
                }
                callback.locationsListCallback(locationsList);
            }//end onSuccess
        });
    }// end getLocations



    public void setResultRating(float rating, String resultID, String uniqueID){
        // Do Some Operations to save the rating
        String rateID = resultID+uniqueID;
        Map<String,Object>  rate = new HashMap<>();
        rate.put("rate",rating);
        rate.put("resultID",resultID);

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

    }
}
