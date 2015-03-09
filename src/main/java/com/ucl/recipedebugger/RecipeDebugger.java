package com.ucl.recipedebugger;

import java.lang.reflect.Field;
import java.util.Collections;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;



@Mod(modid = RecipeDebugger.MODID, version = RecipeDebugger.VERSION)
public class RecipeDebugger implements INotify {
	
	public static final String MODID = "RecipeDebugger";
    public static final String VERSION = "0.1";
    
    @Override
	public void NotifyAdded(Object added) {
		//System.out.println("Added!");
		
		IRecipe r = (IRecipe)added;
		ItemStack s = r.getRecipeOutput();
		String dn = "";
		String un = "";
		dn = s.getDisplayName();
		un = s.getUnlocalizedName();
		UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(s.getItem());
		System.out.println("Recipe Added by " + id.modId + " [" + dn + "] - [" + un + "]");
		
	}

	@Override
	public void NotifyRemoved(Object removed) {
		//System.out.println("Removed!");
		IRecipe r = (IRecipe)removed;
		ItemStack s = r.getRecipeOutput();
		String dn = "";
		String un = "";
		dn = s.getDisplayName();
		un = s.getUnlocalizedName();
		UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(s.getItem());
		System.out.println("Recipe Removed by " + id.modId + " [" + dn + "] - [" + un + "]");
		
	}
     
    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
    	//NotifyingList<IRecipe> newObsList = FXCollections.observableList(CraftingManager.getInstance().getRecipeList());
    	NotifyingList<IRecipe> newList = new NotifyingList<IRecipe>(CraftingManager.getInstance().getRecipeList());
    	//Collections.copy(newList, CraftingManager.getInstance().getRecipeList());
    	
    
    	newList.AddListener(this);
    	
//    	@Override
//    	public void NotifyAdded() {
//    		System.out.println("Added!");
//    		
//    	}
//
//    	@Override
//    	public void NotifyRemoved() {
//    		System.out.println("Removed!");
//    	} 
    	
    	
//    	newObsList.addListener(new ListChangeListener<IRecipe>() {
//			public void onChanged(
//				javafx.collections.ListChangeListener.Change<? extends IRecipe> change) {
//				while (change.next()) {
//					if (change.wasAdded()){
//						List added = change.getAddedSubList();
//						for (Object o : added) {
//							if(o instanceof IRecipe);
//							{
//								IRecipe r = (IRecipe)o;
//								ItemStack s = r.getRecipeOutput();
//								String dn = "";
//								String un = "";
//								dn = s.getDisplayName();
//								un = s.getUnlocalizedName();
//								UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(s.getItem());
//								System.out.println("Recipe Added by " + id.modId + " [" + dn + "] - [" + un + "]");
//							}
//						}
//					}
//					if (change.wasRemoved())
//					{
//						List removed = change.getAddedSubList();
//						for (Object o : removed) {
//							if(o instanceof IRecipe);
//							{
//								IRecipe r = (IRecipe)o;
//								ItemStack s = r.getRecipeOutput();
//								String dn = "";
//								String un = "";
//								dn = s.getDisplayName();
//								un = s.getUnlocalizedName();
//								UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(s.getItem());
//								System.out.println("Recipe Removed by " + id.modId + " [" + dn + "] - [" + un + "]");
//							}
//						}
//					}
//
//				}
//			}
//		});
    	Field field = null;
    	try {
			field = CraftingManager.class.getDeclaredField("recipes");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
    	field.setAccessible(true);
    	try {
    		field.set(CraftingManager.getInstance(), newList);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    	System.out.println(CraftingManager.getInstance().getRecipeList().size());
    }

	

	
}
