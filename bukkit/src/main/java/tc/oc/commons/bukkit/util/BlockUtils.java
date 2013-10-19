package tc.oc.commons.bukkit.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.util.Vector;

public class BlockUtils {
    public static Location center(Location location) {
        Location center = location.clone();
        center.setX(center.getBlockX() + 0.5);
        center.setY(center.getBlockY() + 0.5);
        center.setZ(center.getBlockZ() + 0.5);
        return center;
    }

    public static Location center(Block block) {
        return center(block.getLocation());
    }

    public static Location center(BlockState state) {
        return center(state.getLocation());
    }

    public static boolean isInside(Vector point, Location blockLocation) {
        return blockLocation.getX() <= point.getX() && point.getX() <= blockLocation.getX() + 1 &&
                blockLocation.getY() <= point.getY() && point.getY() <= blockLocation.getY() + 1 &&
                blockLocation.getZ() <= point.getZ() && point.getZ() <= blockLocation.getZ() + 1;
    }

    public static boolean isSupportive(Material type) {
        if (type.isBlock()) {
            // TODO: Add more
            switch (type) {
                case AIR:
                case BROWN_MUSHROOM:
                case CROPS:
                case DEAD_BUSH:
                case FIRE:
                    return false;
                default:
                    return true;
            }
        } else {
            return false;
        }
    }
}
