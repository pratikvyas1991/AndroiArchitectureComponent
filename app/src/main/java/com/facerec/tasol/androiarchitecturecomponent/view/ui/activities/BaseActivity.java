package com.facerec.tasol.androiarchitecturecomponent.view.ui.activities;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.facerec.tasol.androiarchitecturecomponent.viewmodel.StudentViewModel;

public class BaseActivity extends AppCompatActivity {
    private String TAGHOME = "%%%BaseActivity";
    public StudentViewModel studentViewModel;
    private static final int LOCATION_PERMISSION_CALLBACK_CONSTANT = 100;
    private String[] permissionsRequired = new String[]{
            android.Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (studentViewModel == null) {
            studentViewModel = ViewModelProviders.of(BaseActivity.this).get(StudentViewModel.class);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkRunTimePermissionsLocation()) {
                //Checking Location Permission
                proceedAfterPermissionLocation();
            } else {
                askRuntimePermissionsLocations();
            }
        }
    }

    public boolean checkRunTimePermissionsLocation() {
        boolean retVal = false;
        if (ActivityCompat.checkSelfPermission(BaseActivity.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED) {
            retVal = false;
        } else {
            retVal = true;
        }
        return retVal;
    }

    private void proceedAfterPermissionLocation() {

        if (getGPSStatus() == 0) {
//            Forcefully start location here , as dialogue will look weired
            Intent viewIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(viewIntent);
        }
//        Handler handler =new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent intent =new Intent(Splash.this, MapsACtivityHome.class);
//                startActivity(intent);
//                finish();
//
//            }
//        },5000);
    }

    public int getGPSStatus() {
        LocationManager locationManager;
        boolean gpsStatus;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return gpsStatus ? 1 : 0;
    }

    public void askRuntimePermissionsLocations() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(BaseActivity.this, permissionsRequired[0])) {
            //Show Information about why you need the permission
            AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
            builder.setTitle("Need Permission");
            builder.setMessage("This app needs Location permission.");
            builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    ActivityCompat.requestPermissions(BaseActivity.this, permissionsRequired, LOCATION_PERMISSION_CALLBACK_CONSTANT);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        } else {
            //just request the permission
            ActivityCompat.requestPermissions(BaseActivity.this, permissionsRequired, LOCATION_PERMISSION_CALLBACK_CONSTANT);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_CALLBACK_CONSTANT) {
            //check if all permissions are granted
            boolean allgranted = false;
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    allgranted = true;
                } else {
                    allgranted = false;
                    break;
                }
            }
            if (allgranted) {
                proceedAfterPermissionLocation();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(BaseActivity.this, permissionsRequired[0])) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
                builder.setTitle("Need Permission");
                builder.setMessage("This app needs Location permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(BaseActivity.this, permissionsRequired, LOCATION_PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        }
    }
}
