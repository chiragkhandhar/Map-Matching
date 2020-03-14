package mapmatching;

public class Probe 
{
    private int sample_ID;
    private String date_Time;
    private int source_Code;
    private Double latitude;
    private Double longitude;
    private Double altitude;
    private Double speed;
    private Double heading;
    private char probe_Direction;
    private Double ref_Dist;
    private String slope;
    private String linkProbeData;

    public String getSlope() {
            return slope;
    }

    public void setSlope(String slope) {
            this.slope = slope;
    }

    public Double getDistFromRef() {
            return ref_Dist;
    }

    public void setDistFromRef(Double distFromRef) {
            this.ref_Dist = distFromRef;
    }

    public Double getDistFromLink() {
            return distFromLink;
    }

    public void setDistFromLink(Double distFromLink) {
            this.distFromLink = distFromLink;
    }

    private Double distFromLink;

    public Probe() {

    }

    public int getSampleID() {
            return sample_ID;
    }

    public void setSampleID(int sampleID) {
            this.sample_ID = sampleID;
    }

    public String getDateTime() {
            return date_Time;
    }

    public void setDateTime(String dateTime) {
            this.date_Time = dateTime;
    }

    public int getSourceCode() {
            return source_Code;
    }

    public void setSourceCode(int sourceCode) {
            this.source_Code = sourceCode;
    }

    public Double getLatitude() {
            return latitude;
    }

    public void setLatitude(Double latitude) {
            this.latitude = latitude;
    }

    public Double getLongitude() {
            return longitude;
    }

    public void setLongitude(Double longitude) {
            this.longitude = longitude;
    }

    public Double getAltitude() {
            return altitude;
    }

    public void setAltitude(Double altitude) {
            this.altitude = altitude;
    }

    public Double getSpeed() {
            return speed;
    }

    public void setSpeed(Double speed) {
            this.speed = speed;
    }

    public Double getHeading() {
            return heading;
    }

    public void setHeading(Double heading) {
            this.heading = heading;
    }

    public char getProbeDirection() {
            return probe_Direction;
    }

    public void setProbeDirection(char probeDirection) {
            this.probe_Direction = probeDirection;
    }

    public String getLinkPVID() {
            return linkProbeData;
    }

    public void setLinkPVID(String linkPVID) {
            this.linkProbeData = linkPVID;
    }    
}
