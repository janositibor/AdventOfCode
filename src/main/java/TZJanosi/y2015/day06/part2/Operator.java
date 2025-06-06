package TZJanosi.y2015.day06.part2;

public enum Operator {
    ON("on") {
        @Override
        public void operation(Bulb bulb) {
            bulb.turnOn();
        }
    }, OFF("off") {
        @Override
        public void operation(Bulb bulb) {
            bulb.turnOff();
        }
    }, TOGGLE("toggle") {
        @Override
        public void operation(Bulb bulb) {
            bulb.toggle();
        }
    };

    private String nameAsString;

    Operator(String nameAsString) {
        this.nameAsString = nameAsString;
    }

    public abstract void operation(Bulb bulb);

    public String getNameAsString() {
        return nameAsString;
    }
}
