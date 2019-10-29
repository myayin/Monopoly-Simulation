public class Dice {

    private int firstFace;
    private int secondFace;
    private int sum;

    public int roll() {
        return (int) (Math.ceil(Math.random() * 5) + 1);
    }

    public int getFirstFace() {
        return firstFace;
    }

    public void setFirstFace(int firstFace) {
        this.firstFace = firstFace;
    }

    public int getSecondFace() {
        return secondFace;
    }

    public void setSecondFace(int secondFace) {
        this.secondFace = secondFace;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
