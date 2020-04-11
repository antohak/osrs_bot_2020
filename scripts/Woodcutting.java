package scripts;

import org.powerbot.script.PaintListener;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.Game;
import scripts.paths.WoodcuttingPaths;
import scripts.tasks.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="Woodcutting", description="Woodcut bot", properties="client=4; author=Antoine; topic=999;")

public class Woodcutting extends PollingScript<ClientContext> implements PaintListener {

    List<Task> taskList = new ArrayList<Task>();
    int startExp = 0;

    @Override
    public void start() {

        String userOptions[] = {"Bank", "Powerchop"};
        String userChoice = ""+(String) JOptionPane.showInputDialog(
                null,
                "Bank or Powerchop",
                "Woodcutting",
                JOptionPane.PLAIN_MESSAGE,
                null,
                userOptions,
                userOptions[1]
                );
        if(userChoice.equals("Bank")) {
            taskList.add(new Bank(ctx));
            taskList.add(new Walk(ctx, WoodcuttingPaths.LUMBRIDGE_WILLOW_PATH));
            taskList.add(new Chop(ctx));
        }
        else if(userChoice.equals("Powerchop")) {
            taskList.add(new Drop(ctx));
            taskList.add(new Chop(ctx));
        } else {
            ctx.controller.stop();
        }
        startExp = ctx.skills.experience(Constants.SKILLS_WOODCUTTING);
    }

    @Override
    public void poll() {
        ctx.game.tab(Game.Tab.INVENTORY);
        for(Task task : taskList) {
            if(ctx.controller.isStopping()) {
                break;
            }

            if(task.activate()) {
                task.execute();
                break;
            }
        }
    }


    @Override
    public void repaint(Graphics graphics) {
        long ms = this.getTotalRuntime();
        long s = (ms / 1000) % 60;
        long m = (ms / (1000 * 60)) % 60;
        long h = (ms / (1000 * 60 * 60)) % 24;

        int expGained = ctx.skills.experience(Constants.SKILLS_WOODCUTTING) - startExp;

        Graphics2D g = (Graphics2D)graphics;

        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 150, 150);


        g.setColor(new Color(255, 255, 255));
        g.drawRect(0, 0, 150, 150);

        g.drawString("Woodcutting", 20, 20);
        g.drawString("Current level: " + ctx.skills.level(Constants.SKILLS_WOODCUTTING), 20, 40 );
        g.drawString("Running: " + String.format("%02d:%02d:%02d", h, m, s), 20, 60);
        g.drawString("Exp/h: " + (int) (expGained * (3600000 / ms)), 20, 80);
    }
}
