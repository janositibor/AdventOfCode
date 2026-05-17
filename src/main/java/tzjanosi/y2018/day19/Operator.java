package tzjanosi.y2018.day19;

import java.util.ArrayList;
import java.util.List;

public enum Operator {
    ADDR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) + input.get(arguments.get(1)));
            return output;
        }
    },
    ADDI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) + arguments.get(1));
            return output;
        }
    },
    MULR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) * input.get(arguments.get(1)));
            return output;
        }
    },
    MULI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) * arguments.get(1));
            return output;
        }
    },
    BANR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) & input.get(arguments.get(1)));
            return output;
        }
    },
    BANI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) & arguments.get(1));
            return output;
        }
    },
    BORR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) | input.get(arguments.get(1)));
            return output;
        }
    },
    BORI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) | arguments.get(1));
            return output;
        }
    },
    SETR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)));
            return output;
        }
    },
    SETI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), arguments.get(0));
            return output;
        }
    },
    GTIR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (arguments.get(0) > input.get(arguments.get(1))) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    },
    GTRI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (input.get(arguments.get(0)) > arguments.get(1)) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    },
    GTRR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (input.get(arguments.get(0)) > input.get(arguments.get(1))) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    },
    EQIR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (arguments.get(0).equals(input.get(arguments.get(1)))) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    },
    EQRI {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (input.get(arguments.get(0)).equals(arguments.get(1))) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    },
    EQRR {
        @Override
        public List<Integer> execute(List<Integer> input, List<Integer> arguments) {
            List<Integer> output = new ArrayList<>(List.copyOf(input));
            int result = 0;
            if (input.get(arguments.get(0)).equals(input.get(arguments.get(1)))) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    };

    public abstract List<Integer> execute(List<Integer> input, List<Integer> arguments);
}
