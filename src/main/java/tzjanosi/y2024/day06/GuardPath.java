package tzjanosi.y2024.day06;

public class GuardPath {
    private int[] coordinates;
    private char[][] originalMatrixMap;
    private char[][] matrixMap;
    private int direction;
    private Path path=new Path();

    public GuardPath(char[][] matrixMap) {
        this.originalMatrixMap = Matrix.newMatrix(matrixMap);
        this.matrixMap = Matrix.newMatrix(matrixMap);
        coordinates = findInitCoordinates();
    }

    private Integer[] transformCoordinatesIntoZeroDirection(int[] original){
        Integer[] result;
//        System.out.println("original: "+Arrays.toString(original)+" "+direction);
        switch(direction) {
            case 0:
                result = new Integer[]{original[0], original[1]};
                break;
            case 1:
                result = new Integer[]{original[1], matrixMap.length - original[0] - 1};
                break;
            case 2:
                result = new Integer[]{matrixMap.length - original[0] - 1, matrixMap[0].length - original[1] - 1};
                break;
            case 3:
                result = new Integer[]{matrixMap[0].length - original[1] - 1, original[0]};
                break;
            default:
                throw new IllegalStateException("Direction should not be higher than 3! actual value: " + direction);
        }
//        System.out.println("rotated: "+Arrays.toString(result));

        return result;
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

    public boolean findPath(boolean verbose){
        int lineNumber;
        int start;
        int to;
        boolean loop;
        rotate();
        rotate();

        do{
            rotate();
            lineNumber=coordinates[0];
            start=coordinates[1];
            StringBuilder sb= new StringBuilder(new String(matrixMap[lineNumber]));
            if(verbose) {
                System.out.println(sb);
            }
            to = sb.indexOf("#",start);
            if(to>=0) {
                loop=markPath(to);
                coordinates[1]=to-1;
                if(loop){
                    return true;
                }
//                System.out.println(countVisitedPlaces());
            }
            else{
                markPath(matrixMap[lineNumber].length);
            }
        }
        while(to>=0);
        return false;
    }

    private void rotate(){
        coordinates=rotateCoordinatesCounterClockWise();
        matrixMap=Matrix.rotateCounterClockWiseChar(matrixMap);
        if(direction>3){
            throw new IllegalStateException("Direction should not be higher than 3! actual value: "+direction);
        }
        direction=direction==3?0:direction+1;
    }

    private boolean markPath(int changeToThisIndex){
        int lineNumber=coordinates[0];
        int changeFrom=coordinates[1];
        Trio actaulCoordinates;
        StringBuilder sb= new StringBuilder(new String(matrixMap[lineNumber]));
        for (int i = changeFrom; i < changeToThisIndex; i++) {
            sb.setCharAt(i,'X');
            actaulCoordinates=new Trio(new Integer[] {transformCoordinatesIntoZeroDirection(new int[]{lineNumber,i})[0], transformCoordinatesIntoZeroDirection(new int[]{lineNumber,i})[1],direction});
            if(path.alreadyVisited(actaulCoordinates)){
                return true;
            }
            path.addPath(actaulCoordinates);
        }
        matrixMap[lineNumber]=sb.toString().toCharArray();
        return false;
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
        return new int[]{};
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

    public Path getPath() {
        return path;
    }

    public char[][] getOriginalMatrixMap() {
        return originalMatrixMap;
    }

    public void addClog(Integer[] actualCoordinates) {
        int lineNumber=actualCoordinates[0];
        int indexToChange=actualCoordinates[1];
        StringBuilder sb= new StringBuilder(new String(matrixMap[lineNumber]));
        if(sb.charAt(indexToChange)!='^') {
            sb.setCharAt(indexToChange, '#');
            matrixMap[lineNumber] = sb.toString().toCharArray();
        }
    }
}
