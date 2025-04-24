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

public class Project extends AbsProject implements IProjectApp {
    private int projectNum = 0;
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

    public Project(int projectId, int projectManagerId, String projectName, String startDate, String dueDate, int membernum, float budget, float expectedprofit, int progressstatus) {
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

    public String SearchProject(String searchid) throws IOException {
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
                + "\nbudget=" + Float.toString(budget) + "\nexpectedprofit=" + Float.toString(expectedprofit) + "\nprogressstatus=" + Integer.toString(progressstatus) + "\n\n\n";
    }
public static String DeleteFromFile(String fileName, String searchid) throws IOException, ParseException {
    boolean found = false;
    StringBuilder updatedContent = new StringBuilder();

    // Read and filter the main file
    try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = bReader.readLine()) != null) {
            String[] infos = line.split("\t");

            // Defensive check
            if (infos.length > 0 && !infos[0].equalsIgnoreCase(searchid)) {
                updatedContent.append(line).append(System.lineSeparator());
            } else if (infos.length > 0 && infos[0].equalsIgnoreCase(searchid)) {
                found = true;
            }
        }
    }

    if (found) {
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileName))) {
            bWriter.write(updatedContent.toString().trim()); // remove trailing newlines
        }

        // Update employee references if fileName is FProject.txt
        if (fileName.equalsIgnoreCase("FProject.txt")) {
            StringBuilder updatedEmployees = new StringBuilder();

            try (BufferedReader bReader2 = new BufferedReader(new FileReader("FEmployee.txt"))) {
                String line2;
                while ((line2 = bReader2.readLine()) != null) {
                    String[] infos2 = line2.split("\t");

                    // Defensive check for index 6 (project ID)
                    if (infos2.length > 6 && infos2[6].equalsIgnoreCase(searchid)) {
                        infos2[6] = "-"; // Remove project reference
                    }

                    // Reconstruct the line
                    for (int i = 0; i < infos2.length; i++) {
                        updatedEmployees.append(infos2[i]);
                        if (i < infos2.length - 1) {
                            updatedEmployees.append("\t");
                        }
                    }
                    updatedEmployees.append(System.lineSeparator());
                }
            }

            // Write updated employee content back
            try (BufferedWriter bWriter2 = new BufferedWriter(new FileWriter("FEmployee.txt"))) {
                bWriter2.write(updatedEmployees.toString().trim());
            }
        }

        return "Project deleted successfully!!!";
    } else {
        return "This ID does not exist in the system!!!";
    }
}

    public String AddProject(String searchid, String Info) {
        try {
            projectNum++;
            return AddFile("FProject.txt", searchid, Info);
        } catch (IOException ex) {
            Logger.getLogger(ProjectMember.class.getName()).log(Level.SEVERE, null, ex);
            return "Project could not be deleted!!!";
        }
    }

    @Override
    public void ReadFile() throws FileNotFoundException, IOException {
        // Read the projects from the file and handle expired projects
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

        String[] infos;
        int i = 0;

        BufferedReader bReader = new BufferedReader(new FileReader(new File("FProject.txt")));
        String line;

        // Iterate through the entire file
        while ((line = bReader.readLine()) != null) {
            infos = line.split("\t");

            // Parse the project's due date
            try {
                date1 = new SimpleDateFormat("dd/MM/yyyy").parse(infos[4]);
            } catch (ParseException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }

            // If the project is expired, delete it
            if (!date12.before(date1)) {
                try {
                    DeleteFromFile("FProject.txt", infos[0]);
                } catch (ParseException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                // Otherwise, add the project to the list
                Project objProject = new Project(Integer.valueOf(infos[0]), Integer.valueOf(infos[1]), infos[2], infos[3], infos[4],
                        Integer.valueOf(infos[5]), Float.valueOf(infos[6]), Float.valueOf(infos[7]), Integer.valueOf(infos[8]));
                projectList.add(objProject);
                i++;
            }
        }
        projectNum = i;
        bReader.close();
    }
}