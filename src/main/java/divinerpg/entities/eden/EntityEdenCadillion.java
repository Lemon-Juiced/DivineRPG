package divinerpg.entities.eden;

import divinerpg.entities.base.*;
import divinerpg.registries.*;
import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.*;

public class EntityEdenCadillion extends EntityDivineMonster {
    public EntityEdenCadillion(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        this.xpReward = 40;
    }
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.3F;
    }
    @Override public boolean isAggressive() {return true;}
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.CADILLION.get();
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
    public float getWalkTargetValue(BlockPos pos, LevelReader reader) {
        return 0.0F;
    }
}
