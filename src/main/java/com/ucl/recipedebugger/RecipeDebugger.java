package com.ucl.recipedebugger;

import java.lang.reflect.Field;
import java.util.Collections;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;



@Mod(modid = RecipeDebugger.MODID, version = RecipeDebugger.VERSION)
public class RecipeDebugger implements INotify {
	
	public static final String MODID = "RecipeDebugger";
    public static final String VERSION = "0.1";
    
    @Override
	public void NotifyAdded(Object added) {
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
    	NotifyingList<IRecipe> newList = new NotifyingList<IRecipe>(CraftingManager.getInstance().getRecipeList());
    	newList.AddListener(this);
    	Field field = null;
		field = ReflectionHelper.findField(CraftingManager.class, "field_77597_b", "recipes");
    	field.setAccessible(true);
    	try {
    		field.set(CraftingManager.getInstance(), newList);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }

	

	
}
