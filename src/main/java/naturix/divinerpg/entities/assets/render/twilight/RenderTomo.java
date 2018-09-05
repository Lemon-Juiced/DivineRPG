package naturix.divinerpg.entities.assets.render.twilight;

import javax.annotation.Nullable;

import naturix.divinerpg.entities.assets.model.twilight.model.ModelTomo;
import naturix.divinerpg.entities.entity.twilight.Tomo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderTomo extends RenderLiving<Tomo> {
	
	public static final IRenderFactory FACTORY = new Factory();
	ResourceLocation texture = new ResourceLocation("divinerpg:textures/entity/tomo.png");
	private final ModelTomo ModelTomo;
    
	public RenderTomo(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, new ModelTomo(), 1F);
        ModelTomo = (ModelTomo) super.mainModel;

    }


	@Nullable
    @Override
    protected ResourceLocation getEntityTexture(Tomo entity) {
        return texture;
    }

	 public static class Factory implements IRenderFactory<Tomo> {

	        @Override
	        public Render<? super Tomo> createRenderFor(RenderManager manager) {
	            return new RenderTomo(manager, new ModelTomo(), 0.5F);
	        }
	    }

	}