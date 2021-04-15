package Net;

public class Nodes {
    String nodeName;
    String nodeIP;
    int portNo;

    public Nodes(String nodeName, String nodeIP, int portNo) {
        this.nodeName = nodeName;
        this.nodeIP = nodeIP;
        this.portNo = portNo;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeIP() {
        return nodeIP;
    }

    public void setNodeIP(String nodeIP) {
        this.nodeIP = nodeIP;
    }

    public int getPortNo() {
        return portNo;
    }

    public void setPortNo(int portNo) {
        this.portNo = portNo;
    }
}
