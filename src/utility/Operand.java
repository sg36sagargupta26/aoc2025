package utility;

public enum Operand {
    ADD("+"),
    MULTIPLY("*");

    final String operator;

    Operand(String operator) {
        this.operator = operator;
    }
    public long doOperation(long a, long b){
        switch (this){
            case ADD -> {
                return a + b;
            }
            case MULTIPLY -> {
                return a * b;
            }
        }
        return 0;
    }
    public static Operand getOperand(String operator){
        if(Operand.ADD.operator.equals(operator)){
            return Operand.ADD;
        }
        return Operand.MULTIPLY;
    }

    public long starter(){
        switch (this){
            case ADD -> {
                return 0L;
            }
            case MULTIPLY -> {
                return 1L;
            }
        }
        return 1;
    }
}
