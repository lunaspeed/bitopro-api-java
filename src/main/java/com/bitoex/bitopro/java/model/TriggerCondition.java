package com.bitoex.bitopro.java.model;

public enum TriggerCondition {

    GREATER_THAN_OR_EQUAL_TO {
        @Override
        public String asCondition() {
            return ">=";
        }
    },
    LESS_THAN_OR_EQUAL_TO {
        @Override
        public String asCondition() {
            return "<=";
        }
    };

    public abstract String asCondition();

    public static TriggerCondition fromString(String condition) {
        if (condition.equals(">=")) {
            return GREATER_THAN_OR_EQUAL_TO;
        }
        else if (condition.equals("<=")) {
            return LESS_THAN_OR_EQUAL_TO;
        } else {
            throw new IllegalArgumentException(condition + " is not accepted");
        }
    }
}
