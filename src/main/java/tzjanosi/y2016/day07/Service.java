package tzjanosi.y2016.day07;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<IPAddress> ipAddresses = new ArrayList<>();

    public Service(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        IPAddress ipAddress = new IPAddress(line);
        ipAddresses.add(ipAddress);
    }

    public int calculateNumberOfTLSSupporters() {
        return (int) ipAddresses.stream()
                .filter(IPAddress::supportTLS)
                .count();
    }
}
