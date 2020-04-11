package scripts.tasks;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.Task;
import scripts.Walker;

public class Walk extends Task {

    public Tile [] pathToBnk;
    private final Walker walker = new Walker(ctx);

    public Walk(ClientContext ctx, Tile[] pathToBnk)
    {
        super(ctx);
        this.pathToBnk = pathToBnk;
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull() || (ctx.inventory.select().count() < 28 && pathToBnk[0].distanceTo(ctx.players.local()) > 6);
    }

    @Override
    public void execute() {
        int energyLevel = Random.nextInt(20, 45);
        if(!ctx.movement.running() && ctx.movement.energyLevel() > energyLevel)
            ctx.movement.running(true);

        if(!ctx.players.local().inMotion() || ctx.movement.destination().equals((Tile.NIL))
                || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if(ctx.inventory.isFull())
                walker.walkPath(pathToBnk);
            else
                walker.walkPathReverse(pathToBnk);
        }
    }
}