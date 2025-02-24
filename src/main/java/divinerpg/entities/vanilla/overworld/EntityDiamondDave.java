package divinerpg.entities.vanilla.overworld;

import divinerpg.entities.base.EntityDivineMerchant;
import divinerpg.registries.*;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class EntityDiamondDave extends EntityDivineMerchant {
    public EntityDiamondDave(EntityType<? extends EntityDivineMerchant> type, Level worldIn) {
        super(type, worldIn);
    }
    @Override
    public String[] getChatMessages() {
        return new String[] {
                "message.diamond_dave.isolation",
                "message.diamond_dave.cruel",
                "message.diamond_dave.dream",
                "message.diamond_dave.creative",
                "message.diamond_dave.exploit",
                "message.diamond_dave.mysterious"
        };
    }
    @Override
    protected void updateTrades() {
        MerchantOffers merchantoffers = this.getOffers();

        DivineTrades[] tradetrades = new DivineTrades[] {
                new EntityDivineMerchant.DivineTrades(new ItemStack(Items.DIAMOND, 16 + random.nextInt(8)), random.nextBoolean() ? new ItemStack(ItemRegistry.realmite_pickaxe.get()) : EnchantmentHelper.enchantItem(random, new ItemStack(ItemRegistry.realmite_pickaxe.get(), 1), random.nextInt(30), false), random.nextInt(3), 5),
                new EntityDivineMerchant.DivineTrades(new ItemStack(Items.COAL, 8 + random.nextInt(2)), new ItemStack(Items.TORCH, 16), random.nextInt(7), 5),
                new EntityDivineMerchant.DivineTrades(new ItemStack(Items.RAW_IRON, 4), new ItemStack(Items.GLOW_BERRIES, 2 + random.nextInt(14)), random.nextInt(7), 5),
                new EntityDivineMerchant.DivineTrades(new ItemStack(Blocks.DEEPSLATE, 32 + random.nextInt(32)), new ItemStack(Items.ARROW, 16), random.nextInt(3), 5),
                new EntityDivineMerchant.DivineTrades(new ItemStack(random.nextBoolean() ? Items.DIAMOND : Items.EMERALD, 8 + random.nextInt(2)), PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.NIGHT_VISION), random.nextInt(2), 5),
                new EntityDivineMerchant.DivineTrades(new ItemStack(Items.COAL, 4 + random.nextInt(12)), new ItemStack(BlockRegistry.divineLog.get(), 4 + random.nextInt(12)), random.nextInt(3), 5)
        };
        this.addOffersFromItemListings(merchantoffers, tradetrades, 5);
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.LIVESTOCK_MERCHANT.get();
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.LIVESTOCK_MERCHANT_HURT.get();
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.LIVESTOCK_MERCHANT_HURT.get();
    }

    public static boolean rules(EntityType<? extends Mob> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        BlockPos blockpos = pos.below();
        List<EntityDiamondDave> entityList = level.getEntitiesOfClass(EntityDiamondDave.class, new AABB(pos).inflate(32));
        return spawnType == MobSpawnType.SPAWNER || level.getBlockState(blockpos).isValidSpawn(level, blockpos, type) && entityList.isEmpty();
    }

}