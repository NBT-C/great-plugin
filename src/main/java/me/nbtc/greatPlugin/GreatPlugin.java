package me.nbtc.greatPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class GreatPlugin extends JavaPlugin {
    private HashMap<Player, Integer> playerColors = new HashMap<>();

    @Override
    public void onEnable() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers()) {
                    if (!playerColors.containsKey(player)) {
                        int colorCode = new Random().nextInt(8) + 1;
                        playerColors.put(player, colorCode);
                    }
                    int colorCode = playerColors.get(player);
                    double angle = 0;
                    double radius = 1.0;
                    for (int i = 0; i < 100; i++) {
                        angle += Math.PI / 8;
                        radius += 0.05;
                        double x = radius * Math.cos(angle);
                        double z = radius * Math.sin(angle);
                        double y = 1.5 + (Math.sin(angle) * 0.5);
                        player.spawnParticle(Particle.REDSTONE, player.getLocation().add(x, y, z), 0, new Particle.DustOptions(Color.fromRGB(colorCode * 30, colorCode * 30, colorCode * 30), 1));
                    }
                }
            }
        }.runTaskTimer(this, 0, 20);
    }
}
