package sample;

import java.util.HashMap;

public class Path {

    private int step1;
    private int step2;
    private int step3;
    private int step4;
    private int step5;
    private int length;

    public Path(int step1, int step2, int step3, int step4, int step5) {
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;
        this.step4 = step4;
        this.step5 = step5;
    }

    public void calcLength(HashMap<String, Integer> length) {
        String keyLength1 = step1 + Integer.toString(step2);
        String keyLength2 = step2 + Integer.toString(step3);
        String keyLength3 = step3 + Integer.toString(step4);
        String keyLength4 = step4 + Integer.toString(step5);
        String keyLength5 = step5 + Integer.toString(step1);
        int length1 = length.get(keyLength1);
        int length2 = length.get(keyLength2);
        int length3 = length.get(keyLength3);
        int length4 = length.get(keyLength4);
        int length5 = length.get(keyLength5);
        this.length = length1 + length2 + length3 + length4 + length5;
    }

    public String getPath(boolean hum) {
        String path;
        if (hum) {
            path = "\"" + (step1 + 1) + "->" + (step2 + 1) + "->" + (step3 + 1) + "->" + (step4 + 1) + "->" + (step5 + 1) + "->" + (step1 + 1) + "\"";
        } else {
            path = Integer.toString(step1) + step2 + step3 + step4 + step5 + step1;
        }
        return path;
    }

    public int getStep1() {
        return step1;
    }

    public void setStep1(int step1) {
        this.step1 = step1;
    }

    public int getStep2() {
        return step2;
    }

    public void setStep2(int step2) {
        this.step2 = step2;
    }

    public int getStep3() {
        return step3;
    }

    public void setStep3(int step3) {
        this.step3 = step3;
    }

    public int getStep4() {
        return step4;
    }

    public void setStep4(int step4) {
        this.step4 = step4;
    }

    public int getStep5() {
        return step5;
    }

    public void setStep5(int step5) {
        this.step5 = step5;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
