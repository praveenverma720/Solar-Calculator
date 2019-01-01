package com.praveen.gooded.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlacesActivity extends AppCompatActivity {
    private static final int PLACE_PICKER_REQUEST = 1;
    private static double latitude;
    private static double longitude;
    Intent sendIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude", 0.0);
        longitude = intent.getDoubleExtra("longitude", 0.0);

        sendIntent = new Intent(PlacesActivity.this, MainActivity.class);
        LatLng position = new LatLng(latitude, longitude);
        LatLngBounds latLngBounds = new LatLngBounds(position, position);
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        builder.setLatLngBounds(latLngBounds);
        try {
            startActivityForResult(builder.build(PlacesActivity.this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {

                Place place = PlacePicker.getPlace(data, this);
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;
                sendIntent.putExtra("latitude",latitude);
                sendIntent.putExtra("longitude",longitude);
                startActivity(sendIntent);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
