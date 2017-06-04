package me.fudged.helpers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import me.fudged.helpers.commands.Helpers;
import me.fudged.helpers.items.Files;
import me.fudged.helpers.items.Items;
import me.fudged.helpers.storage.UserManager;

public class Main extends JavaPlugin {

	private static UserManager usermanager;
	private static BukkitTask runnable;
	private static Files itemsFile;
	private static Items items;
	private static Integer time;

	@Override
	public void onEnable(){
		saveDefaultConfig();
		usermanager = new UserManager();
		itemsFile = new Files("items");
		items = new Items();
		time = getConfig().getInt("delay");
		getCommand("helpers").setExecutor(new Helpers());
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			usermanager.addUser(p);
		}

		runnable = new Runnable().runTaskTimer(getPlugin(), time * 1200L, time * 1200L);
	}

	@Override
	public void onDisable(){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			usermanager.removeUser(p);
		}
	}

	public static Plugin getPlugin(){
		return Bukkit.getServer().getPluginManager().getPlugin("Helpers");
	}

	public static UserManager getUserManager(){
		return usermanager;
	}
	
	public static BukkitTask getRunnable(){
		return runnable;
	}
	
	public static Files getItemsFile(){
		return itemsFile;
	}
	
	public static Items getItemsEditor(){
		return items;
	}
	
	public static Integer getDelay(){
		return time;
	}

}
