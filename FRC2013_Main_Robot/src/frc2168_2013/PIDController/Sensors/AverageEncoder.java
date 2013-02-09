package frc2168_2013.PIDController.Sensors;

import edu.wpi.first.wpilibj.Encoder;
import frc2168_2013.PIDController.Sensors.SpeedSensorInterface;

/**
 * This class extends the basic WPI encoder class. Its purpose is to provide
 * a smoother rate output by averaging the rate of N samplesIt Implements the SpeedSensorInterface
 * for use with our custom PID controller.
 * Encoder with N point averager Misspelling intentional. 
 *
 * @author Kevin Harrilal, Team 2168 Aluminum Falcons
 *
 */
public class AverageEncoder extends Encoder implements SpeedSensorInterface{
	
	private int averagorSize;
	private double[] averagorArray;
	private int arrayPos = 0;		//Next array position to put values to be averaged
	
	long timeNow;
	long oldTime;
	double countNow;
	double countBefore;
	double rate;
	
	int PPR;

	
	
	/**
	 * Constructor for end point average class
	 * @param n the size of end point average
	 */
	public AverageEncoder(int channelA, int channelB, int PPR, boolean reverseDirection, EncodingType encoderType, int averageN){
		
		super(channelA,channelB,reverseDirection,encoderType);
		
	this.averagorSize = averageN;
		this.averagorArray = new double[averagorSize];
		this.timeNow = 0;
		this.oldTime = 0;
		this.countNow = 0;
		this.countBefore = 0;
		this.rate = 0;
		this.PPR=PPR;
		
	}
	
	
	/**
	 * returns (gets) Average of last n values sent, as name says.
	 * @return the Average
	 */
	private double getAverage(){
		double sum = 0;
		
		for(int i = 0; i<averagorSize; i++)
			sum+=averagorArray[i];
				
		return sum/averagorSize;	
	}
	
	/**
	 * puts data in to array to be averaged, hence the class name and method name. Its like magic but cooler.
	 * @param value the value being inserted into the array to be averaged.
	 */
	
	private void putData(double value){
		
		averagorArray[arrayPos] = value;
		arrayPos++;
		
		if (arrayPos>=averagorSize) 		//Is equal or greater to averagorSize because array is zero indexed. Rolls over index position.
			arrayPos=0;	
	}
	
	//@Override
	public double getRate()
	{
		
		//getRate
		timeNow = System.currentTimeMillis();
		countNow = super.get();
		rate=(countNow-countBefore)/(timeNow-oldTime); //counts per millisecond
		oldTime=timeNow;
		countBefore=countNow;
	
		
		//scale to RPM and add to array
		putData(rate*1000*60/PPR);
	
		
		return getAverage(); //ticks per minute... rpm
	}
	
	
	
	public double getPos()
	{
		;
		//counts divided by PulsesPerRot/360
		return (double)(super.get())/PPR*360;
	}
	
//	public double getPos()
//	{
//		return super.get();
//	}	
}
