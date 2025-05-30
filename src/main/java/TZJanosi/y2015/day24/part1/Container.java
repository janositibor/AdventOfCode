package TZJanosi.y2015.day24.part1;

import java.util.HashSet;
import java.util.Set;

public class Container {
    private Set<Integer> packages=new HashSet<>();
    private int numberOfPackages=0;
    private int weight=0;
    private long qe=0;

    public Container() {
    }

    public Container(Container original){
        numberOfPackages= original.numberOfPackages;
        weight=original.weight;
        qe= original.qe;
        packages=new HashSet<>(original.packages);

    }

    public void addPackage(int value){
        packages.add(value);
        updatePackageProperties(value);
    }

    private void updatePackageProperties(int value) {
        numberOfPackages++;
        weight+=value;
        qe=(qe==0?value:value*qe);
    }

    public Set<Integer> getPackages() {
        return packages;
    }

    public int getWeight() {
        return weight;
    }

    public int getNumberOfPackages() {
        return numberOfPackages;
    }

    public long getQe() {
        return qe;
    }

    @Override
    public String toString() {
        return "Container{" +
                "packages=" + packages +
                '}';
    }
}
