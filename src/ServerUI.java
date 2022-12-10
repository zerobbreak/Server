public class ServerUI {

    public static void main(String[] args) {

        ServerManager s = new ServerManager();
        System.out.println(s.allServer());

        System.out.println("Number of servers with a temp fault and Custom role" + s.countServer("Temp", Server.ROLETYPE_CUSTOM));
        System.out.println();

        s.assigendTechnicians();
        System.out.println(s.allServer());

        System.out.println(s.printMap("A11"));
    }
}
