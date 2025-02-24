package divinerpg.registries;

import divinerpg.enchant.*;
import net.minecraftforge.registries.*;

import net.minecraft.world.item.enchantment.Enchantment;

import java.util.function.Supplier;

import static divinerpg.DivineRPG.MODID;

public class EnchantmentRegistry {
    public static final DeferredRegister<Enchantment> ENCHANTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);
    public static final RegistryObject<Enchantment> RIVE = registerEnchantment("rive", RiveEnchant::new);
    public static final RegistryObject<Enchantment> AFTERSHOCK = registerEnchantment("aftershock", AftershockEnchant::new);

    private static RegistryObject<Enchantment> registerEnchantment(String id, Supplier<Enchantment> enchantment) {
        return ENCHANTS.register(id, enchantment);
    }

}
