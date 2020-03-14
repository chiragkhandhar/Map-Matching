package mapmatching;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MapMatching 
{
    static ArrayList<Probe> probePointArrayList = new ArrayList<Probe>();
    static ArrayList<Link> linkDataArrayList = new ArrayList<Link>();

    public static void main(String[] args) throws IOException 
    {
        FileOperation fileOperation = new FileOperation();
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\n Enter Probe Points file path (Along with file name and extension .csv): ");
        String fp_probleFile = sc.next();
        probePointArrayList = fileOperation.readProbePointCSV(fp_probleFile);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\n Enter Link Data file path (Along with file name and extension .csv): ");
        String fp_Linkdata = sc.next();
        System.out.println("--------------------------------------------------------------------------------");

        linkDataArrayList = fileOperation.readLinkDataCSV(fp_Linkdata);
        sc.close();

        Double minDistLink = 0.0;
        Double minDistProbe = 0.0;
        Double distanceMapMatched = 0.0;
        Double straightDistance = 0.0;
        Double previousdistMapMatched = 0.0;
        int minIndex = 0, previousProbeID = 0;

        String closestLink = "";
        String previousClosestLink = "";

        for (int probecount = 0; probecount < 100000; probecount++) 
        {
            Probe probe = probePointArrayList.get(probecount);
            Double[] mapMatchedPoint = null;
            for (int i = 0; i < 100000; i++) 
            {
                Link link = linkDataArrayList.get(i);
                List<Double> distMapMatchedPoints = new ArrayList<Double>();
                for (int j = 0; j < link.getShapeInfo().size() - 1; j++) 
                {
                        Double start_distance[] = link.getShapeInfo().get(j);
                        Double end_distance[] = link.getShapeInfo().get(j + 1);

                        Double[] vector_Line = new Double[] { end_distance[0] - start_distance[0], end_distance[1] - start_distance[1] };
                        Double[] vector_pt = new Double[] { probe.getLatitude() - start_distance[0], probe.getLongitude() - start_distance[1] };

                        Double magnitude = Math.sqrt(Math.pow(vector_Line[0], 2) + Math.pow(vector_Line[1], 2));
                        Double[] unit_vectorLine = new Double[] { vector_Line[0] / magnitude, vector_Line[1] / magnitude };

                        Double[] vector_UnitPoint = new Double[] { vector_pt[0] / magnitude, vector_pt[1] / magnitude };
                        Double product_dot = unit_vectorLine[0] * vector_UnitPoint[0] + unit_vectorLine[1] * vector_UnitPoint[1];
                        if (product_dot < 0.0) 
                                product_dot = 0.0;
                        else if (product_dot > 1.0) 
                                product_dot = 1.0;

                        mapMatchedPoint = new Double[] { vector_Line[0] * product_dot, vector_Line[1] * product_dot };
                        straightDistance = Math.sqrt(Math.pow(vector_pt[0] - mapMatchedPoint[0], 2) + Math.pow(vector_pt[1] - mapMatchedPoint[1], 2)) * 1000;
                        mapMatchedPoint[0] = mapMatchedPoint[0] + start_distance[0];
                        mapMatchedPoint[1] = mapMatchedPoint[1] + start_distance[1];
                        distMapMatchedPoints.add(straightDistance);
                }
                minDistLink = Collections.min(distMapMatchedPoints);
                if (i == 0 || minDistLink < minDistProbe) 
                {
                        minDistProbe = minDistLink;
                        closestLink = link.getLinkProbeID();
                        minIndex = distMapMatchedPoints.indexOf(minDistLink);
                        distanceMapMatched = distanceTomap_Matched(link, mapMatchedPoint, minIndex);
                }
            }
            if (minDistProbe < 20) 
            {
                if (probecount == 0) 
                    probe.setProbeDirection('U');
                else 
                {
                    if (probe.getSampleID() == previousProbeID && previousClosestLink == closestLink) 
                    {
                        if (previousdistMapMatched > distanceMapMatched) 
                            probe.setProbeDirection('T');
                        else if (previousdistMapMatched < distanceMapMatched) 
                            probe.setProbeDirection('F');
                        else
                            probe.setProbeDirection('U');
                    }
                    else
                        probe.setProbeDirection('U');  
                }
                previousClosestLink = closestLink;
                previousdistMapMatched = distanceMapMatched;
                previousProbeID = probe.getSampleID();
                probe.setDistFromLink(minDistProbe);
                probe.setDistFromRef(distanceMapMatched);
                probe.setLinkPVID(closestLink);
                System.out.println("============================================");
                System.out.println("Probe no: " + probecount + "   ProbeSampleID: " + probe.getSampleID() + "   Nearest Link: " + probe.getLinkPVID() + "   Direction of Travel: " + probe.getProbeDirection() + "   Distance from Reference: " + probe.getDistFromRef() + "   Distance from Link: " + probe.getDistFromLink());
                System.out.println("============================================");
            } 
            else
                continue;			
        }
        SlopeCalculate.slopeCalculation(linkDataArrayList, probePointArrayList);
    }

    /***
     * 
     *
     * @param link
     * @param matchPoint
     * @param num        is the index
     * @return the distance to map matched
     */
    public static double distanceTomap_Matched(Link link, Double[] matchPoint, int num) 
    {
            double dist = 0.0;
            for (int i = 0; i < num - 1; i++) 
                dist += haversineFormula(link.getShapeInfo().get(i)[0], link.getShapeInfo().get(i)[1],link.getShapeInfo().get(i + 1)[0], link.getShapeInfo().get(i + 1)[1]);
            dist += haversineFormula(link.getShapeInfo().get(num)[0], link.getShapeInfo().get(num)[1], matchPoint[0],matchPoint[1]);
            return dist * 1000;
    }


    public static double haversineFormula(double firstLat, double firstLong, double secondLat, double secondLong) 
    {
            final double R = 6372.8;
            double directionLat = Math.toRadians(secondLat - firstLat);
            double directionLong = Math.toRadians(secondLong - firstLong);
            secondLat = Math.toRadians(secondLat);
            firstLat = Math.toRadians(firstLat);

            double a = Math.pow(Math.sin(directionLat / 2), 2) + Math.pow(Math.sin(directionLong / 2), 2) * Math.cos(firstLat) * Math.cos(secondLat);
            double c = 2 * Math.asin(Math.sqrt(a));
            return R * c;
    }
}