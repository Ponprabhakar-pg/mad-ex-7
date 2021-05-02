package com.example.ex_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria c = new Criteria();
        String s = lm.getBestProvider(c, false);
        if (s != null && !s.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location l = lm.getLastKnownLocation(s);
            lm.requestLocationUpdates(s, 20000, 1, this);
            if(l!=null)
                onLocationChanged(l);
            else
                Toast.makeText(getApplicationContext(), "Location can't be retrieved !!!", Toast.LENGTH_LONG).show();
        }

        else
            Toast.makeText(getApplicationContext(), "Provider not found !!!", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onLocationChanged(Location arg0) {
// TODO Auto-generated method stub
        EditText e1=(EditText)findViewById(R.id.editText);
        EditText e2=(EditText)findViewById(R.id.editText2);

        Button b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setText("Check Location Once Again");
                e1.setText(""+arg0.getLatitude());
                e2.setText(""+arg0.getLongitude());

            }
        });

    }
    @Override
    public void onProviderDisabled(String arg0) {
// TODO Auto-generated method stub
    }
    @Override
    public void onProviderEnabled(String arg0) {
// TODO Auto-generated method stub
    }
    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
// TODO Auto-generated method stub
    }
}