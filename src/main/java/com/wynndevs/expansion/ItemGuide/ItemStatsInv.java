package com.wynndevs.expansion.ItemGuide;

import com.wynndevs.expansion.ExpReference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class ItemStatsInv {
	
	private static boolean Active = false;
	
	@SubscribeEvent
	public void InputEventKeyInputEvent(GuiScreenEvent.KeyboardInputEvent.Pre event){
		if (ExpReference.inGame()){
			if ((event.getGui() instanceof GuiContainer) == false || event.getGui().mc == null || event.getGui().mc.player == null){
				return;
			}
				if (Keyboard.getEventKey() == 17){
						if (Active){
							Active = false;
						}else{
							Active = true;
						}
				}
		}
	}
	
	
	@SubscribeEvent
	public void onMouseInputEventPre(GuiScreenEvent.MouseInputEvent.Pre event){
		if (ExpReference.inGame()){
			if ((event.getGui() instanceof GuiContainer) == false || event.getGui().mc == null || event.getGui().mc.player == null){
				return;
			}
			Slot InvSlot = ((GuiContainer) event.getGui()).getSlotUnderMouse();
			
			if (InvSlot != null && InvSlot.inventory.getName().equals("container.inventory")){
				if (Active) {
					
				}
			}
		}
	}
}
