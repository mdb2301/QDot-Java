package QDot.WebServer;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import QDot.URIHandler;

public class ServerRoute {
    String path;
    Pattern pathRegex;
    ArrayList<String> methods = new ArrayList<String>();

    ServerRoute(String path){
        this.path = path;
        this.pathRegex = URIHandler.generateRegex(path);
    }

    void setMethods(ArrayList<String> methods){
        this.methods = methods;
    }

    public Future<Object> call(HttpRequest request,HashMap<String,Object> params){
        return null;
    }
}
