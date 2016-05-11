package no.oscen.visualr;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VisualrCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if(!sender.hasPermission("visualr.commands.visualr")) {
			sender.sendMessage(Util.PREFIX + "§cYou don't have permission to use this command.");
			return true;
		}
		
		if(args.length >= 1) {
			String cd = args[0].toLowerCase();
			
			switch(cd) {
			case "help":
				this.help(sender);
				return true;
			case "reload":
				this.confReload();
				sender.sendMessage(Util.PREFIX + "Config reloaded.");
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					Visualr.initPlayer(p);
				}
				sender.sendMessage(Util.PREFIX + "Prefixes and suffixes reloaded.");
				return true;
			default:
				this.help(sender);
				return true;
			}
		} else {
			this.help(sender);
			return true;
		}
	}
	
    public void confReload() {
    	Visualr.getPlugin(Visualr.class).reloadConfig();
    	new ConfVal(Visualr.getPlugin(Visualr.class).getConfig());
    }
	
	public void help(CommandSender sender) {
		sender.sendMessage(Util.PREFIX + "Help - Page 1 of 1");
		sender.sendMessage(Util.PREFIX + "/visualr §6help <page> §7- Displays this list");
		sender.sendMessage(Util.PREFIX + "/visualr §6reload §7- Reloads all config values");
	}

}
