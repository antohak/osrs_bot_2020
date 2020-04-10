package scripts.tasks;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.Task;
import scripts.Walker;
import scripts.constants.Banks;

public class Walk extends Task {

    final Tile [] pathToBnk = {
            new Tile(3282, 3429, 0),
            new Tile(3272, 3428, 0),
            new Tile(3269, 3428, 0),
            new Tile(3265, 3428, 0),
            new Tile(3260, 3428, 0),
            new Tile(3258, 3428, 0),
            new Tile(3256, 3427, 0),
            new Tile(3254, 3426, 0),
            new Tile(3254, 3423, 0),
            new Tile(3254, 3419, 0)
    };
    private final Walker walker = new Walker(ctx);

    public Walk(ClientContext ctx) {
        super(ctx);
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

    private enum State {
        WALKINGTOTREE, WALKINGTOBANK, IDLE
    }

    private State getState() {
        if(ctx.players.local().inMotion()) {
            if(ctx.inventory.isFull()) {
                return State.WALKINGTOBANK;
            } else if(!ctx.inventory.isEmpty() && !ctx.inventory.isFull()) {
                return State.WALKINGTOTREE;
            }
        }
        return State.IDLE;

    }
}