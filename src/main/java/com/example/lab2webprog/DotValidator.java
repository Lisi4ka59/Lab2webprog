package com.example.lab2webprog;

public class DotValidator {

        public boolean validate(String x, String y, String r) {
            try {
                double xValue = Double.parseDouble(x);
                double yValue = Double.parseDouble(y);
                double rValue = Double.parseDouble(r);
                return validateX(xValue) && validateY(yValue) && validateR(rValue);
            } catch (NumberFormatException | NullPointerException e) {
                return false;
            }
        }

        private boolean validateX(double x) {
            return x >= -3 && x <= 5;
        }

        private boolean validateY(double y) {
            return y >= -5 && y <= 3;
        }

        private boolean validateR(double r) {
            return r >= 1 && r <= 4;
        }
}
