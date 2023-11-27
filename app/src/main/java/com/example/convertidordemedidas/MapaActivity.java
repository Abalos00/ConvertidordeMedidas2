package com.example.convertidordemedidas;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView textViewLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        textViewLocation = findViewById(R.id.textViewLocation);

        // Obtener el fragmento de mapa y notificar cuando el mapa esté listo para usarse
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Añadir marcador en una ubicación específica y mover la cámara
        LatLng ubicacion = new LatLng(-34, 151); // Latitud y Longitud de ejemplo
        mMap.addMarker(new MarkerOptions().position(ubicacion).title("Ubicación de ejemplo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));

        // Puedes agregar más funcionalidades según tus necesidades
    }
}
