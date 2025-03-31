package org.randomlima.lotracesv2;


import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.randomlima.lotracesv2.Managers.ODSManager;

import java.util.logging.Level;
import java.util.Map;
import java.util.HashMap;

public class Origin {
    private final LOTRacesV2 plugin;
    private ODSManager odsManager;
    private String name;
    private final Map<String, Double> attributes = new HashMap<>();
    private final Map<String, Double> debuffAttributes = new HashMap<>();

    public Origin(LOTRacesV2 plugin, String name){
        this.name = name;
        this.plugin = plugin;
        odsManager = new ODSManager(plugin);
        load();
    }

    public void load(){
        applyValues();
    }

    public String getname(){
        return name;
    }

    public void applyValues(){
        try {
            String[] attributeKeys = {
                    "armor", "armorToughness", "attackDamage", "attackKnockback", "attackSpeed",
                    "knockbackResistance", "luck", "maxHealth", "movementSpeed", "scale", "stepHeight",
                    "jumpStrength", "blockInteractionRange", "entityInteractionRange", "blockBreakSpeed",
                    "gravity", "safeFallDistance", "fallDamageMultiplier", "burningTime", "explosionKnockbackResistance",
                    "miningEfficiency", "movementEfficiency", "oxygenBonus", "sneakingSpeed", "submergedMiningSpeed",
                    "sweepingDamageRatio", "waterMovementEfficiency"
            };

            for (String key : attributeKeys) {
                attributes.put(key, odsManager.getAttribute(name, key));
                debuffAttributes.put(key, odsManager.getDebuffAttribute(name, key));
            }
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, "Failed to apply values: " + e.getMessage());
            Bukkit.getLogger().log(Level.SEVERE, "This may be due to missing values. Make sure you included everything.");
        }
    }

    public void applyPlayer(Player player) {
        for (Map.Entry<String, Double> entry : attributes.entrySet()) {
            AttributeInstance attributeInstance = player.getAttribute(getBukkitAttribute(entry.getKey()));
            if (attributeInstance != null) {
                attributeInstance.setBaseValue(entry.getValue());
            }
        }
    }

    public void applyPlayerDebuff(Player player) {
        for (Map.Entry<String, Double> entry : debuffAttributes.entrySet()) {
            AttributeInstance attributeInstance = player.getAttribute(getBukkitAttribute(entry.getKey()));
            if (attributeInstance != null) {
                attributeInstance.setBaseValue(entry.getValue());
            }
        }
    }

    private Attribute getBukkitAttribute(String key) {
        switch (key) {
            case "armor": return Attribute.ARMOR;
            case "armorToughness": return Attribute.ARMOR_TOUGHNESS;
            case "attackDamage": return Attribute.ATTACK_DAMAGE;
            case "attackKnockback": return Attribute.ATTACK_KNOCKBACK;
            case "attackSpeed": return Attribute.ATTACK_SPEED;
            case "knockbackResistance": return Attribute.KNOCKBACK_RESISTANCE;
            case "luck": return Attribute.LUCK;
            case "maxHealth": return Attribute.MAX_HEALTH;
            case "movementSpeed": return Attribute.MOVEMENT_SPEED;
            case "scale": return Attribute.SCALE;
            case "stepHeight": return Attribute.STEP_HEIGHT;
            case "jumpStrength": return Attribute.JUMP_STRENGTH;
            case "blockInteractionRange": return Attribute.BLOCK_INTERACTION_RANGE;
            case "entityInteractionRange": return Attribute.ENTITY_INTERACTION_RANGE;
            case "blockBreakSpeed": return Attribute.BLOCK_BREAK_SPEED;
            case "gravity": return Attribute.GRAVITY;
            case "safeFallDistance": return Attribute.SAFE_FALL_DISTANCE;
            case "fallDamageMultiplier": return Attribute.FALL_DAMAGE_MULTIPLIER;
            case "burningTime": return Attribute.BURNING_TIME;
            case "explosionKnockbackResistance": return Attribute.EXPLOSION_KNOCKBACK_RESISTANCE;
            case "miningEfficiency": return Attribute.MINING_EFFICIENCY;
            case "movementEfficiency": return Attribute.MOVEMENT_EFFICIENCY;
            case "oxygenBonus": return Attribute.OXYGEN_BONUS;
            case "sneakingSpeed": return Attribute.SNEAKING_SPEED;
            case "submergedMiningSpeed": return Attribute.SUBMERGED_MINING_SPEED;
            case "sweepingDamageRatio": return Attribute.SWEEPING_DAMAGE_RATIO;
            case "waterMovementEfficiency": return Attribute.WATER_MOVEMENT_EFFICIENCY;
            default: return null;
        }
    }
}