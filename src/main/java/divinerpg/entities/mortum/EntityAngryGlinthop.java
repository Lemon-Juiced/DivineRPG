package divinerpg.entities.mortum;

import divinerpg.entities.base.*;
import divinerpg.registries.*;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.*;

public class EntityAngryGlinthop extends EntityDivineMonster {
    public EntityAngryGlinthop(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        xpReward=40;
    }
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.4F;
    }
    @Override public boolean fireImmune() {return true;}
    @Override public boolean isAggressive() {return true;}
    @Override
    public boolean doHurtTarget(Entity entity) {
        return entity.hurt(DamageSource.mobAttack(this), 12);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.HISS.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.GROWL_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.GROWL_HURT.get();
    }
    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader world) {
        return 0.0F;
    }

}