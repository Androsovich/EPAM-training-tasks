package com.epam.winter.java.lab.enums;

public enum Operation {
    PLUS {
        private final static String signOperation = "+";
        Double operand1;
        Double operand2;

        public String getSignOperation() {
            return signOperation;
        }

        @Override
        public String operationToString() {
            return operand1 + signOperation + operand2;
        }

        @Override
        public Double operation(Double operand1, Double operand2) {
            this.operand1 = operand1;
            this.operand2 = operand2;
            return operand1 + operand2;
        }
    }, MINUS {
        final String operation = "-";
        Double operand1;
        Double operand2;

        public String getSignOperation() {
            return operation;
        }

        @Override
        public Double operation(Double operand1, Double operand2) {
            this.operand1 = operand1;
            this.operand2 = operand2;
            return operand1 - operand2;
        }

        @Override
        public String operationToString() {
            return operand1 + operation + operand2;
        }

    }, MULTIPLICATION {
        final String signOperation = "*";
        Double operand1;
        Double operand2;

        public String getSignOperation() {
            return signOperation;
        }

        @Override
        public Double operation(Double operand1, Double operand2) {
            this.operand1 = operand1;
            this.operand2 = operand2;
            return operand1 * operand2;
        }

        @Override
        public String operationToString() {
            return operand1 + signOperation + operand2;
        }
    }, DIVISION {

        final String signOperation = "/";
        Double operand1;
        Double operand2;

        public String getSignOperation() {
            return signOperation;
        }

        @Override
        public Double operation(Double operand1, Double operand2) {
            this.operand1 = operand1;
            this.operand2 = operand2;
            return operand1 / operand2;
        }

        @Override
        public String operationToString() {
            return operand1 + signOperation + operand2;
        }
    };

    public static Operation getOperation(String operation) {
        Operation resultOperation = null;
        for (Operation value : Operation.values()) {
            if (value.getSignOperation().equals(operation))
                resultOperation = value;
        }
        return resultOperation;
    }

    public abstract String getSignOperation();

    public abstract Double operation(Double operand1, Double operand2);

    public abstract String operationToString();
}
