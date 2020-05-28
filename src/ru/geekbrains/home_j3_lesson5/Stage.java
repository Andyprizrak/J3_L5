package ru.geekbrains.home_j3_lesson5;

public abstract class Stage {
    protected int length;
    protected String description;
    public abstract void go(Car c, int stagePos, int stageCount);
}
