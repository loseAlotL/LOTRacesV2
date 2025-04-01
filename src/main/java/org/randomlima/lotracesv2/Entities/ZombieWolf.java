package org.randomlima.lotracesv2.Entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import org.bukkit.entity.Player;
import org.randomlima.lotracesv2.Entities.Pathfinding.FollowPlayerGoal;

public class ZombieWolf extends Zombie{
    private final Player owner;
    public ZombieWolf(Level world, Player owner){
        super(EntityType.ZOMBIE, world);
        this.owner = owner;
        this.getAttribute(Attributes.SCALE).setBaseValue(0.9);
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(100);
        registerGoals();
    }

    @Override
    protected void registerGoals(){
        if(owner != null)return;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true));
        this.goalSelector.addGoal(2, new FollowPlayerGoal(this,1.5));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, LivingEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }
}
