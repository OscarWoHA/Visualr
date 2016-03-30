package no.oscen.visualr;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfVal {
	public static boolean CHANGEDISPLAYNAME = true;
	public static boolean CHANGETABNAME = true;
	public static boolean CHANGECHAT = true;
	public static boolean CHANGEJOIN = true;
	public static boolean CHANGEQUIT = true;
	
	public static String CHATFORMAT = "";
	public static String QUITMSG = "";
	public static String JOINMSG = "";
	
	public ConfVal(FileConfiguration conf) {
		CHANGEDISPLAYNAME = conf.getBoolean("conf.changedisplayname");
		CHANGETABNAME = conf.getBoolean("conf.changetabname");
		CHANGECHAT = conf.getBoolean("conf.changechat");
		CHANGEQUIT = conf.getBoolean("conf.changequit");
		CHANGEJOIN = conf.getBoolean("conf.changejoin");
		
		CHATFORMAT = conf.getString("chat.format") == null ? conf.getString("chat.format") : "";
		QUITMSG = conf.getString("chat.quitmsg") == null ? conf.getString("chat.quitmsg") : "";
		JOINMSG = conf.getString("chat.joinmsg") == null ? conf.getString("chat.joinmsg") : "";		
	}

}
