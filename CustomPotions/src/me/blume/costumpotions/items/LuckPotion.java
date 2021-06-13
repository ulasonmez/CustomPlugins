package me.blume.costumpotions.items;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.blume.costumpotions.Main;

public class LuckPotion implements Listener{

	@SuppressWarnings("unused")
	private Main plugin;
	public LuckPotion(Main plugin) {
		this.plugin=plugin;
	}
	public static ItemStack luckPotion() {
		ItemStack potion = new ItemStack(Material.HONEY_BOTTLE);
		ItemMeta meta = potion.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_BLUE+"LuckPotion");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("You will have luck for 5 minutes");
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
		checkCraft(luckPotion(),inv,new HashMap<Integer,ItemStack>(){{
			put(3,new ItemStack(Material.HEART_OF_THE_SEA));
			put(4,new ItemStack(Material.DIAMOND_SHOVEL));
			put(5,new ItemStack(Material.DIAMOND_PICKAXE));
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
	public void consumeCreativePotion(PlayerItemConsumeEvent event) {
		if(event.getItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_BLUE+"LuckPotion")) {
			Player player = event.getPlayer();
			player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,5*60*20,1));
		}
	}
}
