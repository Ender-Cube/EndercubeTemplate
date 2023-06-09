package me.zax71.EndercubeTemplate.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.ansi.ANSIComponentSerializer;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.tag.Tag;
import org.jetbrains.annotations.NotNull;

import static me.zax71.EndercubeTemplate.Main.logger;


public class PlayerLogin implements EventListener<PlayerLoginEvent> {
    @Override
    public @NotNull Class<PlayerLoginEvent> eventType() {
        return PlayerLoginEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerLoginEvent event) {
        Player player = event.getPlayer();
        // event.setSpawningInstance(HUB);
        player.setRespawnPoint(new Pos(0, 100, 0));
        player.setGameMode(GameMode.ADVENTURE);

        // Tell players, and the log, that someone joined
        Component playerJoinMessage = player.getName().append(Component.text(" joined the server")).color(NamedTextColor.YELLOW);
        Audiences.players().sendMessage(playerJoinMessage);
        logger.info(ANSIComponentSerializer.ansi().serialize(playerJoinMessage));

        // Initialise the spectating tag
        Tag<Boolean> spectating = Tag.Boolean("spectating");
        player.setTag(spectating, false);
        return Result.SUCCESS;
    }
}
