package tzjanosi.y2024.day14;

public class Robot {
    private Coordinate position;
    private Coordinate velocity;
    private Coordinate limit;
    private Coordinate transformedPosition;

    public Robot(Coordinate position, Coordinate velocity, Coordinate limit) {
        this.position = position;
        this.velocity = velocity;
        this.limit=limit;
        transformPosition();
    }
    public void positionAfterTime(int time){
        position.shift(velocity.multiply(time));
        transformPosition();

    }
    public int getQuadrant(){
        int result=0;
        int xLength=(limit.getX()-1)/2;
        int yLength=(limit.getY()-1)/2;
        for (int i = 0; i < 2; i++) {
            int xFrom=i*(xLength+1);
            int xTo=xLength-1+i*(xLength+1);
            for (int j = 0; j < 2; j++) {
                result++;
                int yFrom=j*(yLength+1);
                int yTo=yLength-1+j*(yLength+1);
                if (inside(xFrom, xTo, yFrom, yTo)) {
                    return result;
                }
            }
        }
        return 0;
    }

    private boolean inside(int xFrom, int xTo, int yFrom, int yTo) {

        return (xFrom<=transformedPosition.getX() && transformedPosition.getX()<=xTo && yFrom<= transformedPosition.getY() && transformedPosition.getY()<=yTo);
    }

    private void transformPosition() {
        int xActual= position.getX()% limit.getX();
        xActual=(xActual<0?xActual+ limit.getX():xActual);
        int yActual= position.getY()% limit.getY();
        yActual=(yActual<0?yActual+ limit.getY():yActual);
        transformedPosition=new Coordinate(xActual,yActual);
    }

    @Override
    public String toString() {
        return "Robot{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", transformedPosition=" + transformedPosition +
                '}';
    }

    public Coordinate getPosition() {
        return position;
    }

    public Coordinate getVelocity() {
        return velocity;
    }

    public Coordinate getTransformedPosition() {
        return transformedPosition;
    }
}
