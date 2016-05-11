package no.oscen.visualr.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import no.oscen.visualr.Visualr;
import ru.tehkode.permissions.events.PermissionEntityEvent;
import ru.tehkode.permissions.events.PermissionEntityEvent.Action;

public class ListnrPEX implements Listener {
	
	@EventHandler
	public void onPEXChange(PermissionEntityEvent e) {
		if(e.getAction().equals(Action.INHERITANCE_CHANGED)) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(p.getUniqueId().toString().equals(e.getEntityIdentifier())) {
					Visualr.initPlayer(p);
				}
			}
		}
	}
}
