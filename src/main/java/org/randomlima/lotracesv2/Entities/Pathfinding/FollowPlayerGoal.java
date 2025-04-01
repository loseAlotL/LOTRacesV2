package org.randomlima.lotracesv2.Entities.Pathfinding;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
public class FollowPlayerGoal extends Goal {
    double speed;
    PathfinderMob mob;
    Player player;
    double x, y, z;
    public FollowPlayerGoal(PathfinderMob mob, double speed){
        this.mob = mob;

        this.speed = speed;
    }
    @Override
    public boolean canUse() {
        if (player == null) player = mob.level().getNearestPlayer(mob, 5);
        return player != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.player != null && this.mob.distanceTo(this.player) > 3;
    }

    @Override
    public void start() {
    }

    @Override
    public void tick(){
        x = player.getX();
        y = player.getY();
        z = player.getZ();
        mob.getNavigation().moveTo(x, y, z, speed);
    }
}
