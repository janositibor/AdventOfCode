package TZJanosi.y2024.day15;

public class MoveableMapObject extends MapObject {
    public MoveableMapObject(Coordinate position, boolean moveable, char sign) {
        super(position, moveable, sign);
    }

    public void move(Direction direction){
        getPosition().shift(direction.getShift());
    }
}
