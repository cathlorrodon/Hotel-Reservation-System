package Login_System;

import java.util.HashMap;

/**
 *
 * @author Kenneth Odgien
 */
    public class Accounts {

	HashMap<String,String> logininfo = new HashMap<>();
	
	protected Accounts(){
		
		logininfo.put("name001","password01");
		logininfo.put("name002","password02");
		logininfo.put("name003","password03");
	}
	
	public HashMap getLoginInfo(){
		return logininfo;
	}
}

