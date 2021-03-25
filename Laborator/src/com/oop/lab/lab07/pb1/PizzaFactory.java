package com.oop.lab.lab07.pb1;

public class PizzaFactory {

    private static PizzaFactory pizzaFactory;

    private PizzaFactory() {

    }

    public static PizzaFactory CurrentPizzaFactory() {
        if (pizzaFactory == null)
            pizzaFactory = new PizzaFactory();
        return pizzaFactory;
    }

    public Pizza creeazaPizza(TipPizza p, int dimensiune, int pret) {
        switch (p) {
            case Diavola:
                return new DiavolaPizza(dimensiune, pret);
            case Hawaii:
                return new HawaiiPizza(dimensiune, pret);
            case Pepperoni:
                return new PepperoniPizza(dimensiune, pret);
        }
        throw new IllegalArgumentException("Tipul de pizza " + p + " nu este cunoscut.");
    }

    public enum TipPizza {
        Diavola, Hawaii, Pepperoni
    }

    public static void main(String[] args) {
        PizzaFactory pizzaFactory = PizzaFactory.CurrentPizzaFactory();
        Pizza[] pizzas = new Pizza[3];
        pizzas[0] = pizzaFactory.creeazaPizza(TipPizza.Diavola, 30, 30);
        pizzas[1] = pizzaFactory.creeazaPizza(TipPizza.Hawaii, 40, 40);
        pizzas[2] = pizzaFactory.creeazaPizza(TipPizza.Pepperoni, 30, 20);
        for (Pizza pizza : pizzas) {
            System.out.println(pizza.toString());
        }
    }
}
