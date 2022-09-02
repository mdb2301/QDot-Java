import QDot.WebServer.WebServer;

class Main{
    public static void main(String[] args) {        
        System.out.println("Main::main()");
        WebServer server = new WebServer();
        server.call();
    }
}