package me.JJDev256.WarperPlugin.Utils;

import org.bukkit.Location;
import org.bukkit.World;

import me.JJDev256.WarperPlugin.Main;

public class ConfigUtils {
	static Main plugin;
	
	public static void init() {
		plugin = Main.plugin;
	}
	
	public static void saveArray(String location, String[] warps) {
		String warpstr = "[";
		if (warps.length > 0) {
			warpstr += warps[0];
		}
		
		for (int i = 1; i < warps.length; i++) {
			warpstr += ","+warps[i];
		}
		
		warpstr += "]";
		
		plugin.getConfig().set(location, warpstr);
		plugin.saveConfig();
	}
	
	public static String[] loadArray(String location) {
		String loadedStr = (String) plugin.getConfig().get(location);
		if (!(loadedStr.charAt(0) == '[' && loadedStr.charAt(loadedStr.length()-1) == ']')) {
			System.out.print("ERROR PARSING ARRAY CONFIG DATA!");
		}

		loadedStr = loadedStr.replaceAll("\\[", "");
		loadedStr = loadedStr.replaceAll("\\]", "");
		
		return loadedStr.split(",");
	}
	
	public static void saveLocation(String where, Location location) {
		String svstr = "["+location.getWorld().getName()+","+location.getX()+","+location.getY()+","+location.getZ()+","+location.getYaw()+","+location.getPitch()+"]";

		plugin.getConfig().set(where, svstr);
		plugin.saveConfig();
	}
	
	public static Location loadLocation(String where) {
		String[] s = loadArray(where);
		World world = null;
		for (World w : plugin.getServer().getWorlds()) {
			if (w.getName().equals(s[0])) {
				world = w;
				break;
			}
		}

		float x = Float.parseFloat(s[1]);
		float y = Float.parseFloat(s[2]);
		float z = Float.parseFloat(s[3]);
		float yaw = Float.parseFloat(s[4]);
		float pitch = Float.parseFloat(s[5]);
		
		return new Location(world, x, y, z, yaw, pitch);
	}
}
