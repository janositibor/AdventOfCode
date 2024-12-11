package TZJanosi.y2024.day09;

import java.util.*;

public class Disk {
    private List<Fragment> area = new LinkedList<>();
    public void init(String input){
        List<Integer> inputList=createInputListFromString(input);
        fillArea(inputList);
    }

    public long calculateCheckSum(){
        return area.stream().mapToLong(f->f.checkSum()).reduce(0,(n,m)->n+m);
//        int result=0;
//        for (int i = 0; i < area.size(); i++) {
//            result+=area.get(i).checkSum();
//            System.out.println(i +" -> "+area.get(i).checkSum()+" -> "+result);
//
//        }
//        return result;
    }

    public void refragment(){
        Optional<Fragment> findFirstBlank=findFirstBlank();
        Optional<Fragment> findLaststDataFragment=findLaststDataFragment();
        while(findFirstBlank.isPresent() && (findFirstBlank.get().getOrder()<findLaststDataFragment.get().getOrder())){
            System.out.println(findFirstBlank.get().getOrder()+" <? "+findLaststDataFragment.get().getOrder());
            move(findLaststDataFragment.get(),findFirstBlank.get());
            findFirstBlank=findFirstBlank();
            findLaststDataFragment=findLaststDataFragment();
        }
    }

    public void rearrangeFiles(){
        Optional<Fragment> findLaststDataFragment=findLaststDataFragment();
        Optional<Fragment> findProperBlank=findFirstLongEnoughBlank(findLaststDataFragment.get().getLength());
        while(findProperBlank.isPresent() && (findProperBlank.get().getOrder()<findLaststDataFragment.get().getOrder())){
            System.out.println(findProperBlank.get().getOrder()+" <? "+findLaststDataFragment.get().getOrder());
            move(findLaststDataFragment.get(),findProperBlank.get());
            findLaststDataFragment=findLaststDataFragment();
            findProperBlank=findFirstLongEnoughBlank(findLaststDataFragment.get().getLength());

        }

    }

    private Optional<Fragment> findFirstLongEnoughBlank(int length) {
        return area.stream()
                .filter(f->((!f.isData()) && (f.getLength()>length)))
                .sorted(Comparator.comparingInt(f->f.getOrder()))
                .findFirst();
    }

    private Optional<Fragment> findFirstBlank(){
        return area.stream()
                .filter(f->((!f.isData()) && (f.getLength()>0)))
                .sorted(Comparator.comparingInt(f->f.getOrder()))
                .findFirst();
    }
    private Optional<Fragment> findLaststDataFragment(){
        return area.stream()
                .filter(f->((f.isData()) && (f.getLength()>0)))
                .sorted(Comparator.comparingInt((Fragment f)->f.getOrder()).reversed())
                .findFirst();
    }

    private void move(Fragment from, Fragment to){
        int dataLengthToMove=Math.min(from.getLength(), to.getLength());
        int lengthOfRemainingBlank=0;
        boolean emptyFrom=false;
        if(dataLengthToMove<to.getLength()){
            lengthOfRemainingBlank=to.getLength()-dataLengthToMove;
        }
        if(dataLengthToMove==from.getLength()){
            emptyFrom=true;
        }
        to.setData(true);
        to.setLength(dataLengthToMove);
        to.setFileID(from.getFileID());
        from.setLength(from.getLength()-dataLengthToMove);
        if(emptyFrom){
            from.setData(false);
            from.setFileID(-1);
//            uniteBlanks
        }
//        else{
//            increaseBlankAtTheEnd(dataLengthToMove);
//        }
        if(lengthOfRemainingBlank>0){
            insertEmptyFragmentAsSeparator(to.getOrder(),lengthOfRemainingBlank);
        }
        reorder();

    }

    private void reorder() {
        int startPosition=0;
        for (int i = 0; i < area.size(); i++) {
            Fragment fragment=area.get(i);
            fragment.setOrder(i);
            fragment.setStartFrom(startPosition);
            startPosition+= fragment.getLength();
        }
    }

    private void insertEmptyFragmentAsSeparator(int order, int lengthOfRemainingBlank) {
        Fragment blankFragment=new Fragment(order,false,0,lengthOfRemainingBlank,-1);
        area.add(order+1,blankFragment);
    }

    private void fillArea(List<Integer> inputList) {
        boolean isData=true;
        int startFrom=0;
        int fileID=0;

        for (int i = 0; i < inputList.size(); i++) {
            int length= inputList.get(i);
            int value=fileID;
            if(!isData){
                value=-1;
            }
            area.add(new Fragment(i,isData,startFrom,length,value));
            if(isData){
                fileID++;
            }
            isData=!isData;
            startFrom+=length;
        }
    }

    private List<Integer> createInputListFromString(String input){
        List<Integer> inputList = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            inputList.add(Integer.valueOf(String.valueOf(input.charAt(i))));
        }
        return inputList;
    }

    public List<Fragment> getArea() {
        return area;
    }
}
