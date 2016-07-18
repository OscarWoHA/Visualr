package no.oscen.visualr.utils;

import org.bukkit.Bukkit;

public class Util {
	public static String PREFIX = "";

	static {
		PREFIX = "§6[Visualr]§7 ";
	}
	
	public enum PrintType {
		CONSOLE,
		INGAME,
		BOTH;
	}
	
	public static void DEBUG(String message, PrintType type) {
		if(!ConfVal.DEBUGENABLE) {
			return;
		}
		
		message = PREFIX + "§4[DEBUG] §7"+ message;
		
		switch(type) {
		case CONSOLE:
			System.out.println(message);
			break;
		case INGAME:
			Bukkit.broadcastMessage(message);
			break;
		case BOTH:
			System.out.println(message);
			Bukkit.broadcastMessage(message);
			break;
		}
	}
}
