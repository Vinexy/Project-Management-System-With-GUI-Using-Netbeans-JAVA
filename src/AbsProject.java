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
import java.util.Date;

public abstract class AbsProject {
    public static String GetFromFile(String fileName, String searchid) throws FileNotFoundException, IOException{
        // search işlemleri için kullanılır
        int i=-1;
        String[] infos;
        String str="This file is empty!!!";
        BufferedReader bReader = new BufferedReader(new FileReader(new File(fileName)));
        String line;
        int index=0;
        while((line = bReader.readLine()) != null){
            
            i++;
            infos=line.split("\t");
            
            //aranan id ye sahip satırı tutar ve döndürür
            if(infos[0].equalsIgnoreCase(searchid)){
                str = line;
                break;
            }else{
                str="this id is not exist!!!";
            }
        }
        bReader.close();
        
        return str;
            
    }
    
    public static String AddFile(String fileName, String searchid, String Info) throws FileNotFoundException, IOException{
        //proje ve proje çalışanı ekleme işlemleri için kullanılır
        int x=0;
        int x2=0;
        int i=-1;
        String[] infos;
        String str="";
        String message="added successfully !!!";
        BufferedReader bReader = new BufferedReader(new FileReader(new File(fileName)));
        String line;
        
        while((line = bReader.readLine()) != null){
            
            i++;
            infos=line.split("\t");
            str+=line+"\n";
            
            /*
            eklenmesi istenilen id ye sahip satırların zaten var olup olmadığına 
            bakar varsa uyarı mesajını buna göre düzenler ve döngüyü sonlandırır
            x sayacını kullanılmak üzere düzenler*/
            if(infos[0].equalsIgnoreCase(searchid)){
                message="This id is already exist on the system!!!";
                x=1;
                break;
            }
        }
        bReader.close();
        
        // x sayacı 0 ise eklenmek istenilen id eklenebilirdir ve ekleme işlemi yapılır
        if(x==0){
            String[] str3=Info.split("\t");
            str+=Info ;
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(new File(fileName)));
            bWriter.write(str);
            bWriter.close();
            
            //hangi dosyaya ekleme yapılacağını sorgular
            if(fileName.equalsIgnoreCase("FProject.txt")){
                
                BufferedReader bReader2 = new BufferedReader(new FileReader(new File("FEmployee.txt")));
                String line2,str2="";
                String[] infos2;
                
                //proje için istenilen proje müdürünün başka projesi olup olmadığı kontrol edilir
                while((line2 = bReader2.readLine()) != null){
            
                    infos2=line2.split("\t");
                    
                    /*proje müdürünün başka projesi yoksa ekleme işlemi yapılır 
                    ve müdürün proje bilgisini oluşturulan proje id si eklenir*/
                    if(infos2[6].equalsIgnoreCase("-") && infos2[0].equalsIgnoreCase(str3[1])){
                        infos2[6]=searchid;
                        line2=infos2[0]+"\t"+infos2[1]+"\t"+infos2[2]+"\t"+infos2[3]+"\t"+infos2[4]+"\t"+infos2[5]+"\t"+infos2[6]+"\t"+infos2[7]+"\n";
                        str2+=line2;
                        x2=1;
                    }else{
                        str2+=line2+"\n";
                    }
                }
                
                //Proje müdürünün başka projesi varsa mesaj buna göre düzenlenir ve proje eklenmez
                if(x2==0){
                    message="This project Manager already have a project!!!";
                }
                
                bReader2.close();
                BufferedWriter bWriter2 = new BufferedWriter(new FileWriter(new File("FEmployee.txt")));
                bWriter2.write(str2);
                bWriter2.close();
            }
        }
        return message;
    }
    
}
