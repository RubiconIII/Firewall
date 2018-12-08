import org.junit.Assert;
import org.junit.Test;

public class TestFirewall {

    @Test
    public void testUrlsInAccessList() throws Exception {
            Firewall firewall = new Firewall();

            Packet test1 = new Packet("128.60.25.3", "This packet's url should be in the Access List");
            Packet test2 = new Packet("127.128.32.5", "This packet's url should be in the Access List");
            Packet test3 = new Packet("61.110.4.11", "This packet's url should be in the Access List");

            Assert.assertEquals("128.60.25.3", firewall.testPacket(test1));
            Assert.assertEquals("127.128.32.5", firewall.testPacket(test2));
            Assert.assertEquals("61.110.4.11", firewall.testPacket(test3));
    }

    @Test
    public void testUrlsNotInAccessList() throws Exception {
        Firewall firewall = new Firewall();

        Packet test3 = new Packet("0.0.0.0", "This packet's url should NOT be in the Access List");
        Packet test4 = new Packet("128.128.128.128", "This packet's url should NOT be in the Access List");

        String errorMessage = "ERROR - Packet Rejected (Not in access list)";

        Assert.assertEquals(errorMessage, firewall.testPacket(test3));
        Assert.assertEquals(errorMessage, firewall.testPacket(test4));
    }

}
