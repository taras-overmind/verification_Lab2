package org.example;

public class Node implements Cloneable
{
    Node prev;
    StringBuilder chain;
    Integer state;
    boolean isFinal;
    public Node(Node prev, String letter, int state, boolean isFinal)
    {
        this.prev = prev;
        this.state = state;
        this.chain = new StringBuilder(letter);
        this.isFinal = isFinal;
    }
    public Node append(StringBuilder chain)
    {
        this.chain.append(chain);
        return this;
    }
    public Node clone() throws CloneNotSupportedException {
        var node = (Node) super.clone();
        node.chain = new StringBuilder(this.chain);
        return node;
    }
    public String getChain() { return chain.toString(); }
    public boolean isLoop() throws CloneNotSupportedException {
        int finalStates = 1;
        Node probablyChainLoop = getSubChains(this, finalStates);
        if (probablyChainLoop != null)
            while (true)
            {
                Node workChain = getSubChains(probablyChainLoop.prev, finalStates);
                if (workChain == null)
                    return false;
                if (probablyChainLoop.getChain().equals(workChain.getChain()))
                    return true;
                probablyChainLoop = workChain.append(probablyChainLoop.chain);
                finalStates +=1;
            }
        return false;
    }

    private Node getSubChains(Node node, int finalStates) throws CloneNotSupportedException {
        if (node.prev == null)
            return null;
        if (node.prev.isFinal)
        {
            finalStates -= 1;
            if (finalStates == 0)
                return node;
        }
        return getSubChains(node.prev.clone().append(node.chain), finalStates);
    }
}