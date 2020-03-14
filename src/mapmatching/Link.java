package mapmatching;
import java.util.ArrayList;

public class Link 
{
    private String link_ProbeID;
    private String ref_NodeID;
    private String nRef_NodeID;
    private Double length;
    private int class_Function;
    private char direction_travel;
    private int category_speed;
    private int speedLimit_Ref;
    private int speedLimit_toRef;
    private int lanes_from_Refernece;
    private int to_Ref_NumLanes;
    private char multiDigitized;
    private char urban;
    private Double timeZone;
    private ArrayList<Double[]> shape_Info;
    private ArrayList<Double[]> curvature_Info;
    private ArrayList<Double[]> slope_Info;
    private ArrayList<Probe> link_ProbePointss;

    public Link() 
    {

    }

    public String getLinkProbeID() 
    {
            return link_ProbeID;
    }

    public void setLinkPVID(String linkPVID) 
    {
            this.link_ProbeID = linkPVID;
    }

    public String getRef_NodeID() 
    {
            return ref_NodeID;
    }

    public void setRef_NodeID(String refNodeID) 
    {
            this.ref_NodeID = refNodeID;
    }

    public String getnRef_NodeID() 
    {
            return nRef_NodeID;
    }

    public void setnRef_NodeID(String nRefNodeID) 
    {
            this.nRef_NodeID = nRefNodeID;
    }

    public Double getLength() 
    {
            return length;
    }

    public void setLength(Double length) 
    {
            this.length = length;
    }

    public int getclass_Function() {
            return class_Function;
    }

    public void setclass_Function(int functionClass) 
    {
            this.class_Function = functionClass;
    }

    public char getdirection_travel() 
    {
            return direction_travel;
    }

    public void setdirection_travel(char directionOfTravel) 
    {
            this.direction_travel = directionOfTravel;
    }

    public int getcategory_speed() 
    {
            return category_speed;
    }

    public void setcategory_speed(int speedCategory) 
    {
            this.category_speed = speedCategory;
    }

    public int getspeedLimit_Ref() 
    {
            return speedLimit_Ref;
    }

    public void setspeedLimit_Ref(int fromRefSpeedLimit) 
    {
            this.speedLimit_Ref = fromRefSpeedLimit;
    }

    public int getToRefSpeedLimit() 
    {
            return speedLimit_toRef;
    }

    public void setToRefSpeedLimit(int toRefSpeedLimit) 
    {
            this.speedLimit_toRef = toRefSpeedLimit;
    }

    public int getFromRefNumLanes() 
    {
            return lanes_from_Refernece;
    }

    public void setFromRefNumLanes(int fromRefNumLanes) 
    {
            this.lanes_from_Refernece = fromRefNumLanes;
    }

    public int getToRefNumLanes() 
    {
            return to_Ref_NumLanes;
    }

    public void setToRefNumLanes(int toRefNumLanes) 
    {
            this.to_Ref_NumLanes = toRefNumLanes;
    }

    public char getMultiDigitized() 
    {
            return multiDigitized;
    }

    public void setMultiDigitized(char multiDigitized) 
    {
            this.multiDigitized = multiDigitized;
    }

    public char getUrban() 
    {
            return urban;
    }

    public void setUrban(char urban) 
    {
            this.urban = urban;
    }

    public Double getTimeZone() 
    {
            return timeZone;
    }

    public void setTimeZone(Double timeZone) 
    {
            this.timeZone = timeZone;
    }

    public ArrayList<Double[]> getShapeInfo() 
    {
            return shape_Info;
    }

    public void setShapeInfo(ArrayList<Double[]> shapeInfo) 
    {
            this.shape_Info = shapeInfo;
    }

    public ArrayList<Double[]> getCurvatureInfo() 
    {
            return curvature_Info;
    }

    public void setCurvatureInfo(ArrayList<Double[]> curvatureInfo) 
    {
            this.curvature_Info = curvatureInfo;
    }

    public ArrayList<Double[]> getSlopeInfo() 
    {
            return slope_Info;
    }

    public void setSlopeInfo(ArrayList<Double[]> slopeInfo) 
    {
            this.slope_Info = slopeInfo;
    }

    public ArrayList<Probe> getLinkProbes() 
    {
            return link_ProbePointss;
    }

    public void setLinkProbes(ArrayList<Probe> linkProbes) 
    {
            this.link_ProbePointss = linkProbes;
    }
}
