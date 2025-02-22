package divinerpg.entities.vethea;

import divinerpg.entities.base.EntityDivineMonster;
import divinerpg.entities.projectile.EntityKazroticShot;
import divinerpg.registries.*;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.level.Level;

public class EntityKazrotic extends EntityDivineMonster implements RangedAttackMob {
	
    public EntityKazrotic(EntityType<? extends Monster> type, Level worldIn) {
		super(type, worldIn);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.8F;
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new RangedAttackGoal(this, 0.25F, 15, 40.0F));
    	super.registerGoals();
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (isAlive() && getTarget() != null && !level.isClientSide) {
            EntityKazroticShot projectile = new EntityKazroticShot(EntityRegistry.KAZROTIC_SHOT.get(), target, level);
            double d0 = getTarget().getX() - this.getX();
            double d1 = getTarget().getY(0.3333333333333333D) - projectile.getY();
            double d2 = getTarget().getZ() - this.getZ();
            double d3 = Math.sqrt((float) (d0 * d0 + d2 * d2));
            projectile.shoot(d0, d1 + d3 * .2 - .2, d2, 1.6F, 0.8F);
            this.level.addFreshEntity(projectile);
        }
    }

    @Override
    public boolean hurt(DamageSource par1, float par2) {
        if(par1.isExplosion()) return false;
        return super.hurt(par1, par2);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.KAZROTIC.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.KAZROTIC_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.KAZROTIC_HURT.get();
    }

}