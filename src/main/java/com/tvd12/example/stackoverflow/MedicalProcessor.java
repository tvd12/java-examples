package com.tvd12.example.stackoverflow;

class SettingsForIncubator {
}

public class MedicalProcessor {

	protected volatile boolean active;
	protected volatile boolean medicalDeviceIsGivingData;
	
	public void start(String[] args) throws Exception {
		this.active = true;
		Thread thread = new Thread(() -> loop(args));
		thread.start();
	}
	
	protected void loop(String[] args) {
		System.out.println("start");
		while(active) {
			SettingsForIncubator settings = getSettings(args);
	        int counter=0;

	        while(medicalDeviceIsGivingData && counter < 1000){
	            readData(settings); //using settings

	            //a lot of of other functions that use settings.

	            counter++;
	        }
	        try {
	        	Thread.sleep(100); //against overheating and CPU usage.
	        }
	        catch (Exception e) {
	        	break;
			}
		}
	}
	
	protected byte[] readData(SettingsForIncubator settings) {
		// logic read data
		return null;
	}
	
	protected SettingsForIncubator getSettings(String[] args) {
		// logic to get settings
		return null;
	}
	
	public void stop() {
		this.active = false;
	}
	
	public static void main(String[] args) throws Exception {
		MedicalProcessor processor = new MedicalProcessor();
		processor.start(args);
	}
	
}
