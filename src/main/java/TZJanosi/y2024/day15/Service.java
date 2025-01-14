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
    private boolean isSecondPart;

    public Service(List<String> input,boolean isSecondPart) {
        this.isSecondPart=isSecondPart;
        boolean inputForMap=true;
        int xLimit=0;
        for (int i = 0; i < input.size(); i++) {
            String line=input.get(i);
            xLimit=(line.length()>0?line.length():xLimit);
            if(line.isBlank()){
                inputForMap=false;
                limit=new Coordinate(xLimit,i);
                if(isSecondPart){
                    limit.setX(2*limit.getX());
                }
                emptyLine=" ".repeat(limit.getX());
            }
            else{
                if(inputForMap){
                    if(isSecondPart) {
                        fillMapPart2(i, line);
                    }
                    else {
                        fillMap(i, line);
                    }
                }
                else{
                    directions=directions.concat(line);
                }
            }
        }
    }
    private void fillMapPart2(int i, String line) {
        char[] chars=line.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            Coordinate coordinate=new Coordinate(2*j,i);
            Coordinate nextCoordinate=new Coordinate(2*j+1,i);
            char sign=chars[j];
            switch(sign){
                case 'O': map.put(coordinate,new BoxLeft(coordinate));
                    map.put(nextCoordinate,new BoxRight(nextCoordinate));
                    break;
                case '#': map.put(coordinate,new Wall(coordinate));
                    map.put(nextCoordinate,new Wall(nextCoordinate));
                    break;
                case '@': Robot robot=new Robot(coordinate);
                    map.put(coordinate,robot);
                    train=new Train(robot);
                    break;
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
        if(!isSecondPart) {
            Coordinate nextPosition = train.findNextPosition();
            MapObject next = map.get(nextPosition);
            if (next != null) {
                if (next.isMoveable()) {
                    train.add((MoveableMapObject) next);
                    oneStep(sign);
                }
            }
            else {
                train.move(map);
            }
        }
        else{
            addElementsToTrain();

            if(isTrainMoveable()) {
                train.move(map);
            }
        }
    }

    private boolean isTrainMoveable() {
        List<Coordinate> nextPositions = train.findNextPositions();
        for (Coordinate nextPosition:nextPositions) {
            MapObject next = map.get(nextPosition);
            if (next != null && !next.isMoveable()) {
                return false;
            }
        }
        return true;
    }

    private void addElementsToTrain() {
        List<Coordinate> nextPositions = train.findNextPositions();
        for (Coordinate nextPosition:nextPositions) {
            MapObject next = map.get(nextPosition);
            if (next != null) {
                if (next.isMoveable()) {
                    if(!train.containsCoordinate(nextPosition)) {
                        train.add((MoveableMapObject) next);
                        if (next.getSign() == '[') {
                            Coordinate coordinateOfPair = next.getPosition().add(new Coordinate(1, 0));
                            if (!train.containsCoordinate(coordinateOfPair)) {
                                train.add((MoveableMapObject) map.get(coordinateOfPair));
                            }
                        }
                        if (next.getSign() == ']') {
                            Coordinate coordinateOfPair = next.getPosition().add(new Coordinate(-1, 0));
                            if (!train.containsCoordinate(coordinateOfPair)) {
                                train.add((MoveableMapObject) map.get(coordinateOfPair));
                            }
                        }
                        addElementsToTrain();
                    }
                }
            }
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
            if(sign=='^'&& train.getMoveableMapObjects().size()>1){
                boolean debug=true;
            }
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
        long output;
        if(!isSecondPart) {
            output = map.entrySet().stream()
                    .map(e -> e.getValue())
                    .filter(mo -> mo.getClass() == Box.class)
                    .collect(Collectors.summingLong(mo -> calculateGPSToCoordinate(mo.getPosition())));
        }
        else{
            output = map.entrySet().stream()
                    .map(e -> e.getValue())
                    .filter(mo -> mo.getClass() == BoxLeft.class)
                    .collect(Collectors.summingLong(mo -> calculateGPSToCoordinate(mo.getPosition())));

        }
        return output;
    }
}
