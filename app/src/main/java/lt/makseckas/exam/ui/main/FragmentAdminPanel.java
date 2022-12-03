package lt.makseckas.exam.ui.main;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Locale;

import lt.makseckas.exam.databinding.FragmentAdminPanelBinding;

public class FragmentAdminPanel extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    FragmentAdminPanelBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdminPanelBinding.inflate(inflater, container, false);
        sensorManager = (SensorManager) requireContext().getSystemService(Context.SENSOR_SERVICE);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
        // You must implement this callback in your code.
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        binding.textViewXValue.setText(String.format(Locale.getDefault(), "%.3f", event.values[0]));
        binding.textViewYValue.setText(String.format(Locale.getDefault(), "%.3f", event.values[1]));
        binding.textViewZValue.setText(String.format(Locale.getDefault(), "%.3f", event.values[2]));
    }

    @Override
    public void onResume() {
        super.onResume();
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
        Sensor magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticField != null) {
            sensorManager.registerListener(this, magneticField,
                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}