package org.example;

import java.util.*;

public class DFA {
    public  Set<Character> alphabet;
    public int nAlphabet;
    public int nStates;

    public  Set<Integer> states;
    public  Integer initState;
    public  Set<Integer> finalStates;
    public  Map<Integer, Map<Character, Integer>> transitions= new HashMap<>();

    public void setnAlphabet(int nAlphabet) {
        this.nAlphabet = nAlphabet;
        for (int i = 0; i < nAlphabet; i++)
            alphabet.add((char) (97 + i));
    }

    public void setnStates(int nStates) {
        this.nStates = nStates;
        for (int i = 0; i < nStates; i++)
            states.add(i);
    }

    public void setInitState(Integer initState) throws Exception {
        if(initState>=nStates || initState<0)
            throw new Exception("");
        this.initState = initState;
    }

    public void setFinalStates(Set<Integer> finalStates) {
        this.finalStates = finalStates;
    }

    public DFA() {
        finalStates = new HashSet<>();
        alphabet = new HashSet<>();
        states = new HashSet<>();
    }
    public void addTransition(int from, char letter, int to) throws Exception {
        if (!transitions.containsKey(from))
            transitions.put(from, new HashMap<>());

        if (transitions.get(from).containsKey(letter))
            throw new Exception("");
        if (from < to)
            transitions.get(from).put(letter, to);
    }


    public Set<String> findAllAcceptedWords() throws Exception {
        AcceptedWords finder = new AcceptedWords(this);
        return finder.getWords();
    }


}

