package me.JJDev256.WarperPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.JJDev256.WarperPlugin.Utils.ChatUtils;
import me.JJDev256.WarperPlugin.Utils.ConfigUtils;

public class WarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player) sender;
			if (!p.hasPermission("warp.use")) {
				ChatUtils.NoPermErr(sender);
				return true;
			}
		} else {
			ChatUtils.NoConsoleErr(sender);
			return true;
		}
		
		if (args.length != 1) {
			ChatUtils.ArglenErr(sender, label, args.length, 1);
			return false;
		}
		
		String[] warps = ConfigUtils.loadArray("warps.warps");
		boolean canWarp = false;
		for (int i = 0; i < warps.length; i++) {
			canWarp |= warps[i].equalsIgnoreCase(args[0]);
		}
		
		if (canWarp) {
			p.teleport(ConfigUtils.loadLocation("warps.warp_locations."+warps[0]));
			ChatUtils.Info(sender, "warps.warp_sucsessful");
		} else {
			ChatUtils.CustomErr(sender, label, "warps.warp_doesnt_exist_err");
			return false;
		}
		
		return true;
	}

}
