package Login_System;

import java.util.HashMap;

/**
 *
 * @author Kenneth Odgien
 */
    public class Accounts {

	HashMap<String,String> logininfo = new HashMap<>();
	
	protected Accounts(){
		
		logininfo.put("ODON","admin01");
                logininfo.put("BATACANDOLO","admin01");
                logininfo.put("ODGIEN","admin01");
                logininfo.put("VILLANUEVA","admin01");
                logininfo.put("PASTOR","admin01");
	}
	
	public HashMap getLoginInfo(){
		return logininfo;
	}
}

