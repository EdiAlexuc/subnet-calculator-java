import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SubnetCalculator {
    public static void main(String[] args) throws UnknownHostException {

        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the IP address and subnet mask
        System.out.print("Enter the IP address: ");
        String ipAdress = scanner.nextLine();
        System.out.print("Enter the subnet mask: ");
        String subnetMask = scanner.nextLine();

        // Convert the IP address and subnet mask to binary form
        InetAddress ip = InetAddress.getByName(ipAdress);
        InetAddress mask = InetAddress.getByName(subnetMask);
        byte[] ipBytes = ip.getAddress();
        byte[] maskBytes = mask.getAddress();

        // Perform a bitwise AND operation to determine the network address
        byte[] networkAddress = new byte[4];
        for(int i = 0; i < 4; i++){
            networkAddress[i] = (byte)(ipBytes[i] & maskBytes[i]);
        }

        // Determine the range of IP addresses available in the subnet
        byte[] minIP = new byte[4];
        byte[] maxIP = new byte[4];
        for(int i = 0; i < 4; i++){
            minIP[i] = (byte) (networkAddress[i] & maskBytes[i]);
            maxIP[i] = (byte) (minIP[i] | ~maskBytes[i]);
        }

        // Print the results to the user
        System.out.println("Network address: " + InetAddress.getByAddress(networkAddress).getHostAddress());
        System.out.println("Range of IP addresses: " + InetAddress.getByAddress(minIP).getHostAddress() + " - "
                            +InetAddress.getByAddress(maxIP).getHostAddress());

    }
}
