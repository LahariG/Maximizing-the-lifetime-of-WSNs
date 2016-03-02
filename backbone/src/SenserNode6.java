import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;



public class SenserNode6 extends Frame implements ActionListener {

    // Object fields
   
    private TextField infoText;
    private Button sencedinfoButton;
    private Button  exitButton;
    private Socket requestSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private List sourceNode;
    private Label tl1,tl2,tl3;
    private String sinkserver_ip;

    public void SetStatus(String s)
    {
    	this.setTitle(s);
    }    
 
    class PortListener2 implements Runnable {

    	ServerSocket server;
    	Socket connection;
    	BufferedReader br = null;
    	Boolean flag;
    	public String strLine;
    	int port;


    	public PortListener2(int port) {
    		this.port = port;
    		strLine = "waiting for client";
    		flag = true;//initial sleeping
    	}

    	public void run() {
    		if(port==3112)
    		{
    		try {
    			server = new ServerSocket(3112);

    			while (true) {
    				
    				connection = server.accept();
    				System.out.println("Connection received from " + connection.getInetAddress().getHostName());    				   				
    				ObjectInputStream in = new ObjectInputStream(connection.getInputStream()); 				   				
    				strLine = (String)in.readObject();
    				System.out.println(strLine);
    				in.close();
    				connection.close();
    				
    				tl3.setText("Passing from Node-2 to Sink");
    	    		try {      				
    	    			Thread.sleep(1500);      	    		
        	    	} 	    		
        	    	catch(Exception e) {
        	    			System.out.println("Exception in Server1 Connection: "+e); 
        	    	}
        	    	tl3.setText("                                 ");
       		   		
        	    	try{
    	    			String message;
    	    			requestSocket = new Socket(sinkserver_ip, 1116);
    	    			System.out.println("Connected to localhost in port 1116");
    	    			//2. get Input and Output streams
    	    			out = new ObjectOutputStream(requestSocket.getOutputStream());
    	    			out.flush();
    	    			message =  strLine + "Node-6)";  	    			
    	    			out.writeObject(message);
    	    			out.flush();
    	    			out.close();
    	    			requestSocket.close();
    	     		}
    	    		catch(UnknownHostException unknownHost){
    	    			System.err.println("You are trying to connect to an unknown host!");
    	    		}
    	    		catch(IOException ioException){
    	    			ioException.printStackTrace();
    	    		}
    			}
    		} 
    		
			catch(ClassNotFoundException classnot){
				System.err.println("Data received in unknown format");
			}
    		catch(IOException ioException){
    			ioException.printStackTrace();
    		} finally {
    		}

    		}
    		if(port==3114)
    		{
    		try {
    			server = new ServerSocket(3114);

    			while (true) {
    				
    				connection = server.accept();
    				System.out.println("Connection received from " + connection.getInetAddress().getHostName());    				   				
    				ObjectInputStream in = new ObjectInputStream(connection.getInputStream()); 				   				
    				strLine = (String)in.readObject();
    				System.out.println(strLine);
    				in.close();
    				connection.close();
    				
    				tl3.setText("Passing from Node-4 to Sink");
    	    		try {      				
    	    			Thread.sleep(1500);      	    		
        	    	} 	    		
        	    	catch(Exception e) {
        	    			System.out.println("Exception in Server1 Connection: "+e); 
        	    	}
        	    	tl3.setText("                                ");
       		   		
        	    	try{
    	    			String message;
    	    			requestSocket = new Socket(sinkserver_ip, 1116);
    	    			System.out.println("Connected to localhost in port 1116");
    	    			//2. get Input and Output streams
    	    			out = new ObjectOutputStream(requestSocket.getOutputStream());
    	    			out.flush();
    	    			message =  strLine + "Node-6)";  	    			
    	    			out.writeObject(message);
    	    			out.flush();
    	    			out.close();
    	    			requestSocket.close();
    	     		}
    	    		catch(UnknownHostException unknownHost){
    	    			System.err.println("You are trying to connect to an unknown host!");
    	    		}
    	    		catch(IOException ioException){
    	    			ioException.printStackTrace();
    	    		}
    			}
    		} 
    		
			catch(ClassNotFoundException classnot){
				System.err.println("Data received in unknown format");
			}
    		catch(IOException ioException){
    			ioException.printStackTrace();
    		} finally {
    		}

    		}
    	}
    }

    
    class PortListener implements Runnable {

    	ServerSocket server;
    	Socket connection;
    	BufferedReader br = null;
    	Boolean flag;
    	public String strLine;
    	int port;


    	public PortListener(int port) {
    		this.port = port;
    		strLine = "waiting for client";
    		flag = false;//initaly Active
    	}

    	public void run() {
    		if(port==2116)
    		{
    		try {
    			server = new ServerSocket(2116);

    			while (true) {
    				connection = server.accept();
    				
    				System.out.println("Connection received from " + connection.getInetAddress().getHostName());    				   				
    				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
    				   				
    				strLine = (String)in.readObject();
    				
    				System.out.println(strLine);
//    				infoText.setText(strLine);
    				flag = !flag;
    				
    				if (flag){
    					SetStatus("Node 6 - Active");
    				} else {
       					SetStatus("Node 6 - Sleeping");
    				}
    				
    				sencedinfoButton.setEnabled(flag);
    				in.close();
    				connection.close();   				

    			}
    		} 
    		
			catch(ClassNotFoundException classnot){
				System.err.println("Data received in unknown format");
			}
    		catch(IOException ioException){
    			ioException.printStackTrace();
    		} finally {
    		}

    		}
    	}
    }

    
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        
        if (action.equals("Exit")) {
            dispose();
            System.out.println("Exiting.");
            System.exit(0);
        } else {
            System.out.println(action);
// add to send-info
    		try{
    			//1. creating a socket to connect to the server
    			String message;
    			requestSocket = new Socket(sinkserver_ip, 1116);
    			System.out.println("Connected to localhost in port 1111");
    			//2. get Input and Output streams
    			out = new ObjectOutputStream(requestSocket.getOutputStream());
    			out.flush();
    			message = infoText.getText()+"(Path:Node-6)";
    			
    			out.writeObject(message);
    			out.flush();
     		}
    		catch(UnknownHostException unknownHost){
    			System.err.println("You are trying to connect to an unknown host!");
    		}
    		catch(IOException ioException){
    			ioException.printStackTrace();
    		}
    		finally{
    			//4: Closing connection
    			try{
//    				in.close();
    				out.close();
    				requestSocket.close();
    			}
    			catch(IOException ioException){
    				ioException.printStackTrace();
    			}
    		}
        }

    }
    
    public SenserNode6() {

        super("Senser Node 6");
        setSize(400, 200);

        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

        sinkserver_ip = "localhost";
        // Toolbar Panel
        Panel toolbarPanel = new Panel();
        toolbarPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolbarPanel.setBackground(Color.PINK);
//        toolbarPanel.setLayout(new () );
                
        tl1 = new Label("Information");
        tl2 = new Label("WSN Node Id");
        tl3 = new Label("               ");
        
        infoText = new TextField(30);
        sourceNode = new List();
        

        toolbarPanel.add(tl1);
        toolbarPanel.add(infoText);
   //     toolbarPanel.add(tl2,2);
//        toolbarPanel.add(sourceNode);
        
        sencedinfoButton = new Button("Send Sensed Info To Sink");
        sencedinfoButton.addActionListener(this);
        toolbarPanel.add(sencedinfoButton);

        add(toolbarPanel, BorderLayout.CENTER);

        // Bottom Panel
        Panel bottomPanel = new Panel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        exitButton = new Button("Exit");
        exitButton.addActionListener(this);
        
        bottomPanel.add(exitButton);
        tl3.setForeground(Color.RED);
        
       
        bottomPanel.add(tl3);
        
        bottomPanel.setBackground(Color.BLUE);
        add(bottomPanel, BorderLayout.AFTER_LAST_LINE);
        
		Thread t1 = new Thread (new PortListener(2116));
		t1.setName("Togel node-6");
		t1.start();

		Thread t2 = new Thread (new PortListener2(3112));
		t2.setName("lision from-N2");
		t2.start();

		Thread t3 = new Thread (new PortListener2(3114));
		t3.setName("lision from-N4");
		t3.start();
    }

    /**
     * Sole entry point to the class and application.
     * @param args Array of String arguments.
     */
    public static void main(String[] args) {

    	SenserNode6 mainFrame = new SenserNode6();
        mainFrame.setVisible(true);
    }

}

