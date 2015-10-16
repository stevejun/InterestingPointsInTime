
public class InterestingPoints {

	/*
	 * Problem Description:
	 * Time is represented as a string of the format
	 * 	"hh:mm:ss" where hours are 0-24, mins are 0-60, and seconds are 0-60.
	 * A point in time is called 'interesting' if the time is written
	 * with up to at most two different digits.
	 * Provide a function to return the number of 'interesting points in time'
	 * from starting time S and terminal time T.
	 */

	public static int solution(String S, String T){
		int numInterestingPoints=0;
		//calculate starting time
		int startingTime=0;
		startingTime+=((Integer.valueOf(S.substring(0,2)))*(60*60));
		startingTime+=((Integer.valueOf(S.substring(3,5)))*(60));
		startingTime+=((Integer.valueOf(S.substring(6,8))));
		
		//calculate terminal time
		int terminalTime=0;
		terminalTime+=((Integer.valueOf(T.substring(0,2)))*(60*60));
		terminalTime+=((Integer.valueOf(T.substring(3,5)))*(60));
		terminalTime+=((Integer.valueOf(T.substring(6,8))));
		
		System.out.println("Starting time: "+startingTime);
		System.out.println("Terminal time: "+terminalTime);
		//calculate time difference between S and T
		//assuming terminalTime is greater than startingTime within a 1-day period
		int timeDifference = terminalTime-startingTime;
		System.out.println("Time difference: "+timeDifference);
		
		//store time into an array
		int[] time = new int[6];
		time[0]=Integer.valueOf(S.substring(0,1));
		time[1]=Integer.valueOf(S.substring(1,2));
		time[2]=Integer.valueOf(S.substring(3,4));
		time[3]=Integer.valueOf(S.substring(4,5));
		time[4]=Integer.valueOf(S.substring(6,7));
		time[5]=Integer.valueOf(S.substring(7,8));
		
		int numOfDiffDigit;
		//for each time from S to T, check if the time is interesting
		while (timeDifference>0){
			
			//System.out.println("\nCurent time: "+time[0]+time[1]+":"+time[2]+time[3]+":"+time[4]+time[5]);
			numOfDiffDigit=0; 
			int digit1 = Integer.valueOf(time[0]);
			int digit2 = Integer.valueOf(time[0]);
			
			//find a second different digit
			int indexForDigit2=0;
			for (int i=1; i<6; i++){
				if (time[i]!=digit1){
					digit2=time[i];
					indexForDigit2=i;
					numOfDiffDigit++;
					break;
				}
			}
			
			if (digit1!=digit2){
				for (int i=indexForDigit2+1; i<6; i++){
					if(time[i]!=digit1&&time[i]!=digit2){
						numOfDiffDigit++;
					}
				}
			}
			//if time is interesting, numInterestingPoints++
			if(numOfDiffDigit<2)
				numInterestingPoints++;
			
			
			//increment the time
			//meaning, increment second
			//if second >=60, set sec to 0 and increment min
			//if min >=60, set min to 0 and increment hr
			int tempSec, tempMin, tempHour;
			tempSec=time[4]*10+time[5];
			tempMin=time[2]*10+time[3];
			tempHour=time[0]*10+time[1];
			
			tempSec++;
			if(tempSec==60){
				tempSec=0;
				tempMin++;
				if(tempMin==60){
					tempMin=0;
					tempHour++;
					if(tempHour==24){
						tempHour=0;
					}
				}
			}
			
			time[0]=tempHour/10;
			time[1]=tempHour%10;
			time[2]=tempMin/10;
			time[3]=tempMin%10;
			time[4]=tempSec/10;
			time[5]=tempSec%10;
			
			//System.out.println("Number of Different Digits: "+numOfDiffDigit);
			//System.out.println("Number of Interesting Points: "+numInterestingPoints+"\n");
			timeDifference--;
		}
		
		return numInterestingPoints;
	}
	
	public static void main(String[] args){
		 
		String timeS1="15:15:05";
		String timeT1="15:15:16";
		System.out.println("\nThe number of interesting points between times "+timeS1+" and "+timeT1+" is "+
				solution(timeS1,timeT1));
		
		String timeS2="22:22:00";
		String timeT2="22:22:30";
		System.out.println("\nThe number of interesting points between times "+timeS2+" and "+timeT2+" is "+
				solution(timeS2,timeT2));
		
	}
	
	
	
}
