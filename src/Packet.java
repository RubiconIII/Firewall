/**Packet class to test with Firewall
 *
 */
public class Packet {
    public String url;
    public String payload;

    public Packet(String url, String payload) throws Exception {
        if (url.length() <= 16 && payload.length() <= 64) { //set limits on the url and payload length
            this.url = url;
            this.payload = payload;
        }
        else throw new Exception("Packet URL or Payload has too many characters.");
    }

    public String getUrl() {
        return url;
    }

    public String getPayload() {
        return payload;
    }

}
