package data_structures;

import java.util.*;

import data_structures.NodeObject;

/**
 * NOTE: Removing from the list will cause the indices map to become invalid.
 * NOTE: Adding partway through (not at the end of) the list will cause the indices map to become invalid.
 * Presently untested.
 * A class representing the graph using an adjacency list.
 */
public class AdjacencyList {

    //List of maps containing parent nodes and the edge weight to them.
    private Map<String,Map<String, Integer>> adjacencyList = new HashMap<>();

    //Maps nodes to their weights.
    private Map<String, Integer> nodeWeights = new HashMap<>();
    

    /**
     * Adds a node to the graph, or does nothing, if the node has already been added.
     * This may be updated to an exception.
     * @param id The unique identifier for the node.
     */
    public void addNode(String id){

        //Check that the node hasn't already been added. If it has, end the function.
        if (adjacencyList.containsKey(id)){
            return;
        }

        //Add a new map to the adjacency list representing that node.
        Map<String, Integer> newEntry = new HashMap<>();
        adjacencyList.put(id, newEntry);

    }

    /**
     * Adds the weight to an existing node.
     * @param id The unique identifier for the node.
     * @param cost The cost, in time units, for the node.
     */
    public void addNodeWeight(String id, int cost){
        //Add node to weights map.
        nodeWeights.put(id,cost);
    }
    
 

    /**
     * Adds an edge to the graph.
     * If the edge contains a node that already is in the graph, addNode is called.
     * @param source The "head" of the node.
     * @param destination The "tail" of the node.
     * @param cost The cost of the edge.
     */
    public void addEdge(String source, String destination, int cost){
        //Add nodes if they haven't been added. (AddNode method performs this check)
        addNode(source);
        addNode(destination);

        //Add the edge and its weight to the map for the node.
        adjacencyList.get(destination).put(source,cost);
    }

    //Add other methods here to make recursive lookup easier, and replace these getters.
    /**
     * Checks if the newNode(String) is dependent on node(Any particular Node with a string name)
     * @return true if is dependent and is inside the adjacency list : false if not in the map
     */
    public boolean isDependent(NodeObject node, String newNode){
        if(adjacencyList.get(newNode).containsKey(node.getNodeName())){
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Integer> getParents(String node){
        return adjacencyList.get(node);
    }

    /**
     * Getting the edge weight from the currentNode to the new valid node
     * @param currentNode
     * @param nextNode
     * @return
     */
    public int getEdgeWeight(NodeObject currentNode, String nextNode){
        if(currentNode.getNodeName().equals("rootNode")){
            return 0;
        } else {
            return adjacencyList.get(nextNode).get(currentNode.getNodeName());
        }
    }

    /**
     * Gets all source nodes in the adjacency list.
     * Note that this is O(n), and should be stored into a variable if possible, rather than run often.
     * @return a set of Strings containing the names of all source nodes.
     */
    public List<String> getSourceNodes(){
        List<String> sources = new ArrayList<>();
        for(String n : adjacencyList.keySet()){
            if (adjacencyList.get(n).isEmpty()) {
                //If the node's map of parents is empty, add it to the set of sources
                sources.add(n);
            }
        }
        return sources;
    }
    public Set<String> getNodes(){
        return adjacencyList.keySet();
    }

    public Map<String, Map<String,Integer>> getAdjacencyList(){
        return adjacencyList;
    }
    public Map<String, Integer> getNodeWeights() {
        return nodeWeights;
    }
}
