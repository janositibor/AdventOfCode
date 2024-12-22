package TZJanosi.y2024.day13;

public class Machine {
    private int axShift;
    private int ayShift;
    private int bxShift;
    private int byShift;
    private long xTarget;
    private long yTarget;
    private boolean ispossible=false;
    private long aPress=0;
    private long bPress=0;
    private long cost=0;

    public Machine(int axShift, int ayShift, int bxShift, int byShift, int xTarget, int yTarget,long shiftTarget) {
        this.axShift = axShift;
        this.ayShift = ayShift;
        this.bxShift = bxShift;
        this.byShift = byShift;
        this.xTarget = xTarget+shiftTarget;
        this.yTarget = yTarget+shiftTarget;
    }

    public void calculate(){
        calculatePress("A");
        if(ispossible) {
            calculatePress("B");
            calculateCost();
        }
    }

    private long calculateDirectPressB() {
        long numerator=yTarget-ayShift*aPress;
        long denominator=byShift;
        if(numerator%denominator!=0){
            return 0;
        }
        return (yTarget-ayShift*aPress)/byShift;
    }

    private void calculateCost() {
        cost=aPress*3+bPress;
    }

    private void calculatePress(String button) {
        int[] master;
        int[] sub;
        switch (button){
            case "A":
                master=new int[]{bxShift,byShift};
                sub=new int[]{axShift,ayShift};
                break;
            case "B":
                master=new int[]{axShift,ayShift};
                sub=new int[]{bxShift,byShift};
                break;
            default:
                throw new IllegalArgumentException("The button can be A or B. We got: "+ button);
        }
        long numerator = yTarget*master[0]-xTarget*master[1];
        long denominator = sub[1]*master[0]-sub[0]*master[1];
        if (numerator%denominator==0){
            ispossible=true;
            long result=numerator/denominator;
            switch (button){
                case "A":
                    aPress=result;
                    break;
                case "B":
                    bPress=result;
                    break;
                default:
                    throw new IllegalArgumentException("The button can be A or B. We got: "+ button);
            }
        }
        else{
            ispossible=false;
            aPress=0;
            bPress=0;
        }

    }

    @Override
    public String toString() {
        return "Machine{" +
                "axShift=" + axShift +
                ", ayShift=" + ayShift +
                ", bxShift=" + bxShift +
                ", byShift=" + byShift +
                ", xTarget=" + xTarget +
                ", yTarget=" + yTarget +
                ", ispossible=" + ispossible +
                ", aPress=" + aPress +
                ", bPress=" + bPress +
                ", cost=" + cost +
                '}';
    }

    public int getAxShift() {
        return axShift;
    }

    public int getAyShift() {
        return ayShift;
    }

    public int getBxShift() {
        return bxShift;
    }

    public int getByShift() {
        return byShift;
    }

    public long getxTarget() {
        return xTarget;
    }

    public long getyTarget() {
        return yTarget;
    }

    public boolean isIspossible() {
        return ispossible;
    }

    public long getaPress() {
        return aPress;
    }

    public long getbPress() {
        return bPress;
    }

    public long getCost() {
        return cost;
    }
}
