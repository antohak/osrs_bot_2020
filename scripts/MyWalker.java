package scripts.tasks;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

public class MyWalker {

    private ClientContext ctx;

    public MyWalker(ClientContext ctx) {
        this.ctx = ctx;
    }

    private enum State {
        CHOPPING, INBANK, BANKING, WALKINGTOTREE, WALKINGTOBANK, IDLE, MOVING
    }

    public void WalkToPath(Tile [] tiles) {
        Tile nextTile = ctx.movement.newTilePath(tiles).next(); ///gets next reachable tile in array provided
        ctx.movement.step(nextTile);
    }

    public void WalkReverse(Tile [] tiles) {

    }

    private State getState() {
        //Is in bank
        if(ctx.bank.inViewport()) {
            if(ctx.bank.opened()) {
                return State.BANKING;
            }
            return State.INBANK;
        }
        if(ctx.players.local().inMotion()) {
            if(ctx.inventory.isFull()) {
                return State.WALKINGTOBANK;
            } else if(!ctx.inventory.isEmpty()) {
                Tile tile = new Tile(3280, 3436);
                Tile tile2 = new Tile(3284, 3431);
                Area area = new Area(tile, tile2);
                System.out.println(area);
                return State.WALKINGTOTREE;
            }
        }
        return State.IDLE;

    }
}
