package br.com.maissaudeapp;
 
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;
 
public class AndroidJSWebView extends Activity {
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //WebView 
        WebView browser;
        browser = (WebView)findViewById(R.id.webkit);
        //Habilita Javascript
        browser.getSettings().setJavaScriptEnabled(true);
        //Interface nome 'Android' 
        browser.addJavascriptInterface(new WebAppInterface(this), "Android");
        //Carrega URL dentro da WebView
        browser.loadUrl("http://aspspider.net/index.html");
    }
    
    //Classe para ser injetada na Web page
    public class WebAppInterface {
        Context mContext;
 
        /** */
        WebAppInterface(Context c) {
            mContext = c;
        }
 
        /**
         * 
         * @param toast
         */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
 
        /**
         * 
         * @param dialogMsg
         */
        @JavascriptInterface
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
         * Intent - move para PacienteScanActivity
         */
        @JavascriptInterface
        public void moveToNextScreen(){
//             AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
//             // Setting Dialog Title
//             alertDialog.setTitle("Alerta");
//             // Setting Dialog Message
//             alertDialog.setMessage("Tem certeza que sair dessa tela?");
//             
//             // Setting Positive "Yes" Button
//             alertDialog.setPositiveButton("SIM",
//                     new DialogInterface.OnClickListener() {
//                         public void onClick(DialogInterface dialog, int which) {
                             //Move to Next screen
                             Intent chnIntent = new Intent(AndroidJSWebView.this, PacienteScanActivity.class);  
                             startActivity(chnIntent);  
//                         }
//                     });
//             
//             // Setting Negative "NO" Button
//             alertDialog.setNegativeButton("NÃO",
//                     new DialogInterface.OnClickListener() {
//                         public void onClick(DialogInterface dialog, int which) {
//                             // Cancel Dialog
//                             dialog.cancel();
//                         }
//                     });
//             // Showing Alert Message
//             alertDialog.show();
        }
    }
}


