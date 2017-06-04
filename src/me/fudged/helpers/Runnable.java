package me.fudged.helpers;

import java.util.Random;

import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.fudged.helpers.storage.User;
import net.md_5.bungee.api.ChatColor;

public class Runnable extends BukkitRunnable {

	public Runnable(){
	}

	Random r = new Random();

	@Override
	public void run(){
		for(User u : Main.getUserManager().getUsers()){
			u.getPlayer().playSound(u.getPlayer().getLocation(), Sounds.LEVEL_UP.bukkitSound(), 0.75F, 1F);
			u.getPlayer().sendMessage(ChatColor.GOLD + "Helpers" + ChatColor.GRAY + " » " + ChatColor.YELLOW + "Benefits have just been given out!" + ChatColor.GRAY + " /helpers " + ChatColor.YELLOW + "to redeem");
			
			// Miners
			if(u.getMiners() > 0){
				if(Main.getItemsEditor().getMinerItems() != null){
					int i = r.nextInt(Main.getItemsEditor().getMinerItems().size());
					if(u.getInventory().firstEmpty() != -1){
						ItemStack item = Main.getItemsEditor().getMinerItems().get(i).clone();
						item.setAmount(item.getAmount() + u.getMiners());
						u.getInventory().setItem(u.getInventory().firstEmpty(), item);
					}
				}
			}
			// Farmers
			if(u.getFarmers() > 0){
				if(Main.getItemsEditor().getFarmerItems() != null){
					int i = r.nextInt(Main.getItemsEditor().getFarmerItems().size());
					if(u.getInventory().firstEmpty() != -1){
						ItemStack item = Main.getItemsEditor().getFarmerItems().get(i).clone();
						item.setAmount(item.getAmount() + u.getFarmers());
						u.getInventory().setItem(u.getInventory().firstEmpty(), item);
					}
				}
			}
			// Butchers
			if(u.getButchers() > 0){
				if(Main.getItemsEditor().getButcherItems() != null){
					int i = r.nextInt(Main.getItemsEditor().getButcherItems().size());
					if(u.getInventory().firstEmpty() != -1){
						ItemStack item = Main.getItemsEditor().getButcherItems().get(i).clone();
						item.setAmount(item.getAmount() + u.getButchers());
						u.getInventory().setItem(u.getInventory().firstEmpty(), item);
					}
				}
			}
			// Lumberjacks
			if(u.getLumberjacks() > 0){
				if(Main.getItemsEditor().getLumberjackItems() != null){
					int i = r.nextInt(Main.getItemsEditor().getLumberjackItems().size());
					if(u.getInventory().firstEmpty() != -1){
						ItemStack item = Main.getItemsEditor().getLumberjackItems().get(i).clone();
						item.setAmount(item.getAmount() + u.getLumberjacks());
						u.getInventory().setItem(u.getInventory().firstEmpty(), item);
					}
				}
			}
			// Excavators
			if(u.getExcavators() > 0){
				if(Main.getItemsEditor().getMinerItems() != null){
					int i = r.nextInt(Main.getItemsEditor().getExcavatorItems().size());
					if(u.getInventory().firstEmpty() != -1){
						ItemStack item = Main.getItemsEditor().getExcavatorItems().get(i).clone();
						item.setAmount(item.getAmount() + u.getExcavators());
						u.getInventory().setItem(u.getInventory().firstEmpty(), item);
					}
				}
			}
		}
	}

}
