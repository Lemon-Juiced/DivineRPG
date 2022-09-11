package divinerpg.entities.vanilla.overworld;

import divinerpg.entities.base.*;
import divinerpg.entities.projectile.*;
import divinerpg.enums.*;
import divinerpg.registries.*;
import divinerpg.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

public class EntityAridWarrior extends EntityDivineMob implements IRangedAttackMob {

    public EntityAridWarrior(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }
    public static AttributeModifierMap.MutableAttribute attributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, EntityStats.aridWarriorHealth).add(Attributes.ATTACK_DAMAGE, EntityStats.aridWarriorDamage).add(Attributes.MOVEMENT_SPEED, EntityStats.aridWarriorSpeed).add(Attributes.FOLLOW_RANGE, EntityStats.aridWarriorFollowRange);
    }
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 2.25F;
    }

    @Override
    protected void registerGoals() {
        addAttackingAI();
        goalSelector.addGoal(0, new RangedAttackGoal(this, this.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue(), 5, (float)getAttribute(Attributes.FOLLOW_RANGE).getBaseValue()));
    }
    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (getTarget() != null && this.isAlive()) {
            EntityDivineArrow projectile = new EntityDivineArrow(EntityRegistry.ARROW_SHOT, level, ArrowType.ARID_WARRIOR_ARROW, this);
            double d0 = target.getX() - this.getX();
            double d1 = target.getY(0.3333333333333333D) - projectile.getY();
            double d2 = target.getZ() - this.getZ();
            double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
            projectile.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level.getDifficulty().getId() * 4));
            this.level.addFreshEntity(projectile);
        }
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.ARID_WARRIOR;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.ARID_WARRIOR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.ARID_WARRIOR_HURT;
    }

    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return true;
    }
    @Override
    public float getWalkTargetValue(BlockPos pos, IWorldReader reader) {
        return 0.0F;
    }
}
