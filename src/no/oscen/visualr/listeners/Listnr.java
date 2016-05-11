package no.oscen.visualr.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import no.oscen.visualr.Visualr;
import no.oscen.visualr.utils.ConfVal;
import no.oscen.visualr.utils.Util;

public class Listnr implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if(ConfVal.DISABLEQUIT) {
			Util.DEBUG("Quit disabled");
			e.setQuitMessage(null);
			return;
		}
		
		String format = ConfVal.QUITMSG;
		Player p = e.getPlayer();

		Util.DEBUG("Quit message enabled");
		Util.DEBUG(format);
		
		e.setQuitMessage(this.formatjq(format, p));
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Visualr.initPlayer(e.getPlayer());
		
		if(ConfVal.DISABLEJOIN) {
			Util.DEBUG("Join disabled");
			e.setJoinMessage(null);
			return;
		}
		
		String format = ConfVal.JOINMSG;
		Player p = e.getPlayer();
		
		Util.DEBUG("Join message enabled");
		Util.DEBUG(format);
		
		e.setJoinMessage(this.formatjq(format, p));
	}
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(ConfVal.DISABLECHAT) {
			Util.DEBUG("Chat disabled");
			e.setCancelled(true);
			return;
		}
		
		if(!ConfVal.CHANGECHAT) {
			Util.DEBUG("Chat change disabled");
			return;
		}
		
		Player p = e.getPlayer();
		
		String chform = ConfVal.CHATFORMAT;
		chform = this.formatchat(chform, p, e.getMessage());
		
		Util.DEBUG("Chat format changed");
		Util.DEBUG(chform);
		
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
