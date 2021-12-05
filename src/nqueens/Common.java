package nqueens;

public  class Common {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static int getRandomNumberFromZeroTo(int max){
        return getRandomNumber(0,max);
    }
}
