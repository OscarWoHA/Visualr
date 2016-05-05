package no.oscen.visualr;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Visualr extends JavaPlugin {
	public Logger _l = this.getLogger();    
	private Permission permission = null;
    private Economy economy = null;
    private Chat chat = null;
    
	public void onEnable() {	
		_l.info("Setting up Vault!");
		this.setupVault();
		_l.info("Vault setup complete.");
		
		_l.info("Trying to save default config.");
		this.saveDefaultConfig();
		
		_l.info("Configuration values loading.");
		new ConfVal(this.getConfig());
		
		_l.info("Registering listener.");
		this.getServer().getPluginManager().registerEvents(new Listnr(), this);
		
		_l.info("Registering commands.");
		this.getCommand("visualr").setExecutor(new VisualrCommand());
		
		_l.info("Initializing values onto online players.");
		for(Player p : this.getServer().getOnlinePlayers()) {
			this.initPlayer(p);	
		}
	}
	
	private void setupVault() {
		this.setupPermissions();
		this.setupChat();
		this.setupEconomy();
	}
	
	public void initPlayer(Player p) {
		String gr = this.permission.getPrimaryGroup(p);
		String basepre = this.chat.getGroupPrefix(p.getWorld(), gr);
		String basesuff = this.chat.getGroupSuffix(p.getWorld(), gr);
		
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

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

}
