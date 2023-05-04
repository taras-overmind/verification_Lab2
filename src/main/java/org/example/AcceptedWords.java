package org.example;


import java.util.*;

public class AcceptedWords {
    private final DFA dfa;

    public AcceptedWords(DFA dfa) {this.dfa = dfa;}

    public Set<String> getWords() throws CloneNotSupportedException {
        var words = new HashSet<String>();
        Node head = new Node(null,
                "",
                dfa.initState,
                dfa.finalStates.contains(dfa.initState));
        for (var word : findWords(head))
            words.add(word.getChain());
        return words;
    }
    private Set<Node> findWords(Node tail) throws CloneNotSupportedException {
        Set<Node> words = new HashSet<>();
        if (tail.isFinal)
        {
            if (tail.isLoop())
                return words;
            words.add(tail);
        }

        for (var entity : dfa.transitions.getOrDefault(tail.state, new HashMap<>()).entrySet())
        {
            var node = new Node(tail,
                    String.valueOf(entity.getKey()),
                    entity.getValue(),
                    dfa.finalStates.contains(entity.getValue()));
            for (var endWord : findWords(node))
                words.add(tail.clone().append(endWord.chain));
        }

        return words;
    }


}

