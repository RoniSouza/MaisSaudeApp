package br.com.maissaudeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

   //private EditText field;
  
   @Override		
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      WebView browser;
      //field = (EditText)findViewById(R.id.urlField);
      browser = (WebView)findViewById(R.id.webView1);
      browser.setWebViewClient(new MyBrowser());
      browser.getSettings().setLoadsImagesAutomatically(true);
      browser.getSettings().setJavaScriptEnabled(true);
      browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
      browser.addJavascriptInterface(new WebAppInterface(this), "Android");
      browser.loadUrl("http://maissaude.azurewebsites.net/");
   }
   
   //Class to be injected in Web page
   public class WebAppInterface {
       Context mContext;

       /** Instantiate the interface and set the context */
       WebAppInterface(Context c) {
           mContext = c;
       }

       /**
        * Show Toast Message
        * @param toast
        */
       @JavascriptInterface
       public void showToast(String toast) {
           Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
       }

       /**
        * Show Dialog 
        * @param dialogMsg
        */
       @SuppressWarnings("deprecation")
		public void showDialog(String dialogMsg){
           AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();

           // Setting Dialog Title
           alertDialog.setTitle("JS triggered Dialog");

           // Setting Dialog Message
           alertDialog.setMessage(dialogMsg);

           // Setting alert dialog icon
           //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

           // Setting OK Button
           alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                   Toast.makeText(mContext, "Dialog dismissed!", Toast.LENGTH_SHORT).show();
               }
           });

           // Showing Alert Message
           alertDialog.show();
       }

       /**
        * Intent - Move to next screen
        */
       @JavascriptInterface
       public void moveToNextScreen(){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
            // Setting Dialog Title
            alertDialog.setTitle("Alerta");
            // Setting Dialog Message
            alertDialog.setMessage("Quer sair dessa tela?");
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("SIM",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Move to Next screen
                            Intent chnIntent = new Intent(MainActivity.this, PacienteScanActivity.class);  
                            startActivity(chnIntent);  
                        }
                    });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NÃO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Cancel Dialog
                            dialog.cancel();
                        }
                    });
            // Showing Alert Message
            alertDialog.show();
       }
   }

   private class MyBrowser extends WebViewClient {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
         view.loadUrl("http://maissaude.azurewebsites.net/");
         return true;
      }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
}

