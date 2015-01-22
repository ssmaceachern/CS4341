package player;

import java.util.Comparator;

public class Threat {

	public int x;
	public int y;
	public int streak;
	public int potentialStreak;
	public boolean sign;
	public int threat;
	
	public Threat(int x, int y, int streak, int potentialStreak, boolean sign)
	{
		this.x=x;
		this.y=y;
		this.streak=streak;
		this.potentialStreak=potentialStreak;
		this.sign=sign;
		threat = y%2+1;
	}
	
	@SuppressWarnings("rawtypes")
	public static Comparator getComparator()
	{
		return new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				
				Threat t1 = (Threat)o1;
				Threat t2 = (Threat)o2;
				
				if (t1.x>t2.x)
					return 1; //greater
				if (t1.x==t2.x) {
					if (t1.y>t2.y)
						return 1; //greater
					if (t1.y==t2.y)
						return 0; //equal, impossible
					if (t1.y<t2.y)
						return -1;
				}
				if (t1.x<t2.x)
					return -1;
				
				return 0;
			}
		};
	}
}
