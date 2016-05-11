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

public class Visualr extends JavaPlugin {
	public Logger _l = this.getLogger();    
	public static Permission permission = null;
	public static Chat chat = null;
	
	public void onEnable() {		
		_l.info("Setting up Vault!");
		this.setupVault();
		_l.info("Vault setup complete.");

		_l.info("Trying to save default config.");
		this.saveDefaultConfig();

		_l.info("Configuration values loading.");
		new ConfVal(this.getConfig());

		_l.info("Registering listener.");
		if (getServer().getPluginManager().getPlugin("PermissionsEx") != null) {
			_l.info("Found PEX, using custom listener");
			getServer().getPluginManager().registerEvents(new ListnrPEX(), this);
		}
		
		getServer().getPluginManager().registerEvents(new Listnr(), this);
		
		_l.info("Registering commands.");
		this.getCommand("visualr").setExecutor(new VisualrCommand());

		_l.info("Initializing values onto online players.");
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
