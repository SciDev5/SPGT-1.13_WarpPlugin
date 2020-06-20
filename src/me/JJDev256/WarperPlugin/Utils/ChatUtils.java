package me.JJDev256.WarperPlugin.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.JJDev256.WarperPlugin.Main;

public class ChatUtils {
	
	private static Main plugin;
	private static char ccc;
	
	public static void init() {
		plugin = Main.plugin;
		ccc = ((String) plugin.getConfig().get("general.chat_color_char")).charAt(0);
	}
	
	public static String chatCol(String s) {
		return ChatColor.translateAlternateColorCodes(ccc, s);
	}
	
	public static void ArglenErr(CommandSender s, String cmd, int argslen, int reqargslen) {
		s.sendMessage(chatCol(
				((String) plugin.getConfig().get("general.prefix")) +
				((String) plugin.getConfig().get("general.num_args_err"))
				.replaceAll("<cmd>", cmd)
				.replaceAll("<argslen>", ""+argslen)
				.replaceAll("<reqlen>", ""+reqargslen)));
	}
	
	public static void TypErr(CommandSender s, String cmd, String argval, String typ) {
		s.sendMessage(chatCol(
				((String) plugin.getConfig().get("general.prefix")) +
				((String) plugin.getConfig().get("general.typ_err"))
				.replaceAll("<cmd>", cmd)
				.replaceAll("<arg>", argval)
				.replaceAll("<typ>", typ)));
	}
	
	public static void CustomErr(CommandSender s, String cmdlabel, String errloc) {
		s.sendMessage(chatCol(
				((String) plugin.getConfig().get("general.prefix")) +
				((String) plugin.getConfig().get(errloc))
				.replaceAll("<cmd>", cmdlabel)));
	}
	
	public static void NoPermErr(CommandSender s) {
		s.sendMessage(chatCol(((String) plugin.getConfig().get("general.no_permission"))));
	}
	
	public static void NoConsoleErr(CommandSender s) {
		s.sendMessage(chatCol(((String) plugin.getConfig().get("general.no_console"))));
	}
	
	public static void Info(CommandSender s, String loc) {
		String str = ((String) plugin.getConfig().get(loc));
		s.sendMessage(chatCol(str));
	}
}
