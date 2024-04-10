import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Lp extends AppCompatActivity {
    
    private FusedLocationProviderClient a;
    private static final String BASE_URL = "https://yourserver.com/api/";
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        a = LocationServices.getFusedLocationProviderClient(this);
        b();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private void b() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            a.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location c) {
                    if (c != null) {
                        Log.d("D", "L: " + c.getLatitude() + ", " + c.getLongitude());
                        p(c.getLatitude(), c.getLongitude());
                    }
                }
            });
        }
    }

    public void p(double latitude, double longitude) {
        Call<Void> call = apiService.sendLocation(latitude, longitude);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                } else {
                    // Handle failed response
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle network errors
            }
        });
    }
}