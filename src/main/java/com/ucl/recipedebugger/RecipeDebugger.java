package com.ucl.recipedebugger;

import java.lang.reflect.Field;
import java.util.List;









import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;



@Mod(modid = RecipeDebugger.MODID, version = RecipeDebugger.VERSION)
public class RecipeDebugger {
	
	public static final String MODID = "RecipeDebugger";
    public static final String VERSION = "0.1";

	private CraftingManager cm;
	private List<IRecipe> startList;
	private List<IRecipe> endList;
	private String sdn;
	private String sun;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
    	//cm = CraftingManager.getInstance();
    	ObservableList<IRecipe> newObsList = FXCollections.observableList(CraftingManager.getInstance().getRecipeList());
    	
    	newObsList.addListener(new ListChangeListener<IRecipe>() {

			public void onChanged(
				javafx.collections.ListChangeListener.Change<? extends IRecipe> change) {
				while (change.next()) {
					if (change.wasAdded()){
						List added = change.getAddedSubList();
						for (Object o : added) {
							if(o instanceof IRecipe);
							{
								
								IRecipe r = (IRecipe)o;
								ItemStack s = r.getRecipeOutput();
								String dn = "";
								String un = "";
								//if(s.hasDisplayName()) 
								dn = s.getDisplayName();
								un = s.getUnlocalizedName();
								UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(s.getItem());
															
								
								System.out.println("Recipe Added by " + id.modId + " [" + dn + "] - [" + un + "]");
									
							}
							
						}
						//System.out.println("Recipe Added!");
					}
					if (change.wasRemoved())
					{
						List removed = change.getAddedSubList();
						for (Object o : removed) {
							if(o instanceof IRecipe);
							{
								
								IRecipe r = (IRecipe)o;
								ItemStack s = r.getRecipeOutput();
								String dn = "";
								String un = "";
								//if(s.hasDisplayName()) 
								dn = s.getDisplayName();
								un = s.getUnlocalizedName();
								UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(s.getItem());
															
								
								System.out.println("Recipe Removed by " + id.modId + " [" + dn + "] - [" + un + "]");
									
							}
							
						}
					}

				}
			}
		});
    	
    	Field field = null;
    	
    	try {
			field = CraftingManager.class.getDeclaredField("recipes");
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	field.setAccessible(true);
    	try {
    		field.set(CraftingManager.getInstance(), newObsList);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println(CraftingManager.getInstance().getRecipeList().size());
    } 
}
