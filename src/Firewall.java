/** Firewall program built for Security and Privacy in Computing course.
 * @author Curtis P. Hohl
 * @date 12/4/18
 */

import java.io.*;

public class Firewall {

    private String accessListFile = "AccessList/AccessList.txt";
    private String logFile = "Log/Log.txt";

    /** Run the given URL through the firewall
     *  if the packet is accepted, the URL is returned.
     *  Otherwise, an error is returned.
     * @param packet
     * @return URL or error message
     */
    public String testPacket(Packet packet) {
        String url = packet.getUrl();
        if(urlIsInAccessList(url)) { //check if the url is in the access list
            System.out.println(url);
            return url; //return the valid url
        }
        System.out.println("Logging firewall penetration attempt.");
        writeToLog("Firewall penetration attempt: " + url); //log the penetration attempt
        return "ERROR - Packet Rejected (Not in access list)"; //return error message, the url was not in the access list
    }

    /** Add the given URL to the access list.
     * This method is currently private to prevent security break-in.
     * @param url
     */
    private void addUrlToAccessList(String url) {
        System.out.println("Attempting to add URL: " + url + " to access list");

        try {
            BufferedWriter writeToAccessList = new BufferedWriter(new FileWriter(accessListFile, true));
            writeToAccessList.write(url); //write URL to access list
            writeToAccessList.newLine(); //newline for readability
            System.out.println("Completed successfully.");
            writeToAccessList.close(); //close the connection
        } catch(Exception e) {
            System.out.println("Error writing to file: " + e);
        }
    }

    /** Test if the given URL is in the access list.
     * @param url
     * @return true if the URL is in the access list, false if not.
     */
    private boolean urlIsInAccessList(String url) {
        System.out.println("Attempting to read (" + url + ") from the access list");

        try {
            BufferedReader readFromAccessList = new BufferedReader(new FileReader(accessListFile));
            var accessList = readFromAccessList.lines().toArray(); //read all lines from the access list
            for(int i = 0; i < accessList.length; i++) {
                if (accessList[i].equals(url)) { //if there is an entry in the access list matching the given URL
                    System.out.println("The URL was found in the access list.");
                    return true;
                }
            }
                System.out.println("The URL was not found in the access list.");
                return false;
        } catch (Exception e){
            System.out.println("Error reading from file: " + e);
            return false;
        }
    }

    /** Write specified text to the log file.
     * Used to record penetration attempts.
     * @param textToWrite
     */
    private void writeToLog(String textToWrite) {
        try {
            BufferedWriter writeToLog = new BufferedWriter(new FileWriter(logFile, true));
            writeToLog.write(textToWrite); //write to log file
            writeToLog.newLine(); //newline for readability
            writeToLog.close(); //close the connection
        } catch(Exception e) {
            System.out.println("Error writing to file: " + e);
        }
    }
}
