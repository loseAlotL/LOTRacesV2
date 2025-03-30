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
            case "armor": return Attribute.GENERIC_ARMOR;
            case "armorToughness": return Attribute.GENERIC_ARMOR_TOUGHNESS;
            case "attackDamage": return Attribute.GENERIC_ATTACK_DAMAGE;
            case "attackKnockback": return Attribute.GENERIC_ATTACK_KNOCKBACK;
            case "attackSpeed": return Attribute.GENERIC_ATTACK_SPEED;
            case "knockbackResistance": return Attribute.GENERIC_KNOCKBACK_RESISTANCE;
            case "luck": return Attribute.GENERIC_LUCK;
            case "maxHealth": return Attribute.GENERIC_MAX_HEALTH;
            case "movementSpeed": return Attribute.GENERIC_MOVEMENT_SPEED;
            case "scale": return Attribute.GENERIC_SCALE;
            case "stepHeight": return Attribute.GENERIC_STEP_HEIGHT;
            case "jumpStrength": return Attribute.GENERIC_JUMP_STRENGTH;
            case "blockInteractionRange": return Attribute.PLAYER_BLOCK_INTERACTION_RANGE;
            case "entityInteractionRange": return Attribute.PLAYER_ENTITY_INTERACTION_RANGE;
            case "blockBreakSpeed": return Attribute.PLAYER_BLOCK_BREAK_SPEED;
            case "gravity": return Attribute.GENERIC_GRAVITY;
            case "safeFallDistance": return Attribute.GENERIC_SAFE_FALL_DISTANCE;
            case "fallDamageMultiplier": return Attribute.GENERIC_FALL_DAMAGE_MULTIPLIER;
            case "burningTime": return Attribute.GENERIC_BURNING_TIME;
            case "explosionKnockbackResistance": return Attribute.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE;
            case "miningEfficiency": return Attribute.PLAYER_MINING_EFFICIENCY;
            case "movementEfficiency": return Attribute.GENERIC_MOVEMENT_EFFICIENCY;
            case "oxygenBonus": return Attribute.GENERIC_OXYGEN_BONUS;
            case "sneakingSpeed": return Attribute.PLAYER_SNEAKING_SPEED;
            case "submergedMiningSpeed": return Attribute.PLAYER_SUBMERGED_MINING_SPEED;
            case "sweepingDamageRatio": return Attribute.PLAYER_SWEEPING_DAMAGE_RATIO;
            case "waterMovementEfficiency": return Attribute.GENERIC_WATER_MOVEMENT_EFFICIENCY;
            default: return null;
        }
    }
}