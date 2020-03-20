import java.io.*;
import java.util.*;

public class Filewalker {

    public Filewalker() {}

    private ArrayList<String> list = new ArrayList<>();
    
    public void walk( String path, boolean byName ) {

        File root = new File( path );
        File[] files = root.listFiles();
        String name;
        
        if (files == null) return;

        for ( File f : files ) {
            if ( f.isDirectory() ) {
                if (!byName) 
                    list.add("(DIR)------------" + f.getName() + "-------------(DIR)\n");
                
                walk( f.getAbsolutePath(), byName );
            } else {
                name = f.getName();

                list.add( name.substring(0, name.length() - 4) + "\n");
            }
        }

        if(byName) Collections.sort(list);
    }

    public ArrayList<String> getList() {
        return list;
    }
}