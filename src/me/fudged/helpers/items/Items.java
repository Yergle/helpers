package me.fudged.helpers.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.fudged.helpers.Main;

public class Items implements Listener {

	private HashMap<String, Inventory> editor;
	private List<ItemStack> miners = new ArrayList<>();
	private List<ItemStack> farmers = new ArrayList<>();
	private List<ItemStack> butchers = new ArrayList<>();
	private List<ItemStack> lumberjacks = new ArrayList<>();
	private List<ItemStack> excavators = new ArrayList<>();

	public Items(){
		editor = new HashMap<>();
		loadInventories();
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.getPlugin());
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e){
		if(editor.containsKey(e.getPlayer().getName()) && editor.containsValue(e.getInventory())){
			Main.getItemsFile().set(e.getInventory().getName().toLowerCase(), null);
			Main.getItemsFile().createSection(e.getInventory().getName().toLowerCase());
			for(int i = 0; i < e.getInventory().getSize(); i++){
				if(e.getInventory().getItem(i) != null){
					Main.getItemsFile().set(e.getInventory().getName().toLowerCase() + "." + i, e.getInventory().getItem(i));
				}
			}
			loadInventories();
		}
	}

	public Inventory editorInv(String name, String inv){
		Inventory i = Bukkit.createInventory(null, 54, inv);
		if(inv.equalsIgnoreCase("miners")){
			for(ItemStack items : miners){
				i.addItem(items);
			}
		}
		if(inv.equalsIgnoreCase("farmers")){
			for(ItemStack items : farmers){
				i.addItem(items);
			}
		}
		if(inv.equalsIgnoreCase("butchers")){
			for(ItemStack items : butchers){
				i.addItem(items);
			}
		}
		if(inv.equalsIgnoreCase("lumberjacks")){
			for(ItemStack items : lumberjacks){
				i.addItem(items);
			}
		}
		if(inv.equalsIgnoreCase("excavators")){
			for(ItemStack items : excavators){
				i.addItem(items);
			}
		}
		editor.put(name, i);
		return i;
	}

	public HashMap<String, Inventory> getEditorMap(){
		return editor;
	}
	
	public List<ItemStack> getMinerItems(){
		return miners;
	}
	
	public List<ItemStack> getFarmerItems(){
		return farmers;
	}
	
	public List<ItemStack> getButcherItems(){
		return butchers;
	}
	
	public List<ItemStack> getLumberjackItems(){
		return lumberjacks;
	}
	
	public List<ItemStack> getExcavatorItems(){
		return excavators;
	}
	
	public void loadInventories(){
		if(Main.getItemsFile().get("miners") != null){
			miners.clear();
			for(int i = 0; i < 54; i++){
				if(Main.getItemsFile().config.getItemStack("miners." + i) != null){
					miners.add(Main.getItemsFile().config.getItemStack("miners." + i));
				}
			}
		}
		
		if(Main.getItemsFile().get("farmers") != null){
			farmers.clear();
			for(int i = 0; i < 54; i++){
				if(Main.getItemsFile().config.getItemStack("farmers." + i) != null){
					farmers.add(Main.getItemsFile().config.getItemStack("farmers." + i));
				}
			}
		}
		
		if(Main.getItemsFile().get("butchers") != null){
			butchers.clear();
			for(int i = 0; i < 54; i++){
				if(Main.getItemsFile().config.getItemStack("butchers." + i) != null){
					butchers.add(Main.getItemsFile().config.getItemStack("butchers." + i));
				}
			}
		}
		
		if(Main.getItemsFile().get("lumberjacks") != null){
			lumberjacks.clear();
			for(int i = 0; i < 54; i++){
				if(Main.getItemsFile().config.getItemStack("lumberjacks." + i) != null){
					lumberjacks.add(Main.getItemsFile().config.getItemStack("lumberjacks." + i));
				}
			}
		}
		
		if(Main.getItemsFile().get("excavators") != null){
			excavators.clear();
			for(int i = 0; i < 54; i++){
				if(Main.getItemsFile().config.getItemStack("excavators." + i) != null){
					excavators.add(Main.getItemsFile().config.getItemStack("excavators." + i));
				}
			}
		}
	}

}
