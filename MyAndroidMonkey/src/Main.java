import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.UUID;

public class Main {

	private static String adbRoot = "";
    private static final String ADB_INPUT = "adb shell input ";
    private static final String TELNET_LOCALHOST = "telnet localhost ";
    private static String telnetToken = "";
    private static String emulatorPort = "";
    private static int numberOfEvents = 0;
    private static String[] distribution;
    private static String[] eventsToExecute;
    private static Random randomizer = new Random(1234);
    private static final String[] SPEEDS = {"gsm", "hscsd", "gprs", "edge", "umts", "hsdpa", "lte", "evdo", "full"};
    private static String apk = "";
    private static String packageApp = "";
             
    // Connects to Telnet
    private static BufferedWriter connectToTelnet() throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process telnet = rt.exec(TELNET_LOCALHOST + emulatorPort);
        System.out.println("Executing: telnet localhost " + emulatorPort);
        return new BufferedWriter(new OutputStreamWriter(telnet.getOutputStream()));
    }

    // Executes a telnet command
    private static void executeTelnetCommand(String telnetCommand) throws IOException {
    	
        BufferedWriter out = connectToTelnet();
        out.write("auth " + telnetToken + "\n");
        System.out.println("Executing: auth " + telnetToken);
        out.write(telnetCommand + "\n");
        System.out.println("Executing telnet command: " + telnetCommand);
        out.write("quit\n");
        out.flush();
    }
    
    // Executes an ADB command
    private static void executeAdbCommand(String adbCommand) throws IOException {
    	adbCommand = adbRoot + adbCommand;
    	System.out.println("Executing ADB command: " + adbCommand);
        Runtime rt = Runtime.getRuntime();
        rt.exec(adbCommand);        
    }
    
    // Sends a rotate 
    private static void rotate() throws IOException, InterruptedException {
    	executeTelnetCommand("rotate");
        Thread.sleep(1000);
    }
    
    // Inputs a text 
    private static void inputText() throws IOException, InterruptedException {
    	String randomText = UUID.randomUUID().toString().replaceAll("-", "");
    	startApp();
    	executeAdbCommand(ADB_INPUT + "text " + randomText);
    }    

    // Sends a key event 
    private static void sendKeyEvent() throws IOException, InterruptedException {
    	int randomKey = randomizer.nextInt(283);
    	startApp();
    	executeAdbCommand(ADB_INPUT + "keyevent " + randomKey);
    }    

    // Sends a tap 
    private static void tap() throws IOException, InterruptedException {
    	int randomX = randomizer.nextInt(1080);
    	int randomY = randomizer.nextInt(1920);
    	startApp();
    	executeAdbCommand(ADB_INPUT + "tap " + randomX + " " + randomY );
    }    
    
    // Sends a swipe 
    private static void swipe() throws IOException, InterruptedException {
    	int randomX = randomizer.nextInt(1080);
    	int randomY = randomizer.nextInt(1920);
    	int randomX1 = randomizer.nextInt(1080);
    	int randomY1 = randomizer.nextInt(1920);
    	startApp();
    	executeAdbCommand(ADB_INPUT + "swipe " + randomX + " " + randomY + " " + randomX1 + " " + randomY1);
    }  
    
    // Set network speed
    private static void setNetworkSpeed() throws IOException, InterruptedException {
    	int randomSpeed = randomizer.nextInt(SPEEDS.length);
    	if(randomSpeed > 0)
    		randomSpeed = randomSpeed - 1;
    	executeTelnetCommand("network speed " + SPEEDS[randomSpeed]);
        Thread.sleep(1000);
    }        
    
    // Set Accelerometer
    private static void setAccelerometer() throws IOException, InterruptedException {
    	int randomX = randomizer.nextInt(10);
    	int randomY = randomizer.nextInt(10);
    	int randomZ = randomizer.nextInt(10);
    	executeTelnetCommand("sensor set acceleration " + randomX + ":" + randomY + ":" + randomZ);
        Thread.sleep(1000);
    }       
    
    // Installs an APK 
    private static void installApk() throws IOException, InterruptedException {
    	executeAdbCommand("adb install -r " + apk);
    	Thread.sleep(1000);
    	System.out.println("APK installed: " + apk);
    }    
    
    // Starts an APP 
    private static void startApp() throws IOException, InterruptedException {
    	executeAdbCommand("adb shell monkey -p " + packageApp + " 1");
    	Thread.sleep(1000);
    	System.out.println("APP started: " + packageApp);
    }    


    // Reads the command line arguments
    private static void readArgs(String[] params) throws IOException {
    	
        for (String param : params) {
        	
            if(param.toLowerCase().startsWith("adb_root")){
            	adbRoot = param.substring(param.indexOf('=') + 1);
            }
            
            if(param.toLowerCase().startsWith("telnet_token")){
            	telnetToken = param.substring(param.indexOf('=') + 1);
            } 
            
			if (param.toLowerCase().startsWith("emulator_port")){
				emulatorPort = param.substring(param.indexOf('=') + 1);
            }                   

			if (param.toLowerCase().startsWith("number_events")){
				numberOfEvents = Integer.parseInt(param.substring(param.indexOf('=') + 1));
            }    

			if (param.toLowerCase().startsWith("events")){
				eventsToExecute = param.substring(param.indexOf('=') + 1).split(",");
            }        			
			
			if (param.toLowerCase().startsWith("distribution")){
				distribution = param.substring(param.indexOf('=') + 1).split(",");
            }
			
			if (param.toLowerCase().startsWith("apk")){
				apk = param.substring(param.indexOf('=') + 1);
            }
			
			if (param.toLowerCase().startsWith("package")){
				packageApp = param.substring(param.indexOf('=') + 1);
            } 	
        }
        
        validateArgs();
 
    }

    // Validate the command line arguments
    private static void validateArgs() throws IOException {
    
	    if(adbRoot == null || adbRoot.isEmpty()){
	    	throw new IOException("You must send the path of ADB command");
	    }
	    else{
	    	System.out.println("adb_root = " + adbRoot);
	    }

	    if(apk == null || apk.isEmpty()){
	    	throw new IOException("You must send the path and name of APK");
	    }
	    else{
	    	System.out.println("APK = " + apk);
	    }
	    
	    if(packageApp == null || packageApp.isEmpty()){
	    	throw new IOException("You must send the package of the APP");
	    }
	    else{
	    	System.out.println("Package APP = " + packageApp);
	    }

	    if(telnetToken == null || telnetToken.isEmpty()){
	    	throw new IOException("You must send the token of telnet");
	    }
	    else{
	    	System.out.println("telnet_token = " + telnetToken);
	    }
	
	    if(emulatorPort == null || emulatorPort.isEmpty()){
	    	throw new IOException("You must send the port of emulator");
	    }
	    else{
	    	System.out.println("emulator_port = " + emulatorPort);
	    }
	
	    if(numberOfEvents == 0){
	    	throw new IOException("You must send the number of events");
	    }
	    else{
	    	System.out.println("number_events = " + numberOfEvents);
	    }
	    
	    if(eventsToExecute == null || eventsToExecute.length == 0){
	    	throw new IOException("You must send at least one event to execute");
	    }
	    else{
	    	for(int i = 0; i < eventsToExecute.length; i++){
	    		String event = eventsToExecute[i].toLowerCase();
	    		if( !( event.equals("text") || event.equals("tap") || event.equals("rotate") || event.equals("swipe") || event.equals("keyevent") || event.equals("networkspeed") || event.equals("accelerometer") ) ){
	    			throw new IOException("You must send valid events to execute");
	     		}        		  
	    		System.out.println("Event: " + event);
	    	}
	    }
	
	    if(distribution == null || distribution.length == 0){
	    	throw new IOException("You must send the distribution of probabilities");
	    }
	    else{
	    	
	        if(eventsToExecute.length != distribution.length ){
	        	throw new IOException("each event must have a corresponding probability");
	        }
	    	
	    	double sumOfProbabilities = 0;
	    	for(int i = 0; i < distribution.length; i++){
	    		double probability = Double.parseDouble(distribution[i]);
	    		sumOfProbabilities += probability; 
	    		System.out.println("Probability: " + probability);
	    	}
	    	
	    	if((int)sumOfProbabilities != 1){
	    		throw new IOException("The sum of probabilities must equal to 1 -- " + sumOfProbabilities);
	    	}
	    }  
    }
    
    public static void main(String[] args) { 
    	
        try {

        	// Reads an validate arguments
        	readArgs(args);
        	
        	// Reinstalls APK
        	installApk();
        	
        	// Starts APP
        	startApp();
        	
        	// Sets a distribution of probabilities
            DistributedRandomNumberGenerator distributedRandom = new DistributedRandomNumberGenerator();
            for (int i = 0; i < distribution.length; i++) {
            	distributedRandom.addNumber(i, Double.parseDouble(distribution[i]));
            }
            
            // Executes the events
            int counter = 0;
            while(counter < numberOfEvents) {
            	int randomNumber = distributedRandom.getDistributedRandomNumber();
            	String luckyEvent = eventsToExecute[randomNumber];
            	
            	switch (luckyEvent){
            	case "text":
            		inputText();
            		break;
            	case "rotate":
            		rotate();
            		break;
            	case "keyevent":
            		sendKeyEvent();
            		break;    
               	case "tap":
            		tap();
            		break;   
               	case "swipe":
            		swipe();
            		break;    
               	case "networkspeed":
               		setNetworkSpeed();
            		break;  
              	case "accelerometer":
               		setAccelerometer();
            		break;                            		
            	}            	
                counter++;
                Thread.sleep(1000);
            }    
            System.out.println("Process finished");
        	
        } catch (Exception e) {
            e.printStackTrace();
        }   
        
    }
}
