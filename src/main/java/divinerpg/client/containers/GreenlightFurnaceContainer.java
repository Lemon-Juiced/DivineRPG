package divinerpg.client.containers;

import divinerpg.registries.*;
import net.minecraft.network.*;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class GreenlightFurnaceContainer extends AbstractFurnaceMenu {
    public GreenlightFurnaceContainer(int p_i50082_1_, Inventory p_i50082_2_) {
        super(MenuTypeRegistry.GREENLIGHT_FURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, p_i50082_1_, p_i50082_2_);
    }
    public GreenlightFurnaceContainer(int p_i50083_1_, Inventory p_i50083_2_, Container p_i50083_3_, ContainerData p_i50083_4_) {
        super(MenuTypeRegistry.GREENLIGHT_FURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, p_i50083_1_, p_i50083_2_, p_i50083_3_, p_i50083_4_);
    }
    public GreenlightFurnaceContainer(int i, Inventory playerInventory, FriendlyByteBuf buffer) {
        this(i, playerInventory);
    }
}