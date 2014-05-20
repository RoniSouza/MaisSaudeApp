package br.com.maissaudeapp;

import jim.h.common.android.zxinglib.integrator.IntentIntegrator;
import jim.h.common.android.zxinglib.integrator.IntentResult;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;	

public class PacienteScanActivity extends Activity {
    private Handler  handler = new Handler();
    private TextView txtScanResult;
    private WebView webviewPaciente;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        webviewPaciente = (WebView)findViewById(R.id.webView1);
        webviewPaciente.setWebViewClient(new MyBrowser());
        txtScanResult = (TextView) findViewById(R.id.scan_result);
        View btnScan = findViewById(R.id.scan_button);
        
 
        // Ação de escanear QRCode
        btnScan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ultimo parametro true para ligar flash da câmera
                IntentIntegrator.initiateScan(PacienteScanActivity.this, 
                		R.layout.scan,
                        R.id.viewfinder_view, 
                        R.id.preview_view, true);
               
            }
        });
    }
    
    // Abrir WebView Prescrição
    @SuppressLint("SetJavaScriptEnabled")
	public void open(View view){
        String url = "http://aspspider.info/maissaude/Mobile/VisualizarPrescricaoMedica?id="
        			+ txtScanResult.getText().toString();
        webviewPaciente.getSettings().setLoadsImagesAutomatically(true);
        webviewPaciente.getSettings().setJavaScriptEnabled(true);
        webviewPaciente.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webviewPaciente.loadUrl(url);

     }
    
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
           view.loadUrl(url);
           return true;
        }
     }

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
}
