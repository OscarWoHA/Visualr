package no.oscen.visualr;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listnr implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		String format = ConfVal.QUITMSG;
		Player p = e.getPlayer();
		
		e.setQuitMessage(this.formatjq(format, p));
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		String format = ConfVal.JOINMSG;
		Player p = e.getPlayer();
		
		e.setJoinMessage(this.formatjq(format, p));
	}
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(!ConfVal.CHANGECHAT) {
			return;
		}
		
		Player p = e.getPlayer();
		
		String chform = ConfVal.CHATFORMAT;
		chform = this.formatchat(chform, p, e.getMessage());
		
		e.setFormat(chform);
	}
	
	public String formatjq(String format, Player p) {
		format = format.replaceAll("%PLAYER%", p.getDisplayName());
		format = ChatColor.translateAlternateColorCodes('&', format);
		
		return format;
	}
	
	public String formatchat(String ch, Player p, String message) {
		ch = ch.replaceAll("%PLAYER%", p.getDisplayName());
		message = message.replaceAll("%","%%");
		ch = ChatColor.translateAlternateColorCodes('&', ch);
		
		if(p.hasPermission("visualr.chat.colours"))
			ch = ch.replaceAll("%MESSAGE%", ChatColor.translateAlternateColorCodes('&', message));
		else
			ch = ch.replace("%MESSAGE%", message);
		
		return ch;
	}
}
