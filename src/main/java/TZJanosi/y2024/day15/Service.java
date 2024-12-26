package TZJanosi.y2024.day15;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service {
    private Map<Coordinate,MapObject> map=new HashMap<>();
    private String directions="";
    private Train train;
    private Coordinate limit;
    private String emptyLine;

    public Service(List<String> input) {
        boolean inputForMap=true;
        int yLimit=0;
        for (int i = 0; i < input.size(); i++) {
            String line=input.get(i);
            yLimit=(line.length()>0?line.length():yLimit);
            if(line.isBlank()){
                inputForMap=false;
                limit=new Coordinate(i,yLimit);
                emptyLine=" ".repeat(limit.getX());
            }
            else{
                if(inputForMap){
                    fillMap(i,line);
                }
                else{
                    directions=directions.concat(line);
                }
            }
        }
    }

    private void fillMap(int i, String line) {
        char[] chars=line.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            Coordinate coordinate=new Coordinate(j,i);
            char sign=chars[j];
            switch(sign){
                case 'O': map.put(coordinate,new Box(coordinate));
                    break;
                case '#': map.put(coordinate,new Wall(coordinate));
                    break;
                case '@': Robot robot=new Robot(coordinate);
                    map.put(coordinate,robot);
                    train=new Train(robot);
                    break;
            }
        }
    }
    private String lineToDraw(int i){
        StringBuilder line=new StringBuilder(emptyLine);
        List<MapObject> mapObjects=map.entrySet().stream().filter(e->e.getKey().getY()==i).map(e->e.getValue()).toList();
        for (MapObject mapObject:mapObjects) {
            line.setCharAt(mapObject.getPosition().getX(),mapObject.getSign());
        }
        return line.toString();
    }
    public void draw(char info){
        System.out.println("After step into direction: "+info);
        for (int i = 0; i < limit.getY(); i++) {
            System.out.println(lineToDraw(i));

        }
    }
    private Direction findDirectionToSign(char sign){
        for (Direction direction:Direction.values()) {
            if(direction.getSign()==sign){
                return direction;
            }
        }
        throw new IllegalArgumentException("No direction found to the sign: "+sign);
    }

    private void oneStep(char sign){
        Direction direction=findDirectionToSign(sign);
        train.setDirection(direction);
        Coordinate nextPosition=train.findNextPosition();
        MapObject next=map.get(nextPosition);
        if(next!=null){
            if(next.isMoveable()){
                train.add((MoveableMapObject) next);
                oneStep(sign);
            }
        }
        else{
            train.move(map);
        }
    }
    public void moveAll(boolean draw){
        char[] signs=directions.toCharArray();
        if(draw){
            draw(' ');
        }
        char sign;
        char previousSign=' ';
        for (int i = 0; i < signs.length; i++) {
            sign=signs[i];
            if(sign!=previousSign){
                train.removeBoxes();
            }
            oneStep(sign);
            if(draw){
                draw(sign);
            }
            previousSign=sign;
        }
    }
    private long calculateGPSToCoordinate(Coordinate coordinate){
        return(coordinate.getX()+100*coordinate.getY());
    }
    public long getSumOfGPS(){
        return map.entrySet().stream()
                .map(e->e.getValue())
                .filter(mo->mo.getClass()== Box.class)
                .collect(Collectors.summingLong(mo->calculateGPSToCoordinate(mo.getPosition())));
    }
}
