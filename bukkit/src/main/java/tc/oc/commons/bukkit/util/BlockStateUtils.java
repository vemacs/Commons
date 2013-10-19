package tc.oc.commons.bukkit.util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

public class BlockStateUtils {
    @SuppressWarnings("deprecation")
    public static BlockState toAir(BlockState state) {
        BlockState newState = state.getBlock().getState(); // this creates a new copy of the state
        newState.setType(Material.AIR);
        newState.setRawData((byte) 0);
        return newState;
    }

    public static BlockState cloneWithMaterial(Block block, Material material) {
        return cloneWithMaterial(block, material, (byte) 0);
    }

    @SuppressWarnings("deprecation")
    public static BlockState cloneWithMaterial(Block block, Material material, byte data) {
        BlockState state = block.getState();
        state.setType(material);
        state.setRawData(data);
        return state;
    }
}
