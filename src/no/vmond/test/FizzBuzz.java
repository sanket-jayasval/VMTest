package no.vmond.test;

public class FizzBuzz {

    private final  String FIZZ = "Fizz";
    private final  String BUZZ = "Fizz";

    public static void main(String[] args) {

        FizzBuzz bazz = new FizzBuzz();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i=1; i<100; i++) {
            bazz.generatePrintSequence(i, stringBuilder);
        }

        System.out.println(stringBuilder.toString());
    }

    /**
     *  Print numbers in specific pattern
     *  Rules
     *      i) number is multiple of 3 then print "Fizz" e.g 3,6,9 for this number o/p should be "Fizz"
     *      i) number is multiple of 5 then print "Fizz" e.g 5,10,15 for this number o/p should be "Buzz"
     *      i) number is multiple of 3 & 5 then print "Fizz" e.g 15,30 for this number o/p should be "FizzBuzz"
     */
    private void generatePrintSequence(int num, StringBuilder stringBuilder) {
        boolean isFactorable = false ;
        if(num%3==0) {
            stringBuilder.append(FIZZ) ;
            isFactorable = true ;
        }
        if(num%5==0) {
            stringBuilder.append(BUZZ) ;
            isFactorable = true ;
        }
        if(!isFactorable) {
            stringBuilder.append(num) ;
        }
        stringBuilder.append("\n") ;
    }
}
