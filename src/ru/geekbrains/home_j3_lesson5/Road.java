package ru.geekbrains.home_j3_lesson5;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c, int stagePos, final int stageCount) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            if (stagePos == stageCount && Main.winner) {
                Main.winner = false;
                System.out.println(c.getName() + " Winner!!!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
