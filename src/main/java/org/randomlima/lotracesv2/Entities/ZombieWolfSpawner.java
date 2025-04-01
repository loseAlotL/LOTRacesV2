package org.randomlima.lotracesv2.Entities;

import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;

public class ZombieWolfSpawner {
    public static void spawnZombieWolf(Player owner, Location location) {
        Level nmsWorld = ((CraftWorld) location.getWorld()).getHandle();
        System.out.println("SPANWING ZOMBIE AHHHHHHHHHHHHHHHHHH" + owner.getName());
        ZombieWolf zombieWolf = new ZombieWolf(nmsWorld, owner);
        zombieWolf.setPos(location.getX(), location.getY(), location.getZ());

        nmsWorld.addFreshEntity(zombieWolf);
    }
}
