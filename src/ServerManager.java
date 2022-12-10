import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ServerManager {

    private Server[] sArr = new Server [50];
    private int size = 0;

    public ServerManager() {

        try {
            Scanner sc = new Scanner (new File("servers.txt"));
            String sid, location, role, fault;

            while (sc.hasNext()){
                String line = sc.nextLine();
                String tokens[] = line.split("#");
                sid = tokens[0];
                location = tokens[1];
                role = tokens[2];
                fault = tokens[3];

                sArr[size] = new Server(sid, location, role, fault);
                size++;
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String allServer(){
        String temp = "";
        for (int i = 0; i < size; i++){
            temp = temp + sArr[i].toString() + "\n\n";
        }
        return temp;
    }

    public int countServer(String fault, String role){

        int count = 0;
        for (int i = 0; i < size;  i++){
            if (sArr[i].getFault().equalsIgnoreCase(fault) && sArr[i].getRole().equalsIgnoreCase(role)){
                count = count + 1;
            }
        }
        return count;
    }

    public void assigendTechnicians(){

        try {
            Scanner sc = new Scanner (new File ("technicians.txt"));
            while(sc.hasNext()) {
                String line = sc.nextLine();
                String[] tokens = line.split("#");
                String tid = tokens[0];
                String name = tokens[1];
                int exp = Integer.parseInt(tokens[2]);
                String rs = tokens[3];

                Technician t = new Technician(tid, name, exp, rs);

                int limit = 0;
                int k = 0;

                while (k < size & limit < 4) {
                    if (sArr[k].getRole().equalsIgnoreCase(t.getRoleSpeciality()) & sArr[k].getAssignedTech() == null) {
                        sArr[k].setAssignedTech(t);
                        limit++;
                    }
                    k++;
                }
            }
        }catch (Exception e) {
            System.out.println("File Missing " + e.getMessage());
        }
    }

    private boolean findServer(String loc, String tID)
    {
//        loop through servers
        for (int i = 0; i < size; i++)
        {

            if (sArr[i].getAssignedTech() != null &&
                    sArr[i].getAssignedTech().getTechID().equals(tID) &&
                    sArr[i].getLocation().equals(loc))
            {
                return true;
            }
        }
        return false;
    }


    public String printMap(String techID)
    {

//        creating string for the map
        String map = "";
//        create date stamp
//        correct format
        DateTimeFormatter formatDate =
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        add date to map
        map = map + formatDate.format(LocalDateTime.now()) + "\n";
//        create and append column numbers
        for (int i = 1; i <= 15; i++)
        {
            map += "\t" + i;
        }
        map += "\n";
//        create loop for row letters
        for (char row = 'A'; row <= 'J'; row++)
        {
            map += row;
//            create loop for columns
            for (int col = 1; col <= 15; col++)
            {
//                check if a server location is found
                String loc = (row + "" + col);
                if (findServer(loc, techID))
                {
                    map += "\tX";
//                    appending x
                } else
                {

                    map += "\t*";
//                    appending *
                }
            }
            map += "\n";
        }
        try
        {
//            create file to write save map data with techID as file name
            PrintWriter out = new PrintWriter(new FileWriter(techID + ".txt"));
//            write map data to file
            out.println(map);
//            close file
            out.close();
        } catch (Exception e)
        {
            System.out.println("Failed to write to file");
        }
//        return map
        return map;
    }
}
