package no.oscen.visualr;

import org.bukkit.Bukkit;

public class Util {
	public static String PREFIX = "";

	static {
		PREFIX = "§6[Visualr]§7 ";
	}
	
	public static void DEBUG(String message) {
		if(!ConfVal.DEBUGENABLE) {
			return;
		}
		
		message = PREFIX + "§4[DEBUG] §7"+ message;
		
		System.out.println(message);
		Bukkit.broadcastMessage(message);
	}
}
