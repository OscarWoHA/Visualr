package no.oscen.visualr.utils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import no.oscen.visualr.utils.Util.PrintType;

public class ConfVal {
	//ENABLE (boolean)
	public static boolean DEBUGENABLE = true;

	//CHANGE (boolean)
	public static boolean CHANGEDISPLAYNAME = true;
	public static boolean CHANGETABNAME = true;
	public static boolean CHANGECHAT = true;
	public static boolean CHANGEJOIN = true;
	public static boolean CHANGEQUIT = true;

	//DISABLE (boolean)
	public static boolean DISABLEJOIN = false;
	public static boolean DISABLEQUIT = false;
	public static boolean DISABLECHAT = false;

	//FORMAT (string)
	public static String CHATFORMAT = "";

	//MESSAGE (string)
	public static String QUITMSG = "";
	public static String JOINMSG = "";

	public ConfVal(FileConfiguration conf) {
		ConfigurationSection enable = conf.getConfigurationSection("conf");
		DEBUGENABLE = enable.getBoolean("debug");

		ConfigurationSection change = conf.getConfigurationSection("conf.change");

		for(String keys : change.getKeys(true)) {
			Util.DEBUG(Boolean.toString(change.getBoolean(keys)), PrintType.BOTH);
		}

		CHANGEDISPLAYNAME = change.getBoolean("displayname");
		CHANGETABNAME = change.getBoolean("tabname");
		CHANGECHAT = change.getBoolean("chat");
		CHANGEQUIT = change.getBoolean("quit");
		CHANGEJOIN = change.getBoolean("join");

		ConfigurationSection disable = conf.getConfigurationSection("conf.disable");

		for(String keys : disable.getKeys(true)) {
			Util.DEBUG(Boolean.toString(disable.getBoolean(keys)), PrintType.BOTH);
		}

		DISABLEJOIN = disable.getBoolean("join");
		DISABLEQUIT = disable.getBoolean("quit");
		DISABLECHAT = disable.getBoolean("chat");

		ConfigurationSection formats = conf.getConfigurationSection("formats");

		for(String keys : formats.getKeys(true)) {
			Util.DEBUG(formats.getString(keys), PrintType.BOTH);
		}

		CHATFORMAT = formats.getString("chat");


		ConfigurationSection messages = conf.getConfigurationSection("messages");

		for(String keys : messages.getKeys(true)) {
			Util.DEBUG(messages.getString(keys), PrintType.BOTH);
		}

		QUITMSG = messages.getString("quitmsg");
		JOINMSG = messages.getString("joinmsg");		
	}


}
