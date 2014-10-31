package appname.companyname.com.appname;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;
 
public class Functions {
     
    private Context mContext;
     
    public Functions(Context context){
        this.mContext = context;
    }
    
    // Toast Message (Long)
    public void toast(String message) 
	{
		Toast.makeText(mContext.getApplicationContext(), message, Toast.LENGTH_LONG)
		.show();
	}
    
    // Get Device Information 
    public String getDeviceName() {
    	  String manufacturer = Build.MANUFACTURER;
    	  String model = Build.MODEL;
    	  if (model.startsWith(manufacturer)) {
    	    return capitalize(model);
    	  } else {
    	    return capitalize(manufacturer) + " " + model;
    	  }
    	}
    
    // Capitalize
	public String capitalize(String text) {
		char first = text.charAt(0);
		if (text == null || text.length() == 0) {
		    return "Unknow";
		}
		else if (Character.isUpperCase(first)) {
		    return text;
		} else {
		    return Character.toUpperCase(first) + text.substring(1);
		}
	}

    // Keydown

}