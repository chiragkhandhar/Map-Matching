package mapmatching;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperation 
{
    BufferedReader bufferReader = null;
    static FileWriter fileWriter = null;

    public ArrayList<Probe> readProbePointCSV(String filePath) throws IOException 
    {
        String line = "";
        ArrayList<Probe> listProbePoints = new ArrayList<Probe>();
        try 
        {
            bufferReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferReader.readLine()) != null) 
            {
                String[] probeString = line.split(",");
                Probe probepoints = new Probe();
                probepoints.setSampleID(Integer.parseInt(probeString[0]));
                probepoints.setDateTime(probeString[1]);
                probepoints.setSourceCode(Integer.parseInt(probeString[2]));
                probepoints.setLatitude(Double.parseDouble(probeString[3]));
                probepoints.setLongitude(Double.parseDouble(probeString[4]));
                probepoints.setAltitude(Double.parseDouble(probeString[5]));
                probepoints.setSpeed(Double.parseDouble(probeString[6]));
                probepoints.setHeading(Double.parseDouble(probeString[7]));
                listProbePoints.add(probepoints);
            }
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } finally 
        {
            if (bufferReader != null) 
                bufferReader.close();
        }
        return listProbePoints;
    }

    /***
     * 
     * @param filePath
     * @return reads the data on the file imported to the file path
     * @throws NumberFormatException
     * @throws IOException
     */
    public ArrayList<Link> readLinkDataCSV(String filePath) throws NumberFormatException, IOException 
    {
        ArrayList<Link> listLinkData = new ArrayList<Link>();
        String line = "";
        try 
        {
            bufferReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferReader.readLine()) != null) 
            {
                String[] stringDatalink = line.split(",");
                Link listDataLink = new Link();
                int length = stringDatalink.length;

                listDataLink.setLinkPVID(stringDatalink[0]);
                listDataLink.setRef_NodeID(stringDatalink[1]);
                listDataLink.setnRef_NodeID(stringDatalink[2]);
                listDataLink.setLength(Double.parseDouble(stringDatalink[3]));
                listDataLink.setclass_Function(Integer.parseInt(stringDatalink[4]));
                listDataLink.setdirection_travel(stringDatalink[5].charAt(0));
                listDataLink.setcategory_speed(Integer.parseInt(stringDatalink[6]));
                listDataLink.setspeedLimit_Ref(Integer.parseInt(stringDatalink[7]));
                listDataLink.setToRefSpeedLimit(Integer.parseInt(stringDatalink[8]));
                listDataLink.setFromRefNumLanes(Integer.parseInt(stringDatalink[9]));
                listDataLink.setToRefNumLanes(stringDatalink[10].charAt(0));
                listDataLink.setMultiDigitized(stringDatalink[11].charAt(0));
                listDataLink.setUrban(stringDatalink[12].charAt(0));
                listDataLink.setTimeZone(Double.parseDouble(stringDatalink[13]));

                if (length >= 15) 
                {

                    if (!stringDatalink[14].isEmpty()) 
                    {
                        ArrayList<Double[]> shapeListInfo = new ArrayList<>();
                        String[] arrShapeInfoList = stringDatalink[14].split("\\|");
                        for (int i = 0; i < arrShapeInfoList.length; i++) 
                        {
                            String[] shapeListArray = arrShapeInfoList[i].split("/");
                            Double[] value = new Double[shapeListArray.length];
                            for (int j = 0; j < shapeListArray.length; j++) 
                                    value[j] = Double.parseDouble(shapeListArray[j]);
                            shapeListInfo.add(value);
                        }
                        listDataLink.setShapeInfo(shapeListInfo);
                    }
                }

                if (length >= 16) 
                {

                    if (!stringDatalink[15].isEmpty()) 
                    {
                            ArrayList<Double[]> curvatureInfo = new ArrayList<>();
                            String[] curvatureArraylist = stringDatalink[15].split("\\|");
                            for (int i = 0; i < curvatureArraylist.length; i++) 
                            {
                                String[] arrCurvatureInfo = curvatureArraylist[i].split("/");
                                Double[] d = new Double[arrCurvatureInfo.length];
                                for (int j = 0; j < arrCurvatureInfo.length; j++)
                                        d[j] = Double.parseDouble(arrCurvatureInfo[j]);
                                curvatureInfo.add(d);
                            }
                            listDataLink.setCurvatureInfo(curvatureInfo);
                    }
                }

                if (length >= 17) 
                {

                    if (!stringDatalink[16].isEmpty()) 
                    {
                        ArrayList<Double[]> lstSlopeInfo = new ArrayList<>();
                        String[] arrSlopeInfoList = stringDatalink[16].split("\\|");
                        for (int i = 0; i < arrSlopeInfoList.length; i++) 
                        {
                                String[] arrSlopeInfo = arrSlopeInfoList[i].split("/");
                                Double[] d = new Double[arrSlopeInfo.length];
                                for (int j = 0; j < arrSlopeInfo.length; j++)
                                    d[j] = Double.parseDouble(arrSlopeInfo[j]);
                                lstSlopeInfo.add(d);
                        }
                        listDataLink.setSlopeInfo(lstSlopeInfo);
                    }
                }
                listLinkData.add(listDataLink);
            }
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } finally 
        {
            if (bufferReader != null)
                bufferReader.close();
        }
        return listLinkData;
    }

	/***
	 * 
	 * @param probePoints
	 * @param index
	 * @throws IOException
	 */
	public static void write_MatchedMapPoints(Probe probePoints, int index) throws IOException 
        {
            final String NEW_LINE_SEPARATOR = "\n";
            try 
            {
                if (index == 0) 
                {
                    fileWriter = new FileWriter("D:\\\\Sample\\\\Partition6467MatchedPoints.csv", false);
                    String header = "SampleID,DateTime,SourceCode,Latitude,Longitude,Altitude,Speed,Heading,LinkPVID,Direction,DistFromRef,DistFromLink,Slope";
                    String data = String.valueOf(probePoints.getSampleID()) + "," + probePoints.getDateTime() + ","
                                    + probePoints.getSourceCode() + "," + probePoints.getLatitude() + "," + probePoints.getLongitude() + ","
                                    + probePoints.getAltitude() + "," + probePoints.getSpeed() + "," + probePoints.getHeading() + ","
                                    + probePoints.getLinkPVID() + "," + probePoints.getProbeDirection() + "," + probePoints.getDistFromRef() + ","
                                    + probePoints.getDistFromLink() + "," + probePoints.getSlope();

                    fileWriter.append(header);
                    fileWriter.append(NEW_LINE_SEPARATOR);
                    fileWriter.append(data);
                    fileWriter.append(NEW_LINE_SEPARATOR);
                } 
                else 
                {
                    fileWriter = new FileWriter("D:\\Sample\\Partition6467MatchedPoints.csv", true);
                    String data = String.valueOf(probePoints.getSampleID()) + "," + probePoints.getDateTime() + "," + probePoints.getSourceCode() + "," + probePoints.getLatitude() + "," + probePoints.getLongitude() + "," + probePoints.getAltitude() + "," + probePoints.getSpeed() + "," + probePoints.getHeading() + "," + probePoints.getLinkPVID() + "," + probePoints.getProbeDirection() + "," + probePoints.getDistFromRef() + ","  + probePoints.getDistFromLink() + "," + probePoints.getSlope();
                    fileWriter.append(data);
                    fileWriter.append(NEW_LINE_SEPARATOR);
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            } 
            finally 
            {
                fileWriter.flush();
                fileWriter.close();
            }
	}

	/***
	 * 
	 * @param linkDataID
	 * @param mean
	 * @param givenMean
	 * @param index
	 * @throws IOException
	 * 
	 */
	public static void write_SlopeData(String linkDataID, double mean, double givenMean, int index) throws IOException 
        {
            final String LINE_SEPARATOR = "\n";
            try 
            {
                    if (index == 0) 
                    {
                        fileWriter = new FileWriter("D:\\Sample\\SlopeEvaluation.csv", false); // 
                        String header = "LinkPVID,CalculatedMeanSlope,GivenMeanSlope";
                        String data = linkDataID + "," + String.valueOf(mean) + "," + String.valueOf(givenMean);
                        fileWriter.append(header);
                        fileWriter.append(LINE_SEPARATOR);
                        fileWriter.append(data);
                        fileWriter.append(LINE_SEPARATOR);
                    } 
                    else 
                    {
                        fileWriter = new FileWriter("D:\\Sample\\SlopeEvaluation.csv", true);
                        String data = linkDataID + "," + String.valueOf(mean) + "," + String.valueOf(givenMean);
                        fileWriter.append(data);
                        fileWriter.append(LINE_SEPARATOR);
                    }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            } 
            finally 
            {
                fileWriter.flush();
                fileWriter.close();
            }
	}
}
