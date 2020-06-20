package me.JJDev256.WarperPlugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.JJDev256.WarperPlugin.Main;
import me.JJDev256.WarperPlugin.Utils.ChatUtils;
import me.JJDev256.WarperPlugin.Utils.CommandUtils;
import me.JJDev256.WarperPlugin.Utils.ConfigUtils;

public class SetWarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = null;
		Boolean isConsole = false;
		
		if (sender instanceof Player) {
			p = (Player) sender;
			if (!p.hasPermission("setwarp.use")) {
				ChatUtils.NoPermErr(sender);
			}
		} else {
			isConsole = true;
		}
		
		switch (args.length) {
			case 1:
				if (isConsole) {ChatUtils.ArglenErr(sender, label, args.length, isConsole ? 7 : 1); return false;}
				String[] warps = ConfigUtils.loadArray("warps.warps");
				String[] warpsNew = new String[warps.length+1];

				
				for (int i = 0; i < warps.length; i++) {
					if (warps[i].equalsIgnoreCase(args[0])) {
						ChatUtils.CustomErr(sender, label, "warps.warp_already_set_err");
						return true;
					}
					warpsNew[i] = warps[i];
				}
				warpsNew[warps.length] = args[0];
				ConfigUtils.saveLocation("warps.warps."+args[0], p.getLocation());
				ConfigUtils.saveArray("warps.warps", warps);

			break;
			case 7:
				String[] warps1 = ConfigUtils.loadArray("warps.warps");
				String[] warpsNew1 = new String[warps1.length+1];
				
				for (int i = 0; i < warps1.length; i++) {
					if (warps1[i].equalsIgnoreCase(args[0])) {
						ChatUtils.CustomErr(sender, label, "warps.warp_already_set_err");
						return true;
					}
					warpsNew1[i] = warps1[i];
				}
				warpsNew1[warps1.length] = args[0];
				
				float x = CommandUtils.getNumber(args[1]);
				float y = CommandUtils.getNumber(args[2]);
				float z = CommandUtils.getNumber(args[3]);
				float yaw = CommandUtils.getNumber(args[4]);
				float pitch = CommandUtils.getNumber(args[5]);

				if (Float.isNaN(x)) {ChatUtils.TypErr(sender, label, args[1], "number");}
				if (Float.isNaN(y)) {ChatUtils.TypErr(sender, label, args[2], "number");}
				if (Float.isNaN(z)) {ChatUtils.TypErr(sender, label, args[3], "number");}
				if (Float.isNaN(yaw)) {ChatUtils.TypErr(sender, label, args[4], "number");}
				if (Float.isNaN(pitch)) {ChatUtils.TypErr(sender, label, args[5], "number");}
				if (Main.plugin.getServer().getWorld(args[6]) == null) {ChatUtils.TypErr(sender, label, args[6], "world");}
				
				ConfigUtils.saveLocation("warps.warp_locations."+args[0], new Location(Main.plugin.getServer().getWorld(args[6]), x, y, z, yaw, pitch));
				
				
				ConfigUtils.saveArray("warps.warps", warps1);
			break;
			default:
				ChatUtils.ArglenErr(sender, label, args.length, isConsole ? 7 : 1);
			return false;
		}
		
		return true;
	}
}
