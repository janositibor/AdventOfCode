package TZJanosi.y2024.day14;

public class Robot {
    private Coordinate position;
    private Coordinate velocity;
    private Coordinate transformedPosition;

    public Robot(Coordinate position, Coordinate velocity) {
        this.position = position;
        this.velocity = velocity;
    }
    public void positionAfterTime(int time){
        position.shift(velocity.multiply(time));
    }
    public int getQuadrant(Coordinate limit){
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
                if(inside(limit,xFrom,xTo,yFrom,yTo)){
                    return result;
                }
            }
        }
        return 0;
    }

    private boolean inside(Coordinate limit, int xFrom, int xTo, int yFrom, int yTo) {
        int xActual= position.getX()% limit.getX();
        xActual=(xActual<0?xActual+ limit.getX():xActual);
        int yActual= position.getY()% limit.getY();
        yActual=(yActual<0?yActual+ limit.getY():yActual);
        transformedPosition=new Coordinate(xActual,yActual);
        return (xFrom<=xActual && xActual<=xTo && yFrom<=yActual && yActual<=yTo);
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
}
