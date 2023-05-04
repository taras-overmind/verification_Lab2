import org.example.DFA;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.startsWith;
import static org.testng.Assert.*;



public class TestDFA {
    @BeforeClass
    public void setUp(){
        System.out.println("Початок тестів");
    }
    @BeforeMethod
    public void setTest(){
        System.out.println("Початок тесту");
    }
    @Test(groups = {"constructor"})
    void testConstructor(){
        DFA automaton = new DFA();
        assertNotNull(automaton.alphabet);
        assertNotNull(automaton.transitions);
        assertNotNull(automaton.finalStates);

        assertTrue(automaton.alphabet.isEmpty());
        assertTrue(automaton.states.isEmpty());
        assertTrue(automaton.finalStates.isEmpty());
    }

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod(){
        return new Object[][] {{3}, {4}, {5}};
    }

    @Test(groups = {"data"})
    void AutomatonTest1() throws Exception {
        DFA dfa = new DFA();
        dfa.setnAlphabet(2);
        dfa.setnStates(3);
        dfa.setInitState(0);
        dfa.setFinalStates(Set.of(2));

        dfa.addTransition(0, 'a', 1);
        dfa.addTransition(0, 'b', 2);
        dfa.addTransition(1, 'b', 2);
        dfa.addTransition(2, 'b', 1);

        assertEquals(dfa.finalStates.size(), 1);
    }

    @Test(groups={"data"})
    void AutomatonTest2() throws Exception {
        DFA dfa = new DFA();
        dfa.setnAlphabet(2);
        dfa.setnStates(3);
        dfa.setInitState(0);

        dfa.setFinalStates(Set.of(2));
        dfa.addTransition(0, 'a', 1);
        dfa.addTransition(0, 'b', 2);
        dfa.addTransition(1, 'b', 2);
        dfa.addTransition(2, 'b', 1);

        assertThat(dfa.alphabet, containsInAnyOrder('a', 'b'));
        assertThat(dfa.finalStates, containsInAnyOrder(2));
        assertEquals(dfa.finalStates.size(), 1);
    }

    @Test(groups={"data"})
    void AutomatonTest4() throws Exception {
        DFA dfa = new DFA();
        dfa.setnAlphabet(2);
        dfa.setnStates(3);
        dfa.setInitState(0);
        dfa.setFinalStates(Set.of(2));
        dfa.addTransition(0, 'a', 1);
        dfa.addTransition(0, 'b', 0);
        dfa.addTransition(1, 'a', 1);
        dfa.addTransition(1, 'b', 0);
        dfa.addTransition(2, 'a', 2);
        dfa.addTransition(2, 'b', 1);

        var output = dfa.findAllAcceptedWords();

        assertThat(output.toString(), startsWith("[]"));
    }

    @Test(groups = {"data"})
    void AutomatonTest3() throws Exception {
        DFA dfa = new DFA();
        dfa.setnAlphabet(2);
        dfa.setnStates(5);
        dfa.setInitState(0);
        dfa.setFinalStates(Set.of(1, 4));
        dfa.addTransition(0, 'a', 1);
        dfa.addTransition(0, 'b', 1);
        dfa.addTransition(1, 'a', 3);
        dfa.addTransition(1, 'b', 2);
        dfa.addTransition(2, 'a', 4);
        dfa.addTransition(2, 'b', 3);
        dfa.addTransition(3, 'a', 4);
        dfa.addTransition(3, 'b', 4);

        var output = dfa.findAllAcceptedWords();

        assert(output.toString().contains("aaa"));
        assert(output.toString().contains("aba"));
        assert(output.toString().contains("bbba"));
        assert(output.toString().contains("abba"));
        assert(output.toString().contains("bbbb"));
    }

    @Test(groups = {"data"}, dataProvider = "data-provider", expectedExceptions = Exception.class)
    void ExceptionTest(int state) throws Exception {
        DFA dfa = new DFA();
        dfa.setnStates(3);
        dfa.setInitState(state);
    }

}
