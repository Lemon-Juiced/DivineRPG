package divinerpg.client.renders.layer;

import com.mojang.blaze3d.vertex.*;
import divinerpg.*;
import divinerpg.client.models.*;
import divinerpg.util.*;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.world.entity.player.*;
import net.minecraftforge.api.distmarker.*;

import java.util.*;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.resources.ResourceLocation;

@OnlyIn(Dist.CLIENT)
public class PlayerHatRender<T extends Player, M extends PlayerModel<T>> extends RenderLayer<T, M> {
    private ModelHat<T> modelHat;
    private ResourceLocation dev = new ResourceLocation(DivineRPG.MODID, "textures/model/devhats/hat_red.png");
    private ResourceLocation tester = new ResourceLocation(DivineRPG.MODID, "textures/model/devhats/hat_blue.png");
    private ResourceLocation artist = new ResourceLocation(DivineRPG.MODID, "textures/model/devhats/hat_purple.png");
    private ResourceLocation special = new ResourceLocation(DivineRPG.MODID, "textures/model/devhats/hat_yellow.png");
    private ResourceLocation friend = new ResourceLocation(DivineRPG.MODID, "textures/model/devhats/hat_pink.png");

    public PlayerHatRender(RenderLayerParent<T, M> parent, EntityModelSet set) {
        super(parent);
        modelHat = new ModelHat<T>(set.bakeLayer(ModelHat.LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity != null) {
            UUID id = entity.getUUID();
            if (entity.inventory.getArmor(3).isEmpty()) {
                if (Utils.isDeveloperName(id) || Utils.isTesterName(id) || Utils.isFriend(id) || Utils.isSpecial(id) || Utils.isArtist(id)) {
                    if (entity.isCrouching()) {
                        matrixStackIn.translate(0.0F, 0.3F, 0.0F);
                    }
                    this.getParentModel().copyPropertiesTo(modelHat);
                    this.modelHat.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
                    this.modelHat.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityCutout(getTextureLocation(entity)));
                    this.modelHat.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                }
            }
        }
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        if (entity != null) {
        UUID id = entity.getUUID();

        if (Utils.isDeveloperName(id)) {
            return dev;
        }

        if (Utils.isSpecial(id)) {
            return special;
        }

        if (Utils.isTesterName(id)) {
            return tester;
        }

        if (Utils.isArtist(id)) {
            return artist;
        }

        if (Utils.isFriend(id)) {
            return friend;
        }
    }

        return dev;
    }

}
