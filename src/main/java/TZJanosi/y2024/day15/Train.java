package TZJanosi.y2024.day15;

import java.util.*;

public class Train {
    private List<MoveableMapObject> moveableMapObjects;
    private Direction direction;

    public Train(MoveableMapObject moveableMapObject) {
        this.moveableMapObjects = new ArrayList<>(List.of(moveableMapObject));
    }
    public Coordinate getRobotCoordinate(){
        return moveableMapObjects.get(0).getPosition();
    }

    public void add(MoveableMapObject moveableMapObject){
        moveableMapObjects.add(moveableMapObject);
    }
    public void removeBoxes(){
        for (int i = moveableMapObjects.size()-1; i > 0; i--) {
            moveableMapObjects.remove(i);
        }
    }
    public void move(Map<Coordinate,MapObject> map){
        for (int i = moveableMapObjects.size()-1; i >= 0; i--) {
            Coordinate old=new Coordinate(moveableMapObjects.get(i).getPosition());
            map.remove(old);

            MoveableMapObject newElement=moveableMapObjects.get(i);
            newElement.move(direction);
            map.put(newElement.getPosition(),newElement);

        }
    }
    public Coordinate findNextPosition(){
        return moveableMapObjects.get(moveableMapObjects.size()-1).getPosition().add(direction.getShift());
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
