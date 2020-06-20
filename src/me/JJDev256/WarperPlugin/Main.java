package me.JJDev256.WarperPlugin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.JJDev256.WarperPlugin.Utils.UtilsManager;
import me.JJDev256.WarperPlugin.commands.SetWarpCommand;
import me.JJDev256.WarperPlugin.commands.SpawnTeleportCommand;
import me.JJDev256.WarperPlugin.commands.WarpCommand;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	public static FileConfiguration config;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		saveDefaultConfig();
		
		config = getConfig();
		
		config.set("isWorking", "hello12345");
		saveConfig();
		
		UtilsManager.init();

		addCommand("setwarp", new SetWarpCommand());
		addCommand("warp", new WarpCommand());
		addCommand("spawn", new SpawnTeleportCommand());
	}
	
	private void addCommand(String command, CommandExecutor ce) {
		getCommand(command).setExecutor(ce);
	}
	
	@SuppressWarnings("unused")
	private void addListener(Listener l) {
		Bukkit.getPluginManager().registerEvents(l, this);
	}
}
