package com.ucl.recipedebugger;

import java.util.List;



import net.minecraft.item.crafting.CraftingManager;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RDEventHandler {
	CraftingManager cm;
	List oldList;
	List newList;
	
	
	@SubscribeEvent
	public void OnModLoaded(FMLLoadCompleteEvent event){
		cm = CraftingManager.getInstance();
		if (oldList == null) {
			oldList = cm.getRecipeList();
		}
		else {
			newList = cm.getRecipeList();
			
			// Get oldList - newlist = temp
			// oldlist.count != templist.count > Removed?
			// get name of removed recipe
			List tempList = oldList;
			tempList.removeAll(newList);
			int sizediff = tempList.size() - oldList.size();
			if(sizediff > 0)
				System.out.println("***** Possible Recipe Removed! ***** Diff " +  sizediff);
			
		}
		

	}
	

}
