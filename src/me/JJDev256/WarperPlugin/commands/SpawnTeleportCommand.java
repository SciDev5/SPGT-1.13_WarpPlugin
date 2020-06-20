package me.JJDev256.WarperPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.JJDev256.WarperPlugin.Utils.ChatUtils;

public class SpawnTeleportCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = null;
		if (!(sender instanceof Player)) {
			ChatUtils.NoConsoleErr(sender);
			return true;
		}
		p = (Player) sender;
		
		p.teleport(p.getWorld().getSpawnLocation());
		return true;
	}
	
}
