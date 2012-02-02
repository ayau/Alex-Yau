package edu.upenn.cis350.gpx;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class GPXcalculatorTest {


	// If the GPXtrk object is null, the method should return -1
	@Test
	public void nullinput(){
		assertEquals(-1,GPXcalculator.calculateDistanceTraveled(null),0);
	}
	//If the GPXtrk contains no GPXtrkseg objects, the method should return -1.
	@Test
	public void noGPXtrksegobjects(){
		ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
		GPXtrk track = new GPXtrk("track",seglist);
		assertEquals(-1,GPXcalculator.calculateDistanceTraveled(track),0);
	}
	// If any GPXtrkseg in the GPXtrk is null, the distance traveled for that GPXtrkseg should be
	//considered 0
	@Test
	public void onlynullGPXtrkseg(){
		ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
		seglist.add(null);
		GPXtrk track = new GPXtrk("track",seglist);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
	}
	//One working trkseg, one null. Should return 0.
	@Test
	public void onenullGPXtrkseg(){							
		ArrayList<GPXtrkseg> seglist= new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
		Date date = new Date();
		GPXtrkpt trackpt = new GPXtrkpt(40,190,date);
		ptlist.add(trackpt);
		Date date2 = new Date();
		GPXtrkpt trackpt2 = new GPXtrkpt(40,30,date2);
		ptlist.add(trackpt2);
		GPXtrkseg trackseg = new GPXtrkseg(ptlist);
		seglist.add(trackseg);
		seglist.add(null);
		GPXtrk track = new GPXtrk("track",seglist);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
	}
	//If a GPXtrkseg contains no GPXtrkpt objects, the distance traveled for that GPXtrkseg should
	//be considered 0.
	@Test
	public void noGPXtrkptobjects(){
		ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
		GPXtrkseg trackseg = new GPXtrkseg(ptlist);
		seglist.add(trackseg);
		GPXtrk track = new GPXtrk("track",seglist);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
	}
	// If a GPXtrkseg contains only one GPXtrkpt, the distance traveled for that GPXtrkseg should be
	//considered 0 
	@Test
	public void onlyoneGPXtrkpt(){
		ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
		Date date = new Date();
		GPXtrkpt trackpt = new GPXtrkpt(30,30,date);
		ptlist.add(trackpt);
		GPXtrkseg trackseg = new GPXtrkseg(ptlist);
		seglist.add(trackseg);
		GPXtrk track = new GPXtrk("track",seglist);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
	}
	//If any GPXtrkpt in a GPXtrkseg is null, the distance traveled for that GPXtrkseg should be
	//considered 0.
	//Only one null GPXtrkpt.
	@Test
	public void onlyonenullGPXtrkpt(){
		ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
		ptlist.add(null);
		GPXtrkseg trackseg = new GPXtrkseg(ptlist);
		seglist.add(trackseg);
		GPXtrk track = new GPXtrk("track",seglist);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
	}
	//two valid GPXtrkpt, one null. Should return 0.
	@Test
	public void onenullGPXtrkpt(){
		ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
		Date date = new Date();
		GPXtrkpt trackpt = new GPXtrkpt(30,30,date);
		ptlist.add(trackpt);
		Date date2 = new Date();
		GPXtrkpt trackpt2 = new GPXtrkpt(40,30,date2);
		ptlist.add(trackpt2);
		ptlist.add(null);
		GPXtrkseg trackseg = new GPXtrkseg(ptlist);
		seglist.add(trackseg);
		GPXtrk track = new GPXtrk("track",seglist);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
	}
	//If any GPXtrkpt in a GPXtrkseg has a latitude that is greater than 90 or less than -90, the
	//distance traveled for that GPXtrkseg should be considered 0.
	//One point on 90. Should return 90-40 = 50
	@Test
	public void lat90(){
		ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
		Date date = new Date();
		GPXtrkpt trackpt = new GPXtrkpt(90,30,date);
		ptlist.add(trackpt);
		Date date2 = new Date();
		GPXtrkpt trackpt2 = new GPXtrkpt(40,30,date2);
		ptlist.add(trackpt2);
		GPXtrkseg trackseg = new GPXtrkseg(ptlist);
		seglist.add(trackseg);
		GPXtrk track = new GPXtrk("track",seglist);
		assertEquals(50,GPXcalculator.calculateDistanceTraveled(track),0);
	}
	//One point on 90. Should return 40--90 = 130
	@Test
	public void latminus90(){
		ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
		Date date = new Date();
		GPXtrkpt trackpt = new GPXtrkpt(-90,30,date);
		ptlist.add(trackpt);
		Date date2 = new Date();
		GPXtrkpt trackpt2 = new GPXtrkpt(40,30,date2);
		ptlist.add(trackpt2);
		GPXtrkseg trackseg = new GPXtrkseg(ptlist);
		seglist.add(trackseg);
		GPXtrk track = new GPXtrk("track",seglist);
		assertEquals(130,GPXcalculator.calculateDistanceTraveled(track),0);
	}
	//one point over 90. Should return 0.
	@Test
	public void latover90(){
		ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
		Date date = new Date();
		GPXtrkpt trackpt = new GPXtrkpt(100,30,date);
		ptlist.add(trackpt);
		Date date2 = new Date();
		GPXtrkpt trackpt2 = new GPXtrkpt(40,30,date2);
		ptlist.add(trackpt2);
		GPXtrkseg trackseg = new GPXtrkseg(ptlist);
		seglist.add(trackseg);
		GPXtrk track = new GPXtrk("track",seglist);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
	}
	//one point below 90. Should return 0.
		@Test
		public void latbelow90(){
			ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
			ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
			Date date = new Date();
			GPXtrkpt trackpt = new GPXtrkpt(-100,30,date);
			ptlist.add(trackpt);
			Date date2 = new Date();
			GPXtrkpt trackpt2 = new GPXtrkpt(40,30,date2);
			ptlist.add(trackpt2);
			GPXtrkseg trackseg = new GPXtrkseg(ptlist);
			seglist.add(trackseg);
			GPXtrk track = new GPXtrk("track",seglist);
			assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
		}
	//If any GPXtrkpt in a GPXtrkseg has a longitude that is greater than 180 or less than -180, the
	//distance traveled for that GPXtrkseg should be considered 0.
		//One point on 180. Should return 180-30 = 150
		@Test
		public void long180(){
			ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
			ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
			Date date = new Date();
			GPXtrkpt trackpt = new GPXtrkpt(40,180,date);
			ptlist.add(trackpt);
			Date date2 = new Date();
			GPXtrkpt trackpt2 = new GPXtrkpt(40,30,date2);
			ptlist.add(trackpt2);
			GPXtrkseg trackseg = new GPXtrkseg(ptlist);
			seglist.add(trackseg);
			GPXtrk track = new GPXtrk("track",seglist);
			assertEquals(150,GPXcalculator.calculateDistanceTraveled(track),0);
		}
		//One point on -180. Should return 30--180 = 210
		@Test
		public void longminus180(){
			ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
			ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
			Date date = new Date();
			GPXtrkpt trackpt = new GPXtrkpt(40,-180,date);
			ptlist.add(trackpt);
			Date date2 = new Date();
			GPXtrkpt trackpt2 = new GPXtrkpt(40,30,date2);
			ptlist.add(trackpt2);
			GPXtrkseg trackseg = new GPXtrkseg(ptlist);
			seglist.add(trackseg);
			GPXtrk track = new GPXtrk("track",seglist);
			assertEquals(210,GPXcalculator.calculateDistanceTraveled(track),0);
		}
		//one point over 180. Should return 0.
		@Test
		public void longover180(){
			ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
			ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
			Date date = new Date();
			GPXtrkpt trackpt = new GPXtrkpt(40,190,date);
			ptlist.add(trackpt);
			Date date2 = new Date();
			GPXtrkpt trackpt2 = new GPXtrkpt(40,30,date2);
			ptlist.add(trackpt2);
			GPXtrkseg trackseg = new GPXtrkseg(ptlist);
			seglist.add(trackseg);
			GPXtrk track = new GPXtrk("track",seglist);
			assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
		}
		//one point below 180. Should return 0.
			@Test
			public void longbelow180(){
				ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
				ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
				Date date = new Date();
				GPXtrkpt trackpt = new GPXtrkpt(40,-200,date);
				ptlist.add(trackpt);
				Date date2 = new Date();
				GPXtrkpt trackpt2 = new GPXtrkpt(40,30,date2);
				ptlist.add(trackpt2);
				GPXtrkseg trackseg = new GPXtrkseg(ptlist);
				seglist.add(trackseg);
				GPXtrk track = new GPXtrk("track",seglist);
				assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
			}
			
			//one point on boundary (90,180). Should return 30.
			@Test
			public void boundarypoint(){
				ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
				ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
				Date date = new Date();
				GPXtrkpt trackpt = new GPXtrkpt(90,180,date);
				ptlist.add(trackpt);
				Date date2 = new Date();
				GPXtrkpt trackpt2 = new GPXtrkpt(90,150,date2);
				ptlist.add(trackpt2);
				GPXtrkseg trackseg = new GPXtrkseg(ptlist);
				seglist.add(trackseg);
				GPXtrk track = new GPXtrk("track",seglist);
				assertEquals(30,GPXcalculator.calculateDistanceTraveled(track),0);
			}
			//Testing the other boundary
			@Test
			public void otherboundarypoint(){
				ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
				ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
				Date date = new Date();
				GPXtrkpt trackpt = new GPXtrkpt(-90,-180,date);
				ptlist.add(trackpt);
				Date date2 = new Date();
				GPXtrkpt trackpt2 = new GPXtrkpt(-90,-150,date2);
				ptlist.add(trackpt2);
				GPXtrkseg trackseg = new GPXtrkseg(ptlist);
				seglist.add(trackseg);
				GPXtrk track = new GPXtrk("track",seglist);
				assertEquals(30,GPXcalculator.calculateDistanceTraveled(track),0);
			}
			
			//Lat and Long both out of bound.
			@Test
			public void bothoutofbound(){
				ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
				ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
				Date date = new Date();
				GPXtrkpt trackpt = new GPXtrkpt(-100,-200,date);
				ptlist.add(trackpt);
				Date date2 = new Date();
				GPXtrkpt trackpt2 = new GPXtrkpt(30,-150,date2);
				ptlist.add(trackpt2);
				GPXtrkseg trackseg = new GPXtrkseg(ptlist);
				seglist.add(trackseg);
				GPXtrk track = new GPXtrk("track",seglist);
				assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
			}
			
			//Testing 0 distance. Should return 0.
			@Test
			public void zerodistance(){
				ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
				ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
				Date date = new Date();
				GPXtrkpt trackpt = new GPXtrkpt(50,50,date);
				ptlist.add(trackpt);
				Date date2 = new Date();
				GPXtrkpt trackpt2 = new GPXtrkpt(50,50,date2);
				ptlist.add(trackpt2);
				GPXtrkseg trackseg = new GPXtrkseg(ptlist);
				seglist.add(trackseg);
				GPXtrk track = new GPXtrk("track",seglist);
				assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),0);
			}
			
			//Multiple points. Testing the sum of distance. 50+10 = 60.
			@Test
			public void multiplepoints(){
				ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
				ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
				Date date = new Date();
				GPXtrkpt trackpt = new GPXtrkpt(40,50,date);
				ptlist.add(trackpt);
				Date date2 = new Date();
				GPXtrkpt trackpt2 = new GPXtrkpt(40,100,date2);
				ptlist.add(trackpt2);
				Date date3 = new Date();
				GPXtrkpt trackpt3 = new GPXtrkpt(50,100,date3);
				ptlist.add(trackpt3);
				GPXtrkseg trackseg = new GPXtrkseg(ptlist);
				seglist.add(trackseg);
				GPXtrk track = new GPXtrk("track",seglist);
				assertEquals(60,GPXcalculator.calculateDistanceTraveled(track),0);
			}
			//Multiple segments. Testing the sum of distance. 50+10+10 = 70.
			@Test
			public void multiplesegs(){
				ArrayList<GPXtrkpt> ptlist = new ArrayList<GPXtrkpt>();
				ArrayList<GPXtrkpt> ptlist2 = new ArrayList<GPXtrkpt>();
				ArrayList<GPXtrkseg> seglist = new ArrayList<GPXtrkseg>();
				Date date = new Date();
				GPXtrkpt trackpt = new GPXtrkpt(40,50,date);
				ptlist.add(trackpt);
				Date date2 = new Date();
				GPXtrkpt trackpt2 = new GPXtrkpt(40,100,date2);
				ptlist.add(trackpt2);
				Date date3 = new Date();
				GPXtrkpt trackpt3 = new GPXtrkpt(50,100,date3);
				ptlist2.add(trackpt3);
				Date date4 = new Date();
				GPXtrkpt trackpt4 = new GPXtrkpt(50,120,date4);
				ptlist2.add(trackpt4);
				GPXtrkseg trackseg = new GPXtrkseg(ptlist);
				seglist.add(trackseg);
				GPXtrkseg trackseg2 = new GPXtrkseg(ptlist2);
				seglist.add(trackseg2);
				GPXtrk track = new GPXtrk("track",seglist);
				assertEquals(70,GPXcalculator.calculateDistanceTraveled(track),0);
			}
}
