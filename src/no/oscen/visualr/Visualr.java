package no.oscen.visualr;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import no.oscen.visualr.listeners.Listnr;
import no.oscen.visualr.listeners.ListnrPEX;
import no.oscen.visualr.listeners.commands.VisualrCommand;
import no.oscen.visualr.utils.ConfVal;
import no.oscen.visualr.utils.Util;
import no.oscen.visualr.utils.Util.PrintType;

public class Visualr extends JavaPlugin {
	public Logger _l = this.getLogger();    
	public static Permission permission = null;
	public static Chat chat = null;
	
	public void onEnable() {
		Util.DEBUG("Setting up Vault!", PrintType.CONSOLE);
		this.setupVault();
		Util.DEBUG("Vault setup complete.", PrintType.CONSOLE);

		Util.DEBUG("Trying to save default config.", PrintType.CONSOLE);
		this.saveDefaultConfig();
		
		Util.DEBUG("Configuration values loading.", PrintType.CONSOLE);
		new ConfVal(this.getConfig());

		Util.DEBUG("Registering listener.", PrintType.CONSOLE);
		if (getServer().getPluginManager().getPlugin("PermissionsEx") != null) {
			Util.DEBUG("Found PEX, using custom listener", PrintType.BOTH);
			getServer().getPluginManager().registerEvents(new ListnrPEX(), this);
		}
		
		getServer().getPluginManager().registerEvents(new Listnr(), this);
		
		Util.DEBUG("Registering commands.", PrintType.CONSOLE);
		this.getCommand("visualr").setExecutor(new VisualrCommand());

		Util.DEBUG("Initializing values onto online players.", PrintType.CONSOLE);
		for(Player p : this.getServer().getOnlinePlayers()) {
			initPlayer(p);	
		}
	}

	private void setupVault() {
		this.setupPermissions();
		this.setupChat();
	}

	public static void initPlayer(Player p) {
		String gr = permission.getPrimaryGroup(p);
		String basepre = chat.getGroupPrefix(p.getWorld(), gr);
		String basesuff = chat.getGroupSuffix(p.getWorld(), gr);

		String clpre = ChatColor.translateAlternateColorCodes('&', basepre);
		String clsuff = ChatColor.translateAlternateColorCodes('&', basesuff);

		String strippre = ChatColor.getLastColors(clpre);

		String displaynm = clpre + p.getName() + clsuff;
		String tabnm = strippre + p.getName().substring(0, p.getName().length());

		if(ConfVal.CHANGEDISPLAYNAME) {
			p.setDisplayName(displaynm);
		}

		if(ConfVal.CHANGETABNAME) {
			p.setPlayerListName(tabnm);
		}
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			permission = permissionProvider.getProvider();
		}
		return (permission != null);
	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
		if (chatProvider != null) {
			chat = chatProvider.getProvider();
		}

		return (chat != null);
	}
}
