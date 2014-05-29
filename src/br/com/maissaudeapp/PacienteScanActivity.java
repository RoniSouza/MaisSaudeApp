package br.com.maissaudeapp;

import jim.h.common.android.zxinglib.integrator.IntentIntegrator;
import jim.h.common.android.zxinglib.integrator.IntentResult;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
<<<<<<< HEAD
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;	
=======
import android.widget.TextView;
>>>>>>> parent of 36f4e8d... implantando wv em psa

public class PacienteScanActivity extends Activity {
    private Handler  handler = new Handler();
    private TextView txtScanResult;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE:
                IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,
                        resultCode, data);
                if (scanResult == null) {
                    return;
                }
                
                final String result = scanResult.getContents();
                if (result != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtScanResult.setText(result);
                        }
                        
                    });
                }
                break;
            default:
        }
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
<<<<<<< HEAD
        
        webviewPaciente = (WebView)findViewById(R.id.webView1);
      //Interface nome 'Android' 
        webviewPaciente.setWebViewClient(new MyBrowser());
=======

>>>>>>> parent of 36f4e8d... implantando wv em psa
        txtScanResult = (TextView) findViewById(R.id.scan_result);
        View btnScan = findViewById(R.id.scan_button);

        // Ação de escanear QRCode
        // Scan button
        btnScan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ultimo parametro true para ligar flash da câmera
                IntentIntegrator.initiateScan(PacienteScanActivity.this, PacienteScanActivity.class
                		R.layout.scan,
                        R.id.viewfinder_view, 
                        R.id.preview_view, true);
               
            }
        });
    }
<<<<<<< HEAD
    
    // Abrir WebView Prescrição
    @SuppressLint("SetJavaScriptEnabled")
	public void open(View view){
  String url = "http://aspspider.info/maissaude/Mobile/VisualizarPrescricaoMedica?id="
     //  String url = "http://maissaude01.azurewebsites.net/Mobile/VisualizarPrescricaoMedica?id="
        			+ txtScanResult.getText().toString();
        webviewPaciente.getSettings().setLoadsImagesAutomatically(true);
        webviewPaciente.getSettings().setJavaScriptEnabled(true);
        webviewPaciente.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webviewPaciente.loadUrl(url);

     }
    
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
           view.loadUrl("http://maissaude.azurewebsites.net/");
           return true;
        }
     }
    
=======

>>>>>>> parent of 36f4e8d... implantando wv em psa
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.main, menu);
       return true;
    }
}
