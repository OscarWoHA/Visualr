package no.oscen.visualr.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import no.oscen.visualr.Visualr;

public class VisualrAPI {
	
	/**
	 * Function for getting String prefix
	 * @param player
	 * @return String This returns string with the colourized prefix of the given Player player
	 */
	public static String getPrefix(Player player) {
		String gr = Visualr.permission.getPrimaryGroup(player);
		String basepre = ChatColor.translateAlternateColorCodes(
				'&',
				Visualr.chat.getGroupPrefix(player.getWorld(), gr)
				);

		return basepre;
	}

	/**
	 * Function for getting String prefix (colours only)
	 * @param player
	 * @return String This returns string with last colours of the prefix of the given Player player
	 */
	public static String getPrefixColour(Player player) {
		String gr = Visualr.permission.getPrimaryGroup(player);
		String basepre = ChatColor.getLastColors(
				ChatColor.translateAlternateColorCodes(
						'&',
						Visualr.chat.getGroupPrefix(player.getWorld(),gr)
						));

		return basepre;
	}
}
