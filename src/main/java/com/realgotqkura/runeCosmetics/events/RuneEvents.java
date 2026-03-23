package com.realgotqkura.runeCosmetics.events;

import com.realgotqkura.runeCosmetics.RuneCosmetics;
import com.realgotqkura.runeCosmetics.utils.RandomUtils;
import com.realgotqkura.runeCosmetics.utils.Rune;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RuneEvents implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(!RandomUtils.hasRune(player.getInventory().getItemInMainHand())) return;

        if(!item.getType().toString().contains("AXE") && !item.getType().toString().contains("SHOVEL")) return;

        Rune rune = RandomUtils.getItemRune(player.getInventory().getItemInMainHand());

        Location blockLoc = event.getBlock().getLocation().clone().add(0.5,0.5,0.5);
        ThreadLocalRandom rndm = ThreadLocalRandom.current();
        if(rune == Rune.REDSTONE){
            for(int i = 0; i < 5; i++){
                blockLoc.getWorld().spawnParticle(rune.getParticle(), blockLoc, 2, rndm.nextFloat(-0.7f, 0.7f), rndm.nextFloat(-0.7f, 0.7f), rndm.nextFloat(-0.7f, 0.7f), 2, new Particle.DustOptions(Color.fromRGB(255, 0, 0), 2));

            }
        }
    }



    private List<Material> possibleTillingBlocks = new ArrayList<>(
            Arrays.asList(Material.GRASS_BLOCK, Material.DIRT, Material.DIRT_PATH)
    );

    private static HashMap<Projectile, Rune> projectileRuneHashMap = new HashMap<>();

    @EventHandler
    public void launch(ProjectileLaunchEvent event){
        if(!(event.getEntity().getShooter() instanceof LivingEntity)) return;
        ItemStack item = ((LivingEntity) event.getEntity().getShooter()).getEquipment().getItemInMainHand();

        if(!RandomUtils.hasRune(item)) return;

        if(!item.getType().toString().contains("BOW") && !item.getType().toString().contains("CROSSBOW")) return;

        Rune rune = RandomUtils.getItemRune(item);
        projectileRuneHashMap.put(event.getEntity(), rune);
    }

    @EventHandler
    public void hit(ProjectileHitEvent event){
        if(projectileRuneHashMap.keySet().contains(event.getEntity())){
            projectileRuneHashMap.remove(event.getEntity());
        }
    }

    @EventHandler
    public void attack(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof LivingEntity)) return;
        ItemStack item = ((LivingEntity) event.getDamager()).getEquipment().getItemInMainHand();

        if(!RandomUtils.hasRune(item)) return;

        if(!item.getType().toString().contains("SWORD") && !item.getType().toString().contains("AXE")) return;

        Rune rune = RandomUtils.getItemRune(item);
        Location attackLoc = event.getEntity().getLocation().clone().add(0.5,0.5,0.5);
        ThreadLocalRandom rndm = ThreadLocalRandom.current();
        if(rune == Rune.REDSTONE){
            for(int i = 0; i < 5; i++){
                attackLoc.getWorld().spawnParticle(rune.getParticle(), attackLoc, 2, rndm.nextFloat(-0.7f, 0.7f), rndm.nextFloat(-0.7f, 0.7f), rndm.nextFloat(-0.7f, 0.7f), 2, new Particle.DustOptions(Color.fromRGB(255, 0, 0), 2));

            }
        }
    }

    @EventHandler
    public void till(PlayerInteractEvent event){
        if(event.getClickedBlock() == null) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(!RandomUtils.hasRune(player.getInventory().getItemInMainHand())) return;

        if(!item.getType().toString().contains("HOE")) return;

        if(!possibleTillingBlocks.contains(event.getClickedBlock().getType())) return;

        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Rune rune = RandomUtils.getItemRune(item);
        Location blockLoc = event.getClickedBlock().getLocation().clone().add(0.5,1,0.5);
        ThreadLocalRandom rndm = ThreadLocalRandom.current();
        if(rune == Rune.REDSTONE){
            for(int i = 0; i < 5; i++){
                blockLoc.getWorld().spawnParticle(rune.getParticle(), blockLoc, 2, rndm.nextFloat(-0.7f, 0.7f), rndm.nextFloat(-0.7f, 0.7f), rndm.nextFloat(-0.7f, 0.7f), 2, new Particle.DustOptions(Color.fromRGB(255, 0, 0), 2));

            }
        }
    }


    @EventHandler
    public void updateItemLoreAndName(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(!RandomUtils.hasRune(item)) return;


        Rune rune = RandomUtils.getItemRune(item);
        ItemStack newItem = RandomUtils.updateItem(item, rune);

        if(newItem == null) return;

        player.getInventory().setItemInMainHand(newItem);
    }

    public static void runProjectiles(RuneCosmetics plugin){
        new BukkitRunnable(){

            @Override
            public void run() {
                projectileRuneHashMap.keySet().forEach(k ->{
                    Rune rune = projectileRuneHashMap.get(k);

                    Location attackLoc = k.getLocation().clone().add(0.5,0,0.5);
                    ThreadLocalRandom rndm = ThreadLocalRandom.current();
                    if(rune == Rune.REDSTONE){
                        for(int i = 0; i < 5; i++){
                            attackLoc.getWorld().spawnParticle(rune.getParticle(), attackLoc, 2, rndm.nextFloat(-0.7f, 0.7f), rndm.nextFloat(-0.7f, 0.7f), rndm.nextFloat(-0.7f, 0.7f), 2, new Particle.DustOptions(Color.fromRGB(255, 0, 0), 1));

                        }
                    }
                });
            }
        }.runTaskTimer(plugin, 1, 1);
    }
}
