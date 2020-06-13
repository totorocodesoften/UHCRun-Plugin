package com.totocode.uhcrun;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UHCListener implements Listener {
    Main main;
    public UHCListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(event.getBlock().getType() == Material.TNT){
            event.getBlock().getWorld().getBlockAt(event.getBlock().getLocation()).setType(Material.AIR);
            event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.PRIMED_TNT);
        }
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event){
        if(event.getRecipe() == null) return;
        if(event.getRecipe().getResult().getType() == Material.WOOD_PICKAXE){
            event.getInventory().setResult(new ItemStack(Material.STONE_PICKAXE, 1));
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        switch(event.getBlock().getType()){
            default:
                break;
            case LOG_2:
                log(event);
                break;
            case LOG:
                log(event);
                break;
            case GOLD_ORE:
                event.setDropItems(false);
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, 2));
                break;
            case IRON_ORE:
                event.setDropItems(false);
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, 2));
                break;
            case CACTUS:
                event.setCancelled(false);
                int i = 0;
                event.setDropItems(false);
                for(int w = 0; w <= 255; w++){
                    Location loc = event.getBlock().getLocation();
                    if(loc.getWorld().getBlockAt((int)loc.getX(), w, (int)loc.getZ()).getType() == Material.CACTUS){
                        i++;
                        loc.getWorld().getBlockAt((int)loc.getX(), w, (int)loc.getZ()).setType(Material.AIR);
                    }
                }
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.LOG, i));
                break;
            case GRAVEL:
                event.setDropItems(false);
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.ARROW, 1));
                break;
            case SAND:
                event.setDropItems(false);
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.GLASS, 1));
                break;
            case COAL_ORE:
                event.setDropItems(false);
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.TORCH, 3));
                break;
            case DEAD_BUSH:
                event.setDropItems(false);
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.BREAD, 1));
                break;
            case STONE:
                event.setDropItems(false);
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.COBBLESTONE, 1));
                break;
            case BROWN_MUSHROOM:
                event.setDropItems(false);
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.MUSHROOM_SOUP, 2));
                break;
            case OBSIDIAN:
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 30 * 20, 1));
                break;
        }
    }

    public void log(BlockBreakEvent event){
        int i = 0;
        event.setDropItems(false);
        event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
        for(int w = 0; w <= 255; w++){
            Location loc = event.getBlock().getLocation();
            if(loc.getWorld().getBlockAt((int)loc.getX(), w, (int)loc.getZ()).getType() == Material.LOG || loc.getWorld().getBlockAt((int)loc.getX(), w, (int)loc.getZ()).getType() == Material.LOG_2){
                i++;
                loc.getWorld().getBlockAt((int)loc.getX(), w, (int)loc.getZ()).setType(Material.AIR);
            }
        }
        event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.LOG, i));
    }
    //ONPLACE TNT EXPLODE
    @EventHandler
    public void onKill(EntityDeathEvent event){
        Entity entity = event.getEntity();
        Entity killer = event.getEntity().getKiller();
        if(killer instanceof Player) {
            if (entity.getType() == EntityType.SQUID) {
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.COOKED_FISH, 1));
            }
            if (entity.getType() == EntityType.ZOMBIE) {
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.COOKED_BEEF, 1));
            }
            if (entity.getType() == EntityType.CREEPER) {
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.TNT, 1));
            }
            if (entity.getType() == EntityType.SHEEP) {
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.COOKED_MUTTON, 1));
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.LEATHER, 1));
            }
            if (entity.getType() == EntityType.COW) {
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.COOKED_BEEF, 1));
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.LEATHER, 1));
            }
            if (entity.getType() == EntityType.RABBIT) {
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.COOKED_RABBIT, 1));
            }
            if (entity.getType() == EntityType.CHICKEN) {
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.COOKED_CHICKEN, 1));
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.ARROW, 1));
            }
            if (entity.getType() == EntityType.PIG) {
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.GRILLED_PORK, 1));
                entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.LEATHER, 1));
            }
        }
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event){
        if(event.getItem().getType() == Material.GOLDEN_APPLE){
            event.getPlayer().setHealth(event.getPlayer().getHealth() + 8);
        }
    }
}
