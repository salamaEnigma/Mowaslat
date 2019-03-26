package com.paramgy.mowaslat.data.firestore;

import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.paramgy.mowaslat.data.model.Location;
import com.paramgy.mowaslat.data.model.Result;
import com.paramgy.mowaslat.data.repository.FirestoreCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class FireStoreRepository {

    //Database Fields

    FirebaseFirestore db;
    CollectionReference locationsCollectionRef;
    CollectionReference resultsCollectionRef;
    Result result;

    public static final String TAG = "Firestore";

    private static final String KEY_RESULT_CURRENT = "currentLocation";
    private static final String KEY_RESULT_DESTINATION = "destination";
    private static final String  KEY_RESULT_METHOD = "transportationMethod";

    public FireStoreRepository(){
        db = FirebaseFirestore.getInstance();
        locationsCollectionRef = db.collection("locations");
        resultsCollectionRef = db.collection("results");
    }

    public Result getResult(String current, String destination , int method){

        resultsCollectionRef.whereEqualTo(KEY_RESULT_CURRENT,current)
                .whereEqualTo(KEY_RESULT_DESTINATION,destination)
                .whereEqualTo(KEY_RESULT_METHOD,method)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()) {
                    DocumentSnapshot doc = queryDocumentSnapshots.getDocuments().get(0);
                    String documentID = doc.getId();
                    result = doc.toObject(Result.class);
                    result.setDocumentID(documentID);
                }
            }
        });

        return result;
    } //end getResult

    public void getLocations(FirestoreCallback callback){
        ArrayList<Location> locationsList = new ArrayList<>();
        locationsCollectionRef
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Location location = doc.toObject(Location.class);
                    locationsList.add(location);
                }
                    callback.dataCallback(locationsList);
            }//end onSuccess
        });
    }// end getLocations




}
