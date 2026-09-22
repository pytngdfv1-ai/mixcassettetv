package com.mixcassettetv;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Creamos una vista simple para confirmar que la app abrió correctamente en la TV
        TextView tv = new TextView(this);
        tv.setText("¡MixCassetteTV funcionando en Android TV!");
        tv.setTextSize(24);
        setContentView(tv);
    }
}
