package tzjanosi_temp.y2024.day16.part1;

import java.util.*;

public class Labyrinth {
    private List<Coordinate> walls=new ArrayList<>();
    private Set<Node> inProgress=new HashSet<>();
    private Coordinate start;
    private Coordinate end;

    public Labyrinth(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            readLine(i,input.get(i));
        }
    }
    public int calculateWay(){
        Node startNode=new Node(start, new Coordinate(1,0));
        startNode.setCost(0);
        if(!inProgressContains(startNode)){

            inProgress.add(startNode);
        }
        Node endNode=new Node(end, new Coordinate(0,0));
        while(!inProgressContains(endNode) && !inProgress.isEmpty()) {
            workWithProgressList();
//            System.out.println("inProgress: "+inProgress);
        }
        return getMinFromInProgressToCoordinate(endNode.getLocation());
    }
    private int getMinFromInProgressToCoordinate(Coordinate location) {
        return inProgress.stream()
                .filter(n->n.getLocation().equals(location))
                .mapToInt(Node::getCost)
                .sorted()
                .findFirst()
                .getAsInt();
    }
    private void workWithProgressList() {
        Node node=getTheCheapestFromInProgressSet();
        workWithNode(node);

    }
    private void removeFromInProgressSet(Node node) {
//        System.out.println("Remove: "+node);
//        inProgress.removeIf(n->n.getLocation().equals(node.getLocation()));
        inProgress.remove(node);
    }
    private Node getTheCheapestFromInProgressSet() {
        return inProgress.stream().sorted(Comparator.comparingInt(Node::getCost)).findFirst().get();
    }
    private void workWithNode(Node node) {
        List<Node> neighbourNodes=findNeighbourNodes(node);
        addToInProgress(neighbourNodes);
        removeFromInProgressSet(node);
    }
    private void addToInProgress(List<Node> nodes) {
        for (Node node:nodes) {
//            System.out.println("Add: "+node);
            inProgress.add(node);
        }
    }
    public List<Node> findNeighbourNodes(Node node){
        Coordinate location=node.getLocation();
        List<Node> output=new ArrayList<>();
        List<Coordinate> wayOuts=wayOut(location);
        Node nextNode;
        for (Coordinate to:wayOuts) {
            if(!to.equals(node.getLocation().minus(node.getIncomingDirection()))){
                int cost=node.getCost()+1;
                if(!to.minus(location).equals(node.getIncomingDirection())){
                    cost+=1000;
                }
                if(isPipe(to)){
                    nextNode=endOfPipe(to,location,cost);
                }
                else{
                    nextNode=new Node(to,to.minus(location));

                    nextNode.setCost(cost);
                }
                output.add(nextNode);
            }
        }
        return output;
    }
    private boolean inProgressContains(Node actualNode) {
        return inProgress.stream().anyMatch(n->n.getLocation().equals(actualNode.getLocation()));
    }
    private Node endOfPipe(Coordinate location, Coordinate previous, int cost) {
//        System.out.print("Pipe: "+location+" -> ");
        if(numberOfWalls(location)==2 && !location.equals(end)){
            Coordinate next=null;
            for (Coordinate neighbour:wayOut(location)) {
                if(!neighbour.equals(previous)){
                    next=neighbour;
                    break;
                }
            }
            int nextCost = cost + 1;
            if(!next.minus(location).equals(location.minus(previous))){
                nextCost += 1000;
            }
            return endOfPipe(next, location, nextCost);
        }
        else{
            Node output= new Node(location,location.minus(previous));
            output.setCost(cost);
            return output;
        }
    }
    private boolean isPipe(Coordinate location){
        return wayOut(location).size()==2;
    }
    private List<Coordinate> wayOut(Coordinate location){
        List<Coordinate> output=new ArrayList<>();
        Coordinate neighbour;
        for (int i = -1; i <= 1; i+=2) {
            neighbour=new Coordinate(location.getX()+i, location.getY());
            if(!walls.contains(neighbour)){
                output.add(neighbour);
            }
        }
        for (int j = -1; j <= 1; j+=2) {
            neighbour=new Coordinate(location.getX(), location.getY()+j);
            if(!walls.contains(neighbour)){
                output.add(neighbour);
            }
        }
        return output;
    }
    private int numberOfWalls(Coordinate location){
        int output=0;
        Coordinate neighbour;
        for (int i = -1; i <= 1; i+=2) {
            neighbour=new Coordinate(location.getX()+i, location.getY());
            if(walls.contains(neighbour)){
                output++;
            }
        }
        for (int j = -1; j <= 1; j+=2) {
            neighbour=new Coordinate(location.getX(), location.getY()+j);
            if(walls.contains(neighbour)){
                output++;
            }
        }
        return output;
    }
    private void readLine(int i, String line) {
        char[] chars=line.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            char sign = chars[j];
            if (sign != '.') {
                Coordinate coordinate = new Coordinate(j, i);
                switch (sign) {
                    case '#':
                        walls.add(coordinate);
                        break;
                    case 'E':
                        end=coordinate;
                        break;
                    case 'S':
                        start=coordinate;
                        break;
                    default:
                        break;
                }
            }
        }
    }
    public Coordinate getStart() {
        return start;
    }
    public Coordinate getEnd() {
        return end;
    }
}
