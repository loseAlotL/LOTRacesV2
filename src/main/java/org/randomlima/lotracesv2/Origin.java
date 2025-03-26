package org.randomlima.lotracesv2;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.randomlima.lotracesv2.Managers.ODSManager;

public class Origin {
    private final LOTRacesV2 plugin;
    private ODSManager odsManager;
    //private final String data;
    private String name; // default:
    private double attackDamage; // default: 1 (0~2048)
    private double attackKnockback; // default: 0 (0~5)
    //private double attackReach; // default: 2.5 (0~6)
    private double attackSpeed; // default: 4 (0~1024)
    private double knockbackResistance; // default: 0 (0~1)
    private double luck; // default: 0 (-1024~1024)
    private double maxHealth; // default: 20 (0~1024)
    private double movementSpeed; // default: 0.1 (0~1024)
    private double scale; // default: 1 (0.0625~16)
    private double stepHeight; // default: 0.6 (0~10)
    private double jumpStrength; // default: 0.42 (0~32)
    private double blockInteractionRange; // default: 4.5 (0~64)
    private double entityInteractionRange; // default: 3 (0~64)
    private double blockBreakSpeed; // default: 1 (0~1024)
    private double gravity; // default: 0.08 (-1~1)
    private double safeFallDistance; // default: 3 (-1024~1024)
    private double fallDamageMultiplier; // default: 1 (0~100)
    private double burningTime; // default: 1 (0~1024)
    private double explosionKnockbackResistance; // default: 0 (0~1)
    private double miningEfficiency; // default: 0 (0~1024)
    private double movementEfficiency; // default: 0 (0~1)
    private double oxygenBonus; // default: 0 (0~1024)
    private double sneakingSpeed; // default: 0.3 (0~1)
    private double submergedMiningSpeed; // default: 0.2 (0~20)
    private double sweepingDamageRatio; // default: 0 (0~1)
    private double waterMovementEfficiency; // default: 0 (0~1)

    public Origin(LOTRacesV2 plugin, String name){
        this.name = name;
        this.plugin = plugin;
        odsManager = new ODSManager(plugin);

        load();
    }
    public void load(){
        applyValues();
    }
    public void applyPlayer(Player player){
        AttributeInstance ad = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        if (ad != null) ad.setBaseValue(attackDamage);

        AttributeInstance ak = player.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK);
        if (ak != null) ak.setBaseValue(attackKnockback);

        AttributeInstance as = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (as != null) as.setBaseValue(attackSpeed);

        AttributeInstance kr = player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        if (kr != null) kr.setBaseValue(knockbackResistance);

        AttributeInstance lk = player.getAttribute(Attribute.GENERIC_LUCK);
        if (lk != null) lk.setBaseValue(luck);

        AttributeInstance mh = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (mh != null) mh.setBaseValue(maxHealth);

        AttributeInstance ms = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if (ms != null) ms.setBaseValue(movementSpeed);

        AttributeInstance s = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if (s != null) s.setBaseValue(scale);

        AttributeInstance sh = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if (sh != null) sh.setBaseValue(stepHeight);

        AttributeInstance js = player.getAttribute(Attribute.GENERIC_JUMP_STRENGTH);
        if (js != null) js.setBaseValue(jumpStrength);

        AttributeInstance bir = player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE);
        if (bir != null) bir.setBaseValue(blockInteractionRange);

        AttributeInstance eer = player.getAttribute(Attribute.PLAYER_ENTITY_INTERACTION_RANGE);
        if (eer != null) eer.setBaseValue(entityInteractionRange);

        AttributeInstance bbs = player.getAttribute(Attribute.PLAYER_BLOCK_BREAK_SPEED);
        if (bbs != null) bbs.setBaseValue(blockBreakSpeed);

        AttributeInstance g = player.getAttribute(Attribute.GENERIC_GRAVITY);
        if (g != null) g.setBaseValue(gravity);

        AttributeInstance sfd = player.getAttribute(Attribute.GENERIC_SAFE_FALL_DISTANCE);
        if (sfd != null) sfd.setBaseValue(safeFallDistance);

        AttributeInstance fdm = player.getAttribute(Attribute.GENERIC_FALL_DAMAGE_MULTIPLIER);
        if (fdm != null) fdm.setBaseValue(fallDamageMultiplier);

        AttributeInstance bt = player.getAttribute(Attribute.GENERIC_BURNING_TIME);
        if (bt != null) bt.setBaseValue(burningTime);

        AttributeInstance ekr = player.getAttribute(Attribute.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE);
        if (ekr != null) ekr.setBaseValue(explosionKnockbackResistance);

        AttributeInstance me = player.getAttribute(Attribute.PLAYER_MINING_EFFICIENCY);
        if (me != null) me.setBaseValue(miningEfficiency);

        AttributeInstance mef = player.getAttribute(Attribute.GENERIC_MOVEMENT_EFFICIENCY);
        if (mef != null) mef.setBaseValue(movementEfficiency);

        AttributeInstance ob = player.getAttribute(Attribute.GENERIC_OXYGEN_BONUS);
        if (ob != null) ob.setBaseValue(oxygenBonus);

        AttributeInstance ss = player.getAttribute(Attribute.PLAYER_SNEAKING_SPEED);
        if (ss != null) ss.setBaseValue(sneakingSpeed);

        AttributeInstance sms = player.getAttribute(Attribute.PLAYER_SUBMERGED_MINING_SPEED);
        if (sms != null) sms.setBaseValue(submergedMiningSpeed);

        AttributeInstance sdr = player.getAttribute(Attribute.PLAYER_SWEEPING_DAMAGE_RATIO);
        if (sdr != null) sdr.setBaseValue(sweepingDamageRatio);

        AttributeInstance wme = player.getAttribute(Attribute.GENERIC_WATER_MOVEMENT_EFFICIENCY);
        if (wme != null) wme.setBaseValue(waterMovementEfficiency);

    }
    public void applyValues(){
        this.attackDamage = odsManager.getDouble(name, "attackDamage");
        this.attackKnockback = odsManager.getDouble(name, "attackKnockback");
        //this.attackReach = odsManager.getDouble(name, "attackReach");
        this.attackSpeed = odsManager.getDouble(name, "attackSpeed");
        this.knockbackResistance = odsManager.getDouble(name, "knockbackResistance");
        this.luck = odsManager.getDouble(name, "luck");
        this.maxHealth = odsManager.getDouble(name, "maxHealth");
        this.movementSpeed = odsManager.getDouble(name, "movementSpeed");
        this.scale = odsManager.getDouble(name, "scale");
        this.stepHeight = odsManager.getDouble(name, "stepHeight");
        this.jumpStrength = odsManager.getDouble(name, "jumpStrength");
        this.blockInteractionRange = odsManager.getDouble(name, "blockInteractionRange");
        this.entityInteractionRange = odsManager.getDouble(name, "entityInteractionRange");
        this.blockBreakSpeed = odsManager.getDouble(name, "blockBreakSpeed");
        this.gravity = odsManager.getDouble(name, "gravity");
        this.safeFallDistance = odsManager.getDouble(name, "safeFallDistance");
        this.fallDamageMultiplier = odsManager.getDouble(name, "fallDamageMultiplier");
        this.burningTime = odsManager.getDouble(name, "burningTime");
        this.explosionKnockbackResistance = odsManager.getDouble(name, "explosionKnockbackResistance");
        this.miningEfficiency = odsManager.getDouble(name, "miningEfficiency");
        this.movementEfficiency = odsManager.getDouble(name, "movementEfficiency");
        this.oxygenBonus = odsManager.getDouble(name, "oxygenBonus");
        this.sneakingSpeed = odsManager.getDouble(name, "sneakingSpeed");
        this.submergedMiningSpeed = odsManager.getDouble(name, "submergedMiningSpeed");
        this.sweepingDamageRatio = odsManager.getDouble(name, "sweepingDamageRatio");
        this.waterMovementEfficiency = odsManager.getDouble(name, "waterMovementEfficiency");
    }
}
