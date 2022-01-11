/*
* Polymorphism Example: 
* Method Overriding , notice how the movie.getName() methods works so well :)  at line 12
* */

package com.scripter;

public class ClientMain {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            Movie movie = randomMovie();
            System.out.println("Movie #" + i + ":" +movie.getName() + "\n"+"Plot: "+movie.plot()+"\n");
        }
    }

    public static Movie randomMovie() {
        int randomNumber = (int) (Math.random() * 5) + 1;
        System.out.println("Random Number generator : " + randomNumber);
        switch (randomNumber) {
            case 1:
                return new Jaws();
            case 2:
                return new MazeRunner();
            case 3:
                return new IndependenceDay();
            case 4:
                return new Starwars();
            case 5:
                return new Forgetable();
            default:
                return null;
        }
    }
}

class Movie {
    private String name;

    public Movie(String name) {
        this.name = name;
    }

    public String plot() {
        return "No Plot here";
    }

    public String getName() {
        return name;
    }
}

class Jaws extends Movie {

    public Jaws() {
        super("Jaws");
    }

    @Override
    public String plot() {
        return "A shark eats lots of peeps";
    }
}

class IndependenceDay extends Movie {
    public IndependenceDay() {
        super("Independence Day");
    }

    @Override
    public String plot() {
        return "Aliens attempts to take over planet earth";
    }
}

class MazeRunner extends Movie {
    public MazeRunner() {
        super("Maze Runner");
    }

    @Override
    public String plot() {
        return "Kids try to escape a maze";
    }
}

class Starwars extends Movie {
    public Starwars() {
        super("Star Wars");
    }

    @Override
    public String plot() {
        return "Imperial Forces try to take over the universe";
    }
}

class Forgetable extends Movie {
    public Forgetable() {
        super("Forgetable");
    }
    //No Plot method
}




