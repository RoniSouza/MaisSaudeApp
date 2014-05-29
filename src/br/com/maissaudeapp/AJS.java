package br.com.maissaudeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import jim.h.common.android.zxinglib.integrator.IntentIntegrator;
import jim.h.common.android.zxinglib.integrator.IntentResult;

public class PacienteScanActivity extends Activity
{
  private Handler handler = new Handler();
  private TextView txtScanResult;
  private WebView webviewPaciente;

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default:
    case 195543262:
    }
    while (true)
    {
      return;
      IntentResult localIntentResult = IntentIntegrator.parseActivityResult(paramInt1, paramInt2, paramIntent);
      if (localIntentResult == null)
        continue;
      String str = localIntentResult.getContents();
      if (str == null)
        continue;
      this.handler.post(new Runnable(str)
      {
        public void run()
        {
          PacienteScanActivity.this.txtScanResult.setText(this.val$result);
        }
      });
    }
  }

  public void onCreate(Bundle paramBundle){
    super.onCreate(paramBundle);
    setContentView(2130903041);
    this.webviewPaciente = ((WebView)findViewById(2131230720));
    this.webviewPaciente.setWebViewClient(new MyBrowser(null));
    this.txtScanResult = ((TextView)findViewById(2131230723));
    findViewById(2131230722).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        IntentIntegrator.initiateScan(PacienteScanActivity.this, 2130903042, 2131230726, 2131230725, true);
      }
    });
  }

  public void open(View paramView)
  {
    String str = "http://aspspider.info/maissaude/Mobile/VisualizarPrescricaoMedica?id=" + this.txtScanResult.getText().toString();
    this.webviewPaciente.getSettings().setLoadsImagesAutomatically(true);
    this.webviewPaciente.getSettings().setJavaScriptEnabled(true);
    this.webviewPaciente.setScrollBarStyle(0);
    this.webviewPaciente.loadUrl(str);
  }

  private class MyBrowser extends WebViewClient
  {
    private MyBrowser()
    {
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      paramWebView.loadUrl(paramString);
      return true;
    }
  }
}

/* Location:           C:\Nova Pasta (2)\tools\classes-dex2jar.jar
 * Qualified Name:     br.com.maissaudeapp.PacienteScanActivity
 * JD-Core Version:    0.6.0
 */