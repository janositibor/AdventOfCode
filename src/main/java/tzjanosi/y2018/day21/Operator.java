package tzjanosi.y2018.day21;

import java.util.ArrayList;
import java.util.List;

public enum Operator {
    ADDR {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) + input.get(arguments.get(1)));
            return output;
        }
    },
    ADDI {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) + arguments.get(1));
            return output;
        }
    },
    MULR {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) * input.get(arguments.get(1)));
            return output;
        }
    },
    MULI {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) * arguments.get(1));
            return output;
        }
    },
    BANR {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) & input.get(arguments.get(1)));
            return output;
        }
    },
    BANI {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) & arguments.get(1));
            return output;
        }
    },
    BORR {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) | input.get(arguments.get(1)));
            return output;
        }
    },
    BORI {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)) | arguments.get(1));
            return output;
        }
    },
    SETR {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), input.get(arguments.get(0)));
            return output;
        }
    },
    SETI {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            output.set(arguments.get(2), (long) arguments.get(0));
            return output;
        }
    },
    GTIR {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            long result = 0;
            if (arguments.get(0) > input.get(arguments.get(1))) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    },
    GTRI {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            long result = 0;
            if (input.get(arguments.get(0)) > arguments.get(1)) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    },
    GTRR {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            long result = 0;
            if (input.get(arguments.get(0)) > input.get(arguments.get(1))) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    },
    EQIR {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            long result = 0;
            if (arguments.get(0).equals(input.get(arguments.get(1)))) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    },
    EQRI {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            long result = 0;
            if (input.get(arguments.get(0)).equals((long) arguments.get(1))) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    },
    EQRR {
        @Override
        public List<Long> execute(List<Long> input, List<Integer> arguments) {
            List<Long> output = new ArrayList<>(List.copyOf(input));
            long result = 0;
            if (input.get(arguments.get(0)).equals(input.get(arguments.get(1)))) {
                result = 1;
            }
            output.set(arguments.get(2), result);
            return output;
        }
    };

    public abstract List<Long> execute(List<Long> input, List<Integer> arguments);
}
