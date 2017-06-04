package me.fudged.helpers.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.fudged.helpers.Main;

public class UserManager implements Listener{

	private List<User> users;

	public UserManager(){
		users = new ArrayList<>();
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.getPlugin());
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent e){
		addUser(e.getPlayer());
	}
	@EventHandler
	public void onClick(InventoryClickEvent e){
		if(!(e.getWhoClicked() instanceof Player)){
			return;
		}
		Player p = (Player) e.getWhoClicked();
		if(e.getClickedInventory().equals(getUser(p).getInventory())){
			if(e.getSlot() + 1 > 36 || e.getSlot() + 1 <= 9){
				e.setCancelled(true);
				return;
			}
		}
	}
			
	public User getUser(Player p){
		for(User u : users){
			if(u.getPlayer() == p) return u;
		}
		return null;
	}
	
	public void addUser(Player p){
		users.add(new User(p, miners(p), farmers(p), butchers(p), lumberjacks(p), excavators(p), inv(p)));
	}
	
	public void removeUser(Player p){
		users.remove(getUser(p));
	}
	
	public void reloadUser(Player p){
		removeUser(p);
		addUser(p);
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	public Integer miners(Player p){
		if(p.isOp()) return 10;
		for(int i = 1; i <= 10; i++){
			if(p.hasPermission("helpers.miners." + i)){
				return i;
			}
		}
		return 0;
	}
	
	public Integer farmers(Player p){
		if(p.isOp()) return 10;
		for(int i = 1; i <= 10; i++){
			if(p.hasPermission("helpers.farmers." + i)){
				return i;
			}
		}
		return 0;
	}
	
	public Integer butchers(Player p){
		if(p.isOp()) return 10;
		for(int i = 1; i <= 10; i++){
			if(p.hasPermission("helpers.butchers." + i)){
				return i;
			}
		}
		return 0;
	}
	
	public Integer lumberjacks(Player p){
		if(p.isOp()) return 10;
		for(int i = 1; i <= 10; i++){
			if(p.hasPermission("helpers.lumberjacks." + i)){
				return i;
			}
		}
		return 0;
	}
	
	public Integer excavators(Player p){
		if(p.isOp()) return 10;
		for(int i = 1; i <= 10; i++){
			if(p.hasPermission("helpers.excavators." + i)){
				return i;
			}
		}
		return 0;
	}
	
	public Inventory inv(Player p){
		Inventory inv = Bukkit.createInventory(null, 45, "Helpers");
		inv.setMaxStackSize(127);
		inv.setItem(44, createItem(p, Material.IRON_SPADE, "Excavators", "Amount:", excavators(p)));
		inv.setItem(43, filler()); 
		inv.setItem(42, createItem(p, Material.IRON_AXE, "Lumberjacks", "Amount:", lumberjacks(p)));
		inv.setItem(41, filler());
		inv.setItem(40, createItem(p, Material.IRON_SWORD, "Butchers", "Amount:", butchers(p)));
		inv.setItem(39, filler());
		inv.setItem(38, createItem(p, Material.IRON_HOE, "Farmers", "Amount:", farmers(p)));
		inv.setItem(37, filler());
		inv.setItem(36, createItem(p, Material.IRON_PICKAXE, "Miners", "Amount:", miners(p)));
		for(int i = 0; i < 9; i++){
			inv.setItem(i, filler());
		}
		p.updateInventory();
		
		return inv;
	}
	
	public ItemStack createItem(Player p, Material m, String name, String lore, Integer in){
		ItemStack i = new ItemStack(m);
		ItemMeta im = i.getItemMeta();
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im.setDisplayName(ChatColor.YELLOW + name);
		im.setLore(Arrays.asList(ChatColor.GRAY + lore + " " + in));
		i.setItemMeta(im);
		return i;
	}
	
	public ItemStack filler(){
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta im  = i.getItemMeta();
		im.setDisplayName(" ");
		i.setItemMeta(im);
		return i;
	}

}
