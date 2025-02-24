package divinerpg.entities.base;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public abstract class EntityDivineFlyingMob extends EntityDivineMonster {
    protected @Nullable Vec3 pathfindPos;
    protected final float preferredHeight, preferredDistance;
    protected EntityDivineFlyingMob(EntityType<? extends EntityDivineMonster> type, Level worldIn) {
        super(type, worldIn);
        preferredHeight = 5F;
        preferredDistance = 8F;
    }
    protected EntityDivineFlyingMob(EntityType<? extends EntityDivineMonster> type, Level worldIn, float preferredHeight) {
        super(type, worldIn);
        this.preferredHeight = preferredHeight;
        preferredDistance = 8F;
    }
    protected EntityDivineFlyingMob(EntityType<? extends EntityDivineMonster> type, Level worldIn, float preferredHeight, float preferredDistance) {
        super(type, worldIn);
        this.preferredHeight = preferredHeight;
        this.preferredDistance = preferredDistance;
    }
    @Override
    protected void registerGoals() {
        targetSelector.addGoal(2, new HurtByTargetGoal(this));
        if(isAggressive()) {
            targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
            if(!(this instanceof RangedAttackMob)) goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, false));
        }
    }
    @Override
    protected boolean shouldDespawnInPeaceful() {return true;}
    @Override
    public boolean causeFallDamage(float f, float g, DamageSource d) {return false;}
    @Override
    protected void checkFallDamage(double d, boolean b, BlockState s, BlockPos p) {}
    @Override
    public boolean onClimbable() {return false;}
    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        setNoGravity(true);
        boolean blockedPath = horizontalCollision || verticalCollision || !level.getBlockState(new BlockPos(position().add(getDeltaMovement()))).isAir();
        //decide where to go next
        if(pathfindPos == null || blockedPath) {
            double findX = getX() + ((random.nextFloat() - .5F) * 14D), findY = getY() + ((random.nextFloat() - .6F) * 14D), findZ = getZ() + ((random.nextFloat() - .5F) * 14D);
            LivingEntity target = getTarget();
            if(target != null && !blockedPath) {
                if(this instanceof RangedAttackMob) {
                    boolean tooclose = distanceTo(target) < preferredDistance;
                    pathfindPos = new Vec3(findX + (tooclose ? -1 : 1) * (target.getX() - getX()) / 4, findY + (target.getY() - getY() + preferredHeight) / 1.1 + (level.getBlockState(blockPosition()).isAir() ? 0D : 2D), findZ + (tooclose ? -1 : 1) * (target.getZ() - getZ()) / 4);
                } else {
                    pathfindPos = target.position().add(target.getDeltaMovement().multiply(3D, 3D, 3D));
                    if(!level.getBlockState(blockPosition()).isAir()) pathfindPos.add(0D, 2D, 0D);
                }
            } else pathfindPos = new Vec3(findX, findY, findZ);
        }
        //movement
        double speed = getAttributeValue(Attributes.FLYING_SPEED);
        setDeltaMovement(getDeltaMovement().x + (pathfindPos.x - getX()) / 64D * speed, getDeltaMovement().y + (pathfindPos.y- getY()) / 64D * speed, getDeltaMovement().z + (pathfindPos.z - getZ()) / 64D * speed);
        double distanceX = pathfindPos.x - getX(), distanceY = pathfindPos.y- getY(), distanceZ = pathfindPos.z - getZ();
        yRot = rotlerp(yRot, (float) (Mth.atan2(distanceZ, distanceX) * 180D / Math.PI) - 90F, 90F);
        xRot = rotlerp(xRot, (float) -(Mth.atan2(distanceY, Math.sqrt(distanceX * distanceX + distanceZ * distanceZ)) * 180D / Math.PI), 20F);
        if(Math.sqrt(distanceToSqr(pathfindPos.x, pathfindPos.y, pathfindPos.z)) < 2D) pathfindPos = null;
    }
    protected float rotlerp(float rot, float g, float bound) {
        float f = Mth.wrapDegrees(g - rot);
        if(f > bound) f = bound;
        if(f < -bound) f = -bound;
        float f1 = rot + f;
        if(f1 < 0F) f1 += 360F;
        else if(f1 > 360F) f1 -= 360F;
        return f1;
    }
}