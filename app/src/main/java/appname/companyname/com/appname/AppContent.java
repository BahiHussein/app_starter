package appname.companyname.com.appname;

public class AppContent {


/*[1] Offline Application*/								public static final Boolean offline_app = false;
/*[3] One Page Application*/							public static final Boolean fullscreen_webview = false;
//==========================================RateME================================================	

/*[D1]Application Name( Used for Rating )*/     		public static final String application_name = "Your App Name"; /*<<=========Important
/*[D2]Package Name(Example com.example.appname)*/     	public static final String package_name  = "com.yourcompany.appname";  /*<<=========Important 
/*[D3]Number of lunches before Rate me message shows*/ 	public static final int number_of_uses_before_launching_the_rate_dialog = 1;
/*[D4]To deactivate RateDialog change "yes" to "no"*/ 	public static final boolean rate_dialog_active = true;
//==========================================Admob================================================

/*[E1]please replace it with you Admob Banner ID*/     	public static final String google_play_ads_banner_id = "Admob banner id";
/*[E2]To deactivate Admob change "yes" to "no"*/     	public static final Boolean google_play_ads_banner_active = true;
/*[E3]please replace it with you Admob Interstitial ID*/public static final String google_play_ads_interstitial_id = "Admob Interstitial id";
/*[E4]Deactivate InterstitialAd change "yes" to "no"*/  public static final Boolean google_play_ads_interstitial_active = true;
//========================================MESSAGES=============================================

/*[B2]Connection issues message*/                       public static final String connection_error_message = "Connection Error! please try later";
/*[B3]Message shows before the shared link*/			public static final String share_message = "Check Out this link: ";
//==========================================Forced UI================================================

/*[E1]Hide HintText on focus (on the registration form)*/public static final Boolean hide_hint_on_focus = false;


}
