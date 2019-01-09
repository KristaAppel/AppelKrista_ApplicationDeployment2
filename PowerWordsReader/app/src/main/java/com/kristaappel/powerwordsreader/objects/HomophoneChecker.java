// Krista Appel
// Application Deployment 2: Android
// HomophoneChecker.java

package com.kristaappel.powerwordsreader.objects;

import java.util.ArrayList;


public class HomophoneChecker {

    public static ArrayList<String> checkForHomophones(String result, ArrayList<String> resultsList){
        // Speech recognition is a little buggy.  These will help:
        switch (result){
            case "B":
                resultsList.add("be");
                break;
            case "4":
                resultsList.add("for");
                break;
            case "1":
                resultsList.add("one");
                break;
            case "C":
                resultsList.add("see");
                break;
            case "their":
                resultsList.add("there");
                resultsList.add("they're");
                break;
            case "cant":
                resultsList.add("can't");
                break;
            case "m": case "AM":
                resultsList.add("am");
                break;
            case "no":
                resultsList.add("know");
                break;
            case "He":
                resultsList.add("he");
                break;
            case "I":
                resultsList.add("eye");
                break;
            case "two":
                resultsList.add("to");
                resultsList.add("too");
                break;
            case "chews":
                resultsList.add("choose");
                break;
            case "bye":
                resultsList.add("by");
                resultsList.add("buy");
                break;
            case "sent":
                resultsList.add("cent");
                break;
            case "Wii":
                resultsList.add("we");
                break;
            case "pool":
                resultsList.add("pull");
                break;
            case "School":
                resultsList.add("school");
                break;
            case "there":
                resultsList.add("their");
                resultsList.add("they're");
                break;
            case "Wok":
                resultsList.add("walk");
                break;
            case "who's":
                resultsList.add("whose");
                break;
            case "hi":
                resultsList.add("high");
                break;
            case "whined":
                resultsList.add("wind");
                break;
            case "Ben":
                resultsList.add("been");
                break;
            case "bored":
                resultsList.add("board");
                break;
            case "City":
                resultsList.add("city");
                break;
            case "close":
                resultsList.add("clothes");
                break;
            case "Giant":
                resultsList.add("giant");
                break;
            case "Library":
                resultsList.add("library");
                break;
            case "Magic":
                resultsList.add("magic");
                break;
            case "Moon":
                resultsList.add("moon");
                break;
            case "Mountain":
                resultsList.add("mountain");
                break;
            case "mr.":
                resultsList.add("Mr.");
                break;
            case "Secret":
                resultsList.add("secret");
                break;
            case "Sal":case "shell":
                resultsList.add("shall");
                break;
            case "stud":
                resultsList.add("stood");
                break;
            case "Wild":
                resultsList.add("wild");
                break;
            case "Beauty":
                resultsList.add("beauty");
                break;
            case "Bowl":
                resultsList.add("bowl");
                break;
            case "come":case "call":
                resultsList.add("calm");
                resultsList.add("comb");
                break;
            case "Danger":
                resultsList.add("danger");
                break;
            case "Escape":
                resultsList.add("escape");
                break;
            case "Island":
                resultsList.add("island");
                break;
            case "mint":
                resultsList.add("meant");
                break;
            case "Pleasant":
                resultsList.add("pleasant");
                break;
            case "too":
                resultsList.add("to");
                resultsList.add("two");
                break;
            case "right":
                resultsList.add("write");
                break;
            case "Carrie":
                resultsList.add("carry");
                break;
            case "Earth":
                resultsList.add("earth");
                break;
            case "sore":
                resultsList.add("sure");
                break;
            case "Young":
                resultsList.add("young");
                break;
            case "c**":
                resultsList.add("come");
                break;
            case "y":
                resultsList.add("why");
                break;
            case "Boy":
                resultsList.add("boy");
                break;
            case "butt":
                resultsList.add("but");
                break;
            case "buy":
                resultsList.add("by");
                resultsList.add("bye");
                break;
            case "by":
                resultsList.add("bye");
                resultsList.add("buy");
                break;
            case "wood":
                resultsList.add("would");
                break;
            case "your":
                resultsList.add("you're");
                break;
            case "doctor":
                resultsList.add("Dr.");
                break;
            case "our":
                resultsList.add("hour");
                resultsList.add("are");
                break;
            case "aight":
                resultsList.add("I");
                resultsList.add("eye");
                break;
            case "Inn":
                resultsList.add("in");
                break;
            case "Of":
                resultsList.add("of");
                break;
            case "new":
                resultsList.add("knew");
                break;
            case "mister":
                resultsList.add("Mr.");
                break;
            case "mrs.":
                resultsList.add("Mrs.");
                break;
            case "Miss":
                resultsList.add("Ms.");
                break;
            case "o clock":
                resultsList.add("o'clock");
                break;
            case "peace":
                resultsList.add("piece");
                break;
            case "way":
                resultsList.add("weigh");
                break;
            case "com":
                resultsList.add("calm");
                break;
            case "Barry":
                resultsList.add("bury");
                break;
            case "blonde":
                resultsList.add("blond");
                break;
            case "serial":
                resultsList.add("cereal");
                break;
            case "caller":
                resultsList.add("collar");
                break;
            case "guessed":
                resultsList.add("guest");
                break;
            case "horse":
                resultsList.add("hoarse");
                break;
            case "patients":
                resultsList.add("patience");
                break;
            case "root":
                resultsList.add("route");
                break;
            case "so":
                resultsList.add("sew");
                break;
            case "AR":
                resultsList.add("are");
                resultsList.add("our");
                break;
            case "Bruce":
                resultsList.add("bruise");
                break;
            case "Avenue":
                resultsList.add("avenue");
                break;
            case "Cavs":
                resultsList.add("calves");
                break;
            case "Coco":
                resultsList.add("cocoa");
                break;
            case "College":
                resultsList.add("college");
                break;
            case "Court": case "quart":
                resultsList.add("court");
                break;
            case "Journey":
                resultsList.add("journey");
                break;
            case "League":
                resultsList.add("league");
                break;
            case "Meadow":
                resultsList.add("meadow");
                break;
            case "meth":
                resultsList.add("myth");
                break;
            case "Dimond":
                resultsList.add("diamond");
                break;
            case "worried":
                resultsList.add("worry");
                break;
            case "I am":
                resultsList.add("am");
                break;
            case "&":
                resultsList.add("and");
                break;
            case "2":
                resultsList.add("to");
                resultsList.add("two");
                resultsList.add("too");
                break;
            case "War":
                resultsList.add("were");
                break;
        }
        return resultsList;
    }
}
