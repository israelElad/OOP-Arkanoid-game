package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * class name: AnimationRunner
 * The AnimationRunner takes an Animation object and runs it.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor.
     *
     * @param gui the graphical user interface of the game.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * run the animation.
     *
     * @param animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            /*the time it takes to perform each loop may be non-negligible.
             We therefor subtract the time it takes to do the work from
             the sleep time of millisecondsPerFrame milliseconds.
            */
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime; //the time it took
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime; //time left to sleep after the iteration.
            if (milliSecondLeftToSleep > 0) { // there is still time to sleep
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}