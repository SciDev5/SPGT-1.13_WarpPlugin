package me.JJDev256.WarperPlugin.Utils;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import me.JJDev256.WarperPlugin.Main;

public class CommandUtils {
	static Main plugin;
	
	public static void init() {
		plugin = Main.plugin;
	}
	
	@Nullable
	public static float getNumber(String s) {
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
				case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': case '-': case '.': break;
				default: return Float.NaN;
			}
		}
		
		return Float.parseFloat(s);
	}
}
