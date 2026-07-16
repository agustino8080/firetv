package com.pizarro.firetvapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends Activity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        myWebView = new WebView(this);
        setContentView(myWebView);

        // --- Configuración Multiplataforma ---
        WebSettings webSettings = myWebView.getSettings();
        
        // Habilitar JS y Almacenamiento (Esencial para webs modernas)
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        
        // Adaptación de pantalla (Móvil y TV)
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        
        // Zoom (Útil en móviles)
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false); // Oculta los botones feos de +/-

        // Permitir cookies
        CookieManager.getInstance().setAcceptCookie(true);
        
        // Mejorar rendimiento
        myWebView.setFocusable(true);
        myWebView.setFocusableInTouchMode(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        myWebView.setWebViewClient(new WebViewClient());

        // URL a cargar
        myWebView.loadUrl("https://www.google.com"); 
    }

    // Lógica del mando y botón atrás de Android
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Si pulsamos atrás y el navegador tiene historial, retrocedemos en la web
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
