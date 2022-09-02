package QDot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class URIHandler {

    public static Pattern generateRegex(String template){
        if(template==""||template=="/") return Pattern.compile("^/$");
        String[] explode = template.split("/");
        ArrayList<String> finalRegex = new ArrayList<String>();
        for(int i=0;i<explode.length;i++){
            String s = explode[i];
            if(s.startsWith("<")){
                s = s.replaceAll("<","").replaceAll(">","");
                switch(s.split(":")[0]){
                    case "int":
                        finalRegex.add("[0-9]+");
                        break;
                    case "float":
                        finalRegex.add("[0-9]+.*[0-9]*");
                        break;
                    default:
                        finalRegex.add("[A-Za-z0-9]+");
                }
            }else{
                finalRegex.add(s);
            }
        }
        finalRegex.remove(0);
        return Pattern.compile("\\/"+String.join("/",finalRegex));
    }

    public static HashMap<String,Object> parsePath(String template,String path){
        String[] templateParts = template.split("/");
        String[] pathParts = path.split("/");
        HashMap<String,Object> params = new HashMap<String,Object>();
        if(templateParts.length==pathParts.length){
            for(int i=0;i<pathParts.length;i++){
                if(templateParts[i].startsWith("<")){
                    String[] s = templateParts[i].replaceAll("<","").replaceAll(">","").split(":");
                    switch(s[0]){
                        case "int":
                            params.put(s[1],Integer.parseInt(pathParts[i],0));
                            break;
                        case "float":
                            Double val;
                            try{
                                val = Double.parseDouble(pathParts[i]);
                            }catch(Exception e){
                                continue;
                            }
                            params.put(s[1],val);
                            break;
                        default:
                            params.put(s[1],pathParts[i]);
                    }
                }
            }
        }
        return params;
    }
}
