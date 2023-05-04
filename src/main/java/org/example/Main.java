package org.example;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.print("Enter file path: ");
            File file = new File("src/main/resources/file.txt");
            Scanner scanner = new Scanner(file);
            if (!file.exists())
                throw new Exception();
            DFA dfa=new DFA();
            dfa.setnAlphabet(Integer.parseInt(scanner.nextLine()));
            dfa.setnStates( Integer.parseInt(scanner.nextLine()));
            dfa.setInitState( Integer.parseInt(scanner.nextLine()));
            dfa.setFinalStates(new HashSet<>());


            String[] lineFinalStates = scanner.nextLine().split(" ");
            var nFinalStates= Integer.parseInt(lineFinalStates[0]);
            for (int i = 0; i < nFinalStates; i++) {
                int finalState = Integer.parseInt(lineFinalStates[i + 1]);
                if (finalState >= dfa.nStates)
                    throw new Exception("");
                dfa.finalStates.add(finalState);
            }


            while (scanner.hasNextLine()) {
                String[] transition = scanner.nextLine().split(" ");
                int from = Integer.parseInt(transition[0]);
                char letter = transition[1].charAt(0);
                int to = Integer.parseInt(transition[2]);
                if (from >= dfa.nStates || to >= dfa.nStates)
                    throw new Exception("");
                if ((int) letter > 96 + dfa.nAlphabet)
                    throw new Exception("");

                dfa.addTransition(from, letter, to);
            }

            var words = dfa.findAllAcceptedWords();
            for (var word : words)
                System.out.println(word);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}