package TZJanosi.y2024.day06;

public class GuardPath {
    private int[] coordinates;
    private char[][] matrixMap;

    public GuardPath(char[][] matrixMap) {
        this.matrixMap = matrixMap;
        coordinates = findInitCoordinates();
        rotate();
        rotate();
    }

    public int countVisitedPlaces(){
        String line;
        int count=0;
        for (int i = 0; i < matrixMap.length; i++) {
            line=new String(matrixMap[i]);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'X') {
                    count++;
                }
            }
        }
        return count;
    }

    public void findPath(){
        int lineNumber;
        int start;
        int to;
        do{
            rotate();
            lineNumber=coordinates[0];
            start=coordinates[1];
            StringBuilder sb= new StringBuilder(new String(matrixMap[lineNumber]));
//            System.out.println(sb);
            to = sb.indexOf("#",start);
            if(to>=0) {
                markPath(to);
                coordinates[1]=to-1;
//                System.out.println(countVisitedPlaces());
            }
            else{
                markPath(matrixMap[lineNumber].length);
            }
        }
        while(to>=0);
    }

    private void rotate(){
        coordinates=rotateCoordinatesCounterClockWise();
        matrixMap=Matrix.rotateCounterClockWiseChar(matrixMap);
    }

    private void markPath(int changeToThisIndex){
        int lineNumber=coordinates[0];
        int changeFrom=coordinates[1];
        StringBuilder sb= new StringBuilder(new String(matrixMap[lineNumber]));
        for (int i = changeFrom; i < changeToThisIndex; i++) {
            sb.setCharAt(i,'X');
        }
        matrixMap[lineNumber]=sb.toString().toCharArray();
    }

    private int[] findInitCoordinates(){
        String line;
        int j;
        for (int i = 0; i < matrixMap.length; i++) {
            line=new String(matrixMap[i]);
            if((j=line.indexOf('^'))>=0){
                return new int[]{i,j};
            }
        }
        return null;
    }

    public int[] rotateCoordinatesCounterClockWise() {
        return new int[]{matrixMap.length-coordinates[1]-1,coordinates[0]};
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public char[][] getMatrixMap() {
        return matrixMap;
    }
}
