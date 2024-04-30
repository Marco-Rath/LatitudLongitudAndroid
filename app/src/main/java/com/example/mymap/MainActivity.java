package com.example.mymap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    EditText cajaLatidud,cajaLongitud;
    Button btnBuscar;
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cajaLatidud=findViewById(R.id.cajaLatitud);
        cajaLongitud=findViewById(R.id.cajaLongitud);
        btnBuscar=findViewById(R.id.btnBuscar);


        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        // Agrega el OnClickListener al botón btnBuscar
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene las coordenadas ingresadas
                String latitudStr = cajaLatidud.getText().toString();
                String longitudStr = cajaLongitud.getText().toString();

                // Verifica que las cadenas no estén vacías
                if (!latitudStr.isEmpty() && !longitudStr.isEmpty()) {
                    try {
                        double latitud = Double.parseDouble(latitudStr);
                        double longitud = Double.parseDouble(longitudStr);

                        // Crea un objeto LatLng con las coordenadas
                        LatLng coordenadas = new LatLng(latitud, longitud);

                        // Centra el mapa en las coordenadas
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadas));

                        // Agrega un marcador en las coordenadas
                        mMap.addMarker(new MarkerOptions().position(coordenadas).title(""));
                    } catch (NumberFormatException e) {
                        // Maneja el caso en que las coordenadas ingresadas no sean válidas
                        // Por ejemplo, mostrar un mensaje de error
                    }
                }
            }
        });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
    mMap=googleMap;
    this.mMap.setOnMapClickListener(this);
    this.mMap.setOnMapLongClickListener(this);




    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
    cajaLatidud.setText(""+latLng.latitude);
    cajaLongitud.setText(""+latLng.longitude);

    mMap.clear();
    LatLng peru=new LatLng(latLng.latitude,latLng.longitude);
    mMap.addMarker(new MarkerOptions().position(peru).title(""));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(peru));


    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        cajaLatidud.setText(""+latLng.latitude);
        cajaLongitud.setText(""+latLng.longitude);


        mMap.clear();
        LatLng peru=new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(peru).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(peru));
    }
}