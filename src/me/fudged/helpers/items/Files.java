package me.fudged.helpers.items;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import me.fudged.helpers.Main;

public class Files {
	
	public File file;
	public FileConfiguration config;

	public Files(String s){
		if(!Main.getPlugin().getDataFolder().exists()){
			Main.getPlugin().getDataFolder().mkdir();
		}

		file = new File(Main.getPlugin().getDataFolder(), s + ".yml");

		if(!file.exists()){
			try{
				file.createNewFile();
			} catch (Exception e){
				
			}
		}
		
		config = YamlConfiguration.loadConfiguration(file);
		
		if(getSection("miners") == null){
			createSection("miners");
			set("miners." + 0, new ItemStack(Material.DIAMOND_PICKAXE));
		}
		if(getSection("farmers") == null){
			createSection("farmers");
			set("farmers." + 0, new ItemStack(Material.DIAMOND_HOE));
		}
		if(getSection("butchers") == null){
			createSection("butchers");
			set("butchers." + 0, new ItemStack(Material.DIAMOND_SWORD));
		}
		if(getSection("lumberjacks") == null){
			createSection("lumberjacks");
			set("lumberjacks." + 0, new ItemStack(Material.DIAMOND_AXE));
		}
		if(getSection("excavators") == null){
			createSection("excavators");
			set("excavators." + 0, new ItemStack(Material.DIAMOND_SPADE));
		}
		
	}


	public void set(String p, Object o){
		config.set(p, o);
		try{
			config.save(file);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void createSection(String p){
		config.createSection(p);
		try{
			config.save(file);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String p){
		return (T) config.get(p);
	}
	
	public ConfigurationSection getSection(String p){
		return config.getConfigurationSection(p);
	}

}
