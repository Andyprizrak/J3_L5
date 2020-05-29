package ru.geekbrains.home_j3_lesson5;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static final int CARS_COUNT = 4;
    public static  boolean winner = true;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        CountDownLatch ready = new CountDownLatch(CARS_COUNT);
        CountDownLatch finish = new CountDownLatch(CARS_COUNT);

        for (int i = 0; i < cars.length; i++)
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), ready, finish);

        for (int i = 0; i < cars.length; i++)  new Thread(cars[i]).start();

        try {
            ready.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        try {
            finish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
