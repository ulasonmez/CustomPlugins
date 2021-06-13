package me.blume.costumpotions.items;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.blume.costumpotions.Main;
import net.md_5.bungee.api.ChatColor;

public class FlyPotion implements Listener{
	private Main plugin;
	public FlyPotion(Main plugin) {
		this.plugin=plugin;
	}
	
	public static ItemStack flightPotion() {
		ItemStack potion = new ItemStack(Material.HONEY_BOTTLE);
		ItemMeta meta = potion.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW+"FlightPotion");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("You will be able to fly for 3 minutes after drinking this");
		meta.addEnchant(Enchantment.DURABILITY, 100, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setLore(lore);
		potion.setItemMeta(meta);
		return potion;
	}
	@SuppressWarnings("serial")
	@EventHandler
	public void craftstick(PrepareItemCraftEvent event) {
		CraftingInventory inv = event.getInventory();
		if(inv.getMatrix().length<9) return;
		checkCraft(flightPotion(),inv,new HashMap<Integer,ItemStack>(){{
			put(3,new ItemStack(Material.FEATHER));
			put(4,new ItemStack(Material.CLAY));
			put(5,new ItemStack(Material.PHANTOM_MEMBRANE));
		}});
	}
	public void checkCraft(ItemStack result,CraftingInventory inv,HashMap<Integer, ItemStack> ingredients) {
		ItemStack[] matrix = inv.getMatrix();
		for(int i =0;i<9;i++) {
			if(ingredients.containsKey(i)) {
				if(matrix[i]==null || !matrix[i].equals(ingredients.get(i))) {
					return;
				}
			}
			else {
				if(matrix[i]!=null) {
					return;
				}
			}
		}
		inv.setResult(result);
	}
	@EventHandler
	public void consumeFlightPotion(PlayerItemConsumeEvent event) {

		if(event.getItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW+"FlightPotion")) {
			Player player = event.getPlayer();
			player.setAllowFlight(true);
			player.setFlySpeed(0.3F);
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				@Override
				public void run() {
					player.setAllowFlight(false);
					player.setFlySpeed(0.1F);
				}
			}, 3*60*20L);
		}
	}

}
