
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Administration extends Employee implements IProjectApp{
    int adminNum=0;
    private String Job;

    ArrayList<Administration> adminList = new ArrayList<Administration>();
    
    public Administration() {
        super();
    }

    public Administration(String Job, int employeeId, String name, String sname, int age, String department, float salary) {
        super(employeeId, name, sname, age, department, salary);
        this.Job = Job;
    }
    
    @Override
    public String toString() {
        return "Administration:" + "Job=" + Job + super.toString() + "\n\n\n";
    }

    public int getAdminNum() {
        return adminNum;
    }

    public ArrayList<Administration> getAdminList() {
        return adminList;
    }
    
    public String SearchAdmin(String searchid){
        
        try {
            return GetFromFile("FAdmin.txt", searchid);
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
   @Override
    public void ReadFile() throws FileNotFoundException, IOException{
        //Admin dosyasını okuyarak bilgileri ArrayListe aktarmak için kullanılır
        adminList.clear();
        String[] infos ;
        int i=0;
        String str="";
        BufferedReader bReader = new BufferedReader(new FileReader(new File("FAdmin.txt")));
        String line;
        
        while((line = bReader.readLine()) != null){
            str+=line;
            infos=line.split("\t");
            
            Administration admin = new Administration(infos[1], Integer.valueOf(infos[0]), infos[2], infos[3], Integer.valueOf(infos[4]), infos[5], Float.valueOf(infos[6]));
            adminList.add(admin);
            
            i++;
              
        }
        adminNum=i;
        bReader.close();
    }
}
