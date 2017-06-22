package com.ireolaniyan.codescann;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends AppCompatActivity {
    TextView resultTv;
    Button scanButton;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0){
            if (resultCode == CommonStatusCodes.SUCCESS){
                if (data != null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    resultTv.setText(barcode.displayValue);
                }else{
                    resultTv.setText("No barcode detected");
                }
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = (TextView) findViewById(R.id.barcode_result);
        scanButton = (Button) findViewById(R.id.scan_button);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ScanBarcodeActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }


}
