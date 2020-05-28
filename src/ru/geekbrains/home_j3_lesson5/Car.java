package ru.geekbrains.home_j3_lesson5;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {CARS_COUNT = 0;}
    private Race race;
    private int speed;
    private String name;
    private CountDownLatch cdlR;
    private CountDownLatch cdlF;
    private static CountDownLatch cdl = new CountDownLatch(Main.CARS_COUNT);

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CountDownLatch cdlR, CountDownLatch cdlF) {
        this.race = race;
        this.speed = speed;
        this.cdlR = cdlR;
        this.cdlF = cdlF;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cdl.countDown();
            cdlR.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this, i + 1, race.getStages().size());
        }
        cdlF.countDown();
    }
}
