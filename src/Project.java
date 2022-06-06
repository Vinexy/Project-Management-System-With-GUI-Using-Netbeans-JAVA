
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project extends AbsProject implements IProjectApp{
    private int projectNum=0;
    
    private int projectId;
    private int projectManagerId;
    private String projectName;
    private String startDate;
    private String dueDate;
    private int membernum;
    private float budget;
    private float expectedprofit;
    private int progressstatus;
    
    ArrayList<Project> projectList = new ArrayList<Project>();

    public Project() {
    }
    
    public Project(int projectId, int projectManagerId, String projectName, String startDate, String dueDate, int membernum ,float budget, float expectedprofit, int progressstatus) {
        this.projectId = projectId;
        this.projectManagerId = projectManagerId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.membernum = membernum;
        this.budget = budget;
        this.expectedprofit = expectedprofit;
        this.progressstatus = progressstatus;
    }

    public int getProjectNum() {
        return projectNum;
    }
    
    public String SearchProject(String searchid) throws IOException{
        
        try {
            return GetFromFile("FProject.txt", searchid);
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Project> getProjectList() {
        return projectList;
    }

    @Override
    public String toString() {
        return "Project:" + "projectId=" + Integer.toString(projectId) + "\nprojectManagerId=" + Integer.toString(projectManagerId) + 
                "\nprojectName=" + projectName + "\nstartDate=" + startDate + "\ndueDate=" + dueDate + "\nmembernum=" + Integer.toString(membernum)
                + "\nbudget=" + Float.toString(budget) + "\nexpectedprofit=" + Float.toString(expectedprofit) + "\nprogressstatus=" + Integer.toString(progressstatus)+ "\n\n\n";
    
    }
    
    public static String DeleteFromFile(String fileName, String searchid) throws FileNotFoundException, IOException, ParseException{
        //Varolan bir projeyi silmek için kullanılır
        int i=0;
        String[] infos;
        String str="";
        BufferedReader bReader = new BufferedReader(new FileReader(fileName));
        String line;
        
        //silinmek istenilen projenin sistemde olup olmadığını kontrol eder
        while((line = bReader.readLine()) != null){
            
            infos=line.split("\t");
            
            if(!infos[0].equalsIgnoreCase(searchid) ){
                str+=line+"\n";
            }else{
                i=1;
            }
        }
        bReader.close();
        
        //sistemde varsa projenin çalışan bilgilerindeki ve kendi dosyasındaki bilgileri silinir
        if(i==1){
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileName));
            bWriter.write(str);
            bWriter.close();
            
            if(fileName.equalsIgnoreCase("FProject.txt")){
                
                BufferedReader bReader2 = new BufferedReader(new FileReader(new File("FEmployee.txt")));
                String line2,str2="";
                String[] infos2;
                
                //o projede çalışan çalışanları bulur ve bilgilerinden belirtilen id yi siler
                while((line2 = bReader2.readLine()) != null){

                    infos2=line2.split("\t");

                    if(infos2[6].equalsIgnoreCase(searchid)){
                        infos2[6]="-";
                        line2=infos2[0]+"\t"+infos2[1]+"\t"+infos2[2]+"\t"+infos2[3]+"\t"+infos2[4]+"\t"+infos2[5]+"\t"+infos2[6]+"\t"+infos2[7]+"\n";
                        str2+=line2;
                    }else{
                        str2+=line2+"\n";
                    }
                }
                bReader2.close();
                BufferedWriter bWriter2 = new BufferedWriter(new FileWriter(new File("FEmployee.txt")));
                bWriter2.write(str2);
                bWriter2.close();
            }
            return "Project deleted successfully!!!";
        }else{
            return "This id is not exist in system!!!";
        }
    }
    
    public String AddProject(String searchid, String Info){
        try {
            projectNum++;
            return AddFile("FProject.txt", searchid, Info);
        } catch (IOException ex) {
            Logger.getLogger(ProjectMember.class.getName()).log(Level.SEVERE, null, ex);
            return "Project could not be deleted!!!";
        }
    }

    @Override
    public void ReadFile() throws FileNotFoundException, IOException{
        
        /*projelerin bulunduğu dosyayı okuyarak bir arrayListe atar eğer
        projenin son tarihi geçmiş ise o projeyi sistemden tamamen çıkarır siler*/
        projectList.clear();
        
        Date objDate = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date12 = null; 
        try {
            date12 = new SimpleDateFormat("dd/MM/yyyy").parse(df.format(objDate));
        } catch (ParseException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date date1 = null;

        String[] infos ;
        int i=0;
        
        BufferedReader bReader = new BufferedReader(new FileReader(new File("FProject.txt")));
        String line;
        
        //Tüm dosya taranır
        while((line = bReader.readLine()) != null){
            
            infos=line.split("\t");
            //projelerin son tarihi tutulur
            try {
                date1 =new SimpleDateFormat("dd/MM/yyyy").parse(infos[4]);
            } catch (ParseException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //son tarihi geçmiş ise sistemden silinir ve arrayListe alınmaz
            if(!date12.before(date1) ){
                
                try {
                    DeleteFromFile("FProject.txt",infos[0]);
                } catch (ParseException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                //geri kalan projeler arrayListlere eklenir
                Project objProject = new Project(Integer.valueOf(infos[0]), Integer.valueOf(infos[1]), infos[2], infos[3], infos[4], 
                    Integer.valueOf(infos[5]), Float.valueOf(infos[6]), Float.valueOf(infos[7]), Integer.valueOf(infos[8]));
                projectList.add(objProject);
                i++;
            }
        }
        projectNum=i;
        bReader.close();
        
        
    }

    
}
