package info.aea.drawer;

import info.aea.snippets.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview_java extends Fragment {
	
	 private static final String WebSettings = null;
	 private WebView webView;
	 private Bundle webViewBundle;
	 
	public webview_java(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		
        View rootView = inflater.inflate(R.layout.webview_home, container, false);
        
        webView = (WebView) rootView.findViewById(R.id.webView1);
		webView.setWebViewClient(new WebViewClient());
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);   
        webView.getSettings().setAllowFileAccess(true); 
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setRenderPriority(RenderPriority.HIGH);
       
      
    
         if (webViewBundle == null) {
    	 try{
		    webView.loadUrl("http://www.orcale.com/");
    	 }catch (Exception e){
 			e.printStackTrace();}
		} else {
		    webView.restoreState(webViewBundle);
		}
        
		return rootView;

	    }
	    
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	  
	   
	    public void onPause() {
		super.onPause();

		webViewBundle = new Bundle();
		webView.saveState(webViewBundle);
	    
        }
	}

