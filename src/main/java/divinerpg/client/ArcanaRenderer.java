package divinerpg.client;

import divinerpg.api.java.divinerpg.api.Reference;
import divinerpg.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ArcanaRenderer {

    Minecraft mc = Minecraft.getMinecraft();

    public static float percantage = 100;
    public static boolean regen;

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if(event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            onTickRender();
        }
    }

    private void onTickRender() {
        if (mc.currentScreen == null) {
            GlStateManager.color(1.0F, 1.0F, 1.0F);//resets color

            GuiIngame gig = mc.ingameGUI;
            ScaledResolution scaledresolution = new ScaledResolution(mc);
            int i = scaledresolution.getScaledWidth();
            int k = scaledresolution.getScaledHeight();
            this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/arcana_bar.png"));
            int y = k - Config.arcanaY;
            int x = i - Config.arcanaX;
            gig.drawTexturedModalRect(x, y, 0, 0, 100, 9);
            gig.drawTexturedModalRect(x, y, 0, 9, (int) percantage, 18);
        }
    }

}
