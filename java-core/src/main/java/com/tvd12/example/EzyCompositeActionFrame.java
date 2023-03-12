package com.tvd12.example;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EzyCompositeActionFrame {

    private final int seconds;
    private volatile long totalActions;
    private final long maxActions;
    private final List<EzyActionFrame> inSecondFrames;

    public EzyCompositeActionFrame(int seconds, long maxActions) {
        this.seconds = seconds;
        this.maxActions = maxActions;
        this.inSecondFrames = new ArrayList<>();

    }

    public synchronized boolean addActions(int actions) {
        final long currentTime = System.currentTimeMillis();
        final long minStartTime = currentTime - seconds * 1000L;
        final Iterator<EzyActionFrame> iterator = inSecondFrames.iterator();
        while (iterator.hasNext()) {
            final EzyActionFrame frame = iterator.next();
            if (frame.getStartTime() < minStartTime) {
                iterator.remove();
            } else {
                break;
            }
        }
        EzyActionFrame currentFrame = null;
        for (EzyActionFrame frame : inSecondFrames) {
            if (frame.isValid()) {
                currentFrame = frame;
                break;
            }
        }
        if (currentFrame == null) {
            currentFrame = new EzyActionFrameSecond(
                maxActions,
                currentTime
            );
            inSecondFrames.add(currentFrame);
        }
        currentFrame.addActions(actions);
        totalActions = inSecondFrames
            .stream()
            .mapToLong(EzyActionFrame::getActions)
            .sum();
        return totalActions > maxActions;
    }

    public synchronized boolean isInvalid() {
        return totalActions > maxActions;
    }

    @Override
    public String toString() {
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSS");
        return df.format(System.currentTimeMillis()) +
            ", frames: " + inSecondFrames.size() +
            ", actions: " + totalActions +
            ", maxActions: " + maxActions +
            ", tooManyRequest: " + isInvalid();
    }

    @Getter
    static abstract class EzyActionFrame {

        protected final long endTime;
        protected final long startTime;
        protected final long maxActions;
        protected volatile long actions;

        public EzyActionFrame(long maxActions) {
            this(maxActions, System.currentTimeMillis());
        }

        public EzyActionFrame(long maxActions, long startTime) {
            this.maxActions = maxActions;
            this.startTime = startTime;
            this.endTime = startTime + getExistsTime();
        }

        protected abstract int getExistsTime();

        public boolean addActions(long actions) {
            return (this.actions += actions) > maxActions;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > endTime;
        }

        public boolean isInvalid() {
            return actions > maxActions;
        }

        public boolean isValid() {
            return !isInvalid();
        }

        public abstract EzyActionFrame nextFrame();

        @Override
        public String toString() {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSS");
            return getClass().getSimpleName() +
                ": " +
                df.format(startTime) +
                " -> " +
                df.format(endTime);
        }
    }

    static class EzyActionFrameSecond extends EzyActionFrame {

        public EzyActionFrameSecond() {
            this(Integer.MAX_VALUE);
        }

        public EzyActionFrameSecond(long maxActions) {
            super(maxActions);
        }

        public EzyActionFrameSecond(long maxActions, long startTime) {
            super(maxActions, startTime);
        }

        @Override
        protected final int getExistsTime() {
            return 1000;
        }

        @Override
        public final EzyActionFrame nextFrame() {
            return new EzyActionFrameSecond(maxActions, endTime);
        }
    }

    public static void main(String[] args) throws Exception {
        final EzyCompositeActionFrame frame = new EzyCompositeActionFrame(3, 4);
        // 1st request comes (second ~0.5)
        Thread.sleep(500);
        System.out.println("add 1st actions");
        frame.addActions(1);
        System.out.println(frame);

        // 2nd request comes (second ~1)
        Thread.sleep(450);
        System.out.println("add 2nd actions");
        frame.addActions(2);
        System.out.println(frame);

        // 3th request comes (second ~2)
        Thread.sleep(1000);
        System.out.println("add 3th actions");
        frame.addActions(3);
        System.out.println(frame);

        // 4th request comes (second ~3)
        Thread.sleep(1000);
        System.out.println("add 4th actions");
        frame.addActions(4);
        System.out.println(frame);

        // 5th request comes (second ~4)
        Thread.sleep(1000);
        System.out.println("add 5th actions");
        frame.addActions(5);
        System.out.println(frame);

        // 6th request comes (second ~4.1)
        Thread.sleep(100);
        System.out.println("add 6th actions");
        frame.addActions(6);
        System.out.println(frame);

        // 7th request comes (second ~4.2)
        Thread.sleep(100);
        System.out.println("add 7th actions");
        frame.addActions(7);
        System.out.println(frame);

        // 8th request comes (second ~4.3)
        Thread.sleep(100);
        System.out.println("add 8th actions");
        frame.addActions(8);
        System.out.println(frame);
    }
}

