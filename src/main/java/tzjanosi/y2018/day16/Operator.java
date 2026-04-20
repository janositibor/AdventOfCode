package tzjanosi.y2018.day16;

import java.util.ArrayList;
import java.util.List;

public enum Operator {
    ADDR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(3), input.get(arguments.get(1)) + input.get(arguments.get(2)));
            return output;
        }
    },
    ADDI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(3), input.get(arguments.get(1)) + arguments.get(2));
            return output;
        }
    },
    MULR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(3), input.get(arguments.get(1)) * input.get(arguments.get(2)));
            return output;
        }
    },
    MULI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(3), input.get(arguments.get(1)) * arguments.get(2));
            return output;
        }
    },
    BANR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(3), input.get(arguments.get(1)) & input.get(arguments.get(2)));
            return output;
        }
    },
    BANI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(3), input.get(arguments.get(1)) & arguments.get(2));
            return output;
        }
    },
    BORR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(3), input.get(arguments.get(1)) | input.get(arguments.get(2)));
            return output;
        }
    },
    BORI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(3), input.get(arguments.get(1)) | arguments.get(2));
            return output;
        }
    },
    SETR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(3), input.get(arguments.get(1)));
            return output;
        }
    },
    SETI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(3), arguments.get(1));
            return output;
        }
    },
    GTIR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (arguments.get(1) > input.get(arguments.get(2))) {
                result = 1;
            }
            output.set(arguments.get(3), result);
            return output;
        }
    },
    GTRI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (input.get(arguments.get(1)) > arguments.get(2)) {
                result = 1;
            }
            output.set(arguments.get(3), result);
            return output;
        }
    },
    GTRR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (input.get(arguments.get(1)) > input.get(arguments.get(2))) {
                result = 1;
            }
            output.set(arguments.get(3), result);
            return output;
        }
    },
    EQIR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (arguments.get(1).equals(input.get(arguments.get(2)))) {
                result = 1;
            }
            output.set(arguments.get(3), result);
            return output;
        }
    },
    EQRI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (input.get(arguments.get(1)).equals(arguments.get(2))) {
                result = 1;
            }
            output.set(arguments.get(3), result);
            return output;
        }
    },
    EQRR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (input.get(arguments.get(1)).equals(input.get(arguments.get(2)))) {
                result = 1;
            }
            output.set(arguments.get(3), result);
            return output;
        }
    };

    public abstract List<Integer> execute(List<Integer> input, List<Integer> arguments);
}
