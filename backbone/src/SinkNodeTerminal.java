
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class SinkNodeTerminal extends Frame implements ActionListener {

    // Object fields   
    private TextField infoText;
    private Button sencedinfoButton;
    private Button  graphButton, reportButton, exitButton;
    private ImageButton b1;
    private ImageButton b2;
    
    private String node_1_IP;
    private String node_2_IP;
    private String node_3_IP;
    private String node_4_IP;
    private String node_5_IP;
    private String node_6_IP;
    private String node_7_IP;
    

    class AutoRun implements Runnable
    {
    	private Boolean flag;
    	public AutoRun() {
    		flag = false;
    	}
    	
    	public void run() {
    		
    	for( int i = 0;i<25 ;i++ ){
    			
    	        if (flag ) {
    	        	b2.setVisible(true);
    	            b1.setVisible(false);
    	        }else
    	        {
    	        	b2.setVisible(false);
    	            b1.setVisible(true);
    	        	
    	        }
    	        flag = !flag;	
    	        String ss = "TestData" + new Date().toString()+"\n";
    	        writetoFile(ss );
    	        try {
	    			
    	    		Thread.sleep(100);
    	    		} 
    	    		catch(Exception e) {
    	    			System.out.println("Exception in Auto Grph: "+e); 
    	    		}
    	    }
    
        List<Integer> scores = new ArrayList<Integer>();
        Random random = new Random();
        int maxDataPoints = 20;
        int maxScore = 20;
        
        for (int i = 0; i < maxDataPoints ; i++) {
           scores.add(i*2/3);
           System.out.println(i*2/3);
        }
        linegraph mainPanel = new linegraph(scores);

        JFrame frame = new JFrame("MaxLifeGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

    	//linegraph lg = new linegraph();
    	}
    }
    
    public class ImageButton extends Canvas {
    	private Image image;
    	private String command;
    	private boolean selected = false;
    	private Dimension size;

    	public ImageButton(Image img, String command) {
    		super();
    		this.image = img;
    		this.command = command;
    		if (image == null) {
    			System.out.println("Dimension(0, 0)");
    			size = new Dimension(0, 0);
    		} else {
    			size = new Dimension(image.getWidth(this), image.getHeight(this));
    			System.out.println("Dimension(3, 3)");
    		}
    	}
    
    	public boolean handleEvent(Event e) {
    		if (e.id == Event.MOUSE_UP) {
    			if (selected) {
    				e.id = Event.ACTION_EVENT;
    				e.arg = command;
    			}
    		}
    		if (e.id == Event.MOUSE_ENTER) {
    			selected = true;
    			repaint();
    			return true;
    		}
    		if (e.id == Event.MOUSE_EXIT) {
    			selected = false;
    			repaint();
    			return true;
    		}
    		return super.handleEvent(e);
    	}
    	
    	public Dimension minimumSize() {
    		return size;
    	}
    	
 //   	public Dimension preferredSize() {
 //   		return minimumSize();
 //   	}

    	public void paint(Graphics g) {
    		g.drawImage(image, 0, 0, this);
    		if (selected) {
    			g.setColor(Color.yellow);
    			g.drawRect(0, 0, image.getWidth(this)-1, image.getHeight(this)-1);
    		}
    	}
    	
    	public void update(Graphics g) {
    		paint(g);
    	}
    }     
  
    class PortListener2 implements Runnable {
    	private Boolean flag;
        private Socket requestSocket;
        private ObjectOutputStream out;

     	public PortListener2(int port) {
    		flag = false;
    	}

    	public void run() {
    		while (true){
    			
    	        if (flag ) {
    	        	b2.setVisible(true);
    	            b1.setVisible(false);
    	        }else
    	        {
    	        	b2.setVisible(false);
    	            b1.setVisible(true);
    	        	
    	        }
    	        flag = !flag;
    	        
    	    		try{
    	    			//1. creating a socket to connect to the server
    	    			String message;
    	    			requestSocket = new Socket(node_1_IP, 2111);
    	    			System.out.println("Connected to localhost in port 2111");
    	    			//2. get Input and Output streams
    	    			out = new ObjectOutputStream(requestSocket.getOutputStream());
    	    			out.flush();
    	    			message = "Sink message";	
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
    	    				out.close();
    	    				requestSocket.close();
    	    			}
    	    			catch(IOException ioException){
    	    				ioException.printStackTrace();
    	    			}
    	    		}
    				
   	    		try{
    	    			//1. creating a socket to connect to the server
    	    			String message;
    	    			requestSocket = new Socket(node_3_IP, 2113);
    	    			System.out.println("Connected to localhost in port 2113");
    	    			//2. get Input and Output streams
    	    			out = new ObjectOutputStream(requestSocket.getOutputStream());
    	    			out.flush();
    	    			message = "Sink message";	
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
    	    				out.close();
    	    				requestSocket.close();
    	    			}
    	    			catch(IOException ioException){
    	    				ioException.printStackTrace();
    	    			}
    	    		}
    	    		try{
    	    			//1. creating a socket to connect to the server
    	    			String message;
    	    			requestSocket = new Socket(node_5_IP, 2115);
    	    			System.out.println("Connected to localhost in port 2115");
    	    			//2. get Input and Output streams
    	    			out = new ObjectOutputStream(requestSocket.getOutputStream());
    	    			out.flush();
    	    			message = "Sink message";	
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
    	    				out.close();
    	    				requestSocket.close();
    	    			}
    	    			catch(IOException ioException){
    	    				ioException.printStackTrace();
    	    			}
    	    		}
    	    		
    	    		
    	    		try{
    	    			//1. creating a socket to connect to the server
    	    			String message;
    	    			requestSocket = new Socket(node_7_IP, 2117);
    	    			System.out.println("Connected to localhost in port 2117");
    	    			//2. get Input and Output streams
    	    			out = new ObjectOutputStream(requestSocket.getOutputStream());
    	    			out.flush();
    	    			message = "Sink message";	
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
    	    				out.close();
    	    				requestSocket.close();
    	    			}
    	    			catch(IOException ioException){
    	    				ioException.printStackTrace();
    	    			}
    	    		}

    	    		try{
    	    			//1. creating a socket to connect to the server
    	    			String message;
    	    			requestSocket = new Socket(node_2_IP, 2112);
    	    			System.out.println("Connected to localhost in port 2112");
    	    			//2. get Input and Output streams
    	    			out = new ObjectOutputStream(requestSocket.getOutputStream());
    	    			out.flush();
    	    			message = "Sink message";	
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
    	    				out.close();
    	    				requestSocket.close();
    	    			}
    	    			catch(IOException ioException){
    	    				ioException.printStackTrace();
    	    			}
    	    		}

    	    		try{
    	    			//1. creating a socket to connect to the server
    	    			String message;
    	    			requestSocket = new Socket(node_4_IP, 2114);
    	    			System.out.println("Connected to localhost in port 2114");
    	    			//2. get Input and Output streams
    	    			out = new ObjectOutputStream(requestSocket.getOutputStream());
    	    			out.flush();
    	    			message = "Sink message";	
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
    	    				out.close();
    	    				requestSocket.close();
    	    			}
    	    			catch(IOException ioException){
    	    				ioException.printStackTrace();
    	    			}
    	    		}

    	    		try{
    	    			//1. creating a socket to connect to the server
    	    			String message;
    	    			requestSocket = new Socket(node_6_IP, 2116);
    	    			System.out.println("Connected to localhost in port 2116");
    	    			//2. get Input and Output streams
    	    			out = new ObjectOutputStream(requestSocket.getOutputStream());
    	    			out.flush();
    	    			message = "Sink message";	
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
    	    				out.close();
    	    				requestSocket.close();
    	    			}
    	    			catch(IOException ioException){
    	    				ioException.printStackTrace();
    	    			}
    	    		}
    	    		try {
    	    			
    	    		Thread.sleep(5500);
    	    		} 
    	    		catch(Exception e) {
    	    			System.out.println("Exception in Server1 Connection: "+e); 
    	    		}
    		}
    	}
    }
    
    class PortListener implements Runnable {

    	ServerSocket server;
    	Socket connection;
    	BufferedReader br = null;
    	
    	public String strLine;
    	int port;


    	public PortListener(int port) {
    		this.port = port;
    		strLine = "waiting for client";
    	}

    	public void run() {
    		if(port==1111)
    		{
    		try {
    			server = new ServerSocket(1111);

    			while (true) {
    				connection = server.accept();
    				
    				System.out.println("Connection received from " + connection.getInetAddress().getHostName());    				   				
    				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
    				   				
    				strLine = (String)in.readObject();
    				
    				System.out.println(strLine);
    				infoText.setText(strLine);
    				writetoFile(strLine);
    				//add current time and write to file
    				
    				
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
    	else if(port==1112)
    		{
    		try {
    			server = new ServerSocket(1112);

    			while (true) {
    				connection = server.accept();
    				
    				System.out.println("Connection received from " + connection.getInetAddress().getHostName());    				   				
    				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
    				   				
    				strLine = (String)in.readObject();
    				
    				System.out.println(strLine);
    				infoText.setText(strLine);
    				writetoFile(strLine);
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
    	else if(port==1113)
		{
		try {
			server = new ServerSocket(1113);

			while (true) {
				connection = server.accept();
				
				System.out.println("Connection received from " + connection.getInetAddress().getHostName());    				   				
				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
				   				
				strLine = (String)in.readObject();
				
				System.out.println(strLine);
				infoText.setText(strLine);
				writetoFile(strLine);
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
    	else if(port==1114)
		{
		try {
			server = new ServerSocket(1114);

			while (true) {
				connection = server.accept();
				
				System.out.println("Connection received from " + connection.getInetAddress().getHostName());    				   				
				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
				   				
				strLine = (String)in.readObject();
				
				System.out.println(strLine);
				infoText.setText(strLine);

				in.close();
				connection.close();   				

				writetoFile(strLine);
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
    	else if(port==1115)
		{
		try {
			server = new ServerSocket(1115);

			while (true) {
				connection = server.accept();
				
				System.out.println("Connection received from " + connection.getInetAddress().getHostName());    				   				
				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
				   				
				strLine = (String)in.readObject();
				
				System.out.println(strLine);
				infoText.setText(strLine);

				in.close();
				connection.close();   				
				writetoFile(strLine);

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
    	else if(port==1116)
		{
		try {
			server = new ServerSocket(1116);

			while (true) {
				connection = server.accept();
				
				System.out.println("Connection received from " + connection.getInetAddress().getHostName());    				   				
				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
				   				
				strLine = (String)in.readObject();
				
				System.out.println(strLine);
				infoText.setText(strLine);

				in.close();
				connection.close();   				
				writetoFile(strLine);
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
    	else if(port==1117)
		{
		try {
			server = new ServerSocket(1117);

			while (true) {
				connection = server.accept();
				
				System.out.println("Connection received from " + connection.getInetAddress().getHostName());    				   				
				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
				   				
				strLine = (String)in.readObject();
				
				System.out.println(strLine);
				infoText.setText(strLine);

				in.close();
				connection.close();   				

				writetoFile(strLine);
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
        
        } else if (action.equals("InfoReport")){
        	new view();
            System.out.println("InfoReport.");        	
        }
    }
    
    public void writetoFile(String s)
    {
		try
		{  
			//code needs to be modified to append string s to existing file
			String ss = s + new Date().toString()+"\n";
			String fname = "F:\\TARF.txt";
			FileWriter fw = new FileWriter(fname,true);
			fw.write(ss);
			fw.close();
			
		} catch(Exception e){
					System.out.println("File Not opened");
		}

    }

    void GenarateTestData()
    {
		Thread t1 = new Thread (new AutoRun());
		t1.setName("Auto Togel nodes");
		t1.start();  	
    }
    
    public SinkNodeTerminal() {

       super("Sink Node Terminal");
       setSize(650, 400);

       addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

       node_1_IP = "localhost";
       node_2_IP = "localhost";
       node_3_IP = "localhost";
       node_4_IP = "localhost";
       node_5_IP = "localhost";
       node_6_IP = "localhost";
       node_7_IP = "localhost";
       
        Panel toolbarPanel = new Panel();
        toolbarPanel.setBackground(Color.PINK);
     
        toolbarPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoText = new TextField(30);
        toolbarPanel.add(infoText);

        sencedinfoButton = new Button("Sensed Info");
        sencedinfoButton.addActionListener(this);
        toolbarPanel.add(sencedinfoButton);

        add(toolbarPanel, BorderLayout.NORTH);
        
        Panel centerPanel = new Panel();
        centerPanel.setBackground(Color.LIGHT_GRAY);

       
  	  	Image img = Toolkit.getDefaultToolkit().getImage("vbs1.jpg");
  	  	
  	  	b1 = new ImageButton(img, "SRSIT");
  	  	b1.setBackground(Color.WHITE);
        b1.setSize(650, 400);

        Image img2 = Toolkit.getDefaultToolkit().getImage("vbs2.jpg");

  	  	b2 = new ImageButton(img2, "SRSIT1");
  	  	b2.setBackground(Color.WHITE);
        b2.setSize(650, 400);
        b2.setVisible(true);
        b1.setVisible(false);
        centerPanel.add(b1);
        centerPanel.add(b2);
        add(centerPanel, BorderLayout.CENTER);
        

        // Bottom Panel
        Panel bottomPanel = new Panel();

        graphButton = new Button("");
        reportButton = new Button("InfoReport");       
        exitButton = new Button("Exit");

        reportButton.addActionListener(this);
        graphButton.addActionListener(this);
        exitButton.addActionListener(this);

        bottomPanel.add(graphButton);
        bottomPanel.add(reportButton);
        bottomPanel.add(exitButton);
        bottomPanel.setBackground(Color.BLUE);
        add(bottomPanel, BorderLayout.SOUTH);
        

    	int[] ports = new int[] { 1111,1112,1113,1114,1115,1116,1117};
		for (int i = 0; i < 7; i++) {
			Thread t = new Thread(new PortListener(ports[i]));
			t.setName("Listener-" + ports[i]);
			t.start();
		}
		
		Thread t1 = new Thread (new PortListener2(1111));
		t1.setName("Togel nodes");
		t1.start();
    }

    public static void main(String[] args) {

    	SinkNodeTerminal mainFrame = new SinkNodeTerminal();
        mainFrame.setVisible(true);
    }

}
