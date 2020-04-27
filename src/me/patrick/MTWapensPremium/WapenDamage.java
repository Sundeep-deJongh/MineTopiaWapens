package me.patrick.MTWapensPremium;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class WapenDamage {
    
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Snowball) {
            Snowball snowball = (Snowball) event.getDamager();
            Entity hitBySnowball = event.getEntity();
            //LivingEntity shooter = (LivingEntity) snowball.getShooter();
            if (hitBySnowball instanceof Player) {
                event.setDamage(Double.parseDouble((snowball.getCustomName())));
            }
        }
    }
}
