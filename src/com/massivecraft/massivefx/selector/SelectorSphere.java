package com.massivecraft.massivefx.selector;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.massivecraft.massivefx.P;

public class SelectorSphere extends SelectorAbstract
{
	public Collection<Object> getSelection(String data, Object origin)
	{
		ArrayList<Object> ret = new ArrayList<Object>();
		
		int radius = 0;
		try
		{
			radius = Integer.parseInt(data);
		}
		catch (Exception e){}
		if (radius > 5)
		{
			radius = 5;
		}
		
		Location originLocation = P.p.getLocation(origin);
		
		if (originLocation == null) return ret;
		Block centerBlock = originLocation.getBlock();
		
		int xFrom = -radius;
		int xTo = +radius;
		int yFrom = -radius;
		int yTo = +radius;
		int zFrom = -radius;
		int zTo = +radius;
		
		for(int y=yFrom; y<=yTo; y++)
		{
			for(int z=zFrom; z<=zTo; z++)
			{
				for(int x=xFrom; x<=xTo; x++)
				{
					if( x*x+y*y+z*z <= radius*radius)
					{
						ret.add(centerBlock.getRelative(x, -y, z).getLocation());
					}
				}
			}
		}
		
		return ret;
	}
	
	private SelectorSphere()
	{
		super("sphere", "Select sphere.", "sphere:2");
	}
	private static SelectorSphere instance = new SelectorSphere();
	public static SelectorSphere getInstance() { return instance; }
}
