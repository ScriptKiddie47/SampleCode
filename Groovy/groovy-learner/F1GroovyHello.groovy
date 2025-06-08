class F1GroovyHello {
    static void main(String[] args) {
        def value = 13
        switch(value) {
            case 0..8: println("Child"); break;
            case 8..15: println("Teenager"); break;
            case 16..17: println("Can Drive"); break;
            case 18..60: println("Can Vote"); break;
            default: println("Have Fun");
        }
    }
}
