package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EightBall {
    List<String> answers;

    public EightBall(){
    }
    public List<String> getAnswers() {
        return answers;
    }

    public void run () {


        String answer0 = "Well the outlook seems positive.";
        String answer1 = "So sorry but probably not.";
        String answer2 = "Of course!";
        String answer3 = "You already know that answer.";
        String answer4 = "Umm ask again, you were breaking up.";
        String answer5 = "If you were better you'd be better ya know.";
        String answer6 = "Try harder and then maybe so.";
        String answer7 = "Dont give up keep rollin. Make it happen";
        String answer8 = "Too much fast food, Its not good. Really";
        String answer9 = "Well when you put it that way it seems like a good idea";

        List<String> answers = new ArrayList<>();
        answers.add(answer0);
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        answers.add(answer5);
        answers.add(answer6);
        answers.add(answer7);
        answers.add(answer8);
        answers.add(answer9);


        Scanner userInput = new Scanner(System.in);
        System.out.println("\nWelcome to the magic 8 ball!!");
        System.out.println("Go ahead and ask me a question.....\n");
        String userResponse = userInput.nextLine();
        System.out.println("hmmmm let me seeeeee\n" + "you asked \n" + userResponse );

        System.out.println("******* HERE \n" + "*********** WE \n" + "************** GO \n");
        System.out.println("SHAKE SHAKE SHAKE    ANDDDDDDD");
            int random = (int)(Math.random()*10);
        System.out.println(answers.get(random));



    }

}