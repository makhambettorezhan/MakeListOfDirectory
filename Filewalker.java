import java.io.*;
import java.util.*;

public class Filewalker {

    public Filewalker() {}

    private ArrayList<String> list = new ArrayList<>();
    
    int count = 0;
    public void walk( String path, boolean byName ) {

        File root = new File( path );
        File[] files = root.listFiles();
        String name;
        
        if (files == null) return;
        for ( File f : files ) {
            if ( f.getParent().equals(path)) System.out.println(f.getAbsolutePath());//count = 0;
            if ( f.isDirectory() ) {
                count++;
                if (!byName) 
                    list.add("(DIR)------------" + f.getName() + "-------------(DIR)\n");
                
                walk( f.getAbsolutePath(), byName );
            } else {
                name = f.getName();
                name = name.substring(0, name.length() - 4) + "\n";

                for( int i = 0; i < count; i++) name = '\t' + name;
                list.add( name );//name.substring(0, name.length() - 4) + "\n");
            }
        }

        if(byName) Collections.sort(list);
    }

    public ArrayList<String> getList() {
        return list;
    }

    public static void main(String[] args) {
        Filewalker filewalker = new Filewalker();
        filewalker.walk("c://movies", false);


        //for(String s: filewalker.getList()) System.out.println(s);
    }
}