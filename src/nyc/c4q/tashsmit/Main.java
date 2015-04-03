package nyc.c4q.tashsmit;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Created by tasha.smith on 4/1/2015.
 */
public class Main {

    public static void main(String args[]) {
        System.out.println(letterCount());
    }

    //this method returns a hashMap of the Number words.txt file
    //the hashmap will contain number keys and corresponding string word values
    public static HashMap<Integer, String> numToString() {

        File myFile = new File("/Users/c4q-tashasmith/Desktop/ProjectEuler17/number words.txt");
        HashMap<Integer, String> numWords = new HashMap<Integer, String>();

        try {
            Scanner input = new Scanner(myFile);

            while (input.hasNext()) {

                String line = input.next();
                int comma1 = line.indexOf(",");
                Integer num = Integer.parseInt(line.substring(0, comma1));
                String numWord = line.substring(comma1 + 1);
                numWords.put(num, numWord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return numWords;

    }
    // for numbers 1 - 19
    public static int oneToTwenty(int number) {
        HashMap<Integer, String> numWords = numToString();

        String numWord = numWords.get(number); //get key in hashMap corresponding to the number
        int count = numWord.length(); //get length of that number and add to totalCount

        return count;
    }
    //for numbers 20 - 99
    public static int tensPlace(int number) {
        HashMap<Integer, String> numWords = numToString();

        int count = 0;

        int num = (number / 10) * 10; //find the tens place
        String numWord = numWords.get(num); //get string for tens place word
        for (int m = 1; m < 10; m++) { //run a loop to see if (i) is an even 30, 40, 50 etc
            if (number == num + m) {
                String remainder = numWords.get(m); //store remainder to a variable
                count = remainder.length(); //count now has length of temp variable
            }
        }
        int totalCount = (numWord.length() + count); //add length of numWord and count of remainder if there was one
        return totalCount;
    }
    //for numbers 100 - 999
    public static int hundredsPlace(int number) {
        HashMap<Integer, String> numWords = numToString();

        int count1 = 0;
        int count2;

        int hundredsPlace = (number / 100); //find hundreds place of number and store to hundredsPlace variable
        String hundredWord = numWords.get(hundredsPlace); //get value of corresponding key(hundredsPlace) in hashMap
        int numHundredLength = hundredWord.length(); //count now has length of that key

        if (!(number % 100 == 0)) { //if number is not evenly 100, 200, 300...
            int remainder = number % 100; //find remainder
            if (remainder < 20) {
                count1 = oneToTwenty(remainder) + 3; //get length of the remainder word, plus 3 for "and"
            } else {
                count1 = tensPlace(remainder) + 3; //get length of the remainder word, plus 3 for "and"
            }
        }
        count2 = numHundredLength + numWords.get(100).length() + count1;
        return count2;
    }
    //method that does the calculation
    public static int letterCount() {

        int totalCount = 0;

        HashMap<Integer, String> numbers = numToString();

        for (int i = 1; i < 1001; i++) {
            //number 1 - 19
            if (i < 20) {
                totalCount += oneToTwenty(i);
            }
            //numbers from 20 - 99
            else if (i > 19 && i < 100) {
                totalCount += tensPlace(i);
            }
            //numbers 100 to 999
            else if (i > 99 && i < 1000) {
                totalCount += hundredsPlace(i);
            }
            //if the number is one thousand
            else
                totalCount += numbers.get(1000).length() + 3; //one thousand
        }

        return totalCount;
    }
}
