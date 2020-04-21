package me.patrick.MTWapensPremium;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class WapenHandler implements Listener {
    Main plugin;

    public WapenHandler(Main instance) {
        this.plugin = instance;
    }


    @EventHandler
    public void gun1(final PlayerInteractEvent e) {
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR))
            return;
        Player p = e.getPlayer();
        for (String wconfig : plugin.getConfig().getConfigurationSection("Wapens.").getKeys(false)) {
            ItemStack wapen = new ItemStack(Material.valueOf(String.valueOf(plugin.getConfig().getString("Wapens." + wconfig + ".type"))));
            if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Wapens." + wconfig + ".name")))) {
                if (e.getItem().getDurability() == 0) return;
                ItemStack kogel = new ItemStack(Material.valueOf(String.valueOf(plugin.getConfig().getString( "Wapens." + wconfig + ".kogeltype"))));
                kogel = NBTEditor.set(kogel, plugin.getConfig().getString( "Wapens." + wconfig + ".kogelNBTtag"), plugin.getConfig().getString( "Wapens." + wconfig + ".kogelcustom"));
                ItemMeta kogelmeta = kogel.getItemMeta();
                kogelmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString( "Wapens." + wconfig + ".kogelname")));
                kogel.setItemMeta(kogelmeta);

                if (!(p.getInventory().containsAtLeast(kogel, 1))) {
                    p.sendMessage(ChatColor.RED + "Jij hebt geen kogels");
                    return;

                } else {

                    int damage = e.getItem().getDurability() - 1;
                    e.getItem().setDurability((short) damage);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Durability: " + e.getItem().getDurability());
                    p.getInventory().removeItem(kogel);
                    Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                    s.setCustomName(plugin.getConfig().getString("Wapens." + wconfig + ".Damage"));
                    s.setVelocity(s.getVelocity().multiply(2D));


                }

            }
        }
    }
}
